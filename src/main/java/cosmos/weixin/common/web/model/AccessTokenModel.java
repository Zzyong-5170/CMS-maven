package cosmos.weixin.common.web.model;

import java.util.Date;

/**
 * access_token实体类
 * @author zhuzhaoyong
 *
 */
public class AccessTokenModel {

	private String id;
	private String accessTokenId;
	private String accessTokenValue; // access_token
	private int accessTokenExpiresTime; // 失效时间
	private Date accessTokenAddTime; // 添加时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccessTokenId() {
		return accessTokenId;
	}

	public void setAccessTokenId(String accessTokenId) {
		this.accessTokenId = accessTokenId;
	}

	public String getAccessTokenValue() {
		return accessTokenValue;
	}

	public void setAccessTokenValue(String accessTokenValue) {
		this.accessTokenValue = accessTokenValue;
	}

	public int getAccessTokenExpiresTime() {
		return accessTokenExpiresTime;
	}

	public void setAccessTokenExpiresTime(int accessTokenExpiresTime) {
		this.accessTokenExpiresTime = accessTokenExpiresTime;
	}

	public Date getAccessTokenAddTime() {
		return accessTokenAddTime;
	}

	public void setAccessTokenAddTime(Date accessTokenAddTime) {
		this.accessTokenAddTime = accessTokenAddTime;
	}
}
