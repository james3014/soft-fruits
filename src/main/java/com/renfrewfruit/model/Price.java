package com.renfrewfruit.model;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Price {

  private double total;
  private double gradeA;
  private double gradeB;
  private double gradeC;

  public Price(double gradeA, double gradeB, double gradeC) {
    this.total = gradeA + gradeB + gradeC;
  }
}
