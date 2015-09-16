package cosmos.cms.common.web.model;

import java.io.Serializable;

/**
 * 资源信息实体类
 * 
 * @author zhu zhy
 *
 */
@SuppressWarnings("serial")
public class ResourcesModel extends BaseModel implements Serializable {

	private String id;
	private String sysResourcesId;
	private String sysResourcesName;
	private String sysResourcesUrl;
	private String sysResourcesParentId;
	private String sysResourcesPermission;
	private String sysResourcesIcon;
	private String sysResourcesSeq;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysResourcesId() {
		return sysResourcesId;
	}

	public void setSysResourcesId(String sysResourcesId) {
		this.sysResourcesId = sysResourcesId;
	}

	public String getSysResourcesName() {
		return sysResourcesName;
	}

	public void setSysResourcesName(String sysResourcesName) {
		this.sysResourcesName = sysResourcesName;
	}

	public String getSysResourcesUrl() {
		return sysResourcesUrl;
	}

	public void setSysResourcesUrl(String sysResourcesUrl) {
		this.sysResourcesUrl = sysResourcesUrl;
	}

	public String getSysResourcesParentId() {
		return sysResourcesParentId;
	}

	public void setSysResourcesParentId(String sysResourcesParentId) {
		this.sysResourcesParentId = sysResourcesParentId;
	}

	public String getSysResourcesPermission() {
		return sysResourcesPermission;
	}

	public void setSysResourcesPermission(String sysResourcesPermission) {
		this.sysResourcesPermission = sysResourcesPermission;
	}

	public String getSysResourcesIcon() {
		return sysResourcesIcon;
	}

	public void setSysResourcesIcon(String sysResourcesIcon) {
		this.sysResourcesIcon = sysResourcesIcon;
	}

	public String getSysResourcesSeq() {
		return sysResourcesSeq;
	}

	public void setSysResourcesSeq(String sysResourcesSeq) {
		this.sysResourcesSeq = sysResourcesSeq;
	}

	@Override
	public String toString() {
		return "ResourcesEntity [id=" + id + ", sysResourcesId=" + sysResourcesId + ", sysResourcesName="
				+ sysResourcesName + ", sysResourcesUrl=" + sysResourcesUrl + ", sysResourcesParentId="
				+ sysResourcesParentId + ", sysResourcesPermission=" + sysResourcesPermission + ", sysResourcesIcon="
				+ sysResourcesIcon + ", sysResourcesSeq=" + sysResourcesSeq + ", toString()=" + super.toString() + "]";
	}
}
