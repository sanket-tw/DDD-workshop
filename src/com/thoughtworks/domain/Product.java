package com.thoughtworks.domain;

public class Product {
  private String name;

  private Price price;

  private Weight weight;

  public Product(String name) {
    this.name = name;
  }


  public Product(String name, int price) {
    this.name = name;
    this.price = new Price(price);
  }

  public Product(String name, Price price) {
    this.name = name;
    this.price = price;
  }

  public Product(String name, Price price, Weight weight) {
    this.name = name;
    this.price = price;
    this.weight = weight;
  }

  public Price getPrice() {
    return price;
  }

  public void setPriceBelowCompetitor(Price competitorPrice) {
    this.price = new Price(competitorPrice.getValue() * 0.9);
  }

  public Weight getWeight() {
    return weight;
  }
}
