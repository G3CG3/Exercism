import java.util.HashSet;
import java.util.List;

class RelationshipComputer<T> {
    Relationship computeRelationship(List<T> firstList, List<T> secondList) {
        if (areEqual(firstList, secondList)) {
            return Relationship.EQUAL;
        } else if (isSuperlist(firstList, secondList) || secondList.isEmpty()) {
            return Relationship.SUPERLIST;
        } else if (isSublist(firstList, secondList)) {
            return Relationship.SUBLIST;
        } else {
            return Relationship.UNEQUAL;
        }
    }

    private static <T> boolean areEqual(List<T> firstList, List<T> secondList) {
        return firstList.equals(secondList);
    }

    private static <T> boolean isSuperlist(List<T> firstList, List<T> secondList) {
        return new HashSet<>(firstList).containsAll(secondList) && isSublist(secondList, firstList);
    }

    private static <T> boolean isSublist(List<T> firstList, List<T> secondList) {
        if (firstList.size() > secondList.size()) return false;
        for (int i = 0; i <= secondList.size() - firstList.size(); i++) {
            if (secondList.subList(i, i + firstList.size()).equals(firstList)) {
                return true;
            }
        }
        return false;
    }
}
