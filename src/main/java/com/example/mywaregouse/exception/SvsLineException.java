package com.example.mywaregouse.exception;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class SvsLineException extends IllegalArgumentException{
    public SvsLineException(int size){
        System.out.println("In correct line." + "It should be = 4!");
    }
    public SvsLineException(MultipartFile file){
        System.out.println("In correct line." + "It should be = 4!");
    }
}
