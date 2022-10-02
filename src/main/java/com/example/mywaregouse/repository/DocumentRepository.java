package com.example.mywaregouse.repository;

import com.example.mywaregouse.models.Document;
import com.example.mywaregouse.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {
    boolean existsByNumber(int number);
    Document findByProductListContains(Product product);
}
