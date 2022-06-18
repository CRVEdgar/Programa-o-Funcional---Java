package com.example.programacao_funcional.comparator_predicate.util_implemented_classes;

import com.example.programacao_funcional.comparator_predicate.model.Product;
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
