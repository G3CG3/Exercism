import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Tournament {
    private final Map<String, int[]> teams = new HashMap<>();

    String printTable() {
        List<String> sortedTeams = new ArrayList<>(teams.keySet());
        sortedTeams.sort((t1, t2) -> {
            int comparator = Integer.compare(teams.get(t2)[4], teams.get(t1)[4]);
            return comparator != 0 ? comparator : t1.compareTo(t2);
        });

        StringBuilder output = new StringBuilder();
        output.append("Team                           | MP |  W |  D |  L |  P\n");
        for (String team : sortedTeams) {
            int[] stats = teams.get(team);
            output.append(String.format("%-30s | %2d | %2d | %2d | %2d | %2d\n",
                    team, stats[0], stats[1], stats[2], stats[3], stats[4]));
        }
        return output.toString();
    }

    void applyResults(String resultString) {
        // Split by newline to handle multiple results
        String[] results = resultString.split("\n");
        for (String result : results) {
            String[] parts = result.split(";");
            if (parts.length != 3) {
                continue;
            }

            String team1 = parts[0];
            String team2 = parts[1];
            String matchResult = parts[2];

            // Initialize teams if they are not already in the map
            teams.putIfAbsent(team1, new int[5]); // MP, W, D, L, P
            teams.putIfAbsent(team2, new int[5]);

            teams.get(team1)[0]++; // MP
            teams.get(team2)[0]++; // MP

            if ("win".equals(matchResult)) {
                teams.get(team1)[1]++; // W
                teams.get(team1)[4] += 3; // P
                teams.get(team2)[3]++; // L
            } else if ("loss".equals(matchResult)) {
                teams.get(team1)[3]++; // L
                teams.get(team2)[4] += 3; // P
                teams.get(team2)[1]++; // W
            } else { // "draw"
                teams.get(team1)[2]++; // D
                teams.get(team2)[2]++; // D
                teams.get(team1)[4]++; // P
                teams.get(team2)[4]++; // P
            }
        }
    }
}
