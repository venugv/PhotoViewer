package com.example.photo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    private static final String TAG = DateUtils.class.getName();

    // "2014-12-16T08:09:18.000Z",
    private static final String DATE_FORMAT_LONG = "yyyy-MM-dd'T'kk:mm:ss.SSSSSS'Z'";
    private final SimpleDateFormat mFormatLong;

    public DateUtils() {
        final TimeZone DATE_TIMEZONE = TimeZone.getTimeZone("UTC");
        mFormatLong = new SimpleDateFormat(DATE_FORMAT_LONG, Locale.getDefault());
        mFormatLong.setTimeZone(DATE_TIMEZONE);
    }

    public Date getDateLong(String str) {
        try {
            return mFormatLong.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }
}
