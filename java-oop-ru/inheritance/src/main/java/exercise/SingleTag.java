package exercise;

import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class SingleTag extends Tag {

    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }

    @Override
    public String toString() {

        // Only open tag
        if (getAttributes().isEmpty()) {
            return String.format("<%s>", getName());
        }

        String attributes = getAttributes().entrySet()
                .stream()
                .map(a -> a.getKey() + "=\"" + a.getValue() + "\"")
                .collect(Collectors.joining(" "));

        return String.format("<%s %s>", getName(), attributes);
    }
}
// END
