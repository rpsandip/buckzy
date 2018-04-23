package com.buckzy.dashboard.portlet.portlet;

import com.buckzy.common.beans.PaymentBean;
import com.buckzy.common.beans.UserBean;
import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.common.service.service.CustomUserLocalServiceUtil;
import com.buckzy.common.util.BuckzyConstants;
import com.buckzy.dashboard.portlet.constants.DashboardModulePortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
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
		"javax.portlet.display-name=buckzy-dashboard-module Portlet",
		"javax.portlet.init-param.template-path=/",
		"com.liferay.portlet.action-url-redirect=true",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class DashboardModulePortlet extends MVCPortlet {
	
		Log _log = LogFactoryUtil.getLog(DashboardModulePortlet.class.getName());
	
		@Override
		public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
			
				ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				
				String token = (String)PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest)).getSession().getAttribute("token");
				
				UserBean userBean = CustomUserLocalServiceUtil.getPartyUserBean(token, themeDisplay.getUserId());
				
				boolean isProfileCompleted = userBean.getCustomUserBean().isAccountCompleted() && userBean.getCustomUserBean().isDocumentVerified();
				
				if(Validator.isNotNull(userBean)){
					renderRequest.setAttribute("isProfileCompleted", isProfileCompleted);
					renderRequest.setAttribute("userBean", userBean);

					if(Validator.isNotNull(userBean.getPartyBean()) && Validator.isNotNull(userBean.getPartyBean().getPartyAddressBean())){
						renderRequest.setAttribute("userCountry", userBean.getPartyBean().getPartyAddressBean().getCntrycd().toLowerCase());
					}else{
						renderRequest.setAttribute("isProfileCompleted", false);
					}
				}
				
				if(isProfileCompleted){
					double totalAmountTransferred=0;
					List<PaymentBean> paymentBeanList = BuckzyCommonLocalServiceUtil.getPaymentTransactionList(token, 0, BuckzyConstants.PAYMENT_PAGE_SIZE);
					for(PaymentBean paymentBean : paymentBeanList){
						totalAmountTransferred = totalAmountTransferred + paymentBean.getSndrinstramt();
					}
					renderRequest.setAttribute("totalAmountTransferred", totalAmountTransferred);
					renderRequest.setAttribute("totalTransactions", paymentBeanList.size());
					renderRequest.setAttribute("paymentBeanList", paymentBeanList);
					if(paymentBeanList.size()>0){
						renderRequest.setAttribute("sndrcurrcd", paymentBeanList.get(0).getSndrcurrcd());
					}
				}
			
			
			include(viewTemplate, renderRequest, renderResponse);
		}
	
}