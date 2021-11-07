package com.epam.training.ticketservice.service.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

public final class DateConverter {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String DATE_FORMAT_OUTPUT = "yyyy-MM-dd hh:mm aa";

    public static Date convertStringToDate(String stringToDate) {
        Date date = null;
        try {
            var dateFormat = new SimpleDateFormat(DATE_FORMAT);
            date = dateFormat.parse(stringToDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String convertDateToString(Date dateToString) {
        String date;
        var dateFormat = new SimpleDateFormat(DATE_FORMAT);
        date = dateFormat.format(dateToString);
        return date;
    }
}
