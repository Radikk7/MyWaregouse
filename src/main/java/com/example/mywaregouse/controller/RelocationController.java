package com.example.mywaregouse.controller;

import com.example.mywaregouse.models.*;
import com.example.mywaregouse.service.RelocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.*;
import java.util.List;

@RestController
public class RelocationController {
    @Autowired
    RelocationService relocationService;
    @PostMapping("/arrivalProducts")// отправка
    public ResponseEntity arrivalProducts(@RequestParam(name = "idStorage") Long idStorage,@RequestParam(name = "file")MultipartFile file,
                                @RequestParam(name ="type" ) Type type) throws IOException {
        if (idStorage == null || file == null || type == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
       Document document= relocationService.arrivalProducts(idStorage,file,type);
       if (document==null){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/saleProducts/{id}")// продажа
    public ResponseEntity saleProducts(@PathVariable(name = "id")Long id,@RequestParam(name = "file")MultipartFile file) throws IOException {
        if (id == null ||  file == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
       Document document= relocationService.saleProduct(id,file);
        if (document==null){
            return new ResponseEntity<Document>(document, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/movingProducts")// перемещение
    public ResponseEntity movingProducts(@RequestParam (name = "idStorage1")Long idStorage1, @RequestParam(name = "idStorage2")Long idStorage2,
                                         @RequestParam(name = "file")MultipartFile file) throws IOException {
             Relocation relocation = relocationService.movingProducts(idStorage1,idStorage2,file);
             if (idStorage1 == null || idStorage2 ==null || file== null){
                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
             }
              if (relocation==null){
                 // System.out.println(relocation +);
               return new ResponseEntity<>(HttpStatus.NO_CONTENT);
              }
              return new ResponseEntity<>(HttpStatus.OK);
    }
}
