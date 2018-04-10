package com.buckzy.send.money.portlet.rendercommand;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.send.money.portlet.constants.BuckzySendMoneyControllerPortletKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.PortalUtil;

@Component(
	    property = {
	    	"javax.portlet.name="+BuckzySendMoneyControllerPortletKeys.PORTLET_ID,
	        "mvc.command.name=/create_account"
	    },
	    service = MVCRenderCommand.class
)
public class CreateAccoungRenderCommand implements MVCRenderCommand{

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		String token = (String)PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest)).getSession().getAttribute("token");
		
		// Get country List
		JSONArray countriesArray = BuckzyCommonLocalServiceUtil.getCountryList(token);
		List<JSONObject> countryJsonList = new ArrayList<JSONObject>();
		for(int i=0;i<countriesArray.length();i++){
			countryJsonList.add(countriesArray.getJSONObject(i));
		}
		renderRequest.setAttribute("countryJsonList", countryJsonList);
		
		return "/create-account.jsp";
	}

}
