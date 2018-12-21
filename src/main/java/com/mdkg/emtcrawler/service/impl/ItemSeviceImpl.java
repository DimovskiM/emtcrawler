package com.mdkg.emtcrawler.service.impl;

import com.mdkg.emtcrawler.model.Category;
import com.mdkg.emtcrawler.model.Item;
import com.mdkg.emtcrawler.parser.MockParser;
import com.mdkg.emtcrawler.repository.jpa.ItemRepository;
import com.mdkg.emtcrawler.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemSeviceImpl implements ItemService {
    @Autowired
    ItemRepository repository;
    @Autowired
    MockParser parser;
    @Override
    public void save(Item item) {
        repository.save(item);
    }

    @Override
    public Item findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Item findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Item> findByCategory(String category) {
        return repository.findByCategoryName(category);

    }
    @Override
    public void buildDatabase(){
        parser.buildDatabase();
    }

    @Override
    public List<Item> findAll(){
        return repository.findAll();
    }


}