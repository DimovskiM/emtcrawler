package com.mdkg.emtcrawler.repository.jpa;
import com.mdkg.emtcrawler.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Integer> {

       @Query("select i from Item i where i.name like :name")
       Item findByName(@Param("name") String name);

       List<Item> findAll();

       @Query("select i from Item i where category_name=:category")
       List<Item> findByCategoryName(@Param("category")String category);

       @Query("select i from Item i where i.id like :id")
       Item findById(@Param("id") String id);
}
