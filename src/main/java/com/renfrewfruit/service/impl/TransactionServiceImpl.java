package com.renfrewfruit.service.impl;

import com.renfrewfruit.model.Batch;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.service.TransactionService;

import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

  private final FileService fileService = new FileServiceImpl();

  public void generateReport(String date) {

    List<Batch> batches = fileService.findTransactionFiles(date);

    List<Batch> strawberries = new ArrayList<>();
    List<Batch> raspberries = new ArrayList<>();
    List<Batch> blackberries = new ArrayList<>();
    List<Batch> gooseberries = new ArrayList<>();

    System.out.println(batches.size() + " Transactions Found For This Day");
    System.out.println("DAILY TOTAL");
    System.out.println("FRUIT\t GRADE A\t GRADE B\t GRADE C\t REJECTED\t TOTAL PAID");

    for (Batch batch : batches) {
      if (batch.getBatchFruit().getProductName().equalsIgnoreCase("Strawberries")) {
        strawberries.add(batch);
      } else if (batch.getBatchFruit().getProductName().equalsIgnoreCase("Raspberries")) {
        raspberries.add(batch);
      } else if (batch.getBatchFruit().getProductName().equalsIgnoreCase("Blackberries")) {
        blackberries.add(batch);
      } else if (batch.getBatchFruit().getProductName().equalsIgnoreCase("Gooseberries")) {
        gooseberries.add(batch);
      } else {
        System.out.println("Invalid Product Name");
      }
    }

    generateStrawberryReport(strawberries);
    generateRaspberryReport(raspberries);
    generateBlackberryReport(blackberries);
    generateGooseberryReport(gooseberries);
  }

  public void generateStrawberryReport(List<Batch> strawberries) {

    double totalGradeA = 0.0;
    double totalGradeB = 0.0;
    double totalGradeC = 0.0;
    double totalRejected = 0.0;
    double totalPaid = 0.0;

    for (Batch strawberry : strawberries) {
      totalGradeA = totalGradeA + strawberry.getBatchWeight().getGradeA();
      totalGradeB = totalGradeB + strawberry.getBatchWeight().getGradeB();
      totalGradeC = totalGradeC + strawberry.getBatchWeight().getGradeC();
      totalRejected = totalRejected + strawberry.getBatchWeight().getRejected();
      totalPaid = totalPaid + strawberry.getBatchValue().getTotal();
    }

    System.out.println("STRAWBERRIES\t" + totalGradeA + "\t" + totalGradeB + "\t"
        + totalGradeC + "\t" + totalRejected + "\t" + totalPaid + "\n");
  }

  private void generateRaspberryReport(List<Batch> raspberries) {

    double totalGradeA = 0.0;
    double totalGradeB = 0.0;
    double totalGradeC = 0.0;
    double totalRejected = 0.0;
    double totalPaid = 0.0;

    for (Batch raspberry : raspberries) {
      totalGradeA = totalGradeA + raspberry.getBatchWeight().getGradeA();
      totalGradeB = totalGradeB + raspberry.getBatchWeight().getGradeB();
      totalGradeC = totalGradeC + raspberry.getBatchWeight().getGradeC();
      totalRejected = totalRejected + raspberry.getBatchWeight().getRejected();
      totalPaid = totalPaid + raspberry.getBatchValue().getTotal();
    }

    System.out.println("RASPBERRIES\t" + totalGradeA + "\t" + totalGradeB + "\t"
        + totalGradeC + "\t" + totalRejected + "\t" + totalPaid + "\n");
  }

  private void generateBlackberryReport(List<Batch> blackberries) {

    double totalGradeA = 0.0;
    double totalGradeB = 0.0;
    double totalGradeC = 0.0;
    double totalRejected = 0.0;
    double totalPaid = 0.0;

    for (Batch blackberry : blackberries) {
      totalGradeA = totalGradeA + blackberry.getBatchWeight().getGradeA();
      totalGradeB = totalGradeB + blackberry.getBatchWeight().getGradeB();
      totalGradeC = totalGradeC + blackberry.getBatchWeight().getGradeC();
      totalRejected = totalRejected + blackberry.getBatchWeight().getRejected();
      totalPaid = totalPaid + blackberry.getBatchValue().getTotal();
    }

    System.out.println("BLACKBERRIES\t" + totalGradeA + "\t" + totalGradeB + "\t"
        + totalGradeC + "\t" + totalRejected + "\t" + totalPaid + "\n");
  }

  private void generateGooseberryReport(List<Batch> gooseberries) {

    double totalGradeA = 0.0;
    double totalGradeB = 0.0;
    double totalGradeC = 0.0;
    double totalRejected = 0.0;
    double totalPaid = 0.0;

    for (Batch gooseberry : gooseberries) {
      totalGradeA = totalGradeA + gooseberry.getBatchWeight().getGradeA();
      totalGradeB = totalGradeB + gooseberry.getBatchWeight().getGradeB();
      totalGradeC = totalGradeC + gooseberry.getBatchWeight().getGradeC();
      totalRejected = totalRejected + gooseberry.getBatchWeight().getRejected();
      totalPaid = totalPaid + gooseberry.getBatchValue().getTotal();
    }

    System.out.println("GOOSEBERRIES\t" + totalGradeA + "\t" + totalGradeB + "\t"
        + totalGradeC + "\t" + totalRejected + "\t" + totalPaid + "\n");
  }


}
