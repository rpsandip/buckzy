package com.buckzy.send.money.portlet.resourcecommand;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.service.service.CustomUserLocalServiceUtil;
import com.buckzy.send.money.portlet.constants.BuckzySendMoneyControllerPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;


@Component(
	    property = {
	    	"javax.portlet.name=" + BuckzySendMoneyControllerPortletKeys.PORTLET_ID,
	        "mvc.command.name=/getReceiver_Account_Detail"
	    },
	    service = MVCResourceCommand.class
	)
public class GetReceiverAccountResourceCommand implements MVCResourceCommand{

	Log _log = LogFactoryUtil.getLog(GetReceiverAccountResourceCommand.class.getName());
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		long receiverPartyId = ParamUtil.getLong(resourceRequest, "receiverPartyId");
		long senderPartyId = ParamUtil.getLong(resourceRequest, "senderPartyId");
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		String token = (String)PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest)).getSession().getAttribute("token");
		try {
			responseObj = CustomUserLocalServiceUtil.getReceiverAccountDetail(token, senderPartyId, receiverPartyId);
		} catch (PortalException e1) {
			_log.error(e1);
		}
		try {
			resourceResponse.getWriter().write(responseObj.toString());
		} catch (IOException e) {
			_log.error(e);
		}
		return true;
	}

}
