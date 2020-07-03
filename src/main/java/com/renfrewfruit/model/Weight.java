package com.renfrewfruit.model;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

public class Weight {

  double total;
  double gradeA;
  double gradeB;
  double gradeC;
  double rejected;

  public Weight(double total, double gradeA, double gradeB, double gradeC, double rejected) {
    this.total = total;
    this.gradeA = gradeA;
    this.gradeB = gradeB;
    this.gradeC = gradeC;
    this.rejected = rejected;
  }

  public Weight() {
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

  public double getRejected() {
    return this.rejected;
  }

  public void setRejected(double rejected) {
    this.rejected = rejected;
  }
}
