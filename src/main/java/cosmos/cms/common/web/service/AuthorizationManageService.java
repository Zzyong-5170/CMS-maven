package cosmos.cms.common.web.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cosmos.cms.common.web.dao.I.AuthorizationManageDAOI;
import cosmos.cms.common.web.model.ResourcesModel;
import cosmos.cms.common.web.service.I.AuthorizationManageServiceI;

/**
 * 业务层实现
 * @author zhuzhaoyong
 * @date 2015-09-09 14:22:26
 */
@Service
public class AuthorizationManageService implements AuthorizationManageServiceI {

	@Autowired
	private AuthorizationManageDAOI authorizationManageDAOI;

	@Override
	public Map<Object, List<Object>> getMenuResources(String username) throws Exception {
		List<ResourcesModel> sysUsersResourcesList = authorizationManageDAOI.getSysUsersResources(username);
		Map<Object, List<Object>> result = compositeDateByLevel(sysUsersResourcesList);
		return result;
	}

	/**
	 * 目前菜单采用两级目录的形式来显示，如果需要更多级的展示需要重写所有与菜单资源有关的东西
	 * @param sysUsersResourcesList
	 * @return
	 */
	private Map<Object, List<Object>> compositeDateByLevel(
			List<ResourcesModel> sysUsersResourcesList) {
		if(null == sysUsersResourcesList) {
			return null;
		}
		/*
		 * 表设计
		 *  第一级目录的parent id == 0
		 *  第二级目录的parent id == 上级id
		 */
		// 1.找出所有父元素
		// 2.根据父元素找出所有子元素
		// 3.拼map
		// TODO 后期清理掉垃圾代码
		//Map<Object, List<Object>> result = new HashMap<Object, List<Object>>();
		// 此处需要对菜单的父节点根据seq进行排序
		// TreeMap实现，需要自定义比较器
		TreeMap<Object, List<Object>> result = new TreeMap<Object, List<Object>>(new ResourcesComparator()); // 结果集容器
		List<ResourcesModel> parentList = new ArrayList<ResourcesModel>(); // 父元素list容器
		List<Object> childList = null; // 对应子元素容器
		// 遍历找出所有的父元素
		for(ResourcesModel re : sysUsersResourcesList) {
			if(null != re) {
				if("0".equals(re.getSysResourcesParentId())) {
					parentList.add(re);
				}
			}
		}
		sysUsersResourcesList.removeAll(parentList);
		for(ResourcesModel parent : parentList) {
			childList = new ArrayList<Object>();
			for(ResourcesModel child : sysUsersResourcesList) {
				String parentId = parent.getSysResourcesId();
				if(null != parentId) {
					if(parentId.equals(child.getSysResourcesParentId())) {
						childList.add(child);
					}
				}
			}
			// 对子节点按照seq排序
			Collections.sort(childList, new ResourcesComparator());
			result.put(parent, childList);
		}
		return result;
	}

	/**
	 * 私有内部类
	 * 自定义比较器，用来对从数据库中查询出来的资源按照seq排序
	 * @author zhuzhaoyong
	 *
	 */
	private class ResourcesComparator implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {
			if(o1 instanceof ResourcesModel && o2 instanceof ResourcesModel
					&& o1 != null && o2 != null) {
				ResourcesModel re1 = (ResourcesModel)o1;
				ResourcesModel re2 = (ResourcesModel)o2;
				String seq1 = re1.getSysResourcesSeq();
				String seq2 = re2.getSysResourcesSeq();
				if(seq1 != null) {
					return seq1.compareTo(seq2);
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		}
	}
}
