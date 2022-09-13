package com.epam.onlinestore.controller;

import com.epam.onlinestore.dto.ProductDto;
import com.epam.onlinestore.entity.Product;
import com.epam.onlinestore.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * RestController for working with products and api for swagger
 */
@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/product")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProductsDto();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/product/{id}")
    public ProductDto getProductById(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/product/{id}")
    public ProductDto updateProduct(@PathVariable("id") Long id,
                                    @Valid @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted successfully!.", HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/product")
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) {
        return productService.addNewProduct(productDto);
    }

    @GetMapping("/showProductsByPage/{page}")
    public String showProductController(@PathVariable("page") int page) {
        PageRequest pageRequest = PageRequest.of(page, 5, Sort.by("price").descending().and(Sort.by("name")));
        Page<Product> productPage = productService.findAllByPrice(10, pageRequest);
        for (int i = 0; i < productPage.getContent().size(); i++) {
            log.info(String.valueOf(productPage.getContent().get(i)));
            log.info(String.valueOf(productPage.getTotalElements()));
        }
        return "/showProducts";
    }


}
