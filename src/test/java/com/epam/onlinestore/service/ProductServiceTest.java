package com.epam.onlinestore.service;

import com.epam.onlinestore.dto.ProductDto;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.repository.ProductRepository;
import com.epam.onlinestore.util.UtilsForTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Class tests methods for working with Products
 */
@Slf4j
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private final static Long MOCK_ID = 2L;
    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;

    @Test
    public void getProductByIdTest() {
        //given
        Product expectedProduct = Product.builder().id(UtilsForTests.MOCK_ID).build();
        when(productRepository.getProductById(UtilsForTests.MOCK_ID)).thenReturn(expectedProduct);

        //when
        ProductDto actualProduct = productService.getProductById(UtilsForTests.MOCK_ID);

        //then
        assertEquals(expectedProduct.getId(), actualProduct.getId());
    }

    @Test
    public void getAllProductsTest() {
        //given
        List<Product> expectedData = new ArrayList<>();
        ProductDto productDto = UtilsForTests.createTestProductDto();
        productDto.setId(UtilsForTests.MOCK_ID);
        expectedData.add(ProductMapper.INSTANCE.mapProduct(productDto));
        when(productRepository.findAll()).thenReturn(expectedData);

        //when
        List<Product> actualProducts = productService.getAllProducts();

        //then
        assertEquals(expectedData.get(0), actualProducts.get(0));
    }

    @Test
    public void updateProductTest() {
        //given
        Product editedProduct = Product.builder()
                .id(UtilsForTests.MOCK_ID)
                .name("TESTNAME")
                .description("Good thing")
                .price(1.1)
                .quantity(2)
                .build();
        when(productRepository.save(editedProduct)).thenReturn(editedProduct);
        editedProduct.setPrice(2.1);
        when(productRepository.save(editedProduct)).thenReturn(editedProduct);

        //when
        ProductDto editedProductDto = ProductMapper.INSTANCE.mapProductDto(editedProduct);
        ProductDto productActual = productService.updateProduct(editedProduct.getId(), editedProductDto);

        //then
        assertEquals(editedProduct.getPrice(), productActual.getPrice());
    }

    @Test
    public void deleteProductTest() {
        //given
        doNothing().when(productRepository).deleteProduct(UtilsForTests.MOCK_ID);

        //when
        productService.deleteProduct(UtilsForTests.MOCK_ID);

        //then
        verify(productRepository, times(1)).deleteProduct(MOCK_ID);
    }

    @Test
    public void addNewProductTest() {
        //given
        when(productRepository.findAll()).thenReturn(Collections.singletonList(Product.builder()
                .name("test")
                .price(10)
                .quantity(5)
                .build()));

        //when
        List<ProductDto> productDtos = productService.getAllProductsDto();
        System.out.println(productDtos);
        //then
        assertThat(productDtos, hasSize(1));
    }

    @Test
    void deleteProductWithExceptionTest() {
        doThrow(RuntimeException.class).when(productRepository).deleteProduct(any());

        assertThrows(RuntimeException.class,
                () -> productService.deleteProduct(UtilsForTests.MOCK_ID));
    }
}
