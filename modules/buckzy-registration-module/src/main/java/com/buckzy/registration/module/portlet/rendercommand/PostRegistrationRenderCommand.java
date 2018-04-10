package com.buckzy.registration.module.portlet.rendercommand;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.registration.module.portlet.constants.BuckzyRegistrationModulePortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;

@Component(
	    property = {
	    	"javax.portlet.name="+BuckzyRegistrationModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/post_registration"
	    },
	    service = MVCRenderCommand.class
)
public class PostRegistrationRenderCommand implements MVCRenderCommand{

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		SessionMessages.add(renderRequest, "registration-success");
		return "/post_registration.jsp";
	}

}
