package com.epam.onlinestore.service;

import com.epam.onlinestore.controller.dto.ProductDto;
import com.epam.onlinestore.dao.impl.ProductDAOImpl;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.exception.DaoException;
import com.epam.onlinestore.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

    private ProductDAOImpl productDAO;

    {
        try {
            productDAO = new ProductDAOImpl();
        } catch (SQLException e) {
            log.error(String.valueOf(e));
        }
    }

    public ProductDto getProductById(Long productId) throws DaoException {
        Product product = productDAO.getProductsById(productId);
        if (product == null) {
            throw new EntityNotFoundException(format("Product with id %s not found", productId));
        }
        return ProductMapper.INSTANCE.mapProductDto(product);
    }

    public List<ProductDto> getAllProductsDto() {
        return productDAO.findAll()
                .stream()
                .map(this::mapProductToProductDto)
                .collect(Collectors.toList());

    }

    public void updateProduct(int id, ProductDto productDto) {
        Product product = ProductMapper.INSTANCE.mapProduct(productDto);
        product.setId(id);
        productDAO.updateProduct(product);
    }

    public void deleteProduct(long id) throws DaoException {
        ProductDto productDto = getProductById(id);
        Product product = ProductMapper.INSTANCE.mapProduct(productDto);
        productDAO.deleteProduct(product);
    }

    public Product addNewProduct(ProductDto productDto) throws DaoException {
        Product product = ProductMapper.INSTANCE.mapProduct(productDto);
        return productDAO.save(product);
    }

    private ProductDto mapProductToProductDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

    private Product mapProductDtoToProduct(ProductDto productDto) {
        return Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build();
    }

}
