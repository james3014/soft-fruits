package com.renfrewfruit.exception;

/**
 * This class extends the RunTimeException class and allows a custom exception with a specific
 * details on what has went wrong during runtime.
 */
public class SoftFruitException extends RuntimeException {

  public SoftFruitException(String message) {
    super(message);
  }
}
