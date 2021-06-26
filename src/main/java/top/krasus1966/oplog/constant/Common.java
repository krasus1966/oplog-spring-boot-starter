package top.krasus1966.oplog.constant;

/**
 * @author Krasus1966
 * @date 2021/6/9 17:48
 **/
public interface Common {

    interface DateTimeFormat {
        String DATE_TIME_FORMAT_STANDARD = "yyyy-MM-dd HH:mm:ss";
        String DATE_TIME_FORMAT_HOUR = "yyyy-MM-dd hh:mm:ss";
        String DATE_TIME_FORMAT_NO_SEC = "yyyy-MM-dd HH:mm";
        String DATE_TIME_FORMAT_ONLY_HOUR = "yyyy-MM-dd HH";
        String DATE_FORMAT_STANDARD = "yyyy-MM-dd";
        String DATE_FORMAT_YEAR_MONTH = "yyyy-MM";
        String DATE_FORMAT_ONLY_YEAR = "yyyy";

        String DATE_TIME_FORMAT8 = "yyyy/MM/dd HH:mm:ss";
        String DATE_TIME_FORMAT9 = "yyyy/MM/dd HH:mm";
        String DATE_TIME_FORMAT10 = "yyyy/MM/dd HH";
        String DATE_TIME_FORMAT11 = "yyyy/MM/dd";
        String DATE_TIME_FORMAT12 = "yyyy/MM";

        String TIME_FORMAT = "HH:mm:ss";
    }
}
