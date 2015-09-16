package cosmos.cms.common.exception.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cosmos.cms.common.exception.SysUserDBException;

/**
 * @description 异常处理抽象类
 * 提供一个全局异常处理的抽象类，实现系统用户认证的异常处理，并对外提供接口，供
 * 开发者对业务中出现的异常进行单独的处理。
 * @author zhuzhaoyong
 * @date 2015-09-13 15:31:28
 * @version 1.0
 */
public abstract class GlobalExceptionHandler implements HandlerExceptionResolver {

	private final static String VIEW_NAME = "login";

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception ex) {
		ModelAndView mv = null;
		if(ex instanceof AuthenticationException) {
			// 登陆异常统一处理
			mv = handlerLoginException(ex);
		} else {
			// 其余的异常统一处理
			// 开发用户通过继承改类，并且实现这个方法，来实现自己的错误处理方式
			mv = handlerOtherException();
		}
		return mv;
	}

	private ModelAndView handlerLoginException(Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>(); // 传递错误信息
		if(ex instanceof UnknownAccountException){
			// 用户不存在
			model.put("nameError", "用户名不存在");
		} else if(ex instanceof SysUserDBException) {
			// 调用数据出现异常
			model.put("dbError", "数据库调用异常");
		} else if(ex instanceof IncorrectCredentialsException) {
			// 密码错误
			model.put("passwordError", "密码错误");
		}
		ModelAndView mv = new ModelAndView(VIEW_NAME, model);
		return mv;
	}

	/**
	 * 提供给用户来实现自己的错误页面处理方式
	 * @return
	 */
	public abstract ModelAndView handlerOtherException();

}
