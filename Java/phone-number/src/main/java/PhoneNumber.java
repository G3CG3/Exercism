public class PhoneNumber {
    private String number;

    public PhoneNumber(String numberString) {
        this.number = cleanNumber(numberString);
    }

    private String cleanNumber(String numberString) {
        // Remove all non-digit characters
        String cleaned = numberString.replaceAll("\\D", "");

        // Check if the input contains letters
        if (numberString.matches(".*[a-zA-Z].*")) {
            throw new IllegalArgumentException("letters not permitted");
        }

        // Check if the input contains punctuations before cleaning
        if (numberString.matches(".*[^\\d\\s().+-].*")) {
            throw new IllegalArgumentException("punctuations not permitted");
        }

        // Check for country code and remove if necessary
        if (cleaned.length() == 11) {
            if (cleaned.startsWith("1")) {
                cleaned = cleaned.substring(1);
            } else {
                throw new IllegalArgumentException("11 digits must start with 1");
            }
        }

        // Ensure number has exactly 10 digits
        if (cleaned.length() != 10) {
            throw new IllegalArgumentException(cleaned.length() < 10 ? "must not be fewer than 10 digits" : "must not be greater than 11 digits");
        }

        // Check if area code starts with 0 or 1
        if (cleaned.charAt(0) == '0') {
            throw new IllegalArgumentException("area code cannot start with zero");
        }
        if (cleaned.charAt(0) == '1') {
            throw new IllegalArgumentException("area code cannot start with one");
        }

        // Check if exchange code starts with 0 or 1
        if (cleaned.charAt(3) == '0') {
            throw new IllegalArgumentException("exchange code cannot start with zero");
        }
        if (cleaned.charAt(3) == '1') {
            throw new IllegalArgumentException("exchange code cannot start with one");
        }

        return cleaned;
    }

    public String getNumber() {
        return number;
    }
}