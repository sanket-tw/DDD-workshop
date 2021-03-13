package com.thoughtworks.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Cart {

  private UUID id;

  private boolean isCheckedout;//enum status

  List<Item> items;

  List<DomainEvent> domainEvents;

  public Cart() {
    this.id = UUID.randomUUID();
    this.items = new ArrayList<>();
    this.domainEvents = new ArrayList<>();
    this.isCheckedout = false;
  }

  public void add(Item item) {
    items.add(item);
    ItemCreatedDomainEvent itemCreatedDomainEvent = new ItemCreatedDomainEvent(item);
    domainEvents.add(itemCreatedDomainEvent);
  }

  public int cartSize() {
    return items.size();
  }

  public boolean productExists(Product product) {
    return items.stream().anyMatch(item -> item.getProduct().equals(product));
  }

  public boolean remove(Item item) {
    if (productExists(item.getProduct())) {
      items.remove(item);
      ItemDeletedDomainEvent itemDeletedDomainEvent = new ItemDeletedDomainEvent(item);
      domainEvents.add(itemDeletedDomainEvent);
      return true;
    }
    return false;
  }

  public List<Item> getItems() {
    return items;
  }

  public List<DomainEvent> getDomainEvents() {
    return domainEvents;
  }


  public void checkout() {
    this.isCheckedout = true;

    CartCheckoutDomainEvent cartCheckoutDomainEvent = new CartCheckoutDomainEvent();
    domainEvents.add(cartCheckoutDomainEvent); //maybe add a consumer listening to it
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cart cart = (Cart) o;
    return Objects.equals(id, cart.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
