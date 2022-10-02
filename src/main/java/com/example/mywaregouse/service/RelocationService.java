package com.example.mywaregouse.service;

import com.example.mywaregouse.exception.ProductIdException;
import com.example.mywaregouse.exception.StorageIdException;
import com.example.mywaregouse.models.*;
import com.example.mywaregouse.repository.DocumentRepository;
import com.example.mywaregouse.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class RelocationService {
    @Autowired
    StorageRepository storageRepository;
    @Autowired
    ProductService productService;
    @Autowired
    DocumentRepository documentRepository;
    public Document arrivalProducts(Long idStorage, MultipartFile file, Type type) throws IOException {
       try {
           if (!storageRepository.existsById(idStorage)){
            throw new StorageIdException(idStorage);
           }
        if (type == null){
            return null;
        }
        Document document = new Document();
        Storage storage= storageRepository.findById(idStorage).get();
        List<Product> productList= productService.createProduct(file);
        if (productList.size() ==0){
            return null;
        }
        document.setNumber(((int)(Math.random()*100)));
        document.setCountOfProduct(productList.size());
        document.setNumber(randomNumber());
        storage.setProductList(productList);
        document.setStorage(storage);
        document.setProductList(productList);
        document.setType(type);
       return documentRepository.save(document);
       }
       catch (IllegalArgumentException e){
           System.out.println(e);
           return null;
       }

    }
    public int randomNumber(){
        int random = (int)(Math.random() * 1000);
      if (documentRepository.existsByNumber(random)){
          random = randomNumber();
      }
      return random;
    }
    public Document saleProduct(Long idStorage,MultipartFile file) throws IOException {
        try {
            if (idStorage < 0) {
                throw new StorageIdException(idStorage);
            }
            else {
                Document document = new Document();
                Storage storage= storageRepository.findById(idStorage).get();
                document.setNumber((int)(Math.random()*100));
                List<Product>productList= productService.products(file);
                for (Product i:productList) {
                    System.out.println(i);
                }
                if (productList.size() == 0){
                    return null;
                }
                document.setCountOfProduct(productList.size());
                document.setNumber(randomNumber());
                List<Product>products= storage.getProductList();
                for (int i = 0; i < productList.size(); i++){
                    products.remove(productList.get(i));
                }
                storage.setProductList(products);
                storageRepository.save(storage);
                document.setStorage(storage);
                document.setProductList(productList);
                documentRepository.save(document);
                return document;
            }
        }
     catch (IllegalArgumentException e){
         System.out.println(e);
         return null;
     }
    }


    public Relocation movingProducts(Long idStorage1, Long idStorage2, MultipartFile file) throws IOException {
      try {
          if (!storageRepository.existsById(idStorage1)  || !storageRepository.existsById(idStorage2)){
              throw new IllegalArgumentException();
          }
          List<Product>productList= productService.products(file);
          if (productList.size() == 0){
              return null;
          }
          Storage storage1= storageRepository.findById(idStorage1).get();
          Storage storage2= storageRepository.findById(idStorage2).get();
          List<Product>productListStorage1= storage1.getProductList();
          List<Product>productListStorage2= storage2.getProductList();
          List<Product>addProducts = new ArrayList<>();
          for (int i =0; i <productList.size();i++){
              productListStorage1.remove(productList.get(i));
          }
          productListStorage2.addAll(productList);
          storage1.setProductList(productListStorage1);
          storage2.setProductList(productListStorage2);
          storageRepository.save(storage1);
          storageRepository.save(storage2);
          Relocation relocation = new Relocation();
          relocation.setNumber((int)(Math.random()*100));
          relocation.setDepartureStorage(storage1);
          relocation.setArrivalStorage(storage2);
          return relocation;
      }
        catch (IllegalArgumentException e){
            System.out.println(e);
            return null;
        }
    }

}
