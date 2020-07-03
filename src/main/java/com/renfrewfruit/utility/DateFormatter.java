package com.renfrewfruit.utility;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.NoArgsConstructor;

/**
 * This class is responsible for parsing local date objects into the required format of the
 * application.
 */
@NoArgsConstructor
public class DateFormatter {

  /**
   * This function creates a local date object with today's date before passing it to a date time
   * formatter object which has the assigned pattern required by the application.
   *
   * @return - the correctly formatted date
   */
  public String processDate() {
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
    return formatter.format(today);
  }
}
