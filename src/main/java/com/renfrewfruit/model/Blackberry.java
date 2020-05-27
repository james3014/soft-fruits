package com.renfrewfruit.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Blackberry extends Fruit {

  public Blackberry() {
    super();
    setProductName("Blackberries");
    setFruitCode("BL");
  }
}
