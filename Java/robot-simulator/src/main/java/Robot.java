class Robot {
    GridPosition position;
    Orientation orientation;

    Robot(GridPosition initialPosition, Orientation initialOrientation) {
        this.position = initialPosition;
        this.orientation = initialOrientation;
    }

    GridPosition getGridPosition() {
        return position;
    }

    Orientation getOrientation() {
        return orientation;
    }

    void advance() {
        switch (getOrientation()) {
            case NORTH -> position = new GridPosition(position.x, position.y + 1);
            case WEST -> position = new GridPosition(position.x - 1, position.y);
            case SOUTH -> position = new GridPosition(position.x, position.y - 1);
            case EAST -> position = new GridPosition(position.x + 1, position.y);
        }
    }

    void turnLeft() {
        switch (orientation) {
            case NORTH -> orientation = Orientation.WEST;
            case WEST -> orientation = Orientation.SOUTH;
            case SOUTH -> orientation = Orientation.EAST;
            case EAST -> orientation = Orientation.NORTH;
        }
    }

    void turnRight() {
        switch (orientation) {
            case NORTH -> orientation = Orientation.EAST;
            case WEST -> orientation = Orientation.NORTH;
            case SOUTH -> orientation = Orientation.WEST;
            case EAST -> orientation = Orientation.SOUTH;
        }
    }

    void simulate(String instructions) {
        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case 'R' -> turnRight();
                case 'L' -> turnLeft();
                case 'A' -> advance();
                default -> throw new IllegalArgumentException("Invalid instruction: " + instruction);
            }
        }
    }

}