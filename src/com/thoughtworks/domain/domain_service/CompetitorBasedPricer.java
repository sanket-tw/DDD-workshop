package com.thoughtworks.domain.domain_service;

import com.thoughtworks.domain.Price;

import java.util.Map;

public class CompetitorBasedPricer {

  Map<String, Price> productPriceMap;

  public CompetitorBasedPricer(Map<String, Price> productPriceMap) {
    this.productPriceMap = productPriceMap;
  }

  public static Price getPrice(Price price) {
    return price;//by 10%
  }

  public static Price getDiscountPrice(Price price) {
    return price;//by 10%
  }
}
