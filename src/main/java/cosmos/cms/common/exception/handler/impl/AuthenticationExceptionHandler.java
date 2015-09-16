package cosmos.cms.common.exception.handler.impl;

import org.springframework.web.servlet.ModelAndView;

import cosmos.cms.common.exception.handler.GlobalExceptionHandler;

/**
 * 实现自己的对于业务异常的处理
 * @author zhuzhaoyong
 *
 */
public class AuthenticationExceptionHandler extends GlobalExceptionHandler {

	private final static String VIEW_NAME = "error";

	@Override
	public ModelAndView handlerOtherException() {
		return new ModelAndView(VIEW_NAME);
	}

}
