package com.example.programacao_funcional.comparator_predicate_consumer_function.controller;

import com.example.programacao_funcional.comparator_predicate_consumer_function.model.dto.ProductRequest;
import com.example.programacao_funcional.comparator_predicate_consumer_function.model.dto.ProductResponse;
import com.example.programacao_funcional.comparator_predicate_consumer_function.sevice.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse add(@RequestBody ProductRequest request){
        return service.addProduct(request);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/allSorted")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> findAllSorted(){
        return service.sortByName();
    }

    @GetMapping("/allSorted/price")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> findAllSortedByPrice(){
        return service.sortByPrice();
    }

    @GetMapping("/allSorted/price/callback")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> findAllSortedByPriceCallBackMehod(){
        return service.sortByPriceArgument();
    }

    @DeleteMapping("/removeAllBy/{price}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public List<ProductResponse> removeIf(@PathVariable Double price){
       return service.removeIf( Double.valueOf(price));
    }

    @PutMapping("/updatePrice/{percent}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> updatePrice(@PathVariable Double percent){
        return service.updatePrice(percent);
    }

    @PutMapping("/updateName")
    @ResponseStatus(HttpStatus.OK)
    public List<String> updateName(){
        return service.updateName();
    }

    @GetMapping("/sumPrice/{letra}")
    @ResponseStatus(HttpStatus.OK)
    public double sumByCharAt(@PathVariable Character letra){
        return service.sumPrice(letra);
    }

    @GetMapping("/stream/consoleLog")
    @ResponseStatus(HttpStatus.OK)
    public void stream(){
        service.streamFunction();
    }

    @GetMapping("/stream/priceAll")
    @ResponseStatus(HttpStatus.OK)
    public Double sumAllPrices(){
        return service.reduce();
    }

    @GetMapping("stream/orderByPrice/{init}")
    @ResponseStatus(HttpStatus.OK)
    public List<Double> orderByPrice(@PathVariable Double init) {
        return service.orderByPrice(init);
    }


    }
