package cosmos.cms.common.web.model;

import java.io.Serializable;

/**
 * 树实体类
 * @author zhuzhaoyong
 *
 */
@SuppressWarnings("serial")
public class ResourcesTreeModel implements Serializable {

	private String id;
	private String parentId;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TreeEntity [id=" + id + ", parentId=" + parentId + ", name=" + name + "]";
	}
}
