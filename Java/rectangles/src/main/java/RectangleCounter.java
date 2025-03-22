class RectangleCounter {

    int countRectangles(String[] grid) {
        if (grid == null || grid.length == 0 || grid[0].isEmpty()) {
            return 0; // Handle empty input
        }


        int rows = grid.length;
        int cols = grid[0].length();
        int count = 0;

        // Iterate over all possible top-left corners
        for (int r1 = 0; r1 < rows; r1++) {
            for (int c1 = 0; c1 < cols; c1++) {
                if (grid[r1].charAt(c1) == '+') { // Top-left corner
                    // Iterate over all possible bottom-right corners
                    for (int r2 = r1 + 1; r2 < rows; r2++) {
                        for (int c2 = c1 + 1; c2 < cols; c2++) {
                            if (grid[r2].charAt(c2) == '+') { // Bottom-right corner
                                if (isValidRectangle(grid, r1, c1, r2, c2)) {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    private static boolean isValidRectangle(String[] grid, int r1, int c1, int r2, int c2) {
        // Check top and bottom edges
        for (int c = c1; c <= c2; c++) {
            if (grid[r1].charAt(c) != '+' && grid[r1].charAt(c) != '-') return false;
            if (grid[r2].charAt(c) != '+' && grid[r2].charAt(c) != '-') return false;
        }
        // Check left and right edges
        for (int r = r1; r <= r2; r++) {
            if (grid[r].charAt(c1) != '+' && grid[r].charAt(c1) != '|') return false;
            if (grid[r].charAt(c2) != '+' && grid[r].charAt(c2) != '|') return false;
        }
        return true;
    }
}