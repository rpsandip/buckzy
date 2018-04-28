package com.buckzy.dashboard.portlet.actioncommand;

import java.text.DecimalFormat;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.service.service.CustomUserLocalServiceUtil;
import com.buckzy.dashboard.portlet.constants.DashboardModulePortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
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
	    	"javax.portlet.name=" + DashboardModulePortletKeys.PORTLET_ID,
	        "mvc.command.name=/update_Account"
	    },
	    service = MVCActionCommand.class
	)
public class UpdateAccountActionCommand extends BaseMVCActionCommand{

	Log _log = LogFactoryUtil.getLog(UpdateAccountActionCommand.class.getName());
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse){
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
		
		String token = (String)PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(actionRequest)).getSession().getAttribute("token");
		String accountType = ParamUtil.getString(actionRequest, "accountType");
		String cardNumber = ParamUtil.getString(actionRequest, "cardNumber");
		String cardFirstName = ParamUtil.getString(actionRequest, "cardFirstName");
		String cardLastName = ParamUtil.getString(actionRequest, "cardLastNumber");
		String expireOnMonth = ParamUtil.getString(actionRequest, "expireOnMonth");
		String expireOnYear = ParamUtil.getString(actionRequest, "expireOnYear");
		String accountNumber = ParamUtil.getString(actionRequest, "accountNumber");
		String acctInstnNm = StringPool.BLANK;
		String searchBranchType = ParamUtil.getString(actionRequest, "searchBranchType");
		String routingNo = StringPool.BLANK;
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
		
		try {
			CustomUserLocalServiceUtil.updateAccountDetail(token, themeDisplay.getUserId(), accountType,
					cardNumber, cardFirstName, cardLastName, expireOnMonth, expireOnYear, accountNumber,
					acctInstnNm, bankId, branchId, routingNo, searchBranchType);
			SessionMessages.add(actionRequest, "account-success");
		} catch (PortalException e) {
			if(Validator.isNotNull(e.getMessage())){
				SessionErrors.add(actionRequest, "profile-custom-update-error");
				request.getSession().setAttribute("customErr", e.getMessage());
			}else{
				SessionErrors.add(actionRequest, "acccount-err");
			}
			SessionMessages.add(actionRequest,
					PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			_log.error(e);
		} 
		
	}

}
