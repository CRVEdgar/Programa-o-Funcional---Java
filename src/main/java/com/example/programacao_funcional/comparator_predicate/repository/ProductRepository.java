package com.example.programacao_funcional.comparator_predicate.repository;

import com.example.programacao_funcional.comparator_predicate.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
