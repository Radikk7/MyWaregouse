package com.example.mywaregouse.service;

import com.example.mywaregouse.models.Product;
import com.example.mywaregouse.models.Storage;
import com.example.mywaregouse.repository.ProductRepository;
import com.example.mywaregouse.repository.StorageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StorageServiceTest {
    @Autowired
    ProductService productService;
    @Autowired
    StorageRepository storageRepository;
    @Autowired
    ProductRepository productRepository;
    @Test
    void createStorage() throws IOException {
        File file = new File("file");
        byte[] fileContent = Files.readAllBytes(file.toPath());
        MultipartFile multipartFile = new MockMultipartFile("file",fileContent);
        multipartFile.transferTo(file);
        List<Product> productList=productService.createProduct(multipartFile);
        String name = "Cola";
        Storage storage = new Storage();

        storage.setProductList(productList);
        storage.setName(name);
        storageRepository.save(storage);
        Assertions.assertNotNull(storage);

    }

    @Test
    void deleteStorage() {
        Long id = 41L;
     Storage storage= storageRepository.findById(id).get();
     List<Product>productList= storage.getProductList();
     storage.getProductList().clear();
     storageRepository.save(storage);
        for (Product i: productList) {
          productRepository.deleteById(i.getId());
        }

       storage.setProductList(productList);
       storageRepository.deleteById(id);
    }

    @Test
    void updateStorage() {
    }
}