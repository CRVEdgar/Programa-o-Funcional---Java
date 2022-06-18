package com.example.programacao_funcional.comparator_predicate.utils_interface_functional.consumer;

import com.example.programacao_funcional.comparator_predicate.model.Product;

import java.util.function.Consumer;

public class PriceUpdate implements Consumer<Product> {

    public static Double PERCENTE = 0.0;
    @Override
    public void accept(Product product) {
        product.setPrice( product.getPrice() * PERCENTE );
    }
}
