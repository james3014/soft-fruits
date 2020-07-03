package com.renfrewfruit.service.impl;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

import static com.renfrewfruit.model.Constants.DIRECTORY;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renfrewfruit.exception.SoftFruitException;
import com.renfrewfruit.model.Batch;
import com.renfrewfruit.model.Market;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.utility.DateFormatter;

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

/**
 * The responsibility of this class is to provide file IO functionality to the application. The
 * class also handles json mapping via Jackson and the ObjectMapper class. This is expensive to use
 * so this class holds the only instance of it.
 */
public class FileServiceImpl implements FileService {

  private final ObjectMapper mapper;
  private final DateFormatter dateFormatter;

  public FileServiceImpl() {
    this.mapper = new ObjectMapper();
    this.dateFormatter = new DateFormatter();
  }

  /**
   * This function creates a hashmap of the batch details passed to it. These are then written to a
   * json file using ObjectMapper.
   *
   * @param batch - the new batch details to be written to a new batch file
   */
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
      mapper
          .writerWithDefaultPrettyPrinter()
          .writeValue(Paths.get(createFileName(batch.getBatchNumber())).toFile(), batchMap);
    } catch (Exception ex) {
      throw new SoftFruitException("The batch could not be written a JSON file");
    }
  }

  /**
   * This function allows an existing batch file to be overwritten with new data from the batch
   * parameter. A new file does not need to be created for the update to work.
   *
   * @param batch - the updated batch details to be written to the batch file
   */
  @Override
  public void updateBatchFile(Batch batch) {
    try {
      mapper
          .writerWithDefaultPrettyPrinter()
          .writeValue(new File(DIRECTORY + batch.getBatchNumber() + ".json"), batch);
    } catch (IOException ex) {
      throw new SoftFruitException("The batch could not be written a JSON file");
    }
  }

  /**
   * This function allows the user to update the existing marketplace with new prices. These are
   * passed as a market object which holds the date and fruit values.
   *
   * @param market - the new fruit prices to be written to the marketplace file
   */
  @Override
  public void updateMarketFile(Market market) {
    try {
      mapper
          .writerWithDefaultPrettyPrinter()
          .writeValue(new File("src/main/resources/json/market/" + "Pricing.json"), market);
    } catch (IOException ex) {
      throw new SoftFruitException("The batch could not be written a JSON file");
    }
  }

  /**
   * This function performs a check on whether the current marketplace file has today's date.
   *
   * @return a true of false value depending on whether the marketplace is up to date
   */
  @Override
  public boolean checkCurrentMarketplaceDate() {
    Market market = retrieveMarket();
    String today = dateFormatter.processDate();
    return market.getDate().equals(today);
  }

  /**
   * This function is used complete a search on a batch file name. The filename is passed by the
   * user and the file extension .json is appended.
   *
   * @param batchName - user provided batch name minus the file format
   * @return the complete filename including .json
   */
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

  /**
   * This function is used to retrieve an existing batch by searching on the filename. ObjectMapper
   * is then used to map the json file to a batch object to be returned.
   *
   * @param filename - user provided filename for the search
   * @return the batch matching the filename
   */
  @Override
  public Batch mapBatchFromFile(String filename) {
    try {
      return mapper.readValue(Paths.get(DIRECTORY + filename).toFile(), Batch.class);
    } catch (IOException e) {
      return null;
    }
  }

  /**
   * This function is used to find all transactions that took place on a specific date. This is
   * achieved by collecting all existing batch filenames and matching the date string. If found, the
   * matching files are then read by ObjectMapper into Batch objects and added a list.
   *
   * @param date - user provided date as a string
   * @return a list of batches with the matching date
   * @link https://stackoverflow.com/questions/54668332/ fastest-method-to-find-a-filename-from-a-pattern-in-nio-or-file-object-in-java
   */
  @Override
  public List<Batch> findTransactionFiles(String date) {
    List<Batch> batches = new ArrayList<>();
    List<String> fileNames;
    Path path = Paths.get(DIRECTORY);

    try {
      fileNames =
          Files.list(path)
              .map(p -> p.getFileName().toString())
              .filter(s -> s.contains(date))
              .collect(Collectors.toList());
    } catch (IOException ex) {
      throw new SoftFruitException("An error occurred whilst creating the list of file names");
    }

    fileNames.forEach(
        f -> {
          try {
            batches.add(mapper.readValue(Paths.get(DIRECTORY + f).toFile(), Batch.class));
          } catch (IOException e) {
            throw new SoftFruitException("An error occurred whilst adding a batch into the list");
          }
        });
    return batches;
  }

  /**
   * This function is used to retrieve the existing marketplace file and map the current fruit
   * prices into a market object.
   *
   * @return the market object with the current prices
   */
  @Override
  public Market retrieveMarket() {
    Market market;
    try {
      market =
          mapper.readValue(
              Paths.get("src/main/resources/json/market/" + "Pricing.json").toFile(), Market.class);
    } catch (IOException ex) {
      throw new SoftFruitException("An error occurred whilst creating the market object");
    }
    return market;
  }

  /**
   * This function is used to create a brand new marketplace only if one does not currently exist.
   *
   * @param market - user input fruit prices to be written to file
   */
  @Override
  public void createInitialMarketFile(Market market) {
    try {
      mapper
          .writerWithDefaultPrettyPrinter()
          .writeValue(new File("src/main/resources/json/market/Pricing.json"), market);
    } catch (IOException ex) {
      throw new SoftFruitException("An error occurred whilst creating the pricing file");
    }
  }

  /**
   * This function is used to create a complete filename including the extension .json.
   *
   * @param batchNumber - the batch number which will exist as the filename
   * @return the complete batch filename including extension
   */
  @Override
  public String createFileName(String batchNumber) {
    return DIRECTORY + batchNumber + ".json";
  }
}
