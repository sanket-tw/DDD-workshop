package com.thoughtworks.domain;

public class ItemDeletedDomainEvent extends DomainEvent{

  public ItemDeletedDomainEvent(Item item) {
    super(item);
    this.eventType = EventType.DELETED;
  }
}
