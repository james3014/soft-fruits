package com.renfrewfruit;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

import com.renfrewfruit.bootstrap.Bootstrap;
import com.renfrewfruit.driver.Driver;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.service.impl.FileServiceImpl;

import java.io.File;

public class Application {

  /**
   * The main method has the responsibility of creating an instance of the driver class which will
   * then enable the main menu for the user. The method also checks whether a marketplace already
   * exists within the resources folder and whether this is up to date.
   *
   * <p>If not this is then created, otherwise the application will start as normal with the main
   * menu.
   *
   * @param args - command line arguments
   */
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
