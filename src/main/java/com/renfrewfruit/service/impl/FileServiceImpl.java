package com.renfrewfruit.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renfrewfruit.model.Batch;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.utility.BatchNumberCreator;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileServiceImpl implements FileService {

    public void createFile(Batch batch) {

        BatchNumberCreator batchNumberCreator = new BatchNumberCreator();

        try {
            String batchNumber = batchNumberCreator.createBatchNumber(batch);
            Map<String, Object> batchMap = new HashMap<>();
            batchMap.put("batchDate", batch.getBatchDate());
            batchMap.put("productCode", batch.getProductCode());
            batchMap.put("batchWeight", batch.getBatchWeight());
            batchMap.put("originCode", batch.getOriginCode());

            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(Paths.get(createFileName(batchNumber)).toFile(), batchMap);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String createFileName(String batchNumber) {
        return "src/main/resources/json/" + batchNumber + ".json";
    }
}
