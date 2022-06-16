package com.example.programacao_funcional.comparator.sevice;

import com.example.programacao_funcional.comparator.model.Product;
import com.example.programacao_funcional.comparator.model.dto.ProductRequest;
import com.example.programacao_funcional.comparator.model.dto.ProductResponse;
import com.example.programacao_funcional.comparator.repository.ProductRepository;
import com.example.programacao_funcional.comparator.util.MyComparator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

    public List<ProductResponse> sort(){

        List<Product> productList = repository.findAll();

        /** utilizando a inteface Comparator para comparar objetos e ordena-los*/
        productList.sort(new MyComparator());

        //TODO - analisar se ordena , senao: criar ordenação para DTO

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


    /** METODOS AUXILIARES */
    private List<ProductResponse> convertList(List<Product> product){
        List<ProductResponse> productResponseSet = new ArrayList<>();

        product.forEach( p -> {

            productResponseSet.add(ProductResponse.fromModel(p));

        });

        return productResponseSet;
    }
}
