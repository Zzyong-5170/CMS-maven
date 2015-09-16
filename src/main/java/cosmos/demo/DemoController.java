package cosmos.demo;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cosmos.cms.common.shiro.SysUserRealm;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {

	private Logger log = Logger.getLogger(getClass());

	@Autowired
	private SysUserRealm sysUserRealm;

	@RequiresPermissions(value = {"sys"})
	@RequestMapping(value = "/demo")
	public String demo() {
		return "demo";
	}

	@RequestMapping(value ="/clearCache")
	@ResponseBody
	public boolean clearCache() {
		sysUserRealm.clearSysUserCache();
		return true;
	}

	/*@RequestMapping(value ="/login")
	public String login(String username, String password) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		subject.login(token);
		return "redirect:demo";
	}*/
}
