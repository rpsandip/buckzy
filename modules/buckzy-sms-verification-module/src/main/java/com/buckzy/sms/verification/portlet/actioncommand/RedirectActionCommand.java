package com.buckzy.sms.verification.portlet.actioncommand;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.buckzy.sms.verification.portlet.constants.BuckzySMSVerificationModulePortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.PortalUtil;


@Component(
	    property = {
	    	"javax.portlet.name=" + BuckzySMSVerificationModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/go_home_page"
	    },
	    service = MVCActionCommand.class
	)
public class RedirectActionCommand extends BaseMVCActionCommand{

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(actionRequest));
		request.getSession().setAttribute("smsVerified", true);
		
		actionResponse.sendRedirect("/group/guest");
		
	}

}
