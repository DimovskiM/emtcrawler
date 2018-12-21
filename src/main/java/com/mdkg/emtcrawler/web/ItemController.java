package com.mdkg.emtcrawler.web;

import com.mdkg.emtcrawler.model.Item;
import com.mdkg.emtcrawler.parser.MockParser;
import com.mdkg.emtcrawler.parser.Parser;
import com.mdkg.emtcrawler.repository.mock.RepositoryMock;
import com.mdkg.emtcrawler.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;
    @Autowired
    MockParser parser;
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllItems(){
        ModelAndView modelAndView= new ModelAndView("index.html");


         modelAndView.addObject("items",itemService.findAll()) ;
         return modelAndView;
    }

    @RequestMapping("/item/{name}")
    public ModelAndView getItemByName(@PathVariable("name") String name){
        Item item = itemService.findByName(name);
        ModelAndView modelAndView= new ModelAndView("index.html");
        modelAndView.addObject("item",item);
        return modelAndView;
    }



}
