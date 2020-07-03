package com.renfrewfruit.model;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

public class Market {

  private String date;
  private Price strawberryPrice;
  private Price raspberryPrice;
  private Price blackberryPrice;
  private Price gooseberryPrice;

  public Market(String date, Price strawberryPrice, Price raspberryPrice, Price blackberryPrice,
                Price gooseberryPrice) {
    this.date = date;
    this.strawberryPrice = strawberryPrice;
    this.raspberryPrice = raspberryPrice;
    this.blackberryPrice = blackberryPrice;
    this.gooseberryPrice = gooseberryPrice;
  }

  public Market() {
  }

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Price getStrawberryPrice() {
    return this.strawberryPrice;
  }

  public void setStrawberryPrice(Price strawberryPrice) {
    this.strawberryPrice = strawberryPrice;
  }

  public Price getRaspberryPrice() {
    return this.raspberryPrice;
  }

  public void setRaspberryPrice(Price raspberryPrice) {
    this.raspberryPrice = raspberryPrice;
  }

  public Price getBlackberryPrice() {
    return this.blackberryPrice;
  }

  public void setBlackberryPrice(Price blackberryPrice) {
    this.blackberryPrice = blackberryPrice;
  }

  public Price getGooseberryPrice() {
    return this.gooseberryPrice;
  }

  public void setGooseberryPrice(Price gooseberryPrice) {
    this.gooseberryPrice = gooseberryPrice;
  }
}
