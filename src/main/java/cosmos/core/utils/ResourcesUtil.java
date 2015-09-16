package cosmos.core.utils;

import java.util.ResourceBundle;

/**
 * 资源文件读取工具类
 * @author zhuzhaoyong
 *
 */
public class ResourcesUtil {

	private static final ResourceBundle bundle = ResourceBundle.getBundle("weixin/wx");

	public static String getPropertiesValue(String key) {
		return bundle.getString(key);
	}
}
