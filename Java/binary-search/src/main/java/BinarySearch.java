import java.util.List;

class BinarySearch {
    private final List<Integer> items;

    BinarySearch(List<Integer> items) {
        this.items = items;
    }

    int indexOf(int item) throws ValueNotFoundException {
        int left = 0;
        int right = items.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (items.get(mid) == item) {
                return mid;
            } else if (items.get(mid) < item) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        throw new ValueNotFoundException("Value not in array");
    }
}
