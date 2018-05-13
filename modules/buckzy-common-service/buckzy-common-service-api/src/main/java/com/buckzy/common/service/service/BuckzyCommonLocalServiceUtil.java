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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for BuckzyCommon. This utility wraps
 * {@link com.buckzy.common.service.service.impl.BuckzyCommonLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Sandip.Patel
 * @see BuckzyCommonLocalService
 * @see com.buckzy.common.service.service.base.BuckzyCommonLocalServiceBaseImpl
 * @see com.buckzy.common.service.service.impl.BuckzyCommonLocalServiceImpl
 * @generated
 */
@ProviderType
public class BuckzyCommonLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.buckzy.common.service.service.impl.BuckzyCommonLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean resendOTP(java.lang.String email,
		java.lang.String password) {
		return getService().resendOTP(email, password);
	}

	public static boolean verifyOTP(java.lang.String email,
		java.lang.String password, java.lang.String verificationCode) {
		return getService().verifyOTP(email, password, verificationCode);
	}

	public static com.buckzy.common.beans.PaymentBean converPaymentObjectToBean(
		com.liferay.portal.kernel.json.JSONObject paymentObj) {
		return getService().converPaymentObjectToBean(paymentObj);
	}

	public static com.liferay.portal.kernel.json.JSONArray getBankList(
		java.lang.String token, java.lang.String cityId) {
		return getService().getBankList(token, cityId);
	}

	public static com.liferay.portal.kernel.json.JSONArray getBranches(
		java.lang.String token, java.lang.String bankId, java.lang.String cityId) {
		return getService().getBranches(token, bankId, cityId);
	}

	public static com.liferay.portal.kernel.json.JSONArray getCityList(
		java.lang.String token, java.lang.String keyWord,
		java.lang.String stateCode, java.lang.String countryCode) {
		return getService().getCityList(token, keyWord, stateCode, countryCode);
	}

	public static com.liferay.portal.kernel.json.JSONArray getCountryList(
		java.lang.String token) {
		return getService().getCountryList(token);
	}

	public static com.liferay.portal.kernel.json.JSONArray getCurrencyList(
		java.lang.String token) {
		return getService().getCurrencyList(token);
	}

	public static com.liferay.portal.kernel.json.JSONArray getReceiverList(
		java.lang.String token, long partyId) {
		return getService().getReceiverList(token, partyId);
	}

	public static com.liferay.portal.kernel.json.JSONArray getStateList(
		java.lang.String token, java.lang.String countryCode) {
		return getService().getStateList(token, countryCode);
	}

	public static com.liferay.portal.kernel.json.JSONObject getAPIResponse(
		java.lang.String URL, java.lang.String type, java.lang.String params,
		java.lang.String token) {
		return getService().getAPIResponse(URL, type, params, token);
	}

	public static com.liferay.portal.kernel.json.JSONObject getBankDetail(
		java.lang.String token, int bankId) {
		return getService().getBankDetail(token, bankId);
	}

	public static com.liferay.portal.kernel.json.JSONObject getCreditCardDetail(
		java.lang.String token, java.lang.String cardNumber) {
		return getService().getCreditCardDetail(token, cardNumber);
	}

	public static com.liferay.portal.kernel.json.JSONObject getExchangeRate(
		java.lang.String token, java.lang.String fromCurCode,
		java.lang.String toCurCode) {
		return getService().getExchangeRate(token, fromCurCode, toCurCode);
	}

	public static com.liferay.portal.kernel.json.JSONObject getMultiPartResponse(
		java.lang.String URL, long partyId, java.lang.String token,
		java.io.File file, java.lang.String docTypeCode) {
		return getService()
				   .getMultiPartResponse(URL, partyId, token, file, docTypeCode);
	}

	public static com.liferay.portal.kernel.json.JSONObject getPartyByEmail(
		java.lang.String token, java.lang.String emailAddres) {
		return getService().getPartyByEmail(token, emailAddres);
	}

	public static com.liferay.portal.kernel.json.JSONObject getPaymentDetail(
		java.lang.String token, long paymentId) {
		return getService().getPaymentDetail(token, paymentId);
	}

	public static com.liferay.portal.kernel.json.JSONObject makePayment(
		java.lang.String token, long senderPartyId, long senderAcntId,
		long receiverPartyId, long receiverAcntId,
		java.lang.String sendingCurCode, java.lang.String receivingCurCode,
		float exchangeRate, double amount, java.lang.String purposeOfTransfer,
		java.lang.String purposeCode, java.lang.String instructionText)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .makePayment(token, senderPartyId, senderAcntId,
			receiverPartyId, receiverAcntId, sendingCurCode, receivingCurCode,
			exchangeRate, amount, purposeOfTransfer, purposeCode,
			instructionText);
	}

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
	public static com.liferay.portal.kernel.json.JSONObject registerUser(
		java.lang.String token, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String emailAddress, java.lang.String password1,
		java.lang.String address, java.lang.String city,
		java.lang.String zipcode, java.lang.String state,
		java.lang.String countryCode, java.lang.String currencyCode,
		java.lang.String dob, java.lang.String mobileNum,
		java.lang.String mobileCountryCode, java.lang.String reminderQuestion,
		java.lang.String reminderAnswer, java.lang.String reminderQuestion2,
		java.lang.String reminderAnswer2, java.lang.String deviceInfo,
		boolean isSocialLogin, long creatorUserId, long groupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .registerUser(token, firstName, middleName, lastName,
			emailAddress, password1, address, city, zipcode, state,
			countryCode, currencyCode, dob, mobileNum, mobileCountryCode,
			reminderQuestion, reminderAnswer, reminderQuestion2,
			reminderAnswer2, deviceInfo, isSocialLogin, creatorUserId, groupId,
			serviceContext);
	}

	public static java.lang.String dedcryptPassword(
		java.lang.String encryptedPass) {
		return getService().dedcryptPassword(encryptedPass);
	}

	public static java.lang.String encryptPassword(java.lang.String password) {
		return getService().encryptPassword(password);
	}

	public static java.lang.String extractErrMsgFromJson(
		com.liferay.portal.kernel.json.JSONObject erroObj) {
		return getService().extractErrMsgFromJson(erroObj);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.lang.String getRegistrationOTP(long userId) {
		return getService().getRegistrationOTP(userId);
	}

	public static java.lang.String getToken(java.lang.String email,
		java.lang.String password) {
		return getService().getToken(email, password);
	}

	public static java.util.List<com.buckzy.common.beans.PaymentBean> getPaymentTransactionList(
		java.lang.String token, int pageNo, int pageSize) {
		return getService().getPaymentTransactionList(token, pageNo, pageSize);
	}

	public static long getDefaultSiteGroupId() {
		return getService().getDefaultSiteGroupId();
	}

	public static void sendOTPMail(java.lang.String emailAddress,
		java.lang.String userName, java.lang.String otp,
		java.lang.String mobileNumber) {
		getService().sendOTPMail(emailAddress, userName, otp, mobileNumber);
	}

	public static BuckzyCommonLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BuckzyCommonLocalService, BuckzyCommonLocalService> _serviceTracker =
		ServiceTrackerFactory.open(BuckzyCommonLocalService.class);
}