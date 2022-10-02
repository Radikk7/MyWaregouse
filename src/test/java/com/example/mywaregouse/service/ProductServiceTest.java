package com.example.mywaregouse.service;

import com.example.mywaregouse.models.Product;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductServiceTest {
        @Autowired
        ProductService productService;
    @Test
    void createProduct() throws IOException {
        File file = new File("file");
        byte[] fileContent = Files.readAllBytes(file.toPath());
    MultipartFile multipartFile = new MockMultipartFile("file",fileContent);

        multipartFile.transferTo(file);
     List<Product> productList=productService.createProduct(multipartFile);
        Assertions.assertNotNull(productList);
    }

    @Test
    void products() throws IOException {
            File file = new File("file");
            byte[] fileContent = Files.readAllBytes(file.toPath());
            MultipartFile multipartFile = new MockMultipartFile("file", fileContent);
            List<Product> productList = productService.products(multipartFile);
        for (Product i:productList) {
            System.out.println(i);
        }
            Assertions.assertNotNull(productList);//проверка на null

    }

    @Test
    void readFile() throws IOException {
        File file = new File("file");
        byte[] fileContent = Files.readAllBytes(file.toPath());
        MultipartFile multipartFile = new MockMultipartFile("file",fileContent);

        multipartFile.transferTo(file);
       BufferedReader bufferedReader= productService.readFile(multipartFile);
       Assertions.assertNotNull(bufferedReader);
    }

    @Test
    void addProduct() {
        Product product = new Product("Ary1123","BMW","950.00","1000.00");
        Product product1= productService.addProduct(product);
        Assertions.assertNotNull(product1);
        Assertions.assertEquals(product,product1);
    }

    @Test
    void deleteProduct() {

        productService.deleteProduct(4L);

    }

    @Test
    void updateProduct() {
        Product product = new Product("Ary11222333","Mercedes","9500.00","10000.00");
        Product product1= productService.updateProduct(product);
        Assertions.assertNotNull(product1);
        Assertions.assertEquals(product,product1);
    }
}