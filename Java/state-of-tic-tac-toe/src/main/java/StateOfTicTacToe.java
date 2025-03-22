class StateOfTicTacToe {
    public GameState determineState(String[] board) {
        char[][] grid = new char[3][3];
        int xCount = 0, oCount = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = board[i].charAt(j);
                if (grid[i][j] == 'X') xCount++;
                if (grid[i][j] == 'O') oCount++;
            }
        }

        if (oCount > xCount) {
            throw new IllegalArgumentException("Wrong turn order: O started");
        }
        if (xCount - oCount > 1) {
            throw new IllegalArgumentException("Wrong turn order: X went twice");
        }

        boolean xWins = checkWinner(grid, 'X');
        boolean oWins = checkWinner(grid, 'O');

        if (xWins && oWins) {
            throw new IllegalArgumentException("Impossible board: game should have ended after the game was won");
        }
        if (xWins && xCount == oCount) {
            throw new IllegalArgumentException("Wrong turn order: X went twice");
        }
        if (oWins && xCount > oCount) {
            throw new IllegalArgumentException("Wrong turn order: O started");
        }

        if (xWins || oWins) return GameState.WIN;

        for (char[] row : grid) {
            for (char cell : row) {
                if (cell == ' ') return GameState.ONGOING;
            }
        }
        return GameState.DRAW;
    }

    private boolean checkWinner(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true; // Rows
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true; // Columns
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true; // Diagonal \
        return board[0][2] == player && board[1][1] == player && board[2][0] == player; // Diagonal /
    }
}
