package com.mdkg.emtcrawler.model;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Price implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int Id;

    public Double price;
    public LocalDate date;

    public Price(){}

    public Price(Double price, LocalDate date) {
        this.price = price;
        this.date = date;
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
        return Objects.hash(this.price,this.date);
    }
}
