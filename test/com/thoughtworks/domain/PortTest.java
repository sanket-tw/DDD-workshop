package com.thoughtworks.domain;

import com.thoughtworks.domain.domain_service.CompetitorBasedPricer;
import com.thoughtworks.domain.domain_service.OrderManagerService;
import com.thoughtworks.domain.misc.Address;
import com.thoughtworks.domain.misc.Customer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PortTest {

  private Cart cart;


  @Test
  void shouldAddProductToCart() {
    Cart cart = new Cart();
    Item ipadPro = new Item("Ipad Pro");

    cart.add(ipadPro);

    assertEquals(1, cart.cartSize());
  }

  @Test
  void shouldAddMultipleProductToCart() {
    Cart cart = new Cart();
    Item ipadPro = new Item("Ipad Pro");
    Item heroInkPen = new Item("Hero ink Pen");
    cart.add(ipadPro);
    cart.add(heroInkPen);

    assertEquals(2, cart.cartSize());
  }

  @Test
  void shouldAddItemToCard() {
    Product gmCricketBat = new Product("GM Cricket bat");
    Item gmCricketBatItem = new Item(gmCricketBat, 2);

    Cart cart = new Cart();
    cart.add(gmCricketBatItem);

    assertEquals(1, cart.cartSize());
    assertTrue(cart.productExists(gmCricketBat));
  }

  @Test
  void shouldRemoveItemFromCart() {
    Cart cart = new Cart();
    Product gmCricketBat = new Product("GM Cricket bat");
    Item gmCricketBatItem = new Item(gmCricketBat, 2);
    cart.add(gmCricketBatItem);


    boolean removed = cart.remove(gmCricketBatItem);
    assertTrue(removed);
    assertEquals(0, cart.cartSize());
  }

  @Test
  void shouldSendDeleteItemFromCartEventToAnalytics() {
    Cart cart = new Cart();

    Product gmCricketBat = new Product("GM Cricket bat");
    Item gmCricketBatItem = new Item(gmCricketBat, 2);
    cart.add(gmCricketBatItem);

    boolean removed = cart.remove(gmCricketBatItem);
    assertTrue(removed);

    assertEquals(2, cart.getDomainEvents().size());
    assertEquals(gmCricketBatItem, cart.getDomainEvents().get(1).item);
    assertEquals(DomainEvent.EventType.DELETED, cart.getDomainEvents().get(1).eventType);
  }

  @Test
  void shouldDifferentiateCartsEvenWithSameItems() {

    Cart cartOne = new Cart();
    Product gmCricketBat = new Product("GM Cricket bat");
    Item gmCricketBatItem = new Item(gmCricketBat, 2);
    cartOne.add(gmCricketBatItem);

    Cart cartTwo = new Cart();
    Product gmCricketBat2 = new Product("GM Cricket bat");
    Item gmCricketBatItem2 = new Item(gmCricketBat2, 2);
    cartTwo.add(gmCricketBatItem2);

    assertNotEquals(cartOne, cartTwo);
    assertFalse(cartOne.equals(cartTwo));
  }

  @Test
  void shouldSeePriceOfProductBeforeAddingToCart() {

    Cart cartOne = new Cart();
    Product gmCricketBat = new Product("GM Cricket bat", 2);
    Item gmCricketBatItem = new Item(gmCricketBat, 1);

    cartOne.add(gmCricketBatItem);
    assertEquals(true, cartOne.productExists(gmCricketBat));

    assertEquals(new Price(2), cartOne.getItems().get(0).getProduct().getPrice());
  }

  @Test
  void shouldSetPriceBelow10PercentOfCompetitorPrice() {

    Map<String, Price> productPriceMap = Map.of("ipad", new Price(10));//pass in CompetitorBasedPricer const

    String productName = "ipad";
    Price price = CompetitorBasedPricer.getPrice(productPriceMap.get(productName));

    Product product = new Product(productName, price);

    assertEquals(10.0, price.getValue());
    assertEquals(9.0, product.getPrice().getValue());

  }

  @Test
  void shouldCheckoutCartAndCreateOrder() {
    Cart cartOne = new Cart();
    Product gmCricketBat = new Product("GM Cricket bat", 2);
    Item gmCricketBatItem = new Item(gmCricketBat, 1);
    cartOne.add(gmCricketBatItem);

    Order order = OrderManagerService.checkoutCart(cartOne);
    //should order have products? or have cart...: ans : should add products to add .or item
    assertEquals(gmCricketBatItem, order.getItems().get(0));
  }

  @Test
  void shouldUpdateBankAccountAddressWhenCustomerUpdatesAddress() {
    Address oldAdress = new Address("some address");
    Address newAdress = new Address("new address");

    Customer customer = new Customer(oldAdress);
    customer.addBankAccount();
    customer.addBankAccount();

    //start logical transaction
    customer.updateAddress(newAdress);
    //end logical transaction

    assertEquals(newAdress, customer.getBankAccountList().get(0).getCustomerAddress());
    assertEquals(newAdress, customer.getBankAccountList().get(1).getCustomerAddress());
  }

  @Test
  void shouldCalculateTotalCostOfOrder() {
    Cart cartOne = new Cart();
    Product gmCricketBat = new Product("GM Cricket bat", new Price(2), new Weight(10.0));
    Item gmCricketBatItem = new Item(gmCricketBat, 1);
    cartOne.add(gmCricketBatItem);

    Order order = OrderManagerService.checkoutCart(cartOne);

    assertEquals(2 * 1 + 10.0 * 0.1, order.calculateTotalCost());

  }
}
