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
    private static String REPTIL_WEBSITE = "http://www.marketonline.mk";

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
                    String id = element.getElementsByClass("dodaj_koshn").attr("data-id");
                    String priceString = element.getElementsByClass("price_cont").text();
                    String otherString = element.getElementsByClass("nova_cena").text();

                    priceString = !otherString.equals("") ? otherString : priceString;

                    String itemName = element.getElementsByTag("img").attr("title");
                    String itemUrl = element.getElementsByTag("img").attr("src");
                    Double price = Parse(priceString.split(" ")[0]);
                    String categoryName = element.getElementsByTag("img").attr("alt");
                    Category category = new Category(categoryName);
                    if(!categoryName.equals(""))
                    categoryRepository.save(category);

                    Item item = new Item(id,itemName, price, itemUrl, category);
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
            document = Jsoup.connect(REPTIL_WEBSITE).get();
            grabLinks(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void getReptilItems(String website) throws IOException {

        Document document = Jsoup.connect(website).get();
        Category cat =    document.getAllElements()
                    .stream()
                    .filter(e-> e.hasClass("active"))
                    .map(element -> {
                        Category category  = new Category(element.getElementsByClass("active").text());
                        return category;
                    }).findAny().orElse(null);

            List<Item> itemList =  document.getAllElements()
                    .stream()
                    .filter(e-> e.hasClass("thumbnail")).map(element -> {
                        String id = element.getElementsByTag("select").attr("id");
                        String priceString = element.getElementsByClass("priceCurrent").text();
                        Double price = Parse(priceString.split(" ")[1].replace(",",""));
                        String name = element.getElementsByClass("image-thumb").attr("title");
                        String imageUrl = element.getElementsByClass("image-thumb").attr("src");
                        Item item = new Item(id,name,price,imageUrl,cat);
                        return item.price > 0 ? item : null;
                    }).collect(Collectors.toList());

           if(cat != null && !itemList.isEmpty()) {
               categoryRepository.save(cat);
               saveParsedData(itemList);
           }
    }

    void getLaptopItems(String website, Category category) throws IOException {

        Document document = Jsoup.connect(website).get();

        List<Item> itemList = document.getAllElements().stream().filter(e -> e.hasClass("product-thumb"))
                .map(e -> {

                    String name = e.getElementsByClass("img-responsive").attr("title");
                    String imgLink = e.getElementsByClass("img-responsive").attr("src");

                    String value = e.getElementsByClass("price-new").text().split(" ")[0].replace(",","");


                    Double price = Parse(value);
                    Item item = new Item(name,name, price, imgLink, category);

                    return item.price > 0 ? item : null;
                }).collect(Collectors.toList());
        saveParsedData(itemList);
    }

    private void saveParsedData(List<Item> itemList) {
        itemList.stream().filter(item -> item != null).forEach(item -> {
            Item newItem = itemRepository.findById(item.name);
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
                        getLaptopItems(website, techCategory);
                    }
                    else if(website.contains(REPTIL_WEBSITE)){
                        Thread.sleep(10000);
                        getReptilItems(website);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
    }

    double Parse(String value){
        if(value.length()<0){
            return 0.0;
        }

        try{
         double price = Double.parseDouble(value);

         return price;
        } catch (NumberFormatException e){
            return 0.0;
        }
    }
}
