package com.renfrewfruit.service;

import com.renfrewfruit.model.Batch;
import com.renfrewfruit.model.Market;

import java.util.List;

public interface FileService {

  void createFile(Batch batch);

  void updateBatchFile(Batch batch);

  void updateMarketFile(Market market);

  String createFileName(String batchNumber);

  String findBatchFile(String batchName);

  List<Batch> findTransactionFiles(String date);

  Market retrieveMarket();

}
