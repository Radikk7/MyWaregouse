package com.example.mywaregouse.repository;

import com.example.mywaregouse.models.Product;
import com.example.mywaregouse.models.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage,Long> {
    boolean existsById(long id);
    Storage findByProductListContains(Product product);

}
