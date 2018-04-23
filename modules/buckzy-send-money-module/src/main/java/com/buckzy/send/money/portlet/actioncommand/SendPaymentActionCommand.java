package com.buckzy.send.money.portlet.actioncommand;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

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
		String token = (String)PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(actionRequest)).getSession().getAttribute("token");
		long receiverPartyId=0;
		String receiverDetail = ParamUtil.getString(actionRequest, "receiver");
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
			if(Validator.isNotNull(senderPartyDetail) && Validator.isNotNull(receiverAccountDetail)){
				
				long senderAccountId = 0;
				long receiverAccountId = 0;
				JSONObject senderAccountDetail = senderPartyDetail.getJSONObject("partyAccount");
				
				if(Validator.isNotNull(senderAccountDetail) && Validator.isNotNull(receiverAccountDetail)){
					senderAccountId = senderAccountDetail.getLong("acctId");
					receiverAccountId = receiverAccountDetail.getLong("acctId");
					long senderPartyId = userBean.getCustomUserBean().getPartyId();	
					String fromCurCode = ParamUtil.getString(actionRequest, "fromCur");
					String toCurCode = ParamUtil.getString(actionRequest, "toCur");
					Double amount = ParamUtil.getDouble(actionRequest, "amount");
					
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
						actionResponse.setRenderParameter("pID", responseObj.getString("lineitemid"));
						actionResponse.setRenderParameter("sdNm", responseObj.getString("sndrdbtrnm"));
						actionResponse.setRenderParameter("sdAc", senderLast4digitAccount);
						actionResponse.setRenderParameter("sdAm", responseObj.getString("sndrinstramt"));
						actionResponse.setRenderParameter("sdCr", responseObj.getString("sndrcurrcd"));
						actionResponse.setRenderParameter("sdCn", responseObj.getString("sndrcntrycd"));
						
						actionResponse.setRenderParameter("rcNm", responseObj.getString("rcvrnm"));
						actionResponse.setRenderParameter("rcAc", receiverLast4digitAccount);
						actionResponse.setRenderParameter("rcCr", responseObj.getString("rcvrcurrcd"));
						actionResponse.setRenderParameter("rcAm", responseObj.getString("rcvramt"));
						actionResponse.setRenderParameter("rcMl", responseObj.getString("rcvremail"));
						actionResponse.setRenderParameter("rcPh", responseObj.getString("rcvrmobilenr"));
						actionResponse.setRenderParameter("rcCn", responseObj.getString("rcvrcntrycd"));
						
						actionResponse.setRenderParameter("purpofpymt", responseObj.getString("purpofpymt"));
						
						actionResponse.setRenderParameter("exRt", String.valueOf(exchangeRate));
					}else{
						SessionErrors.add(actionRequest, "payment-error");
					}
				}else{
					SessionErrors.add(actionRequest, "sender-account-not-exist");
				}
			}else{
				SessionErrors.add(actionRequest, "sender-account-not-exist");
			}
			
			
		
		} catch (PortalException e) {
			SessionErrors.add(actionRequest, "payment-error");
			_log.error(e);
		}
		
		}else{
			SessionErrors.add(actionRequest, "exchange-rate-not-available");
		}
	}

}
