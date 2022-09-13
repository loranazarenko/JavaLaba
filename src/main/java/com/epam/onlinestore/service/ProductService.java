package com.epam.onlinestore.service;

import com.epam.onlinestore.dto.ProductDto;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.EntityNotFoundException;
import com.epam.onlinestore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * Class that contains methods for working with Products
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> findAllByPrice(double price, Pageable pageable) {
        return productRepository.findAllByPrice(price, pageable);
    }

    public ProductDto getProductById(Long productId) {
        log.info("get Product with id {}", productId);
        Product product = productRepository.getProductById(productId);
        if (product == null) {
            throw new EntityNotFoundException(format("Product with id %s not found", productId));
        }
        return ProductMapper.INSTANCE.mapProductDto(product);
    }

    public List<ProductDto> getAllProductsDto() {
        log.info("get all Products");
        return productRepository.findAll()
                .stream()
                .map(this::mapProductToProductDto)
                .collect(Collectors.toList());
    }

    public List<Product> getAllProducts() {
        log.info("get all Products");
        return productRepository.findAll();
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        log.info("update Product with id {}", id);
        Product product = ProductMapper.INSTANCE.mapProduct(productDto);
        product = productRepository.save(product);
        return ProductMapper.INSTANCE.mapProductDto(product);
    }

    public void deleteProduct(Long id) {
        log.info("deleteProduct with id {}", id);
        productRepository.deleteProduct(id);
    }

    public ProductDto addNewProduct(ProductDto productDto) {
        log.info("create new Product with name {} ", productDto.getName());
        Product product = ProductMapper.INSTANCE.mapProduct(productDto);
        product = productRepository.save(product);
        return ProductMapper.INSTANCE.mapProductDto(product);
    }

    public ProductDto mapProductToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

    public Product getProductByName(String name) {
        log.info("get Product with id {}", name);
        Product product = productRepository.getProductByName(name);
        if (product == null) {
            throw new EntityNotFoundException(format("Product with name %s not found", name));
        }
        return product;
    }
}
