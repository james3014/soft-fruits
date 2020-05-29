package com.renfrewfruit.service.impl;

import com.renfrewfruit.driver.Driver;
import com.renfrewfruit.model.Market;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.service.PricingService;

import java.util.Scanner;

public class PricingServiceImpl implements PricingService {

  private final Scanner scanner;
  private final FileService fileService;

  public PricingServiceImpl() {
    this.scanner = new Scanner(System.in);
    this.fileService = new FileServiceImpl();
  }

  public void priceStrawberries(Market market) {
    System.out.println("Enter Prices Below For Strawberries:");
    System.out.print("\nGRADE A: ");
    market.getStrawberryPrice().setGradeA(scanner.nextDouble());
    System.out.print("\nGRADE B: ");
    market.getStrawberryPrice().setGradeB(scanner.nextDouble());
    System.out.print("\nGRADE C: ");
    market.getStrawberryPrice().setGradeC(scanner.nextDouble());
    System.out.print("\nConfirm Pricing Details Are Correct Y/N: ");

    String selection = scanner.next();
    if (selection.equalsIgnoreCase("Y")) {
      fileService.updateMarketFile(market);
    } else if (selection.equalsIgnoreCase("N")) {
      System.out.println("Press Any Key For Main Menu");
      Driver driver = new Driver();
      driver.openMenu();
    } else {
      System.out.println("Invalid Selection");
    }
  }

  public void priceRaspberries(Market market) {
    System.out.println("Enter Prices Below For Raspberries:");
    System.out.print("\nGRADE A: ");
    market.getRaspberryPrice().setGradeA(scanner.nextDouble());
    System.out.print("\nGRADE B: ");
    market.getRaspberryPrice().setGradeB(scanner.nextDouble());
    System.out.print("\nGRADE C: ");
    market.getRaspberryPrice().setGradeC(scanner.nextDouble());
    System.out.print("\nConfirm Pricing Details Are Correct Y/N: ");

    String selection = scanner.next();
    if (selection.equalsIgnoreCase("Y")) {
      fileService.updateMarketFile(market);
    } else if (selection.equalsIgnoreCase("N")) {
      System.out.println("Press Any Key For Main Menu");
      Driver driver = new Driver();
      driver.openMenu();
    } else {
      System.out.println("Invalid Selection");
    }
  }

  public void priceBlackberries(Market market) {
    System.out.println("Enter Prices Below For Blackberries:");
    System.out.print("\nGRADE A: ");
    market.getBlackberryPrice().setGradeA(scanner.nextDouble());
    System.out.print("\nGRADE B: ");
    market.getBlackberryPrice().setGradeB(scanner.nextDouble());
    System.out.print("\nGRADE C: ");
    market.getBlackberryPrice().setGradeC(scanner.nextDouble());
    System.out.print("\nConfirm Pricing Details Are Correct Y/N: ");

    String selection = scanner.next();
    if (selection.equalsIgnoreCase("Y")) {
      fileService.updateMarketFile(market);
    } else if (selection.equalsIgnoreCase("N")) {
      System.out.println("Press Any Key For Main Menu");
      Driver driver = new Driver();
      driver.openMenu();
    } else {
      System.out.println("Invalid Selection");
    }
  }

  public void priceGooseberries(Market market) {
    System.out.println("Enter Prices Below For Gooseberries:");
    System.out.print("\nGRADE A: ");
    market.getGooseberryPrice().setGradeA(scanner.nextDouble());
    System.out.print("\nGRADE B: ");
    market.getGooseberryPrice().setGradeB(scanner.nextDouble());
    System.out.print("\nGRADE C: ");
    market.getGooseberryPrice().setGradeC(scanner.nextDouble());
    System.out.print("\nConfirm Pricing Details Are Correct Y/N: ");

    String selection = scanner.next();
    if (selection.equalsIgnoreCase("Y")) {
      fileService.updateMarketFile(market);
    } else if (selection.equalsIgnoreCase("N")) {
      System.out.println("Press Any Key For Main Menu");
      Driver driver = new Driver();
      driver.openMenu();
    } else {
      System.out.println("Invalid Selection");
    }
  }
}
