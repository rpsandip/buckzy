package com.buckzy.registration.module.portlet.resourcecommand;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.registration.module.portlet.constants.BuckzyRegistrationModulePortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;

@Component(
	    property = {
	    	"javax.portlet.name=" + BuckzyRegistrationModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/resent-otp"
	    },
	    service = MVCResourceCommand.class
	)
public class ResendOTPResourceCommand implements MVCResourceCommand{
	
	Log _log = LogFactoryUtil.getLog(ResendOTPResourceCommand.class.getName());

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		String email = ParamUtil.getString(resourceRequest, "email");
		String password = ParamUtil.getString(resourceRequest, "password");
		
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		
		boolean isOTPSent = BuckzyCommonLocalServiceUtil.resendOTP(email, password);
		
		if(isOTPSent){
			responseObj.put("otpSent", true);
		}else{
			responseObj.put("otpSent", false);
		}
		
		try {
			resourceResponse.getWriter().write(responseObj.toString());
		} catch (IOException e) {
			_log.error(e);
		}
		return true;
	}

}
