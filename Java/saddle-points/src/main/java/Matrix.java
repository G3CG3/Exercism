import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Matrix {
    private final List<List<Integer>> values;

    public Matrix(List<List<Integer>> values) {
        this.values = values;
    }

    Set<MatrixCoordinate> getSaddlePoints() {
        Set<MatrixCoordinate> saddlePoints = new HashSet<>();
        int rows = values.size();
        int cols = rows > 0 ? values.getFirst().size() : 0;

        for (int r = 0; r < rows; r++) {
            int maxInRow = Collections.max(values.get(r));

            for (int c = 0; c < cols; c++) {
                int minInCol = Integer.MAX_VALUE;
                for (int row = 0; row < rows; row++) {
                    minInCol = Math.min(minInCol, values.get(row).get(c));
                }

                if (values.get(r).get(c) == maxInRow && values.get(r).get(c) == minInCol) {
                    saddlePoints.add(new MatrixCoordinate(r + 1, c + 1));
                }
            }
        }
        return saddlePoints;
    }
}
