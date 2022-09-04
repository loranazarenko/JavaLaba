package com.epam.onlinestore.repository;

import com.epam.onlinestore.entity.Product;

import java.util.List;

public interface ProductRepository {
    Product getProductsById(long productId);

    List<Product> findAll();

    Product updateProduct(String name, Product product);

    void deleteProduct(Product product);

    Product save(Product product);
}
