package com.example.mywaregouse.exception;

import com.example.mywaregouse.models.Product;
import org.springframework.web.multipart.MultipartFile;

public class ProductException extends IllegalArgumentException{
    public ProductException (Product product) {
        System.out.println("Invalid product");
    }
}
