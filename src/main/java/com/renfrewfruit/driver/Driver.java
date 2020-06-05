package com.renfrewfruit.driver;

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

import java.util.Scanner;

public class Driver {

  private final BatchService batchService;
  private final SortingService sortingService;
  private final PricingService pricingService;
  private final TransactionService transactionService;
  private final FileService fileService;
  private final Scanner scanner;

  public Driver() {
    this.batchService = new BatchServiceImpl();
    this.sortingService = new SortingServiceImpl();
    this.pricingService = new PricingServiceImpl();
    this.fileService = new FileServiceImpl();
    this.transactionService = new TransactionServiceImpl();
    this.scanner = new Scanner(System.in);
  }

  public void openMenu() {
    boolean startApplication = true;
    do {
      System.out.println("Welcome To Renfrewshire Soft Fruits Cooperative \n");
      System.out.print("Select An Option: \n");
      System.out.print("1. Create a New Batch \n2. List All Batches \n3. View Details of a Batch" +
          "\n4. Sort & Grade a Batch \n5. Payments \n6. Transaction Report \n7. Quit \n> ");

      switch (scanner.nextInt()) {
        case 1:
          batchService.batchProcess();
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

  public void gradeProcess() {
    System.out.print("Enter A Batch Number :");
    String fileName = fileService.getBatchFileName(scanner.next());
    Batch batch = fileService.mapBatchFromFile(fileName);
    sortingService.gradeBatch(batch, fileName);
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

  public void returnToMainMenu() {
    System.out.println("Return To Main Menu? Y/N");
    if (scanner.next().equalsIgnoreCase(YES)) {
      openMenu();
    } else {
      System.exit(0);
    }
  }
}
