package cosmos.weixin.common.web.service.I;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cosmos.weixin.common.web.dao.I.WXDAOI;
import cosmos.weixin.common.web.model.AccessTokenModel;

/**
 * 微信服务实现类
 * @author zhuzhaoyong
 *
 */
@Service
public class WXService implements WXServiceI {

	@Autowired
	private WXDAOI wxDAOI;

	@Override
	public AccessTokenModel getAccessToken() throws Exception {
		List<AccessTokenModel> list = wxDAOI.getAccessTokens();
		if(null == list) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public void save(AccessTokenModel accessTokenModel) throws Exception {
		wxDAOI.save(accessTokenModel);
	}

	@Override
	public void update(AccessTokenModel accessTokenModel) throws Exception {
		wxDAOI.update(accessTokenModel);
	}

}
