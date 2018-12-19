package com.mdkg.emtcrawler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
