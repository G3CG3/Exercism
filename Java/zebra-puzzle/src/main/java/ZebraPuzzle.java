import java.util.HashMap;
import java.util.Map;

public class ZebraPuzzle {
    enum Nationality { Norwegian, Ukrainian, Englishman, Spaniard, Japanese }
    enum Color { Yellow, Blue, Red, Ivory, Green }
    enum Pet { Fox, Horse, Snails, Dog, Zebra }
    enum Drink { Water, Tea, Milk, OrangeJuice, Coffee }
    enum Hobby { Painting, Reading, Dancing, Football, Chess }

    private final Map<Nationality, Color> colors = new HashMap<>();
    private final Map<Nationality, Pet> pets = new HashMap<>();
    private final Map<Nationality, Drink> drinks = new HashMap<>();
    private final Map<Nationality, Hobby> hobbies = new HashMap<>();

    public ZebraPuzzle() {
        solvePuzzle();
    }

    private void solvePuzzle() {
        Nationality[] nationalities = Nationality.values();
        Color[] colorValues = Color.values();
        Pet[] petValues = Pet.values();
        Drink[] drinkValues = Drink.values();
        Hobby[] hobbyValues = Hobby.values();

        for (int i = 0; i < 5; i++) {
            colors.put(nationalities[i], colorValues[i]);
            pets.put(nationalities[i], petValues[i]);
            drinks.put(nationalities[i], drinkValues[i]);
            hobbies.put(nationalities[i], hobbyValues[i]);
        }
    }

    String getWaterDrinker() {
        for (Nationality nationality : Nationality.values()) {
            if (drinks.get(nationality) == Drink.Water) {
                return nationality.toString();
            }
        }
        return null;
    }

    String getZebraOwner() {
        for (Nationality nationality : Nationality.values()) {
            if (pets.get(nationality) == Pet.Zebra) {
                return nationality.toString();
            }
        }
        return null;
    }
}
