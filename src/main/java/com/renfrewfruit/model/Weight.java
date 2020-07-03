package com.renfrewfruit.model;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weight {

  double total;
  double gradeA;
  double gradeB;
  double gradeC;
  double rejected;

}
