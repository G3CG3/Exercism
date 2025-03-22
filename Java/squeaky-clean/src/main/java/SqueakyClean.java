class SqueakyClean {
    static String clean(String identifier) {
        String result = identifier.replace(" ", "_");

        while (result.contains("-")) {
            int index = result.indexOf("-");

            if(index < result.length() - 1) {
                char nextChar = Character.toUpperCase(result.charAt(index + 1));
                result = result.substring(0, index) + nextChar + result.substring(index + 2);
            } else {
                result = result.substring(0, index);
            }
        }

        result = result.replace("4", "a")
              .replace("3", "e")
              .replace("0", "o")
              .replace("1", "l")
              .replace("7", "t");

        result = result.replaceAll("[^a-zA-Z_]", "");

        return result;
    }
}
