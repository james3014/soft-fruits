package com.renfrewfruit;

import com.renfrewfruit.bootstrap.Bootstrap;
import com.renfrewfruit.controller.BatchController;
import com.renfrewfruit.service.BatchService;
import com.renfrewfruit.service.impl.BatchServiceImpl;

import java.io.File;

public class Application {

  public final static BatchService batchService = new BatchServiceImpl();

  public static void main(String[] args) {

    File file = new File("src/main/resources/json/market/Pricing.json");

      if (!file.exists()) {
          Bootstrap bootstrap = new Bootstrap();
          bootstrap.initialiseMarket();
          System.out.println("Created Marketplace");
      } else {
          System.out.println("Marketplace Already Created");
      }

    BatchController batchController = new BatchController(batchService);
    batchController.batchProcess();
  }
}
