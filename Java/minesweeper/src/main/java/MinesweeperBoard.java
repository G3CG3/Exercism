import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MinesweeperBoard {
    private final List<String> BOARD;

    MinesweeperBoard(List<String> boardRows) {
        this.BOARD = boardRows;
    }

    List<String> withNumbers() {
        if (BOARD.isEmpty()) {
            return Collections.emptyList();
        }

        if (BOARD.getFirst().isEmpty()){
            return new ArrayList<>(List.of(""));
        }

        int rows = BOARD.size();
        int cols = BOARD.getFirst().length();
        char[][] result = new char[rows][cols];

        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},          {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (BOARD.get(r).charAt(c) == '*') {
                    result[r][c] = '*';
                    continue;
                }

                int mineCount = 0;
                for (int[] dir : directions) {
                    int nr = r + dir[0], nc = c + dir[1];
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && BOARD.get(nr).charAt(nc) == '*') {
                        mineCount++;
                    }
                }

                result[r][c] = mineCount > 0 ? (char) ('0' + mineCount) : ' ';
            }
        }

        List<String> transformedBoard = new ArrayList<>();
        for (char[] row : result) {
            transformedBoard.add(new String(row));
        }
        return transformedBoard;
    }

}