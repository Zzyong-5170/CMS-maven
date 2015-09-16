package cosmos.cms.common.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库表基础字段实体类
 * @author zhuzhaoyong
 * @date 2015-09-10 15:53:20
*/
@SuppressWarnings("serial")
public class BaseModel implements Serializable {

	private String createBy;
	private Date createTime;
	private String updateBy;
	private Date updateTime;

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
