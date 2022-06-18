package com.example.programacao_funcional.comparator_predicate.sevice;

import com.example.programacao_funcional.comparator_predicate.model.Product;
import com.example.programacao_funcional.comparator_predicate.model.dto.ProductRequest;
import com.example.programacao_funcional.comparator_predicate.model.dto.ProductResponse;
import com.example.programacao_funcional.comparator_predicate.repository.ProductRepository;
import com.example.programacao_funcional.comparator_predicate.util_implemented_classes.MyComparator;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    private final MyComparator comparator;

    private final ProductRepository repository;

    public ProductService(MyComparator comparator, ProductRepository repository) {
        this.comparator = comparator;
        this.repository = repository;
    }

    public ProductResponse addProduct(ProductRequest request){
        return ProductResponse.fromModel(
                repository.save(request.toModel(request))
        );
    }

    public List<ProductResponse> sortByName(){

        List<Product> productList = repository.findAll();

        /** utilizando a inteface Comparator para comparar objetos e ordena-los*/

        /** FOMR 1 - classe implementada */
        // productList.sort(new MyComparator());

        /** FORM 2 - intanciação do Comparator */
        //Comparator<Product> comparator = getComparator();
        //productList.sort(comparator);

        /** FORM 3 - intanciação do Comparator utilizando EXPRESSAO LAMBDA */
        //Comparator<Product> comparator = getArrowFunction();
        //productList.sort(comparator);

        /** FORM 4 - funcao anônima hardcode*/
        productList.sort((p1, p2) -> p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase()));

        return convertList( productList );

    }

    public List<ProductResponse> sortByPrice(){

        List<Product> productList = repository.findAll();

        /** funcao anônima */
        productList.sort((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));

        return convertList( productList );

    }

    public List<ProductResponse> sortByPriceArgument() {

        List<Product> productList = repository.findAll();

        /** Funcao como argumento de outra funcao*/
        productList.sort( ProductService::comparePrices );

        return convertList( productList );
    }


    public ProductResponse findById(Long id) throws RuntimeException {
        return ProductResponse.fromModel(
                repository.findById(id).orElseThrow(
                        () -> new RuntimeException("Objeto nao encontrado")
                )
        );
    }

    public List<ProductResponse> findAll(){

        return convertList(
                repository.findAll()
        );

    }

    /** PREDICATE */
    public List<ProductResponse> removeIf(Double price){
        List<Product> productList = repository.findAll();

        /** FORM 1 - Expressao lambda declarada */
//        Predicate<Product> predicate = p -> p.getPrice() <= price;
//        productList.removeIf(predicate);

        /** FOMR 2 - Expressão lambda inline */
        productList.removeIf( p -> p.getPrice() <= price);

        /** FOMR 3 - implementação da Interface Predicate */
//        ProductPredicate.PRICE = price;
//        productList.removeIf(new ProductPredicate());

        /** FORM 4 - Reference Method com método estático */
//        Product.PRICE = price;
//        productList.removeIf( Product::staticProductPredicate );

        /** FORM 5 - Reference Method com método não estático */
//        Product.PRICE = price;
//        productList.removeIf( Product::nonStaticProductPredicate );


        return convertList(
                productList
        );
    }


    /** METODOS AUXILIARES */
    private List<ProductResponse> convertList(List<Product> product){
        List<ProductResponse> productResponseSet = new ArrayList<>();

        product.forEach( p -> {

            productResponseSet.add(ProductResponse.fromModel(p));

        });

        return productResponseSet;
    }

    private Comparator<Product> getComparator() {
        return new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase());
            }
        };
    }

    private Comparator<Product> getArrowFunction() {
        /** modo 1*/
//        return (p1, p2) -> {
//            return p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase());
//        };

        /** modo 2*/
        return (p1, p2) -> p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase());
    }

    private static int comparePrices(Product p1, Product p2){
        return p1.getPrice().compareTo(p2.getPrice());
    }

}
