package com.buckzy.email.verification.portlet;

import com.buckzy.email.verification.constants.EmailverificationPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.TicketLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author sandip
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=buckzy-email-verification-module Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EmailverificationPortlet extends MVCPortlet {
	
		Log _log = LogFactoryUtil.getLog(EmailverificationPortlet.class.getName());
	
		@Override
		public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
				throws IOException, PortletException {

			HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
			String verificationCode = request.getParameter("ticketKey");
			
			DynamicQuery dynamicQuery = TicketLocalServiceUtil.dynamicQuery();
			dynamicQuery.add(RestrictionsFactoryUtil.eq("key", verificationCode));
			List<Ticket> ticketList = TicketLocalServiceUtil.dynamicQuery(dynamicQuery);
			if (ticketList.size() > 0) {
				// Set Token Expiration as 1 days
				Ticket ticket = ticketList.get(0);
				if (ticket.getKey().equals(verificationCode)) {
					// TODO : Check for expiration date and time
					// Update user as verified email address

					try {
						UserLocalServiceUtil.updateEmailAddressVerified(ticket.getClassPK(), true);
					} catch (PortalException e) {
						_log.info(e.getMessage());
					}

					SessionMessages.add(renderRequest, "verified");
					renderRequest.setAttribute("emailVerified", true);
				} else {
					SessionMessages.add(renderRequest, PortalUtil.getPortletId(request)
							+ SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
					SessionErrors.add(renderRequest, "not-verified");
				}
			
			}else{
				SessionMessages.add(renderRequest, PortalUtil.getPortletId(request)
						+ SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
				SessionErrors.add(renderRequest, "not-verified");
			}
			include(viewTemplate, renderRequest, renderResponse);
		}
}