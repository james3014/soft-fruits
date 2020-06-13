package com.renfrewfruit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Price {

  private double total;
  private double gradeA;
  private double gradeB;
  private double gradeC;

  public Price(double gradeA, double gradeB, double gradeC) {
    this.total = gradeA + gradeB + gradeC;
  }
}
