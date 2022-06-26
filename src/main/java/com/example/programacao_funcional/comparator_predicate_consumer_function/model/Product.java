package com.example.programacao_funcional.comparator_predicate_consumer_function.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    public static Double PRICE = 0.0;

    public Product() {
    }

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    /**
     * TODO: Lógica de comparação para interface Comparable
     * public int compareTo(Product p){
     *     return name.toUpperCase().comparableTo(p.getName().toUpperCase());
     * }
     * */

    /***
     * TODO: Predicate com Metodo estatico
     */
    public static boolean staticProductPredicate(Product p){
        return p.getPrice() <= PRICE;
    }

    /***
     * TODO: Predicate com Metodo estatico
     */
    public boolean nonStaticProductPredicate(){
        return price <= PRICE;
    }

}
