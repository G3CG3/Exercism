class CircularBuffer<T> {
    private final T[] buffer;
    private int head;
    private int tail;
    private int size;
    private final int capacity;

    @SuppressWarnings("unchecked")
    CircularBuffer(final int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0.");
        }
        this.capacity = size;
        this.buffer = (T[]) new Object[size]; // Create an array of the generic type T
        this.head = 0; // Pointer to the next read position
        this.tail = 0; // Pointer to the next write position
        this.size = 0; // Buffer starts empty
    }

    T read() throws BufferIOException {
        if (size == 0) {
            throw new BufferIOException("Tried to read from empty buffer");
        }

        T data = buffer[head];
        head = (head + 1) % capacity;
        size--;
        return data;
    }

    void write(T data) throws BufferIOException {
        if (size == capacity) {
            throw new BufferIOException("Tried to write to full buffer");
        }

        buffer[tail] = data;
        tail = (tail + 1) % capacity;
        size++;
    }

    void overwrite(T data) {
        if (size == capacity) {
            head = (head + 1) % capacity; // Remove the oldest item
        } else {
            size++;
        }
        buffer[tail] = data;
        tail = (tail + 1) % capacity;
    }

    void clear() {
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }
}