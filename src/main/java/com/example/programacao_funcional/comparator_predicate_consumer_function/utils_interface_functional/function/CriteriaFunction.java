package com.example.programacao_funcional.comparator_predicate_consumer_function.utils_interface_functional.function;

import com.example.programacao_funcional.comparator_predicate_consumer_function.model.Product;

import java.util.List;
import java.util.function.Predicate;

public class CriteriaFunction {

    public static double filteredSum(List<Product> list, Predicate<Product> criteria){
        double sum = 0.0;
        for(Product p: list){
            if(criteria.test(p)){
                sum += p.getPrice();
            }
        }
        return sum;
    }
}
