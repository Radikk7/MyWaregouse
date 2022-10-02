package com.example.mywaregouse.exception;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class FileException extends IllegalArgumentException{
    public FileException (MultipartFile file) {
        System.out.println("Invalid File");
    }
}
