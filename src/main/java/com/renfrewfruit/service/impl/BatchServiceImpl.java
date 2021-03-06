package com.renfrewfruit.service.impl;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

import static com.renfrewfruit.model.Constants.NO;
import static com.renfrewfruit.model.Constants.SEPARATOR;
import static com.renfrewfruit.model.Constants.YES;

import com.renfrewfruit.driver.Driver;
import com.renfrewfruit.model.Batch;
import com.renfrewfruit.model.Blackberry;
import com.renfrewfruit.model.Farm;
import com.renfrewfruit.model.Fruit;
import com.renfrewfruit.model.Gooseberry;
import com.renfrewfruit.model.Price;
import com.renfrewfruit.model.Raspberry;
import com.renfrewfruit.model.Strawberry;
import com.renfrewfruit.model.Weight;
import com.renfrewfruit.service.BatchService;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.service.SortingService;
import com.renfrewfruit.utility.DateFormatter;
import com.renfrewfruit.utility.UserInputValidator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The responsibility of this class is to create a batch from user input. The class can also display
 * details of existing batches.
 */
public class BatchServiceImpl implements BatchService {

  private final Scanner scanner;
  private final FileService fileService;
  private final SortingService sortingService;
  private final UserInputValidator validator;

  public BatchServiceImpl() {
    this.scanner = new Scanner(System.in);
    this.fileService = new FileServiceImpl();
    this.sortingService = new SortingServiceImpl();
    this.validator = new UserInputValidator();
  }

  /**
   * This function utilises a builder pattern to construct a batch. Once created the batch is then
   * printed to the screen for confirmation.
   */
  @Override
  public void processNewBatch() {
    final DateFormatter dateResolver = new DateFormatter();
    final StringBuilder sb = new StringBuilder();

    Batch batch =
        Batch.builder()
            .batchDate(dateResolver.processDate())
            .batchFruit(processFruitType())
            .batchWeight(processBatchWeight())
            .batchOrigin(processFarmNumber())
            .batchValue(new Price(0.0, 0.0, 0.0, 0.0))
            .build();
    batch.setBatchNumber(batch.createBatchNumber(batch));

    sb.append(SEPARATOR)
        .append("\nToday's Date: ")
        .append(batch.getBatchDate())
        .append("\nThis batch contains ")
        .append(batch.getBatchWeight().getTotal())
        .append("KG of ")
        .append(batch.getBatchFruit().getProductName())
        .append(" from farm number ")
        .append(batch.getBatchOrigin().getFarmCode())
        .append(" received on ")
        .append(batch.getBatchDate())
        .append(". Is this correct Y/N: \n>");
    System.out.print(sb);

    boolean validBatch = false;
    do {
      switch (scanner.next().toUpperCase()) {
        case YES:
          validBatch = true;
          createBatch(batch);
          break;
        case NO:
          System.out.println("No Problem. Let's Start Again.");
          processNewBatch();
          break;
        default:
          System.out.print("Invalid Selection. Enter Y/N. \n> ");
      }
    } while (!validBatch);
  }

  /**
   * This function takes user input for the farm number and returns a farm object.
   *
   * @return a farm object which will have a farm code set to the user input
   */
  @Override
  public Farm processFarmNumber() {
    Farm farm = new Farm();
    int farmNumber;
    do {
      System.out.println("\nEnter Farm Number (001 to 999) \n>");
      farmNumber = validator.getIntSelection();
      if (farmNumber < 1 || farmNumber > 999) {
        System.out.println("Invalid Farm Number. Try Again.");
      } else {
        farm.setFarmCode(farmNumber);
      }
    } while (farmNumber < 1 || farmNumber > 999);

    return farm;
  }

  /**
   * This function takes user input to confirm the fruit type of the batch and returns a fruit
   * object.
   *
   * @return an instance of a fruit subclass
   */
  @Override
  public Fruit processFruitType() {
    Fruit fruitType = new Fruit();
    System.out.println("\nSelect a Fruit Type: ");
    System.out.print(
        "1. Strawberries\n2. Raspberries\n3. Blackberries\n4. Gooseberries\n> ");

    switch (validator.getIntSelection()) {
      case 1:
        fruitType = new Strawberry();
        break;
      case 2:
        fruitType = new Raspberry();
        break;
      case 3:
        fruitType = new Blackberry();
        break;
      case 4:
        fruitType = new Gooseberry();
        break;
      default:
        System.out.println("Invalid Selection. Try Again.");
        processFruitType();
    }
    return fruitType;
  }

  /**
   * This function takes user input for the weight of the batch and returns a weight object.
   *
   * @return a weight object with the total weight set
   */
  @Override
  public Weight processBatchWeight() {
    Weight batchWeight = new Weight();
    do {
      System.out.print("\nEnter Batch Weight in kg's (Max Weight Is 100kg)\n> ");
      batchWeight.setTotal(validator.getIntSelection());

      if (batchWeight.getTotal() < 1 || batchWeight.getTotal() > 100) {
        System.out.println("Invalid Batch Weight. Try Again.");
      }
    } while (batchWeight.getTotal() < 1 || batchWeight.getTotal() > 100);

    return batchWeight;
  }

  /**
   * This function retrieves all existing batch files from the resources folder and displays them as
   * a list.
   */
  @Override
  public void listAllBatches() {
    File[] files = new File("src/main/resources/json/batches/").listFiles();
    List<String> fileNames = new ArrayList<>();
    System.out.println("Current Batches" + "\n" + SEPARATOR);

    if (files != null) {
      for (File file : files) {
        fileNames.add(file.getName());
      }
      fileNames.forEach(
          b -> {
            Batch batch = fileService.mapBatchFromFile(b);
            displayAllBatchDetails(b, batch);
          });
    } else {
      System.out.println("No Batches Available");
    }

    Driver driver = new Driver();
    driver.returnToMainMenu();
  }

  /**
   * This function allows the user to search for a batch number and display its details.
   */
  @Override
  public void batchDetails() {
    System.out.print("Enter A Batch Number: ");
    String fileName = fileService.getBatchFileName(scanner.next());
    Batch batch = fileService.mapBatchFromFile(fileName);
    if (batch == null) {
      System.out.println("Batch Could Not Be Found. Try Again.\n");
      batchDetails();
    } else {
      displaySelectedBatchDetails(fileName, batch);
      sortingService.calculatePercentages(batch);
      Driver driver = new Driver();
      driver.returnToMainMenu();
    }
  }

  /**
   * This function receives the user constructed batch and prints the details for confirmation. The
   * user can then ask the file service to create the batch file in the resources folder.
   *
   * @param batch - the user created batch to be confirmed for file creation
   */
  @Override
  public void createBatch(Batch batch) {
    System.out.print("Print Batch Details Y/N?\n> ");

    if (scanner.next().equalsIgnoreCase("Y")) {
      fileService.createBatchFile(batch);
      confirmNewBatchDetails(batch);

      if (scanner.next().equalsIgnoreCase(YES)) {
        Driver driver = new Driver();
        driver.openMenu();
      } else {
        System.exit(0);
      }
    } else {
      System.out.println("Batch Not Created.");
      Driver driver = new Driver();
      driver.openMenu();
    }
  }

  /**
   * This private function takes the filename and batch details then prints these to the console.
   *
   * @param b - the batch name including the extension which will be removed
   * @param batch - the batch with all of its details to be printed out
   */
  private void displayAllBatchDetails(String b, Batch batch) {
    System.out.print(b.substring(0, b.lastIndexOf(".")) + "\t");
    System.out.format("%15s", batch.getBatchFruit().getProductName() + "\t");
    System.out.print(batch.getBatchOrigin().getFarmCode() + "\t");
    System.out.print(batch.getBatchWeight().getTotal() + "kg" + "\t");
    System.out.print(batch.getBatchDate() + "\t");
    System.out.print("£" + batch.calculateBatchTotal(batch) + "\n");
  }

  /**
   * This private function is used when creating a new batch to confirm the details are valid.
   *
   * @param batch - the batch with the user input details for confirmation
   */
  private void confirmNewBatchDetails(Batch batch) {
    System.out.println("Batch Number: " + batch.getBatchNumber());
    System.out.println("Received Date: " + batch.getBatchDate());
    System.out.println("Fruit Type: " + batch.getBatchFruit().getProductName());
    System.out.println("Batch Weight: " + batch.getBatchWeight().getTotal() + "kg\n");
    System.out.println("Return To Main Menu? Y/N");
  }

  /**
   * This private function is used by the batchDetails method to print out the details of a specific
   * batch.
   *
   * @param fileName - the complete batch filename
   * @param batch - the batch with it's details for printing to console
   */
  private void displaySelectedBatchDetails(String fileName, Batch batch) {
    System.out.print(fileName.substring(0, fileName.lastIndexOf(".")) + "\t");
    System.out.print(batch.getBatchFruit().getProductName() + "\t");
    System.out.print(batch.getBatchOrigin().getFarmCode() + "\t");
    System.out.print(batch.getBatchWeight().getTotal() + "kg" + "\t");
    System.out.print(batch.getBatchDate() + "\t");
    System.out.println("£" + batch.calculateBatchTotal(batch));
    System.out.println(SEPARATOR);
  }
}
