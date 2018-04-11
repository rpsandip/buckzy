package com.buckzy.login.post.event;

import java.io.IOException;

import org.apache.http.Header;
import org.osgi.service.component.annotations.Component;

import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.common.util.BuckzyConstants;
import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author sandip
 */

@Component(
		immediate = true, property = {"key=login.events.post"},
		service = LifecycleAction.class
	)
public class BuckzyLoginPostEventActivator  implements LifecycleAction{
 
	Log _log = LogFactoryUtil.getLog(BuckzyLoginPostEventActivator.class.getName());
	
	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {

		_log.info(lifecycleEvent.getRequest().getAttribute(WebKeys.USER_ID));
		long userId = (long)lifecycleEvent.getRequest().getAttribute(WebKeys.USER_ID);
		
		User user;
		try {
			user = UserLocalServiceUtil.getUser(userId);
			
			//String password = (String)lifecycleEvent.getRequest().getSession().getAttribute("user_token");
			PortalCache portalCache =   MultiVMPoolUtil.getCache(User.class.getName());
			String password = (String)portalCache.get("user_token");
			
			// Create Security token for logIn users.
			String token = BuckzyCommonLocalServiceUtil.getToken(user.getEmailAddress(),password);
			
			String skippEmailForRestAuth = PropsUtil.get("email.skip.rest.auth");
			
			if(Validator.isNull(token) && skippEmailForRestAuth.indexOf(user.getEmailAddress())<0){
				lifecycleEvent.getRequest().getSession().invalidate();
				try {
					lifecycleEvent.getResponse().sendRedirect("/web/guest/sign-in?login=invalid");
				} catch (IOException e) {
					_log.error(e);
				}
			}else{
				lifecycleEvent.getRequest().getSession().setAttribute("token", token);
			}
		
		} catch (PortalException e) {
			_log.error(e);
		}
	}	
		
}