package com.renfrewfruit.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renfrewfruit.model.Batch;
import com.renfrewfruit.model.Blackberry;
import com.renfrewfruit.model.Farm;
import com.renfrewfruit.model.Fruit;
import com.renfrewfruit.model.Gooseberry;
import com.renfrewfruit.model.Market;
import com.renfrewfruit.model.Price;
import com.renfrewfruit.model.Raspberry;
import com.renfrewfruit.model.Strawberry;
import com.renfrewfruit.model.Weight;
import com.renfrewfruit.service.BatchService;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.service.PricingService;
import com.renfrewfruit.service.SortingService;
import com.renfrewfruit.service.TransactionService;
import com.renfrewfruit.utility.BatchNumberCreator;
import com.renfrewfruit.utility.DateResolver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BatchServiceImpl implements BatchService {

  private final ObjectMapper mapper = new ObjectMapper();
  private final Scanner scanner = new Scanner(System.in);
  private final FileService fileService = new FileServiceImpl();
  private final SortingService sortingService = new SortingServiceImpl();
  private final PricingService pricingService = new PricingServiceImpl();
  private final TransactionService transactionService = new TransactionServiceImpl();
  private final BatchNumberCreator batchNumberCreator = new BatchNumberCreator();
  private final DateResolver dateResolver = new DateResolver();

  public void openMenu() {

    boolean startApplication = true;

    do {
      System.out.println("Welcome To Renfrewshire Soft Fruits Cooperative \n");
      System.out.print("Select An Option: \n");
      System.out.print("1. Create a New Batch \n2. List All Batches \n3. View Details of a Batch" +
          "\n4. Sort & Grade a Batch \n5. Payments \n6. Transaction Report \n7. Quit \n>");

      switch (scanner.nextInt()) {
        case 1:
          batchProcess();
          break;
        case 2:
          listAllBatches();
          break;
        case 3:
          batchDetails();
          break;
        case 4:
          gradeProcess();
          break;
        case 5:
          fruitPricingProcess();
          break;
        case 6:
          transactionReport();
          break;
        case 7:
          startApplication = false;
          System.out.println("Exiting Application\n");
          break;
        default:
          System.out.println("Invalid Selection\n");
      }
    } while (startApplication);
  }

  private void transactionReport() {

    System.out.println("TRANSACTION REPORT");
    System.out.print("Please Enter Transaction Date: ");
    String transactionDate = scanner.next();
    transactionService.generateReport(transactionDate);
  }

  public void batchProcess() {

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
      System.out.println("-----------------------");
      System.out.println("Today's Date: " + batch.getBatchDate());
      System.out
          .println("\nThis batch contains " + batch.getBatchWeight().getTotal() + "KG " + "of "
              + batch.getBatchFruit().getProductName() + " from farm number " + batch
              .getBatchOrigin().getFarmCode()
              + " received on " + batch.getBatchDate() + ". " + "Is this correct Y/N? \n>");

      switch (scanner.next()) {
        case "Y":
          validBatch = true;
          printDetails(batch, batchNumber);
          break;
        case "N":
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
    System.out.println("1. Strawberries\n2. Raspberries\n3. Blackberries\n4. Gooseberries");
    System.out.print("> ");

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
      System.out.println("\nEnter Batch Weight in kg's (Max Weight Is 100kg)");
      System.out.print("> ");
      batchWeight.setTotal(scanner.nextDouble());

      if (batchWeight.getTotal() < 1 || batchWeight.getTotal() > 100) {
        System.out.println("Invalid Batch Weight. Try Again.");
      }

    } while (batchWeight.getTotal() < 1 || batchWeight.getTotal() > 100);

    return batchWeight;
  }

  public void printDetails(Batch batch, String batchNumber) {

    System.out.println("Print Batch Details Y/N? ");
    System.out.println("> ");

    if (scanner.next().equalsIgnoreCase("Y")) {
      fileService.createFile(batch);
      System.out.println("Batch Number: " + batchNumber);
      System.out.println("Received Date: " + batch.getBatchDate());
      System.out.println("Fruit Type: " + batch.getBatchFruit().getProductName());
      System.out.println("Batch Weight: " + batch.getBatchWeight().getTotal() + "kg\n");
      System.out.println("Return To Main Menu? Y/N");

      if (scanner.next().equalsIgnoreCase("Y")) {
        openMenu();
      } else {
        System.exit(0);
      }

    } else {
      System.out.println("Batch Not Printed.");
    }
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
        try {
          Batch
              batch =
              mapper.readValue(Paths.get("src/main/resources/json/batches/" + b).toFile(),
                  Batch.class);
          System.out.print(b.substring(0, b.lastIndexOf(".")) + "\t");
          System.out.format("%15s", batch.getBatchFruit().getProductName() + "\t");
          System.out.print(batch.getBatchOrigin().getFarmCode() + "\t");
          System.out.print(batch.getBatchWeight().getTotal() + "kg" + "\t");
          System.out.print(batch.getBatchDate() + "\t");
          System.out.print("£" + calculateBatchTotal(batch) + "\n");

        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    } else {
      System.out.println("No Batches Available");
    }

    System.out.print("\nReturn To Main Menu? Y/N : ");
    if (scanner.next().equalsIgnoreCase("Y")) {
      openMenu();
    } else {
      System.exit(0);
    }
  }

  public void batchDetails() {

    System.out.print("Enter A Batch Number: ");

    try {
      String fileName = fileService.findBatchFile(scanner.next());
      Batch
          batch =
          mapper.readValue(Paths.get("src/main/resources/json/batches/" + fileName).toFile(),
              Batch.class);
      System.out.print(fileName.substring(0, fileName.lastIndexOf(".")) + "\t");
      System.out.print(batch.getBatchFruit().getProductName() + "\t");
      System.out.print(batch.getBatchOrigin().getFarmCode() + "\t");
      System.out.print(batch.getBatchWeight().getTotal() + "kg" + "\t");
      System.out.print(batch.getBatchDate() + "\t");
      System.out.println("£" + calculateBatchTotal(batch));
      sortingService.calculatePercentages(batch);

      System.out.println("Return To Main Menu? Y/N");

      if (scanner.next().equalsIgnoreCase("Y")) {
        openMenu();
      } else {
        System.exit(0);
      }

    } catch (NullPointerException | IOException ex) {
      ex.printStackTrace();
    }
  }

  public void gradeProcess() {

    System.out.print("Enter A Batch Number :");

    try {
      String fileName = fileService.findBatchFile(scanner.next());
      Batch
          batch =
          mapper.readValue(Paths.get("src/main/resources/json/batches/" + fileName).toFile(),
              Batch.class);
      sortingService.gradeBatch(batch, fileName);
    } catch (NullPointerException | IOException ex) {
      ex.printStackTrace();
    }
  }

  public void fruitPricingProcess() {

    Market market = fileService.retrieveMarket();

    System.out.println("\nSelect A Fruit To Price:");
    System.out.print("1. STRAWBERRIES\n2. RASPBERRIES\n3. BLACKBERRIES \n4. GOOSEBERRIES\n>");

    switch (scanner.nextInt()) {
      case 1:
        pricingService.priceStrawberries(market);
        break;
      case 2:
        pricingService.priceRaspberries(market);
        break;
      case 3:
        pricingService.priceBlackberries(market);
        break;
      case 4:
        pricingService.priceGooseberries(market);
        break;
      default:
        System.out.println("Invalid Selection");
    }
  }

  public double calculateBatchTotal(Batch batch) {

    return batch.getBatchValue().getGradeA()
        + batch.getBatchValue().getGradeB()
        + batch.getBatchValue().getGradeC();
  }
}
