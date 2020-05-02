package com.renfrewfruit.service;


import com.renfrewfruit.model.Farm;
import com.renfrewfruit.model.Fruit;

public interface BatchService {

    void openMenu();

    void batchProcess();

    Farm processFarmNumber();

    Fruit processFruitType();

    int processBatchWeight();

    void printDetails(String date, Fruit fruitType, Farm farmNumber, int batchWeight);

}
