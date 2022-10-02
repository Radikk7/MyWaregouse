package com.example.mywaregouse.service;

import com.example.mywaregouse.models.Document;
import com.example.mywaregouse.models.Product;
import com.example.mywaregouse.models.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.parameters.P;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RelocationServiceTest {
        @Autowired
        RelocationService relocationService;
        @Autowired
        ProductService productService;
    @Test
    void arrivalProducts() throws IOException {

        Document document =  relocationService.arrivalProducts(14L,generateMultipartFile(),Type.Admission);
    }

    @Test
    void randomNumber() {
      Integer a =  relocationService.randomNumber();
        Assertions.assertNotNull(a);
        Assertions.assertTrue(a instanceof Integer);// a образованно от класса Integer
    }

    @Test
    void saleProduct() throws IOException {
      List<Product>productList= productService.products(generateMultipartFileId());
       for (Product i:productList) {
           System.out.println(i);
       }
          MultipartFile file= generateMultipartFileId();
        String content = new String(file.getBytes());
        System.out.println(content);
        Document document= relocationService.saleProduct(14L,generateMultipartFileId());
        Assertions.assertNotNull(document);


    }

    @Test
    void movingProducts() {
    }

    public MultipartFile generateMultipartFile() throws IOException {
        File file = new File("file");
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return new MockMultipartFile("file", fileContent);
    }

    public MultipartFile generateMultipartFileId() throws IOException {
        File file = new File("OnlyId.txt");
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return new MockMultipartFile("OnlyId.txt", fileContent);
    }

}