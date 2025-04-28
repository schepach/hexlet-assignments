package exercise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String title;

    private int price;

    @CreatedDate
    private LocalDate createdAt;
}
