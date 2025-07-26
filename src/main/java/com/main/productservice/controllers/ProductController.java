package com.main.productservice.controllers;

import com.main.productservice.dtos.ExceptionData;
import com.main.productservice.dtos.FakeStoreProductDto;
import com.main.productservice.dtos.GenericProductDto;
import com.main.productservice.exceptions.NotFoundException;
import com.main.productservice.models.Product;
import com.main.productservice.security.JwtObject;
import com.main.productservice.security.TokenValidator;
import com.main.productservice.services.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private TokenValidator tokenValidator;

    public ProductController(ProductService productService,TokenValidator validator) {
        this.productService = productService;
        this.tokenValidator=validator;
    }

    @GetMapping()
    public List<GenericProductDto> getAllProducts(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken) {
        return productService.getAllProduct();

    }

    @GetMapping("{id}")
    public ResponseEntity<GenericProductDto> getProductById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken, @PathVariable("id") Long id) throws NotFoundException {
        System.out.println(authToken);
        Optional<JwtObject> jwtObject;
        if(authToken!=null){
            jwtObject=tokenValidator.validateToken(authToken);
        }
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
