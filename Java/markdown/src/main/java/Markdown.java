class Markdown {

    String parse(String markdown) {
        String[] lines = markdown.split("\n");
        StringBuilder result = new StringBuilder(); // Changed from String to StringBuilder for better performance
        boolean activeList = false;

        for (String line : lines) {
            String parsedLine = parseHeader(line);

            // Optimization: Combined conditional checks to avoid redundant calls
            parsedLine = (parsedLine != null) ? parsedLine : parseListItem(line);
            parsedLine = (parsedLine != null) ? parsedLine : parseParagraph(line);

            boolean isListItem = parsedLine.startsWith("<li>");
            if (isListItem && !activeList) {
                activeList = true;
                result.append("<ul>"); // Changed from string concatenation to append() for efficiency
            } else if (!isListItem && activeList) {
                activeList = false;
                result.append("</ul>");
            }
            result.append(parsedLine);
        }

        if (activeList) {
            result.append("</ul>");
        }

        return result.toString();
    }

    private String parseHeader(String markdown) {
        int count = 0;
        while (count < markdown.length() && markdown.charAt(count) == '#') {
            count++; // Changed loop to a while loop for better readability
        }

        if (count == 0) return null;
        if (count > 6) return wrapWithTag("p", markdown);

        return wrapWithTag("h" + count, markdown.substring(count + 1).trim()); // Added trim() to remove leading spaces
    }

    private String parseListItem(String markdown) {
        if (markdown.startsWith("* ")) { // Added space after '*' to prevent unintended matches
            return wrapWithTag("li", parseSomeSymbols(markdown.substring(2).trim())); // Added trim() for consistency
        }
        return null;
    }

    private String parseParagraph(String markdown) {
        return wrapWithTag("p", parseSomeSymbols(markdown));
    }

    private String parseSomeSymbols(String markdown) {
        return markdown.replaceAll("__(.+?)__", "<strong>$1</strong>") // Changed regex to non-greedy quantifier (.+?)
                .replaceAll("_(.+?)_", "<em>$1</em>");
    }

    private String wrapWithTag(String tag, String content) {
        return "<" + tag + ">" + content + "</" + tag + ">"; // Introduced helper method to reduce redundancy
    }
}
