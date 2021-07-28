package com.trade_republic.quotation.infrastructure.util;


import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class DateTimeUtils {

    public String toStringFormat(Date dateTime, String dateTimeFormat) {
        DateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);
        return dateFormat.format(dateTime);
    }

    public Date subtractFromDate(Date date, int min) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.MINUTE, -min);
        return cal.getTime();
    }
}
