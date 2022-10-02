package com.example.mywaregouse.controller;

import com.example.mywaregouse.models.Storage;
import com.example.mywaregouse.service.ProductService;
import com.example.mywaregouse.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class StorageController {
   // @Autowired
   // ProductService productService;
    @Autowired
    StorageService storageService;
    @PostMapping("/addStorage")
    public ResponseEntity addStorage(@RequestParam(name = "name") String name, @RequestParam MultipartFile file) throws IOException {
          if (storageService.createStorage(name,file)){
              return new  ResponseEntity(HttpStatus.OK);
          }
              return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/deleteStorage/{id}")
    public ResponseEntity deleteStorage(@PathVariable (name = "id")Long id){
        try {
            storageService.deleteStorage(id);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateStorage/{id}")
    public ResponseEntity updateStorage(@PathVariable (name = "id")Long id,@RequestParam (name = "name")String name,MultipartFile file){
        {
            try {
                return new ResponseEntity(storageService.updateStorage(id,name,file),HttpStatus.OK);
            }
            catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
