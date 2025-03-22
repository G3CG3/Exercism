import java.util.*;

class School {
    private final Map<Integer, Set<String>> roster;

    public School() {
        roster = new TreeMap<>();
    }

    boolean add(String student, int grade) {
        for (Set<String> students : roster.values()) {
            if (students.contains(student)) {
                return false;
            }
        }
        roster.putIfAbsent(grade, new TreeSet<>());
        roster.get(grade).add(student);
        return true;
    }

    List<String> roster() {
        List<String> sortedStudents = new ArrayList<>();
        for (Set<String> students : roster.values()) {
            sortedStudents.addAll(students);
        }
        return sortedStudents;
    }

    List<String> grade(int grade) {
        return new ArrayList<>(roster.getOrDefault(grade, new TreeSet<>()));
    }

}
