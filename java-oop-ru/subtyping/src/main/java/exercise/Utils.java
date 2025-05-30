// Методы класса в этом файл нужны для самостоятельной работы

package exercise;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static String serialize(Map<String, String> map) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return json;
    }

    public static Map<String, String> deserialize(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> data = new HashMap<>();
        try {
            data = mapper.readValue(json, Map.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    public static String readFile(String path) {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        String content = "";
        try {
            content = Files.readString(fullPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return content;
    }

    public static void writeFile(String path, String content) {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        try {
            Files.writeString(fullPath, content, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
