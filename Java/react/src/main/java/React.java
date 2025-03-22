import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class React
{

    public static class Cell<T> {
        protected List<Cell<T>> computeCells = new ArrayList<>();
        protected T value;

        public T getValue() {
            return value;
        }

        protected void update() {

        }
    }

    public static class InputCell<T> extends Cell<T> {
        public void setValue(T newValue) {
            this.value = newValue;
            computeCells.forEach(Cell::update);
        }
    }

    public static class ComputeCell<T> extends Cell<T> {
        private Function<List<T>, T> function;
        private List<Cell<T>> cells;
        private final List<Consumer<T>> callbacks = new ArrayList<>();
        private T oldValue;

        public T getValue() {
            // Compute the new value based on the dependencies (cells)
            return function.apply(cells.stream().map(Cell::getValue).toList());
        }

        public void addCallback(Consumer<T> callback) {
            callbacks.add(callback);
        }

        public void removeCallback(Consumer<T> callback) {
            callbacks.remove(callback);
        }

        @Override
        protected void update() {
            // Get the new value by evaluating the function
            T newValue = getValue();

            // If the value has changed, invoke the callbacks
            if (!newValue.equals(oldValue)) {
                // Notify the registered callbacks with the new value
                callbacks.forEach(callback -> callback.accept(newValue));
                oldValue = newValue;  // Update the old value to the new value
            }

            // Propagate updates to listening cells (i.e., dependents)
            computeCells.forEach(Cell::update);
        }
    }

    public static <T> InputCell<T> inputCell(T initialValue) {
        final InputCell<T> cell = new InputCell<>();
        cell.setValue(initialValue);
        return cell;
    }

    public static <T> ComputeCell<T> computeCell(Function<List<T>, T> function, List<Cell<T>> cells) {
        final ComputeCell<T> cell = new ComputeCell<>();

        cells.forEach( c-> c.computeCells.add(cell));

        cell.function = function;
        cell.cells = cells;

        cell.oldValue = cell.getValue();

        return cell;
    }
}