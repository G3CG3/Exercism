public class Hamming {
    private final String leftStrand;
    private final String rightStrand;
    private int distance = 0;

    public Hamming(String leftStrand, String rightStrand) {
        if (leftStrand.length() != rightStrand.length()) {
            throw new IllegalArgumentException("strands must be of equal length");
        }
        this.leftStrand = leftStrand;
        this.rightStrand = rightStrand;

        for (int i = 0; i < leftStrand.length(); i++) {
            if (leftStrand.charAt(i) != rightStrand.charAt(i)){
                distance ++;
            }
        }
    }

    public int getHammingDistance() {
        return distance;
    }
}
