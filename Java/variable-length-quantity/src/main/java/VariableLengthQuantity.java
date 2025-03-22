import java.util.ArrayList;
import java.util.List;

class VariableLengthQuantity {

    List<String> encode(List<Long> numbers) {
        List<String> encoded = new ArrayList<>();
        for (long number : numbers) {
            List<String> bytes = new ArrayList<>();
            do {
                int byteVal = (int) (number & 0x7F);
                number >>= 7;
                if (!bytes.isEmpty()) {
                    byteVal |= 0x80;
                }
                bytes.add(0, String.format("0x%x", byteVal)); // Ensure lowercase hex
            } while (number > 0);
            encoded.addAll(bytes);
        }
        return encoded;
    }

    List<String> decode(List<Long> bytes) {
        List<String> decoded = new ArrayList<>();
        long value = 0;
        boolean hasIncompleteSequence = false;

        for (long b : bytes) {
            hasIncompleteSequence = true; // We assume it's incomplete unless terminated properly
            value = (value << 7) | (b & 0x7F);

            if ((b & 0x80) == 0) { // Last byte in the VLQ sequence
                decoded.add(String.format("0x%x", value));
                value = 0;
                hasIncompleteSequence = false; // Sequence ended correctly
            }
        }

        if (hasIncompleteSequence) {
            throw new IllegalArgumentException("Invalid variable-length quantity encoding");
        }
        return decoded;
    }
}
