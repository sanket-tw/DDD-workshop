package com.thoughtworks.domain;

public class Item {

  private Product product;
  private int quantity;


  public Item(Product product) {
    this.product = product;
    this.quantity = 1;
  }

  public Item(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public Item(String productName) {
    this.product = new Product(productName);
    this.quantity = 1;
  }

  public Product getProduct() {
    return product;
  }


  public int getQuantity() {
    return quantity;
  }
}
