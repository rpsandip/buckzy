package com.buckzy.send.money.portlet.rendercommand;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.beans.PaymentBean;
import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.send.money.portlet.constants.BuckzySendMoneyControllerPortletKeys;
import com.buckzy.send.money.portlet.util.HelperUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

@Component(
	    property = {
	    	"javax.portlet.name="+BuckzySendMoneyControllerPortletKeys.PORTLET_ID,
	        "mvc.command.name=/post_payment"
	    },
	    service = MVCRenderCommand.class
)
public class PostPaymentRenderCommand implements MVCRenderCommand{

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse){

		String token = (String)PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest)).getSession().getAttribute("token");
		SimpleDateFormat sd = new SimpleDateFormat("MMM-dd-YYYY hh:mm a");

		long paymentId = ParamUtil.getLong(renderRequest, "paymentId");
		
		if(paymentId>0){
			HelperUtil.setPaymentDetail(token, paymentId, renderRequest);
		}
		
		return "/post_payment.jsp";
	}

}
