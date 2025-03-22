import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class GrepTool {

    String grep(String pattern, List<String> flags, List<String> files) {
        Set<String> matchingFiles = new HashSet<>();
        List<String> results = new ArrayList<>();
        boolean caseInsensitive = flags.contains("-i");
        boolean onlyFilenames = flags.contains("-l");
        boolean invertMatch = flags.contains("-v");
        boolean wholeLineMatch = flags.contains("-x");
        boolean showLineNumbers = flags.contains("-n");

        for (String file : files) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(file));
                for (int i = 0; i < lines.size(); i++) {
                    String originalLine = lines.get(i);
                    String line = caseInsensitive ? originalLine.toLowerCase() : originalLine;
                    String target = caseInsensitive ? pattern.toLowerCase() : pattern;

                    boolean match = wholeLineMatch ? line.equals(target) : line.contains(target);
                    if (invertMatch) {
                        match = !match;
                    }

                    if (match) {
                        if (onlyFilenames) {
                            matchingFiles.add(file);
                            break;
                        }
                        StringBuilder output = new StringBuilder();
                        if (files.size() > 1) {
                            output.append(file).append(":");
                        }
                        if (showLineNumbers) {
                            output.append(i + 1).append(":");
                        }
                        output.append(originalLine);
                        results.add(output.toString());
                    }
                }
            } catch (IOException e) {
                return("grep: " + file + ": No such file or directory");
            }
        }
        if (onlyFilenames) {
            results.addAll(matchingFiles);
        }

        return String.join("\n", results);
    }

}