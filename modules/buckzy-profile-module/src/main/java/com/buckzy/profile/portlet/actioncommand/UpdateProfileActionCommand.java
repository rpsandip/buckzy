package com.buckzy.profile.portlet.actioncommand;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.exception.PortalException;
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
import com.liferay.portal.kernel.upload.FileItem;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.buckzy.common.beans.CustomUserBean;
import com.buckzy.common.service.exception.NoSuchCustomUserException;
import com.buckzy.common.service.model.CustomUser;
import com.buckzy.common.service.service.CustomUserLocalServiceUtil;
import com.buckzy.profile.portlet.constants.BuckzyProfileModulePortletKeys;
@Component(
	    property = {
	    	"javax.portlet.name=" + BuckzyProfileModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/profile_update"
	    },
	    service = MVCActionCommand.class
	)
public class UpdateProfileActionCommand extends BaseMVCActionCommand{

	Log _log = LogFactoryUtil.getLog(UpdateProfileActionCommand.class.getName());
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse){

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		String token = (String)PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(actionRequest)).getSession().getAttribute("token");
		HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
		long userId = themeDisplay.getUserId();
		
		String firstName = ParamUtil.getString(actionRequest, "firstName");
		String lastName = ParamUtil.getString(actionRequest, "lastName");
		String address = ParamUtil.getString(actionRequest, "address");
		String zipcode = ParamUtil.getString(actionRequest, "zipcode");
		String city = ParamUtil.getString(actionRequest, "city");
		String state = ParamUtil.getString(actionRequest, "state");
		String countryDetail = ParamUtil.getString(actionRequest, "country");
		String mobileNo = ParamUtil.getString(actionRequest, "mobile");
		String[]countryarray = countryDetail.split(StringPool.COMMA);
		
		String accountType = ParamUtil.getString(actionRequest, "accountType");
		String cardNumber = ParamUtil.getString(actionRequest, "cardNumber");
		String cardFirstName = ParamUtil.getString(actionRequest, "cardFirstName");
		String cardLastNumber = ParamUtil.getString(actionRequest, "cardLastNumber");
		String expireOnMonth = ParamUtil.getString(actionRequest, "expireOnMonth");
		String expireOnYear = ParamUtil.getString(actionRequest, "expireOnYear");
		String accountNumber = ParamUtil.getString(actionRequest, "accountNumber");
		String documentVerificationType = ParamUtil.getString(actionRequest, "documentVerificationType");
		String acctInstnNm = StringPool.BLANK;
		String searchBranchType = ParamUtil.getString(actionRequest, "searchBranchType");
		String routingNo = StringPool.BLANK;
		String currencyCode = StringPool.BLANK;
		int bankId=0;
		int branchId=0;
		
		if(accountType.equals("debit_card")){
			//TODO: Need to remove this once validation remove from REST API side
			bankId=1;
			expireOnMonth = new DecimalFormat("00").format(Integer.parseInt(expireOnMonth));
			expireOnYear = new DecimalFormat("00").format(Integer.parseInt(expireOnYear));
		}else{
			
			if(searchBranchType.equals("search")){
			
				String bankNameDetail = ParamUtil.getString(actionRequest, "bankName");
				
				String branchDetail = ParamUtil.getString(actionRequest, "branchName");
				if(Validator.isNotNull(bankNameDetail) && bankNameDetail.indexOf(StringPool.COMMA)>0){
					bankId = Integer.parseInt(bankNameDetail.split(StringPool.COMMA)[0]);
					acctInstnNm = bankNameDetail.split(StringPool.COMMA)[1];
				}
				if(Validator.isNotNull(branchDetail) && branchDetail.indexOf(StringPool.COMMA)>0){
					branchId = Integer.parseInt(branchDetail.split(StringPool.COMMA)[0]);
				}
			}else{
				routingNo = ParamUtil.getString(actionRequest, "unique_branch_code");
			}
			
		}
		
		if(Validator.isNotNull(mobileNo)){
			mobileNo= mobileNo.replaceAll("-", "");
			mobileNo= mobileNo.replaceAll("\\(", "");
			mobileNo = mobileNo.replaceAll("\\)", "");
		}
		
		File verificationDoc = null;
		String verificationDocName = StringPool.BLANK;
		/*
		Map<String, FileItem[]> files= uploadPortletRequest.getMultipartParameterMap();
		for (Entry<String, FileItem[]> file2 : files.entrySet()) {
			FileItem item[] =file2.getValue();
			String name  = file2.getKey();
			if(name.startsWith("verificationDoc")){
				for (FileItem fileItem : item) {
					verificationDocName = fileItem.getFileName();
					verificationDoc = fileItem.getStoreLocation();
				}
			}
		}*/
		verificationDoc = uploadPortletRequest.getFile("verificationDoc");
		verificationDocName = uploadPortletRequest.getFileName("verificationDoc");
		
		if(Validator.isNotNull(documentVerificationType)){
			if(documentVerificationType.equals("drive_license")){
				documentVerificationType = "KYCD";
			}else{
				documentVerificationType = "KYCP";
			}
		}
		
		
		
		if(countryarray.length>0){
			try {
				CustomUser customUser = CustomUserLocalServiceUtil.getCustomUserByLRUserId(userId);
				CustomUserBean customUserBean = new CustomUserBean(customUser);
				try {
					User user =  UserLocalServiceUtil.getUser(userId);
					JSONObject responseObj = CustomUserLocalServiceUtil.updateParty(token, customUserBean.getPartyId(), userId, 
							firstName, lastName, user.getEmailAddress(), 
							countryarray[1], countryarray[0], countryarray[3].trim() ,mobileNo, address, city, state,zipcode,  documentVerificationType,verificationDoc, verificationDocName,
							accountType, cardNumber, cardFirstName, cardLastNumber, expireOnMonth, expireOnYear,
						    accountNumber, acctInstnNm,bankId, branchId,routingNo,searchBranchType);
			
					String status = responseObj.getString("status");
					
					if(status=="success"){
						SessionMessages.add(actionRequest, "profile-update-success");
					}else{
						SessionErrors.add(actionRequest, "profile-update-error");
					}
				} catch (PortalException e) {
					if(Validator.isNotNull(e.getMessage())){
						SessionErrors.add(actionRequest, "profile-custom-update-error");
						request.getSession().setAttribute("customErr", e.getMessage());
					}else{
						SessionErrors.add(actionRequest, "profile-update-error");
					}
					SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
					_log.error(e);
				}
				
			} catch (NoSuchCustomUserException e) {
				SessionErrors.add(actionRequest, "profile-update-error");
				_log.error(e);
			}
		}
	}

}
