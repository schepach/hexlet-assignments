package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {

    public static List<String> validate(Address address) {
        List<String> invalidFieldNames = new ArrayList<>();
        Class<?> clazz = address.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.isAnnotationPresent(NotNull.class) && field.get(address) == null)
                    invalidFieldNames.add(field.getName());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return invalidFieldNames;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> invalidFieldNamesMap = new HashMap<>();
        Class<?> clazz = address.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.isAnnotationPresent(NotNull.class) && field.get(address) == null) {
                    invalidFieldNamesMap.put(field.getName(), new ArrayList<>(List.of("can not be null")));
                } else if (field.isAnnotationPresent(MinLength.class)
                        && field.get(address).toString().length() < field.getAnnotation(MinLength.class).minLength()) {
                    int minValue = field.getAnnotation(MinLength.class).minLength();
                    invalidFieldNamesMap.put(field.getName(), new ArrayList<>(List.of("length less than " + minValue)));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return invalidFieldNamesMap;
    }
}
// END
