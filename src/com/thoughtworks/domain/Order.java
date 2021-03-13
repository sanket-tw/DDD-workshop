package com.thoughtworks.domain;

import java.util.List;
import java.util.UUID;

public class Order {

  private UUID id;

  //change to products
  private List<Item> items; //have products instead of cart

  public Order(List<Item> items) {
    this.items = items;
    this.id = UUID.randomUUID();
  }

  //maybe add total cost
  //domain event not required now
  public UUID getId() {
    return id;
  }

  public List<Item> getItems() {
    return items;
  }

  //should return Price instead
  public double calculateTotalCost() {
    //add logic to handle currency as well,
    //items or products
    return items.stream().map(item -> item.cost()).reduce((double) 0, Double::sum);
  }


}
