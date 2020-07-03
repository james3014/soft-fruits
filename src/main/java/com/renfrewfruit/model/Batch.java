package com.renfrewfruit.model;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Batch {

  private String batchNumber;
  private Fruit batchFruit;
  private String batchDate;
  private Weight batchWeight;
  private Farm batchOrigin;
  private Price batchValue;

  public String createBatchNumber(Batch batch) {
    return this.batchNumber = batch.getBatchDate()
        + "-" + batch.getBatchFruit().getFruitCode()
        + "-" + batch.getBatchOrigin().getFarmCode();
  }

  public double calculateBatchTotal(Batch batch) {
    return batch.getBatchValue().getGradeA()
        + batch.getBatchValue().getGradeB()
        + batch.getBatchValue().getGradeC();
  }
}
