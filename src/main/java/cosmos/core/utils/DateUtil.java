package cosmos.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author zhuzhaoyong
 * @since 2015.06.29
 */
public class DateUtil {

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DateUtil.class);
	
	private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss"; // 时间戳格式
	private static final String DATE_FORMAT = "yyyy-MM-dd"; // 普通时间格式
	
	/**
	 * 获取当前时间戳的字符串
	 * 格式: DATETIME_FORMAT
	 * @return
	 */
	public static String getCurStringDatetime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
		return dateFormat.format(new Date());
	}
	
	/**
	 * 获取当前时间的字符串
	 * 格式: DATE_FORMAT
	 * @return
	 */
	public static String getCurStringDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		return dateFormat.format(new Date());
	}
	
	/**
	 * 将给定的字符串时间戳转换成日期类型时间戳
	 * @param source
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDatetime(String source) throws ParseException {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
			return dateFormat.parse(source);
		} catch (ParseException e) {
			log.error("所给时间字符串的格式与给定的格式" + DATETIME_FORMAT + "无法统一.");
			throw e;
		}
	}
	
	/**
	 * 将给定的字符串时间转换成日期类型时间
	 * @param source
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDate(String source) throws ParseException {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			return dateFormat.parse(source);
		} catch (ParseException e) {
			log.error("所给时间字符串的格式与给定的格式" + DATE_FORMAT + "无法统一.");
			throw e;
		}
	}

	/**
	 * 获取当前时间毫秒值
	 */
	public static long getCurDateSeconds() {
		return System.currentTimeMillis();
	}

	public static void main(String[] args) {
		System.out.println(getCurStringDatetime());
		System.out.println(getCurStringDate());
		try {
			System.out.println(strToDate("1992-01-01 12:01:01"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
