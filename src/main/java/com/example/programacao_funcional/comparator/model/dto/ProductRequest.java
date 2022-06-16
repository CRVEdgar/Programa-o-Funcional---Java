package com.example.programacao_funcional.comparator.model.dto;


import com.example.programacao_funcional.comparator.model.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductRequest {

    @NotBlank
    private String name;
    @NotNull
    private double price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public static Product toModel(ProductRequest request){
        return new Product(request.getName(), request.getPrice());
    }
}
