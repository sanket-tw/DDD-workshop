package com.thoughtworks.domain.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer {

  private UUID uuid;


  public Customer(Address address) {
    this.address = address;
    this.bankAccountList = new ArrayList<>();
    this.uuid = UUID.randomUUID();
  }

  private Address address;

  private List<BankAccount> bankAccountList;


  //ACID
  public void updateAddress(Address address) {
    this.address = address;
    //domain event, or bankaccount method
    bankAccountList.forEach(
        bankAccount -> bankAccount.updateAddress(address)
    );
  }

  public void addBankAccount() {
    this.bankAccountList.add(new BankAccount(address));
  }

  public List<BankAccount> getBankAccountList() {
    return bankAccountList;
  }
}
