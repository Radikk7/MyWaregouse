package com.example.mywaregouse.models;

import com.example.mywaregouse.exception.ProductNameException;

import javax.persistence.*;
import java.util.List;
// сделать такую же тему со считыванием файла как и в продукте
// пробросисть все через трай кетч чтобы не выбивало ошибок
@Entity
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Product> productList;

    public Storage(Long id, String name, List<Product> productList) {
        this.id = id;
        this.name = getValidName(name);
        this.productList = productList;
    }

    public Storage(String name, List<Product> productList) {
        this.name = name;
        this.productList = productList;
    }

    public Storage() {
    }
    public String getValidName(String name){
        if (!name.matches("^[a-zA-Z]*$")){
            throw new ProductNameException(name);
        }
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
