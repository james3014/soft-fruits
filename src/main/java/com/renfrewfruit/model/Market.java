package com.renfrewfruit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Market {

  private String date;
  private Price strawberryPrice;
  private Price raspberryPrice;
  private Price blackberryPrice;
  private Price gooseberryPrice;

}
