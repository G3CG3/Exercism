import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Dominoes {

    List<Domino> formChain(List<Domino> inputDominoes) throws ChainNotFoundException {
        if (inputDominoes.isEmpty()) {
            return Collections.emptyList();
        }

        List<Domino> result = new ArrayList<>();
        for (Domino start : inputDominoes) {
            List<Domino> temp = new ArrayList<>(inputDominoes);
            temp.remove(start);
            result.add(start);
            if (backtrack(temp, result, start.getRight())) {
                return result;
            }
            result.clear();
        }
        throw new ChainNotFoundException("No domino chain found.");
    }

    private boolean backtrack(List<Domino> remaining, List<Domino> chain, int expected) {
        if (remaining.isEmpty()) {
            return chain.getFirst().getLeft() == chain.getLast().getRight();
        }

        for (int i = 0; i < remaining.size(); i++) {
            Domino d = remaining.get(i);
            if (d.getLeft() == expected || d.getRight() == expected) {
                List<Domino> nextRemaining = new ArrayList<>(remaining);
                nextRemaining.remove(i);

                if (d.getLeft() != expected) {
                    d = new Domino(d.getRight(), d.getLeft());
                }

                chain.add(d);
                if (backtrack(nextRemaining, chain, d.getRight())) {
                    return true;
                }
                chain.removeLast();
            }
        }
        return false;
    }
}