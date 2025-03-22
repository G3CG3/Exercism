class DoublyLinkedList<T> {
    private Element<T> head;
    private Element<T> tail;
    private int size;

    void push(T value) {
        Element<T> newNode = new Element<>(value, tail, null);
        if (tail != null) {
            tail.next = newNode;
        } else {
            head = newNode;
        }
        tail = newNode;
        size++;
    }

    T pop() {
        if (tail == null) {
            throw new UnsupportedOperationException("List is empty");
        }
        T value = tail.value;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        }
        else {
            head = null;
        }
        return value;
    }

    void unshift(T value) {
        Element<T> newNode = new Element<>(value, null, head);
        if (head == null) {
            head = tail = newNode;
        } else {
            head.prev = newNode;
            head = newNode;
        }
    }

    T shift() {
        if (head == null) {
            throw new UnsupportedOperationException("List is empty");
        }
        T value = head.value;
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        else {
            tail = null;
        }
        return value;
    }

    private static final class Element<T> {
        private final T value;
        private Element<T> prev;
        private Element<T> next;

        Element(T value, Element<T> prev, Element<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
