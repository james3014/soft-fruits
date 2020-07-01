package com.renfrewfruit.service;

import com.renfrewfruit.model.Batch;

import java.util.List;

public interface TransactionService {

  void generateReport(String date);

  void calculateBatchTotals(List<Batch> batches);
}
