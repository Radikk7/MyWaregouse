package com.example.mywaregouse.exception;

public class StorageIdException extends IllegalArgumentException{
    public StorageIdException (Long id) {
        System.out.println("Invalid Id");
    }
}
