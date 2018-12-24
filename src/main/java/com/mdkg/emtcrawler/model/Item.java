package com.mdkg.emtcrawler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "item",fetch = FetchType.EAGER)
   public  List<Price> priceList = new ArrayList<>();

  public Item(){

  }
  public Item(String name, Double price, String photoLink,Category category) {
    this.name = name;
    this.price = price;
    this.category = category;
    this.photoLink=photoLink;
    this.date=LocalDate.now();


  }
  @Override
  public String toString(){
    return name + " " + price + " MKD " + category.toString();
  }

  public void addPrice(Price newPrice){
        priceList.add(newPrice);


  }
}


