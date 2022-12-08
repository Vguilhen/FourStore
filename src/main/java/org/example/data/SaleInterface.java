package org.example.data;

import org.example.model.Order;
import org.example.model.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleInterface {

    Sale save(Sale sale);

    Sale update(Sale sale);

    void delete(String sku);

    List<Sale> findAll();

    Optional<Sale> findBySku(String sku);
}