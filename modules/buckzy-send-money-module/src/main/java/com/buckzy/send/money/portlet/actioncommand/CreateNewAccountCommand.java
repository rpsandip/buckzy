package com.buckzy.send.money.portlet.actioncommand;

import java.util.Random;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.beans.UserBean;
import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.common.util.BuckzyConstants;
import com.buckzy.send.money.portlet.constants.BuckzySendMoneyControllerPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
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
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

@Component(
	    property = {
	    	"javax.portlet.name=" + BuckzySendMoneyControllerPortletKeys.PORTLET_ID,
	        "mvc.command.name=/create-account"
	    },
	    service = MVCActionCommand.class
	)
public class CreateNewAccountCommand extends BaseMVCActionCommand{

	Log _log = LogFactoryUtil.getLog(CreateNewAccountCommand.class.getName());
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		User user;
		try {
				user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
				UserBean userBean = new UserBean(user);
			
			String countryDetail = ParamUtil.getString(actionRequest, "country");
			String firstName = ParamUtil.getString(actionRequest, "firstName");
			String lastName = ParamUtil.getString(actionRequest, "lastName");
			String email = ParamUtil.getString(actionRequest, "email");
			String address = ParamUtil.getString(actionRequest, "address");
			String city = ParamUtil.getString(actionRequest, "city");
			String state = ParamUtil.getString(actionRequest, "state");
			String pincode = ParamUtil.getString(actionRequest, "pincode");
			String mobileCountryCode = ParamUtil.getString(actionRequest, "mobileCountryCode");
			String mobileNo = ParamUtil.getString(actionRequest, "phoneNumber");
			String bankName = ParamUtil.getString(actionRequest, "bankName");
			String accountNo = ParamUtil.getString(actionRequest, "accountNumber");
			String searchBranchType  = ParamUtil.getString(actionRequest, "searchBranchType");
			String routingNumber = ParamUtil.getString(actionRequest, "unique_branch_code");
			String branchCode = StringPool.BLANK;
			String acctInstnNm = StringPool.BLANK;
			String currencyCode = StringPool.BLANK;
			
			if(Validator.isNull(address)){
				address="Test";
			}
			if(Validator.isNull(city)){
				city="Test";
			}
			if(Validator.isNull(state)){
				state ="Test";
			}
			if(Validator.isNull(pincode)){
				pincode = "12345";
			}
			
			int bankId=0;
			int branchId=0;
			
			if(Validator.isNotNull(mobileNo)){
				mobileNo= mobileNo.replaceAll("-", "");
				mobileNo= mobileNo.replaceAll("\\(", "");
				mobileNo = mobileNo.replaceAll("\\)", "");
			}
			
			String countryCode = StringPool.BLANK;
			if(Validator.isNotNull(countryDetail)){
				String[] countryArray = countryDetail.split(StringPool.COMMA);
				countryCode = countryArray[0];
				currencyCode = countryArray[3];
			}
	
			if(searchBranchType.equals("known_branch")){
				branchCode = ParamUtil.getString(actionRequest, "unique_branch_code");
				// TODO:: Need bankID and branchID based on branchCode or REST API has to make changes
			}else{
				String bankNameDetail = ParamUtil.getString(actionRequest, "bankName");
				
				String branchDetail = ParamUtil.getString(actionRequest, "branchName");
				if(Validator.isNotNull(bankNameDetail) && bankNameDetail.indexOf(StringPool.COMMA)>0){
					bankId = Integer.parseInt(bankNameDetail.split(StringPool.COMMA)[0]);
					acctInstnNm = bankNameDetail.split(StringPool.COMMA)[1];
				}
				if(Validator.isNotNull(branchDetail) && branchDetail.indexOf(StringPool.COMMA)>0){
					branchId = Integer.parseInt(branchDetail.split(StringPool.COMMA)[0]);
					routingNumber = branchDetail.split(StringPool.COMMA)[1];
				}
			}
			
			
			if(Validator.isNull(currencyCode)){
				currencyCode  = BuckzyConstants.SINGAPORE_CURRENCY_CODE;
			}
			
			
			JSONObject paramsJsonObj = JSONFactoryUtil.createJSONObject();
			paramsJsonObj.put("acctownrtype", BuckzyConstants.IND_ACCOUNT_TYPE);
			//TODO: Need to make dynamic
			paramsJsonObj.put("basecurrcd", currencyCode.trim());
	
			paramsJsonObj.put("sndrflg", 0);
			// TODO: Need to get proper value of it
			paramsJsonObj.put("govtidtype", "ABC");
			paramsJsonObj.put("frstnm", firstName);
			paramsJsonObj.put("lastnm", lastName);
			paramsJsonObj.put("email", email);
			// TODO : Need to check what password need to send for recevier account
			paramsJsonObj.put("password", "welcome");
			paramsJsonObj.put("moblnrcntrycd", mobileCountryCode);
			paramsJsonObj.put("moblnr", mobileNo);
			paramsJsonObj.put("sndrflg", "T");
			
			JSONObject addressObj = JSONFactoryUtil.createJSONObject();
			addressObj.put("postaddr", address);
			addressObj.put("townnm", city);
			// TODO : Need to make dynamic
			addressObj.put("cntrycd", countryCode);
			addressObj.put("zipcd", pincode);
			
			paramsJsonObj.put("ptyaddr", addressObj);
			
			JSONObject accountObj = JSONFactoryUtil.createJSONObject();
			accountObj.put("acctNr", accountNo);
			accountObj.put("acctUsgCd", "A");
			
			accountObj.put("nameOnAcct", firstName+ StringPool.SPACE+ lastName);
			accountObj.put("addr1OnAcct", address);
			accountObj.put("addr2OnAcct", address);
			accountObj.put("cityOnAcct", city);
			accountObj.put("stateOnAcct", state);
			accountObj.put("cntryOnAcct", countryCode);
			accountObj.put("zipOnAcct", pincode);
			accountObj.put("prefAcctFlag", "T");
			accountObj.put("acctType", "CV");
			accountObj.put("accountCategory", 1);
			if(searchBranchType.equals("known_branch")){
				accountObj.put("routngNm", routingNumber);
	    	}else{
	    		//accountObj.put("routngNm", routingNumber);
	    		accountObj.put("acctInstnNm", acctInstnNm);
	    		accountObj.put("bankId", bankId);
	    		accountObj.put("bankBranchId", branchId);
	    	}
			
			paramsJsonObj.put("accnt", accountObj);
			
			String token = (String)PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(actionRequest)).getSession().getAttribute("token");
					
			String params = paramsJsonObj.toJSONString();
	
			String url = BuckzyConstants.POST_PARTY_WITH_ACCOUNT_URL;
			
			url = url.replace("partyId", String.valueOf(userBean.getCustomUserBean().getPartyId()));
			
			JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(url, BuckzyConstants.HTTP_POST, params, token);
		
		    int status =  response.getInt("status");
		    
		    if(status==200){
		    	SessionMessages.add(actionRequest, "account-created-success");
		    }else{
		    	SessionErrors.add(actionRequest, "account-created-error");
		    	actionResponse.setRenderParameter("mvcRenderCommandName", "/create_account");
		    }
		    
		} catch (PortalException e) {
			_log.error(e);
			SessionErrors.add(actionRequest, "account-created-error");
	    	actionResponse.setRenderParameter("mvcRenderCommandName", "/create_account");
		}
		
	}

}
