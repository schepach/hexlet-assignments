package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import exercise.model.Product;
import exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping
    public List<Product> getProducts(@RequestParam(defaultValue = "1") Integer min,
                                     @RequestParam(required = false) Integer max) {

        if (min == 1 && max == null) {
            // Default
            return this.productRepository.findAll();
        }

        if (max != null) {
            // From and To
            return this.productRepository.findAllByPriceBetweenOrderByPriceAsc(min, max);
        }

        // Only From
        return this.productRepository.findAllByPriceGreaterThanOrderByPriceAsc(min);
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
