package com.renfrewfruit.service.impl;

import static com.renfrewfruit.model.Constants.BLACKBERRIES;
import static com.renfrewfruit.model.Constants.GOOSEBERRIES;
import static com.renfrewfruit.model.Constants.RASPBERRIES;
import static com.renfrewfruit.model.Constants.SEPARATOR;
import static com.renfrewfruit.model.Constants.STRAWBERRIES;

import com.renfrewfruit.driver.Driver;
import com.renfrewfruit.model.Batch;
import com.renfrewfruit.model.Fruit;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.service.TransactionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TransactionServiceImpl implements TransactionService {

  private final FileService fileService;
  private final Scanner scanner;
  private final HashMap<Fruit, Batch> report;
  private double totalGradeA;
  private double totalGradeB;
  private double totalGradeC;
  private double totalRejected;
  private double totalPaid;

  public TransactionServiceImpl() {
    this.fileService = new FileServiceImpl();
    this.scanner = new Scanner(System.in);
    this.report = new HashMap<>();
    this.totalGradeA = 0.0;
    this.totalGradeB = 0.0;
    this.totalGradeC = 0.0;
    this.totalRejected = 0.0;
    this.totalPaid = 0.0;
  }

  public void generateReport(String date) {
    List<Batch> batches = fileService.findTransactionFiles(date);
    List<Batch> strawberries = new ArrayList<>();
    List<Batch> raspberries = new ArrayList<>();
    List<Batch> blackberries = new ArrayList<>();
    List<Batch> gooseberries = new ArrayList<>();

    System.out.println("\n" + batches.size() + " Transactions Found For This Day");
    System.out.println(SEPARATOR);
    System.out.println("FRUIT\t GRADE A\t GRADE B\t GRADE C\t REJECTED\t TOTAL PAID");
    System.out.println(SEPARATOR);

    for (Batch batch : batches) {
      if (batch.getBatchFruit().getProductName().equalsIgnoreCase(STRAWBERRIES)) {
        strawberries.add(batch);
      } else if (batch.getBatchFruit().getProductName().equalsIgnoreCase(RASPBERRIES)) {
        raspberries.add(batch);
      } else if (batch.getBatchFruit().getProductName().equalsIgnoreCase(BLACKBERRIES)) {
        blackberries.add(batch);
      } else if (batch.getBatchFruit().getProductName().equalsIgnoreCase(GOOSEBERRIES)) {
        gooseberries.add(batch);
      } else {
        System.out.println("Invalid Product Name");
      }
    }
    generateStrawberryReport(strawberries);
    generateRaspberryReport(raspberries);
    generateBlackberryReport(blackberries);
    generateGooseberryReport(gooseberries);

    Driver driver = new Driver();
    driver.returnToMainMenu();
  }


  private void generateStrawberryReport(List<Batch> strawberries) {
    StringBuilder builder = new StringBuilder();
    calculateBatchTotals(strawberries);
    System.out.println(builder.append(STRAWBERRIES)
        .append("\t").append(totalGradeA).append("\t").append(totalGradeB)
        .append("\t").append(totalGradeC).append("\t").append(totalRejected)
        .append("\t").append(totalPaid).append("\n"));
  }

  private void generateRaspberryReport(List<Batch> raspberries) {
    StringBuilder builder = new StringBuilder();
    calculateBatchTotals(raspberries);
    System.out.println(builder.append(RASPBERRIES)
        .append("\t").append(totalGradeA).append("\t").append(totalGradeB)
        .append("\t").append(totalGradeC).append("\t").append(totalRejected)
        .append("\t").append(totalPaid).append("\n"));
  }

  private void generateBlackberryReport(List<Batch> blackberries) {
    StringBuilder builder = new StringBuilder();
    calculateBatchTotals(blackberries);
    System.out.println(builder.append(BLACKBERRIES)
        .append("\t").append(totalGradeA).append("\t").append(totalGradeB)
        .append("\t").append(totalGradeC).append("\t").append(totalRejected)
        .append("\t").append(totalPaid).append("\n"));
  }

  private void generateGooseberryReport(List<Batch> gooseberries) {
    StringBuilder builder = new StringBuilder();
    calculateBatchTotals(gooseberries);
    System.out.println(builder.append(GOOSEBERRIES)
        .append("\t").append(totalGradeA).append("\t").append(totalGradeB)
        .append("\t").append(totalGradeC).append("\t").append(totalRejected)
        .append("\t").append(totalPaid).append("\n"));
  }

  public void calculateBatchTotals(List<Batch> batches) {
    batches.forEach(batch -> {
      totalGradeA = totalGradeA + batch.getBatchWeight().getGradeA();
      totalGradeB = totalGradeB + batch.getBatchWeight().getGradeB();
      totalGradeC = totalGradeC + batch.getBatchWeight().getGradeC();
      totalRejected = totalRejected + batch.getBatchWeight().getRejected();
      totalPaid = totalPaid + batch.getBatchValue().getTotal();
    });
  }
}
