package com.renfrewfruit.utility;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

import java.util.Scanner;

/**
 * This class is responsible for providing validation on user input. This is carried out when the
 * application expects either an integer or double value.
 *
 * <p>If the user inputs anything other than these expected formats the application will not throw
 * an exception and will accept the value before alerting the user that this is an invalid input.
 *
 * <p>The validator will loop until the user provides the expected input.
 */
public class UserInputValidator {

  private final Scanner scanner;

  public UserInputValidator() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * This function carries out input validation for the scanner object on integer values.
   *
   * @return - the integer value input by the user
   */
  public int getIntSelection() {
    int selection;
    while (!scanner.hasNextInt()) {
      String input = scanner.next();
      System.out.printf("\"%s\" is not a valid input\n", input);
    }
    selection = scanner.nextInt();
    return selection;
  }

  /**
   * This function carries out input validtion for the scanner object on double values.
   *
   * @return - the double value input by the user
   */
  public double getDoubleSelection() {
    double selection;
    while (!scanner.hasNextDouble()) {
      String input = scanner.next();
      System.out.printf("\"%s\" is not a valid input\n", input);
    }
    selection = scanner.nextDouble();
    return selection;
  }
}
