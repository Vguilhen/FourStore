package org.example.dao;

import org.example.data.SaleInterface;
import org.example.enums.PaymentMethodEnum;
import org.example.infra.ConnectionFactory;
import org.example.model.Product;
import org.example.model.Sale;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SaleDao implements SaleInterface {
    @Override
    public Sale save(Sale sale) {

        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql1 = "INSERT INTO sales (quantity, total, date, paymentmethod, sku, customername, customercpf, paymentdata) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, sale.getQuantity());
            preparedStatement.setDouble(2, sale.getTotal());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(4, sale.getPaymentMethod().toString());
            preparedStatement.setString(5, sale.getSku());
            preparedStatement.setString(6, sale.getCustomerName());
            preparedStatement.setString(7, sale.getCustomerCpf());
            preparedStatement.setString(8, sale.getPaymentData());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            Long generatedId = resultSet.getLong("id");
            sale.setId(generatedId);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

//    @Override
//    public Sale update(Sale sale) {
//        try (Connection connection = ConnectionFactory.getConnection()) {
//
//            String sql = "UPDATE sales SET quantity = ?, total = ?, date = ?, paymentmethod = ?, sku = ?, customername = ?, customercpf = ?, paymentdata = ? WHERE id = ?;";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, sale.getQuantity());
//            preparedStatement.setDouble(2, sale.getTotal());
//            preparedStatement.setTimestamp(3, java.sql.Timestamp.valueOf(LocalDateTime.now()));
//            preparedStatement.setString(4, sale.getPaymentMethod().toString());
//            preparedStatement.setString(5, sale.getSku());
//            preparedStatement.setString(6, sale.getCustomerName());
//            preparedStatement.setString(7, sale.getCustomerCpf());
//            preparedStatement.setString(8, sale.getPaymentData());
//            preparedStatement.setLong(9, sale.getId());
//
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return sale;
//    }
@Override
public Sale update(Sale sale) {
    try (Connection connection = ConnectionFactory.getConnection()) {

        String sql = "UPDATE sales SET paymentmethod = ?, paymentdata = ? WHERE sku = ?;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, sale.getPaymentMethod().toString());
        preparedStatement.setString(2, sale.getPaymentData());
        preparedStatement.setString(3, sale.getSku());

        preparedStatement.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return sale;
}

    @Override
    public void delete(String sku) {

        try (Connection connection = ConnectionFactory.getConnection()) {

            String sql = "DELETE FROM sales WHERE sku = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sku);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Sale> findAll() {

        String sql = "SELECT id, quantity, total, date, paymentmethod, sku, customername, customercpf, paymentdata FROM sales";

        List<Sale> sales = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                int quantity = resultSet.getInt("quantity");
                Double total = resultSet.getDouble("total");
                LocalDateTime date = resultSet.getDate("date").toLocalDate().atTime(LocalTime.now());
                PaymentMethodEnum paymentmethod = PaymentMethodEnum.valueOf(resultSet.getString("paymentmethod"));
                String sku = resultSet.getString("sku");
                String customername = resultSet.getString("customername");
                String customercpf = resultSet.getString("customercpf");
                String paymentdata = resultSet.getString("paymentdata");

                Sale sale = new Sale(id, quantity, total, date, paymentmethod, sku, customername, customercpf, paymentdata);

                sales.add(sale);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sales;
    }

    @Override
    public Optional<Sale> findBySku(String sku) {

        String sql = "SELECT id, quantity, total, date, paymentmethod, sku, customername, customercpf, paymentdata FROM sales WHERE sku = ?";
        Sale sale = null;

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, sku);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                int quantity = resultSet.getInt("quantity");
                Double total = resultSet.getDouble("total");
                LocalDateTime date = resultSet.getDate("date").toLocalDate().atTime(LocalTime.now());
                PaymentMethodEnum paymentmethod = PaymentMethodEnum.valueOf(resultSet.getString("paymentmethod"));
                String skuFnd = resultSet.getString("sku");
                String customername = resultSet.getString("customername");
                String customercpf = resultSet.getString("customercpf");
                String paymentdata = resultSet.getString("paymentdata");

                sale = new Sale(id, quantity, total, date, paymentmethod, skuFnd, customername, customercpf, paymentdata);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(sale);
    }
}
