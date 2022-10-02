package com.example.mywaregouse.service;

import com.example.mywaregouse.models.Product;
import com.example.mywaregouse.models.Storage;
import com.example.mywaregouse.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StorageService {
    @Autowired
    StorageRepository storageRepository;
    @Autowired
    ProductService productService;
    public Boolean createStorage(String name, MultipartFile file) throws IOException {
        try {
            List<Product>productList= productService.createProduct(file);
            Storage storage = new Storage();
            storage.setName(name);
            storage.setProductList(productList);
            storageRepository.save(storage);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
    public void deleteStorage(Long id){
        storageRepository.deleteById(id);
    }
    public Storage updateStorage(Long id,String name,MultipartFile file) throws IOException {
        List<Product>productList= productService.createProduct(file);
        Storage storage = new Storage(id,name,productList);
      return storageRepository.save(storage);
    }
}
