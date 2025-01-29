package exercise;

import java.util.LinkedHashMap;
import java.util.Map;

// BEGIN
public class Tag {

    private final String name;
    private final Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = new LinkedHashMap<>(attributes);
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }
}
// END
