package com.buckzy.profile.portlet.resourcecommand;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.profile.portlet.constants.BuckzyProfileModulePortletKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

@Component(
	    property = {
	    	"javax.portlet.name=" + BuckzyProfileModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/getstate_detail"
	    },
	    service = MVCResourceCommand.class
	)
public class GetStateDetailResourceCommand implements MVCResourceCommand{

	Log _log = LogFactoryUtil.getLog(GetStateDetailResourceCommand.class.getName());
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		String countryCode = ParamUtil.getString(resourceRequest, "countryCode");
		
		JSONArray stateArray = JSONFactoryUtil.createJSONArray();
		if(Validator.isNotNull(countryCode)){
			
			String token = (String)PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest)).getSession().getAttribute("token");
			
			// Get Branch List
			stateArray = BuckzyCommonLocalServiceUtil.getStateList(token, countryCode);
			
		}
		
		try {
			resourceResponse.getWriter().write(stateArray.toString());
		} catch (IOException e) {
			_log.error(e);
		}
		return true;
		
	}

}
