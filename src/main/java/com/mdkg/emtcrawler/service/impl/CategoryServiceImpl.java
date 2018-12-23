package com.mdkg.emtcrawler.service.impl;

import com.mdkg.emtcrawler.model.Category;
import com.mdkg.emtcrawler.repository.jpa.CategoryRepository;
import com.mdkg.emtcrawler.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository repository;
    @Override
    public void save(Category category) {
        repository.save(category);
    }


    @Override
    public Category findByName(String name) {
        return repository.findByName(name);
    }
}
