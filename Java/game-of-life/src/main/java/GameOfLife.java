class GameOfLife {
    private static final int[][] DIRECTIONS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},         {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };

    public int[][] tick(int[][] matrix){
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0][0];
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] nextBoard = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int liveNeighbors = 0;
                for (int[] dir : DIRECTIONS) {
                    int nr = r + dir[0], nc = c + dir[1];
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && matrix[nr][nc] == 1) {
                        liveNeighbors++;
                    }
                }
                if (matrix[r][c] == 1) {
                    nextBoard[r][c] = (liveNeighbors == 2 || liveNeighbors == 3) ? 1 : 0;
                } else {
                    nextBoard[r][c] = (liveNeighbors == 3) ? 1 : 0;
                }
            }
        }
        return nextBoard;
    }
}
