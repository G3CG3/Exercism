class Connect {
    private final char[][] board;
    private final int rows;
    private final int cols;

    public Connect(String[] boardInput) {
        this.rows = boardInput.length;
        this.cols = boardInput[boardInput.length - 1].replaceAll(" ", "").length();
        this.board = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            String row = boardInput[i].replaceAll(" ", "");
            for (int j = 0; j < row.length(); j++) {
                board[i][j] = row.charAt(j);
            }
        }
    }

    public Winner computeWinner() {
        if (hasPath('O')) return Winner.PLAYER_O;
        if (hasPath('X')) return Winner.PLAYER_X;
        return Winner.NONE;
    }

    private boolean hasPath(char player) {
        boolean[][] visited = new boolean[rows][cols];

        if (player == 'O') {
            for (int col = 0; col < cols; col++) {
                if (board[0][col] == 'O' && dfs(0, col, visited, 'O')) {
                    return true;
                }
            }
        } else {
            for (int row = 0; row < rows; row++) {
                if (board[row][0] == 'X' && dfs(row, 0, visited, 'X')) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int r, int c, boolean[][] visited, char player) {
        if (player == 'O' && r == rows - 1) return true;
        if (player == 'X' && c == cols - 1) return true;

        visited[r][c] = true;

        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1}, {1, -1}
        };

        for (int[] dir : directions) {
            int nr = r + dir[0], nc = c + dir[1];
            if (isValid(nr, nc) && !visited[nr][nc] && board[nr][nc] == player) {
                if (dfs(nr, nc, visited, player)) return true;
            }
        }
        return false;
    }

    private boolean isValid(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }
}