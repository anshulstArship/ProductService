package com.main.productservice.services;

import com.main.productservice.dtos.FakeStoreProductDto;
import com.main.productservice.dtos.GenericProductDto;
import com.main.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfProductServiceImpl implements ProductService{
    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto){
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProduct() {
        return null;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return null;
    }
}
