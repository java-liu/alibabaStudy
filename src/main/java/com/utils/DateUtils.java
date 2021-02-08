package com.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author Liuys
 * @version V1.0
 */
@Slf4j
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    public static Date getCurrentDate() {
        Date now = new Date();
        LocalDate localDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return java.sql.Date.valueOf(localDate);
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 获取当年的最后一天
     *
     * @return
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * @方法说明 获取某一日期的后一天
     **/
    public static Date getAfterOneDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        return c.getTime();
    }

    public static Date getDateByHour(Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + hour);
        return c.getTime();
    }

    /**
     * @方法说明 把符串转成日期
     **/
    public static Date strToDate(String srcDate) {
        Date date = null;
        try {
            if (!StringUtils.isEmpty(srcDate)) {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(srcDate);
            }
        } catch (ParseException e) {
            log.error("日期转换时发生ParseException异常", e);
        }
        return date;
    }

    /**
     * @方法说明 获取某一日期是周几
     **/
    public static int dayForWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static int getLastYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, -1);

        return cal.get(Calendar.YEAR);
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {

        try {
            if (str == null) {
                return null;
            }
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     * 获取当前毫秒数：System.currentTimeMillis(); 而不是new Date().getTime();
     * 说明：如果想获取更加精确的纳秒级时间值，用System.nanoTime。在JDK8中，针对统计时间等场景，推荐使用Instant类。
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24.0);
    }

    public static boolean compareAddDateTime(Date before, Date after, int count) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(before);
        cal.add(Calendar.DAY_OF_YEAR, count);

        return after.compareTo(cal.getTime()) <= 0 ? true : false;
    }

    /**
     * 计算时间差
     *
     * @param startTime
     * @param endTime
     * @param str       d:天数；h:小时；m：分钟；s:秒
     * @return
     */
    public static Long dateDiff(Date startTime, Date endTime, String str) {
        // 按照传入的格式生成一个simpledateformate对象
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        // 获得两个时间的毫秒时间差异
        diff = endTime.getTime() - startTime.getTime();
        min = diff / ns / 60;
        sec = diff / 1000;
        if ("m".equalsIgnoreCase(str)) {
            return min;
        } else {
            return sec;
        }
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @param dateFormat
     * @return
     */
    public static String dateToStr(Date dateDate, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 获取下一年的今天的时间
     *
     * @return
     */
    public static Date dateToNextYear() {
        Date date = new Date();// 取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);// 把日期往后增加一年.整数往后推,负数往前移动
        return calendar.getTime();
    }

    /**
     * 获取上周的周一
     *
     * @return
     */
    public static String getProMonday() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cld = Calendar.getInstance(Locale.CHINA);
        cld.setTimeInMillis(System.currentTimeMillis());
        cld.add(Calendar.WEEK_OF_YEAR, -1);
        cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return df.format(cld.getTime()) + " 00:00:00";
    }

    /**
     * 返回明天 00:00:00
     * @param today
     * @return
     */
    public static String tomorrowBegan(Date today) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        //Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        return df.format(calendar.getTime()) + " 00:00:00";
    }
    /**
     * 返回明天 00:00:00
     * @param today
     * @return
     */
    public static String tomorrowEnd(Date today) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        //Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        return df.format(calendar.getTime()) + " 23:59:59";
    }
    /**
     * 返回后天 00:00:00
     * @param today
     * @return
     */
    public static String dayAfterTomorrowBegan(Date today) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        //Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 2);
        return df.format(calendar.getTime()) + " 00:00:00";
    }
    /**
     * 返回后天 00:00:00
     * @param today
     * @return
     */
    public static String dayAfterTomorrowEnd(Date today) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        //Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 2);
        return df.format(calendar.getTime()) + " 23:59:59";
    }
    /***
     * 获取下周一
     * @return
     */
    public static String getNextWeekMonday(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cld = Calendar.getInstance(Locale.CHINA);
        int dayOfWeek = cld.get(Calendar.DAY_OF_WEEK);
        //获得当前日期相对于下周一的偏移量（在国外，星期一是一周的第二天，所以下周是这周的第九天）
        //如果当前日期是周日，它的下周一偏移量是1
        int nextMondayOfset = dayOfWeek == 1 ? 1 : 9-dayOfWeek;
        cld.setTimeInMillis(System.currentTimeMillis());
        cld.add(Calendar.DATE, nextMondayOfset);
        cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return df.format(cld.getTime()) + " 00:00:00";
    }
    /***
     * 获取下下周一
     * @return
     */
    public static String getNextNextWeekMonday(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cld = Calendar.getInstance(Locale.CHINA);
        int dayOfWeek = cld.get(Calendar.DAY_OF_WEEK);
        int nextMondayOfset = dayOfWeek == 1 ? 8 : 16-dayOfWeek;
        cld.setTimeInMillis(System.currentTimeMillis());
        cld.add(Calendar.DATE, nextMondayOfset);
        cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return df.format(cld.getTime()) + " 00:00:00";
    }

    /***
     * 获取下周日
     * @return
     */
    public static String getNextWeekSunday(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cld = Calendar.getInstance(Locale.CHINA);
        int dayOfWeek = cld.get(Calendar.DAY_OF_WEEK);
        //获得当前日期相对于下周一的偏移量（在国外，星期一是一周的第二天，所以下周是这周的第15天）
        //如果当前日期是周日，它的下周一偏移量是7
        int nextMondayOfset = dayOfWeek == 1 ? 7 : 15-dayOfWeek;
        //cld.setTimeInMillis(System.currentTimeMillis());
        cld.add(Calendar.DATE, nextMondayOfset);
        //cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return df.format(cld.getTime()) + " 23:59:59";
    }
    /***
     * 获取下下周日
     * @return
     */
    public static String getNextNextWeekSunday(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cld = Calendar.getInstance(Locale.CHINA);
        int dayOfWeek = cld.get(Calendar.DAY_OF_WEEK);
        int nextMondayOfset = dayOfWeek == 1 ? 14 : 22-dayOfWeek;
        cld.setTimeInMillis(System.currentTimeMillis());
        cld.add(Calendar.DATE, nextMondayOfset);
        //cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return df.format(cld.getTime()) + " 23:59:59";
    }

    /**
     * 获取上周的周日
     *
     * @return
     */
    public static String getProSunday() {
        Calendar cld = Calendar.getInstance(Locale.CHINA);
        cld.setFirstDayOfWeek(Calendar.MONDAY);
        cld.setTimeInMillis(System.currentTimeMillis());
        cld.add(Calendar.WEEK_OF_YEAR, -1);
        cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(cld.getTime()) + " 23:59:59";
    }

    /***
     * 判断日期是不是今天（本日）
     * @param date
     * @return
     */
    public static boolean isNow(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        //获取今天的时间
        String nowDay = sdf.format(now);
        //对比的时间
        String day = sdf.format(date);
        return day.equals(nowDay);
    }
    /**
     * 获取某月第一天日期
     *
     * @return
     */
    public static Date nextMonthFirstDate(Integer monthNum) {
        Calendar calendar = Calendar.getInstance();
        monthNum = monthNum == null ? 1 : monthNum;
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, monthNum);
        return calendar.getTime();
    }

    /***
     * 获得入参日期下个月的第一天
     * @param date 入参日期
     * @return 入参日期下个月的第一天
     */
    public static Date firstDayOfNextMonth(Date date){
        //获得入参的日期
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        //获取下个月第一天
        cd.add(Calendar.MONTH, 1);
        //设置为1号，当前日期即为次月第一天
        cd.set(Calendar.DAY_OF_MONTH, 1);
        return cd.getTime();
    }

    /**
     * 获得入参日期次年的第一天
     *
     * @param date 入参日期
     * @return 入参日期次年的第一天
     */
    public static Date firstDayOfNextYear(Date date) {
        //获得入参的日期
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        //获取次年第一天：
        cd.add(Calendar.YEAR, 1);
        //设置为1月1号,当前日期既为次年第一天
        cd.set(Calendar.MONTH, 0);
        cd.set(Calendar.DAY_OF_MONTH, 1);

        return cd.getTime();
    }
    public static String getQuarterByDate(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int mouth = calendar.get(Calendar.MONTH) + 1;

        if (mouth >= 1 && mouth <= 3) {
            return year + "一季度";
        } else if (mouth >= 4 && mouth <= 6) {
            return year + "二季度";
        } else if (mouth >= 7 && mouth <= 9) {
            return year + "三季度";
        } else if (mouth >= 10 && mouth <= 12) {
            return year + "四季度";
        } else {
            return "";
        }
    }

    public static void main(String[] args) throws ParseException {
        //System.out.println(compareTwoStringDates("2020-10-15","2020-10-15"));
        //System.out.println(getProMonday());
        //System.out.println(getProSunday());
        //System.out.println(getNextWeekDateList());
        System.out.println("下周一：" + getNextWeekMonday());
        System.out.println("下周日：" + getNextWeekSunday());
        System.out.println("下下周一：" + getNextNextWeekMonday());
        System.out.println("下下周日：" + getNextNextWeekSunday());
        /*Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //sdf.parse("2021-01-30 18:00:01");
        //date.setTime();
        System.out.println("是否是本周：" + isThisWeek(sdf.parse("2021-01-30 18:00:01")));
        System.out.println("是否是下周日：" + isThisWeekSunday(sdf.parse("2021-01-31 18:00:01")));*/
    }

    /**
     * 获取某月最后一天日期
     *
     * @return
     */
    public static Date nextMonthlastDate(Integer monthNum) {
        monthNum = monthNum == null ? 1 : monthNum;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, monthNum);

        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    public static int compareTwoStringDates(String nowDateString, String endDateString) {

        Date nowDate_Date = null,
                endDate_Date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            nowDate_Date = sdf.parse(nowDateString);
            endDate_Date = sdf.parse(endDateString);

            return nowDate_Date.compareTo(endDate_Date);
        } catch (ParseException e) {
            return -1;
        }
    }

    /**
     * 获取上某个季度的开始时间  第一天
     *
     * @param last 1：上个季度，2：上上个季度
     * @return
     */
    public static Date getLastQuarterStartDate(int last) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3 - last) * 3);
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);
        setMinTime(startCalendar);

        return startCalendar.getTime();
    }

    /**
     * 获取上某个季度的结束时间  最后一天
     *
     * @param last 1：上个季度，2：上上个季度
     * @return
     */
    public static Date getLastQuarterEndDate(int last) {

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.MONTH, ((int) endCalendar.get(Calendar.MONTH) / 3 - last) * 3 + 2);
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setMaxTime(endCalendar);

        return endCalendar.getTime();
    }

    private static void setMinTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private static void setMaxTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
    }

    public static String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
    public String getTimeInterval(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String imptimeBegin = sdf.format(cal.getTime());
        // System.out.println("所在周星期一的日期：" + imptimeBegin);
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());
        // System.out.println("所在周星期日的日期：" + imptimeEnd);
        return imptimeBegin + "," + imptimeEnd;
    }

    public String getLastTimeInterval() {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
        int offset1 = 1 - dayOfWeek;
        int offset2 = 7 - dayOfWeek;
        calendar1.add(Calendar.DATE, offset1 - 7);
        calendar2.add(Calendar.DATE, offset2 - 7);
        // System.out.println(sdf.format(calendar1.getTime()));// last Monday
        String lastBeginDate = sdf.format(calendar1.getTime());
        // System.out.println(sdf.format(calendar2.getTime()));// last Sunday
        String lastEndDate = sdf.format(calendar2.getTime());
        return lastBeginDate + "," + lastEndDate;
    }

    public static List<Date> findDates(Date dBegin, Date dEnd)
    {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime()))
        {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    /**
     * 获取当前日期的下周一到下周日的所有日期集合
     * @return
     */
    public static List getNextWeekDateList() throws ParseException {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 =Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d H:m:s");
        LocalDate ld = LocalDate.now();
        System.out.println(ld.toString());
        cal1.setTime(sdf.parse(ld.toString()+" 0:0:0"));

        cal2.setTime(new Date());
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal1.get(Calendar.DAY_OF_WEEK);

        if(dayWeek == 1){
            cal1.add(Calendar.DAY_OF_MONTH, 1);
            cal2.add(Calendar.DAY_OF_MONTH, 7);
        } else {
            cal1.add(Calendar.DAY_OF_MONTH, 1-dayWeek+8);
            cal2.add(Calendar.DAY_OF_MONTH, 1-dayWeek+14);
        }
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(cal1.getTime());

        List dateList = new ArrayList();
        //别忘了，把起始日期加上
        dateList.add(cal1.getTime());
        // 此日期是否在指定日期之后
        while (cal2.getTime().after(cStart.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cStart.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(cStart.getTime());
        }
        return dateList;
    }

    /***
     * 入参是否是本周
     * @param time
     * @return true false
     */
    public static boolean isThisWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if (paramWeek == currentWeek) {
            return true;
        }
        return false;
    }

    /***
     * 判断参数日期是否在本周一00:00:00-周六18:00范围内
     * @param date
     * @return
     * @throws ParseException
     */
    public static boolean isThisWeek(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(date);
        //如果目标日期是周六，只截止到18:00:00，不再是原来的23:59:59
        //hour=18
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        //周一=2（默认周日是一周第一天）
        if(weekDay == 7){
            if (paramWeek == currentWeek && hour < 18) {
                return true;
            }
        }else{
            if (paramWeek == currentWeek && weekDay >=2) {
                return true;
            }
        }
        return false;
    }

    /***
     * 判断参数日期是否在本周六18:00之后-周日24:00范围内
     * @param date
     * @return
     * @throws ParseException
     */
    public static boolean isThisWeekSunday(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        //1、判断当前日期是周几
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);

        //int hour = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.setTime(date);
        //如果目标日期是周六，只截止到18:00:00，不再是原来的23:59:59
        //hour=18
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //2、如果是周六，判断传入参数否已经过18点并且也是本周六
        if(weekDay == 7){
            if (paramWeek == currentWeek && hour >= 18) {
                return true;
            }
        }
        if(weekDay == 1){
            //如果是周日，直接返回
            return true;
        }
        return false;
    }

    /***
     * 某个时间是否在两个时间段内
     * @param date
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    public static boolean isEffectiveDate(String date, String startTime, String endTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(date == null || startTime == null || endTime == null){
            return false;
        }
        if(sdf.parse(date).getTime() == sdf.parse(startTime).getTime() || sdf.parse(date).getTime() == sdf.parse(endTime).getTime()){
            return true;
        }
        Calendar date1 = Calendar.getInstance();
        date1.setTime(sdf.parse(date));

        Calendar begin = Calendar.getInstance();
        begin.setTime(sdf.parse(startTime));

        Calendar end = Calendar.getInstance();
        end.setTime(sdf.parse(endTime));

        if (date1.after(begin) && date1.before(end)) {
            return true;
        } else {
            return false;
        }
    }

}
