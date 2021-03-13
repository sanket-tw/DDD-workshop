package com.thoughtworks.domain;

import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

public class Price {

  private Currency currency;

  private double value;

  public Price(double value) {
    this.currency = Currency.getInstance(new Locale("", "IN"));
    this.value = value;
  }

  public Price(Currency currency, int value) {
    this.currency = currency;
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Price price = (Price) o;
    return value == price.value && currency.equals(price.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currency, value);
  }

  public double getValue() {
    return value;
  }
}
