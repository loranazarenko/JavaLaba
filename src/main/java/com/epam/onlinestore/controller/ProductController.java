package com.epam.onlinestore.controller;

import com.epam.onlinestore.controller.dto.ProductDto;
import com.epam.onlinestore.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * RestController for working with products and api for swagger
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = "API description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation("Get all products")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/product")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProductsDto();
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get product by id")
    @GetMapping("/product/{id}")
    public ProductDto getProductById(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/product/{name}")
    public ProductDto editProduct(@PathVariable("name") String name,
                                  @Valid @RequestBody ProductDto productDto) {
        return productService.updateProduct(name, productDto);
    }

    @ApiOperation("Delete product")
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
    }

    @ApiOperation("Create product")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/product")
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) {
        return productService.addNewProduct(productDto);
    }
}
