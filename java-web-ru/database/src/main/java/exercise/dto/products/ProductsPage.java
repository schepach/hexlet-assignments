package exercise.dto.products;

import exercise.dto.BasePage;
import exercise.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ProductsPage extends BasePage {
    private List<Product> products;
}
