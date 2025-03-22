import java.util.NoSuchElementException;

class SimpleLinkedList<T> {
    private Node<T> head;  // The first node in the linked list
    private int size;      // To keep track of the size of the list

    // Node class representing an element in the linked list
    private static class Node<T> {
        private final T value;
        private Node<T> next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    SimpleLinkedList() {
        head = null;
        size = 0;
    }

    SimpleLinkedList(T[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Values can't be null");
        }
        head = null;
        size = 0;
        for (T value : values) {
            this.push(value);
        }
    }

    void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;
        size ++;
    }

    T pop() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T value = head.value;
        head = head.next;
        size --;
        return value;
    }

    void reverse() {
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    @SuppressWarnings("unchecked")
    T[] asArray(Class<T> clazz) {
        T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.value;
            current = current.next;
        }
        return array;
    }

    int size() {
        return size;
    }
}
