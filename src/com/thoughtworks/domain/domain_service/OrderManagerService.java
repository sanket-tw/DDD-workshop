package com.thoughtworks.domain.domain_service;

import com.thoughtworks.domain.Cart;
import com.thoughtworks.domain.Order;

public class OrderManagerService {


  public static Order checkoutCart(Cart cart) {
    cart.checkout();
    return new Order(cart.getItems());
  }
}
