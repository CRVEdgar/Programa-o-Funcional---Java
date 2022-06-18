package com.example.programacao_funcional.comparator_predicate.utils_interface_functional;

import com.example.programacao_funcional.comparator_predicate.model.Product;

import java.util.function.Predicate;

public class ProductPredicate implements Predicate<Product> {

    public static Double PRICE = 10.0;

    @Override
    public boolean test(Product p) {
        /** retorna quem atende ao predicado abaixo: */
        return p.getPrice() <= PRICE;
    }

    /**
    @Override
    public Predicate<Product> and(Predicate<? super Product> other) {
        return Predicate.super.and(other);
    }

    @Override
    public Predicate<Product> negate() {
        return Predicate.super.negate();
    }

    @Override
    public Predicate<Product> or(Predicate<? super Product> other) {
        return Predicate.super.or(other);
    }
    */
}
