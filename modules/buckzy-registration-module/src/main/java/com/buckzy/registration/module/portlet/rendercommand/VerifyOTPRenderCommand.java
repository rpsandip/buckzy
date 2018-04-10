package com.buckzy.registration.module.portlet.rendercommand;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.registration.module.portlet.constants.BuckzyRegistrationModulePortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.Validator;

@Component(
	    property = {
	    	"javax.portlet.name="+BuckzyRegistrationModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/verift_otp"
	    },
	    service = MVCRenderCommand.class
)
public class VerifyOTPRenderCommand implements MVCRenderCommand{

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		String partyPass = (String)renderRequest.getParameter("partyPass");
		String email = (String)renderRequest.getParameter("email");
		String success = (String)renderRequest.getParameter("success");
		
		System.out.println("email->" + email +" password ->"+ partyPass);
		
		renderRequest.setAttribute("email", email);
		renderRequest.setAttribute("partyPass", partyPass);
		if(Validator.isNotNull(success) && success.equals("true")){
			renderRequest.setAttribute("success", true);
		}
		
		return "/opt_verification.jsp";
	}

}
