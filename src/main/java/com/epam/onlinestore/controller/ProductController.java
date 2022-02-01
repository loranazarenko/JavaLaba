package com.epam.onlinestore.controller;

import com.epam.onlinestore.controller.dto.ProductDto;
import com.epam.onlinestore.exception.DaoException;
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
    public String getAllProducts() {
     //   productService.getAllProductsDto();
        return "get all products";
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Get product by id")
    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable("id") long id) throws DaoException {
      // ProductDto product = productService.getProductById(id);
        return "get product";
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/product/{id}")
    public String editProduct(@PathVariable("id") Long id,
                              @Valid @RequestBody ProductDto productDto) {
       // productService.updateProduct(Math.toIntExact(id), productDto);
        return "edit product";
    }

    @ApiOperation("Delete product")
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id) throws DaoException {
       // productService.deleteProduct(id);
        return "delete product";
    }

    @ApiOperation("Create product")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/product")
    public String createProduct(@Valid @RequestBody ProductDto productDto) throws DaoException {
     //   productService.addNewProduct(productDto);
        return "Create product";
    }

}
