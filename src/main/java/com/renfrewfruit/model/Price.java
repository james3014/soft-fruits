package com.renfrewfruit.model;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

public class Price {

  private double total;
  private double gradeA;
  private double gradeB;
  private double gradeC;

  public Price(double gradeA, double gradeB, double gradeC) {
    this.total = gradeA + gradeB + gradeC;
  }

  public Price(double total, double gradeA, double gradeB, double gradeC) {
    this.total = total;
    this.gradeA = gradeA;
    this.gradeB = gradeB;
    this.gradeC = gradeC;
  }

  public Price() {
  }

  public static PriceBuilder builder() {
    return new PriceBuilder();
  }

  public double getTotal() {
    return this.total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public double getGradeA() {
    return this.gradeA;
  }

  public void setGradeA(double gradeA) {
    this.gradeA = gradeA;
  }

  public double getGradeB() {
    return this.gradeB;
  }

  public void setGradeB(double gradeB) {
    this.gradeB = gradeB;
  }

  public double getGradeC() {
    return this.gradeC;
  }

  public void setGradeC(double gradeC) {
    this.gradeC = gradeC;
  }

  public static class PriceBuilder {
    private double total;
    private double gradeA;
    private double gradeB;
    private double gradeC;

    PriceBuilder() {
    }

    public Price.PriceBuilder total(double total) {
      this.total = total;
      return this;
    }

    public Price.PriceBuilder gradeA(double gradeA) {
      this.gradeA = gradeA;
      return this;
    }

    public Price.PriceBuilder gradeB(double gradeB) {
      this.gradeB = gradeB;
      return this;
    }

    public Price.PriceBuilder gradeC(double gradeC) {
      this.gradeC = gradeC;
      return this;
    }

    public Price build() {
      return new Price(total, gradeA, gradeB, gradeC);
    }

    public String toString() {
      return "Price.PriceBuilder(total=" + this.total + ", gradeA=" + this.gradeA + ", gradeB="
          + this.gradeB + ", gradeC=" + this.gradeC + ")";
    }
  }
}
