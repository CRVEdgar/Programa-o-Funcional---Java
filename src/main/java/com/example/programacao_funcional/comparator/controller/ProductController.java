package com.example.programacao_funcional.comparator.controller;

import com.example.programacao_funcional.comparator.model.dto.ProductRequest;
import com.example.programacao_funcional.comparator.model.dto.ProductResponse;
import com.example.programacao_funcional.comparator.sevice.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ProductResponse findById(Long id){
        return service.findById(id);
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
        return service.sort();
    }
}
