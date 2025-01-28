package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class InMemoryKV implements KeyValueStorage {

    private final Map<String, String> store;

    public InMemoryKV(Map<String, String> store) {
        this.store = new HashMap<>(store);
    }

    @Override
    public void set(String key, String value) {
        this.store.put(key, value);
    }

    @Override
    public void unset(String key) {
        this.store.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return this.store.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(this.store);
    }
}
// END
