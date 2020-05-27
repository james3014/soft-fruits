package com.renfrewfruit.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renfrewfruit.model.Batch;
import com.renfrewfruit.model.Market;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.utility.BatchNumberCreator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileServiceImpl implements FileService {

  private final ObjectMapper mapper = new ObjectMapper();
  private final String directory = "src/main/resources/json/batches/";

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
      batchMap.put("batchValue", batch.getBatchValue());

      mapper.writerWithDefaultPrettyPrinter()
          .writeValue(Paths.get(createFileName(batchNumber)).toFile(), batchMap);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void updateBatchFile(Batch batch) {

    try {
      mapper.writerWithDefaultPrettyPrinter().writeValue(
          new File(directory + batch.getBatchNumber() + ".json"), batch);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void updateMarketFile(Market market) {

    try {
      mapper.writerWithDefaultPrettyPrinter().writeValue(
          new File("src/main/resources/json/market/" + "Pricing.json"), market);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public String findBatchFile(String batchName) {

    File[] files = new File(directory).listFiles();
    String fileNameFound = null;
    batchName = batchName + ".json";

    if (files != null) {
      for (File file : files) {
        if (batchName.equalsIgnoreCase(file.getName())) {
          fileNameFound = batchName;
        }
      }
    } else {
      System.out.println("No Batches Recorded");
    }
    return fileNameFound;
  }

  /**
   * Reference : https://stackoverflow.com/questions/54668332/
   * fastest-method-to-find-a-filename-from-a-pattern-in-nio-or-file-object-in-java
   */
  public List<Batch> findTransactionFiles(String date) {

    List<Batch> batches = new ArrayList<>();
    List<String> fileNames = new ArrayList<>();
    Path path = Paths.get(directory);

    try {
      fileNames = Files.list(path)
          .map(p -> p.getFileName().toString())
          .filter(s -> s.contains(date))
          .collect(Collectors.toList());
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    fileNames.forEach(f -> {
      try {
        batches.add(mapper.readValue(Paths.get(directory + f).toFile(), Batch.class));
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    return batches;
  }

  public Market retrieveMarket() {

    Market market = new Market();
    try {
      market = mapper.readValue(
          Paths.get("src/main/resources/json/market/" + "Pricing.json").toFile(), Market.class);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return market;
  }

  public String createFileName(String batchNumber) {
    return directory + batchNumber + ".json";
  }
}
