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
 * Provides a wrapper for {@link CustomUserLocalService}.
 *
 * @author Sandip.Patel
 * @see CustomUserLocalService
 * @generated
 */
@ProviderType
public class CustomUserLocalServiceWrapper implements CustomUserLocalService,
	ServiceWrapper<CustomUserLocalService> {
	public CustomUserLocalServiceWrapper(
		CustomUserLocalService customUserLocalService) {
		_customUserLocalService = customUserLocalService;
	}

	@Override
	public com.buckzy.common.beans.UserBean getPartyUserBean(
		java.lang.String token, long userId) {
		return _customUserLocalService.getPartyUserBean(token, userId);
	}

	/**
	* Adds the custom user to the database. Also notifies the appropriate model listeners.
	*
	* @param customUser the custom user
	* @return the custom user that was added
	*/
	@Override
	public com.buckzy.common.service.model.CustomUser addCustomUser(
		com.buckzy.common.service.model.CustomUser customUser) {
		return _customUserLocalService.addCustomUser(customUser);
	}

	/**
	* Creates a new custom user with the primary key. Does not add the custom user to the database.
	*
	* @param customUserId the primary key for the new custom user
	* @return the new custom user
	*/
	@Override
	public com.buckzy.common.service.model.CustomUser createCustomUser(
		long customUserId) {
		return _customUserLocalService.createCustomUser(customUserId);
	}

	/**
	* Deletes the custom user from the database. Also notifies the appropriate model listeners.
	*
	* @param customUser the custom user
	* @return the custom user that was removed
	*/
	@Override
	public com.buckzy.common.service.model.CustomUser deleteCustomUser(
		com.buckzy.common.service.model.CustomUser customUser) {
		return _customUserLocalService.deleteCustomUser(customUser);
	}

	/**
	* Deletes the custom user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customUserId the primary key of the custom user
	* @return the custom user that was removed
	* @throws PortalException if a custom user with the primary key could not be found
	*/
	@Override
	public com.buckzy.common.service.model.CustomUser deleteCustomUser(
		long customUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customUserLocalService.deleteCustomUser(customUserId);
	}

	@Override
	public com.buckzy.common.service.model.CustomUser fetchCustomUser(
		long customUserId) {
		return _customUserLocalService.fetchCustomUser(customUserId);
	}

	/**
	* Returns the custom user with the primary key.
	*
	* @param customUserId the primary key of the custom user
	* @return the custom user
	* @throws PortalException if a custom user with the primary key could not be found
	*/
	@Override
	public com.buckzy.common.service.model.CustomUser getCustomUser(
		long customUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customUserLocalService.getCustomUser(customUserId);
	}

	@Override
	public com.buckzy.common.service.model.CustomUser getCustomUserByLRUserId(
		long userId)
		throws com.buckzy.common.service.exception.NoSuchCustomUserException {
		return _customUserLocalService.getCustomUserByLRUserId(userId);
	}

	@Override
	public com.buckzy.common.service.model.CustomUser getCustomUserByMobileNumber(
		java.lang.String mobileNo, java.lang.String countryCode)
		throws com.buckzy.common.service.exception.NoSuchCustomUserException {
		return _customUserLocalService.getCustomUserByMobileNumber(mobileNo,
			countryCode);
	}

	/**
	* Updates the custom user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param customUser the custom user
	* @return the custom user that was updated
	*/
	@Override
	public com.buckzy.common.service.model.CustomUser updateCustomUser(
		com.buckzy.common.service.model.CustomUser customUser) {
		return _customUserLocalService.updateCustomUser(customUser);
	}

	@Override
	public com.buckzy.common.service.model.CustomUser updateMobileNumber(
		java.lang.String emailAddress, java.lang.String mobileNo,
		java.lang.String mobCountryCode)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customUserLocalService.updateMobileNumber(emailAddress,
			mobileNo, mobCountryCode);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _customUserLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _customUserLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _customUserLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Add Custom user detail from registration
	*
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject addBuckzyCustomUser(
		java.lang.String token, com.liferay.portal.kernel.model.User user,
		java.lang.String emailAddress, java.lang.String password,
		java.lang.String address, java.lang.String zipcode,
		java.lang.String city, java.lang.String state,
		java.lang.String countryCode, java.lang.String currencyCode,
		java.lang.String mobileNo, java.lang.String mobileCountryCode,
		java.lang.String deviceInfo, boolean socialLogin, long creatorUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customUserLocalService.addBuckzyCustomUser(token, user,
			emailAddress, password, address, zipcode, city, state, countryCode,
			currencyCode, mobileNo, mobileCountryCode, deviceInfo, socialLogin,
			creatorUserId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject createParty(
		java.lang.String token, long userId, java.lang.String fName,
		java.lang.String lastName, java.lang.String email,
		java.lang.String password, java.lang.String mobileContryCode,
		java.lang.String mobileNo, java.lang.String address,
		java.lang.String city, java.lang.String zipcode,
		java.lang.String stateCode, java.lang.String countryCode,
		java.lang.String currencyCode)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customUserLocalService.createParty(token, userId, fName,
			lastName, email, password, mobileContryCode, mobileNo, address,
			city, zipcode, stateCode, countryCode, currencyCode);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getBranchDetail(
		int bankId, int branchId, java.lang.String token)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customUserLocalService.getBranchDetail(bankId, branchId, token);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getPartyDetail(
		long partyId, java.lang.String token)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customUserLocalService.getPartyDetail(partyId, token);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getPartyPreferAccount(
		long partyId, java.lang.String token)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customUserLocalService.getPartyPreferAccount(partyId, token);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getReceiverAccountDetail(
		java.lang.String token, long senderPartyId, long receiverPartyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customUserLocalService.getReceiverAccountDetail(token,
			senderPartyId, receiverPartyId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject updateAccountDetail(
		java.lang.String token, long userId, java.lang.String accountType,
		java.lang.String cardNumber, java.lang.String cardFirstName,
		java.lang.String cardLastName, java.lang.String expireOnMonth,
		java.lang.String expireOnYear, java.lang.String accountNumber,
		java.lang.String acctInstnNm, int bankId, int branchId,
		java.lang.String routingNumber, java.lang.String searchBranchType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.json.JSONException {
		return _customUserLocalService.updateAccountDetail(token, userId,
			accountType, cardNumber, cardFirstName, cardLastName,
			expireOnMonth, expireOnYear, accountNumber, acctInstnNm, bankId,
			branchId, routingNumber, searchBranchType);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject updateParty(
		java.lang.String token, long partyId, long userId,
		java.lang.String fName, java.lang.String lastName,
		java.lang.String email, java.lang.String mobileCountryCode,
		java.lang.String countryCode, java.lang.String currencyCode,
		java.lang.String mobileNo, java.lang.String address,
		java.lang.String city, java.lang.String stateCode,
		java.lang.String zipcode, java.lang.String documentType,
		java.io.File file, java.lang.String verificationDocName,
		java.lang.String accountType, java.lang.String cardNumber,
		java.lang.String cardFirstName, java.lang.String cardLastName,
		java.lang.String expireOnMonth, java.lang.String expireOnYear,
		java.lang.String accountNumber, java.lang.String acctInstnNm,
		int bankId, int branchId, java.lang.String routingNumber,
		java.lang.String searchBranchType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customUserLocalService.updateParty(token, partyId, userId,
			fName, lastName, email, mobileCountryCode, countryCode,
			currencyCode, mobileNo, address, city, stateCode, zipcode,
			documentType, file, verificationDocName, accountType, cardNumber,
			cardFirstName, cardLastName, expireOnMonth, expireOnYear,
			accountNumber, acctInstnNm, bankId, branchId, routingNumber,
			searchBranchType);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customUserLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _customUserLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of custom users.
	*
	* @return the number of custom users
	*/
	@Override
	public int getCustomUsersCount() {
		return _customUserLocalService.getCustomUsersCount();
	}

	@Override
	public java.lang.String addDeviceInfo(
		com.buckzy.common.service.model.CustomUser customUser,
		java.lang.String newDeviceInfo) {
		return _customUserLocalService.addDeviceInfo(customUser, newDeviceInfo);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _customUserLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _customUserLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.buckzy.common.service.model.impl.CustomUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _customUserLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.buckzy.common.service.model.impl.CustomUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _customUserLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the custom users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.buckzy.common.service.model.impl.CustomUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom users
	* @param end the upper bound of the range of custom users (not inclusive)
	* @return the range of custom users
	*/
	@Override
	public java.util.List<com.buckzy.common.service.model.CustomUser> getCustomUsers(
		int start, int end) {
		return _customUserLocalService.getCustomUsers(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _customUserLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _customUserLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public CustomUserLocalService getWrappedService() {
		return _customUserLocalService;
	}

	@Override
	public void setWrappedService(CustomUserLocalService customUserLocalService) {
		_customUserLocalService = customUserLocalService;
	}

	private CustomUserLocalService _customUserLocalService;
}