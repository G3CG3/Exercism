import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HighScores {
    private final List<Integer> scores;

    public HighScores(List<Integer> highScores) {
        if (highScores.isEmpty()) {
            throw new IllegalArgumentException("The highScores list cannot be null.");
        }
        scores = new ArrayList<>(highScores);
    }

    List<Integer> scores() {
        return new ArrayList<>(scores);
    }

    Integer latest() {
        return scores.getLast();
    }

    Integer personalBest() {
        return Collections.max(scores);
    }

    List<Integer> personalTopThree() {
        List<Integer> topScores = new ArrayList<>(scores);
        topScores.sort((a, b) -> b - a);
        return topScores.size() > 3 ? topScores.subList(0,3) : topScores;
    }

}
