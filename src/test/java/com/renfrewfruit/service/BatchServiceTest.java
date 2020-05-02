package com.renfrewfruit.service;

import com.renfrewfruit.service.impl.BatchServiceImpl;
import com.renfrewfruit.service.impl.FileServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

public class BatchServiceTest {

    @InjectMocks
    private BatchService batchService;

    @Mock private FileService fileService;

    @Before
    public void setup() {
        int testFarmNumber = 50;
        batchService = new BatchServiceImpl();
        fileService = new FileServiceImpl();
        when(batchService.processFarmNumber()).thenReturn(testFarmNumber);
    }

    @Test
    public void processFarmNumberTest() {

    }

}
