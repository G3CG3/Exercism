import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class GottaSnatchEmAll {

    static Set<String> newCollection(List<String> cards) {
        return new HashSet<>(cards);
    }

    static boolean addCard(String card, Set<String> collection) {
        return collection.add(card);
    }

    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        boolean cardsTheyHaveAndIWant = theirCollection.stream().anyMatch(card -> !myCollection.contains(card));

        boolean cardsIHaveAndTheyWant = myCollection.stream().anyMatch((card -> !theirCollection.contains(card)));

        return cardsIHaveAndTheyWant && cardsTheyHaveAndIWant;
    }

    static Set<String> commonCards(List<Set<String>> collections) {
        if (collections.isEmpty()) {
            return new HashSet<>(); // No collections, no common cards
        }

        Set<String> common = new HashSet<>(collections.getFirst());

        for (Set<String> collection : collections) {
            common.retainAll(new HashSet<>(collection));
        }

        return common;
    }

    static Set<String> allCards(List<Set<String>> collections) {
        return collections.stream().flatMap(Set::stream).collect(Collectors.toSet());
    }
}
