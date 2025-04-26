package exercise.model;

import jakarta.persistence.*;
import lombok.Data;

// BEGIN
@Entity
@Table(name = "person")
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
}
// END
