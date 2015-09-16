package cosmos.core.utils;

import java.util.List;

import com.alibaba.fastjson.JSONArray;

/**
 * @description json数据处理
 * @author zhuzhaoyong
 * @date 2015-09-13 20:28:30
 */
@SuppressWarnings("rawtypes")
public class JSONUtil {

	/**
	 * 将集合装换成json
	 * @param collection
	 * @return
	 */
	public static String CollectionToJSONString(List collection) {
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(collection);
		return jsonArray.toString();
	}
}
