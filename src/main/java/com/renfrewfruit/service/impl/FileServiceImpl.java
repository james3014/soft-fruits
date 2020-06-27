package com.renfrewfruit.service.impl;

import static com.renfrewfruit.model.Constants.DIRECTORY;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renfrewfruit.model.Batch;
import com.renfrewfruit.model.Market;
import com.renfrewfruit.service.FileService;

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

  private final ObjectMapper mapper;

  public FileServiceImpl() {
    this.mapper = new ObjectMapper();
  }

  @Override
  public void createBatchFile(Batch batch) {
    try {
      Map<String, Object> batchMap = new HashMap<>();
      batchMap.put("batchNumber", batch.getBatchNumber());
      batchMap.put("batchDate", batch.getBatchDate());
      batchMap.put("batchFruit", batch.getBatchFruit());
      batchMap.put("batchWeight", batch.getBatchWeight());
      batchMap.put("batchOrigin", batch.getBatchOrigin());
      batchMap.put("batchValue", batch.getBatchValue());
      mapper.writerWithDefaultPrettyPrinter()
          .writeValue(Paths.get(createFileName(batch.getBatchNumber())).toFile(), batchMap);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void updateBatchFile(Batch batch) {
    try {
      mapper.writerWithDefaultPrettyPrinter().writeValue(
          new File(DIRECTORY + batch.getBatchNumber() + ".json"), batch);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void updateMarketFile(Market market) {
    try {
      mapper.writerWithDefaultPrettyPrinter().writeValue(
          new File("src/main/resources/json/market/" + "Pricing.json"), market);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public String getBatchFileName(String batchName) {
    File[] files = new File(DIRECTORY).listFiles();
    String fileNameFound = "";
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

  @Override
  public Batch mapBatchFromFile(String filename) {
    try {
      return mapper.readValue(Paths.get(DIRECTORY + filename).toFile(), Batch.class);
    } catch (IOException e) {
      System.out.println("File name " + filename + " has no batches associated with it.");
    }
    return new Batch();
  }

  /**
   * Reference : https://stackoverflow.com/questions/54668332/ fastest-method-to-find-a-filename-from-a-pattern-in-nio-or-file-object-in-java
   */
  @Override
  public List<Batch> findTransactionFiles(String date) {
    List<Batch> batches = new ArrayList<>();
    List<String> fileNames = new ArrayList<>();
    Path path = Paths.get(DIRECTORY);

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
        batches.add(mapper.readValue(Paths.get(DIRECTORY + f).toFile(), Batch.class));
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    return batches;
  }

  @Override
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

  @Override
  public void createInitialMarketFile(Market market) {
    try {
      mapper.writerWithDefaultPrettyPrinter().writeValue(
          new File("src/main/resources/json/market/Pricing.json"), market);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public String createFileName(String batchNumber) {
    return DIRECTORY + batchNumber + ".json";
  }
}
