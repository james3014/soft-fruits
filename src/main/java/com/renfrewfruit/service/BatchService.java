package com.renfrewfruit.service;

import com.renfrewfruit.model.Farm;
import com.renfrewfruit.model.Fruit;
import com.renfrewfruit.model.Weight;

public interface BatchService {

  void batchProcess();

  void listAllBatches();

  void batchDetails();

  Farm processFarmNumber();

  Fruit processFruitType();

  Weight processBatchWeight();

}
