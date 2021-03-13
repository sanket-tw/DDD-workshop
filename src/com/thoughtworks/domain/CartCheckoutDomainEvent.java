package com.thoughtworks.domain;

public class CartCheckoutDomainEvent extends DomainEvent {

  public CartCheckoutDomainEvent() {
    super(null); //refactor
    this.eventType = EventType.CART_CHECKEDOUT;
  }
}
