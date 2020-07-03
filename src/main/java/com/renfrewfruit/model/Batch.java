package com.renfrewfruit.model;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

public class Batch {

  private String batchNumber;
  private Fruit batchFruit;
  private String batchDate;
  private Weight batchWeight;
  private Farm batchOrigin;
  private Price batchValue;

  public Batch(String batchNumber, Fruit batchFruit, String batchDate, Weight batchWeight,
               Farm batchOrigin, Price batchValue) {
    this.batchNumber = batchNumber;
    this.batchFruit = batchFruit;
    this.batchDate = batchDate;
    this.batchWeight = batchWeight;
    this.batchOrigin = batchOrigin;
    this.batchValue = batchValue;
  }

  public Batch() {
  }

  public static BatchBuilder builder() {
    return new BatchBuilder();
  }

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

  public String getBatchNumber() {
    return this.batchNumber;
  }

  public void setBatchNumber(String batchNumber) {
    this.batchNumber = batchNumber;
  }

  public Fruit getBatchFruit() {
    return this.batchFruit;
  }

  public void setBatchFruit(Fruit batchFruit) {
    this.batchFruit = batchFruit;
  }

  public String getBatchDate() {
    return this.batchDate;
  }

  public void setBatchDate(String batchDate) {
    this.batchDate = batchDate;
  }

  public Weight getBatchWeight() {
    return this.batchWeight;
  }

  public void setBatchWeight(Weight batchWeight) {
    this.batchWeight = batchWeight;
  }

  public Farm getBatchOrigin() {
    return this.batchOrigin;
  }

  public void setBatchOrigin(Farm batchOrigin) {
    this.batchOrigin = batchOrigin;
  }

  public Price getBatchValue() {
    return this.batchValue;
  }

  public void setBatchValue(Price batchValue) {
    this.batchValue = batchValue;
  }

  public static class BatchBuilder {
    private String batchNumber;
    private Fruit batchFruit;
    private String batchDate;
    private Weight batchWeight;
    private Farm batchOrigin;
    private Price batchValue;

    BatchBuilder() {
    }

    public Batch.BatchBuilder batchNumber(String batchNumber) {
      this.batchNumber = batchNumber;
      return this;
    }

    public Batch.BatchBuilder batchFruit(Fruit batchFruit) {
      this.batchFruit = batchFruit;
      return this;
    }

    public Batch.BatchBuilder batchDate(String batchDate) {
      this.batchDate = batchDate;
      return this;
    }

    public Batch.BatchBuilder batchWeight(Weight batchWeight) {
      this.batchWeight = batchWeight;
      return this;
    }

    public Batch.BatchBuilder batchOrigin(Farm batchOrigin) {
      this.batchOrigin = batchOrigin;
      return this;
    }

    public Batch.BatchBuilder batchValue(Price batchValue) {
      this.batchValue = batchValue;
      return this;
    }

    public Batch build() {
      return new Batch(batchNumber, batchFruit, batchDate, batchWeight, batchOrigin, batchValue);
    }

    public String toString() {
      return "Batch.BatchBuilder(batchNumber=" + this.batchNumber + ", batchFruit="
          + this.batchFruit
          + ", batchDate=" + this.batchDate + ", batchWeight=" + this.batchWeight + ", batchOrigin="
          + this.batchOrigin + ", batchValue=" + this.batchValue + ")";
    }
  }
}
