package com.mdkg.emtcrawler.repository.jpa;

import com.mdkg.emtcrawler.model.Category;
import com.mdkg.emtcrawler.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {

       Item findByName(String name);
       List<Item> findAll();
       @Query("select i from Item i where category_name=:category")
       List<Item> findByCategoryName(@Param("category")String category);
}
