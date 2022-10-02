package com.example.mywaregouse.exception;

public class ProductNumberException extends IllegalArgumentException{
    public ProductNumberException(int n){
        System.out.println("Invalid Product number");
    }
}
