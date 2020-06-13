package com.renfrewfruit.service.impl;

import static com.renfrewfruit.model.Constants.BLACKBERRIES;
import static com.renfrewfruit.model.Constants.GOOSEBERRIES;
import static com.renfrewfruit.model.Constants.RASPBERRIES;
import static com.renfrewfruit.model.Constants.STRAWBERRIES;
import static com.renfrewfruit.model.Constants.YES;

import com.renfrewfruit.model.Batch;
import com.renfrewfruit.model.Market;
import com.renfrewfruit.model.Price;
import com.renfrewfruit.model.Weight;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.service.SortingService;

import java.text.DecimalFormat;
import java.util.Scanner;

public class SortingServiceImpl implements SortingService {

  private final FileService fileService;
  private final Market marketPlace;
  private final Scanner scanner;

  public SortingServiceImpl() {
    this.fileService = new FileServiceImpl();
    this.marketPlace = fileService.retrieveMarket();
    this.scanner = new Scanner(System.in);
  }

  @Override
  public void gradeBatch(Batch batch, String fileName) {
    String sb = "Batch contains "
        + batch.getBatchWeight().getTotal()
        + "kg of "
        + batch.getBatchFruit().getProductName()
        + " from farm number "
        + batch.getBatchOrigin().getFarmCode()
        + " received on "
        + batch.getBatchDate();
    System.out.println(sb);

    System.out.print("\nEnter percentage of GRADE A fruit in batch: ");
    batch.getBatchFruit().setGradeA(scanner.nextInt());
    System.out.print("\nEnter percentage of GRADE B fruit in batch: ");
    batch.getBatchFruit().setGradeB(scanner.nextInt());
    System.out.print("\nEnter percentage of GRADE C fruit in batch: ");
    batch.getBatchFruit().setGradeC(scanner.nextInt());
    System.out.print("\nEnter percentage of rejected fruit in batch: ");
    batch.getBatchFruit().setRejected(scanner.nextInt());
    System.out.print("\nConfirm grading details are correct Y/N: ");

    if (scanner.next().equalsIgnoreCase(YES)) {
      calculateBatchDetails(batch);
      System.out.println("Grading Details Added");
    } else {
      System.out.println("Press Any Key To Return To Main Menu");
    }
  }

  @Override
  public void calculatePercentages(Batch batch) {
    DecimalFormat df = new DecimalFormat("#.##");
    double
        percentageA =
        (batch.getBatchWeight().getTotal() / 100) * batch.getBatchFruit().getGradeA();
    double
        percentageB =
        (batch.getBatchWeight().getTotal() / 100) * batch.getBatchFruit().getGradeB();
    double
        percentageC =
        (batch.getBatchWeight().getTotal() / 100) * batch.getBatchFruit().getGradeC();
    double
        percentageRejected =
        (batch.getBatchWeight().getTotal() / 100) * batch.getBatchFruit().getRejected();

    System.out.println("GRADE A\t\t " + batch.getBatchFruit().getGradeA()
        + "% = " + df.format(percentageA) + "kg" + " = £" + batch.getBatchValue().getGradeA());
    System.out.println("GRADE B\t\t " + batch.getBatchFruit().getGradeB()
        + "% = " + df.format(percentageB) + "kg" + " = £" + batch.getBatchValue().getGradeB());
    System.out.println("GRADE C\t\t " + batch.getBatchFruit().getGradeB()
        + "% = " + df.format(percentageC) + "kg" + " = £" + batch.getBatchValue().getGradeC());
    System.out.println("REJECTED \t " + batch.getBatchFruit().getRejected()
        + "% = " + df.format(percentageRejected) + "kg");
  }

  @Override
  public void calculateBatchDetails(Batch batch) {
    String fruitName = batch.getBatchFruit().getProductName();

    switch (fruitName) {
      case STRAWBERRIES:
        processStrawberry(batch);
        break;
      case RASPBERRIES:
        processRaspberry(batch);
        break;
      case BLACKBERRIES:
        processBlackberry(batch);
        break;
      case GOOSEBERRIES:
        processGooseberry(batch);
        break;
      default:
        System.out.println("Invalid Fruit");
        break;
    }
  }

  public void processStrawberry(Batch batch) {
    processFruitPrices(batch, marketPlace.getStrawberryPrice());
  }

  public void processRaspberry(Batch batch) {
    processFruitPrices(batch, marketPlace.getRaspberryPrice());
  }

  public void processBlackberry(Batch batch) {
    processFruitPrices(batch, marketPlace.getBlackberryPrice());
  }

  public void processGooseberry(Batch batch) {
    processFruitPrices(batch, marketPlace.getGooseberryPrice());
  }

  @Override
  public Weight calculateGradeWeights(Batch batch) {
    Weight weight = batch.getBatchWeight();
    weight.setGradeA((batch.getBatchWeight().getTotal() / 100) * batch.getBatchFruit().getGradeA());
    weight.setGradeB((batch.getBatchWeight().getTotal() / 100) * batch.getBatchFruit().getGradeB());
    weight.setGradeC((batch.getBatchWeight().getTotal() / 100) * batch.getBatchFruit().getGradeC());
    weight.setRejected(
        (batch.getBatchWeight().getTotal() / 100) * batch.getBatchFruit().getRejected());

    System.out.println("Total " + weight.getTotal());
    System.out.println("Grade A " + weight.getGradeA());
    System.out.println("Grade B " + weight.getGradeB());
    System.out.println("Grade C " + weight.getGradeC());
    return weight;
  }

  private void processFruitPrices(Batch batch, Price fruitPrice) {
    Price batchValue = new Price();
    double
        gradeA =
        batch.getBatchFruit().getGradeA() * fruitPrice.getGradeA();
    batchValue.setGradeA(gradeA);
    double
        gradeB =
        batch.getBatchFruit().getGradeB() * fruitPrice.getGradeB();
    batchValue.setGradeB(gradeB);
    double
        gradeC =
        batch.getBatchFruit().getGradeC() * fruitPrice.getGradeC();
    batchValue.setGradeC(gradeC);
    calculateFruitTotals(batch, batchValue, gradeA, gradeB, gradeC);
  }

  private void calculateFruitTotals(Batch batch, Price batchValue, double gradeA,
                                    double gradeB, double gradeC) {
    batchValue.setTotal(gradeA + gradeB + gradeC);
    batch.setBatchValue(batchValue);
    batch.setBatchWeight(calculateGradeWeights(batch));
    fileService.updateBatchFile(batch);
  }
}
