import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ProteinTranslator {
    private static final Map<String, String> CODON_TABLE = new HashMap<>();

    static {
        CODON_TABLE.put("AUG", "Methionine");
        CODON_TABLE.put("UUU", "Phenylalanine");
        CODON_TABLE.put("UUC", "Phenylalanine");
        CODON_TABLE.put("UUA", "Leucine");
        CODON_TABLE.put("UUG", "Leucine");
        CODON_TABLE.put("UCU", "Serine");
        CODON_TABLE.put("UCC", "Serine");
        CODON_TABLE.put("UCA", "Serine");
        CODON_TABLE.put("UCG", "Serine");
        CODON_TABLE.put("UAU", "Tyrosine");
        CODON_TABLE.put("UAC", "Tyrosine");
        CODON_TABLE.put("UGU", "Cysteine");
        CODON_TABLE.put("UGC", "Cysteine");
        CODON_TABLE.put("UGG", "Tryptophan");
        CODON_TABLE.put("UAA", "STOP");
        CODON_TABLE.put("UAG", "STOP");
        CODON_TABLE.put("UGA", "STOP");
    }

    List<String> translate(String rnaSequence) {
        List<String> protein = new ArrayList<>();

        for (int i = 0; i < rnaSequence.length(); i += 3) {
            if (i + 3 > rnaSequence.length()) {
                throw new IllegalArgumentException("Invalid codon");
            }

            String codon = rnaSequence.substring(i, i + 3);

            if (!CODON_TABLE.containsKey(codon)) {
                throw new IllegalArgumentException("Invalid codon");
            }

            String aminoAcid = CODON_TABLE.get(codon);
            if (aminoAcid.isEmpty() || aminoAcid.equals("STOP")) {
                break;
            }
            protein.add(aminoAcid);
        }
        return protein;
    }
}
