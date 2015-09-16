package cosmos.core.utils;

import java.util.Random;

/**
 * ID生成器
 * 生成规则: 三位类型前缀 + 13位当前时间毫秒值 + 四位随机数 (20位字符串)
 * @author zhu zhy
 *
 */
public class IDGeneratorUtil {

	private final static String ID_PREFIX = "ff-";

	public static String getID(String typePrefix) {
		StringBuffer id = new StringBuffer();

		id.append(typePrefix);
		id.append(DateUtil.getCurDateSeconds());
		id.append(get4Random());

		return id.toString();
	}

	private static int get4Random() {
		Random random = new Random();
		int result = 1000 + random.nextInt(9999);
		return result;
	}

	public static String getUUID() {
		StringBuffer uuid = new StringBuffer();
		long seconds = System.currentTimeMillis();
		uuid.append(ID_PREFIX);
		uuid.append(seconds);
		uuid.append(get4Random());
		return uuid.toString();
	}

	public static void main(String[] args) {
		//System.out.println(get3Random());
		System.out.println(getUUID());
	}
}
