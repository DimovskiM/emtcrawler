package com.mdkg.emtcrawler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="category")
public class Category {

    @Id
    public String name;

    public Category(){

   }
  public Category(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if (!(obj instanceof  Category )) return false;

        Category that = (Category) obj;
        return Objects.equals(this.name,that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
