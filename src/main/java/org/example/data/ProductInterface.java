package org.example.data;

import org.example.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductInterface {

    Product save(Product product);

    Product update(Product product);

    Product updateQuantity(Product product);

    void delete(String sku);

    List<Product> findAll();

    Optional<Product> findBySku(String sku);
}
