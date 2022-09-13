package com.epam.onlinestore.service;

import com.epam.onlinestore.dto.ProductDto;
import com.epam.onlinestore.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Declaring an instruction for mapstruct
 */
@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDto mapProductDto(Product product);
    Product mapProduct(ProductDto productDto);

}


