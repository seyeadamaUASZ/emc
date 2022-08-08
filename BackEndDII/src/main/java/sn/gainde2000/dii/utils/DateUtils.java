package sn.gainde2000.dii.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Project: dii
 * Package: sn.gainde2000.dii.utils
 * User: Ilo
 * Date: 30/08/2021
 * Time: 12:34
 * Created with IntelliJ IDEA
 */

public class DateUtils {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Renvoie la date du jour sous le type java.util.Date object
     *
     * @return date du jour sous le type java.util.Date object
     */
    public static Date today() {
        return new Date();
    }

    /**
     * Renvoie la date du jour sous le format yyyy-MM-dd format
     *
     * @return date du jour sous le format dd-MM-yyyy format
     */
    public static String todayStr() {
        return sdf.format(today());
    }
    
    public static  Date convertLocalDateTimeToDateUsingInstant(LocalDateTime dateToConvert)
    {    
    	return Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    	
    }

    /**
     * Renvoie la date en String formaté de la date passée java.util.Date object
     *
     * @param date
     * @return
     */
    public static String formattedDate(Date date) {
        return date != null ? sdf.format(date) : todayStr();
    }
}
