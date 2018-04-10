package com.buckzy.dashboard.portlet.portlet;

import com.buckzy.common.beans.UserBean;
import com.buckzy.dashboard.portlet.constants.DashboardModulePortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

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
		"javax.portlet.display-name=buckzy-dashboard-module Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class DashboardModulePortlet extends MVCPortlet {
	
		Log _log = LogFactoryUtil.getLog(DashboardModulePortlet.class.getName());
	
		@Override
		public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
				throws IOException, PortletException {
			
			try {
				ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				
				User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
				UserBean userBean = new UserBean(user);
				
				renderRequest.setAttribute("isProfileCompleted", userBean.getCustomUserBean().isProfileComplete());
			}catch (PortalException e) {
				_log.error(e);
			}
			
			include(viewTemplate, renderRequest, renderResponse);
		}
}