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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BuckzyCommonLocalService}.
 *
 * @author Sandip.Patel
 * @see BuckzyCommonLocalService
 * @generated
 */
@ProviderType
public class BuckzyCommonLocalServiceWrapper implements BuckzyCommonLocalService,
	ServiceWrapper<BuckzyCommonLocalService> {
	public BuckzyCommonLocalServiceWrapper(
		BuckzyCommonLocalService buckzyCommonLocalService) {
		_buckzyCommonLocalService = buckzyCommonLocalService;
	}

	@Override
	public boolean resendOTP(java.lang.String email, java.lang.String password) {
		return _buckzyCommonLocalService.resendOTP(email, password);
	}

	@Override
	public boolean verifyOTP(java.lang.String email, java.lang.String password,
		java.lang.String verificationCode) {
		return _buckzyCommonLocalService.verifyOTP(email, password,
			verificationCode);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getBankList(
		java.lang.String token, java.lang.String countryCode) {
		return _buckzyCommonLocalService.getBankList(token, countryCode);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getBranches(
		java.lang.String token, java.lang.String bankId) {
		return _buckzyCommonLocalService.getBranches(token, bankId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getCityList(
		java.lang.String token, java.lang.String keyWord) {
		return _buckzyCommonLocalService.getCityList(token, keyWord);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getCountryList(
		java.lang.String token) {
		return _buckzyCommonLocalService.getCountryList(token);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getCurrencyList(
		java.lang.String token) {
		return _buckzyCommonLocalService.getCurrencyList(token);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getReceiverList(
		java.lang.String token, long partyId) {
		return _buckzyCommonLocalService.getReceiverList(token, partyId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getStateList(
		java.lang.String token, java.lang.String countryCode) {
		return _buckzyCommonLocalService.getStateList(token, countryCode);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getAPIResponse(
		java.lang.String URL, java.lang.String type, java.lang.String params,
		java.lang.String token) {
		return _buckzyCommonLocalService.getAPIResponse(URL, type, params, token);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getCreditCardDetail(
		java.lang.String token, java.lang.String cardNumber) {
		return _buckzyCommonLocalService.getCreditCardDetail(token, cardNumber);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getExchangeRate(
		java.lang.String token, java.lang.String fromCurCode,
		java.lang.String toCurCode) {
		return _buckzyCommonLocalService.getExchangeRate(token, fromCurCode,
			toCurCode);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject makePayment(
		java.lang.String token, long senderPartyId, long senderAcntId,
		long receiverPartyId, long receiverAcntId,
		java.lang.String sendingCurCode, java.lang.String receivingCurCode,
		float exchangeRate, double amount, java.lang.String purposeOfTransfer,
		java.lang.String purposeCode, java.lang.String instructionText)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _buckzyCommonLocalService.makePayment(token, senderPartyId,
			senderAcntId, receiverPartyId, receiverAcntId, sendingCurCode,
			receivingCurCode, exchangeRate, amount, purposeOfTransfer,
			purposeCode, instructionText);
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
	@Override
	public com.liferay.portal.kernel.model.User registerUser(
		java.lang.String token, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String emailAddress, java.lang.String password1,
		java.lang.String address, java.lang.String city,
		java.lang.String zipcode, java.lang.String state,
		java.lang.String countryCode, java.lang.String dob,
		java.lang.String mobileNum, java.lang.String mobileCountryCode,
		java.lang.String reminderQuestion, java.lang.String reminderAnswer,
		java.lang.String deviceInfo, boolean isSocialLogin, long creatorUserId,
		long groupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _buckzyCommonLocalService.registerUser(token, firstName,
			middleName, lastName, emailAddress, password1, address, city,
			zipcode, state, countryCode, dob, mobileNum, mobileCountryCode,
			reminderQuestion, reminderAnswer, deviceInfo, isSocialLogin,
			creatorUserId, groupId, serviceContext);
	}

	@Override
	public java.lang.String dedcryptPassword(java.lang.String encryptedPass) {
		return _buckzyCommonLocalService.dedcryptPassword(encryptedPass);
	}

	@Override
	public java.lang.String encryptPassword(java.lang.String password) {
		return _buckzyCommonLocalService.encryptPassword(password);
	}

	@Override
	public java.lang.String getMultiPartResponse(java.lang.String URL,
		long partyId, java.lang.String token, java.io.File file,
		java.lang.String docTypeCode) {
		return _buckzyCommonLocalService.getMultiPartResponse(URL, partyId,
			token, file, docTypeCode);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _buckzyCommonLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.lang.String getToken(java.lang.String email,
		java.lang.String password) {
		return _buckzyCommonLocalService.getToken(email, password);
	}

	@Override
	public long getDefaultSiteGroupId() {
		return _buckzyCommonLocalService.getDefaultSiteGroupId();
	}

	@Override
	public BuckzyCommonLocalService getWrappedService() {
		return _buckzyCommonLocalService;
	}

	@Override
	public void setWrappedService(
		BuckzyCommonLocalService buckzyCommonLocalService) {
		_buckzyCommonLocalService = buckzyCommonLocalService;
	}

	private BuckzyCommonLocalService _buckzyCommonLocalService;
}