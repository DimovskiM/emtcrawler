package com.mdkg.emtcrawler.web;

import com.mdkg.emtcrawler.model.Item;
import com.mdkg.emtcrawler.parser.MockParser;
import com.mdkg.emtcrawler.parser.Parser;
import com.mdkg.emtcrawler.repository.mock.RepositoryMock;
import com.mdkg.emtcrawler.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/item")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {
    @Autowired
    ItemService itemService;
    @Autowired
    MockParser parser;
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getAllItems(){

        parser.buildDatabase();
        return itemService.findAll();
    }

    @RequestMapping("/item/{name}")
    public ModelAndView getItemByName(@PathVariable("name") String name){
        Item item = itemService.findByName(name);
        ModelAndView modelAndView= new ModelAndView("index.html");
        modelAndView.addObject("item",item);
        return modelAndView;
    }



}
