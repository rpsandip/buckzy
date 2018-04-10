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

import com.buckzy.common.service.exception.NoSuchCustomUserException;
import com.buckzy.common.service.model.CustomUser;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the custom user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sandip.Patel
 * @see com.buckzy.common.service.service.persistence.impl.CustomUserPersistenceImpl
 * @see CustomUserUtil
 * @generated
 */
@ProviderType
public interface CustomUserPersistence extends BasePersistence<CustomUser> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CustomUserUtil} to access the custom user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the custom user where userId = &#63; or throws a {@link NoSuchCustomUserException} if it could not be found.
	*
	* @param userId the user ID
	* @return the matching custom user
	* @throws NoSuchCustomUserException if a matching custom user could not be found
	*/
	public CustomUser findByUserId(long userId)
		throws NoSuchCustomUserException;

	/**
	* Returns the custom user where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @return the matching custom user, or <code>null</code> if a matching custom user could not be found
	*/
	public CustomUser fetchByUserId(long userId);

	/**
	* Returns the custom user where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching custom user, or <code>null</code> if a matching custom user could not be found
	*/
	public CustomUser fetchByUserId(long userId, boolean retrieveFromCache);

	/**
	* Removes the custom user where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @return the custom user that was removed
	*/
	public CustomUser removeByUserId(long userId)
		throws NoSuchCustomUserException;

	/**
	* Returns the number of custom users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching custom users
	*/
	public int countByUserId(long userId);

	/**
	* Returns the custom user where mobileNo = &#63; and mobCountryCode = &#63; or throws a {@link NoSuchCustomUserException} if it could not be found.
	*
	* @param mobileNo the mobile no
	* @param mobCountryCode the mob country code
	* @return the matching custom user
	* @throws NoSuchCustomUserException if a matching custom user could not be found
	*/
	public CustomUser findByMobileNumber(java.lang.String mobileNo,
		java.lang.String mobCountryCode) throws NoSuchCustomUserException;

	/**
	* Returns the custom user where mobileNo = &#63; and mobCountryCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param mobileNo the mobile no
	* @param mobCountryCode the mob country code
	* @return the matching custom user, or <code>null</code> if a matching custom user could not be found
	*/
	public CustomUser fetchByMobileNumber(java.lang.String mobileNo,
		java.lang.String mobCountryCode);

	/**
	* Returns the custom user where mobileNo = &#63; and mobCountryCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param mobileNo the mobile no
	* @param mobCountryCode the mob country code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching custom user, or <code>null</code> if a matching custom user could not be found
	*/
	public CustomUser fetchByMobileNumber(java.lang.String mobileNo,
		java.lang.String mobCountryCode, boolean retrieveFromCache);

	/**
	* Removes the custom user where mobileNo = &#63; and mobCountryCode = &#63; from the database.
	*
	* @param mobileNo the mobile no
	* @param mobCountryCode the mob country code
	* @return the custom user that was removed
	*/
	public CustomUser removeByMobileNumber(java.lang.String mobileNo,
		java.lang.String mobCountryCode) throws NoSuchCustomUserException;

	/**
	* Returns the number of custom users where mobileNo = &#63; and mobCountryCode = &#63;.
	*
	* @param mobileNo the mobile no
	* @param mobCountryCode the mob country code
	* @return the number of matching custom users
	*/
	public int countByMobileNumber(java.lang.String mobileNo,
		java.lang.String mobCountryCode);

	/**
	* Caches the custom user in the entity cache if it is enabled.
	*
	* @param customUser the custom user
	*/
	public void cacheResult(CustomUser customUser);

	/**
	* Caches the custom users in the entity cache if it is enabled.
	*
	* @param customUsers the custom users
	*/
	public void cacheResult(java.util.List<CustomUser> customUsers);

	/**
	* Creates a new custom user with the primary key. Does not add the custom user to the database.
	*
	* @param customUserId the primary key for the new custom user
	* @return the new custom user
	*/
	public CustomUser create(long customUserId);

	/**
	* Removes the custom user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customUserId the primary key of the custom user
	* @return the custom user that was removed
	* @throws NoSuchCustomUserException if a custom user with the primary key could not be found
	*/
	public CustomUser remove(long customUserId)
		throws NoSuchCustomUserException;

	public CustomUser updateImpl(CustomUser customUser);

	/**
	* Returns the custom user with the primary key or throws a {@link NoSuchCustomUserException} if it could not be found.
	*
	* @param customUserId the primary key of the custom user
	* @return the custom user
	* @throws NoSuchCustomUserException if a custom user with the primary key could not be found
	*/
	public CustomUser findByPrimaryKey(long customUserId)
		throws NoSuchCustomUserException;

	/**
	* Returns the custom user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param customUserId the primary key of the custom user
	* @return the custom user, or <code>null</code> if a custom user with the primary key could not be found
	*/
	public CustomUser fetchByPrimaryKey(long customUserId);

	@Override
	public java.util.Map<java.io.Serializable, CustomUser> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the custom users.
	*
	* @return the custom users
	*/
	public java.util.List<CustomUser> findAll();

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
	public java.util.List<CustomUser> findAll(int start, int end);

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
	public java.util.List<CustomUser> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomUser> orderByComparator);

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
	public java.util.List<CustomUser> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the custom users from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of custom users.
	*
	* @return the number of custom users
	*/
	public int countAll();
}