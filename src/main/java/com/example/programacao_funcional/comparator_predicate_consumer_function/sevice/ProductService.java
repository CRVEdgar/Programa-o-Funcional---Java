package com.example.programacao_funcional.comparator_predicate_consumer_function.sevice;

import com.example.programacao_funcional.comparator_predicate_consumer_function.model.Product;
import com.example.programacao_funcional.comparator_predicate_consumer_function.model.dto.ProductRequest;
import com.example.programacao_funcional.comparator_predicate_consumer_function.model.dto.ProductResponse;
import com.example.programacao_funcional.comparator_predicate_consumer_function.repository.ProductRepository;
import com.example.programacao_funcional.comparator_predicate_consumer_function.utils_interface_functional.comparator.MyComparator;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.programacao_funcional.comparator_predicate_consumer_function.utils_interface_functional.function.CriteriaFunction.filteredSum;

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

    /**todo COMPARATOR  */
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

    /**todo PREDICATE */
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

    /**todo CONSUMER */
    public List<ProductResponse> updatePrice (Double percent){
        List<Product> productList = repository.findAll();

        /** FOMR 1 - implementação da Interface Consumer */
//        PriceUpdate.PERCENTE = percent;
//        productList.forEach(new PriceUpdate());

        /** FORM 2 - Expressao lambda declarada */
//        Consumer<Product> productConsumer = p -> p.setPrice( p.getPrice() * percent );
//        productList.forEach(productConsumer);

        /** FOMR 3 - Expressão lambda inline */
        productList.forEach ( p -> p.setPrice( p.getPrice() * percent ) );

        productList.forEach(System.out::println);
        return convertList(productList);
    }

    /**todo FUNCTION */
    public List<String> updateName ( ) {
        List<Product> productList = repository.findAll();

        /** FOMR 1 - implementação da Interface Function */
//        List<String> names =
//                productList  /**todo: converter a lista para stream // usar a funçao map // applicar a funcao */
//                        .stream()
//                        .map(new UpperCaseName())
//                .collect(Collectors.toList()); /** convert a stream novamente para lista */

        /** FORM 2 - Expressao lambda declarada */
//        Function<Product, String> productStringFunction =  p -> {
//            return p.getName().toUpperCase();
//        };
//
//        List<String> names =
//                productList
//                        .stream()
//                        .map( productStringFunction )
//                .collect(Collectors.toList());

        /** FOMR 3 - Expressão lambda inline */
        List<String> names =
                productList
                        .stream()
                        .map( p -> p.getName().toUpperCase())
                        .collect(Collectors.toList());


        names.forEach(System.out::println);
        return names;
    }

    public double sumPrice(Character initial){
        /** função que recebe uma função como parametro */

        List<Product> products = repository.findAll();
        double sum = filteredSum(products, p-> p.getName().charAt(0) == initial );
        return sum;
    }

    /** STREAM */
    public void streamFunction(){
        List<Integer> list = Arrays.asList(3, 4, 5, 10, 7);

        Stream<Integer> st1 = list.stream().map(n -> n++ );
        System.out.println(Arrays.toString(st1.toArray()));

        Stream<String> st2 = Stream.of("Maria", "Alex", "Bob").sorted();
        System.out.println(Arrays.toString(st2.toArray()));

        Stream<Integer> st3 = Stream.iterate(0, x -> x + 2);
        System.out.println(Arrays.toString(st3.limit(10).toArray()));
        Stream<Integer> st3A = Stream.iterate(10, x -> x + 2);
        System.out.println(Arrays.toString(st3A.limit(20).toArray()));

        Stream<Long> st4 = Stream.iterate(new long[]{ 0L, 1L }, p->new long[]{ p[1], p[0]+p[1] }).map(p -> p[0]);
        System.out.println(Arrays.toString(st4.limit(10).toArray()));
    }

    public Double reduce(){
        /** PIPELINE */

        List<Integer> list = Arrays.asList(3, 4, 5, 10, 7);
        Stream<Integer> st1 = list.stream().map(x -> x * 10);
        System.out.println(Arrays.toString(st1.toArray()));
        int sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println("Sum = " + sum);

        List<Integer> newList = list.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * 10)
                .collect(Collectors.toList());
        System.out.println(Arrays.toString(newList.toArray()));

        /** REST */
        List<Product> productList = repository.findAll();

//        List<Double> prices = productList.stream().map(
//                product -> product.getPrice()
//        ).collect(Collectors.toList());
//
//        Double priceAll = prices.stream()
//                .reduce(0.0, (a,b) -> a+b);

        Double priceAll = productList.stream()
                .map(p -> p.getPrice())
                .reduce(0.0, (a,b) -> a+b);
        return priceAll;
    }

    public /*List<ProductResponse>*/ List<Double> orderByPrice(Double val){
        List<Product> productList = repository.findAll();

        Comparator<Double> comparator = (s1,s2) -> s1.compareTo(s2);

        List<Double> values = productList
                .stream()
                .filter( p -> p.getPrice() > val)
                .map(p -> p.getPrice())
                //.sorted( (s1,s2) -> s1.compareTo(s2) ) /**todo ordem crecence */
                .sorted( comparator.reversed() ) /** ordem decrescente */
                .collect(Collectors.toList());

        return values;
        //return convertList(productList);
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
