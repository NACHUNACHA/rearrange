package org.example.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String convertDate(String buddhistDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        Date date = sdf.parse(buddhistDate);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, -543);

        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }
}
