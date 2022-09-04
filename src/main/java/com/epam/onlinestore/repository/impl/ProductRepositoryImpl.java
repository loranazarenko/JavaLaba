package com.epam.onlinestore.repository.impl;

import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.ProductNotFoundException;
import com.epam.onlinestore.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * a Layer class that contains methods for working with a database with a Product
 */
@Slf4j
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final List<Product> productList = new ArrayList<>();

    public Product getProductsById(long productId) {
        return productList.stream()
                .filter(product -> product.getId() == productId)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product is not found!"));
    }

    public List<Product> findAll() {
        return new ArrayList<>(productList);
    }

    public Product updateProduct(String name, Product product) {
        boolean isDeleted = productList.removeIf(p -> p.getName().equals(name));
        if (isDeleted) {
            productList.add(product);
        } else {
            throw new ProductNotFoundException("Product is not found!");
        }
        return product;
    }

    public void deleteProduct(Product product) {
        productList.removeIf(prod -> prod.getId() == product.getId());
    }

    public Product save(Product product) {
        productList.add(product);
        return product;
    }
}
