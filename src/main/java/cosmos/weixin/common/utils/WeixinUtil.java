package cosmos.weixin.common.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;

import cosmos.weixin.common.web.model.AccessTokenModel;
import cosmos.weixin.common.web.service.I.WXService;
import net.sf.json.JSONObject;

/**
 * 微信工具类
 * 1.提供http访问封装方法
 * 2.access_token的获取
 *   解决方案:
 *     a.将从接口中获取的access_token存放到数据库中
 *     b.将从接口中获取的access_token放到缓存中去(用静态变量来实现)
 * 
 * @author zhuzhaoyong
 *
 */
public class WeixinUtil {
	private static Logger log = Logger.getLogger(WeixinUtil.class);

	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 菜单创建（POST） 限100（次/天）
	public static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	// 客服接口地址
	public static String SEND_MESSAGE_RUL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	//
	// private static final ResourceBundle bundle =
	// ResourceBundle.getBundle("weixin");

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			}

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}" + e.getMessage());
		}
		return jsonObject;
	}

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 * @throws Exception 
	 */
	public static AccessTokenModel getAccessToken(WXService wxService, String appid, String appsecret) throws Exception {
		// 第三方用户唯一凭证
		// String appid = bundle.getString("appId");
		// // 第三方用户唯一凭证密钥
		// String appsecret = bundle.getString("appSecret");

		AccessTokenModel accessTokenModel = getRealAccessToken(wxService);

		if (accessTokenModel != null) {
			java.util.Date end = new java.util.Date();
			if (end.getTime() - accessTokenModel.getAccessTokenAddTime().getTime() > accessTokenModel.getAccessTokenExpiresTime() * 1000) {
				String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
				JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
				// 如果请求成功
				if (null != jsonObject) {
					try {
						// 凭证过期更新凭证
						accessTokenModel.setId(accessTokenModel.getAccessTokenId());
						accessTokenModel.setAccessTokenExpiresTime(jsonObject.getInt("expires_in"));
						accessTokenModel.setAccessTokenValue(jsonObject.getString("access_token"));
						updateAccessToken(accessTokenModel, wxService);
					} catch (Exception e) {
						accessTokenModel = null;
						// 获取token失败
						String wrongMessage = "获取token失败 errcode:{} errmsg:{}" + jsonObject.getInt("errcode")
								+ jsonObject.getString("errmsg");
						log.error(wrongMessage);
					}
				}
				return accessTokenModel;
			} else {
				return accessTokenModel;
			}
		} else {
			String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
			JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
			// 如果请求成功
			if (null != jsonObject) {
				try {
					accessTokenModel = new AccessTokenModel();
					accessTokenModel.setAccessTokenExpiresTime(jsonObject.getInt("expires_in"));
					accessTokenModel.setAccessTokenValue(jsonObject.getString("access_token"));
					saveAccessToken(accessTokenModel, wxService);

				} catch (Exception e) {
					accessTokenModel = null;
					// 获取token失败
					String wrongMessage = "获取token失败 errcode:{} errmsg:{}" + jsonObject.getInt("errcode")
							+ jsonObject.getString("errmsg");
					log.error(wrongMessage);
				}
			}
			return accessTokenModel;
		}
	}

	/**
	 * 从数据库中读取凭证
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static AccessTokenModel getRealAccessToken(WXService wxService) throws Exception {
		return wxService.getAccessToken();
	}

	/**
	 * 保存凭证
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static void saveAccessToken(AccessTokenModel accessTockenModel, WXService wxService) throws Exception {
		wxService.save(accessTockenModel);
	}

	/**
	 * 更新凭证
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static void updateAccessToken(AccessTokenModel accessTockenModel, WXService wxService) throws Exception {
		wxService.update(accessTockenModel);
	}

}
