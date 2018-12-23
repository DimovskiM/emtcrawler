package com.mdkg.emtcrawler.repository.jpa;

import com.mdkg.emtcrawler.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price,Integer> {
}
