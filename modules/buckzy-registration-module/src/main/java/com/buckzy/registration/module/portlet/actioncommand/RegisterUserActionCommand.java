package com.buckzy.registration.module.portlet.actioncommand;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.common.service.service.CustomUserLocalServiceUtil;
import com.buckzy.common.util.BuckzyConstants;
import com.buckzy.registration.module.portlet.constants.BuckzyRegistrationModulePortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

@Component(
	    property = {
	    	"javax.portlet.name=" + BuckzyRegistrationModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/register_user"
	    },
	    service = MVCActionCommand.class
	)
public class RegisterUserActionCommand extends BaseMVCActionCommand{

	Log _log = LogFactoryUtil.getLog(RegisterUserActionCommand.class.getName());
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) {

			ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String firstName = ParamUtil.getString(actionRequest, "firstName");
			String middleName = ParamUtil.getString(actionRequest, "middleName");
			String lastName = ParamUtil.getString(actionRequest, "lastName");
			String emailAddress = ParamUtil.getString(actionRequest, "emailAdddress");
			String password = ParamUtil.getString(actionRequest, "password");
			String address = ParamUtil.getString(actionRequest, "address");
			String zipcode = ParamUtil.getString(actionRequest, "zipcode");
			String city = ParamUtil.getString(actionRequest, "city");
			String state = ParamUtil.getString(actionRequest, "state");
			String countryDetail = ParamUtil.getString(actionRequest, "country");
			String mobile = ParamUtil.getString(actionRequest, "mobile");
			String mobileCountryCode = ParamUtil.getString(actionRequest, "countryCode");
			String reminderQuestion1 = ParamUtil.getString(actionRequest, "reminderQuestion1");
			String reminderAns1 = ParamUtil.getString(actionRequest, "reminderAns1");
			String reminderQuestion2 = ParamUtil.getString(actionRequest, "reminderQuestion2");
			String reminderAns2 = ParamUtil.getString(actionRequest, "reminderAns2");
			String deviceInfo = ParamUtil.getString(actionRequest, "deviceInfo");
			String countryCode = StringPool.BLANK;
			String currencyCode = StringPool.BLANK;
			String  dobDay = ParamUtil.getString(actionRequest, "dobDay");
			String  dobMonth = ParamUtil.getString(actionRequest, "dobMonth");
			String  dobYear = ParamUtil.getString(actionRequest, "dobYear");
			String dob = getDob(dobDay, dobMonth, dobYear);
			
			String[]countryarray = countryDetail.split(StringPool.COMMA);
			
			HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
			
			String token = BuckzyCommonLocalServiceUtil.getToken(BuckzyConstants.LOGIN_API_USERNAME,BuckzyConstants.LOGIN_API_PASSWORD);
			
			boolean isUserExist = isUserExist(emailAddress, mobile, countryCode);
			
			if(countryarray.length>0){
				countryCode = countryarray[0];
				currencyCode = countryarray[3].trim();
			}
			
			if(Validator.isNotNull(mobile)){
				mobile= mobile.replaceAll("-", "");
				mobile= mobile.replaceAll("\\(", "");
				mobile = mobile.replaceAll("\\)", "");
			}
			
			if(!isUserExist){
				try {
					JSONObject responseObject = BuckzyCommonLocalServiceUtil.registerUser(token, firstName, middleName, lastName, emailAddress,
							password, address, city, zipcode, state, countryCode, currencyCode,dob, mobile, mobileCountryCode,reminderQuestion1, reminderAns1,
							reminderQuestion2,reminderAns2, deviceInfo, false,themeDisplay.getUserId(), themeDisplay.getScopeGroupId(), ServiceContextFactory.getInstance(actionRequest));
					
					User user = (User)responseObject.get("user");
					
					if(Validator.isNotNull(user)){
						// User created with Mobile verification now its time to display email verification.
						
						actionResponse.setRenderParameter("mvcRenderCommandName", "/verift_otp");
						//actionResponse.setRenderParameter("userId", String.valueOf(user.getUserId()));
						actionResponse.setRenderParameter("partyPass", password);
						actionResponse.setRenderParameter("email", emailAddress);
						
						SessionMessages.add(actionRequest, "registration-success");
					}else{
						String customErrMsg = responseObject.getString("userErrMsg");
						if(Validator.isNotNull(customErrMsg)){
							request.getSession().setAttribute("customErr", customErrMsg);
							SessionErrors.add(actionRequest, "registration-custom-err");
						}else{
							SessionErrors.add(actionRequest, "registration-error");
						}
						SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
					}
				} catch (PortalException e) {
					request.getSession().setAttribute("customErr", e.getMessage());
					SessionErrors.add(actionRequest, "registration-custom-err");
					if(Validator.isNull(e.getMessage())){
						SessionErrors.add(actionRequest, "registration-error");
					}
					SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
					_log.error(e);
				}
			}else{
				SessionErrors.add(actionRequest, "user-exist");
				SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			}
		
	}

	private boolean isUserExist(String emailAddress,String mobileNo, String countryCode){
		boolean isUserExist = false;
		try {
			
			// Check by email Address
			UserLocalServiceUtil.getUserByEmailAddress(PortalUtil.getDefaultCompanyId(), emailAddress);
			
			// Check by Mobile number
			CustomUserLocalServiceUtil.getCustomUserByMobileNumber(mobileNo, countryCode);
			
			isUserExist= true;
		} catch (PortalException e) {
			_log.error(e.getMessage());
		}
		return isUserExist;
	}
	
	private String getDob(String day, String month, String year){
		return month+"/"+day+"/"+year;
	}
}
