package com.renfrewfruit.service;

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
