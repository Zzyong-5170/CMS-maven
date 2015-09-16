package cosmos.cms.common.web.service.I;

import java.util.List;
import java.util.Map;

/**
 * 业务层接口层
 * @author zhuzhaoyong
 * @date 2015-09-09 15:22:40
 */
public interface AuthorizationManageServiceI {

	/**
	 * 获取菜单资源
	 * @param username
	 * @return
	 */
	public Map<Object, List<Object>> getMenuResources(String username) throws Exception;
}
