package com.renfrewfruit.model;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

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
