package com.epam.onlinestore.repository;

import com.epam.onlinestore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findAll();

    Product getProductById(long productId);

    @Query("select pr from Product pr where pr.price=?1")
    Page<Product> findAllByPrice(double price, Pageable pageable);

}
