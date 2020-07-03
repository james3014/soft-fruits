package com.renfrewfruit.driver;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

import static com.renfrewfruit.model.Constants.YES;

import com.renfrewfruit.model.Batch;
import com.renfrewfruit.model.Market;
import com.renfrewfruit.service.BatchService;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.service.PricingService;
import com.renfrewfruit.service.SortingService;
import com.renfrewfruit.service.TransactionService;
import com.renfrewfruit.service.impl.BatchServiceImpl;
import com.renfrewfruit.service.impl.FileServiceImpl;
import com.renfrewfruit.service.impl.PricingServiceImpl;
import com.renfrewfruit.service.impl.SortingServiceImpl;
import com.renfrewfruit.service.impl.TransactionServiceImpl;
import com.renfrewfruit.utility.DateFormatter;
import com.renfrewfruit.utility.UserInputValidator;

import java.util.Scanner;

/**
 * This class is responsible for driving the application through user input. The class hosts the
 * applications main menu system and allows the user to select what they want to do.
 */
public class Driver {

  private final BatchService batchService;
  private final SortingService sortingService;
  private final PricingService pricingService;
  private final TransactionService transactionService;
  private final FileService fileService;
  private final Scanner scanner;
  private final UserInputValidator validator;
  private final DateFormatter dateFormatter;

  public Driver() {
    this.batchService = new BatchServiceImpl();
    this.sortingService = new SortingServiceImpl();
    this.pricingService = new PricingServiceImpl();
    this.fileService = new FileServiceImpl();
    this.transactionService = new TransactionServiceImpl();
    this.scanner = new Scanner(System.in);
    this.validator = new UserInputValidator();
    this.dateFormatter = new DateFormatter();
  }

  /**
   * This function operates as a main menu within the application allowing the user to select what
   * they would like to do from a list of options. The decision is interpreted through a switch.
   */
  public void openMenu() {
    do {
      System.out.println("Welcome To Renfrewshire Soft Fruits Cooperative \n");
      System.out.print("Select An Option: \n");
      System.out.print(
          "1. Create a New Batch \n2. List All Batches \n3. View Details of a Batch"
              + "\n4. Sort & Grade a Batch \n5. Payments \n6. Transaction Report \n7. Quit \n> ");

      switch (validator.getIntSelection()) {
        case 1:
          batchService.processNewBatch();
          break;
        case 2:
          batchService.listAllBatches();
          break;
        case 3:
          batchService.batchDetails();
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
          System.out.println("Exiting Application\n");
          System.exit(0);
          break;
        default:
          System.out.println("Invalid Selection\n");
      }
    } while (true);
  }

  /**
   * This function is utilised throughout the application to return back to the main menu once the
   * user has completed another task.
   */
  public void returnToMainMenu() {
    System.out.print("Return To Main Menu? Y/N\n> ");
    if (scanner.next().equalsIgnoreCase(YES)) {
      openMenu();
    } else {
      System.exit(0);
    }
  }

  /**
   * This function allows the user to begin the grading process for an existing batch of fruit. The
   * batch number should be provided by the user which the file service can then find before passing
   * it to the sorting service for grading.
   */
  public void gradeProcess() {
    System.out.print("Enter A Batch Number :");
    String fileName = fileService.getBatchFileName(scanner.next());
    Batch batch = fileService.mapBatchFromFile(fileName);
    if (batch == null) {
      System.out.println("Batch Could Not Be Found. Try Again.\n");
      gradeProcess();
    } else {
      sortingService.gradeBatch(batch);
    }
  }

  /**
   * This function allows the user to generate a transaction report for a specific date. This is
   * achieved by prompting the user to provide a date (in the correct format) which is then passed
   * to the transaction service to be generated and displayed to the user.
   */
  private void transactionReport() {
    System.out.println("TRANSACTION REPORT");
    System.out.print("Please Enter Transaction Date: ");
    String transactionDate = scanner.next();
    transactionService.generateReport(transactionDate);
  }

  /**
   * This function allows the user to set new prices for individual fruit within the marketplace.
   * This is achieved by first requesting a market object with all current prices in place. A switch
   * option is then provided to select the fruit which will be passed to the pricing service.
   */
  public void fruitPricingProcess() {
    Market market = fileService.retrieveMarket();
    System.out.println("\nSelect A Fruit To Price:");
    System.out.print("1. STRAWBERRIES\n2. RASPBERRIES\n3. BLACKBERRIES \n4. GOOSEBERRIES\n>");

    switch (validator.getIntSelection()) {
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

  /**
   * This function is only called is the current marketplace is not up to date with the current
   * date. If this is not the case this function will be called and the user will be prompted to
   * input individual prices for all of the unique fruit.
   */
  public void setDailyFruitPrices() {
    Market market = fileService.retrieveMarket();
    market.setDate(dateFormatter.processDate());
    pricingService.priceStrawberries(market);
    pricingService.priceRaspberries(market);
    pricingService.priceBlackberries(market);
    pricingService.priceGooseberries(market);
  }
}
