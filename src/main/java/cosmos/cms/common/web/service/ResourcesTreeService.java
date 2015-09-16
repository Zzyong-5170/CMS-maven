package cosmos.cms.common.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cosmos.cms.common.web.dao.I.ResourcesTreeDAOI;
import cosmos.cms.common.web.model.ResourcesTreeModel;
import cosmos.cms.common.web.service.I.ResourcesTreeServiceI;
import cosmos.core.utils.JSONUtil;

/**
 * 获取资源树数据service层
 * @author zhuzhaoyong
 * @date 2015-09-13 20:50:00
 */
@Service
public class ResourcesTreeService implements ResourcesTreeServiceI {

	@Autowired
	private ResourcesTreeDAOI resourcesTreeDAOI;

	@Override
	public String getResourcesJsonData(String id) throws Exception {
		List<ResourcesTreeModel> resourcesTreeData = resourcesTreeDAOI.getResourcesTreeData(id);
		String resourcesTreeJsonData = JSONUtil.CollectionToJSONString(resourcesTreeData);
		return resourcesTreeJsonData;
	}
}
