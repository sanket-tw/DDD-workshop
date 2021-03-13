package com.thoughtworks.domain;

public class ItemCreatedDomainEvent extends DomainEvent {


  public ItemCreatedDomainEvent(Item item) {
    super(item);
    this.eventType = EventType.CREATED;
  }
}
