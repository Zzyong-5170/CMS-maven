package cosmos.cms.common.web.service.I;

/**
 * 获取资源树数据service接口层
 * @author zhuzhaoyong
 * @date 2015-09-13 20:50:00
 */
public interface ResourcesTreeServiceI {

	/**
	 * 获取资源树数据（json格式）
	 * @param id
	 * @return
	 */
	public String getResourcesJsonData(String id) throws Exception;
}
