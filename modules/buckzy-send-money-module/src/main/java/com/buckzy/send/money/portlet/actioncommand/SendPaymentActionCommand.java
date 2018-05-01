package com.buckzy.send.money.portlet.actioncommand;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.beans.UserBean;
import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.common.service.service.CustomUserLocalServiceUtil;
import com.buckzy.send.money.portlet.constants.BuckzySendMoneyControllerPortletKeys;
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
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

@Component(
	    property = {
	    	"javax.portlet.name=" + BuckzySendMoneyControllerPortletKeys.PORTLET_ID,
	        "mvc.command.name=/send_payment"
	    },
	    service = MVCActionCommand.class
	)
public class SendPaymentActionCommand extends BaseMVCActionCommand{

	Log _log = LogFactoryUtil.getLog(SendPaymentActionCommand.class.getName());
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse){
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
		String token = (String)PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(actionRequest)).getSession().getAttribute("token");
		long receiverPartyId=0;
		String receiverDetail = ParamUtil.getString(actionRequest, "receiver");
		Double amount = ParamUtil.getDouble(actionRequest, "amount");
		
		if(Validator.isNotNull(receiverDetail) && receiverDetail.indexOf(",")>0){
			String[] receiverDetailArray = receiverDetail.split(",");
			receiverPartyId = Long.parseLong(receiverDetailArray[0]);
		}
		
		String exchangeRateStr = ParamUtil.getString(actionRequest, "exchangeRate");
		float exchangeRate = Float.valueOf(exchangeRateStr);
		String purTrans = ParamUtil.getString(actionRequest, "purTrans");
		if(exchangeRate!=0){
		try {
			
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			UserBean userBean = new UserBean(user);
			
			JSONObject senderPartyDetail = CustomUserLocalServiceUtil.getPartyDetail(userBean.getCustomUserBean().getPartyId(),token);
			JSONObject receiverAccountDetail = CustomUserLocalServiceUtil.getReceiverAccountDetail(token, userBean.getCustomUserBean().getPartyId(), receiverPartyId);
			String verificationErrMsg = isValidAccountAndDocumentVerification(userBean, senderPartyDetail, amount);
			if(Validator.isNull(verificationErrMsg) && Validator.isNotNull(senderPartyDetail) && Validator.isNotNull(receiverAccountDetail)){
				
				long senderAccountId = 0;
				long receiverAccountId = 0;
				JSONObject senderAccountDetail = senderPartyDetail.getJSONObject("partyAccount");
				
				if(Validator.isNotNull(senderAccountDetail) && Validator.isNotNull(receiverAccountDetail)){
					senderAccountId = senderAccountDetail.getLong("acctId");
					receiverAccountId = receiverAccountDetail.getLong("acctId");
					long senderPartyId = userBean.getCustomUserBean().getPartyId();	
					String fromCurCode = ParamUtil.getString(actionRequest, "fromCur");
					String toCurCode = ParamUtil.getString(actionRequest, "toCur");
					
					
					JSONObject responseObj = BuckzyCommonLocalServiceUtil.makePayment(token, senderPartyId,
							senderAccountId, receiverPartyId, receiverAccountId, fromCurCode,
							toCurCode, exchangeRate, amount, purTrans, "TEST", 
							"TEST");
					if(Validator.isNotNull(responseObj) && responseObj.get("responseStatus").equals("success")){
						SessionMessages.add(actionRequest, "payment-succcess");
						
						String senderLast4digitAccount = StringPool.BLANK;
						if(Validator.isNotNull(responseObj.getString("sndracctnr")) && responseObj.getString("sndracctnr").length()>=4){
							senderLast4digitAccount = responseObj.getString("sndracctnr").substring((responseObj.getString("sndracctnr").length()-4), responseObj.getString("sndracctnr").length());
						}
						
						String receiverLast4digitAccount = StringPool.BLANK;
						if(Validator.isNotNull(responseObj.getString("rcvracctnr")) && responseObj.getString("rcvracctnr").length()>=4){
							receiverLast4digitAccount = responseObj.getString("rcvracctnr").substring((responseObj.getString("rcvracctnr").length()-4), responseObj.getString("rcvracctnr").length());
						}
						
						actionResponse.setRenderParameter("mvcRenderCommandName", "/post_payment");
						
						actionResponse.setRenderParameter("txnDt", responseObj.getString("rqstexecdt"));
						actionResponse.setRenderParameter("rcBkId", responseObj.getString("rcvrbankid"));
						
						actionResponse.setRenderParameter("paymentId", responseObj.getString("lineitemid"));
						
					}else{
						SessionErrors.add(actionRequest, "payment-error");
						SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
					}
				}else{
					SessionErrors.add(actionRequest, "sender-account-not-exist");
					SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
				}
			}else{
				if(Validator.isNotNull(verificationErrMsg)){
					SessionErrors.add(actionRequest, "payment-custom-error");
					request.getSession().setAttribute("customErr", verificationErrMsg);
				}else{
					SessionErrors.add(actionRequest, "sender-account-not-exist");
				}
				SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			}
		
		} catch (PortalException e) {
			request.getSession().setAttribute("customErr", e.getMessage());
			if(Validator.isNotNull(e.getMessage())){
				SessionErrors.add(actionRequest, "payment-custom-error");
    		}else{
    			SessionErrors.add(actionRequest, "payment-error");
    		}
			SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			_log.error(e);
		}
		
		}else{
			SessionErrors.add(actionRequest, "exchange-rate-not-available");
			SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
		}
	}

	private String isValidAccountAndDocumentVerification(UserBean userBean, JSONObject senderPartyDetail, Double amount){
		String errMsg= StringPool.BLANK;
		if(userBean.getCustomUserBean().isAccountCompleted()){
			if(userBean.getCustomUserBean().isDocumentVerified()){
				return errMsg;
			}else if(userBean.getCustomUserBean().isDocumentRemindLater()){
				JSONObject partyAddressbean = senderPartyDetail.getJSONObject("partyaddress");
				if(Validator.isNotNull(partyAddressbean)){
					String countryCode = partyAddressbean.getString("cntrycd");
					if(countryCode.equals("CA") && amount>1000){
						return "Transfer amount more than 1000 will require document verification.";
					}else{
						
					}
				}else{
					return "Account is not updated";
				}
			}
		}else{
			return "Account is not updated";
		}
		return errMsg;
	}

}
