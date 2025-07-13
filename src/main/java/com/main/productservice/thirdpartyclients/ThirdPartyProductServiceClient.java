package com.main.productservice.thirdpartyclients;

import com.main.productservice.dtos.FakeStoreProductDto;
import com.main.productservice.dtos.GenericProductDto;
import com.main.productservice.exceptions.NotFoundException;

import java.util.List;
/* Wrapper over Fakestore API

 */
public interface ThirdPartyProductServiceClient {
    FakeStoreProductDto getProductById(Long id) throws NotFoundException;
    FakeStoreProductDto createProduct(GenericProductDto genericProductDto);
    List<FakeStoreProductDto> getAllProduct();
    FakeStoreProductDto deleteProduct(Long id);
}
