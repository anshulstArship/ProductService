package com.main.productservice.models;

import lombok.Getter;
import lombok.Setter;

// Models are the data stored in DB
@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    private Category category;
    private double price;
}
