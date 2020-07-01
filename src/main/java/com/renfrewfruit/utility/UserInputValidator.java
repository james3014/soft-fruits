package com.renfrewfruit.utility;

import java.util.Scanner;

public class UserInputValidator {

  private final Scanner scanner;

  public UserInputValidator() {
    this.scanner = new Scanner(System.in);
  }

  public int getIntSelection() {
    int selection;
    while (!scanner.hasNextInt()) {
      String input = scanner.next();
      System.out.printf("\"%s\" is not a valid input\n", input);
    }
    selection = scanner.nextInt();
    return selection;
  }

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
