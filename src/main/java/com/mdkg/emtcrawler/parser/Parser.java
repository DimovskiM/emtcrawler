package com.mdkg.emtcrawler.parser;
import com.mdkg.emtcrawler.model.Category;
import com.mdkg.emtcrawler.model.Item;
import com.mdkg.emtcrawler.model.Price;
import com.mdkg.emtcrawler.repository.jpa.CategoryRepository;
import com.mdkg.emtcrawler.repository.jpa.ItemRepository;
import com.mdkg.emtcrawler.repository.jpa.PriceRepository;
import org.apache.commons.validator.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Parser {

    private static String MARKET_WEBSITE = "https://www.e-tinex.mk";
    private static String LAPTOP_STORE_WEBSITE = "http://setec.mk";

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    PriceRepository priceRepository;


    public Parser() { }

    public void getFoodItems(String document) throws IOException {

        Document doc = Jsoup.connect(document).get();

        List<Item> itemList = doc.getElementsByClass("grid_category1")
                .stream().map(element -> {
                   // Elements informationElements = element.getElementsByTag("img");
                    String priceString = element.getElementsByClass("price_cont").text();
                    String otherString = element.getElementsByClass("nova_cena").text();

                    priceString = !otherString.equals("") ? otherString : priceString;

                    String itemName = element.getElementsByTag("img").attr("title");
                    String itemUrl = element.getElementsByTag("img").attr("src");
                    Double price = priceString.length() > 0 ? Double.parseDouble(priceString.split(" ")[0]) : 0;
                    String categoryName = element.getElementsByTag("img").attr("alt");
                    Category category = new Category(categoryName);
                    if(!categoryName.equals(""))
                    categoryRepository.save(category);

                    Item item = new Item(itemName, price, itemUrl, category);
                    return item.price > 0 ? item : null;

                }).collect(Collectors.toList());

        saveParsedData(itemList);
    }


    @Scheduled(cron = "0 12 1 * * *") //Grabs data on every 1st of the month at 12:00
    public void buildDatabase() {
        Document document;
        try {
            document = Jsoup.connect(MARKET_WEBSITE).get();
            grabLinks(document);
            document = Jsoup.connect(LAPTOP_STORE_WEBSITE).get();
            grabLinks(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void getLaptopItems(Document document, Category category) {


        List<Item> itemList = document.getAllElements().stream().filter(e -> e.hasClass("product-thumb"))
                .map(e -> {

                    Elements element = e.getElementsByClass("img-responsive");
                    String name = e.getElementsByClass("img-responsive").attr("title");
                    String imgLink = e.getElementsByClass("img-responsive").attr("src");

                    String value []= e.getElementsByClass("price-new").text().split(" ")[0].split(",");


                    Double price = Double.parseDouble(value[0] + value[1]);
                    Item item = new Item(name, price, imgLink, category);

                    return item.price > 0 ? item : null;
                }).collect(Collectors.toList());
        saveParsedData(itemList);
    }

    private void saveParsedData(List<Item> itemList) {
        itemList.stream().filter(item -> item != null).forEach(item -> {
            Item newItem = itemRepository.findByName(item.name);
            if (newItem != null && newItem.price >item.price) {
                Price oldPrice = new Price(item.price, item.date);
                Price newPrice = new Price(newItem.price , newItem.date);
                newItem.addPrice(newPrice, oldPrice);
                priceRepository.save(oldPrice);
                itemRepository.save(newItem);
            } else if (newItem == null){
                itemRepository.save(item);
            }
        });
    }

    void grabLinks(Document document) {
        Category techCategory = new Category("Технологија");
        categoryRepository.save(techCategory);
        document.select("a[href]").forEach(element -> {
            try {
                String website = element.attr("abs:href");
                if (new UrlValidator().isValid(website)) {
                    if (website.contains(MARKET_WEBSITE)) {
                        getFoodItems(website);
                    } else if (website.contains(LAPTOP_STORE_WEBSITE)) {
                        getLaptopItems(document, techCategory);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}
