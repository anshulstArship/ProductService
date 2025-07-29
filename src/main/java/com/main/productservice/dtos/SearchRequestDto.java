package com.main.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequestDto {
    private String query;
    private int pageNo;
    private int sizeOfPage;
    private List<SortParameters> sortByParameters;
}
