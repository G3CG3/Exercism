import java.awt.Point;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class GoCounting {
    private final char[][] board; // Access through y then x (ex: this.board[y][x])
    public GoCounting(String board) {
        this.board = Arrays.stream(board.split("\n"))
                .map(String::toCharArray)
                .toList()
                .toArray(new char[0][]);
    }
    public Player getTerritoryOwner(int x, int y) {
        if (!validateCoordinate(x, y)) {
            throw new IllegalArgumentException("Invalid coordinate");
        }
        if (this.board[y][x] == ' ') {
            Set<Point> territory = getTerritory(x, y);
            Set<Player> players = territory.stream()
                    .map(this::getPlayersAroundPoint)
                    .flatMap(Collection::stream)
                    .filter(p -> p != Player.NONE)
                    .collect(Collectors.toSet());
            return players.size() == 1 ? players.stream().findFirst().get() : Player.NONE;
        } else {
            return Player.NONE;
        }
    }
    private Set<Player> getPlayersAroundPoint(Point point) {
        Set<Player> players = new HashSet<>();
        if (point.x - 1 >= 0) {
            players.add(getPlayer(point.x - 1, point.y));
        }
        return new HashSet<>(Arrays.asList(
                getPlayer(point.x - 1, point.y),
                getPlayer(point.x + 1, point.y),
                getPlayer(point.x, point.y - 1),
                getPlayer(point.x, point.y + 1)));
    }
    private Player getPlayer(int x, int y) {
        if (validateCoordinate(x, y)) {
            return this.board[y][x] == 'B' ? Player.BLACK : (this.board[y][x] == 'W' ? Player.WHITE : Player.NONE);
        }
        return Player.NONE;
    }
    public Set<Point> getTerritory(int x, int y) {
        if (!validateCoordinate(x, y)) {
            throw new IllegalArgumentException("Invalid coordinate");
        }
        if (this.board[y][x] == ' ') {
            Set<Point> territory = new HashSet<>();
            territory.add(new Point(x, y));
            findNeighborPoints(territory, x, y);
            return territory;
        } else {
            return Set.of();
        }
    }
    private void findNeighborPoints(Set<Point> territory, int x, int y) {
        if (validateCoordinate(x - 1, y) && this.board[y][x - 1] == ' ') {
            addPoint(territory, x - 1, y);
        }
        if (validateCoordinate(x + 1, y) && this.board[y][x + 1] == ' ') {
            addPoint(territory, x + 1, y);
        }
        if (validateCoordinate(x, y - 1) && this.board[y - 1][x] == ' ') {
            addPoint(territory, x, y - 1);
        }
        if (validateCoordinate(x, y + 1) && this.board[y + 1][x] == ' ') {
            addPoint(territory, x, y + 1);
        }
    }
    private void addPoint(Set<Point> territory, int x, int y) {
        Point p = new Point(x, y);
        if (!territory.contains(p)) {
            territory.add(p);
            findNeighborPoints(territory, p.x, p.y);
        }
    }
    public HashMap<Player, Set<Point>> getTerritories() {
        Set<Point> alreadyAttributed = new HashSet<>();
        HashMap<Player, Set<Point>> result = new HashMap<>();
        result.put(Player.BLACK, new HashSet<>());
        result.put(Player.WHITE, new HashSet<>());
        result.put(Player.NONE, new HashSet<>());
        IntStream.range(0, this.board.length).forEach(y ->
                IntStream.range(0, this.board[y].length).forEach(x -> {
                            Point p = new Point(x, y);
                            if (!alreadyAttributed.contains(p)) {
                                result.get(getTerritoryOwner(p.x, p.y)).addAll(getTerritory(p.x, p.y));
                            }
                            alreadyAttributed.add(p);
                        }
                )
        );
        return result;
    }
    private boolean validateCoordinate(int x, int y) {
        return y >= 0 && y < board.length && x >= 0 && x < board[y].length;
    }
}