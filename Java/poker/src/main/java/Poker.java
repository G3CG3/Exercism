import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Poker {

    private final List<Hand> hands;

    Poker(List<String> hands) {
        this.hands = hands.stream().map(Hand::new).toList();
    }

    List<String> getBestHands() {
        if (hands.size() == 1) {
            return hands.stream().map(Hand::toString).toList();
        }
        return calculateBestHands();
    }

    private List<String> calculateBestHands() {
        ArrayList<Hand> bestHands = new ArrayList<>();

        for (Hand hand : hands) {
            if (bestHands.isEmpty()) {
                bestHands.add(hand);
            } else {
                Hand currentBest = bestHands.getFirst();
                if (hand.compareTo(currentBest) > 0) {
                    bestHands.clear();
                    bestHands.add(hand);
                } else if (hand.compareTo(currentBest) == 0) {
                    bestHands.add(hand);
                }
            }
        }
        return bestHands.stream().map(Hand::toString).toList();
    }

    private static class Hand implements Comparable<Hand> {
        private final String hand;
        private final List<Card> cards;
        private Rank rank;
        private List<Card> sorted;
        private Map<Suit, List<Card>> mappedBySuit;
        private Map<Integer, List<Card>> mappedByPoints;

        Hand(String hand) {
            this.hand = hand;
            this.cards = parseHand(hand);
            this.mappedBySuit = cards.stream().collect(Collectors.groupingBy(Card::getSuit));
            this.mappedByPoints = cards.stream().collect(Collectors.groupingBy(c -> c.getValue().getPoints()));
        }


        private List<Card> parseHand(String input) {
            ArrayList<Card> hand = new ArrayList<>();
            for (String card: input.split("\\s+")) {
                hand.add(new Card(card));
            }
            return hand;
        }

        public Rank getRank() {
            if (rank == null) {
                rank = calculateRank();
            }
            return rank;
        }

        private Rank calculateRank() {
            if (hasStraight() && hasFlush()) {
                return Rank.STRAIGHT_FLUSH;
            } else if (hasFourOfAKind()) {
                return Rank.FOUR_OF_A_KIND;
            } else if (hasFullHouse()) {
                return Rank.FULL_HOUSE;
            } else if (hasFlush()) {
                return Rank.FLUSH;
            } else if (hasStraight()) {
                return Rank.STRAIGHT;
            } else if(hasThreeOfAKind()) {
                return Rank.THREE_OF_A_KIND;
            } else if (hasTwoPair()) {
                return Rank.TWO_PAIR;
            } else if (hasPair()) {
                return Rank.PAIR;
            } else {
                return Rank.NOTHING;
            }
        }

        private boolean hasFourOfAKind() {
            return getFourOfAKind() != null;
        }

        private Value getFourOfAKind() {
            for (List<Card> cards: mapByValue().values()) {
                if (cards.size() == 4) {
                    return cards.getFirst().getValue();
                }
            }
            return null;
        }
        private boolean hasFullHouse() {
            return hasThreeOfAKind() && getPairs().size() == 1;
        }

        private boolean hasFlush() {
            return mapBySuit().size() == 1;
        }

        private boolean hasTwoPair() {
            return getPairs().size() == 2;
        }

        private boolean hasPair() {
            return getPairs().size() == 1;
        }

        private List<Value> getPairs() {
            ArrayList<Value> pairs = new ArrayList<>();
            for (List<Card> cards: mapByValue().values()) {
                if (cards.size() == 2) {
                    pairs.add(cards.getFirst().getValue());
                }
            }
            return pairs;
        }

        private int getPair() {
            return getPairs().getFirst().getPoints();
        }

        private boolean hasThreeOfAKind() {
            return getThreeOfAKind() != null;
        }

        private Value getThreeOfAKind() {
            for (List<Card> cards: mapByValue().values()) {
                if (cards.size() == 3) {
                    return cards.getFirst().getValue();
                }
            }
            return null;
        }

        private boolean hasStraight() {
            List<Integer> pointValues = this.sorted().stream()
                    .map(c -> c.getValue().getPoints())
                    .collect(Collectors.toList());

            if (pointValues.contains(14) && pointValues.contains(2)) {
                // Special case for Ace-low straight: [2, 3, 4, 5, 14]
                pointValues.remove(Integer.valueOf(14));
                pointValues.addFirst(1); // Replace Ace (14) with 1.
            }

            List<Integer> consecutive = generateStraightFrom(pointValues.getFirst());
            return pointValues.equals(consecutive);
        }


        private List<Integer> generateStraightFrom(int start) {
            ArrayList<Integer> straight = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                straight.add(start + i);
            }
            return straight;
        }

        public List<Card> sorted() {
            if (sorted == null)
                sorted = cards.stream().sorted(Card::compareTo).toList();
            return sorted;
        }

        public Map<Suit, List<Card>> mapBySuit() {
            if (mappedBySuit == null)
                mappedBySuit = cards.stream().collect(Collectors.groupingBy(Card::getSuit));
            return mappedBySuit;
        }

        public Map<Integer, List<Card>> mapByValue() {
            if (mappedByPoints == null)
                mappedByPoints = cards.stream().collect(Collectors.groupingBy(c -> c.getValue().getPoints()));
            return mappedByPoints;
        }

        @Override
        public String toString() {
            return hand;
        }

        @Override
        public int compareTo(Hand that) {
            int rankComparison = this.getRank().compareTo(that.getRank());
            if (rankComparison != 0) return rankComparison;

            return switch (this.getRank()) {
                case FOUR_OF_A_KIND -> compareFourOfAKinds(that);
                case FULL_HOUSE -> compareFullHouses(that);
                case STRAIGHT_FLUSH, STRAIGHT -> compareStraights(that);
                case THREE_OF_A_KIND -> compareThreeOfAKinds(that);
                case TWO_PAIR -> compareTwoPairs(that);
                case PAIR -> comparePairs(that);
                case FLUSH, NOTHING -> compareCards(this.sorted(), that.sorted());
            };
        }

        private int compareFourOfAKinds(Hand that) {
            Value thisFour = this.getFourOfAKind();
            Value thatFour = that.getFourOfAKind();
            assert thatFour != null;
            assert thisFour != null;
            int compareThrees = thisFour.compareTo(thatFour);
            if (compareThrees != 0) return compareThrees;
            return compareCards(
                    filterCardsByValues(this.cards, List.of(thisFour)),
                    filterCardsByValues(that.cards, List.of(thatFour))
            );
        }

        private int compareFullHouses(Hand that) {
            int compareThrees = compareThreeOfAKinds(that);
            if (compareThrees != 0) return compareThrees;
            return comparePairs(that);
        }

        private int compareStraights(Hand that) {
            Card thisLowest = this.sorted().getFirst();
            Card thatLowest = that.sorted().get(0);
            int compareLowest = thisLowest.compareTo(thatLowest);
            if (thisLowest.getValue().getPoints() == 2) {
                Card thisHighest = this.sorted().get(4);
                Card thatHighest = that.sorted().get(4);
                return thatHighest.compareTo(thisHighest);
            }
            return compareLowest;
        }

        private int compareThreeOfAKinds(Hand that) {
            Value thisThree = this.getThreeOfAKind();
            Value thatThree = that.getThreeOfAKind();
            assert thatThree != null;
            assert thisThree != null;
            int compareThrees = thisThree.compareTo(thatThree);
            if (compareThrees != 0) return compareThrees;
            return compareCards(
                    filterCardsByValues(this.cards, List.of(thisThree)),
                    filterCardsByValues(that.cards, List.of(thatThree))
            );
        }

        private int compareTwoPairs(Hand that) {
            List<Integer> thesePairs = this.getPairs().stream().map(Value::getPoints).sorted().toList();
            List<Integer> thosePairs = that.getPairs().stream().map(Value::getPoints).sorted().toList();

            int compareLargestPairs = thesePairs.get(1).compareTo(thosePairs.get(1));
            if (compareLargestPairs != 0) return compareLargestPairs;
            int compareSmallestPairs = thesePairs.get(0).compareTo(thosePairs.get(0));
            if (compareSmallestPairs != 0) return compareSmallestPairs;

            List<Card> theseCardsWithoutThePair = filterCardsByValues(this.sorted(), this.getPairs());
            List<Card> thoseCardsWithoutThePair = filterCardsByValues(that.sorted(), that.getPairs());

            return compareCards(
                    theseCardsWithoutThePair,
                    thoseCardsWithoutThePair
            );
        }

        private List<Card> filterCardsByValues(List<Card> cards, List<Value> filtersValues) {
            return cards.stream()
                    .filter(
                            c -> !filtersValues.stream()
                                    .map(Value::getPoints)
                                    .toList()
                                    .contains(c.getValue().getPoints())
                    ).toList();
        }


        private int comparePairs(Hand that) {
            int compare = Integer.compare(this.getPair(), that.getPair());
            if (compare != 0) return compare;

            List<Card> theseCardsWithoutThePair = filterCardsByValues(this.sorted(), this.getPairs());
            List<Card> thoseCardsWithoutThePair = filterCardsByValues(that.sorted(), that.getPairs());

            return compareCards(
                    theseCardsWithoutThePair,
                    thoseCardsWithoutThePair
            );
        }

        private int compareCards(List<Card> thisCards, List<Card> thatCards) {
            for (int i = thisCards.size() - 1; i >= 0; i--) {
                int comparison = thisCards.get(i).compareTo(thatCards.get(i));
                if (comparison != 0) return comparison;
            }
            return 0;
        }

    }

    private enum Rank {

        NOTHING(0),
        PAIR(1),
        TWO_PAIR(2),
        THREE_OF_A_KIND(3),
        STRAIGHT(4),
        FLUSH(5),
        FULL_HOUSE(6),
        FOUR_OF_A_KIND(7),
        STRAIGHT_FLUSH(8);

        public final int rank;

        Rank(int rank) {
            this.rank = rank;
        }
    }

    private static class Card implements Comparable<Card> {

        private final Suit suit;
        private final Value value;

        Card(String card) {
            suit = parseSuit(card);
            value = new Value(card);
        }

        private Suit parseSuit(String card) {
            card = card.toUpperCase();
            char suit = card.startsWith("10") ? card.charAt(2) : card.charAt(1);
            return switch (suit) {
                case 'S' -> Suit.SPADE;
                case 'D' -> Suit.DIAMOND;
                case 'C' -> Suit.CLUB;
                case 'H' -> Suit.HEART;
                default -> throw new IllegalStateException("Unexpected value: " + card.toUpperCase().charAt(1));
            };
        }

        public Suit getSuit() {
            return suit;
        }

        public Value getValue() {
            return value;
        }

        @Override
        public int compareTo(Card that) {
            return Integer.compare(this.value.points, that.value.points);
        }

        @Override
        public String toString() {
            return "Card{" +
                    "suit=" + suit +
                    ", value=" + value +
                    '}';
        }
    }

    private static class Value implements Comparable<Value> {
        private final String value;
        private final int points;

        Value(String card) {
            value = card.startsWith("10")  ? "10" : ""+card.charAt(0);
            points = calculatePoints();
        }

        private int calculatePoints() {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return switch (value) {
                    case "J" -> 11;
                    case "Q" -> 12;
                    case "K" -> 13;
                    case "A" -> 14;
                    default -> throw new IllegalStateException("Unexpected value: " + value);
                };
            }
        }

        public int getPoints() {
            return points;
        }

        @Override
        public String toString() {
            return value;
        }

        @Override
        public int compareTo(Value that) {
            return Integer.compare(this.points, that.points);
        }
    }

    private enum Suit {
        SPADE,
        DIAMOND,
        CLUB,
        HEART,
    }
}