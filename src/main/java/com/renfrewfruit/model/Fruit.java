package com.renfrewfruit.model;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

public class Fruit {

  private String productName;
  private String fruitCode;
  private int gradeA;
  private int gradeB;
  private int gradeC;
  private int rejected;

  public Fruit(String productName, String fruitCode, int gradeA, int gradeB, int gradeC,
               int rejected) {
    this.productName = productName;
    this.fruitCode = fruitCode;
    this.gradeA = gradeA;
    this.gradeB = gradeB;
    this.gradeC = gradeC;
    this.rejected = rejected;
  }

  public Fruit() {
  }

  public String getProductName() {
    return this.productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getFruitCode() {
    return this.fruitCode;
  }

  public void setFruitCode(String fruitCode) {
    this.fruitCode = fruitCode;
  }

  public int getGradeA() {
    return this.gradeA;
  }

  public void setGradeA(int gradeA) {
    this.gradeA = gradeA;
  }

  public int getGradeB() {
    return this.gradeB;
  }

  public void setGradeB(int gradeB) {
    this.gradeB = gradeB;
  }

  public int getGradeC() {
    return this.gradeC;
  }

  public void setGradeC(int gradeC) {
    this.gradeC = gradeC;
  }

  public int getRejected() {
    return this.rejected;
  }

  public void setRejected(int rejected) {
    this.rejected = rejected;
  }
}
