package com.renfrewfruit.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DateResolver {

  public String processDate() {
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
    return formatter.format(today);
  }
}
