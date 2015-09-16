package cosmos.cms.common.web.model;

import java.io.Serializable;

/**
 * 系统用户实体类信息
 * 
 * @author zhuzhaoyong
 *
 */
@SuppressWarnings("serial")
public class SysUserModel extends BaseModel implements Serializable {

	private String id;
	private String sysUserId;
	private String sysName;
	private String sysPassword;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getSysPassword() {
		return sysPassword;
	}

	public void setSysPassword(String sysPassword) {
		this.sysPassword = sysPassword;
	}

	@Override
	public String toString() {
		return "SysUserEntity [id=" + id + ", sysUserId=" + sysUserId + ", sysUsername=" + sysName
				+ ", sysPassword=" + sysPassword + ", toString()=" + super.toString() + "]";
	}

}
