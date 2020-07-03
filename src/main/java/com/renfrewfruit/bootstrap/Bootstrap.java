package com.renfrewfruit.bootstrap;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

import com.renfrewfruit.model.Market;
import com.renfrewfruit.model.Price;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.service.impl.FileServiceImpl;
import com.renfrewfruit.utility.DateFormatter;

/**
 * The responsibility of this class is to create a marketplace file should one not exist with
 * pre-defined values. These prices can then be changed once the application is running normally.
 */
public class Bootstrap {

  private final FileService fileService;
  private final DateFormatter dateFormatter;

  public Bootstrap() {
    this.fileService = new FileServiceImpl();
    this.dateFormatter = new DateFormatter();
  }

  /**
   * This function is responsible for generating the initial marketplace file. This is done by
   * creating a market object and setting the date to today. The initialiseFruitPrices method is
   * then called which will set the prices for all fruits before the market object is passed to the
   * file service to be created in the resources folder.
   */
  public void initialiseMarket() {
    Market market = new Market();
    market.setDate(dateFormatter.processDate());
    initialiseFruitPrices(market);
    fileService.createInitialMarketFile(market);
  }

  /**
   * This function uses builders to set the individual prices of each of the fruits. These values
   * are pre-defined and simply to have an initial marketplace file setup otherwise the application
   * will not work.
   *
   * @param market - the marketplace object with the current date but no values
   */
  private void initialiseFruitPrices(Market market) {
    Price strawberryPrice = Price.builder().gradeA(1.14).gradeB(1.00).gradeC(0.90).build();
    market.setStrawberryPrice(strawberryPrice);

    Price raspberryPrice = Price.builder().gradeA(1.15).gradeB(1.01).gradeC(0.86).build();
    market.setRaspberryPrice(raspberryPrice);

    Price blackberryPrice = Price.builder().gradeA(0.97).gradeB(0.80).gradeC(0.60).build();
    market.setBlackberryPrice(blackberryPrice);

    Price gooseberryPrice = Price.builder().gradeA(0.80).gradeB(0.63).gradeC(0.52).build();
    market.setGooseberryPrice(gooseberryPrice);
  }
}
