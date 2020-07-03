package com.renfrewfruit.service;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

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
