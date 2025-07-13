package com.main.productservice.controllers;

import com.main.productservice.dtos.ExceptionData;
import com.main.productservice.dtos.FakeStoreProductDto;
import com.main.productservice.dtos.GenericProductDto;
import com.main.productservice.exceptions.NotFoundException;
import com.main.productservice.models.Product;
import com.main.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<GenericProductDto> getAllProducts() {
        return productService.getAllProduct();

    }

    @GetMapping("{id}")
    public ResponseEntity<GenericProductDto> getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<GenericProductDto> createProduct(@RequestBody GenericProductDto genericProductDto) {
        return new ResponseEntity<>(productService.createProduct(genericProductDto), HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.BAD_REQUEST);

    }
//    @ExceptionHandler(NotFoundException.class)
//    private ResponseEntity<ExceptionData> handleNotFoundException(NotFoundException e){// Spring Boot will automatically call when any method in controller will throw this exception
//        return new ResponseEntity<>(new ExceptionData(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()),HttpStatus.NOT_FOUND);
//    }
}
