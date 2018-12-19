package com.mdkg.emtcrawler.parser;

import com.mdkg.emtcrawler.model.Category;
import com.mdkg.emtcrawler.model.Item;
import com.mdkg.emtcrawler.repository.jpa.CategoryRepository;
import com.mdkg.emtcrawler.repository.jpa.ItemRepository;
import com.mdkg.emtcrawler.repository.mock.RepositoryMock;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    static String WEBSITE_TO_BE_CRAWLED = "http//marketonline.mk";
    static String GRICKI_SOLENKI_SMOKI = "page0.html";
    static String MLECHNI_PROIZVODI = "page1.html";
    static String SIRENJE = "page2.html";
    static String MLEKO_JOGURT_KISELO_MLEKO = "page3.html";
    static String LEB = "page4.html";
    static String PIVO = "page5.html";
    static String JAJCA = "page6.html";
    static String SVEZH_ZELENCHUK = "page7.html";
    List<String> stringList;

    public MockParser() {

        stringList = new ArrayList<>();
        stringList.add(GRICKI_SOLENKI_SMOKI);
        stringList.add(MLECHNI_PROIZVODI);
        stringList.add(SIRENJE);
        stringList.add(MLEKO_JOGURT_KISELO_MLEKO);
        stringList.add(LEB);
        stringList.add(PIVO);
        stringList.add(JAJCA);
        stringList.add(SVEZH_ZELENCHUK);


    }

    public void getAllElements(Document document) {
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
                    Double price = priceString.length() > 0 ? Double.parseDouble(priceString.split(" ")[1]) : 0;
                    Item item = new Item(element.getElementsByClass("image-thumb").attr("title"), price, cat);
                    return item;
                }).collect(Collectors.toList());

        saveParsedData(itemList);
        //   .forEach(e-> System.out.println(e.getElementsByClass("image-thumb").attr("title") +" cena = " + e.getElementsByClass("priceCurrent").text()));


    }

    public void buildDatabase() {
        stringList.stream().forEach(website -> {
            File file = new File(website);
            Document doc;
            try {
                doc = Jsoup.parse(file, "UTF-8", WEBSITE_TO_BE_CRAWLED);
                getAllElements(doc);
            } catch (IOException e) {
                e.printStackTrace();
            }


        });
    }


    void saveParsedData(List<Item> itemList) {
        itemList.stream().forEach(item -> {
            itemRepository.save(item); });
    }
}