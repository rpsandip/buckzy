package com.buckzy.registration.module.portlet.rendercommand;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.service.model.CustomUser;
import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.common.service.service.CustomUserLocalServiceUtil;
import com.buckzy.registration.module.portlet.constants.BuckzyRegistrationModulePortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

@Component(
	    property = {
	    	"javax.portlet.name="+BuckzyRegistrationModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/verift_otp"
	    },
	    service = MVCRenderCommand.class
)
public class VerifyOTPRenderCommand implements MVCRenderCommand{

	Log _log = LogFactoryUtil.getLog(VerifyOTPRenderCommand.class.getName());
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse){
		
		String partyPass = (String)renderRequest.getParameter("partyPass");
		String email = (String)renderRequest.getParameter("email");
		String success = (String)renderRequest.getParameter("success");
		
		renderRequest.setAttribute("email", email);
		renderRequest.setAttribute("partyPass", partyPass);
		if(Validator.isNotNull(success) && success.equals("true")){
			renderRequest.setAttribute("success", true);
		}
		
		try {
			User user = UserLocalServiceUtil.getUserByEmailAddress(PortalUtil.getDefaultCompanyId(), email);
			CustomUser customUser = CustomUserLocalServiceUtil.getCustomUserByLRUserId(user.getUserId());
			String otp = BuckzyCommonLocalServiceUtil.getRegistrationOTP(customUser.getPartyUserId());
			renderRequest.setAttribute("otp", otp);
			renderRequest.setAttribute("smsAPIkey", PropsUtil.get("buckzy.sms.api.key"));
			renderRequest.setAttribute("mobileNo", customUser.getMobCountryCode().replace('+',' ').trim()+customUser.getMobileNo());
			
			// Send Otp mail
			BuckzyCommonLocalServiceUtil.sendOTPMail(user.getEmailAddress(), user.getFirstName()+" " + user.getLastName(), otp, customUser.getMobileNo());
		} catch (PortalException  e) {
			_log.error(e);
		}
		
		return "/opt_verification.jsp";
	}

}
