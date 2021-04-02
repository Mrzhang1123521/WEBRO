package cn.webro.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	//yyyy年MM月dd日 HH时mm分ss秒
	public static final String CHINESE_DATE_TIME = "yyyy年MM月dd日 HH时mm分ss秒";
	//yyyy-MM-dd HH:mm:ss
	public static final String CHINESE_DATE_TIME_2 = "yyyy-MM-dd HH:mm:ss";
	public static final String CHINESE_DATE_TIME_3 = "yyyy-MM-dd";
	/**
	 *  2018-05-21T10:54:56.955+08:00 时间转化为yyyy-MM-dd HH:mm:ss
	 * @param time
	 * @return
	 */
	public static String timeT(String time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date date;
		try {
			date = formatter.parse(time);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sDate=sdf.format(date);
			return sDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "失败";
		}
	}
	
	public static Date timeTDate(String time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		try {
			Date date = formatter.parse(time);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date parse = sdf.parse(sdf.format(date));
			return parse;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	//时间戳转Date
	public static Date timeStampToDate(long timeStamp){
		Date date = new Date(timeStamp);
		return date;
	}
	
	/**
	 * 字符串转yyyy-MM-dd
	 * @param timeStamp
	 * @return
	 */
	public static String timeStampToDateStr(String timeStamp){
		Date date = new Date(Long.parseLong(timeStamp));
		System.out.println(date);
		SimpleDateFormat sdf = new SimpleDateFormat(CHINESE_DATE_TIME_3);
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	//时间戳转字符串2
	public static String timeStampToDateStr(long timeStamp, String patten){
		Date date = new Date(timeStamp);
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	//Date转时间戳
	public static long DateToTimeStamp(Date date){
		long timeStamp = date.getTime();
		return timeStamp;
	}
	
	//Date转字符串
	public static String DateToDateStr(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(CHINESE_DATE_TIME_3);
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	//Date转字符串2
	public static String DateToDateStr(Date date, String patton){
		SimpleDateFormat sdf = new SimpleDateFormat(patton);
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	//字符串转时间戳
	public static long DateStrToTimeStamp(String dateStr, String patten){
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		
		long timeStamp = 0;
		try {
			Date date = sdf.parse(dateStr);
			timeStamp = date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timeStamp;
	}
	
	//字符串转Date
	public static Date DateStrToDate(String dateStr, String patten){
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		
		Date date = null;
		try{
			date = sdf.parse(dateStr);
		} catch(ParseException e){
			e.printStackTrace();
		}
		return date;
	}
}