package com.mdkg.emtcrawler.web;

import com.mdkg.emtcrawler.parser.MockParser;
import com.mdkg.emtcrawler.parser.Parser;
import com.mdkg.emtcrawler.repository.mock.RepositoryMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class TestController {
    @Autowired
    private MockParser parser;
    @Autowired
    private RepositoryMock repository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getItems(){
        ModelAndView modelAndView= new ModelAndView("index.html");
        parser.buildDatabase();
        repository.listByCategory();
        return modelAndView;
    }

}
