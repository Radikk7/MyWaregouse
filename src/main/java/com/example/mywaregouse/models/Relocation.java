package com.example.mywaregouse.models;

import com.example.mywaregouse.exception.ProductNameException;
import com.example.mywaregouse.exception.ProductNumberException;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Relocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int number;
    @OneToOne
    private Storage departureStorage;
    @OneToOne
    private Storage arrivalStorage;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product>productList;


    public Relocation(int number, Storage departureStorage, Storage arrivalStorage, List<Product> productList) {
        this.number = number;
        this.departureStorage = departureStorage;
        this.arrivalStorage = arrivalStorage;
        this.productList = productList;
    }

    //public Relocation(int number, String departureStorage, String arrivalStorage, List<Product> productList) {
    //    this.number = number;
    //    this.departureStorage = departureStorage;
    //    this.arrivalStorage = arrivalStorage;
    //    this.productList = productList;
    //}



    public Relocation() {

    }

    public int getValidName(String number){
      if (number.matches("\\d+")){
            throw new ProductNameException(number);
        }
        int value = Integer.parseInt(number);
        return value;
    }
    public Relocation(String number, Storage departureStorage, Storage arrivalStorage, List<Product> productList) {
        this.number = getValidName(number);
        this.departureStorage = departureStorage;
        this.arrivalStorage = arrivalStorage;
        this.productList = productList;
    }






    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Storage getDepartureStorage() {
        return departureStorage;
    }

    public void setDepartureStorage(Storage departureStorage) {
        this.departureStorage = departureStorage;
    }

    public Storage getArrivalStorage() {
        return arrivalStorage;
    }

    public void setArrivalStorage(Storage arrivalStorage) {
        this.arrivalStorage = arrivalStorage;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
