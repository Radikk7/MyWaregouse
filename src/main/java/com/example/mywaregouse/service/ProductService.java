package com.example.mywaregouse.service;

import com.example.mywaregouse.exception.*;
import com.example.mywaregouse.models.Document;
import com.example.mywaregouse.models.Product;
import com.example.mywaregouse.models.Storage;
import com.example.mywaregouse.repository.DocumentRepository;
import com.example.mywaregouse.repository.ProductRepository;
import com.example.mywaregouse.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    StorageRepository storageRepository;
    @Autowired
    DocumentRepository documentRepository;

    public List<Product> createProduct(MultipartFile file) throws IOException {
        try {
            BufferedReader bufferedReader = readFile(file);
            String line = "";
            List<Product> productList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split(";");
                Product product = Factory.productException(line);
                productRepository.save(product);
                productList.add(product);
            }
            return productList;
        } catch (SvsLineException | FileException  e){
            throw  new SvsLineException(file);
        }
    }

    public List<Product> products(MultipartFile file) throws IOException {
        try {
            BufferedReader bufferedReader = readFile(file);
            String line = "";
            List<Product> productList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    if (!line.matches("^[1-9]\\d*$")) {
                        throw new ProductIdException(line);
                    } else {
                        Long id = Long.parseLong(line);
                        if (productRepository.existsById(id)){
                            Product product = productRepository.findById(id).get();
                            productList.add(product);
                        }

                    }
                } catch (ProductIdException e) {
                    System.out.println("An error occurred");
                }
            }
            return productList;
        } catch (FileException e) {
            System.out.println(e);
            return null;
        }
    }

    public BufferedReader readFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new FileException(file);
        }
        File convFile = new File(file.getName());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(convFile));
        return bufferedReader;
    }


    public Product addProduct(Product product) {
        try {
            if (product == null) {
                return null;
            }
            return productRepository.save(product);
        } catch (ProductException e) {
            System.out.println(1);
            return null;
        }
    }
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).get();
        Storage storage = storageRepository.findByProductListContains(product);
        Document document = documentRepository.findByProductListContains(product);
        List<Product> products = document.getProductList();
        products.remove(product);
        document.setProductList(products);
        documentRepository.save(document);
        List<Product> productList = storage.getProductList();
        productList.remove(product);
        storage.setProductList(productList);
        storageRepository.save(storage);
        productRepository.deleteById(id);
    }

    public Product updateProduct(Product product) {
        try {
            if (product == null) {
                return null;
            }
            return productRepository.save(product);
        } catch (ProductException e) {
            System.out.println(1);
            return null;
        }
    }

}
