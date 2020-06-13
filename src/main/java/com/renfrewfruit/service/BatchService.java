package com.renfrewfruit.service;

import com.renfrewfruit.model.Batch;
import com.renfrewfruit.model.Farm;
import com.renfrewfruit.model.Fruit;
import com.renfrewfruit.model.Weight;

public interface BatchService {

  void processNewBatch();

  void listAllBatches();

  void batchDetails();

  void createBatch(Batch batch);

  Farm processFarmNumber();

  Fruit processFruitType();

  Weight processBatchWeight();

}
