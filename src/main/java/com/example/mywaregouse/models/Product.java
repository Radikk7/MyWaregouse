package com.example.mywaregouse.models;

import com.example.mywaregouse.exception.PriceException;
import com.example.mywaregouse.exception.ProductIdException;
import com.example.mywaregouse.exception.ProductNameException;
import com.example.mywaregouse.exception.ProductPriceException;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @NotNull
        private Long id;
        @NotNull
        private String vendorCode;
        @NotNull
        private String name;
        @DecimalMin(value = "0.0", inclusive = false)
        @Digits(integer=7, fraction=2)
        @NotNull
        private BigDecimal priceLastPurchases;
        @DecimalMin(value = "0.0", inclusive = false)
        @Digits(integer=7, fraction=2)
        @NotNull
        private BigDecimal priceLastSale;


        public Product(long id, String vendorCode, String name, BigDecimal priceLastPurchases, BigDecimal priceLastSale) {
                this.id = id;
                this.vendorCode = getValidCode(vendorCode);
                this.name = getValidName(name);
                this.priceLastPurchases = priceLastPurchases;
                this.priceLastSale = priceLastSale;
        }

        public Product(String vendorCode, String name, BigDecimal priceLastPurchases, BigDecimal priceLastSale) {
                this.vendorCode = getValidCode(vendorCode);
                this.name = getValidName(name);
                this.priceLastPurchases = priceLastPurchases;
                this.priceLastSale = priceLastSale;
        }




        public Product( String vendorCode, String name, String priceLastPurchases, String priceLastSale) {
                this.vendorCode = getValidCode(vendorCode);
                this.name = getValidName(name);
                this.priceLastPurchases = converter(priceLastPurchases);
                this.priceLastSale = converter(priceLastSale);
        }



        public String getValidCode(String vendorCode){
                if (!vendorCode.matches("^[a-zA-Z0-9_.-]*$")){
                        throw new ProductNameException(vendorCode);
                }
                return vendorCode;
        }
        public String getValidName(String name){
                if (!name.matches("^[a-zA-Z]*$")){
                        throw new ProductNameException(name);
                }
                return name;
        }
        public BigDecimal converter(String price){
                if (!price.matches("\\d+\\.\\d\\d")){
                        throw new ProductPriceException(price);
                }
                return new BigDecimal(price);
        }



        public Product() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                if (id ==null){
                        throw new ProductIdException(id);
                }
                this.id = id;
        }

        public String getVendorCode() {
                return vendorCode;
        }

        public void setVendorCode(String vendorCode) {
                this.vendorCode = getValidCode(vendorCode);
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name =getValidName(name);
        }

        public BigDecimal getPriceLastPurchases() {

                return priceLastPurchases;
        }

        public void setPriceLastPurchases(BigDecimal priceLastPurchases) {
                if (priceLastPurchases ==null){
                        throw new PriceException(priceLastPurchases);
                }
                this.priceLastPurchases = priceLastPurchases;
        }

        public BigDecimal getPriceLastSale() {
                return priceLastSale;
        }

        public void setPriceLastSale(BigDecimal priceLastSale) {
                if (priceLastSale==null){
                        throw new PriceException(priceLastSale);
                }
                this.priceLastSale = priceLastSale;
        }


        public String toString(){
                return getVendorCode() + " " + getName() + " " + getPriceLastPurchases() +" " + getPriceLastSale();
        }
}
