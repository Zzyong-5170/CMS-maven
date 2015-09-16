package cosmos.weixin.common.web.dao.I;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cosmos.weixin.common.web.model.AccessTokenModel;

/**
 * 微信DAO
 * @author zhuzhaoyong
 *
 */
@Repository
public interface WXDAOI {

	/**
	 * 获取access_token
	 * @return
	 * @throws Exception
	 */
	public List<AccessTokenModel> getAccessTokens() throws Exception;

	/**
	 * 保存 access_token
	 * @param accessTokenModel
	 * @throws Exception
	 */
	public void save(@Param("accessTokenModel") AccessTokenModel accessTokenModel) throws Exception;

	/**
	 * 更新 access_token
	 * @param accessTokenModel
	 * @throws Exception
	 */
	public void update(@Param("accessTokenModel") AccessTokenModel accessTokenModel) throws Exception;
}
