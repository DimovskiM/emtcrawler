package com.mdkg.emtcrawler.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name="item")
public class Item {

    @Id
    public String name;

    public Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    public Category category;

    public LocalDate date;
    public String photoLink;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="price_id")
    public  List<Price> priceList = new ArrayList<>();

    public Item(){ }

    public Item(String name, Double price, String photoLink,Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.photoLink=photoLink;
        this.date=LocalDate.now();
    }

    public void addPrice(Price newPrice,Price oldPrice){
        priceList.add(oldPrice);
        this.price = newPrice.price;
        this.date = LocalDate.now();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if (!(obj instanceof  Price )) return false;

        Price that = (Price) obj;
        return Objects.equals(this.price,that.price) && Objects.equals(this.date,that.date
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name,this.date,this.price);
    }

    @Override
    public String toString(){
        return name + " " + price + " MKD " + category.toString();
    }
}
