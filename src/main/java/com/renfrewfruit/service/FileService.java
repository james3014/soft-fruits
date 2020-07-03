package com.renfrewfruit.service;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

import com.renfrewfruit.model.Batch;
import com.renfrewfruit.model.Market;

import java.util.List;

public interface FileService {

  void createBatchFile(Batch batch);

  void updateBatchFile(Batch batch);

  void updateMarketFile(Market market);

  String createFileName(String batchNumber);

  String getBatchFileName(String batchName);

  Batch mapBatchFromFile(String filename);

  List<Batch> findTransactionFiles(String date);

  Market retrieveMarket();

  void createInitialMarketFile(Market market);

  boolean checkCurrentMarketplaceDate();

}
