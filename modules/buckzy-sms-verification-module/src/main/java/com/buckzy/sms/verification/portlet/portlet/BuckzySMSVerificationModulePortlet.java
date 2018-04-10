package com.buckzy.sms.verification.portlet.portlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.service.exception.NoSuchCustomUserException;
import com.buckzy.common.service.model.CustomUser;
import com.buckzy.common.service.service.CustomUserLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author sandip
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=buckzy-sms-verification-module Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class BuckzySMSVerificationModulePortlet extends MVCPortlet {
	
	Log _log = LogFactoryUtil.getLog(BuckzySMSVerificationModulePortlet.class.getName());
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		renderRequest.setAttribute("smsAPIkey", PropsUtil.get("buckzy.sms.api.key"));
		try {
			CustomUser customUser = CustomUserLocalServiceUtil.getCustomUserByLRUserId(themeDisplay.getUserId());
			renderRequest.setAttribute("mobileNo", customUser.getMobCountryCode()+customUser.getMobileNo());
			renderRequest.setAttribute("deviceInfo", customUser.getDeviceInfo());
			renderRequest.setAttribute("emailAddress", themeDisplay.getUser().getEmailAddress());
			renderRequest.setAttribute("userId", themeDisplay.getUserId());
			
		} catch (NoSuchCustomUserException e) {
			_log.error(e);
		}
		include(viewTemplate, renderRequest, renderResponse);
	}
}