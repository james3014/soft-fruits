package com.renfrewfruit;

import com.renfrewfruit.bootstrap.Bootstrap;
import com.renfrewfruit.driver.Driver;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.service.impl.FileServiceImpl;

import java.io.File;

public class Application {

  public static void main(String[] args) {

    Driver driver = new Driver();
    FileService fileService = new FileServiceImpl();
    File file = new File("src/main/resources/json/market/Pricing.json");

    if (!file.exists()) {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.initialiseMarket();
      System.out.println("Created Marketplace");
    } else {
      if (!fileService.checkCurrentMarketplaceDate()) {
        System.out.println("Marketplace Prices Are Out Of Date\n");
        driver.setDailyFruitPrices();
      } else {
        System.out.println("Marketplace Prices Are Up To Date");
      }
    }
    driver.openMenu();
  }
}
