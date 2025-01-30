package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Value;

import java.io.IOException;

// BEGIN
@Value
// END
class Car {

    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car deserialize(String json) {
        try {
            return new ObjectMapper().readValue(json, Car.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // END
}
