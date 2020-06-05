package com.renfrewfruit.bootstrap;

import com.renfrewfruit.model.Market;
import com.renfrewfruit.model.Price;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.service.impl.FileServiceImpl;
import com.renfrewfruit.utility.DateResolver;

public class Bootstrap {

  private final FileService fileService;
  private final DateResolver dateResolver;

  public Bootstrap() {
    this.fileService = new FileServiceImpl();
    this.dateResolver = new DateResolver();
  }

  public void initialiseMarket() {
    Market market = new Market();
    market.setDate(dateResolver.processDate());

    Price strawberryPrice = new Price();
    strawberryPrice.setGradeA(1.14);
    strawberryPrice.setGradeB(1.00);
    strawberryPrice.setGradeC(0.90);
    market.setStrawberryPrice(strawberryPrice);

    Price raspberryPrice = new Price();
    raspberryPrice.setGradeA(1.15);
    raspberryPrice.setGradeB(1.01);
    raspberryPrice.setGradeC(0.86);
    market.setRaspberryPrice(raspberryPrice);

    Price blackberryPrice = new Price();
    blackberryPrice.setGradeA(0.97);
    blackberryPrice.setGradeB(0.80);
    blackberryPrice.setGradeC(0.60);
    market.setBlackberryPrice(blackberryPrice);

    Price gooseberryPrice = new Price();
    gooseberryPrice.setGradeA(0.80);
    gooseberryPrice.setGradeB(0.63);
    gooseberryPrice.setGradeC(0.52);
    market.setGooseberryPrice(gooseberryPrice);

    fileService.createInitialMarketFile(market);
  }
}
