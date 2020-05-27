package com.renfrewfruit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Fruit {

  private String productName;
  private String fruitCode;
  private int gradeA;
  private int gradeB;
  private int gradeC;
  private int rejected;
}
