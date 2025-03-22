import java.util.*;

class Tree {
    private final String label;
    private final List<Tree> children;

    public Tree(String label) {
        this(label, new ArrayList<>());
    }

    public Tree(String label, List<Tree> children) {
        this.label = label;
        this.children = children;
    }

    public static Tree of(String label) {
        return new Tree(label);
    }

    public static Tree of(String label, List<Tree> children) {
        return new Tree(label, children);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return label.equals(tree.label)
                && children.size() == tree.children.size()
                && new HashSet<>(children).containsAll(tree.children)
                && new HashSet<>(tree.children).containsAll(children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, children);
    }

    @Override
    public String toString() {
        return "Tree{" + label +
                ", " + children +
                "}";
    }

    public Tree fromPov(String fromNode) {
        Map<String, String> parentMap = new HashMap<>();
        Map<String, Tree> nodeMap = new HashMap<>();

        if (buildMaps(null, this, parentMap, nodeMap)) {
            throw new UnsupportedOperationException("Tree could not be reoriented");
        }

        if (!nodeMap.containsKey(fromNode)) {
            throw new UnsupportedOperationException("Tree could not be reoriented");
        }

        return rebuildTree(fromNode, parentMap, nodeMap);
    }


    public List<String> pathTo(String fromNode, String toNode) {
        if (!nodeExists(this, fromNode) || !nodeExists(this, toNode)) {
            throw new UnsupportedOperationException("No path found");
        }

        Tree reorientedTree = fromPov(fromNode);
        List<String> path = new ArrayList<>();
        if (!findPath(reorientedTree, toNode, path)) {
            throw new UnsupportedOperationException("No path found");
        }
        return path;
    }

    private boolean nodeExists(Tree current, String target) {
        if (current.label.equals(target)) {
            return true;
        }
        for (Tree child : current.children) {
            if (nodeExists(child, target)) {
                return true;
            }
        }
        return false;
    }

    private boolean buildMaps(String parentLabel, Tree current, Map<String, String> parentMap, Map<String, Tree> nodeMap) {
        nodeMap.put(current.label, current);
        if (parentLabel != null) {
            parentMap.put(current.label, parentLabel);
        }

        for (Tree child : current.children) {
            if (buildMaps(current.label, child, parentMap, nodeMap)) {
                return true;
            }
        }
        return false;
    }

    private Tree rebuildTree(String newRoot, Map<String, String> parentMap, Map<String, Tree> nodeMap) {
        Tree root = new Tree(newRoot, new ArrayList<>());
        Queue<Tree> queue = new LinkedList<>();
        queue.add(root);
        Set<String> visited = new HashSet<>();
        visited.add(newRoot);

        while (!queue.isEmpty()) {
            Tree current = queue.poll();
            String currentLabel = current.label;

            // Add children from the original tree
            for (Tree child : nodeMap.get(currentLabel).children) {
                if (!visited.contains(child.label)) {
                    Tree newChild = new Tree(child.label, new ArrayList<>());
                    current.children.add(newChild);
                    queue.add(newChild);
                    visited.add(child.label);
                }
            }

            // Add former parent as a child if applicable
            if (parentMap.containsKey(currentLabel)) {
                String parentLabel = parentMap.get(currentLabel);
                if (!visited.contains(parentLabel)) {
                    Tree newChild = new Tree(parentLabel, new ArrayList<>());
                    current.children.add(newChild);
                    queue.add(newChild);
                    visited.add(parentLabel);
                }
            }
        }
        return root;
    }

    private boolean findPath(Tree current, String target, List<String> path) {
        path.add(current.label);
        if (current.label.equals(target)) {
            return true;
        }
        for (Tree child : current.children) {
            if (findPath(child, target, path)) {
                return true;
            }
        }
        path.removeLast();
        return false;
    }
}
