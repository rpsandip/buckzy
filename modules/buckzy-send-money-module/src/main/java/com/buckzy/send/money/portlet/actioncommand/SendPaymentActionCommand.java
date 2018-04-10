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
		long receiverPartyId = ParamUtil.getLong(actionRequest, "receiver");
		Float exchangeRate = ParamUtil.getFloat(actionRequest, "exchangeRate");
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
							toCurCode, exchangeRate, amount, "Test", "Test", 
							"Test");
					if(Validator.isNotNull(responseObj) && responseObj.get("responseStatus").equals("success")){
						SessionMessages.add(actionRequest, "payment-succcess");
						actionResponse.setRenderParameter("mvcRenderCommandName", "/post_payment");
						actionResponse.setRenderParameter("paymentId", responseObj.getString("lineitemid"));
						actionResponse.setRenderParameter("senderName", responseObj.getString("sndrdbtrnm"));
						actionResponse.setRenderParameter("senderAcnt", responseObj.getString("sndracctnr"));
						actionResponse.setRenderParameter("senderAmount", responseObj.getString("sndrinstramt") + StringPool.SPACE + responseObj.getString("sndrcurrcd"));
						
						actionResponse.setRenderParameter("recevierName", responseObj.getString("rcvrnm"));
						actionResponse.setRenderParameter("recevierAcnt", responseObj.getString("rcvracctnr"));
						actionResponse.setRenderParameter("receiverAmount", responseObj.getString("rcvramt") + StringPool.SPACE + responseObj.getString("rcvrcurrcd"));
						
						
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
