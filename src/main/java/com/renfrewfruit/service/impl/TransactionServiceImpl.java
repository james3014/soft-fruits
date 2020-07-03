package com.renfrewfruit.service.impl;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

import static com.renfrewfruit.model.Constants.BLACKBERRIES;
import static com.renfrewfruit.model.Constants.GOOSEBERRIES;
import static com.renfrewfruit.model.Constants.RASPBERRIES;
import static com.renfrewfruit.model.Constants.SEPARATOR;
import static com.renfrewfruit.model.Constants.STRAWBERRIES;

import com.renfrewfruit.driver.Driver;
import com.renfrewfruit.model.Batch;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.service.TransactionService;

import java.util.ArrayList;
import java.util.List;

/**
 * The responsibility of this class is to generate transaction reports for the user for a specific
 * date that they can search on. The report includes all batches fully graded on that date and will
 * provide complete details on prices and weights.
 */
public class TransactionServiceImpl implements TransactionService {

  private final FileService fileService;
  private double totalGradeA;
  private double totalGradeB;
  private double totalGradeC;
  private double totalRejected;
  private double totalPaid;

  public TransactionServiceImpl() {
    this.fileService = new FileServiceImpl();
    this.totalGradeA = 0.0;
    this.totalGradeB = 0.0;
    this.totalGradeC = 0.0;
    this.totalRejected = 0.0;
    this.totalPaid = 0.0;
  }

  /**
   * This function operates as the main driver within the class for generating reports. This is
   * achieved by collecting a list of all batches generated on the date entered by the user. These
   * are then sorted into individual fruit list objects which can then be processed individually.
   *
   * @param date - the user provided date to be searched for transactions
   */
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

  /**
   * This private function is used to generate a transaction report for strawberries. It passes a
   * list of strawberry batches and the fruit name as a string to the generateFruitReport method.
   *
   * @param strawberries - a list of strawberry batches
   */
  private void generateStrawberryReport(List<Batch> strawberries) {
    generateFruitReport(strawberries, STRAWBERRIES);
  }

  /**
   * This private function is used to generate a transaction report for strawberries. It passes a
   * list of raspberry batches and the fruit name as a string to the generateFruitReport method.
   *
   * @param raspberries - a list of raspberry batches
   */
  private void generateRaspberryReport(List<Batch> raspberries) {
    generateFruitReport(raspberries, RASPBERRIES);
  }

  /**
   * This private function is used to generate a transaction report for strawberries. It passes a
   * list of blackberries batches and the fruit name as a string to the generateFruitReport method.
   * *
   *
   * @param blackberries - a list of blackberry batches
   */
  private void generateBlackberryReport(List<Batch> blackberries) {
    generateFruitReport(blackberries, BLACKBERRIES);
  }

  /**
   * This private function is used to generate a transaction report for strawberries. It passes a
   * list of gooseberry batches and the fruit name as a string to the generateFruitReport method. *
   *
   * @param gooseberries - a list of gooseberry batches
   */
  private void generateGooseberryReport(List<Batch> gooseberries) {
    generateFruitReport(gooseberries, GOOSEBERRIES);
  }

  /**
   * This function uses a string builder object to generate a specific fruits transaction report.
   *
   * @param fruitList - the list of a specific fruits batches
   * @param fruitName - a string constant of the fruits name
   */
  private void generateFruitReport(List<Batch> fruitList, String fruitName) {
    StringBuilder builder = new StringBuilder();
    calculateBatchTotals(fruitList);
    System.out.println(
        builder
            .append(fruitName)
            .append("\t")
            .append(totalGradeA)
            .append("\t")
            .append(totalGradeB)
            .append("\t")
            .append(totalGradeC)
            .append("\t")
            .append(totalRejected)
            .append("\t")
            .append(totalPaid)
            .append("\n"));
  }

  /**
   * This function is responsible for carrying out the total pricing calculations for all of the
   * batches that were found on the user selected date. This is achieved by looping through the list
   * of batches and created a running total for each attribute of the batch object.
   *
   * @param batches - a list of batches of a specific fruit
   */
  private void calculateBatchTotals(List<Batch> batches) {
    batches.forEach(
        batch -> {
          totalGradeA = totalGradeA + batch.getBatchWeight().getGradeA();
          totalGradeB = totalGradeB + batch.getBatchWeight().getGradeB();
          totalGradeC = totalGradeC + batch.getBatchWeight().getGradeC();
          totalRejected = totalRejected + batch.getBatchWeight().getRejected();
          totalPaid = totalPaid + batch.getBatchValue().getTotal();
        });
  }
}
