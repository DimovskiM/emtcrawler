package com.mdkg.emtcrawler.repository.jpa;

import com.mdkg.emtcrawler.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price,Integer> {

}
