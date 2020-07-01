package com.renfrewfruit.service.impl;

import static com.renfrewfruit.model.Constants.NO;
import static com.renfrewfruit.model.Constants.YES;

import com.renfrewfruit.driver.Driver;
import com.renfrewfruit.model.Market;
import com.renfrewfruit.model.Price;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.service.PricingService;
import com.renfrewfruit.utility.UserInputValidator;

import java.util.Scanner;

public class PricingServiceImpl implements PricingService {

  private final Scanner scanner;
  private final FileService fileService;
  private final UserInputValidator validator;

  public PricingServiceImpl() {
    this.scanner = new Scanner(System.in);
    this.fileService = new FileServiceImpl();
    this.validator = new UserInputValidator();
  }

  @Override
  public void priceStrawberries(Market market) {
    priceFruit(market, "Enter Prices Below For Strawberries:", market.getStrawberryPrice());
  }

  @Override
  public void priceRaspberries(Market market) {
    priceFruit(market, "Enter Prices Below For Raspberries:", market.getRaspberryPrice());
  }

  @Override
  public void priceBlackberries(Market market) {
    priceFruit(market, "Enter Prices Below For Blackberries:", market.getBlackberryPrice());
  }

  @Override
  public void priceGooseberries(Market market) {
    priceFruit(market, "Enter Prices Below For Gooseberries:", market.getGooseberryPrice());
  }

  private void priceFruit(Market market, String fruitType, Price fruitPrice) {
    System.out.println(fruitType);
    System.out.print("\nGRADE A: ");
    fruitPrice.setGradeA(validator.getDoubleSelection());
    System.out.print("\nGRADE B: ");
    fruitPrice.setGradeB(validator.getDoubleSelection());
    System.out.print("\nGRADE C: ");
    fruitPrice.setGradeC(validator.getDoubleSelection());
    System.out.print("\nConfirm Pricing Details Are Correct Y/N: ");
    confirmPricing(market);
  }

  private void confirmPricing(Market market) {
    String selection = scanner.next();
    if (selection.equalsIgnoreCase(YES)) {
      fileService.updateMarketFile(market);
    } else if (selection.equalsIgnoreCase(NO)) {
      System.out.println("Press Any Key For Main Menu");
      Driver driver = new Driver();
      driver.openMenu();
    } else {
      System.out.println("Invalid Selection");
    }
  }
}
