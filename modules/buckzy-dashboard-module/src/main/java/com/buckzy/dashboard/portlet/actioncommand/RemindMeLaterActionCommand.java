package com.buckzy.dashboard.portlet.actioncommand;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import org.osgi.service.component.annotations.Component;

import com.buckzy.common.service.model.CustomUser;
import com.buckzy.common.service.service.CustomUserLocalServiceUtil;
import com.buckzy.dashboard.portlet.constants.DashboardModulePortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;

@Component(
	    property = {
	    	"javax.portlet.name=" + DashboardModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/remindLater"
	    },
	    service = MVCActionCommand.class
	)
public class RemindMeLaterActionCommand extends BaseMVCActionCommand{

	Log _log = LogFactoryUtil.getLog(RemindMeLaterActionCommand.class.getName());
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse){
		
		String remindType = ParamUtil.getString(actionRequest, "remindType");
		long customUserId = ParamUtil.getLong(actionRequest, "customUserId");
		
		if(customUserId >0 && remindType.equals("Document")){
			try {
				CustomUser customUser = CustomUserLocalServiceUtil.getCustomUser(customUserId);
				customUser.setDocumentRemindLater(true);
				CustomUserLocalServiceUtil.updateCustomUser(customUser);
				SessionMessages.add(actionRequest, "remind-success");
			} catch (PortalException e) {
				SessionErrors.add(actionRequest, "remind-err");
				_log.error(e);
			}
		}else if(customUserId >0 &&remindType.equals("Account")){
			try {
				
				CustomUser customUser = CustomUserLocalServiceUtil.getCustomUser(customUserId);
				customUser.setAccountRemindLater(true);
				CustomUserLocalServiceUtil.updateCustomUser(customUser);
				SessionMessages.add(actionRequest, "remind-success");
			} catch (PortalException e) {
				SessionErrors.add(actionRequest, "remind-err");
				_log.error(e);
			}
		}else{
			SessionErrors.add(actionRequest, "remind-err");
		}
		
	}

}
