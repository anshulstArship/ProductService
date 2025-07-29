package com.main.productservice.services;

import com.main.productservice.dtos.GenericProductDto;
import com.main.productservice.dtos.SortParameters;
import com.main.productservice.models.Product;
import com.main.productservice.repositories.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private ProductRepository productRepository;
    public SearchService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    public Page<GenericProductDto> searchProducts(String query, Pageable pageable,List<SortParameters> sortByParameters){
        Sort sort;
        if(sortByParameters.get(0).getSortType().equals("ASC")){
            sort=Sort.by(sortByParameters.get(0).getParameterName());
        }else{
            sort=Sort.by(sortByParameters.get(0).getParameterName()).descending();
        }
        for(int i=1;i< sortByParameters.size();i++){
            if(sortByParameters.get(i).getSortType().equals("ASC")) {
                sort = sort.and(Sort.by(sortByParameters.get(i).getParameterName()));
            }else{
                sort = sort.and(Sort.by(sortByParameters.get(i).getParameterName()).descending());
            }
        }
        Page<Product> productsPage = productRepository.findAllByTitleContaining(query, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),sort));
        List<Product> products = productsPage.get().collect(Collectors.toList());
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for(Product product:products){
            genericProductDtos.add(from(product));
        }
        Page<GenericProductDto> genericProductPage = new PageImpl<>(
                genericProductDtos,productsPage.getPageable(),productsPage.getTotalElements()
        );
        return genericProductPage;
    }
    private GenericProductDto from(Product product){
        GenericProductDto genericProductDto = new GenericProductDto();
        //genericProductDto.setId(product.getId());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setPrice(product.getPrice());
        return genericProductDto;
    }
}
