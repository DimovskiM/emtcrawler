package com.mdkg.emtcrawler.repository.mock;

import com.mdkg.emtcrawler.model.Category;
import com.mdkg.emtcrawler.model.Item;
import com.sun.tools.javac.jvm.Items;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class RepositoryMock {
    List<Item> itemList;
    List<Category> categoryList;

    public RepositoryMock() {
        this.itemList = new ArrayList<>();
        this.categoryList = new ArrayList<>();
    }

    public void addItems(List <Item> itemsList){
        itemsList.stream().filter(e-> !itemList.contains(e)).forEach(e-> itemList.add(e));
    }

    public void addCategory(Category category){
        if(!categoryList.contains(category))
            categoryList.add(category);
    }


    public void listAllElements(){
        itemList.stream().forEach(e-> System.out.println(e.toString()));
    }


    public void listByCategory(){
        categoryList.stream().forEach(cat -> {
            System.out.println(cat);
            itemList.stream().filter(item -> item.category.equals(cat)).forEach(e-> System.out.println(e));
            System.out.println("----------------------------------------------------------------------------------");

        });
    }
}
