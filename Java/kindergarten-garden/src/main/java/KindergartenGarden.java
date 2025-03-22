import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class KindergartenGarden {
    private static final List<String> STUDENTS = List.of("Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet",
            "Ileana", "Joseph", "Kincaid", "Larry");

    private static final Map<Character, Plant> PLANTS = Map.of(
            'V', Plant.VIOLETS,
            'R', Plant.RADISHES,
            'C', Plant.CLOVER,
            'G', Plant.GRASS
    );

    private final String[] rows;
    private final ArrayList<String> students;

    KindergartenGarden(String garden) {
        this.rows = garden.split("\\n");
        this.students = new ArrayList<>(STUDENTS);
        Collections.sort(students);
    }

    List<Plant> getPlantsOfStudent(String student) {
        int index = STUDENTS.indexOf(student) * 2;
        return List.of(
                PLANTS.get(rows[0].charAt(index)),
                PLANTS.get(rows[0].charAt(index + 1)),
                PLANTS.get(rows[1].charAt(index)),
                PLANTS.get(rows[1].charAt(index + 1))
        );
    }

}
