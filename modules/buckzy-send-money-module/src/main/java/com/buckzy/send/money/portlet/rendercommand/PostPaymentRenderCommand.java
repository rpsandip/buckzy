package com.buckzy.send.money.portlet.rendercommand;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.send.money.portlet.constants.BuckzySendMoneyControllerPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.StringPool;

@Component(
	    property = {
	    	"javax.portlet.name="+BuckzySendMoneyControllerPortletKeys.PORTLET_ID,
	        "mvc.command.name=/post_payment"
	    },
	    service = MVCRenderCommand.class
)
public class PostPaymentRenderCommand implements MVCRenderCommand{

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		renderRequest.setAttribute("paymentId", renderRequest.getParameter("paymentId"));
		renderRequest.setAttribute("senderName", renderRequest.getParameter("senderName"));
		renderRequest.setAttribute("senderAcnt", renderRequest.getParameter("senderAcnt"));
		renderRequest.setAttribute("senderAmount", renderRequest.getParameter("senderAmount"));
		renderRequest.setAttribute("recevierName", renderRequest.getParameter("recevierName"));
		renderRequest.setAttribute("recevierAcnt", renderRequest.getParameter("recevierAcnt"));
		renderRequest.setAttribute("receiverAmount", renderRequest.getParameter("receiverAmount"));
		
		return "/post_payment.jsp";
	}

}
