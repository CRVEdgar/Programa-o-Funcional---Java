package com.example.programacao_funcional.comparator.util;

import com.example.programacao_funcional.comparator.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class MyComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        //
        return p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase());
    }
}
