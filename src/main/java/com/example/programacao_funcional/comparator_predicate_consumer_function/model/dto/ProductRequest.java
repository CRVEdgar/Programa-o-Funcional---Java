package com.example.programacao_funcional.comparator_predicate_consumer_function.model.dto;


import com.example.programacao_funcional.comparator_predicate_consumer_function.model.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductRequest {

    @NotBlank
    private String name;
    @NotNull
    private Double price;

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public static Product toModel(ProductRequest request){
        return new Product(request.getName(), request.getPrice());
    }
}
