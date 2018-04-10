package com.buckzy.profile.portlet.resourcecommand;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.profile.portlet.constants.BuckzyProfileModulePortletKeys;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

@Component(
	    property = {
	    	"javax.portlet.name=" + BuckzyProfileModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/getcard_detail"
	    },
	    service = MVCResourceCommand.class
	)
public class GetCardDetailResourceCommand implements MVCResourceCommand{

	Log _log = LogFactoryUtil.getLog(GetCardDetailResourceCommand.class.getName());
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		String cardNumber = ParamUtil.getString(resourceRequest, "cardNumber");
			String token = (String)PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest)).getSession().getAttribute("token");
			JSONObject cardDetailObj = BuckzyCommonLocalServiceUtil.getCreditCardDetail(token, cardNumber);
			
		try {
			resourceResponse.getWriter().write(cardDetailObj.toString());
		} catch (IOException e) {
			_log.error(e);
		}
		return true;
	}

}
