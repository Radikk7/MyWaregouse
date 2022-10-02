package com.example.mywaregouse.exception;

import java.math.BigDecimal;

public class PriceException extends IllegalArgumentException{
    public PriceException(BigDecimal price) {
        System.out.println("Invalid Price");
    }
}
