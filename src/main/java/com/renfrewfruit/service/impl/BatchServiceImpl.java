package com.renfrewfruit.service.impl;

import static com.renfrewfruit.model.Constants.NO;
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
import com.renfrewfruit.utility.BatchNumberCreator;
import com.renfrewfruit.utility.DateResolver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BatchServiceImpl implements BatchService {

  private final Scanner scanner;
  private final BatchNumberCreator batchNumberCreator;
  private final FileService fileService;
  private final SortingService sortingService;

  public BatchServiceImpl() {
    this.scanner = new Scanner(System.in);
    this.batchNumberCreator = new BatchNumberCreator();
    this.fileService = new FileServiceImpl();
    this.sortingService = new SortingServiceImpl();
  }

  public void batchProcess() {
    final DateResolver dateResolver = new DateResolver();
    final StringBuilder sb = new StringBuilder();
    Batch batch = Batch.builder()
        .batchDate(dateResolver.processDate())
        .batchFruit(processFruitType())
        .batchWeight(processBatchWeight())
        .batchOrigin(processFarmNumber())
        .batchValue(new Price(0.0, 0.0, 0.0, 0.0))
        .build();
    String batchNumber = batchNumberCreator.createBatchNumber(batch);

    boolean validBatch = false;
    do {
      sb.append("-----------------------")
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

      switch (scanner.next().toUpperCase()) {
        case YES:
          validBatch = true;
          createBatch(batch, batchNumber);
          break;
        case NO:
          System.out.println("No Problem. Let's Start Again.");
          batchProcess();
          break;
      }
    } while (!validBatch);
  }

  public Farm processFarmNumber() {
    Farm farm = new Farm();
    int farmNumber;
    do {
      System.out.println("\nEnter Farm Number (001 to 999) \n>");
      farmNumber = scanner.nextInt();

      if (farmNumber < 1 || farmNumber > 999) {
        System.out.println("Invalid Farm Number. Try Again.");
      } else {
        farm.setFarmCode(farmNumber);
      }
    } while (farmNumber < 1 || farmNumber > 999);

    return farm;
  }

  public Fruit processFruitType() {
    Fruit fruitType = null;
    System.out.println("\nSelect a Fruit Type: ");
    System.out.print("1. Strawberries\n2. Raspberries\n3. Blackberries\n4. Gooseberries\n> ");

    switch (scanner.nextInt()) {
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

  public Weight processBatchWeight() {
    Weight batchWeight = new Weight();
    do {
      System.out.print("\nEnter Batch Weight in kg's (Max Weight Is 100kg)\n> ");
      batchWeight.setTotal(scanner.nextDouble());

      if (batchWeight.getTotal() < 1 || batchWeight.getTotal() > 100) {
        System.out.println("Invalid Batch Weight. Try Again.");
      }
    } while (batchWeight.getTotal() < 1 || batchWeight.getTotal() > 100);

    return batchWeight;
  }

  public void listAllBatches() {
    File[] files = new File("src/main/resources/json/batches/").listFiles();
    List<String> fileNames = new ArrayList<>();

    System.out.println("Current Batches");
    System.out.println("---------------");

    if (files != null) {
      for (File file : files) {
        fileNames.add(file.getName());
      }
      fileNames.forEach(b -> {
        Batch batch = fileService.mapBatchFromFile(b);
        System.out.print(b.substring(0, b.lastIndexOf(".")) + "\t");
        System.out.format("%15s", batch.getBatchFruit().getProductName() + "\t");
        System.out.print(batch.getBatchOrigin().getFarmCode() + "\t");
        System.out.print(batch.getBatchWeight().getTotal() + "kg" + "\t");
        System.out.print(batch.getBatchDate() + "\t");
        System.out.print("£" + calculateBatchTotal(batch) + "\n");
      });
    } else {
      System.out.println("No Batches Available");
    }
    Driver driver = new Driver();
    driver.returnToMainMenu();
  }

  public void batchDetails() {
    System.out.print("Enter A Batch Number: ");
    String fileName = fileService.getBatchFileName(scanner.next());
    Batch batch = fileService.mapBatchFromFile(fileName);
    System.out.print(fileName.substring(0, fileName.lastIndexOf(".")) + "\t");
    System.out.print(batch.getBatchFruit().getProductName() + "\t");
    System.out.print(batch.getBatchOrigin().getFarmCode() + "\t");
    System.out.print(batch.getBatchWeight().getTotal() + "kg" + "\t");
    System.out.print(batch.getBatchDate() + "\t");
    System.out.println("£" + calculateBatchTotal(batch));
    sortingService.calculatePercentages(batch);
    Driver driver = new Driver();
    driver.returnToMainMenu();
  }

  public void createBatch(Batch batch, String batchNumber) {
    System.out.println("Print Batch Details Y/N?\n> ");

    if (scanner.next().equalsIgnoreCase("Y")) {
      fileService.createBatchFile(batch);
      System.out.println("Batch Number: " + batchNumber);
      System.out.println("Received Date: " + batch.getBatchDate());
      System.out.println("Fruit Type: " + batch.getBatchFruit().getProductName());
      System.out.println("Batch Weight: " + batch.getBatchWeight().getTotal() + "kg\n");
      System.out.println("Return To Main Menu? Y/N");

      if (scanner.next().equalsIgnoreCase(YES)) {
        Driver driver = new Driver();
        driver.openMenu();
      } else {
        System.exit(0);
      }
    } else {
      System.out.println("Batch Not Printed.");
    }
  }

  public double calculateBatchTotal(Batch batch) {
    return batch.getBatchValue().getGradeA()
        + batch.getBatchValue().getGradeB()
        + batch.getBatchValue().getGradeC();
  }
}
