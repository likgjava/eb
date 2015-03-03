package com.gpcsoft.epp.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 广东同望科技股份有限公司（www.gpcsoft.com.cn）</p>
 */

public class DateUtil {
  // 定义比较日期的类型
  public final static int ERROR = -1000;
  public final static int ON_DAY = 1;
  public final static int ON_MONTH = 2;
  public final static int ON_YEAR = 3;
  public final static int ON_HOUR = 4;
  public final static int ON_MINUTE = 5;
  public final static int ON_SECOND = 6;
  public final static int ON_SYSTEM = 7;

  // 定义日期常量
  public final static int YEAR_MONTHS = 12;
  public final static int DAY_HOURS = 24;
  public final static int HOUR_MINUTES = 60;
  public final static int MINUTE_SECONDS = 60;

  // 一天=86400000(24*60*60*1000)毫秒(MSEL)millisecond
  public final static long DAY_MSELS = 86400000;

  // 定义格式化日期的模板
  public final static String PATTERN_SP1 = ":"; // 分隔符号
  public final static String PATTERN_DDHHMM = "dd:hh:mm"; // 天：小时：分钟

  public DateUtil() {

  }

  public static String getCurDateTime() {
    return getCurDate() + " " + getCurTime();
  }

  /**
   * 得到当前日期"####-##-##"
   *
   * @return 当前日期
   */
  public static String getCurDate() {
    String fullDate = getCurYearMonth();
    int temp = getCurDay();
    if (temp < 10) {
      fullDate += "-0" + temp;
    }
    else {
      fullDate += "-" + temp;
    }
    return fullDate;
  }

  // ####-##
  public static String getCurYearMonth() {
    String fullDate = String.valueOf(getCurYear());
    int temp = getCurMonth();
    if (temp < 10) {
      fullDate += "-0" + temp;
    }
    else {
      fullDate += "-" + temp;
    }
    return fullDate;
  }

  // ##:##:##
  public static String getCurTime() {
    String time = getCurHourMinute();
    int temp = getCurSecond();
    if (temp < 10) {
      time += ":0" + temp;
    }
    else {
      time += ":" + temp;
    }
    return time;
  }

  // ##:##
  public static String getCurHourMinute() {
    String time;
    int temp = getCurHour();
    if (temp < 10) {
      time = "0" + temp + ":";
    }
    else {
      time = temp + ":";
    }
    temp = getCurMinute();
    if (temp < 10) {
      time += "0" + temp;
    }
    else {
      time += temp;
    }
    return time;
  }

  public static int getCurYear() {
    return Calendar.getInstance().get(Calendar.YEAR);
  }

  public static int getCurMonth() {
    return Calendar.getInstance().get(Calendar.MONTH) + 1;
  }

  public static int getCurDay() {
    return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
  }

  public static int getCurHour() {
    return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
  }

  public static int getCurMinute() {
    return Calendar.getInstance().get(Calendar.MINUTE);
  }

  public static int getCurSecond() {
    return Calendar.getInstance().get(Calendar.SECOND);
  }

  /**
   * 得到一个日期对象
   *
   * @return Date
   */
  public static Date getDate() {
    return new Date(System.currentTimeMillis());
  }

  /**
   * 得到一个SQL日期对象
   *
   * @return Date
   */
  public static java.sql.Date getSQLDate() {
    return new java.sql.Date(System.currentTimeMillis());
  }

  /**
   * 得到一个SQL日期对象
   *
   * @return Date
   */
  public static java.sql.Timestamp getSQLTimestamp() {
    return new java.sql.Timestamp(System.currentTimeMillis());
  }

  /**
   * 自己编写的判断闰年的方法
   *
   * @param year int 年份
   * @return boolean（true=闰年，false=非闰年）
   */
  public static boolean isLeapYear(int year) {
    if ( (year % 400 == 0) || ( (year % 4 == 0) && (year % 100 != 0))) {
      return true;
    }
    return false;
  }

  /**
   * 自己编写的得到一年的天数的方法
   *
   * @param year int 年份
   * @return int （非闰年=365，闰年=366）
   */
  public static int getYearDays(int year) {
    if (isLeapYear(year)) {
      return 366;
    }
    return 365;
  }

  /**
   * 根据比较深度来比较两个日期，并且按比较深度返回d1-d2
   *
   * @param d1    Date
   * @param d2    Date
   * @param scope int 比较级别
   * @return long
   */
  public static long compareDate(Date d1, Date d2, int scope) {
    switch (scope) {
      case ON_YEAR:
        return compareDateOnYear(d1, d2);
      case ON_MONTH:
        return compareDateOnMonth(d1, d2);
      case ON_DAY:
        return compareDateOnDay(d1, d2);
      case ON_HOUR:
        return compareDateOnHour(d1, d2);
      case ON_MINUTE:
        return compareDateOnMinute(d1, d2);
      case ON_SECOND:
        return compareDateOnSecond(d1, d2);
      case ON_SYSTEM:
        return compareDateOnSystem(d1, d2);
      default:
        return ERROR;
    }
  }
  
  /**
   * 比较日期字符串大小
   * 如果d1>d2返回1
   * 如果d1=d2返回0
   * 如果d1<d2返回-1
   * @param d1
   * @param d2
   */
  public static int compareDate(Date d1, Date d2) {
      return new Long(d1.getTime()).compareTo(new Long(d2.getTime()));    
  }  

  /**
   * 比较两个日期的年份差距
   *
   * @param d1 Date
   * @param d2 Date
   * @return int
   */
  public static int compareDateOnYear(Date d1, Date d2) {
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    c1.setTime(d1);
    c2.setTime(d2);
    int y1 = c1.get(Calendar.YEAR);
    int y2 = c2.get(Calendar.YEAR);
    return y1 - y2;
  }

  /**
   * 比较两个日期在月份上的差距
   *
   * @param d1 Date
   * @param d2 Date
   * @return int
   */
  public static int compareDateOnMonth(Date d1, Date d2) {
    if (d1.getTime() == d2.getTime()) {
      return 0; // 日期相同返回0
    }
    int flag = -1;
    // 比较两个日期使日期较小的日期排在前面
    if (d1.getTime() > d2.getTime()) { // 日期一在日期二之后
      Date temp = d1;
      d1 = d2;
      d2 = temp;
      flag = 1;
    }
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    c1.setTime(d1);
    c2.setTime(d2);
    int y1 = c1.get(Calendar.YEAR);
    int y2 = c2.get(Calendar.YEAR);
    int mon1 = c1.get(Calendar.MONTH);
    int mon2 = c2.get(Calendar.MONTH);
    int mons = 0;
    for (int i = 1; i <= y2 - y1; i++) {
      mons += YEAR_MONTHS; // 一年有12个月
    }
    return (mons - mon1 + mon2) * flag;
  }

  /**
   * 比较两个日期并返回两个日期相差多少天(d1－d2)
   *
   * @param d1 Date
   * @param d2 Date
   * @return int
   */
  public static int compareDateOnDay(Date d1, Date d2) {
    if (d1.getTime() == d2.getTime()) {
      return 0; // 日期相同返回0
    }
    int flag = -1;
    // 比较两个日期使日期较小的日期排在前面
    if (d1.getTime() > d2.getTime()) { // 日期一在日期二之后
      Date temp = d1;
      d1 = d2;
      d2 = temp;
      flag = 1;
    }
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    c1.setTime(d1);
    c2.setTime(d2);
    int y1 = c1.get(Calendar.YEAR);
    int y2 = c2.get(Calendar.YEAR);
    int day1 = c1.get(Calendar.DAY_OF_YEAR);
    int day2 = c2.get(Calendar.DAY_OF_YEAR);
    int days = 0;
    for (int i = 1; i <= y2 - y1; i++) {
      days += getYearDays(y1);
    }
    return (days - day1 + day2) * flag;
  }

  public static long compareDateOnHour(Date d1, Date d2) {
    if (d1.getTime() == d2.getTime()) {
      return 0; // 日期相同返回0
    }
    int flag = -1; // d1<d2
    // 比较两个日期使日期较小的日期排在前面
    if (d1.getTime() > d2.getTime()) { // 日期一在日期二之后
      Date temp = d1;
      d1 = d2;
      d2 = temp;
      flag = 1;
    }
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    c1.setTime(d1);
    c2.setTime(d2);
    int y1 = c1.get(Calendar.YEAR);
    int y2 = c2.get(Calendar.YEAR);
    int day1 = c1.get(Calendar.DAY_OF_YEAR);
    int day2 = c2.get(Calendar.DAY_OF_YEAR);
    int days = 0;
    for (int i = 1; i <= y2 - y1; i++) {
      days += getYearDays(y1);
    }
    days = (days - day1 + day2);
    int h1 = c1.get(Calendar.HOUR_OF_DAY);
    int h2 = c2.get(Calendar.HOUR_OF_DAY);
    return (days * DAY_HOURS - h1 + h2) * flag;
  }

  public static long compareDateOnMinute(Date d1, Date d2) {
    if (d1.getTime() == d2.getTime()) {
      return 0; // 日期相同返回0
    }
    int flag = -1; // d1<d2
    // 比较两个日期使日期较小的日期排在前面
    if (d1.getTime() > d2.getTime()) { // 日期一在日期二之后
      Date temp = d1;
      d1 = d2;
      d2 = temp;
      flag = 1;
    }
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    c1.setTime(d1);
    c2.setTime(d2);
    int y1 = c1.get(Calendar.YEAR);
    int y2 = c2.get(Calendar.YEAR);
    int day1 = c1.get(Calendar.DAY_OF_YEAR);
    int day2 = c2.get(Calendar.DAY_OF_YEAR);
    int days = 0;
    for (int i = 1; i <= y2 - y1; i++) {
      days += getYearDays(y1);
    }
    days = (days - day1 + day2);
    int h1 = c1.get(Calendar.HOUR_OF_DAY);
    int h2 = c2.get(Calendar.HOUR_OF_DAY);
    long hours = days * DAY_HOURS - h1 + h2;
    int m1 = c1.get(Calendar.MINUTE);
    int m2 = c2.get(Calendar.MINUTE);
    return (hours * HOUR_MINUTES - m1 + m2) * flag;
  }

  public static long compareDateOnSecond(Date d1, Date d2) {
    if (d1.getTime() == d2.getTime()) {
      return 0; // 日期相同返回0
    }
    int flag = -1; // d1<d2
    // 比较两个日期使日期较小的日期排在前面
    if (d1.getTime() > d2.getTime()) { // 日期一在日期二之后
      Date temp = d1;
      d1 = d2;
      d2 = temp;
      flag = 1;
    }
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    c1.setTime(d1);
    c2.setTime(d2);
    int y1 = c1.get(Calendar.YEAR);
    int y2 = c2.get(Calendar.YEAR);
    int day1 = c1.get(Calendar.DAY_OF_YEAR);
    int day2 = c2.get(Calendar.DAY_OF_YEAR);
    int days = 0;
    for (int i = 1; i <= y2 - y1; i++) {
      days += getYearDays(y1);
    }
    days = (days - day1 + day2);
    int h1 = c1.get(Calendar.HOUR_OF_DAY);
    int h2 = c2.get(Calendar.HOUR_OF_DAY);
    long hours = days * DAY_HOURS - h1 + h2;
    int m1 = c1.get(Calendar.MINUTE);
    int m2 = c2.get(Calendar.MINUTE);
    long minutes = hours * HOUR_MINUTES - m1 + m2;
    int s1 = c1.get(Calendar.SECOND);
    int s2 = c2.get(Calendar.SECOND);
    return (minutes * MINUTE_SECONDS - s1 + s2) * flag;
  }

  public static int compareDateOnSystem(Date d1, Date d2) {
    return (int) (d1.getTime() - d2.getTime());
  }

  /**
   * 将日期转换在指定的级别上转换为数字
   *
   * @param source  String
   * @param pattern String
   * @param scope   int
   * @return long
   */
  public static long convertTo(String source, String pattern, int scope) {
    switch (scope) {
      case ON_MINUTE:
        return convertToMSEL(source, pattern);
      default:
        return ERROR;
    } // End switch
  } // End converTo function

  /**
   * 格式化一个日期对象，默认的格式：yyyy-MM-dd HH:mm:ss
   *
   * @param date    Date
   * @param pattern String
   * @return String
   */
  public static String format(Date date, String pattern) {
    String fmtStr = "";
    if (date != null) {
      java.text.DateFormat df = new java.text.SimpleDateFormat(pattern);
      fmtStr = df.format(date);
    }
    return fmtStr;
  }

  /**
   * 将一个字符串格式化为一个java.util.Date对象　
   *
   * @param obj Object
   * @return Date
   */
  public static Date parse(Object obj) {
    try {
      if (obj == null) {
        return null;
      }
      String dateString = obj.toString().trim();
      if (dateString.length() == 0) {
        return null;
      }
      if (dateString.length() == 10) {
        dateString += " 00:00:00";
      }
      java.text.DateFormat df = new java.text.SimpleDateFormat(
          "yyyy-MM-dd HH:mm:ss");
      return df.parse(dateString);
    }
    catch (ParseException ex) {
      ex.printStackTrace();
      return null;
    }

  }

  public static String format(Date date) {
    return format(date, "yyyy-MM-dd HH:mm:ss");
  }

  /**
   * 根据指定的pattern格式化类型为String的日期
   * update by zhuzf
   *
   * @param dateStr
   * @param pattern
   * @return
   */
  public static String format(String dateStr, String pattern) {
    java.text.SimpleDateFormat df =null; 
    if(dateStr!=null&&dateStr.length()>=19){
        df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault());
    }else if(dateStr!=null && dateStr.length()==10){
        df=new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
    }
        
    java.util.Date date = null;
    try {
/*        if(dateStr!=null&&dateStr.length()>10){
            dateStr=dateStr.substring(0,10);
        }
*/        
      date = df.parse(dateStr);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return format(date, pattern);
  }

  //毫秒 MSEL millisecond
  public static long convertToMSEL(String source, String pattern) {
    if (pattern == null) {
      return ERROR;
    }
    pattern = pattern.trim().toLowerCase();
    if (!PATTERN_DDHHMM.equals(pattern)) {
      return ERROR;
    }
    String[] ms = StringUtil.toArray(source, PATTERN_SP1);
    if (ms.length != 3) {
      return ERROR;
    }
    int dd = 0;
    int hh = 0;
    int mm = 0;
    try {
      dd = Integer.parseInt(ms[0]);
      hh = Integer.parseInt(ms[1]);
      mm = Integer.parseInt(ms[2]);
    }
    catch (NumberFormatException ex) {
      return ERROR;
    }
    return ( (dd * DAY_HOURS + hh) * HOUR_MINUTES + mm) * 60 * 1000;
  }

  public final static java.util.List separateDateStr(String startDate,
      String endDate, long step) {
    java.text.DateFormat df = new java.text.SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss");
    java.util.List rtn = separateDate(startDate, endDate, step);
    int size = rtn.size();
    for (int i = 0; i < size; i++) {
      rtn.set(i, df.format(rtn.get(i)));
    }
    return rtn;
  }

  public final static java.util.List separateDate(String startDate,
                                                  String endDate, long step) {
    Date d1 = parse(startDate);
    Date d2 = parse(endDate);
    long start = d1.getTime();
    long end = d2.getTime();
    if (start > end) {
      throw new IllegalArgumentException("开始日期（" + startDate + "）晚于结束日期（" +
                                         endDate + "）");
    }
    java.util.List rtn = new java.util.ArrayList();
    long tmp = start;
    while (end > tmp) {
      rtn.add(new Date(tmp));
      tmp += step;
    }
    rtn.add(new Date(tmp));
    return rtn;
  }
  /**
   * 比较日期字符串大小
   * 如果d1>d2返回1
   * 如果d1=d2返回0
   * 如果d1<d2返回-1
   * @param d1
   * @param d2
   */
  public static int compareDateStr(String d1,String d2){
      Date date1=parse(d1);
      Date date2=parse(d2);
      return compareDate(date1,date2);
  }
  public static void main(String[] s) {
//    String d1 = "2004-08-20";
//    String d2 = "2004-08-21";
//    //System.out.println("" + parse(d1).getTime());
//    String source = "00:10:00";
//    long step = convertToMSEL(source, PATTERN_DDHHMM);
//    java.util.List l = separateDate(d1, d2, step);
//    for (int i = 0; i < l.size(); i++) {
//      //System.out.println("【" + i + "】：＝" + l.get(i));
//    } // End for-i
//    String date = format(new Date(), "MM/dd/yyyy HH:mm:ss"); //"yyyy-MM-dd HH:mm:ss"
//    System.out.println("date = " + date);
      System.out.println("sss:"+format("2006-08-26","yyyy年MM月dd日"));
  }
  /**
   * 日期加减
   *
   * @param  format y:年运算 m:月运算 d：日运算
   * @param  number 加减天数
   * @param  strYYYYMMDD 被运算日期
   * @return 运算后结果日期
   */
  public static String addDate (String format, int number, String strYYYYMMDD) {
      Calendar cal = null;
      String strDate = "";
      Date date = null;
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      sdf.setLenient(false);
      try {
          cal = Calendar.getInstance();
          cal.clear();
          date = sdf.parse(strYYYYMMDD);
          cal.setTime(date);

          if (format.toLowerCase().equals("y") == true) {
              cal.add(Calendar.YEAR, number);
          } else {
              if (format.toLowerCase().equals("m") == true) {
                  cal.add(Calendar.MONTH, number);
                  
              } else {
                  cal.add(Calendar.DATE, number);
              }
          }
          date = cal.getTime();
          strDate = sdf.format(date);
      }
      catch (Exception e) {
          strDate = "";
      }
      return strDate;
  }
  
  /**
   * 在日期上加一定的毫秒得到一个新日期
   * @param date 被运算的日期
   * @param milliseconds 加减的毫秒数
   * @return
   */
  public static Date addMillisecond(Date date,long milliseconds){
      long baseMilliseconds = date.getTime();
      Date newDate = new Date();
      newDate.setTime(baseMilliseconds+milliseconds);
      return newDate;
  }

}