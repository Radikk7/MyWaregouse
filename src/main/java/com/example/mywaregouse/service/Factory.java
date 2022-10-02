package com.example.mywaregouse.service;

import com.example.mywaregouse.exception.ProductPriceException;
import com.example.mywaregouse.exception.SvsLineException;
import com.example.mywaregouse.models.Product;
import org.springframework.stereotype.Service;

@Service
public class Factory {
    public static Product productException(String value){
        String [] array= value.split(";");
        try {
            if (array.length==4){
                return new Product(array[0],array[1],array[2],array[3]);
            }
            else {
                throw new SvsLineException(array.length);
            }
        }
        catch (SvsLineException | ProductPriceException e ){
            throw new SvsLineException(array.length);
        }
    }
}
