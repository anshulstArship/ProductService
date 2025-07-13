package com.main.productservice.services;

import com.main.productservice.dtos.FakeStoreProductDto;
import com.main.productservice.dtos.GenericProductDto;
import com.main.productservice.exceptions.NotFoundException;

import com.main.productservice.thirdpartyclients.FakeStoreProductServiceClient;

import org.springframework.context.annotation.Primary;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service("fakestore")
@Primary // Marking this class as Default while Qualifier allows to verify which specification we are injecting
public class FakeStoreProductImpl implements ProductService {
    private final FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductImpl(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return getProduct(fakeStoreProductServiceClient.getProductById(id));
    }

    public GenericProductDto createProduct(GenericProductDto product) {
        return getProduct(fakeStoreProductServiceClient.createProduct(product));

    }

    @Override
    public List<GenericProductDto> getAllProduct() {
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductServiceClient.getAllProduct()) {
            genericProductDtos.add(getProduct(fakeStoreProductDto));
        }
        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return getProduct(fakeStoreProductServiceClient.deleteProduct(id));
    }

    private GenericProductDto getProduct(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());
        return product;
    }

}
/* Using Adapter Design Pattern ( Client will call 3rd Party API instead of our business class calling directly 3rd Party API
Client is a wrapper over FakeStoreAPI
Service Layer+Client Layer
 */
