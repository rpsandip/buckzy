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
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

@Component(
	    property = {
	    	"javax.portlet.name=" + BuckzyRegistrationModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/forgot-password"
	    },
	    service = MVCResourceCommand.class
	)
public class ForgotPasswordResourceCommand implements MVCResourceCommand{

	Log _log = LogFactoryUtil.getLog(ForgotPasswordResourceCommand.class.getName());
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		String emailAddress = ParamUtil.getString(resourceRequest, "emailAddress");
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		
		if(Validator.isNotNull(emailAddress)){
			try {
				User user = UserLocalServiceUtil.getUserByEmailAddress(PortalUtil.getDefaultCompanyId(), emailAddress);
				UserLocalServiceUtil.sendPasswordByEmailAddress(PortalUtil.getDefaultCompanyId(), emailAddress);
				_log.info("Passsoword reset link is sent to email ->" + emailAddress);
				responseObj.put("sentEmail", true);
			} catch (PortalException e) {
				responseObj.put("message", "Please enter valid Email Address");
				responseObj.put("sentEmail", false);
				_log.error(e);
			}
		}else{
			responseObj.put("sentEmail", false);
			responseObj.put("message", "Please enter valid Email Address");
		}
		
		try {
			resourceResponse.getWriter().write(responseObj.toString());
		} catch (IOException e) {
			_log.error(e);
		}
		return true;
	}

}
