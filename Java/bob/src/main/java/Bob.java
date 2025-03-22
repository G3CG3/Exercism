class Bob {

    String hey(String input) {

        if (input == null || input.trim().isEmpty()) {
            return "Fine. Be that way!";
        }

        boolean isYelling = input.equals(input.toUpperCase()) && input.chars().anyMatch(Character::isLetter);
        boolean isQuestion = input.trim().endsWith("?");

        if (isQuestion && isYelling) {
            return "Calm down, I know what I'm doing!";
        }
        if (isYelling) {
            return "Whoa, chill out!" ;
        }
        if (isQuestion) {
            return "Sure.";
        }
        return "Whatever.";
    }

}