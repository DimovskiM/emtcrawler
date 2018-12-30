package com.mdkg.emtcrawler.web;

import com.mdkg.emtcrawler.model.Item;
import com.mdkg.emtcrawler.parser.Parser;
import com.mdkg.emtcrawler.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/item")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {
    @Autowired
    ItemService itemService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getAllItems(){
        return itemService.findAll();
    }

    @RequestMapping(value = "/db" , method = RequestMethod.GET)
    public ModelAndView buildDatabase(){
        itemService.buildDatabase();
       return new ModelAndView("index.html");
    }



}
