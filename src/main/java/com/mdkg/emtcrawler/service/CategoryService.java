package com.mdkg.emtcrawler.service;

import com.mdkg.emtcrawler.model.Category;

import java.util.Optional;

public interface CategoryService {

    void save(Category category);
    Category findByName(String name);

}
