package kwan.org.webloginchatdemo.utils;

import org.mapstruct.Named;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Arpit Khandelwal.
 */
public class DateUtils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Returns today's date as java.util.Date object
     *
     * @return today's date as java.util.Date object
     */
    public static Date today() {
        return new Date();
    }

    /**
     * Returns today's date as yyyy-MM-dd format
     *
     * @return today's date as yyyy-MM-dd format
     */
    public static String todayStr() {
        return sdf.format(today());
    }

    /**
     * Returns the formatted String date for the passed java.util.Date object
     *
     * @param date
     * @return
     */
    public static String formattedDate(Date date) {
        return date != null ? sdf.format(date) : todayStr();
    }

    @Named("stringToTimestamp")
    public static Timestamp convertStringToTimestamp(String strDate) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // you can change format of date
            Date date = formatter.parse(strDate);

            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Named("timestampToString")
    public static String convertTimestampToString(Timestamp ts) {

        if(ts ==null)
            return null;

        Date date = new Date();
        date.setTime(ts.getTime());
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

}
