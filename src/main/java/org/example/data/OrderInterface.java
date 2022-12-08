package org.example.data;

import org.example.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderInterface {

    Order save(Order order);

    Order update(Order order);

    void delete(String sku);

    List<Order> findAll();

    Optional<Order> findBySku(String sku);
}