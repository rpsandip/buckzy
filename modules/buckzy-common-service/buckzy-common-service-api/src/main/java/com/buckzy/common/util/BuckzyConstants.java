package com.buckzy.common.util;

import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.util.PropsUtil;

public class BuckzyConstants {
	
	public static final String DATE_FORMAT="MM/DD/YYYY";
	public static final String BUCKZY_DEFAULT_SITE = GroupConstants.GUEST;
	public static final String FROM_EMAIL="no-reply@buckzy.com";
	
	public static final String HTTP_POST="POST";
	public static final String HTTP_GET="GET";
	public static final String HTTP_PUT="PUT";
	public static final String BASE_URL=PropsUtil.get("buckzy.rest.base.url");
	public static final String LOGIN_API_USERNAME=PropsUtil.get("buckzy.login.api.email");
	public static final String LOGIN_API_PASSWORD=PropsUtil.get("buckzy.login.api.password");
	public static final String IND_ACCOUNT_TYPE="INDV";
	public static final String SINGAPORE_CURRENCY_CODE="SGD";
	public static final String SINGAPORE_COUNTRY_CODE="SG";
	public static final int PAYMENT_PAGE_SIZE=10;
	
	
	public static final String LOGIN_POST_URL="/partyaccount/login";
	public static final String PARTY_CREATE_POST_URL="/partyaccount/V1/party";
	
	public static final String PARTY_UPDATE_PUT_URL="/partyaccount/V1/party/";
	public static final String GET_COUNTRY_LIST_URL ="/partyaccount/V1/countries";
	public static final String GET_BANK__LIST_URL ="/partyaccount/V1/banks?cntryCd=";
	public static final String GET_STATE_LIST_URL = "/partyaccount/V1/countries/countryCode/states";
	public static final String GET_CITY_LIST_URL = "/partyaccount/V1/cities?";
	public static final String GET_BRANCH__LIST_URL ="/partyaccount/V1/banks/bankId/branches/all";
	public static final String GET_PARTY_URL ="/partyaccount/V1/party/";
	public static final String KYC_DOC_URL ="/partyaccount/V1/documents?documentTypeCode=PARTY";
	public static final String GET_PARTY_ACCOUNTS="/partyaccount/V1/parties/partyId/accounts";
	public static final String GET_BRANCH_DETAIL="/partyaccount/V1/banks/bankId/branches/branchId";
	public static final String GET_CURRENCY_LIST_URL="/partyaccount/V1/currencies";
	public static final String GET_RECEIVER_LIST_URL="/partyaccount/V1/party/partyId/receivers";
	public static final String POST_PARTY_WITH_ACCOUNT_URL="/partyaccount/V1/party/partyId/partyWithAccnt";
	public static final String GET_EXCHANGE_URL="/partyaccount/V1/fxrates?fromCurrency=FromCurCd&toCurrency=ToCurCd";
	public static final String MAKE_PAYMENT="/partyaccount/V1/payments";
	public static final String GET_RECEIVER_ACCOUNT_URL="/partyaccount/V1/party/senderPartyId/receiver/receiverPartyId/accounts";
	public static final String GET_CARD_DETAIL="/partyaccount/V1/cardVerification/";
	public static final String POST_RESEND_OTP="/partyaccount/V1/users/resendOTP";
	public static final String GET_BANK_DETAIL="/partyaccount/V1/banks/";
	public static final String GET_ALL_PAYMENTS="/partyaccount/V1/payments/all?page=pageNo&size=pageSize";
	public static final String GET_PAYMENT_DETAIL_URL="/partyaccount/V1/payments/";
	public static final String GET_OTP_DETAIL="/partyaccount/V1/users/userId/otp";
	public static final String GET_PARTY_BY_EMAIL="/partyaccount/V1/party?email=";
	
	
}
