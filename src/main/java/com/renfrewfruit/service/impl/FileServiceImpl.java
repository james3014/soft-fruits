package com.renfrewfruit.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renfrewfruit.model.Batch;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.utility.BatchNumberCreator;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileServiceImpl implements FileService {

    BatchNumberCreator batchNumberCreator = new BatchNumberCreator();

    public void createFile(Batch batch) {

        try {
            String batchNumber = batchNumberCreator.createBatchNumber(batch);
            Map<String, Object> batchMap = new HashMap<>();
            batchMap.put("Batch Number", batchNumber);
            batchMap.put("Received Date", batch.getBatchDate());
            batchMap.put("Fruit Type", batch.getProductCode());
            batchMap.put("Batch Weight", batch.getBatchWeight());
            batchMap.put("Farm Code", batch.getOriginCode());

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(Paths.get(createFileName(batchNumber)).toFile(), batchMap);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String createFileName(String batchNumber) {
        return "src/main/resources/json/" + batchNumber;
    }
}
