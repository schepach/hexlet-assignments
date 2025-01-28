package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {

    private final Map<String, String> store;
    private String path;

    public FileKV(String path, Map<String, String> map) {
        this.store = new HashMap<>(map);
        this.path = path;
    }

    @Override
    public void set(String key, String value) {
        this.store.put(key, value);
        this.saveDataToFile();
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

    public void saveDataToFile() {
        String serialize = Utils.serialize(this.store);
        System.out.println("Serialized map: " + serialize);
        Utils.writeFile(this.path, serialize);
    }
}
// END
