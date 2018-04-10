package com.buckzy.registration.module.portlet.resourcecommand;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.registration.module.portlet.constants.BuckzyRegistrationModulePortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;

@Component(
	    property = {
	    	"javax.portlet.name=" + BuckzyRegistrationModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/lock-user"
	    },
	    service = MVCResourceCommand.class
	)
public class LockUserResourceCommand implements MVCResourceCommand{

	Log _log = LogFactoryUtil.getLog(LockUserResourceCommand.class.getName());
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		
		long userId = ParamUtil.getLong(resourceRequest, "userId");
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		
		try {
			User user = UserLocalServiceUtil.getUser(userId);
			UserLocalServiceUtil.updateLockout(user, true);
			responseObj.put("lock", true);
		} catch (PortalException e) {
			responseObj.put("lock", false);
			_log.error(e);
			
		}
		
		try {
			resourceResponse.getWriter().write(responseObj.toString());
		} catch (IOException e) {
			_log.error(e);
		}
		return true;
	}
	
}
