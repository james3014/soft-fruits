package com.renfrewfruit.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renfrewfruit.model.Batch;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.utility.BatchNumberCreator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileServiceImpl implements FileService {

    private final ObjectMapper mapper = new ObjectMapper();

    public void createFile(Batch batch) {

        BatchNumberCreator batchNumberCreator = new BatchNumberCreator();

        try {
            String batchNumber = batchNumberCreator.createBatchNumber(batch);
            Map<String, Object> batchMap = new HashMap<>();
            batchMap.put("batchNumber", batchNumber);
            batchMap.put("batchDate", batch.getBatchDate());
            batchMap.put("batchFruit", batch.getBatchFruit());
            batchMap.put("batchWeight", batch.getBatchWeight());
            batchMap.put("batchOrigin", batch.getBatchOrigin());

            mapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(createFileName(batchNumber)).toFile(), batchMap);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String createFileName(String batchNumber) {
        return "src/main/resources/json/" + batchNumber + ".json";
    }

    public void updateFile(Batch batch) {

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(
                    new File("src/main/resources/json/" + batch.getBatchNumber() + ".json"), batch);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String findFile(String batchName) {

        File[] files = new File("src/main/resources/json/").listFiles();
        String fileNameFound = null;
        batchName = batchName + ".json";

        if (files != null) {
            for (File file : files) {
                if (batchName.equalsIgnoreCase(file.getName())) fileNameFound = batchName;
            }
        } else System.out.println("No Batches Recorded");

        return fileNameFound;
    }
}
