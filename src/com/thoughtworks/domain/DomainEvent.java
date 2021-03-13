package com.thoughtworks.domain;

public abstract class DomainEvent {

  Item item;
  EventType eventType;

  public DomainEvent(Item item) {
    this.item = item;
  }

  enum EventType{
    CREATED,DELETED,CART_CHECKEDOUT
  }
}
