package com.buckzy.sms.verification.portlet.resourcecommand;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.service.model.CustomUser;
import com.buckzy.common.service.service.CustomUserLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.buckzy.sms.verification.portlet.constants.BuckzySMSVerificationModulePortletKeys;

@Component(
	    property = {
	    	"javax.portlet.name=" + BuckzySMSVerificationModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/add-device-info"
	    },
	    service = MVCResourceCommand.class
	)
public class AddDeviceInfoResourceCommand implements MVCResourceCommand{
	
	Log _log = LogFactoryUtil.getLog(AddDeviceInfoResourceCommand.class.getName());
	
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		String emailAddress = ParamUtil.getString(resourceRequest, "emailAddress");
		String newDeviceInfo = ParamUtil.getString(resourceRequest, "deviceInfo");
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		
		try {
			User user = UserLocalServiceUtil.getUserByEmailAddress(PortalUtil.getDefaultCompanyId(), emailAddress);
			CustomUser customUser = CustomUserLocalServiceUtil.getCustomUserByLRUserId(user.getUserId());
		
			
			CustomUserLocalServiceUtil.addDeviceInfo(customUser, newDeviceInfo);
			
			responseObj.put("status", "success");
			
		} catch (PortalException e) {
			responseObj.put("status", "error");
			_log.error("User does not exist with email addess ->" + emailAddress);
		}
		
		try {
			resourceResponse.getWriter().write(responseObj.toString());
		} catch (IOException e) {
			_log.error(e);
		}
		return true;
	}

}
