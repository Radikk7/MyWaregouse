package com.example.mywaregouse.exception;

public class ProductPriceException extends IllegalArgumentException{
    public ProductPriceException(String price){
        System.out.println("Invalid price");
    }
}
