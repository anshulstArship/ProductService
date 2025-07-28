package com.main.productservice.services;

import com.main.productservice.dtos.FakeStoreProductDto;
import com.main.productservice.dtos.GenericProductDto;
import com.main.productservice.models.Product;
import com.main.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    public SelfProductServiceImpl(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
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
        //Page<Product> products = productRepository.getAll(Pageable.ofSize(2));
        return null;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return null;
    }
}
