import java.util.HashSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Robot {
    private static final HashSet<String> usedNames = new HashSet<>();
    private static final Random random = new Random();
    private String name;

    String getName() {
        if (this.name == null) {
            this.name = generateUniqueName();
        }
        return this.name;
    }

    void reset() {
        if (name != null) {
            usedNames.remove(this.name);
        }
        this.name = null;
    }

    private String generateName() {
        char letter1 = (char) ('A' + random.nextInt(26));
        char letter2 = (char) ('A' + random.nextInt(26));
        int number = random.nextInt(1000);
        return String.format("%c%c%3d", letter1, letter2,number);
    }

    private String generateUniqueName() {
        String newName;
        do {
            newName = generateName();
        } while (usedNames.contains(newName) || !isValidName(newName));
        usedNames.add(newName);
        return newName;
    }

    private boolean isValidName(String name) {
        Pattern pattern = Pattern.compile("^[A-Z]{2}\\d{3}$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}