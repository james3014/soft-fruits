package com.renfrewfruit.service.impl;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

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
import com.renfrewfruit.utility.UserInputValidator;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * The responsibility of this class is to provide fruit grading functionality to the user. This
 * allows the user to grade individual batches of fruit into A, B C or rejected amounts.
 */
public class SortingServiceImpl implements SortingService {

  private final FileService fileService;
  private final Market marketPlace;
  private final Scanner scanner;
  private final UserInputValidator validator;

  public SortingServiceImpl() {
    this.fileService = new FileServiceImpl();
    this.marketPlace = fileService.retrieveMarket();
    this.scanner = new Scanner(System.in);
    this.validator = new UserInputValidator();
  }

  /**
   * This function is the primary driver within the class. It will request the user to input values
   * for each of the relevant grades within a batch before confirming and display these to the
   * user.
   *
   * @param batch - the batch to be graded
   */
  @Override
  public void gradeBatch(Batch batch) {
    String sb =
        "Batch contains "
            + batch.getBatchWeight().getTotal()
            + "kg of "
            + batch.getBatchFruit().getProductName()
            + " from farm number "
            + batch.getBatchOrigin().getFarmCode()
            + " received on "
            + batch.getBatchDate();
    System.out.println(sb);

    System.out.print("\nEnter percentage of GRADE A fruit in batch: ");
    batch.getBatchFruit().setGradeA(validator.getIntSelection());
    System.out.print("\nEnter percentage of GRADE B fruit in batch: ");
    batch.getBatchFruit().setGradeB(validator.getIntSelection());
    System.out.print("\nEnter percentage of GRADE C fruit in batch: ");
    batch.getBatchFruit().setGradeC(validator.getIntSelection());
    System.out.print("\nEnter percentage of rejected fruit in batch: ");
    batch.getBatchFruit().setRejected(validator.getIntSelection());
    System.out.print("\nConfirm grading details are correct Y/N: ");

    if (scanner.next().equalsIgnoreCase(YES)) {
      calculateBatchDetails(batch);
      System.out.println("Grading Details Added");
    } else {
      System.out.println("Press Any Key To Return To Main Menu");
    }
  }

  /**
   * This function is used to calculate the individual percentages of each fruit grade after the
   * grading process has been completed. Once this is done the values are then printed to screen
   * using the two place decimal format defined in the function.
   *
   * @param batch - the fully graded batch
   */
  @Override
  public void calculatePercentages(Batch batch) {
    DecimalFormat df = new DecimalFormat("#.##");
    double percentageA =
        (batch.getBatchWeight().getTotal() / 100) * batch.getBatchFruit().getGradeA();
    double percentageB =
        (batch.getBatchWeight().getTotal() / 100) * batch.getBatchFruit().getGradeB();
    double percentageC =
        (batch.getBatchWeight().getTotal() / 100) * batch.getBatchFruit().getGradeC();
    double percentageRejected =
        (batch.getBatchWeight().getTotal() / 100) * batch.getBatchFruit().getRejected();

    System.out.println(
        "GRADE A\t\t "
            + batch.getBatchFruit().getGradeA()
            + "% = "
            + df.format(percentageA)
            + "kg"
            + " = £"
            + batch.getBatchValue().getGradeA());
    System.out.println(
        "GRADE B\t\t "
            + batch.getBatchFruit().getGradeB()
            + "% = "
            + df.format(percentageB)
            + "kg"
            + " = £"
            + batch.getBatchValue().getGradeB());
    System.out.println(
        "GRADE C\t\t "
            + batch.getBatchFruit().getGradeB()
            + "% = "
            + df.format(percentageC)
            + "kg"
            + " = £"
            + batch.getBatchValue().getGradeC());
    System.out.println(
        "REJECTED \t "
            + batch.getBatchFruit().getRejected()
            + "% = "
            + df.format(percentageRejected)
            + "kg");
  }

  /**
   * This function operates as an automated conditional statement where depending upon which fruit
   * type has been graded the application can then pass the batch to its relevant method.
   *
   * @param batch the full graded batch
   */
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

  /**
   * This function is responsible for calculating the prices of each grade in a batch of
   * strawberries.
   *
   * @param batch - the fully graded batch
   */
  public void processStrawberry(Batch batch) {
    processFruitPrices(batch, marketPlace.getStrawberryPrice());
  }

  /**
   * This function is responsible for calculating the prices of each grade in a batch of
   * raspberries.
   *
   * @param batch - the fully graded batch
   */
  public void processRaspberry(Batch batch) {
    processFruitPrices(batch, marketPlace.getRaspberryPrice());
  }

  /**
   * This function is responsible for calculating the prices of each grade in a batch of
   * blackberries.
   *
   * @param batch - the fully graded batch
   */
  public void processBlackberry(Batch batch) {
    processFruitPrices(batch, marketPlace.getBlackberryPrice());
  }

  /**
   * This function is responsible for calculating the prices of each grade in a batch of
   * gooseberries.
   *
   * @param batch - the fully graded batch
   */
  public void processGooseberry(Batch batch) {
    processFruitPrices(batch, marketPlace.getGooseberryPrice());
  }

  /**
   * This function is used to calculate the individual batch grade weight percentages and displaying
   * them to the console. This is achieved by taking the total weight, dividing by 100 and
   * multiplying against the grade percentage.
   *
   * @param batch - the fully graded batch
   * @return a fully formed weight object with all grades and total
   */
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

  /**
   * This function is responsible for processing an individual batches fruit pricing. This is
   * achieved by taking the fully graded batch as well as the current marketplace and multiplying.
   *
   * @param batch      - the fully graded batch
   * @param fruitPrice - a current marketplace price for the specific fruit
   */
  private void processFruitPrices(Batch batch, Price fruitPrice) {
    Price batchValue = new Price();
    double gradeA = batch.getBatchFruit().getGradeA() * fruitPrice.getGradeA();
    batchValue.setGradeA(gradeA);
    double gradeB = batch.getBatchFruit().getGradeB() * fruitPrice.getGradeB();
    batchValue.setGradeB(gradeB);
    double gradeC = batch.getBatchFruit().getGradeC() * fruitPrice.getGradeC();
    batchValue.setGradeC(gradeC);
    calculateFruitTotals(batch, batchValue, gradeA, gradeB, gradeC);
  }

  /**
   * This function is used to calculate the batch pricing total from each of the grades calculated
   * in the processFruitPrices method. The batch weight is also set here by calling the
   * calculateGradeWeights method and passing the batch. Once these have been completed the batch
   * object is then passed to the file service to be updated.
   *
   * @param batch      - the fully graded batch
   * @param batchValue - the fully priced price object to be added to the batch
   * @param gradeA     - the grade A pricing for the specific fruit
   * @param gradeB     - the grade B pricing for the specific fruit
   * @param gradeC     - the grade C pricing for the specific fruit
   */
  private void calculateFruitTotals(
      Batch batch, Price batchValue, double gradeA, double gradeB, double gradeC) {
    batchValue.setTotal(gradeA + gradeB + gradeC);
    batch.setBatchValue(batchValue);
    batch.setBatchWeight(calculateGradeWeights(batch));
    fileService.updateBatchFile(batch);
  }
}
