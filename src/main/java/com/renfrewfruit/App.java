package com.renfrewfruit;

import com.renfrewfruit.controller.BatchController;
import com.renfrewfruit.service.BatchService;
import com.renfrewfruit.service.impl.BatchServiceImpl;

public class App {

    public static void main( String[] args ) {

        BatchService batchService = new BatchServiceImpl();
        BatchController batchController = new BatchController(batchService);
        batchController.batchProcess();
    }
}
