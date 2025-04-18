import java.util.stream.Collectors;

class MicroBlog {
    public String truncate(String input) {
        return input.codePoints()
                    .limit(5)
                    .mapToObj(Character::toChars)
                    .map(String::new)
                    .collect(Collectors.joining());
    }
}
