package com.thoughtworks.domain;

import java.util.List;
import java.util.UUID;

public class Order {

  private UUID id;

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
}
