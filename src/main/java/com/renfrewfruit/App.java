package com.renfrewfruit;

import com.renfrewfruit.bootstrap.Bootstrap;
import com.renfrewfruit.controller.BatchController;
import com.renfrewfruit.service.BatchService;
import com.renfrewfruit.service.impl.BatchServiceImpl;

import java.io.File;

public class App {

    public static void main( String[] args ) {

        File file = new File("src/main/resources/json/Pricing.json");

        if (!file.exists()) {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.initialiseMarket();
            System.out.println("Created Marketplace");
        } else System.out.println("Marketplace Already Created");

        BatchService batchService = new BatchServiceImpl();
        BatchController batchController = new BatchController(batchService);
        batchController.batchProcess();
    }
}
