package com.example.mywaregouse.controller;

import com.example.mywaregouse.models.Product;
import com.example.mywaregouse.repository.ProductRepository;
import com.example.mywaregouse.service.Factory;
import com.example.mywaregouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;


@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @PostMapping("/initProduct")
    public ResponseEntity<Integer> initProduct(@RequestParam("file")MultipartFile file) throws IOException {
        if (file.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (productService.createProduct(file) == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
      return  new ResponseEntity<>( productService.createProduct(file).size(),HttpStatus.OK);
    }
    @PostMapping("/addproduct")
    public ResponseEntity<Product> addOneProduct(@Valid @RequestBody Product product){//@ //@AuthenticationPrincipal
       // if (product.getId()==null || product.getName() ==null || product.getVendorCode() ==null ||
       //         product.getPriceLastSale() == null || product.getPriceLastPurchases() == null  ){
        //    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       // }
       try {
          return new ResponseEntity<>(productService.addProduct(product),HttpStatus.OK);
       }
       catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }
    @GetMapping("/deleteProduct/{id}")
    public ResponseEntity deleteProduct(@PathVariable(name = "id")Long id){// нужно удалить продукты сначала со складов , а потом уже смогу удалить продукт
        try {
            if (id==null || !productRepository.existsById(id)){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
        catch (Exception e){
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity updateProduct(@RequestBody Product product,@PathVariable(name = "id")Long id){
        try {
            if (id ==null || !productRepository.existsById(id)){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            product.setId(id);
         return new ResponseEntity(productService.updateProduct(product),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
