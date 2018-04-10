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

package com.buckzy.common.service.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.buckzy.common.service.model.CustomUser;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the custom user service. This utility wraps {@link com.buckzy.common.service.service.persistence.impl.CustomUserPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sandip.Patel
 * @see CustomUserPersistence
 * @see com.buckzy.common.service.service.persistence.impl.CustomUserPersistenceImpl
 * @generated
 */
@ProviderType
public class CustomUserUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(CustomUser customUser) {
		getPersistence().clearCache(customUser);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CustomUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CustomUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CustomUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CustomUser> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CustomUser update(CustomUser customUser) {
		return getPersistence().update(customUser);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CustomUser update(CustomUser customUser,
		ServiceContext serviceContext) {
		return getPersistence().update(customUser, serviceContext);
	}

	/**
	* Returns the custom user where userId = &#63; or throws a {@link NoSuchCustomUserException} if it could not be found.
	*
	* @param userId the user ID
	* @return the matching custom user
	* @throws NoSuchCustomUserException if a matching custom user could not be found
	*/
	public static CustomUser findByUserId(long userId)
		throws com.buckzy.common.service.exception.NoSuchCustomUserException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns the custom user where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @return the matching custom user, or <code>null</code> if a matching custom user could not be found
	*/
	public static CustomUser fetchByUserId(long userId) {
		return getPersistence().fetchByUserId(userId);
	}

	/**
	* Returns the custom user where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching custom user, or <code>null</code> if a matching custom user could not be found
	*/
	public static CustomUser fetchByUserId(long userId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUserId(userId, retrieveFromCache);
	}

	/**
	* Removes the custom user where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @return the custom user that was removed
	*/
	public static CustomUser removeByUserId(long userId)
		throws com.buckzy.common.service.exception.NoSuchCustomUserException {
		return getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of custom users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching custom users
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the custom user where mobileNo = &#63; and mobCountryCode = &#63; or throws a {@link NoSuchCustomUserException} if it could not be found.
	*
	* @param mobileNo the mobile no
	* @param mobCountryCode the mob country code
	* @return the matching custom user
	* @throws NoSuchCustomUserException if a matching custom user could not be found
	*/
	public static CustomUser findByMobileNumber(java.lang.String mobileNo,
		java.lang.String mobCountryCode)
		throws com.buckzy.common.service.exception.NoSuchCustomUserException {
		return getPersistence().findByMobileNumber(mobileNo, mobCountryCode);
	}

	/**
	* Returns the custom user where mobileNo = &#63; and mobCountryCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param mobileNo the mobile no
	* @param mobCountryCode the mob country code
	* @return the matching custom user, or <code>null</code> if a matching custom user could not be found
	*/
	public static CustomUser fetchByMobileNumber(java.lang.String mobileNo,
		java.lang.String mobCountryCode) {
		return getPersistence().fetchByMobileNumber(mobileNo, mobCountryCode);
	}

	/**
	* Returns the custom user where mobileNo = &#63; and mobCountryCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param mobileNo the mobile no
	* @param mobCountryCode the mob country code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching custom user, or <code>null</code> if a matching custom user could not be found
	*/
	public static CustomUser fetchByMobileNumber(java.lang.String mobileNo,
		java.lang.String mobCountryCode, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByMobileNumber(mobileNo, mobCountryCode,
			retrieveFromCache);
	}

	/**
	* Removes the custom user where mobileNo = &#63; and mobCountryCode = &#63; from the database.
	*
	* @param mobileNo the mobile no
	* @param mobCountryCode the mob country code
	* @return the custom user that was removed
	*/
	public static CustomUser removeByMobileNumber(java.lang.String mobileNo,
		java.lang.String mobCountryCode)
		throws com.buckzy.common.service.exception.NoSuchCustomUserException {
		return getPersistence().removeByMobileNumber(mobileNo, mobCountryCode);
	}

	/**
	* Returns the number of custom users where mobileNo = &#63; and mobCountryCode = &#63;.
	*
	* @param mobileNo the mobile no
	* @param mobCountryCode the mob country code
	* @return the number of matching custom users
	*/
	public static int countByMobileNumber(java.lang.String mobileNo,
		java.lang.String mobCountryCode) {
		return getPersistence().countByMobileNumber(mobileNo, mobCountryCode);
	}

	/**
	* Caches the custom user in the entity cache if it is enabled.
	*
	* @param customUser the custom user
	*/
	public static void cacheResult(CustomUser customUser) {
		getPersistence().cacheResult(customUser);
	}

	/**
	* Caches the custom users in the entity cache if it is enabled.
	*
	* @param customUsers the custom users
	*/
	public static void cacheResult(List<CustomUser> customUsers) {
		getPersistence().cacheResult(customUsers);
	}

	/**
	* Creates a new custom user with the primary key. Does not add the custom user to the database.
	*
	* @param customUserId the primary key for the new custom user
	* @return the new custom user
	*/
	public static CustomUser create(long customUserId) {
		return getPersistence().create(customUserId);
	}

	/**
	* Removes the custom user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customUserId the primary key of the custom user
	* @return the custom user that was removed
	* @throws NoSuchCustomUserException if a custom user with the primary key could not be found
	*/
	public static CustomUser remove(long customUserId)
		throws com.buckzy.common.service.exception.NoSuchCustomUserException {
		return getPersistence().remove(customUserId);
	}

	public static CustomUser updateImpl(CustomUser customUser) {
		return getPersistence().updateImpl(customUser);
	}

	/**
	* Returns the custom user with the primary key or throws a {@link NoSuchCustomUserException} if it could not be found.
	*
	* @param customUserId the primary key of the custom user
	* @return the custom user
	* @throws NoSuchCustomUserException if a custom user with the primary key could not be found
	*/
	public static CustomUser findByPrimaryKey(long customUserId)
		throws com.buckzy.common.service.exception.NoSuchCustomUserException {
		return getPersistence().findByPrimaryKey(customUserId);
	}

	/**
	* Returns the custom user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param customUserId the primary key of the custom user
	* @return the custom user, or <code>null</code> if a custom user with the primary key could not be found
	*/
	public static CustomUser fetchByPrimaryKey(long customUserId) {
		return getPersistence().fetchByPrimaryKey(customUserId);
	}

	public static java.util.Map<java.io.Serializable, CustomUser> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the custom users.
	*
	* @return the custom users
	*/
	public static List<CustomUser> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the custom users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom users
	* @param end the upper bound of the range of custom users (not inclusive)
	* @return the range of custom users
	*/
	public static List<CustomUser> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the custom users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom users
	* @param end the upper bound of the range of custom users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of custom users
	*/
	public static List<CustomUser> findAll(int start, int end,
		OrderByComparator<CustomUser> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the custom users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom users
	* @param end the upper bound of the range of custom users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of custom users
	*/
	public static List<CustomUser> findAll(int start, int end,
		OrderByComparator<CustomUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the custom users from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of custom users.
	*
	* @return the number of custom users
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CustomUserPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CustomUserPersistence, CustomUserPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CustomUserPersistence.class);
}