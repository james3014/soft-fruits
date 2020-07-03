package com.renfrewfruit.model;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

public class Farm {

  private int farmCode;

  public Farm(int farmCode) {
    this.farmCode = farmCode;
  }

  public Farm() {
  }

  public int getFarmCode() {
    return this.farmCode;
  }

  public void setFarmCode(int farmCode) {
    this.farmCode = farmCode;
  }
}
