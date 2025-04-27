package exercise.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

// BEGIN
@Entity
@Table(name = "product")
@EqualsAndHashCode(of = {"title", "price"})
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int price;
}
// END
