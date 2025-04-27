package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import exercise.model.Product;
import exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "")
    public List<Product> index() {
        return productRepository.findAll();
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // BEGIN
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found!"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        Product updProduct = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found!"));

        updProduct.setId(id);
        updProduct.setPrice(product.getPrice());
        updProduct.setTitle(product.getTitle());
        this.productRepository.save(updProduct);

        return updProduct;
    }
    // END

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
