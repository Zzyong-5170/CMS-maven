package cosmos.cms.common.web.dao.I;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cosmos.cms.common.web.model.ResourcesTreeModel;

/**
 * 获取资源数据
 * @author zhuzhaoyong
 * @date 2015-09-13 20:48:30
 */
@Repository
public interface ResourcesTreeDAOI {

	/**
	 * 获取资源数据集合
	 * @return
	 * @throws Exception
	 */
	public List<ResourcesTreeModel> getResourcesTreeData(@Param("id") String id) throws Exception;
}
