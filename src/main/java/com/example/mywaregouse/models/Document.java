package com.example.mywaregouse.models;

import com.example.mywaregouse.exception.ProductNumberException;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Storage storage;
    private int number;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Product>productList;
    private int countOfProduct;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Document(long id, Storage storage, int number, List<Product> productList, int countOfProduct) {
        this.id = id;
        this.storage = storage;
        this.number = number;
        this.productList = productList;
        this.countOfProduct = countOfProduct;
    }

    public Document() {
    }

    public int getValue(int number){

        if (!(number < 0)){
            throw new ProductNumberException(number);
        }
        return number;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getCountOfProduct() {
        return countOfProduct;
    }

    public void setCountOfProduct(int countOfProduct) {
        this.countOfProduct = countOfProduct;
    }
}
