package com.main.productservice.services;


import com.main.productservice.dtos.GenericProductDto;
import com.main.productservice.exceptions.NotFoundException;


import java.util.List;


public interface ProductService {
    GenericProductDto getProductById(Long id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto genericProductDto);

    List<GenericProductDto> getAllProduct();

    GenericProductDto deleteProduct(Long id);

}
