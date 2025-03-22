import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Matrix {
    private final List<List<Integer>> rows;

    Matrix(String matrixAsString) {
        this.rows = Arrays.stream(matrixAsString.split("\\n"))
                .map(line -> Arrays.stream(line.trim().split("\\s+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    int[] getRow(int rowNumber) {
        return rows.get(rowNumber - 1).stream().mapToInt(Integer::intValue).toArray();
    }

    int[] getColumn(int columnNumber) {
        return rows.stream().map(row -> row.get(columnNumber - 1)).mapToInt(Integer::intValue).toArray();
    }
}
