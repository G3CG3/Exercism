import java.util.HashMap;
import java.util.Map;

class NucleotideCounter {
    private final Map<Character, Integer> counts;

    NucleotideCounter(String sequence) {
        this.counts = new HashMap<>();
        counts.put('A', 0);
        counts.put('C', 0);
        counts.put('G', 0);
        counts.put('T', 0);

        for (char nucleotide : sequence.toCharArray()) {
            if (!counts.containsKey(nucleotide)) {
                throw new IllegalArgumentException("Invalid DNA sequence: contains non-nucleotide characters.");
            }
            counts.put(nucleotide, counts.get(nucleotide) + 1);
        }
    }

    Map<Character, Integer> nucleotideCounts() {
        return counts;
    }

}