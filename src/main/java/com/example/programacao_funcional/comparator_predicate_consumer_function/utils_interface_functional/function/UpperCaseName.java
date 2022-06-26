package com.example.programacao_funcional.comparator_predicate_consumer_function.utils_interface_functional.function;

import com.example.programacao_funcional.comparator_predicate_consumer_function.model.Product;

import java.util.function.Function;

                          /** Function<T,R> --> RECEBE um objeto do tipo T e devolve um objeto do tipo R */
public class UpperCaseName implements Function<Product, String> {

    @Override
    public String apply(Product product) {
        /** recebe o produto e retorna o nome dele em caixa alta
         * todo: pode retornar qualquer tipo de dado desejado, baixa especificar no 2° parametro do Function
         * */
        return product.getName().toUpperCase();
    }
}
