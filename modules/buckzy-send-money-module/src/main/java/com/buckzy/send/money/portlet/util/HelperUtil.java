package com.buckzy.send.money.portlet.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.portlet.RenderRequest;

import com.buckzy.common.beans.PaymentBean;
import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class HelperUtil {
	
	public static void setPaymentDetail(String token, long paymentId, RenderRequest renderRequest){
		SimpleDateFormat sd = new SimpleDateFormat("MMM-dd-YYYY hh:mm a");
		JSONObject paymentObj = BuckzyCommonLocalServiceUtil.getPaymentDetail(token, paymentId);
		if(Validator.isNotNull(paymentObj.get("lineitemid"))){
			PaymentBean paymentBean = BuckzyCommonLocalServiceUtil.converPaymentObjectToBean(paymentObj);
			renderRequest.setAttribute("paymentBean", paymentBean);
			
			// Set TxnDate and Estimate Recv Date
			String dateSt = paymentBean.getRqstexecdt();
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(Long.parseLong(dateSt)));
			
			long t= cal.getTimeInMillis();
			Date txnRecvDate=new Date(t + (5 * 60000)); // Add 5 minutes
			renderRequest.setAttribute("txnDate", sd.format(new Date(Long.parseLong(dateSt))));
			renderRequest.setAttribute("txnRecvDate", sd.format(txnRecvDate));
			
			String senderLast4digitAccount = StringPool.BLANK;
			if(Validator.isNotNull(paymentBean.getSndracctnr()) && paymentBean.getSndracctnr().length()>=4){
				senderLast4digitAccount = paymentBean.getSndracctnr().substring((paymentBean.getSndracctnr().length()-4), paymentBean.getSndracctnr().length());
				paymentBean.setSndracctnr(senderLast4digitAccount);
			}
			
			String receiverLast4digitAccount = StringPool.BLANK;
			if(Validator.isNotNull(paymentBean.getRcvracctnr()) && paymentBean.getRcvracctnr().length()>=4){
				receiverLast4digitAccount = paymentBean.getRcvracctnr().substring((paymentBean.getRcvracctnr().length()-4), paymentBean.getRcvracctnr().length());
				paymentBean.setRcvracctnr(receiverLast4digitAccount);
			}
			
			renderRequest.setAttribute("senderCountry", paymentBean.getSndrcntrycd());
			renderRequest.setAttribute("receiverCountry", paymentBean.getRcvrcntrycd());
		}
	}
}
