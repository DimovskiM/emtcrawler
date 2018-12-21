package com.mdkg.emtcrawler.repository.jpa;

import com.mdkg.emtcrawler.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category findByName(String name);
}
