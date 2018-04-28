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
 * Provides the local service utility for CustomUser. This utility wraps
 * {@link com.buckzy.common.service.service.impl.CustomUserLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Sandip.Patel
 * @see CustomUserLocalService
 * @see com.buckzy.common.service.service.base.CustomUserLocalServiceBaseImpl
 * @see com.buckzy.common.service.service.impl.CustomUserLocalServiceImpl
 * @generated
 */
@ProviderType
public class CustomUserLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.buckzy.common.service.service.impl.CustomUserLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.buckzy.common.beans.UserBean getPartyUserBean(
		java.lang.String token, long userId) {
		return getService().getPartyUserBean(token, userId);
	}

	/**
	* Adds the custom user to the database. Also notifies the appropriate model listeners.
	*
	* @param customUser the custom user
	* @return the custom user that was added
	*/
	public static com.buckzy.common.service.model.CustomUser addCustomUser(
		com.buckzy.common.service.model.CustomUser customUser) {
		return getService().addCustomUser(customUser);
	}

	/**
	* Creates a new custom user with the primary key. Does not add the custom user to the database.
	*
	* @param customUserId the primary key for the new custom user
	* @return the new custom user
	*/
	public static com.buckzy.common.service.model.CustomUser createCustomUser(
		long customUserId) {
		return getService().createCustomUser(customUserId);
	}

	/**
	* Deletes the custom user from the database. Also notifies the appropriate model listeners.
	*
	* @param customUser the custom user
	* @return the custom user that was removed
	*/
	public static com.buckzy.common.service.model.CustomUser deleteCustomUser(
		com.buckzy.common.service.model.CustomUser customUser) {
		return getService().deleteCustomUser(customUser);
	}

	/**
	* Deletes the custom user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customUserId the primary key of the custom user
	* @return the custom user that was removed
	* @throws PortalException if a custom user with the primary key could not be found
	*/
	public static com.buckzy.common.service.model.CustomUser deleteCustomUser(
		long customUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCustomUser(customUserId);
	}

	public static com.buckzy.common.service.model.CustomUser fetchCustomUser(
		long customUserId) {
		return getService().fetchCustomUser(customUserId);
	}

	/**
	* Returns the custom user with the primary key.
	*
	* @param customUserId the primary key of the custom user
	* @return the custom user
	* @throws PortalException if a custom user with the primary key could not be found
	*/
	public static com.buckzy.common.service.model.CustomUser getCustomUser(
		long customUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCustomUser(customUserId);
	}

	public static com.buckzy.common.service.model.CustomUser getCustomUserByLRUserId(
		long userId)
		throws com.buckzy.common.service.exception.NoSuchCustomUserException {
		return getService().getCustomUserByLRUserId(userId);
	}

	public static com.buckzy.common.service.model.CustomUser getCustomUserByMobileNumber(
		java.lang.String mobileNo, java.lang.String countryCode)
		throws com.buckzy.common.service.exception.NoSuchCustomUserException {
		return getService().getCustomUserByMobileNumber(mobileNo, countryCode);
	}

	/**
	* Updates the custom user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param customUser the custom user
	* @return the custom user that was updated
	*/
	public static com.buckzy.common.service.model.CustomUser updateCustomUser(
		com.buckzy.common.service.model.CustomUser customUser) {
		return getService().updateCustomUser(customUser);
	}

	public static com.buckzy.common.service.model.CustomUser updateMobileNumber(
		java.lang.String emailAddress, java.lang.String mobileNo,
		java.lang.String mobCountryCode)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateMobileNumber(emailAddress, mobileNo, mobCountryCode);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Add Custom user detail from registration
	*
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.json.JSONObject addBuckzyCustomUser(
		java.lang.String token, com.liferay.portal.kernel.model.User user,
		java.lang.String emailAddress, java.lang.String password,
		java.lang.String address, java.lang.String zipcode,
		java.lang.String city, java.lang.String state,
		java.lang.String countryCode, java.lang.String currencyCode,
		java.lang.String mobileNo, java.lang.String mobileCountryCode,
		java.lang.String deviceInfo, boolean socialLogin, long creatorUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addBuckzyCustomUser(token, user, emailAddress, password,
			address, zipcode, city, state, countryCode, currencyCode, mobileNo,
			mobileCountryCode, deviceInfo, socialLogin, creatorUserId);
	}

	public static com.liferay.portal.kernel.json.JSONObject createParty(
		java.lang.String token, long userId, java.lang.String fName,
		java.lang.String lastName, java.lang.String email,
		java.lang.String password, java.lang.String mobileContryCode,
		java.lang.String mobileNo, java.lang.String address,
		java.lang.String city, java.lang.String zipcode,
		java.lang.String stateCode, java.lang.String countryCode,
		java.lang.String currencyCode)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .createParty(token, userId, fName, lastName, email,
			password, mobileContryCode, mobileNo, address, city, zipcode,
			stateCode, countryCode, currencyCode);
	}

	public static com.liferay.portal.kernel.json.JSONObject getBranchDetail(
		int bankId, int branchId, java.lang.String token)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getBranchDetail(bankId, branchId, token);
	}

	public static com.liferay.portal.kernel.json.JSONObject getPartyDetail(
		long partyId, java.lang.String token)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPartyDetail(partyId, token);
	}

	public static com.liferay.portal.kernel.json.JSONObject getPartyPreferAccount(
		long partyId, java.lang.String token)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPartyPreferAccount(partyId, token);
	}

	public static com.liferay.portal.kernel.json.JSONObject getReceiverAccountDetail(
		java.lang.String token, long senderPartyId, long receiverPartyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getReceiverAccountDetail(token, senderPartyId,
			receiverPartyId);
	}

	public static com.liferay.portal.kernel.json.JSONObject updateAccountDetail(
		java.lang.String token, long userId, java.lang.String accountType,
		java.lang.String cardNumber, java.lang.String cardFirstName,
		java.lang.String cardLastName, java.lang.String expireOnMonth,
		java.lang.String expireOnYear, java.lang.String accountNumber,
		java.lang.String acctInstnNm, int bankId, int branchId,
		java.lang.String routingNumber, java.lang.String searchBranchType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.json.JSONException {
		return getService()
				   .updateAccountDetail(token, userId, accountType, cardNumber,
			cardFirstName, cardLastName, expireOnMonth, expireOnYear,
			accountNumber, acctInstnNm, bankId, branchId, routingNumber,
			searchBranchType);
	}

	public static com.liferay.portal.kernel.json.JSONObject updateParty(
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
		return getService()
				   .updateParty(token, partyId, userId, fName, lastName, email,
			mobileCountryCode, countryCode, currencyCode, mobileNo, address,
			city, stateCode, zipcode, documentType, file, verificationDocName,
			accountType, cardNumber, cardFirstName, cardLastName,
			expireOnMonth, expireOnYear, accountNumber, acctInstnNm, bankId,
			branchId, routingNumber, searchBranchType);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of custom users.
	*
	* @return the number of custom users
	*/
	public static int getCustomUsersCount() {
		return getService().getCustomUsersCount();
	}

	public static java.lang.String addDeviceInfo(
		com.buckzy.common.service.model.CustomUser customUser,
		java.lang.String newDeviceInfo) {
		return getService().addDeviceInfo(customUser, newDeviceInfo);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
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
	public static java.util.List<com.buckzy.common.service.model.CustomUser> getCustomUsers(
		int start, int end) {
		return getService().getCustomUsers(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static CustomUserLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CustomUserLocalService, CustomUserLocalService> _serviceTracker =
		ServiceTrackerFactory.open(CustomUserLocalService.class);
}