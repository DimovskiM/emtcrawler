package com.mdkg.emtcrawler.parser;

import com.mdkg.emtcrawler.model.Category;
import com.mdkg.emtcrawler.model.Item;
import com.mdkg.emtcrawler.repository.jpa.CategoryRepository;
import com.mdkg.emtcrawler.repository.jpa.ItemRepository;
import com.mdkg.emtcrawler.repository.mock.RepositoryMock;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MockParser {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CategoryRepository categoryRepository;

    Document document;
    static String MARKET_WEBSITE = "http//marketonline.mk";
    static String GRICKI_SOLENKI_SMOKI = "page0.html";
    static String MLECHNI_PROIZVODI = "page1.html";
    static String SIRENJE = "page2.html";
    static String MLEKO_JOGURT_KISELO_MLEKO = "page3.html";
    static String LEB = "page4.html";
    static String PIVO = "page5.html";
    static String JAJCA = "page6.html";
    static String SVEZH_ZELENCHUK = "page7.html";


    static String LAPTOP_STORE_WEBSITE = "http://setec.mk";
    static String SETEC_PAGE_ONE = "http://setec.mk/index.php?route=product/category&path=10003&limit=100&";
    static String SETECT_PAGE_TWO = "http://setec.mk/index.php?route=product/category&path=10003&limit=100&&page=2";



    List<String> foodList;
    List<String> laptopList;


    public MockParser() {

       foodList = new ArrayList<>();
       foodList.add(GRICKI_SOLENKI_SMOKI);
       foodList.add(MLECHNI_PROIZVODI);
       foodList.add(SIRENJE);
       foodList.add(MLEKO_JOGURT_KISELO_MLEKO);
       foodList.add(LEB);
       foodList.add(PIVO);
       foodList.add(JAJCA);
       foodList.add(SVEZH_ZELENCHUK);
       laptopList.add(SETEC_PAGE_ONE);
       laptopList.add(SETECT_PAGE_TWO);


    }

    public void getFoodItems(Document document) {

        StringBuilder sb = new StringBuilder();
        Category cat = document.getAllElements()
                .stream()
                .filter(e -> e.hasClass("active"))
                .map(element -> {
                    Category category = new Category(element.getElementsByClass("active").text());
                    return category;
                }).findFirst().get();
        categoryRepository.save(cat);

        List<Item> itemList = document.getAllElements()
                .stream()
                .filter(e -> e.hasClass("thumbnail")).map(element -> {
                    String priceString = element.getElementsByClass("priceCurrent").text();
                    String itemName = element.getElementsByClass("image-thumb").attr("title");
                    String itemUrl = element.getElementsByClass("image-thumb").attr("src");
                    Double price = priceString.length() > 0 ? Double.parseDouble(priceString.split(" ")[1]) : 0;

                        Item item = price>0 ? new Item(itemName, price,itemUrl, cat):null;
                        return item;
                }).collect(Collectors.toList());

        saveParsedData(itemList);

    }

    public void buildDatabase() {
        foodList.stream().forEach(website -> {
            File file = new File(website);
            Document doc;
            try {
                doc = Jsoup.parse(file, "UTF-8", MARKET_WEBSITE);
                getFoodItems(doc);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        laptopList.stream().forEach(website->{
            File file = new File(website);
            Document doc;
            try{
                Category category=new Category("Лаптопи");
                categoryRepository.save(category);
                doc=Jsoup.parse(file,"UTF-8",LAPTOP_STORE_WEBSITE);
                getLaptopItems(doc,category);
            } catch (IOException e){
                e.printStackTrace();
            }
        });


    }

    public void getLaptopItems(Document document,Category category){


        List<Item> itemList=  document.getAllElements().stream().filter(e-> e.hasClass("product-item-container"))
                  .map(e-> {
                      Elements element = e.getElementsByClass("img-responsive");
                      String name = element.attr("title");
                      String imgLink = element.attr("src");
                     String value =  e.getElementById("RedovnaCena_listing").text().split(" ")[2].split(",")[0]
                             + e.getElementById("RedovnaCena_listing").text().split(" ")[2].split(",")[1];
                      Double price = Double.parseDouble(value);
                      Item item = new Item(name,price,imgLink,category);

                      return item;
                  }).collect(Collectors.toList());
        saveParsedData(itemList);


    }

    void saveParsedData(List<Item> itemList) {
        itemList.stream().forEach(item -> {
            if(item!=null)
            itemRepository.save(item);
        });
    }


}