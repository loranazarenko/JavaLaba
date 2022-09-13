package com.epam.onlinestore.repository;

import com.epam.onlinestore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findAll();

    Product getProductById(long productId);

    @Query("select pr from Product pr where pr.price=?1")
    Page<Product> findAllByPrice(double price, Pageable pageable);

    @Modifying
    @Query("delete from Product pr where pr.id=?1")
    void deleteProduct(Long id);

    Product getProductByName(String name);

    Product save(Product product);
}
