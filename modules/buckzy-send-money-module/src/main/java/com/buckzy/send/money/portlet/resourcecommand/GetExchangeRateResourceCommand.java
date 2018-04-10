package com.buckzy.send.money.portlet.resourcecommand;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.send.money.portlet.constants.BuckzySendMoneyControllerPortletKeys;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

@Component(
	    property = {
	    	"javax.portlet.name=" + BuckzySendMoneyControllerPortletKeys.PORTLET_ID,
	        "mvc.command.name=/getExchange_Rate"
	    },
	    service = MVCResourceCommand.class
	)
public class GetExchangeRateResourceCommand implements MVCResourceCommand{

	Log _log = LogFactoryUtil.getLog(GetExchangeRateResourceCommand.class.getName());
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		
		String fromCurCode = ParamUtil.getString(resourceRequest, "fromCurCode");
		String toCurCode = ParamUtil.getString(resourceRequest, "toCurCode");
		String token = (String)PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest)).getSession().getAttribute("token");
		JSONObject exchangeRate = BuckzyCommonLocalServiceUtil.getExchangeRate(token, fromCurCode, toCurCode);
		try {
			resourceResponse.getWriter().write(exchangeRate.toString());
		} catch (IOException e) {
			_log.error(e);
		}
		return true;
	}

}
