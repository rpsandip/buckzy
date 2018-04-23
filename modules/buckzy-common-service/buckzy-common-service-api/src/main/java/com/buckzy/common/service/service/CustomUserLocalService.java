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

import com.buckzy.common.beans.UserBean;
import com.buckzy.common.service.exception.NoSuchCustomUserException;
import com.buckzy.common.service.model.CustomUser;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.File;
import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for CustomUser. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Sandip.Patel
 * @see CustomUserLocalServiceUtil
 * @see com.buckzy.common.service.service.base.CustomUserLocalServiceBaseImpl
 * @see com.buckzy.common.service.service.impl.CustomUserLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CustomUserLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CustomUserLocalServiceUtil} to access the custom user local service. Add custom service methods to {@link com.buckzy.common.service.service.impl.CustomUserLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserBean getPartyUserBean(java.lang.String token, long userId);

	/**
	* Adds the custom user to the database. Also notifies the appropriate model listeners.
	*
	* @param customUser the custom user
	* @return the custom user that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CustomUser addCustomUser(CustomUser customUser);

	/**
	* Creates a new custom user with the primary key. Does not add the custom user to the database.
	*
	* @param customUserId the primary key for the new custom user
	* @return the new custom user
	*/
	public CustomUser createCustomUser(long customUserId);

	/**
	* Deletes the custom user from the database. Also notifies the appropriate model listeners.
	*
	* @param customUser the custom user
	* @return the custom user that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public CustomUser deleteCustomUser(CustomUser customUser);

	/**
	* Deletes the custom user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customUserId the primary key of the custom user
	* @return the custom user that was removed
	* @throws PortalException if a custom user with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public CustomUser deleteCustomUser(long customUserId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CustomUser fetchCustomUser(long customUserId);

	/**
	* Returns the custom user with the primary key.
	*
	* @param customUserId the primary key of the custom user
	* @return the custom user
	* @throws PortalException if a custom user with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CustomUser getCustomUser(long customUserId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CustomUser getCustomUserByLRUserId(long userId)
		throws NoSuchCustomUserException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CustomUser getCustomUserByMobileNumber(java.lang.String mobileNo,
		java.lang.String countryCode) throws NoSuchCustomUserException;

	/**
	* Updates the custom user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param customUser the custom user
	* @return the custom user that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CustomUser updateCustomUser(CustomUser customUser);

	public CustomUser updateMobileNumber(java.lang.String emailAddress,
		java.lang.String mobileNo, java.lang.String mobCountryCode)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Add Custom user detail from registration
	*
	* @throws PortalException
	*/
	public JSONObject addBuckzyCustomUser(java.lang.String token, User user,
		java.lang.String emailAddress, java.lang.String password,
		java.lang.String address, java.lang.String zipcode,
		java.lang.String city, java.lang.String state,
		java.lang.String countryCode, java.lang.String currencyCode,
		java.lang.String mobileNo, java.lang.String mobileCountryCode,
		java.lang.String deviceInfo, boolean socialLogin, long creatorUserId)
		throws PortalException;

	public JSONObject createParty(java.lang.String token, long userId,
		java.lang.String fName, java.lang.String lastName,
		java.lang.String email, java.lang.String password,
		java.lang.String mobileContryCode, java.lang.String mobileNo,
		java.lang.String address, java.lang.String city,
		java.lang.String zipcode, java.lang.String countryCode,
		java.lang.String currencyCode) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getBranchDetail(int bankId, int branchId,
		java.lang.String token) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getPartyDetail(long partyId, java.lang.String token)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getPartyPreferAccount(long partyId, java.lang.String token)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getReceiverAccountDetail(java.lang.String token,
		long senderPartyId, long receiverPartyId) throws PortalException;

	public JSONObject updateAccountDetail(java.lang.String token, long userId,
		java.lang.String accountType, java.lang.String cardNumber,
		java.lang.String cardFirstName, java.lang.String cardLastName,
		java.lang.String expireOnMonth, java.lang.String expireOnYear,
		java.lang.String accountNumber, java.lang.String acctInstnNm,
		int bankId, int branchId, java.lang.String routingNumber,
		java.lang.String searchBranchType)
		throws PortalException, JSONException;

	public JSONObject updateParty(java.lang.String token, long partyId,
		long userId, java.lang.String fName, java.lang.String lastName,
		java.lang.String email, java.lang.String mobileCountryCode,
		java.lang.String countryCode, java.lang.String currencyCode,
		java.lang.String mobileNo, java.lang.String address,
		java.lang.String city, java.lang.String state,
		java.lang.String zipcode, java.lang.String documentType, File file,
		java.lang.String verificationDocName, java.lang.String accountType,
		java.lang.String cardNumber, java.lang.String cardFirstName,
		java.lang.String cardLastName, java.lang.String expireOnMonth,
		java.lang.String expireOnYear, java.lang.String accountNumber,
		java.lang.String acctInstnNm, int bankId, int branchId,
		java.lang.String routingNumber, java.lang.String searchBranchType)
		throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of custom users.
	*
	* @return the number of custom users
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCustomUsersCount();

	public java.lang.String addDeviceInfo(CustomUser customUser,
		java.lang.String newDeviceInfo);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CustomUser> getCustomUsers(int start, int end);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);
}