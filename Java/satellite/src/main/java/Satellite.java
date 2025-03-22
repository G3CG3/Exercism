import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Satellite {
    public Tree treeFromTraversals(List<Character> preorderInput, List<Character> inorderInput) {
        if (hasDuplicates(preorderInput) || hasDuplicates(inorderInput)) {
            throw new IllegalArgumentException("traversals must contain unique items");
        }

        if (preorderInput.size() != inorderInput.size()) {
            throw new IllegalArgumentException("traversals must have the same length");
        }

        if (!haveSameElements(preorderInput, inorderInput)) {
            throw new IllegalArgumentException("traversals must have the same elements");
        }

        Node root = buildTree(preorderInput, inorderInput);
        return new Tree(root);
    }

    private static boolean hasDuplicates(List<Character> list) {
        HashSet<Character> set = new HashSet<>(list);
        return set.size() != list.size();  // If sizes differ, there are duplicates
    }

    private static boolean haveSameElements(List<Character> preorder, List<Character> inorder) {
        // Check if both lists have the same size and contain the same elements
        HashSet<Character> setPreorder = new HashSet<>(preorder);
        HashSet<Character> setInorder = new HashSet<>(inorder);
        return setPreorder.equals(setInorder) && preorder.size() == inorder.size();
    }

    private static Node buildTree(List<Character> preorder, List<Character> inorder) {
        if (preorder.isEmpty() || inorder.isEmpty()) {
            return null;
        }

        // The first element of preorder is the root
        char rootValue = preorder.get(0);
        Node root = new Node(rootValue);

        // Find the root in the inorder list
        int rootIndexInOrder = inorder.indexOf(rootValue);

        // Separate the inorder list into left and right subtrees
        List<Character> inorderLeft = inorder.subList(0, rootIndexInOrder);
        List<Character> inorderRight = inorder.subList(rootIndexInOrder + 1, inorder.size());

        // Remove the root from the preorder list
        List<Character> preorderLeft = new ArrayList<>(preorder.subList(1, 1 + inorderLeft.size()));
        List<Character> preorderRight = new ArrayList<>(preorder.subList(1 + inorderLeft.size(), preorder.size()));

        // Recursively build the left and right subtrees
        root.left = buildTree(preorderLeft, inorderLeft);
        root.right = buildTree(preorderRight, inorderRight);

        return root;
    }
}
