package com.renfrewfruit.controller;

import com.renfrewfruit.service.BatchService;

public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) { this.batchService = batchService; }

    public void batchProcess() {
        batchService.openMenu();
    }
}
