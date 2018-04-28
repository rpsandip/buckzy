package com.buckzy.dashboard.portlet.actioncommand;

import java.io.File;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.service.model.CustomUser;
import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.common.service.service.CustomUserLocalServiceUtil;
import com.buckzy.common.util.BuckzyConstants;
import com.buckzy.dashboard.portlet.constants.DashboardModulePortletKeys;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

@Component(
	    property = {
	    	"javax.portlet.name=" + DashboardModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/upload_Document"
	    },
	    service = MVCActionCommand.class
	)
public class UploadDocumentActionCommand extends BaseMVCActionCommand{

	Log _log = LogFactoryUtil.getLog(UploadDocumentActionCommand.class.getName());
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
	
		File verificationDoc = null;
		String verificationDocName = StringPool.BLANK;
		String documentVerificationType = ParamUtil.getString(actionRequest, "documentVerificationType");
		
		verificationDoc = uploadPortletRequest.getFile("verificationDoc");
		verificationDocName = uploadPortletRequest.getFileName("verificationDoc");
		
		String token = (String)PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(actionRequest)).getSession().getAttribute("token");
		
		User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
		CustomUser customUser = CustomUserLocalServiceUtil.getCustomUserByLRUserId(themeDisplay.getUserId());
		
		if(Validator.isNotNull(documentVerificationType)){
			if(documentVerificationType.equals("drive_license")){
				documentVerificationType = "KYCD";
			}else{
				documentVerificationType = "KYCP";
			}
		}

		if(customUser.getPartyId()>0 && Validator.isNotNull(verificationDocName) && Validator.isNotNull(verificationDocName)){
			JSONObject fileUploadResponse = BuckzyCommonLocalServiceUtil.getMultiPartResponse(
					BuckzyConstants.KYC_DOC_URL, customUser.getPartyId(), token, verificationDoc, documentVerificationType);
		
			int fileUploadStatus = fileUploadResponse.getInt("status");
		
			if (fileUploadStatus != 200) {
				SessionErrors.add(actionRequest, "document-upload-err");
			} else {
				customUser.setDocumentVerified(true);
				CustomUserLocalServiceUtil.updateCustomUser(customUser);
				SessionMessages.add(actionRequest, "document-upload-success");
				if(customUser.getAccountCompleted()){
					SessionMessages.add(actionRequest, "account-success");
				}
			}
		}else{
			SessionErrors.add(actionRequest, "document-upload-err");
		}
		
	}

}
