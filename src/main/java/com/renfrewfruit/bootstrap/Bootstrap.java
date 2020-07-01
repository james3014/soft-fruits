package com.renfrewfruit.bootstrap;

import com.renfrewfruit.model.Market;
import com.renfrewfruit.model.Price;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.service.impl.FileServiceImpl;
import com.renfrewfruit.utility.DateFormatter;

public class Bootstrap {

  private final FileService fileService;
  private final DateFormatter dateFormatter;

  public Bootstrap() {
    this.fileService = new FileServiceImpl();
    this.dateFormatter = new DateFormatter();
  }

  public void initialiseMarket() {
    Market market = new Market();
    market.setDate(dateFormatter.processDate());
    initialiseFruitPrices(market);
    fileService.createInitialMarketFile(market);
  }

  private void initialiseFruitPrices(Market market) {
    Price strawberryPrice = Price.builder()
        .gradeA(1.14)
        .gradeB(1.00)
        .gradeC(0.90)
        .build();
    market.setStrawberryPrice(strawberryPrice);

    Price raspberryPrice = Price.builder()
        .gradeA(1.15)
        .gradeB(1.01)
        .gradeC(0.86)
        .build();
    market.setRaspberryPrice(raspberryPrice);

    Price blackberryPrice = Price.builder()
        .gradeA(0.97)
        .gradeB(0.80)
        .gradeC(0.60)
        .build();
    market.setBlackberryPrice(blackberryPrice);

    Price gooseberryPrice = Price.builder()
        .gradeA(0.80)
        .gradeB(0.63)
        .gradeC(0.52)
        .build();
    market.setGooseberryPrice(gooseberryPrice);
  }
}
