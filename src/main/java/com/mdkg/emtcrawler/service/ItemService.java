package com.mdkg.emtcrawler.service;

import com.mdkg.emtcrawler.model.Category;
import com.mdkg.emtcrawler.model.Item;

import java.util.List;

public interface ItemService {

     void save(Item item);
     Item findById(int id);
     Item findByName(String name);
     List<Item> findByCategory(Category category);
     void buildDatabase();
     List<Item> findAll();



}
