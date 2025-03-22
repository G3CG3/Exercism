import java.util.ArrayList;
import java.util.List;

class DiamondPrinter {

    List<String> printToList(char a) {
        List<String> diamond = new ArrayList<>();
        int n = a - 'A'; //4
        int width = 2 * n + 1; //9

        for (int i = -n; i <= n; i++) {
            int av = Math.abs(i);
            char c = (char) ('A' + (n - av));
            String row = " ".repeat(av) + c + (av < n ? " ".repeat(2 * (n - av) - 1) + c : "") + " ".repeat(av);
            diamond.add(row);
        }

        return diamond;
    }
}
