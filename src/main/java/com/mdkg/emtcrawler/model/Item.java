package com.mdkg.emtcrawler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
//@Entity
//@Table(name="item")
public class Item {
  //  @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int ID;
    public String name;
    public Double price;
    //@ManyToOne
    public Category category;

    //@Lob
    //@JsonIgnore
    public byte[] photoData;

    public HashMap<LocalDate,Integer> priceList;

  public Item(String name, Double price, Category category) {
    this.name = name;
    this.price = price;
    this.category = category;
  }
  @Override
  public String toString(){
    return name + " " + price + " MKD " + category.toString();
  }
}
