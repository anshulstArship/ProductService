package com.main.productservice.thirdpartyclients;

import com.main.productservice.dtos.FakeStoreProductDto;
import com.main.productservice.dtos.GenericProductDto;
import com.main.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceClient implements ThirdPartyProductServiceClient{
    private String getProductRequestUrl="https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl ="https://fakestoreapi.com/products/";
    private RestTemplateBuilder restTemplateBuilder;
    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }
    @Override
    public FakeStoreProductDto getProductById(Long id)throws NotFoundException {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto=restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto1= fakeStoreProductDto.getBody();
        if(fakeStoreProductDto1==null){
            throw new NotFoundException("Doesn't exist");
        }
        return fakeStoreProductDto1;
    }

    public FakeStoreProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProduct=restTemplate.postForEntity(createProductRequestUrl,product,FakeStoreProductDto.class);
        return fakeStoreProduct.getBody();

    }

    @Override
    public List<FakeStoreProductDto> getAllProduct() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProducts = restTemplate.getForEntity(createProductRequestUrl,FakeStoreProductDto[].class);
        List<FakeStoreProductDto> result = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProducts.getBody()){
            result.add(fakeStoreProductDto);
        }
        return result;
    }

    @Override
    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response=restTemplate.execute(getProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return response.getBody();
    }


}
