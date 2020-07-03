package com.renfrewfruit.service;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

import com.renfrewfruit.model.Batch;
import com.renfrewfruit.model.Weight;

public interface SortingService {

  void gradeBatch(Batch batch);

  void calculatePercentages(Batch batch);

  void calculateBatchDetails(Batch batch);

  Weight calculateGradeWeights(Batch batch);
}
