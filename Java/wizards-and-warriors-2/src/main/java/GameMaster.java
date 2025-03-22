public class GameMaster {

    public String describe(Character character) {
        int level = character.getLevel();
        String playerClass = character.getCharacterClass();
        int hitPoints = character.getHitPoints();

        return String.format("You're a level %d %s with %d hit points.", level, playerClass, hitPoints)  ;
    }

    public String describe(Destination destination) {
        String name = destination.getName();
        int inhabitants = destination.getInhabitants();

        return String.format("You've arrived at %s, which has %d inhabitants.", name, inhabitants)  ;
    }

    public String describe(TravelMethod method) {
        if (method == TravelMethod.WALKING) {
            return "You're traveling to your destination by walking.";
        } else {
            return "You're traveling to your destination on horseback.";
        }
    }

    public String describe(Character character, Destination destination, TravelMethod method) {
        return describe(character) + " " + describe(method) + " " + describe(destination);
    }

    public String describe(Character character, Destination destination) {
        return describe(character) + " " + describe(TravelMethod.WALKING) + " " + describe(destination);
    }
}
