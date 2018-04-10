package com.buckzy.send.money.portlet.portlet;

import com.buckzy.common.beans.UserBean;
import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.send.money.portlet.constants.BuckzySendMoneyControllerPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author sandip
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=buckzy-send-money-module Portlet",
		"javax.portlet.init-param.template-path=/",
		"com.liferay.portlet.action-url-redirect=true",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class BuckzySendMoneyControllerPortlet extends MVCPortlet {
	
	Log _log = LogFactoryUtil.getLog(BuckzySendMoneyControllerPortlet.class.getName());
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException{

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		User user;
		try {
			user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			UserBean userBean = new UserBean(user);
			
			String token = (String)PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest)).getSession().getAttribute("token");
			
			// Get Currency List
			JSONArray currencyArray = BuckzyCommonLocalServiceUtil.getCurrencyList(token);
			List<JSONObject> currenyJSONList = new ArrayList<JSONObject>();
			for(int i=0;i<currencyArray.length();i++){
				currenyJSONList.add(currencyArray.getJSONObject(i));
			}
			renderRequest.setAttribute("currenyJSONList", currenyJSONList);
			
			// Get Currency List
			JSONArray receiverArray = BuckzyCommonLocalServiceUtil.getReceiverList(token, userBean.getCustomUserBean().getPartyId());
			List<JSONObject> receiverJsonList = new ArrayList<JSONObject>();
			for(int i=0;i<receiverArray.length();i++){
				receiverJsonList.add(receiverArray.getJSONObject(i));
			}
			renderRequest.setAttribute("receiverList", receiverJsonList);
		} catch (PortalException e) {
			
			_log.error(e);
		}
		
		include(viewTemplate, renderRequest, renderResponse);
	}
}