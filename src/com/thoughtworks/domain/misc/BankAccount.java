package com.thoughtworks.domain.misc;

import java.util.UUID;

public class BankAccount {

  private UUID accountId;
  //add other details here

  public BankAccount() {
    this.accountId = UUID.randomUUID();
  }

  public BankAccount(Address customerAddress) {
    this.customerAddress = customerAddress;
    this.accountId = UUID.randomUUID();
  }

  private Address customerAddress;

  public Address getCustomerAddress() {
    return customerAddress;
  }


  public void updateAddress(Address address) {
    this.customerAddress = address;
  }
}
