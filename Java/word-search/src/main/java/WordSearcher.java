import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

class WordSearcher {
    Map<String, Optional<WordLocation>> search(final Set<String> words, final char[][] grid) {
        return words
                .stream()
                .collect(Collectors.toMap(word -> word, word -> findLocation(word, grid)));
    }

    private Optional<WordLocation> findLocation(String word, char[][] grid) {
        return find(word, grid, 0, 1)
                .or(() -> find(word, grid, 0, -1))
                .or(() -> find(word, grid, 1, 0))
                .or(() -> find(word, grid, -1, 0))
                .or(() -> find(word, grid, 1, 1))
                .or(() -> find(word, grid, -1, -1))
                .or(() -> find(word, grid, 1, -1))
                .or(() -> find(word, grid, -1, 1));
    }

    private Optional<WordLocation> find(String word, char[][] grid, int dRow, int dCol) {
        int startRow = Math.max(0, word.length() * -dRow - 1);
        int endRow = grid.length - Math.max(0, (word.length() - 1) * dRow);
        for (int row = startRow; row < endRow; row++) {
            int startCol = Math.max(0, word.length() * -dCol - 1);
            int endCol = grid[row].length - Math.max(0, (word.length() - 1) * dCol);
            search:
            for (int col = startCol; col < endCol; col++) {
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) != grid[row + i * dRow][col + i * dCol]) {
                        continue search;
                    }
                }

                return Optional.of(new WordLocation(
                        new Pair(col + 1, row + 1),
                        new Pair(col + 1 + (word.length() - 1) * dCol, row + 1 + (word.length() - 1) * dRow)
                ));
            }
        }
        return Optional.empty();
    }
}