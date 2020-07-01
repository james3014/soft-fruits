package com.renfrewfruit.service;

import com.renfrewfruit.model.Batch;
import com.renfrewfruit.model.Weight;

public interface SortingService {

  void gradeBatch(Batch batch, String fileName);

  void calculatePercentages(Batch batch);

  void calculateBatchDetails(Batch batch);

  Weight calculateGradeWeights(Batch batch);
}
