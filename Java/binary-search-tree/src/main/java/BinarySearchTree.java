import java.util.ArrayList;
import java.util.List;

class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root = null;

    void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
        } else {
            insert(value, root);
        }
    }

    void insert(T value, Node<T> parent) {
        if (value.compareTo(parent.getData()) <= 0) {
            insertLeft(value, parent);
        } else {
            insertRight(value, parent);
        }
    }

    void insertLeft(T value, Node<T> parent) {
        if (parent.getLeft() == null) {
            parent.setLeft(new Node<>(value));
        } else {
            insert(value, parent.getLeft());
        }
    }

    void insertRight(T value, Node<T> parent) {
        if (parent.getRight() == null) {
            parent.setRight(new Node<>(value));
        } else {
            insert(value, parent.getRight());
        }
    }

    List<T> getAsSortedList() {
        List<T> list = new ArrayList<>();
        if (root != null) {
            sort(root, list);
        }
        return list;
    }

    private void sort(Node<T> node, List<T> list) {
        if (node.hasLeft()) sort(node.getLeft(), list);
        list.add(node.getData());
        if (node.hasRight()) sort(node.getRight(), list);
    }

    List<T> getAsLevelOrderList() {
        List<T> accumulator = new ArrayList<>();
        if (root != null) {
            accumulator.add(root.getData());
            collectByLevel(root, accumulator);
        }
        return accumulator;
    }

    private void collectByLevel(Node<T> node, List<T> list) {
        if (node.hasLeft()) list.add(node.getLeft().getData());
        if (node.hasRight()) list.add(node.getRight().getData());
        if (node.hasLeft()) collectByLevel(node.getLeft(), list);
        if (node.hasRight()) collectByLevel(node.getRight(), list);
    }

    Node<T> getRoot() {
        return root;
    }

    static class Node<T> {
        private final T data;
        private Node<T> left, right;

        Node(T data) {
            this.data = data;
        }

        void setLeft(Node<T> node) {
            left = node;
        }

        Node<T> getLeft() {
            return left;
        }

        boolean hasLeft() {
            return left != null;
        }

        void setRight(Node<T> node) {
            right = node;
        }

        Node<T> getRight() {
            return right;
        }

        boolean hasRight() {
            return right != null;
        }

        T getData() {
            return data;
        }

    }
}