package com.buckzy.registration.module.portlet.actioncommand;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.registration.module.portlet.constants.BuckzyRegistrationModulePortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;

@Component(
	    property = {
	    	"javax.portlet.name=" + BuckzyRegistrationModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/otp_verification"
	    },
	    service = MVCActionCommand.class
	)
public class OTPVerificationActionCommand extends BaseMVCActionCommand{

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		String verificationCode1= ParamUtil.getString(actionRequest, "verificationCode1");
		String verificationCode2= ParamUtil.getString(actionRequest, "verificationCode2");
		String verificationCode3= ParamUtil.getString(actionRequest, "verificationCode3");
		String verificationCode4= ParamUtil.getString(actionRequest, "verificationCode4");
		String verificationCode5= ParamUtil.getString(actionRequest, "verificationCode5");
		String verificationCode6= ParamUtil.getString(actionRequest, "verificationCode6");
		String email = ParamUtil.getString(actionRequest, "email");
		String password = ParamUtil.getString(actionRequest, "password");
				
		String finalCode = verificationCode1.concat(verificationCode2).concat(verificationCode3).concat(verificationCode4)
		.concat(verificationCode5).concat(verificationCode6);
		 
		boolean isVerified = BuckzyCommonLocalServiceUtil.verifyOTP(email, password, finalCode);
		
		if(isVerified){
			SessionMessages.add(actionRequest, "verified");
			actionResponse.setRenderParameter("success", "true");
		}else{
			SessionErrors.add(actionRequest , "not-verfied");
		}
		
		actionResponse.setRenderParameter("mvcRenderCommandName", "/verift_otp");
		actionResponse.setRenderParameter("password", password);
		actionResponse.setRenderParameter("email", email);
	}

}
