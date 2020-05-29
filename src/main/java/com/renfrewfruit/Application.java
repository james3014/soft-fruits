package com.renfrewfruit;

import com.renfrewfruit.bootstrap.Bootstrap;
import com.renfrewfruit.driver.Driver;

import java.io.File;

public class Application {

  public static void main(String[] args) {

    File file = new File("src/main/resources/json/market/Pricing.json");

    if (!file.exists()) {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.initialiseMarket();
      System.out.println("Created Marketplace");
    } else {
      System.out.println("Marketplace Already Created");
    }

    Driver driver = new Driver();
    driver.openMenu();
  }
}
