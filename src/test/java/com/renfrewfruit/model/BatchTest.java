package com.renfrewfruit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BatchTest {

  private Batch batch;

  @BeforeEach
  void setUp() {
    batch = Batch.builder()
        .batchDate("13062020")
        .batchFruit(new Fruit("Strawberry", "ST", 0, 0, 0, 0))
        .batchWeight(new Weight(50.0, 30.0, 10.0, 10.0, 0.0))
        .batchOrigin(new Farm(555))
        .batchValue(new Price(50.0, 30.0, 25.0))
        .build();
  }

  @Test
  void givenBatchShouldCalculateTotal() {
    batch.calculateBatchTotal(batch);
    assertEquals(105.0, batch.getBatchValue().getTotal());
  }

  @Test
  void givenBatchShouldCreateNumber() {
    batch.createBatchNumber(batch);
    assertEquals("13062020-ST-555", batch.getBatchNumber());
  }
}
