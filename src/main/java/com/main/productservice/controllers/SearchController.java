package com.main.productservice.controllers;

import com.main.productservice.dtos.GenericProductDto;
import com.main.productservice.dtos.SearchRequestDto;
import com.main.productservice.services.SearchService;
import com.main.productservice.services.SelfProductServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;
    public SearchController(SearchService searchService){
        this.searchService=searchService;
    }

    @PostMapping()
    public Page<GenericProductDto> getAllProducts(@RequestBody SearchRequestDto requestDto){
        return searchService.searchProducts(requestDto.getQuery(), PageRequest.of(requestDto.getPageNo(), requestDto.getSizeOfPage()),requestDto.getSortByParameters());
    }
}
