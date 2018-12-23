package com.mdkg.emtcrawler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
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

  public void addPrice(Double price, LocalDate date){
  }
}


