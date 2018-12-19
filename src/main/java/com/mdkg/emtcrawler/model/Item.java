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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int ID;
    @Column(length = 5000)
    public String name;
    public Double price;
    @ManyToOne(fetch = FetchType.EAGER)
    public Category category;

    public String photoLink;
    @Lob
    @JsonIgnore
    public byte[] photoData;

  public Item(){

  }
  public Item(String name, Double price, Category category) {
    this.name = name;
    this.price = price;
    this.category = category;
    this.photoData = null;
    this.photoLink="";
  }
  @Override
  public String toString(){
    return name + " " + price + " MKD " + category.toString();
  }
}
