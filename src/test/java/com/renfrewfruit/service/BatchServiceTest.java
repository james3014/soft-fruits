package com.renfrewfruit.service;

import com.renfrewfruit.service.impl.BatchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BatchServiceTest {

  private BatchService batchService;

  @BeforeEach
  void setUp() {
    batchService = new BatchServiceImpl();
  }

  @Test
  void validFarmNumberEntered() {

  }

}
