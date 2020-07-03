package com.renfrewfruit.exception;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

/**
 * This class extends the RunTimeException class and allows a custom exception with a specific
 * details on what has went wrong during runtime.
 */
public class SoftFruitException extends RuntimeException {

  public SoftFruitException(String message) {
    super(message);
  }
}
