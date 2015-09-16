package cosmos.cms.common.web.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cosmos.cms.common.web.service.ResourcesTreeService;

@Controller
@RequestMapping("treeController")
public class ResourcesTreeController {

	@Autowired
	private ResourcesTreeService resourcesTreeService;
	
	/**
	 * url访问格式：http://localhost:9999/treeController/tree?format=json
	 * 返回json数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = {"tree"})
	@ResponseBody
	public String getJsonTreeInfo(String id) {
		if(StringUtils.isBlank(id)) {
			id = "0";
		}
		String jsonData = null;
		try {
			Thread.sleep(2000);
			jsonData = resourcesTreeService.getResourcesJsonData(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonData;
	}
}
