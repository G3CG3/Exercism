class RunLengthEncoding {

    String encode(String data) {
        if (data == null || data.isEmpty()) {
            return "";
        }

        StringBuilder encoded = new StringBuilder();
        int count = 1;

        for (int i = 1; i < data.length(); i++) {
            if (data.charAt(i) == data.charAt(i - 1)) {
                count++;
            } else {
                if (count > 1) {
                    encoded.append(count);
                }
                encoded.append(data.charAt(i - 1));
                count = 1;
            }
        }

        if (count > 1) {
            encoded.append(count);
        }
        encoded.append(data.charAt(data.length() - 1));
        return encoded.toString();
    }

    String decode(String data) {
        if (data == null || data.isEmpty()) {
            return "";
        }

        StringBuilder decoded = new StringBuilder();
        StringBuilder numberBuffer = new StringBuilder();

        for (char ch : data.toCharArray()) {
            if (Character.isDigit(ch)) {
                numberBuffer.append(ch);
            } else {
                int count = !numberBuffer.isEmpty() ? Integer.parseInt(numberBuffer.toString()) : 1;
                decoded.append(String.valueOf(ch).repeat(count));
                numberBuffer.setLength(0);
            }
        }

        return decoded.toString();
    }

}