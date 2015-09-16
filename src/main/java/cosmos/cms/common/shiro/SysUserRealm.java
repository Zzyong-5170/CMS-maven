package cosmos.cms.common.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cosmos.cms.common.exception.SysUserDBException;
import cosmos.cms.common.web.dao.I.AuthorizationManageDAOI;
import cosmos.cms.common.web.model.SysUserModel;
import cosmos.cms.common.web.service.AuthorizationManageService;

/**
 * @description 自定义shiro域
 * @function
 *  a.系统用户资源菜单缓存
 *  b.系统用户权限缓存(shiro本身实现)
 *  c.登陆用户身份认证
 * @extends AuthorizingRealm
 * 继承关系
 *  SysUserRealm
 *      AuthorizingRealm
 *          AuthenticatingRealm
 *              CachingRealm
 * @see org.apache.shiro.realm.AuthorizingRealm 权限域管理
 * @see org.apache.shiro.realm.AuthenticatingRealm 身份认证域管理
 * @see org.apache.shiro.realm.CachingRealm 缓存域管理
 * @author zhuzhaoyong
 * @date 2015-09-09 12:10:07
 * @version 1.0
 */
public class SysUserRealm extends AuthorizingRealm {

	private Logger log = Logger.getLogger(getClass()); // log

	/**
	 * The default suffix appended to the realm name for caching Resources instances.
	 */
	private static final String DEFAULT_RESOURCES_CACHE_SUFFIX = ".resourcesCache"; // 在配置文件不提供换要使用的ehcache缓存名称的情况下，用来设置默认要使用的缓存名称

	private static final AtomicInteger INSTANCE_COUNT = new AtomicInteger();

	private PasswordService passwordService; // 密码机制管理service层，shiro框架集成号的

	private PrincipalCollection principals; // 定义全局principals提供给外部调用接口的对象使用

	@Autowired
	private AuthorizationManageDAOI authorizationManageDAOI; // 注入权限获取DAO层

	@Autowired
	private AuthorizationManageService authorizationManageService; // 注入权限管理service层

	/**
	 * 以下三个变量分别是
	 * 1.是否启用自定义的缓存机制
	 * 2.缓存对象类
	 * 3.要使用的ehcache缓存的名称
	 */
	private boolean resourcesCachingEnabled; // 是否启用自定义的缓存机制
	private Cache<Object, Map<Object, List<Object>>> resourcesCache; // 资源缓存对象
	private String resourcesCacheName; // 资源缓存名称，对应ehcache.xml配置的cache的name值

	public SysUserRealm() {
		super();
		this.resourcesCachingEnabled = true;
		// 设置默认cache名称
		int instanceNumber = INSTANCE_COUNT.getAndIncrement();
		this.resourcesCacheName = getClass().getName() + DEFAULT_RESOURCES_CACHE_SUFFIX;
		if (instanceNumber > 0) {
			this.resourcesCacheName = this.resourcesCacheName + "." + instanceNumber;
		}
	}

	public void setPasswordService(PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	public void setResourcesCacheName(String resourcesCacheName) {
		this.resourcesCacheName = resourcesCacheName;
	}

	public String getResourcesCacheName() {
		return resourcesCacheName;
	}

	public boolean isResourcesCachingEnabled() {
		return resourcesCachingEnabled;
	}

	public void setResourcesCachingEnabled(boolean resourcesCachingEnabled) {
		this.resourcesCachingEnabled = resourcesCachingEnabled;
	}

	public Cache<Object, Map<Object, List<Object>>> getResourcesCache() {
		return resourcesCache;
	}

	public void setResourcesCache(Cache<Object, Map<Object, List<Object>>> resourcesCache) {
		this.resourcesCache = resourcesCache;
	}

	public PrincipalCollection getPrincipals() {
		return principals;
	}

	public void setPrincipals(PrincipalCollection principals) {
		this.principals = principals;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		setPrincipals(principals);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		if(null == principals) {
			return authorizationInfo;
		}
		String username = (String) principals.getPrimaryPrincipal();
		try {
			// 取得当前系统用户登陆成功后的所有可用资源权限
			List<String> sysUsersResourcesPermissions = authorizationManageDAOI.getSysUsersResourcesPermissions(username);
			Set<String> permissions = new HashSet<String>();
			permissions.addAll(sysUsersResourcesPermissions);
			authorizationInfo.setStringPermissions(permissions);
		} catch (Exception e) {
			log.error("从数据库中取菜单资源出现异常");
			// 权限为空
		}
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		// 1
		if(StringUtils.isBlank(username)) {
			throw new UnknownAccountException("登陆账户不存在!");
		}
		SysUserModel identifiedInfo = null;
		try {
			identifiedInfo = authorizationManageDAOI.getIdentifiedInfo(username);
		} catch (Exception e) {
			// 2
			log.error("调用数据库获取系统用户信息出现异常");
			throw new SysUserDBException("调用数据库获取系统用户信息出现异常");
		}
		// 3
		if(null == identifiedInfo) {
			throw new UnknownAccountException("登陆账户不存在!");
		}
		// 4
		String dbPassword = identifiedInfo.getSysPassword();
		password = passwordService.encryptPassword(password);
		if(!dbPassword.equals(password)) {
			throw new IncorrectCredentialsException("登陆用户密码输入错误!");
		}
		log.info("用户登陆成功，并将登陆信息返回");
		return new SimpleAuthenticationInfo(username, dbPassword, getName());
	}

	@Override
	protected void onInit() {
		super.onInit();
		// TODO
		getAvailableResourcesCache();
	}

	@Override
	protected void afterCacheManagerSet() {
		super.afterCacheManagerSet();
		// TODO
		getAvailableResourcesCache();
	}

	private Cache<Object, Map<Object, List<Object>>> getAvailableResourcesCache() {
		Cache<Object, Map<Object, List<Object>>> cache = getResourcesCache();
		if(isCachingEnabled() && resourcesCachingEnabled) {
			cache = getAvailableResourcesCacheLazy();
		}
		return cache;
	}

	/**
	 * 检查外部是否引入cache实例，如果引用，直接使用
	 * @return
	 */
	private Cache<Object, Map<Object, List<Object>>> getAvailableResourcesCacheLazy() {
		if(this.resourcesCache == null) {
			CacheManager cacheManage = getCacheManager();
			if(null != cacheManage) {
				this.resourcesCache = cacheManage.getCache(resourcesCacheName);
			} else {
				// 没有找到对应的缓存设置，不做任何的操作
			}
		}
		return this.resourcesCache;
	}

	/**
	 * 提供到外面的接口
	 * 获取缓存资源
	 * @param principals
	 * @return
	 * @throws Exception 
	 */
	public Map<Object, List<Object>> getResourcesCacheInfo(PrincipalCollection principals) throws Exception {
		if(null == principals) {
			return null;
		}
		Map<Object, List<Object>> info = null;
		/*
		 * 1.如果从缓存中查询出信息，则直接从缓存中获取
		 * 2.如果缓存中不存在查询的资源，则调用数据库获取
		 * 3.并且要将从数据库中获取的数据放到缓存中使用，以便下次直接从缓存中获取，提高执行效率
		 */
		Cache<Object, Map<Object, List<Object>>> cache = getAvailableResourcesCache();
		// 1
		if(null != cache) {
			Object cacheKey = getResourcesCacheKey(principals);
			info = cache.get(cacheKey);
		}
		// 2
		if(null == info) {
			info = doGetResourcesInfo(principals);
			// 3
			Object cacheKey = getResourcesCacheKey(principals);
			cache.put(cacheKey, info);
		}
		return info;
	}

	/**
	 * 在没有缓存的情况下，需要从数据库中获取资源信息
	 * @param principals
	 * @return
	 * @throws Exception
	 */
	private Map<Object, List<Object>> doGetResourcesInfo(
			PrincipalCollection principals) throws Exception {
		setPrincipals(principals);
		if(null == principals) {
			return null;
		}
		String username = (String) principals.getPrimaryPrincipal();
		Map<Object, List<Object>> menuResources = authorizationManageService.getMenuResources(username);
		return menuResources;
	}

	/**
	 * 生成资源缓存对象的key值 唯一的
	 * @param principals
	 * @return
	 */
	private Object getResourcesCacheKey(PrincipalCollection principals) {
		return principals + DEFAULT_RESOURCES_CACHE_SUFFIX;
	}

	/**
	 * 清空资源缓存
	 * @param principals
	 */
	private void clearCachedResourcesInfo(PrincipalCollection principals) {
		if (principals == null) {
			return;
		}

		Cache<Object, Map<Object, List<Object>>> cache = getAvailableResourcesCache();
		//cache instance will be non-null if caching is enabled:
		if (cache != null) {
			Object key = getResourcesCacheKey(principals);
			cache.remove(key);
		}
	}

	@Override
	protected void doClearCache(PrincipalCollection principals) {
		super.doClearCache(principals);
		clearCachedResourcesInfo(principals);
	}

	/**
	 * 对外提供接口，清空缓存
	 */
	public void clearSysUserCache() {
		doClearCache(getPrincipals());
	}
}
