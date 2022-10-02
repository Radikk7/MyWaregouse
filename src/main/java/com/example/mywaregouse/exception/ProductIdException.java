package com.example.mywaregouse.exception;

public class ProductIdException extends IllegalArgumentException{
    public ProductIdException(String id) {
        System.out.println("Invalid Id");
    }
    public ProductIdException(Long id) {
        System.out.println("Invalid Id");
    }

}
