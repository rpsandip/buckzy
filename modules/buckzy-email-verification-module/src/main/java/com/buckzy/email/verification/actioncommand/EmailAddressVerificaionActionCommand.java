package com.buckzy.email.verification.actioncommand;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import com.buckzy.email.verification.constants.EmailverificationPortletKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.TicketLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

@Component(property = { "javax.portlet.name=" + EmailverificationPortletKeys.PORTLET_ID,
		"mvc.command.name=/submit_verification_code" }, service = MVCActionCommand.class)
public class EmailAddressVerificaionActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		String verificationCode = ParamUtil.getString(actionRequest, "verificationCode");

		if (Validator.isNotNull(verificationCode)) {
			DynamicQuery dynamicQuery = TicketLocalServiceUtil.dynamicQuery();
			dynamicQuery.add(RestrictionsFactoryUtil.eq("key", verificationCode));
			List<Ticket> ticketList = TicketLocalServiceUtil.dynamicQuery(dynamicQuery);
			if (ticketList.size() > 0) {
				// Set Token Expiration as 1 days
				Ticket ticket = ticketList.get(0);
				if (ticket.getKey().equals(verificationCode)) {
					// TODO : Check for expiration date and time
					// Update user as verified email address

					UserLocalServiceUtil.updateEmailAddressVerified(ticket.getClassPK(), true);

					SessionMessages.add(actionRequest, "verified");
				} else {
					SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest)
							+ SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
					SessionErrors.add(actionRequest, "not-verified");
				}
			} else {
				SessionMessages.add(actionRequest,
						PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
				SessionErrors.add(actionRequest, "not-verified");
			}
		} else {
			SessionMessages.add(actionRequest,
					PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			SessionErrors.add(actionRequest, "not-verified");
		}
	}

}
