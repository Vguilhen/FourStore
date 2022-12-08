package org.example.dao;

import org.example.data.OrderInterface;
import org.example.enums.PaymentMethodEnum;
import org.example.infra.ConnectionFactory;
import org.example.model.Order;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDao implements OrderInterface {
    @Override
    public Order save(Order order) {

        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql1 = "INSERT INTO orders (quantity, total, date, sku, customername, customercpf) VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getQuantity());
            preparedStatement.setDouble(2, order.getTotal());
            preparedStatement.setTimestamp(3, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(4, order.getSku());
            preparedStatement.setString(5, order.getCustomerName());
            preparedStatement.setString(6, order.getCustomerCpf());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            Long generatedId = resultSet.getLong("id");
            order.setId(generatedId);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Order update(Order order) {

        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "UPDATE orders SET quantity = ?, total = ?, date = ?, sku = ?, customername = ?, customercpf = ? WHERE id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, order.getQuantity());
            preparedStatement.setDouble(2, order.getTotal());
            preparedStatement.setTimestamp(3, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(4, order.getSku());
            preparedStatement.setString(5, order.getCustomerName());
            preparedStatement.setString(6, order.getCustomerCpf());
            preparedStatement.setLong(7, order.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    @Override
    public void delete(String sku) {

        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "DELETE FROM orders WHERE sku = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sku);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT id, quantity, total, date, sku, customername, customercpf FROM orders";

        List<Order> orders = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                int quantity = resultSet.getInt("quantity");
                Double total = resultSet.getDouble("total");
                LocalDateTime date = resultSet.getDate("date").toLocalDate().atTime(LocalTime.now());
                String sku = resultSet.getString("sku");
                String customername = resultSet.getString("customername");
                String customercpf = resultSet.getString("customercpf");

                Order order = new Order(id, quantity, total, date, sku, customername, customercpf);

                orders.add(order);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    @Override
    public Optional<Order> findBySku(String sku) {

        String sql = "SELECT id, quantity, total, date, sku, customername, customercpf FROM orders WHERE sku = ?";
        Order order = null;

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sku);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                int quantity = resultSet.getInt("quantity");
                Double total = resultSet.getDouble("total");
                LocalDateTime date = resultSet.getDate("date").toLocalDate().atTime(LocalTime.now());
                String skuFnd = resultSet.getString("sku");
                String customername = resultSet.getString("customername");
                String customercpf = resultSet.getString("customercpf");

                order = new Order(id, quantity, total, date, skuFnd, customername, customercpf);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(order);
    }
}
