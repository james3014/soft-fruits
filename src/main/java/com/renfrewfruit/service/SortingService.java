package com.renfrewfruit.service;

import com.renfrewfruit.model.Batch;

public interface SortingService {

  void gradeBatch(Batch batch, String fileName);

  void calculatePercentages(Batch batch);
}
