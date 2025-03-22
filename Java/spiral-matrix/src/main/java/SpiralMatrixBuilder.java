class SpiralMatrixBuilder {
    int[][] buildMatrixOfSize(int size) {
        int[][] matrix = new int[size][size];
        int value = 1;
        int left = 0;
        int right = size - 1;
        int top = 0;
        int bottom = size - 1;

        while (value <= size * size) {
            // Move left to right
            for (int i = left; i <= right; i++) {
                matrix[top][i] = value++;
            }
            top++;
            // Move top to bottom
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = value++;
            }
            right--;
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = value++;
            }
            bottom--;
            // Move bottom to top
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = value++;
            }
            left++;
        }
        return  matrix;
    }
}
