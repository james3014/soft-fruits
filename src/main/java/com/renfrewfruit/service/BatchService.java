package com.renfrewfruit.service;


import com.renfrewfruit.model.Batch;
import com.renfrewfruit.model.Farm;
import com.renfrewfruit.model.Fruit;

public interface BatchService {

    void openMenu();

    void batchProcess();

    void listAllBatches();

    Farm processFarmNumber();

    Fruit processFruitType();

    int processBatchWeight();

    void printDetails(Batch batch, String batchNumber);

    void gradeProcess();



}
