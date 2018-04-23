package com.buckzy.registration.module.portlet.portlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.common.util.BuckzyConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author sandip
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=buckzy-registration-module Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"com.liferay.portlet.action-url-redirect=true",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class BuckzyRegistrationModulePortlet extends MVCPortlet {
		
		
		Log _log = LogFactoryUtil.getLog(BuckzyRegistrationModulePortlet.class.getName());
		
		@Override
		public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
				throws IOException, PortletException {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			renderRequest.setAttribute("smsAPIKey", PropsUtil.get("buckzy.sms.api.key"));
			try {
				Set<String> remiderQuestions = themeDisplay.getUser().getReminderQueryQuestions();
				renderRequest.setAttribute("remiderQuestions", remiderQuestions);
			} catch (PortalException e) {
				_log.error(e);
			}
			List<String>countryList = ListUtil.fromArray(PropsUtil.get("country.list").split(StringPool.COMMA));
			renderRequest.setAttribute("countryList", countryList);
			
			String token = BuckzyCommonLocalServiceUtil.getToken(BuckzyConstants.LOGIN_API_USERNAME,BuckzyConstants.LOGIN_API_PASSWORD);
			
			JSONArray countriesArray = BuckzyCommonLocalServiceUtil.getCountryList(token);
			
			List<JSONObject> countryJsonList = new ArrayList<JSONObject>();
			
			for(int i=0;i<countriesArray.length();i++){
				JSONObject countryObj = countriesArray.getJSONObject(i);
				JSONObject currencyObj = countryObj.getJSONObject("currency");
				countryObj.put("threedigitcd", currencyObj.get("currcd"));
				countryJsonList.add(countryObj);
			}
			
			renderRequest.setAttribute("countryJsonList", countryJsonList);
			
			include(viewTemplate, renderRequest, renderResponse);
		}
}