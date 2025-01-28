package exercise;

import java.util.HashMap;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage keyValueStorage) {
        KeyValueStorage otherValueStorage = new InMemoryKV(new HashMap<>());
        keyValueStorage.toMap().forEach((k, v) -> otherValueStorage.set(v, k));

        System.out.println("otherValueStorage: " + otherValueStorage.toMap());
        otherValueStorage.toMap().values().forEach(keyValueStorage::unset);
        System.out.println("keyValueStorage: " + keyValueStorage.toMap());

        otherValueStorage.toMap().forEach((k, v) -> {
            System.out.println("new key: " + k + ", new value: " + v);
            keyValueStorage.set(k, v);
        });

        System.out.println("keyValueStorage result: " + keyValueStorage.toMap());
    }
}
// END
