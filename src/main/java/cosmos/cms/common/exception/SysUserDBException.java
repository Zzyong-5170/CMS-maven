package cosmos.cms.common.exception;

import org.apache.shiro.ShiroException;

@SuppressWarnings("serial")
public class SysUserDBException extends ShiroException {

	public SysUserDBException(String message) {
		super(message);
	}

	public SysUserDBException(String message, Throwable cause) {
		super(message, cause);
	}
}
