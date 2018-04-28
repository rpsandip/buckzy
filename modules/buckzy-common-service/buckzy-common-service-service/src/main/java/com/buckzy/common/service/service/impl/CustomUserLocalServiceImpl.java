/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.buckzy.common.service.service.impl;

import java.io.File;
import java.util.Date;
import java.util.Iterator;

import com.buckzy.common.beans.AccountBean;
import com.buckzy.common.beans.BranchBean;
import com.buckzy.common.beans.PartyAddressBean;
import com.buckzy.common.beans.PartyBean;
import com.buckzy.common.beans.UserBean;
import com.buckzy.common.service.exception.NoSuchCustomUserException;
import com.buckzy.common.service.model.CustomUser;
import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.common.service.service.CustomUserLocalServiceUtil;
import com.buckzy.common.service.service.base.CustomUserLocalServiceBaseImpl;
import com.buckzy.common.util.BuckzyConstants;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * The implementation of the custom user local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.buckzy.common.service.service.CustomUserLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Sandip.Patel
 * @see CustomUserLocalServiceBaseImpl
 * @see com.buckzy.common.service.service.CustomUserLocalServiceUtil
 */
public class CustomUserLocalServiceImpl extends CustomUserLocalServiceBaseImpl {

	Log _log = LogFactoryUtil.getLog(CustomUserLocalServiceImpl.class.getName());

	/**
	 * Add Custom user detail from registration
	 * 
	 * @throws PortalException
	 */
	public JSONObject addBuckzyCustomUser(String token, User user, String emailAddress, String password, String address,
			String zipcode, String city, String state, String countryCode, String currencyCode,String mobileNo, String mobileCountryCode,
			String deviceInfo, boolean socialLogin, long creatorUserId) throws PortalException {

		CustomUser customUser = CustomUserLocalServiceUtil.createCustomUser(CounterLocalServiceUtil.increment());
		customUser.setUserId(user.getUserId());

		customUser.setMobileNo(mobileNo);
		customUser.setMobCountryCode(mobileCountryCode);
		customUser.setDeviceInfo(getDeviceJSONString(deviceInfo));

		customUser.setCreateDate(new Date());
		customUser.setModifiedDate(new Date());
		customUser.setCreatedBy(creatorUserId);
		customUser.setModifiedBy(creatorUserId);


		customUser = CustomUserLocalServiceUtil.addCustomUser(customUser);

		// Create Party Account in the REST API..
		JSONObject partyDetailObj = JSONFactoryUtil.createJSONObject();
		if (Validator.isNotNull(mobileNo)) {
			partyDetailObj = createParty(token, user.getUserId(), user.getFirstName(), user.getLastName(),
					user.getEmailAddress(), password, mobileCountryCode, mobileNo, address, city, zipcode, state,countryCode, currencyCode);

			if (partyDetailObj.getLong("prtyid") > 0) {
				customUser.setPartyId(partyDetailObj.getLong("prtyid"));

				JSONArray usersArray = JSONFactoryUtil.createJSONArray(partyDetailObj.getString("users"));
				if (Validator.isNotNull(usersArray) && usersArray.length() > 0) {
					JSONObject userObject = usersArray.getJSONObject(0);
					long userId = userObject.getLong("userid");
					if (userId > 0) {
						customUser.setPartyUserId(userId);
					}
				}
				CustomUserLocalServiceUtil.updateCustomUser(customUser);
			}

		}

		return partyDetailObj;
	}

	public JSONObject createParty(String token, long userId, String fName, String lastName, String email,
			String password, String mobileContryCode, String mobileNo, String address, String city, String zipcode,
			String stateCode, String countryCode, String currencyCode) throws PortalException {

		long partyId = 0;
		if(Validator.isNull(currencyCode)){
			currencyCode = BuckzyConstants.SINGAPORE_CURRENCY_CODE;
		}

		JSONObject paramsJsonObj = JSONFactoryUtil.createJSONObject();
		paramsJsonObj.put("acctownrtype", BuckzyConstants.IND_ACCOUNT_TYPE);
		// TODO: Need to make dynamic
		paramsJsonObj.put("basecurrcd", currencyCode);
		paramsJsonObj.put("sndrflg", "T");
		// TODO: Need to get proper value of it
		paramsJsonObj.put("govtidtype", "ABC");
		paramsJsonObj.put("frstnm", fName);
		paramsJsonObj.put("lastnm", lastName);
		paramsJsonObj.put("email", email);
		paramsJsonObj.put("password", password);
		paramsJsonObj.put("moblnrcntrycd", mobileContryCode);
		paramsJsonObj.put("moblnr", mobileNo);

		JSONObject addressObj = JSONFactoryUtil.createJSONObject();
		addressObj.put("postaddr", address);
		addressObj.put("townnm", city);
		addressObj.put("statecd", stateCode);
		addressObj.put("cntrycd", countryCode);
		addressObj.put("zipcd", zipcode);

		paramsJsonObj.put("ptyaddr", addressObj);

		String params = paramsJsonObj.toJSONString();

		JSONObject responseObj = BuckzyCommonLocalServiceUtil.getAPIResponse(BuckzyConstants.PARTY_CREATE_POST_URL,
				BuckzyConstants.HTTP_POST, params, token);

		int status = responseObj.getInt("status");
		JSONObject dataObj = JSONFactoryUtil.createJSONObject(responseObj.getString("data"));
		
		if (status != 200) {
			String errMsg = BuckzyCommonLocalServiceUtil.extractErrMsgFromJson(dataObj.getJSONObject("errors"));
			PortalException pe = new PortalException(errMsg);
			throw pe;
		}

		return dataObj;
	}

	public JSONObject updateParty(String token, long partyId, long userId, String fName, String lastName, String email,
			String mobileCountryCode, String countryCode, String currencyCode,String mobileNo, String address, String city, String stateCode,
			String zipcode, String documentType, File file, String verificationDocName, String accountType,
			String cardNumber, String cardFirstName, String cardLastName, String expireOnMonth, String expireOnYear,
			String accountNumber, String acctInstnNm, int bankId, int branchId, String routingNumber,
			String searchBranchType) throws PortalException {

		PortalCache portalCache = MultiVMPoolUtil.getCache(User.class.getName());
		String password = (String) portalCache.get("user_token");

		JSONObject obj = JSONFactoryUtil.createJSONObject();
		JSONObject paramsJsonObj = JSONFactoryUtil.createJSONObject();
		
		if(Validator.isNull(currencyCode)){
			currencyCode =BuckzyConstants.SINGAPORE_CURRENCY_CODE;
		}

		// TODO: Need to make dynamic
		paramsJsonObj.put("acctownrtype", BuckzyConstants.IND_ACCOUNT_TYPE);
		paramsJsonObj.put("basecurrcd", currencyCode);
		paramsJsonObj.put("sndrflg", "T");
		paramsJsonObj.put("frstnm", fName);
		paramsJsonObj.put("lastnm", lastName);
		paramsJsonObj.put("moblnrcntrycd", mobileCountryCode);
		paramsJsonObj.put("moblnr", mobileNo);
		paramsJsonObj.put("password", password);

		JSONObject addressObj = JSONFactoryUtil.createJSONObject();
		addressObj.put("postaddr", address);
		addressObj.put("townnm", city);
		addressObj.put("statecd", stateCode);
		addressObj.put("cntrycd", countryCode);
		addressObj.put("zipcd", zipcode);

		paramsJsonObj.put("ptyaddr", addressObj);

		String params = paramsJsonObj.toJSONString();

		// Update Party detail
		JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(
				BuckzyConstants.PARTY_UPDATE_PUT_URL + partyId, BuckzyConstants.HTTP_PUT, params, token);
		JSONObject dataObj = JSONFactoryUtil.createJSONObject(response.getString("data"));

		int status = response.getInt("status");

		if (status == 200) {

			CustomUser customUser = CustomUserLocalServiceUtil.getCustomUserByLRUserId(userId);
			customUser.setMobileNo(mobileNo);
			customUser.setMobCountryCode(mobileCountryCode);
			CustomUserLocalServiceUtil.updateCustomUser(customUser);

			// Update User

			User user = UserLocalServiceUtil.getUser(userId);
			user.setFirstName(fName);
			user.setLastName(lastName);
			UserLocalServiceUtil.updateUser(user);

			// Upload verification Documents
			if (Validator.isNotNull(verificationDocName)) {

				JSONObject fileUploadResponse = BuckzyCommonLocalServiceUtil.getMultiPartResponse(
						BuckzyConstants.KYC_DOC_URL, customUser.getPartyId(), token, file, documentType);

				int fileUploadStatus = fileUploadResponse.getInt("status");

				if (fileUploadStatus != 200) {
					PortalException pe = new PortalException(fileUploadResponse.getString("developerMessage"));
					throw pe;
				} else {
					customUser.setDocumentVerified(true);
				}

			}

			// Update Account Detail

			JSONObject accountJsonObj = JSONFactoryUtil.createJSONObject();

			if (accountType.equals("debit_card")) {

				if(Validator.isNull(acctInstnNm)){
					acctInstnNm = getAccountInstNumber(token, bankId);
				}
				accountJsonObj.put("acctNr", cardNumber);
				accountJsonObj.put("acctUsgCd", "A");
				accountJsonObj.put("acctInstnNm", acctInstnNm);
				accountJsonObj.put("nameOnAcct", cardFirstName + StringPool.SPACE + cardLastName);
				accountJsonObj.put("addr1OnAcct", address);
				accountJsonObj.put("addr2OnAcct", address);
				accountJsonObj.put("cityOnAcct", city);
				accountJsonObj.put("stateOnAcct", stateCode);
				accountJsonObj.put("cntryOnAcct", countryCode);
				accountJsonObj.put("zipOnAcct", zipcode);
				accountJsonObj.put("expryMnth", expireOnMonth);
				accountJsonObj.put("expryYear", expireOnYear);
				accountJsonObj.put("prefAcctFlag", "T");
				accountJsonObj.put("acctType", "SV");
				accountJsonObj.put("bankId", bankId);
				accountJsonObj.put("accountCategory", 2); // 2 for debit card

			} else {

				accountJsonObj.put("acctNr", accountNumber);
				accountJsonObj.put("acctUsgCd", "A");
				accountJsonObj.put("nameOnAcct", user.getFirstName() + StringPool.SPACE + user.getLastName());
				accountJsonObj.put("addr1OnAcct", address);
				accountJsonObj.put("addr2OnAcct", address);
				accountJsonObj.put("cityOnAcct", city);
				accountJsonObj.put("stateOnAcct", stateCode);
				accountJsonObj.put("cntryOnAcct", countryCode);
				accountJsonObj.put("zipOnAcct", zipcode);
				accountJsonObj.put("prefAcctFlag", "T");
				accountJsonObj.put("acctType", "CV");
				if (searchBranchType.equals("known_branch")) {
					accountJsonObj.put("routngNm", routingNumber);
				} else {
					accountJsonObj.put("acctInstnNm", acctInstnNm);
					accountJsonObj.put("bankId", bankId);
					accountJsonObj.put("bankBranchId", branchId);
				}

				accountJsonObj.put("accountCategory", 1); // 1 for bank account
			}

			String accountsParams = accountJsonObj.toJSONString();

			JSONObject accountResponse = BuckzyCommonLocalServiceUtil.getAPIResponse(
					"/partyaccount/V1/parties/" + partyId + "/accounts", BuckzyConstants.HTTP_POST, accountsParams,
					token);
			JSONObject accountDataObject = JSONFactoryUtil.createJSONObject(accountResponse.getString("data"));
			int accountResStatus = accountResponse.getInt("status");

			if (accountResStatus == 200) {

				customUser.setProfileComplete(true);
				customUser.setAccountCompleted(true);
				CustomUserLocalServiceUtil.updateCustomUser(customUser);

				obj.put("status", "success");
				
			} else {
				String errMsg = BuckzyCommonLocalServiceUtil.extractErrMsgFromJson(accountDataObject.getJSONObject("errors"));
				PortalException pe = new PortalException(errMsg);
				throw pe;
			}
		} else {
			String errMsg = BuckzyCommonLocalServiceUtil.extractErrMsgFromJson(dataObj.getJSONObject("errors"));
			PortalException pe = new PortalException(errMsg);
			throw pe;
		}

		return obj;
	}

	public JSONObject getPartyDetail(long partyId, String token) throws PortalException {

		JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(BuckzyConstants.GET_PARTY_URL + partyId,
				BuckzyConstants.HTTP_GET, StringPool.BLANK, token);
		JSONObject partyDataObj = JSONFactoryUtil.createJSONObject(response.getString("data"));
		int status = response.getInt("status");

		if (status == 200) {
			JSONObject accountOnj = getPartyPreferAccount(partyId, token);
			partyDataObj.put("partyAccount", accountOnj);
			return partyDataObj;
		} else {
			String errMsg = BuckzyCommonLocalServiceUtil.extractErrMsgFromJson(partyDataObj.getJSONObject("errors"));
			PortalException pe = new PortalException(errMsg);
			throw pe;
		}
	}

	public UserBean getPartyUserBean(String token, long userId) {

		UserBean userBean = null;
		
		try {

			User user = UserLocalServiceUtil.getUser(userId);
			userBean = new UserBean(user);

			JSONObject partyDetail = CustomUserLocalServiceUtil
					.getPartyDetail(userBean.getCustomUserBean().getPartyId(), token);
			if (Validator.isNotNull(partyDetail)) {

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

				if (Validator.isNotNull(partyAddressDetail)) {
					addrressBean.setPostaddr(partyAddressDetail.getString("postaddr"));
					addrressBean.setTownnm(partyAddressDetail.getString("townnm"));
					addrressBean.setState(partyAddressDetail.getString("statecd"));
					addrressBean.setZipcd(partyAddressDetail.getString("zipcd"));
					addrressBean.setCntrycd(partyAddressDetail.getString("cntrycd"));
				}

				partyBean.setPartyAddressBean(addrressBean);

				JSONObject partyAccountDetail = partyDetail.getJSONObject("partyAccount");
				AccountBean partyAccountBean = new AccountBean();
				if (Validator.isNotNull(partyAccountDetail)) {
					partyAccountBean.setAccountCategory(partyAccountDetail.getInt("accountCategory"));
					partyAccountBean.setAcctnr(partyAccountDetail.getString("acctNr"));
					partyAccountBean.setExprymnth(partyAccountDetail.getString("expryMnth"));
					partyAccountBean.setExpryyear(partyAccountDetail.getString("expryYear"));
					partyAccountBean.setRoutngnm(partyAccountDetail.getString("routngNm"));
					partyAccountBean.setBankId(partyAccountDetail.getInt("bankId"));
					partyAccountBean.setStateonacct(partyAccountDetail.getString("stateOnAcct"));
					partyAccountBean.setCityonacct(partyAccountDetail.getString("cityOnAcct"));
					partyAccountBean.setBranchId(partyAccountDetail.getInt("bankBranchId"));
					String nameonacct = partyAccountDetail.getString("nameonacct");

					String[] nameonacctArray = nameonacct.split(StringPool.SPACE);
					partyAccountBean.setNameonacct(nameonacct);
					if (nameonacctArray.length > 1) {
						partyAccountBean.setfName(nameonacctArray[0]);
						partyAccountBean.setlName(nameonacctArray[1]);
					} else {
						partyAccountBean.setfName(nameonacct);
						partyAccountBean.setlName(nameonacct);
					}
					partyBean.setAccountBean(partyAccountBean);

					JSONObject branchDetail = partyAccountDetail.getJSONObject("accountBranchDetail");
					BranchBean branchBean = new BranchBean();
					if (Validator.isNotNull(branchDetail)) {
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
		
		return userBean;

	}

	public JSONObject getReceiverAccountDetail(String token, long senderPartyId, long receiverPartyId)
			throws PortalException {
		JSONObject receiverAccountObj = JSONFactoryUtil.createJSONObject();
		String url = BuckzyConstants.GET_RECEIVER_ACCOUNT_URL.replace("senderPartyId", String.valueOf(senderPartyId));
		url = url.replace("receiverPartyId", String.valueOf(receiverPartyId));
		JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(url, BuckzyConstants.HTTP_GET,
				StringPool.BLANK, token);
		int status = response.getInt("status");
		if (status == 200) {
			JSONArray receiverAccountsArray = JSONFactoryUtil.createJSONArray(response.getString("data"));
			if (receiverAccountsArray.length() > 0) {
				receiverAccountObj = receiverAccountsArray.getJSONObject(0);
			}
		} else {
			String errMsg = BuckzyCommonLocalServiceUtil.extractErrMsgFromJson(response.getJSONObject("errors"));
			PortalException pe = new PortalException(errMsg);
			throw pe;
		}
		return receiverAccountObj;
	}

	public JSONObject getPartyPreferAccount(long partyId, String token) throws PortalException {

		String url = BuckzyConstants.GET_PARTY_ACCOUNTS.replace("partyId", String.valueOf(partyId));

		JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(url, BuckzyConstants.HTTP_GET,
				StringPool.BLANK, token);

		JSONObject responseObj = JSONFactoryUtil.createJSONObject();

		JSONArray userAccounts = JSONFactoryUtil.createJSONArray(response.getString("data"));

		int status = response.getInt("status");
		boolean isPreferAccountExist = false;

		if (status == 200) {

			for (int i = 0; i < userAccounts.length(); i++) {
				JSONObject userAccount = userAccounts.getJSONObject(i);
				if (Validator.isNotNull(userAccount.get("prefAcctFlag"))
						&& userAccount.get("prefAcctFlag").equals("T")) {
					responseObj = userAccounts.getJSONObject(i);
					isPreferAccountExist = true;
				}
			}

			if (!isPreferAccountExist) {
				responseObj = userAccounts.getJSONObject(0);
			}

			if (Validator.isNotNull(responseObj) && responseObj.getInt("bankId") != 0
					&& responseObj.getInt("bankBranchId") != 0) {
				JSONObject accountBranchDetail = getBranchDetail(responseObj.getInt("bankId"),
						responseObj.getInt("bankBranchId"), token);
				responseObj.put("accountBranchDetail", accountBranchDetail);
			}

			return responseObj;
		} else {
			String errMsg = BuckzyCommonLocalServiceUtil.extractErrMsgFromJson(JSONFactoryUtil.createJSONObject(response.getString("data")).getJSONObject("errors"));
			PortalException pe = new PortalException(errMsg);
			throw pe;
		}
	}

	public JSONObject getBranchDetail(int bankId, int branchId, String token) throws PortalException {

		String url = BuckzyConstants.GET_BRANCH_DETAIL.replace("bankId", String.valueOf(bankId));
		url = url.replace("branchId", String.valueOf(branchId));

		JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(url, BuckzyConstants.HTTP_GET,
				StringPool.BLANK, token);

		int status = response.getInt("status");

		if (status == 200) {
			return JSONFactoryUtil.createJSONObject(response.getString("data"));
		} else {
			String errMsg = BuckzyCommonLocalServiceUtil.extractErrMsgFromJson(JSONFactoryUtil.createJSONObject(response.getString("data")).getJSONObject("errors"));
			PortalException pe = new PortalException(errMsg);
			throw pe;
		}
	}
	
	public JSONObject updateAccountDetail(String token, long userId, String accountType,String cardNumber, String cardFirstName, String cardLastName, String expireOnMonth, String expireOnYear,
			String accountNumber, String acctInstnNm, int bankId, int branchId, String routingNumber,
			String searchBranchType) throws JSONException, PortalException{
		
			CustomUser customUser = CustomUserLocalServiceUtil.getCustomUserByLRUserId(userId);
			long partyId = customUser.getPartyId();
			String state = StringPool.BLANK;
			
			JSONObject responseObj = JSONFactoryUtil.createJSONObject();
			// Update Account Detail
			
			JSONObject partyDetail = CustomUserLocalServiceUtil.getPartyDetail(partyId, token);
			
			if(Validator.isNotNull(partyDetail) && Validator.isNotNull(partyDetail.getJSONObject("partyaddress"))){

			JSONObject partyAddressDetail = partyDetail.getJSONObject("partyaddress");
				
			JSONObject accountJsonObj = JSONFactoryUtil.createJSONObject();
			
			if(Validator.isNotNull(cardFirstName)){
				cardFirstName = partyDetail.getString("frstnm");
				cardLastName = partyDetail.getString("lastnm");
				state = partyAddressDetail.getString("statecd");
			}
			if(Validator.isNull(cardLastName)){
				cardLastName = partyDetail.getString("lastnm");
			}
			if(Validator.isNull(partyAddressDetail.getString("statecd"))){
				state = "Test";
			}

			if (accountType.equals("debit_card")) {
				
				if(Validator.isNull(acctInstnNm)){
					acctInstnNm = getAccountInstNumber(token, bankId);
				}
				accountJsonObj.put("acctNr", cardNumber);
				accountJsonObj.put("acctUsgCd", "A");
				accountJsonObj.put("acctInstnNm", acctInstnNm);
				accountJsonObj.put("nameOnAcct", cardFirstName + StringPool.SPACE + cardLastName);
				accountJsonObj.put("addr1OnAcct", partyAddressDetail.getString("postaddr"));
				accountJsonObj.put("addr2OnAcct", partyAddressDetail.getString("postaddr"));
				accountJsonObj.put("cityOnAcct", partyAddressDetail.getString("townnm"));
				accountJsonObj.put("stateOnAcct", state);
				accountJsonObj.put("cntryOnAcct", partyAddressDetail.getString("cntrycd"));
				accountJsonObj.put("zipOnAcct", partyAddressDetail.getString("zipcd"));
				accountJsonObj.put("expryMnth", expireOnMonth);
				accountJsonObj.put("expryYear", expireOnYear);
				accountJsonObj.put("prefAcctFlag", "T");
				accountJsonObj.put("acctType", "SV");
				accountJsonObj.put("bankId", bankId);
				accountJsonObj.put("accountCategory", 2); // 2 for debit card

			} else {

				accountJsonObj.put("acctNr", accountNumber);
				accountJsonObj.put("acctUsgCd", "A");
				accountJsonObj.put("nameOnAcct", cardFirstName + StringPool.SPACE + cardLastName);
				accountJsonObj.put("addr1OnAcct", partyAddressDetail.getString("postaddr"));
				accountJsonObj.put("addr2OnAcct", partyAddressDetail.getString("postaddr"));
				accountJsonObj.put("cityOnAcct", partyAddressDetail.getString("townnm"));
				accountJsonObj.put("stateOnAcct", state);
				accountJsonObj.put("cntryOnAcct", partyAddressDetail.getString("cntrycd"));
				accountJsonObj.put("zipOnAcct", partyAddressDetail.getString("zipcd"));
				accountJsonObj.put("prefAcctFlag", "T");
				accountJsonObj.put("acctType", "CV");
				if (searchBranchType.equals("known_branch")) {
					accountJsonObj.put("routngNm", routingNumber);
				} else {
					accountJsonObj.put("acctInstnNm", acctInstnNm);
					accountJsonObj.put("bankId", bankId);
					accountJsonObj.put("bankBranchId", branchId);
				}

				accountJsonObj.put("accountCategory", 1); // 1 for bank account
			}

			String accountsParams = accountJsonObj.toJSONString();

			JSONObject accountResponse = BuckzyCommonLocalServiceUtil.getAPIResponse(
					"/partyaccount/V1/parties/" + partyId + "/accounts", BuckzyConstants.HTTP_POST, accountsParams,
					token);
			
			responseObj = JSONFactoryUtil.createJSONObject(accountResponse.getString("data"));

			int accountResStatus = accountResponse.getInt("status");

			if (accountResStatus == 200) {

				customUser.setProfileComplete(true);
				customUser.setAccountCompleted(true);
				CustomUserLocalServiceUtil.updateCustomUser(customUser);

			} else {
				String errMsg = BuckzyCommonLocalServiceUtil.extractErrMsgFromJson(responseObj.getJSONObject("errors"));
				PortalException pe = new PortalException(errMsg);
				throw pe;
			}
			
		 }
			
			return responseObj;
		
	}

	public CustomUser updateMobileNumber(String emailAddress, String mobileNo, String mobCountryCode)
			throws PortalException {
		CustomUser customUser = null;

		User user = UserLocalServiceUtil.getUserByEmailAddress(PortalUtil.getDefaultCompanyId(), emailAddress);
		customUser = CustomUserLocalServiceUtil.getCustomUserByLRUserId(user.getUserId());
		customUser.setMobCountryCode(mobCountryCode);
		customUser.setMobileNo(mobileNo);

		CustomUserLocalServiceUtil.updateCustomUser(customUser);

		return customUser;
	}

	public String addDeviceInfo(CustomUser customUser, String newDeviceInfo) {
		String updatedDeviceInfo = StringPool.BLANK;

		if (Validator.isNotNull(customUser)) {
			String deviceInfo = customUser.getDeviceInfo();
			try {
				JSONArray jsonArray = JSONFactoryUtil.createJSONArray(deviceInfo);
				JSONObject newDeviceObj = JSONFactoryUtil.createJSONObject(newDeviceInfo);
				jsonArray.put(newDeviceObj);
				customUser.setDeviceInfo(jsonArray.toJSONString());
				CustomUserLocalServiceUtil.updateCustomUser(customUser);
			} catch (JSONException e) {
				_log.error(e);
			}

		}

		return updatedDeviceInfo;
	}
	
	
	
	private String getAccountInstNumber(String token, int bankId){
    	String accountInstNm = StringPool.BLANK;
    	JSONObject bankDetailObj = BuckzyCommonLocalServiceUtil.getBankDetail(token, bankId);
    	if(Validator.isNotNull(bankDetailObj) && Validator.isNotNull(bankDetailObj.get("bankNm"))){
    		return (String)bankDetailObj.get("bankNm");
    	}
    	return accountInstNm;
    }

	private String getDeviceJSONString(String deviceInfo) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		JSONObject deviceObj;
		try {
			deviceObj = JSONFactoryUtil.createJSONObject(deviceInfo);
			jsonArray.put(deviceObj);
		} catch (JSONException e) {
			_log.error(e);
		}
		return jsonArray.toJSONString();

	}

	public CustomUser getCustomUserByMobileNumber(String mobileNo, String countryCode)
			throws NoSuchCustomUserException {
		return customUserPersistence.findByMobileNumber(mobileNo, countryCode);
	}

	public CustomUser getCustomUserByLRUserId(long userId) throws NoSuchCustomUserException {
		return customUserPersistence.findByUserId(userId);
	}
}