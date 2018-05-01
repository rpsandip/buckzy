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

@Component(
	    property = {
	    	"javax.portlet.name=" + BuckzyProfileModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/getbank_detail"
	    },
	    service = MVCResourceCommand.class
	)
public class GetBankListResourceCommand implements MVCResourceCommand{

	Log _log = LogFactoryUtil.getLog(GetBankListResourceCommand.class.getName());
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		String cityId = ParamUtil.getString(resourceRequest, "cityCode");
		JSONArray bankArray = JSONFactoryUtil.createJSONArray();
		//if(Validator.isNotNull(cityCode)){
			String token = (String)PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(resourceRequest)).getSession().getAttribute("token");
			// TODO : Need to make this as city wise
			bankArray = BuckzyCommonLocalServiceUtil.getBankList(token, cityId);
		//}
		
		try {
			resourceResponse.getWriter().write(bankArray.toString());
		} catch (IOException e) {
			_log.error(e);
		}
		return true;
	}

}
