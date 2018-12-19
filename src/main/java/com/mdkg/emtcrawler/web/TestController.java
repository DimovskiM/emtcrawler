package com.mdkg.emtcrawler.web;

import com.mdkg.emtcrawler.model.Item;
import com.mdkg.emtcrawler.parser.MockParser;
import com.mdkg.emtcrawler.parser.Parser;
import com.mdkg.emtcrawler.repository.mock.RepositoryMock;
import com.mdkg.emtcrawler.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class TestController {
    @Autowired
    ItemService itemService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getItems(){
        ModelAndView modelAndView= new ModelAndView("index.html");
       itemService.buildDatabase();

        List<Item>  itemList = itemService.findAll();
        itemList.stream().forEach(item -> System.out.println(item));
        return modelAndView;
    }

}
