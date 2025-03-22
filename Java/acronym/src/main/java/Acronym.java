class Acronym {
    private final String acronym;

    Acronym(String phrase) {
        this.acronym = generateAcronym(phrase);
    }

    String get() {
        return acronym;
    }

    private String generateAcronym(String phrase) {
        String noPunctuation = phrase.replaceAll("[^a-zA-Z0-9\\s-]", "");
        String[] words = noPunctuation.split("[\\s\\-]+");

        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(word.charAt(0));
            }
        }
        return sb.toString().toUpperCase();
    }

}
