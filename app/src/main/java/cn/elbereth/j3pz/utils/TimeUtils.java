package cn.elbereth.j3pz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2016/10/18
 */
public class TimeUtils {
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    public static final long MillisInHour = 3600000;
    public static final long MillisInDay = 86400000;

    static {
        sDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public static String parseString(Date date) {
        return sDateFormat.format(date);
    }

    public static Date toDate(String s) throws ParseException {
        return sDateFormat.parse(s);
    }
}
