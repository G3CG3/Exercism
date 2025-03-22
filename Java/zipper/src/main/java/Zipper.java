import java.util.Objects;

class Zipper {
    Zipper left;
    Zipper right;
    Zipper up;
    int value;

    Zipper(int val) {
        this.value = val;
    }

    BinaryTree toTree() {
        Zipper top = this;
        while (top.up != null) {
            top = top.up;
        }
        return new BinaryTree(top);
    }

    int getValue() {
        return value;
    }

    Zipper setLeft(Zipper leftChild) {
        this.left = leftChild;
        if (leftChild != null) {
            leftChild.up = this;
        }
        return this;
    }

    Zipper setRight(Zipper rightChild) {
        this.right = rightChild;
        if (rightChild != null) {
            rightChild.up = this;
        }
        return this;
    }

    void setValue(int val) {
        value = val;
    }
}

class BinaryTree {
    Zipper root;

    BinaryTree(int value) {
        root = new Zipper(value);
    }

    BinaryTree(Zipper root) {
        this.root = root;
    }

    Zipper getRoot() {
        return root;
    }

    String printTree() {
        StringBuilder sb = new StringBuilder();

        sb.append("value: ");
        sb.append(root.value);
        sb.append(", left: ");
        sb.append(printBranch(root.left));
        sb.append(", right: ");
        sb.append(printBranch(root.right));

        return sb.toString();
    }

    private String printBranch(Zipper node)
    {
        if (node == null) return "null";

        StringBuilder sb = new StringBuilder();
        sb.append("{ value: ");
        sb.append(node.value);
        sb.append(", left: ");
        sb.append(printBranch(node.left));
        sb.append(", right: ");
        sb.append(printBranch(node.right));
        sb.append(" }");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BinaryTree that = (BinaryTree) o;
        return Objects.equals(root, that.root);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(root);
    }
}