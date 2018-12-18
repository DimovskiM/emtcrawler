package com.mdkg.emtcrawler.service;

import com.mdkg.emtcrawler.model.Category;

public interface CategoryService {

    void save(Category category);
    Category findById(int id);
    Category findByName(String name);

}
