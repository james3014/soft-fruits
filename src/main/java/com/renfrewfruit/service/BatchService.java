package com.renfrewfruit.service;


public interface BatchService {

    void openMenu();

    void batchProcess();

    int processFarmNumber();

    String processFruitType();

    int processBatchWeight();

    void printDetails(String date, String fruitType, int farmNumber, int batchWeight);

}
