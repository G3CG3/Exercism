import java.util.*;

class DnDCharacter {
    private int dexterity = ability(rollDice());
    private int strength = ability(rollDice());
    private int constitution = ability(rollDice());
    private int intelligence = ability(rollDice());
    private int wisdom = ability(rollDice());
    private int charisma = ability(rollDice());

    int ability(List<Integer> scores) {
        return scores.stream()
                .sorted((a, b) -> b - a)
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum();
    }

    List<Integer> rollDice() {
        Random random = new Random();
        ArrayList<Integer> diceRolls = new ArrayList<>(4);

        for (int i = 0; i < 4; i++) {
            diceRolls.add(random.nextInt(6) + 1);
        }

        return diceRolls;
    }

    int modifier(int input) {
        return Math.floorDiv((input - 10), 2);
    }

    int getStrength() {
        return strength;
    }

    int getDexterity() {
        return dexterity;
    }

    int getConstitution() {
        return constitution;
    }

    int getIntelligence() {
        return intelligence;
    }

    int getWisdom() {
        return wisdom;
    }

    int getCharisma() {
        return charisma;
    }

    int getHitpoints() {
        return 10 + modifier(getConstitution());
    }
}
