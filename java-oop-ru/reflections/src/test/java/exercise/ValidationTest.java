package exercise;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class ValidationTest {

    @Test
    void testValidate() {
        Address address1 = new Address("Russia", "Ufa", "Lenina", "54", null);
        List<String> result1 = Validator.validate(address1);
        List<String> expected1 = List.of();
        assertThat(result1).isEqualTo(expected1);

        Address address2 = new Address(null, "London", "1-st street", "5", "1");
        List<String> result2 = Validator.validate(address2);
        List<String> expected2 = List.of("country");
        assertThat(result2).isEqualTo(expected2);

        Address address3 = new Address("USA", null, null, null, "1");
        List<String> result3 = Validator.validate(address3);
        List<String> expected3 = List.of("city", "street", "houseNumber");
        assertThat(result3).isEqualTo(expected3);
    }

    // BEGIN
    @Test
    void testAdvancedValidate1() {
        Address address = new Address("USA", "Texas", null, "7", "2");
        Map<String, List<String>> result = Validator.advancedValidate(address);
        Map<String, List<String>> expectedMap = new HashMap<>(Map.of(
                "country", new ArrayList<>(List.of("length less than 4")),
                "street", new ArrayList<>(List.of("can not be null"))
        ));
        assertThat(result).isEqualTo(expectedMap);
    }

    @Test
    void testAdvancedValidate2() {
        Address address = new Address(null, "Texas", "Alabyana", null, "2");
        Map<String, List<String>> result = Validator.advancedValidate(address);
        Map<String, List<String>> expectedMap = new HashMap<>(Map.of(
                "country", new ArrayList<>(List.of("can not be null")),
                "street", new ArrayList<>(List.of("length less than 9")),
                "houseNumber", new ArrayList<>(List.of("can not be null"))
        ));
        assertThat(result).isEqualTo(expectedMap);
    }
    // END
}
