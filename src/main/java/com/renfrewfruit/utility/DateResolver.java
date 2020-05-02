package com.renfrewfruit.utility;

import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
public class DateResolver {

    public String processDate() {

        Date todayDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        return dateFormat.format(todayDate);
    }
}
