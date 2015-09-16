package cosmos.cms.common.web.dao.I;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cosmos.cms.common.web.model.ResourcesModel;
import cosmos.cms.common.web.model.SysUserModel;

/**
 * 权限管理DAO
 * @author zhu zhy
 * @date 2015-09-07 16:05:05
 *
 */
@Repository
public interface AuthorizationManageDAOI {

	/**
	 * 获取当前系统用户的资源信息
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public List<ResourcesModel> getSysUsersResources(@Param("username") String username) throws Exception;

	/**
	 * 获取当前用户的权限标识
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public List<String> getSysUsersResourcesPermissions(@Param("username") String username) throws Exception;

	/**
	 * 获取系统用户信息
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public SysUserModel getIdentifiedInfo(@Param("username") String username) throws Exception;
}
