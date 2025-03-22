import java.util.*;

public class MazeGenerator {
    private static final char WALL = '┌';
    private static final char PASSAGE = ' ';
    private static final char ENTRANCE = '⇨';
    private static final char EXIT = '⇨';

    private char[][] maze;
    private Random random;

    public MazeGenerator() {}

    private void initializeMaze(int rows, int cols) {
        if (rows < 5 || rows > 100 || cols < 5 || cols > 100) {
            throw new IllegalArgumentException("Rows and columns must be between 5 and 100.");
        }
        maze = new char[2 * rows + 1][2 * cols + 1];
        for (char[] chars : maze) {
            Arrays.fill(chars, WALL);
        }
    }

    private void generateMaze(int x, int y) {
        maze[x][y] = PASSAGE;
        List<int[]> directions = Arrays.asList(
                new int[]{-2, 0}, new int[]{2, 0}, new int[]{0, -2}, new int[]{0, 2}
        );
        Collections.shuffle(directions, random);

        for (int[] dir : directions) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx > 0 && ny > 0 && nx < maze.length - 1 && ny < maze[0].length - 1 && maze[nx][ny] == WALL) {
                maze[x + dir[0] / 2][y + dir[1] / 2] = PASSAGE;
                generateMaze(nx, ny);
            }
        }
    }

    private void setEntranceAndExit() {
        maze[1][0] = ENTRANCE;
        maze[maze.length - 2][maze[0].length - 1] = EXIT;
    }

    public char[][] generatePerfectMaze(int rows, int columns) {
        random = new Random();
        initializeMaze(rows, columns);
        generateMaze(1, 1);
        setEntranceAndExit();
        return maze;
    }

    public char[][] generatePerfectMaze(int rows, int columns, int seed) {
        random = new Random(seed);
        initializeMaze(rows, columns);
        generateMaze(1, 1);
        setEntranceAndExit();
        return maze;
    }
}
