package com.example.mywaregouse.exception;

public class ProductNameException extends IllegalArgumentException {

    public ProductNameException(String s) {
        System.out.println("Invalid name");
    }

}
