import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

class CustomSet<T> {
    private final Set<T> internalSet;

    CustomSet() {
        internalSet = new HashSet<>();
    }

    CustomSet(Collection<T> data) {
        internalSet = new HashSet<>(data);
    }

    boolean isEmpty() {
        return internalSet.isEmpty();
    }

    boolean contains(T element) {
        return internalSet.contains(element);
    }

    boolean isDisjoint(CustomSet<T> other) {
        for (T element : internalSet) {
            if (other.contains(element)) {
                return false;
            }
        }
        return true;
    }

    boolean add(T element) {
        return internalSet.add(element);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CustomSet<?> other = (CustomSet<?>) obj;
        return internalSet.equals(other.internalSet);
    }

    CustomSet<T> getIntersection(CustomSet<T> other) {
        CustomSet<T> intersectionSet = new CustomSet<>();
        for (T element : internalSet) {
            if (other.contains(element)) {
                intersectionSet.add(element);
            }
        }
        return intersectionSet;
    }

    CustomSet<T> getUnion(CustomSet<T> other) {
        CustomSet<T> unionSet = new CustomSet<>(internalSet);
        for (T element : other.internalSet) {
            unionSet.add(element);
        }
        return unionSet;
    }

    CustomSet<T> getDifference(CustomSet<T> other) {
        CustomSet<T> differenceSet = new CustomSet<>();
        for (T element : internalSet) {
            if (!other.contains(element)) {
                differenceSet.add(element);
            }
        }
        return differenceSet;
    }

    boolean isSubset(CustomSet<T> other) {
        if (this.isEmpty() && !other.isEmpty()) {
            return false;
        }
        return other.internalSet.containsAll(internalSet) || internalSet.containsAll(other.internalSet);
    }
}
