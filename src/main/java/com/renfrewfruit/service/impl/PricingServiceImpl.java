package com.renfrewfruit.service.impl;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

import static com.renfrewfruit.model.Constants.NO;
import static com.renfrewfruit.model.Constants.YES;

import com.renfrewfruit.driver.Driver;
import com.renfrewfruit.model.Market;
import com.renfrewfruit.model.Price;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.service.PricingService;
import com.renfrewfruit.utility.UserInputValidator;

import java.util.Scanner;

/**
 * The responsibility of this class is to provide marketplace fruit pricing functionality to the
 * user. This is achieved through individual pricing of each fruit type.
 */
public class PricingServiceImpl implements PricingService {

  private final Scanner scanner;
  private final FileService fileService;
  private final UserInputValidator validator;

  public PricingServiceImpl() {
    this.scanner = new Scanner(System.in);
    this.fileService = new FileServiceImpl();
    this.validator = new UserInputValidator();
  }

  /**
   * This function is used to set the price of strawberries in the marketplace.
   *
   * @param market - the current market object with the existing prices
   */
  @Override
  public void priceStrawberries(Market market) {
    priceFruit(market, "Enter Prices Below For Strawberries:", market.getStrawberryPrice());
  }

  /**
   * This function is used to set the price of raspberries in the marketplace.
   *
   * @param market - the current market object with the existing prices
   */
  @Override
  public void priceRaspberries(Market market) {
    priceFruit(market, "Enter Prices Below For Raspberries:", market.getRaspberryPrice());
  }

  /**
   * This function is used to set the price of blackberries in the marketplace.
   *
   * @param market - the current market object with the existing prices
   */
  @Override
  public void priceBlackberries(Market market) {
    priceFruit(market, "Enter Prices Below For Blackberries:", market.getBlackberryPrice());
  }

  /**
   * This function is used to set the price of gooseberries in the marketplace.
   *
   * @param market - the current market object with the existing prices
   */
  @Override
  public void priceGooseberries(Market market) {
    priceFruit(market, "Enter Prices Below For Gooseberries:", market.getGooseberryPrice());
  }

  /**
   * This function is called by each of the four fruit pricing methods to set each of the pricing
   * grades. Once this is done the new marketplace is passed to the confirmPricing method.
   *
   * @param market     - the marketplace object
   * @param fruitType  - a string requesting prices for the specific fruit
   * @param fruitPrice - the marketplace price object
   */
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

  /**
   * This function is used to confirm the prices set and if valid sends them to the file service to
   * be updated in the marketplace.
   *
   * @param market - the marketplace object
   */
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
