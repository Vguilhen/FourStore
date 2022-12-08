package org.example.dao;

import org.example.data.ProductInterface;
import org.example.infra.ConnectionFactory;
import org.example.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDao implements ProductInterface {
    @Override
    public Product save(Product product) {

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO products (sku, description, quantity, price) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getSku());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setDouble(4, product.getPrice());

            preparedStatement.executeUpdate();

            //Recovery id from db
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            Long generatedId = resultSet.getLong("id");
            product.setId(generatedId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Product update(Product product) {

        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "UPDATE products SET sku = ?, description = ?, quantity = ?, price = ? WHERE id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getSku());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setLong(5, product.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public void delete(String sku) {

        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "DELETE FROM products WHERE sku = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sku);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT id, sku, description, quantity, price FROM products";

        List<Product> products = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String sku = resultSet.getString("sku");
                String description = resultSet.getString("description");
                int quantity = resultSet.getInt("quantity");
                Double price = resultSet.getDouble("price");

                Product product = new Product(id, sku, description, quantity, price);
                products.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public Optional<Product> findBySku(String sku) {

        String sql = "SELECT id, sku, description, quantity, price FROM products WHERE sku = ?";
        Product product = null;

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sku);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Long primaryKey = resultSet.getLong("id");
                String skuFindBySku = resultSet.getString("sku");
                String description = resultSet.getString("description");
                int quantity = resultSet.getInt("quantity");
                Double price = resultSet.getDouble("price");

                product = new Product(primaryKey, skuFindBySku, description, quantity, price);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(product);
    }

    @Override
    public Product updateQuantity(Product product) {

        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "UPDATE products SET quantity = ? WHERE sku = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, product.getQuantity());
            preparedStatement.setString(2, product.getSku());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
}
