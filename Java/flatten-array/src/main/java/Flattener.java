import java.util.ArrayList;
import java.util.List;

class Flattener {

    List<Object> flatten(List<?> list) {
        List<Object> result = new ArrayList<>();
        flattenHelper(list, result);
        return result;
    }

    private static void flattenHelper(List<?> nestedList, List<Object> result) {
        for (Object element : nestedList) {
            if (element instanceof List) {
                flattenHelper((List<?>) element, result);
            } else if (element != null) {
                result.add(element);
            }
        }
    }
}