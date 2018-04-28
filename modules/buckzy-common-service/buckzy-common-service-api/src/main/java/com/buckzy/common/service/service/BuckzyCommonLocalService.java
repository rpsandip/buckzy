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

package com.buckzy.common.service.service;

import aQute.bnd.annotation.ProviderType;

import com.buckzy.common.beans.PaymentBean;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.io.File;

import java.util.List;

/**
 * Provides the local service interface for BuckzyCommon. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Sandip.Patel
 * @see BuckzyCommonLocalServiceUtil
 * @see com.buckzy.common.service.service.base.BuckzyCommonLocalServiceBaseImpl
 * @see com.buckzy.common.service.service.impl.BuckzyCommonLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface BuckzyCommonLocalService extends BaseLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BuckzyCommonLocalServiceUtil} to access the buckzy common local service. Add custom service methods to {@link com.buckzy.common.service.service.impl.BuckzyCommonLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public boolean resendOTP(java.lang.String email, java.lang.String password);

	public boolean verifyOTP(java.lang.String email, java.lang.String password,
		java.lang.String verificationCode);

	public PaymentBean converPaymentObjectToBean(JSONObject paymentObj);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getBankList(java.lang.String token,
		java.lang.String countryCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getBranches(java.lang.String token, java.lang.String bankId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getCityList(java.lang.String token,
		java.lang.String keyWord, java.lang.String stateCode,
		java.lang.String countryCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getCountryList(java.lang.String token);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getCurrencyList(java.lang.String token);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getReceiverList(java.lang.String token, long partyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getStateList(java.lang.String token,
		java.lang.String countryCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getAPIResponse(java.lang.String URL,
		java.lang.String type, java.lang.String params, java.lang.String token);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getBankDetail(java.lang.String token, int bankId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getCreditCardDetail(java.lang.String token,
		java.lang.String cardNumber);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getExchangeRate(java.lang.String token,
		java.lang.String fromCurCode, java.lang.String toCurCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getMultiPartResponse(java.lang.String URL, long partyId,
		java.lang.String token, File file, java.lang.String docTypeCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getPartyByEmail(java.lang.String token,
		java.lang.String emailAddres);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getPaymentDetail(java.lang.String token, long paymentId);

	public JSONObject makePayment(java.lang.String token, long senderPartyId,
		long senderAcntId, long receiverPartyId, long receiverAcntId,
		java.lang.String sendingCurCode, java.lang.String receivingCurCode,
		float exchangeRate, double amount, java.lang.String purposeOfTransfer,
		java.lang.String purposeCode, java.lang.String instructionText)
		throws PortalException;

	/**
	* Register User
	*
	* @param firstName
	* @param middleName
	* @param lastName
	* @param emailAddress
	* @param password1
	* @param address
	* @param city
	* @param zipcode
	* @param state
	* @param dob
	* @param mobileNum
	* @return
	* @throws PortalException
	*/
	public JSONObject registerUser(java.lang.String token,
		java.lang.String firstName, java.lang.String middleName,
		java.lang.String lastName, java.lang.String emailAddress,
		java.lang.String password1, java.lang.String address,
		java.lang.String city, java.lang.String zipcode,
		java.lang.String state, java.lang.String countryCode,
		java.lang.String currencyCode, java.lang.String dob,
		java.lang.String mobileNum, java.lang.String mobileCountryCode,
		java.lang.String reminderQuestion, java.lang.String reminderAnswer,
		java.lang.String deviceInfo, boolean isSocialLogin, long creatorUserId,
		long groupId, ServiceContext serviceContext) throws PortalException;

	public java.lang.String dedcryptPassword(java.lang.String encryptedPass);

	public java.lang.String encryptPassword(java.lang.String password);

	public java.lang.String extractErrMsgFromJson(JSONObject erroObj);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getRegistrationOTP(long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getToken(java.lang.String email,
		java.lang.String password);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PaymentBean> getPaymentTransactionList(java.lang.String token,
		int pageNo, int pageSize);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getDefaultSiteGroupId();

	public void sendOTPMail(java.lang.String emailAddress,
		java.lang.String userName, java.lang.String otp,
		java.lang.String mobileNumber);
}