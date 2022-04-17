package com.epam.onlinestore.service;

import com.epam.onlinestore.controller.dto.ProductDto;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.EntityNotFoundException;
import com.epam.onlinestore.repository.impl.ProductRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final ProductRepositoryImpl productRepository = new ProductRepositoryImpl();

    public ProductDto getProductById(Long productId) {
        log.info("get Product with id {}", productId);
        Product product = productRepository.getProductsById(productId);
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

    public ProductDto updateProduct(String name, ProductDto productDto) {
        log.info("update Product with name {}", name);
        Product product = ProductMapper.INSTANCE.mapProduct(productDto);
        product = productRepository.updateProduct(name, product);
        return ProductMapper.INSTANCE.mapProductDto(product);
    }

    public void deleteProduct(long id) {
        log.info("delete Product with id {} ", id);
        ProductDto productDto = getProductById(id);
        Product product = ProductMapper.INSTANCE.mapProduct(productDto);
        productRepository.deleteProduct(product);
    }

    public ProductDto addNewProduct(ProductDto productDto) {
        log.info("create new Product with name {} ", productDto.getName());
        Product product = ProductMapper.INSTANCE.mapProduct(productDto);
        product = productRepository.save(product);
        return ProductMapper.INSTANCE.mapProductDto(product);
    }

    private ProductDto mapProductToProductDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

}
