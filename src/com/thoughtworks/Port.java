package com.thoughtworks;

import com.thoughtworks.domain.Cart;
import com.thoughtworks.domain.Item;

public class Port {

  public static void main(String[] args) {
    Cart cart = new Cart();
    Item iPad_pro = new Item("IPad Pro");
    Item heroInkPen = new Item("Hero ink Pen");

    cart.add(iPad_pro);
    cart.add(heroInkPen);
  }

}
