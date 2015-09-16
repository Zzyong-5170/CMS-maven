package cosmos.cms.common.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登陆controller
 * @author zhuzhaoyong
 * @date 2015-09-13 16:06:00
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/sysLogin")
public class LoginController {

	@RequestMapping(value = {"/login", ""})
	public String login(String username, String password) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		subject.login(token); // 调用shiro的登陆进行登陆操作
		// 继续执行,shiro已经登陆成功
		//TODO 执行自己的业务逻辑
		return "redirect:/demo/demo";
	}
}
