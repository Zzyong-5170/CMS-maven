package cosmos.weixin.common.web.service.I;

import cosmos.weixin.common.web.model.AccessTokenModel;

/**
 * 微信服务接口
 * @author zhuzhaoyong
 *
 */
public interface WXServiceI {

	/**
	 * 获取微信用户access_token
	 * @return
	 * @throws Exception
	 */
	public AccessTokenModel getAccessToken() throws Exception;

	/**
	 * 保存 access_token
	 * @param accessTokenModel
	 * @throws Exception
	 */
	public void save(AccessTokenModel accessTokenModel) throws Exception;

	/**
	 * 更新 access_token
	 * @param accessTokenModel
	 * @throws Exception
	 */
	public void update(AccessTokenModel accessTokenModel) throws Exception;
}
