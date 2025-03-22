class WordProblemSolver {
    int solve(final String wordProblem) {
            if (!wordProblem.startsWith("What is ") || !wordProblem.endsWith("?")) {
                throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
            }

        String problem = wordProblem.substring(8, wordProblem.length() - 1).trim();
            String[] tokens = problem.split(" ");

            try {
                int result = Integer.parseInt(tokens[0]);

                for (int i = 1; i < tokens.length; i++) {
                    String operation = tokens[i];

                    if (operation.equals("multiplied") || operation.equals("divided")) {
                        if (i + 2 >= tokens.length || !tokens[i + 1].equals("by")) {
                            throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
                        }
                        i++; // Skip "by"
                    }

                    if (i + 1 >= tokens.length) {
                        throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
                    }

                    int number = Integer.parseInt(tokens[i + 1]);

                    switch (operation) {
                        case "plus":
                            result += number;
                            break;
                        case "minus":
                            result -= number;
                            break;
                        case "multiplied":
                            result *= number;
                            break;
                        case "divided":
                            if (number == 0) {
                                throw new ArithmeticException("Division by zero");
                            }
                            result /= number;
                            break;
                        default:
                            throw new IllegalArgumentException("Unsupported operation: " + operation);
                    }
                    i++; // Move to next operator
                }
                return result;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
            }
    }
}
