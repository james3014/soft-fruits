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
public class Gooseberry extends Fruit {

  public Gooseberry() {
    super();
    setProductName("Gooseberries");
    setFruitCode("GO");
  }
}
