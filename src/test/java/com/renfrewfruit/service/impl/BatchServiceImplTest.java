package com.renfrewfruit.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.renfrewfruit.model.Batch;
import com.renfrewfruit.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BatchServiceImplTest {

  private Batch batch;

  @BeforeEach
  void setUp() {
    batch = Batch.builder()
        .batchValue(new Price(50.0, 30.0, 25.0))
        .build();
  }

  @Test
  void givenBatchShouldCalculateTotal() {
    batch.calculateBatchTotal(batch);
    assertEquals(105.0, batch.getBatchValue().getTotal());
  }
}
