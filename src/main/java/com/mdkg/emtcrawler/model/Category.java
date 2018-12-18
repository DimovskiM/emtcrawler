package com.mdkg.emtcrawler.model;

import javax.persistence.*;

//@Entity
//@Table(name="category")
public class Category {
  //  @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int ID;
    public String name;

  public Category(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
