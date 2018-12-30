package com.mdkg.emtcrawler.web;

import com.mdkg.emtcrawler.model.Category;
import com.mdkg.emtcrawler.repository.jpa.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepository repository;

    @RequestMapping("/all")
    public ModelAndView getAllCategories(){
        ModelAndView modelAndView = new ModelAndView("index.html");
        List<Category> categoryList = repository.findAll();
        modelAndView.addObject("categories",categoryList);
        return modelAndView;
    }
}
