package exercise;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

// BEGIN
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
// END



class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // BEGIN
    @Test
    void wriToFileTest() {
        KeyValueStorage storage = new FileKV(filepath.toString(),
                Map.of("my-key", "my-value",
                        "my-key2", "my-value2"));

        storage.set("file-key","file-value");

        String fileValues = Utils.readFile(filepath.toString());
        Map<String, String> deserializeMap = Utils.deserialize(fileValues);
        System.out.println("DeserializeMap map... ");
        deserializeMap.forEach((k, v) -> {
            System.out.println("Key: " + k + ", Value: " + v);
        });

        // Check map values by key after read the file and deserialize
        assertThat(storage.get("my-key", "default"))
                .isEqualTo(deserializeMap.get("my-key"));
        assertThat(storage.get("my-key2", "default"))
                .isEqualTo(deserializeMap.get("my-key2"));
        assertThat(storage.get("file-key", "default"))
                .isEqualTo(deserializeMap.get("file-key"));
    }

    @Test
    void classTypeTest() {
        KeyValueStorage inMemoryKV = new InMemoryKV(
                Map.of("kv-key", "kv-value",
                        "kv-key2", "kv-value2"));
        assertThat(inMemoryKV.getClass().getName()).isNotEqualTo(FileKV.class.getName());
    }

    @Test
    void mapValueByKeyTest() {
        KeyValueStorage storage = new FileKV(filepath.toString(),
                Map.of("my-key", "my-value",
                        "my-key2", "my-value2"));

        assertThat(storage.get("key", "default"))
                .isEqualTo("default");

        assertThat(storage.get("my-key2", "default"))
                .isEqualTo("my-value2");
    }
    // END
}
