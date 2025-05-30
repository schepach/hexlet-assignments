package exercise.repository;

import exercise.model.Product;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepository extends BaseRepository {

    // BEGIN
    public static void save(Product product) throws SQLException {
        String sql = "INSERT INTO products (title, price) VALUES (?, ?)";
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getTitle());
            statement.setInt(2, product.getPrice());
            statement.executeUpdate();

            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong("id"));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static Optional<Product> find(Long productId) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, productId);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var title = resultSet.getString("title");
                var price = resultSet.getInt("price");
                Product product = new Product(title, price);
                product.setId(productId);
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public static List<Product> getEntities() throws SQLException {
        String sql = "SELECT * FROM products";
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sql)) {

            var products = new ArrayList<Product>();
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var title = resultSet.getString("title");
                var price = resultSet.getInt("price");
                Product product = new Product(title, price);
                product.setId(id);
                products.add(product);
            }
            return products;
        }
    }
    // END
}
