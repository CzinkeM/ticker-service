package com.epam.training.ticketservice.service.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateConverter {

    private static final String DATE_FORMAT = "YYYY-MM-DD hh:mm";

    public static Date convertStringToDate(String stringToDate) {
        Date date = null;
        try {
            date = new SimpleDateFormat(DATE_FORMAT).parse(stringToDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String convertDateToString(Date dateToString) {
        return new SimpleDateFormat(DATE_FORMAT).format(dateToString);
    }
}
