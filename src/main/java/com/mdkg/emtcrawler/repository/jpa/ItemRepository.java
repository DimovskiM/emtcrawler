package com.mdkg.emtcrawler.repository.jpa;

import com.mdkg.emtcrawler.model.Category;
import com.mdkg.emtcrawler.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {

       Item findById(int ID);
       Item findByName(String name);
       List<Item> findAll();
       List<Item> findByCategoryName(String category);
}
