package com.buckzy.profile.portlet.portlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.buckzy.common.beans.AccountBean;
import com.buckzy.common.beans.BranchBean;
import com.buckzy.common.beans.PartyAddressBean;
import com.buckzy.common.beans.PartyBean;
import com.buckzy.common.beans.UserBean;
import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.common.service.service.CustomUserLocalServiceUtil;
import com.buckzy.common.util.BuckzyConstants;
import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author sandip
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=buckzy-profile-module Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"com.liferay.portlet.action-url-redirect=true",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class BuckzyProfileModulePortlet extends MVCPortlet {
	
	Log _log = LogFactoryUtil.getLog(BuckzyProfileModulePortlet.class.getName());
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			
			User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
			UserBean userBean = new UserBean(user);
			
			String token = (String)PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest)).getSession().getAttribute("token");
			
			if(userBean.getCustomUserBean().getPartyId()>0){
				try {
					JSONObject partyDetail = CustomUserLocalServiceUtil.getPartyDetail(userBean.getCustomUserBean().getPartyId(),
							token);
					if(Validator.isNotNull(partyDetail)){
						
						PartyBean partyBean = new PartyBean();
						partyBean.setPrtyid(partyDetail.getLong("prtyid"));
						partyBean.setPrtyunivid(partyDetail.getString("prtyunivid"));
						partyBean.setAcctownrtype(partyDetail.getString("acctownrtype"));
						partyBean.setBasecurrcd(partyDetail.getString("basecurrcd"));
						partyBean.setFrstnm(partyDetail.getString("frstnm"));
						partyBean.setLastnm(partyDetail.getString("lastnm"));
						partyBean.setMidlnm(partyDetail.getString("midlnm"));
						partyBean.setCompnm(partyDetail.getString("compnm"));
						partyBean.setEmail(partyDetail.getString("email"));
						partyBean.setMoblnrcntrycd(partyDetail.getString("moblnrcntrycd"));
						partyBean.setMoblnr(partyDetail.getString("moblnr"));
						partyBean.setHomenr(partyDetail.getString("homenr"));
						partyBean.setOffcnr(partyDetail.getString("offcnr"));
						partyBean.setOffcneextn(partyDetail.getString("offcneextn"));
						partyBean.setGovtidnr(partyDetail.getString("govtidnr"));
						partyBean.setGovtidtype(partyDetail.getString("govtidtype"));
						partyBean.setModdate(partyDetail.getString("moddate"));
						partyBean.setAccount(partyDetail.getString("account"));
						
						JSONObject partyAddressDetail = partyDetail.getJSONObject("partyaddress");
						PartyAddressBean addrressBean = new PartyAddressBean();
					
						if(Validator.isNotNull(partyAddressDetail)){
							addrressBean.setPostaddr(partyAddressDetail.getString("postaddr"));
							addrressBean.setTownnm(partyAddressDetail.getString("townnm"));
							addrressBean.setState(partyAddressDetail.getString("state"));
							addrressBean.setZipcd(partyAddressDetail.getString("zipcd"));
							addrressBean.setCntrycd(partyAddressDetail.getString("cntrycd"));
						}
						
 						partyBean.setPartyAddressBean(addrressBean);
 						
 						JSONObject partyAccountDetail = partyDetail.getJSONObject("partyAccount");
 						AccountBean partyAccountBean = new AccountBean();
 						if(Validator.isNotNull(partyAccountDetail)){
	 						partyAccountBean.setAccountCategory(partyAccountDetail.getInt("accountCategory"));
	 						partyAccountBean.setAcctnr(partyAccountDetail.getString("acctNr"));
	 						partyAccountBean.setExprymnth(partyAccountDetail.getString("expryMnth"));
	 						partyAccountBean.setExpryyear(partyAccountDetail.getString("expryYear"));
	 						partyAccountBean.setRoutngnm(partyAccountDetail.getString("routngNm"));
	 						partyAccountBean.setBankId(partyAccountDetail.getInt("bankId"));
	 						partyAccountBean.setStateonacct(partyAccountDetail.getString("stateOnAcct"));
	 						partyAccountBean.setCityonacct(partyAccountDetail.getString("cityOnAcct"));
	 						partyAccountBean.setBranchId(partyAccountDetail.getInt("bankBranchId"));
	 						String nameonacct = partyAccountDetail.getString("nameOnAcct");
	 						
	 						String[] nameonacctArray = nameonacct.split(StringPool.SPACE);
	 						partyAccountBean.setNameonacct(nameonacct);
	 						if(nameonacctArray.length>1){
	 							partyAccountBean.setfName(nameonacctArray[0]);
	 							partyAccountBean.setlName(nameonacctArray[1]);
	 						}else{
	 							partyAccountBean.setfName(nameonacct);
	 							partyAccountBean.setlName(nameonacct);
	 						}
	 						partyBean.setAccountBean(partyAccountBean);
	 						
	 						JSONObject branchDetail = partyAccountDetail.getJSONObject("accountBranchDetail");
	 						BranchBean branchBean = new BranchBean();
	 						if(Validator.isNotNull(branchDetail)){
		 						branchBean.setBankId(branchDetail.getInt("bankId"));
		 						branchBean.setBranchId(branchDetail.getInt("branchId"));
		 						branchBean.setBranchRtngnr(branchDetail.getString("branchRtngnr"));
		 						branchBean.setStateCd(branchDetail.getString("stateCd"));
		 						branchBean.setCntryCd(branchDetail.getString("cntryCd"));
		 						branchBean.setSwiftBic(branchDetail.getString("swiftBic"));
		 						
		 						JSONObject cityObj = JSONFactoryUtil.createJSONObject(branchDetail.getString("city"));
		 						branchBean.setCityName(cityObj.getString("cityDesc"));
		 						branchBean.setCityId(cityObj.getInt("cityId"));
		 						
		 						partyAccountBean.setBranchBean(branchBean);
	 						}
 						}
 						
 						userBean.setPartyBean(partyBean);
						
					}
				} catch (PortalException e) {
					_log.error(e);
				}
			}
			
			renderRequest.setAttribute("userBean", userBean);
			
			// Get country List
			JSONArray countriesArray = BuckzyCommonLocalServiceUtil.getCountryList(token);
			List<JSONObject> countryJsonList = new ArrayList<JSONObject>();
			for(int i=0;i<countriesArray.length();i++){
				JSONObject countryObj = countriesArray.getJSONObject(i);
				JSONObject currencyObj = countryObj.getJSONObject("currency");
				countryObj.put("threedigitcd", currencyObj.get("currcd"));
				countryJsonList.add(countryObj);
			}
			renderRequest.setAttribute("countryJsonList", countryJsonList);
			
			// Get Bank List
			// TODO : Need to remove after binlist integration
			JSONArray bankArray = BuckzyCommonLocalServiceUtil.getBankList(token, BuckzyConstants.SINGAPORE_COUNTRY_CODE);
			List<JSONObject> bankJsonList = new ArrayList<JSONObject>();
			for(int i=0;i<bankArray.length();i++){
				bankJsonList.add(bankArray.getJSONObject(i));
			}
			renderRequest.setAttribute("bankJsonList", bankJsonList);
		
			
		} catch (PortalException e) {
			_log.error(e);
		}
		
		include(viewTemplate, renderRequest, renderResponse);
	}
}