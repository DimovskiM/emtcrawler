package com.mdkg.emtcrawler.parser;

import com.mdkg.emtcrawler.model.Category;
import com.mdkg.emtcrawler.model.Item;
import com.mdkg.emtcrawler.repository.jpa.CategoryRepository;
import com.mdkg.emtcrawler.repository.jpa.ItemRepository;
import com.mdkg.emtcrawler.repository.mock.RepositoryMock;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/*

 */
@Component
public  class Parser {
    static String LAPTOP_STORE_WEBSITE = "http://setec.mk";
    static String MARKET_WEBSITE = "http://www.marketonline.mk/";
    static String GRICKI_SOLENKI_SMOKI = "http://www.marketonline.mk/%D0%A7%D0%B8%D0%BF%D1%81%2C-%D1%81%D0%BC%D0%BE%D0%BA%D0%B8%2C-%D1%81%D0%BE%D0%BB%D0%B5%D0%BD%D0%BA%D0%B8%2C-%D0%B3%D1%80%D0%B8%D1%86%D0%BA-41/";
    static String MLECHNI_PROIZVODI = "http://www.marketonline.mk/%D0%9C%D0%BB%D0%B5%D1%87%D0%BD%D0%B8-%D0%BF%D1%80%D0%BE%D0%B8%D0%B7%D0%B2%D0%BE%D0%B4%D0%B8-6/";
    static String SIRENJE = "http://www.marketonline.mk/%D0%A1%D0%B8%D1%80%D0%B5%D1%9A%D0%B5-2/";
    static String MLEKO_JOGURT_KISELO_MLEKO = "http://www.marketonline.mk/%D0%9C%D0%BB%D0%B5%D0%BA%D0%BE%2C-%D1%98%D0%BE%D0%B3%D1%83%D1%80%D1%82%2C-%D0%BA%D0%B8%D1%81%D0%B5%D0%BB%D0%BE-%D0%BC%D0%BB%D0%B5%D0%BA%D0%BE-48/";
    static String LEB = "http://www.marketonline.mk/%D0%9B%D0%B5%D0%B1-%D0%B8-%D0%B1%D0%B5%D0%BB%D0%B8-%D0%BF%D0%B5%D1%86%D0%B8%D0%B2%D0%B0-%D0%9B%D0%B5%D0%B1-19/";
    static String PIVO = "http://www.marketonline.mk/%D0%90%D0%BB%D0%BA%D0%BE%D1%85%D0%BE%D0%BB%D0%BD%D0%B8-%D0%BF%D0%B8%D1%98%D0%B0%D0%BB%D0%BE%D1%86%D0%B8-%D0%9F%D0%B8%D0%B2%D0%BE-64/";
    static String JAJCA = "http://www.marketonline.mk/%D0%88%D0%B0%D1%98%D1%86%D0%B0-%D0%88%D0%B0%D1%98%D1%86%D0%B0-277/";
    static String SVEZH_ZELENCHUK = "http://www.marketonline.mk/%D0%97%D0%B5%D0%BB%D0%B5%D0%BD%D1%87%D1%83%D0%BA-%D0%9E%D1%81%D1%82%D0%B0%D0%BD%D0%B0%D1%82-%D0%B7%D0%B5%D0%BB%D0%B5%D0%BD%D1%87%D1%83%D0%BA-296/";
    static String SETEC_PAGE_ONE = "http://setec.mk/index.php?route=product/category&path=10003&limit=100&";
    static String SETECT_PAGE_TWO = "http://setec.mk/index.php?route=product/category&path=10003&limit=100&&page=2";
    List<String> stringList;




    int page = 0;

    public Parser() {
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


}







