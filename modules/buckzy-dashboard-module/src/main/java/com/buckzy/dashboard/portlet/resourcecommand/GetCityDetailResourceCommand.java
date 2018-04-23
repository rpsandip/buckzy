package com.buckzy.dashboard.portlet.resourcecommand;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.dashboard.portlet.constants.DashboardModulePortletKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

@Component(
	    property = {
	    	"javax.portlet.name=" + DashboardModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/getcity_detail"
	    },
	    service = MVCResourceCommand.class
	)
public class GetCityDetailResourceCommand implements MVCResourceCommand{

	Log _log = LogFactoryUtil.getLog(GetCityDetailResourceCommand.class.getName());
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		
		String keyword = ParamUtil.getString(resourceRequest, "keyword");
		JSONArray cityArray = JSONFactoryUtil.createJSONArray();
		if(Validator.isNotNull(keyword)){
			String token = (String)PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest)).getSession().getAttribute("token");
			// Get City List
			cityArray = BuckzyCommonLocalServiceUtil.getCityList(token,keyword);
		}
		
		try {
			resourceResponse.getWriter().write(cityArray.toString());
		} catch (IOException e) {
			_log.error(e);
		}
		return true;
		
	}

}
