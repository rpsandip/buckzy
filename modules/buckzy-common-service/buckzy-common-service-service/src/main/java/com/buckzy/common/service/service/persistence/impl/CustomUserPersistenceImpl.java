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

package com.buckzy.common.service.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.buckzy.common.service.exception.NoSuchCustomUserException;
import com.buckzy.common.service.model.CustomUser;
import com.buckzy.common.service.model.impl.CustomUserImpl;
import com.buckzy.common.service.model.impl.CustomUserModelImpl;
import com.buckzy.common.service.service.persistence.CustomUserPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the custom user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Sandip.Patel
 * @see CustomUserPersistence
 * @see com.buckzy.common.service.service.persistence.CustomUserUtil
 * @generated
 */
@ProviderType
public class CustomUserPersistenceImpl extends BasePersistenceImpl<CustomUser>
	implements CustomUserPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CustomUserUtil} to access the custom user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CustomUserImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CustomUserModelImpl.ENTITY_CACHE_ENABLED,
			CustomUserModelImpl.FINDER_CACHE_ENABLED, CustomUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CustomUserModelImpl.ENTITY_CACHE_ENABLED,
			CustomUserModelImpl.FINDER_CACHE_ENABLED, CustomUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CustomUserModelImpl.ENTITY_CACHE_ENABLED,
			CustomUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_USERID = new FinderPath(CustomUserModelImpl.ENTITY_CACHE_ENABLED,
			CustomUserModelImpl.FINDER_CACHE_ENABLED, CustomUserImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUserId",
			new String[] { Long.class.getName() },
			CustomUserModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(CustomUserModelImpl.ENTITY_CACHE_ENABLED,
			CustomUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns the custom user where userId = &#63; or throws a {@link NoSuchCustomUserException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching custom user
	 * @throws NoSuchCustomUserException if a matching custom user could not be found
	 */
	@Override
	public CustomUser findByUserId(long userId)
		throws NoSuchCustomUserException {
		CustomUser customUser = fetchByUserId(userId);

		if (customUser == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCustomUserException(msg.toString());
		}

		return customUser;
	}

	/**
	 * Returns the custom user where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching custom user, or <code>null</code> if a matching custom user could not be found
	 */
	@Override
	public CustomUser fetchByUserId(long userId) {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the custom user where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching custom user, or <code>null</code> if a matching custom user could not be found
	 */
	@Override
	public CustomUser fetchByUserId(long userId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { userId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_USERID,
					finderArgs, this);
		}

		if (result instanceof CustomUser) {
			CustomUser customUser = (CustomUser)result;

			if ((userId != customUser.getUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_CUSTOMUSER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				List<CustomUser> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_USERID,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CustomUserPersistenceImpl.fetchByUserId(long, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CustomUser customUser = list.get(0);

					result = customUser;

					cacheResult(customUser);

					if ((customUser.getUserId() != userId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_USERID,
							finderArgs, customUser);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_USERID, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CustomUser)result;
		}
	}

	/**
	 * Removes the custom user where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the custom user that was removed
	 */
	@Override
	public CustomUser removeByUserId(long userId)
		throws NoSuchCustomUserException {
		CustomUser customUser = findByUserId(userId);

		return remove(customUser);
	}

	/**
	 * Returns the number of custom users where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching custom users
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CUSTOMUSER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "customUser.userId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_MOBILENUMBER = new FinderPath(CustomUserModelImpl.ENTITY_CACHE_ENABLED,
			CustomUserModelImpl.FINDER_CACHE_ENABLED, CustomUserImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByMobileNumber",
			new String[] { String.class.getName(), String.class.getName() },
			CustomUserModelImpl.MOBILENO_COLUMN_BITMASK |
			CustomUserModelImpl.MOBCOUNTRYCODE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MOBILENUMBER = new FinderPath(CustomUserModelImpl.ENTITY_CACHE_ENABLED,
			CustomUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMobileNumber",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns the custom user where mobileNo = &#63; and mobCountryCode = &#63; or throws a {@link NoSuchCustomUserException} if it could not be found.
	 *
	 * @param mobileNo the mobile no
	 * @param mobCountryCode the mob country code
	 * @return the matching custom user
	 * @throws NoSuchCustomUserException if a matching custom user could not be found
	 */
	@Override
	public CustomUser findByMobileNumber(String mobileNo, String mobCountryCode)
		throws NoSuchCustomUserException {
		CustomUser customUser = fetchByMobileNumber(mobileNo, mobCountryCode);

		if (customUser == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("mobileNo=");
			msg.append(mobileNo);

			msg.append(", mobCountryCode=");
			msg.append(mobCountryCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCustomUserException(msg.toString());
		}

		return customUser;
	}

	/**
	 * Returns the custom user where mobileNo = &#63; and mobCountryCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param mobileNo the mobile no
	 * @param mobCountryCode the mob country code
	 * @return the matching custom user, or <code>null</code> if a matching custom user could not be found
	 */
	@Override
	public CustomUser fetchByMobileNumber(String mobileNo, String mobCountryCode) {
		return fetchByMobileNumber(mobileNo, mobCountryCode, true);
	}

	/**
	 * Returns the custom user where mobileNo = &#63; and mobCountryCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param mobileNo the mobile no
	 * @param mobCountryCode the mob country code
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching custom user, or <code>null</code> if a matching custom user could not be found
	 */
	@Override
	public CustomUser fetchByMobileNumber(String mobileNo,
		String mobCountryCode, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { mobileNo, mobCountryCode };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_MOBILENUMBER,
					finderArgs, this);
		}

		if (result instanceof CustomUser) {
			CustomUser customUser = (CustomUser)result;

			if (!Objects.equals(mobileNo, customUser.getMobileNo()) ||
					!Objects.equals(mobCountryCode,
						customUser.getMobCountryCode())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CUSTOMUSER_WHERE);

			boolean bindMobileNo = false;

			if (mobileNo == null) {
				query.append(_FINDER_COLUMN_MOBILENUMBER_MOBILENO_1);
			}
			else if (mobileNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MOBILENUMBER_MOBILENO_3);
			}
			else {
				bindMobileNo = true;

				query.append(_FINDER_COLUMN_MOBILENUMBER_MOBILENO_2);
			}

			boolean bindMobCountryCode = false;

			if (mobCountryCode == null) {
				query.append(_FINDER_COLUMN_MOBILENUMBER_MOBCOUNTRYCODE_1);
			}
			else if (mobCountryCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MOBILENUMBER_MOBCOUNTRYCODE_3);
			}
			else {
				bindMobCountryCode = true;

				query.append(_FINDER_COLUMN_MOBILENUMBER_MOBCOUNTRYCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMobileNo) {
					qPos.add(mobileNo);
				}

				if (bindMobCountryCode) {
					qPos.add(mobCountryCode);
				}

				List<CustomUser> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_MOBILENUMBER,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"CustomUserPersistenceImpl.fetchByMobileNumber(String, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CustomUser customUser = list.get(0);

					result = customUser;

					cacheResult(customUser);

					if ((customUser.getMobileNo() == null) ||
							!customUser.getMobileNo().equals(mobileNo) ||
							(customUser.getMobCountryCode() == null) ||
							!customUser.getMobCountryCode()
										   .equals(mobCountryCode)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_MOBILENUMBER,
							finderArgs, customUser);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_MOBILENUMBER,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CustomUser)result;
		}
	}

	/**
	 * Removes the custom user where mobileNo = &#63; and mobCountryCode = &#63; from the database.
	 *
	 * @param mobileNo the mobile no
	 * @param mobCountryCode the mob country code
	 * @return the custom user that was removed
	 */
	@Override
	public CustomUser removeByMobileNumber(String mobileNo,
		String mobCountryCode) throws NoSuchCustomUserException {
		CustomUser customUser = findByMobileNumber(mobileNo, mobCountryCode);

		return remove(customUser);
	}

	/**
	 * Returns the number of custom users where mobileNo = &#63; and mobCountryCode = &#63;.
	 *
	 * @param mobileNo the mobile no
	 * @param mobCountryCode the mob country code
	 * @return the number of matching custom users
	 */
	@Override
	public int countByMobileNumber(String mobileNo, String mobCountryCode) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MOBILENUMBER;

		Object[] finderArgs = new Object[] { mobileNo, mobCountryCode };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CUSTOMUSER_WHERE);

			boolean bindMobileNo = false;

			if (mobileNo == null) {
				query.append(_FINDER_COLUMN_MOBILENUMBER_MOBILENO_1);
			}
			else if (mobileNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MOBILENUMBER_MOBILENO_3);
			}
			else {
				bindMobileNo = true;

				query.append(_FINDER_COLUMN_MOBILENUMBER_MOBILENO_2);
			}

			boolean bindMobCountryCode = false;

			if (mobCountryCode == null) {
				query.append(_FINDER_COLUMN_MOBILENUMBER_MOBCOUNTRYCODE_1);
			}
			else if (mobCountryCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MOBILENUMBER_MOBCOUNTRYCODE_3);
			}
			else {
				bindMobCountryCode = true;

				query.append(_FINDER_COLUMN_MOBILENUMBER_MOBCOUNTRYCODE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindMobileNo) {
					qPos.add(mobileNo);
				}

				if (bindMobCountryCode) {
					qPos.add(mobCountryCode);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_MOBILENUMBER_MOBILENO_1 = "customUser.mobileNo IS NULL AND ";
	private static final String _FINDER_COLUMN_MOBILENUMBER_MOBILENO_2 = "customUser.mobileNo = ? AND ";
	private static final String _FINDER_COLUMN_MOBILENUMBER_MOBILENO_3 = "(customUser.mobileNo IS NULL OR customUser.mobileNo = '') AND ";
	private static final String _FINDER_COLUMN_MOBILENUMBER_MOBCOUNTRYCODE_1 = "customUser.mobCountryCode IS NULL";
	private static final String _FINDER_COLUMN_MOBILENUMBER_MOBCOUNTRYCODE_2 = "customUser.mobCountryCode = ?";
	private static final String _FINDER_COLUMN_MOBILENUMBER_MOBCOUNTRYCODE_3 = "(customUser.mobCountryCode IS NULL OR customUser.mobCountryCode = '')";

	public CustomUserPersistenceImpl() {
		setModelClass(CustomUser.class);
	}

	/**
	 * Caches the custom user in the entity cache if it is enabled.
	 *
	 * @param customUser the custom user
	 */
	@Override
	public void cacheResult(CustomUser customUser) {
		entityCache.putResult(CustomUserModelImpl.ENTITY_CACHE_ENABLED,
			CustomUserImpl.class, customUser.getPrimaryKey(), customUser);

		finderCache.putResult(FINDER_PATH_FETCH_BY_USERID,
			new Object[] { customUser.getUserId() }, customUser);

		finderCache.putResult(FINDER_PATH_FETCH_BY_MOBILENUMBER,
			new Object[] {
				customUser.getMobileNo(), customUser.getMobCountryCode()
			}, customUser);

		customUser.resetOriginalValues();
	}

	/**
	 * Caches the custom users in the entity cache if it is enabled.
	 *
	 * @param customUsers the custom users
	 */
	@Override
	public void cacheResult(List<CustomUser> customUsers) {
		for (CustomUser customUser : customUsers) {
			if (entityCache.getResult(
						CustomUserModelImpl.ENTITY_CACHE_ENABLED,
						CustomUserImpl.class, customUser.getPrimaryKey()) == null) {
				cacheResult(customUser);
			}
			else {
				customUser.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all custom users.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CustomUserImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the custom user.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CustomUser customUser) {
		entityCache.removeResult(CustomUserModelImpl.ENTITY_CACHE_ENABLED,
			CustomUserImpl.class, customUser.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((CustomUserModelImpl)customUser, true);
	}

	@Override
	public void clearCache(List<CustomUser> customUsers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CustomUser customUser : customUsers) {
			entityCache.removeResult(CustomUserModelImpl.ENTITY_CACHE_ENABLED,
				CustomUserImpl.class, customUser.getPrimaryKey());

			clearUniqueFindersCache((CustomUserModelImpl)customUser, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CustomUserModelImpl customUserModelImpl) {
		Object[] args = new Object[] { customUserModelImpl.getUserId() };

		finderCache.putResult(FINDER_PATH_COUNT_BY_USERID, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_USERID, args,
			customUserModelImpl, false);

		args = new Object[] {
				customUserModelImpl.getMobileNo(),
				customUserModelImpl.getMobCountryCode()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_MOBILENUMBER, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_MOBILENUMBER, args,
			customUserModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CustomUserModelImpl customUserModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] { customUserModelImpl.getUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_USERID, args);
		}

		if ((customUserModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_USERID.getColumnBitmask()) != 0) {
			Object[] args = new Object[] { customUserModelImpl.getOriginalUserId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_USERID, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
					customUserModelImpl.getMobileNo(),
					customUserModelImpl.getMobCountryCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MOBILENUMBER, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_MOBILENUMBER, args);
		}

		if ((customUserModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_MOBILENUMBER.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					customUserModelImpl.getOriginalMobileNo(),
					customUserModelImpl.getOriginalMobCountryCode()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MOBILENUMBER, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_MOBILENUMBER, args);
		}
	}

	/**
	 * Creates a new custom user with the primary key. Does not add the custom user to the database.
	 *
	 * @param customUserId the primary key for the new custom user
	 * @return the new custom user
	 */
	@Override
	public CustomUser create(long customUserId) {
		CustomUser customUser = new CustomUserImpl();

		customUser.setNew(true);
		customUser.setPrimaryKey(customUserId);

		return customUser;
	}

	/**
	 * Removes the custom user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param customUserId the primary key of the custom user
	 * @return the custom user that was removed
	 * @throws NoSuchCustomUserException if a custom user with the primary key could not be found
	 */
	@Override
	public CustomUser remove(long customUserId)
		throws NoSuchCustomUserException {
		return remove((Serializable)customUserId);
	}

	/**
	 * Removes the custom user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the custom user
	 * @return the custom user that was removed
	 * @throws NoSuchCustomUserException if a custom user with the primary key could not be found
	 */
	@Override
	public CustomUser remove(Serializable primaryKey)
		throws NoSuchCustomUserException {
		Session session = null;

		try {
			session = openSession();

			CustomUser customUser = (CustomUser)session.get(CustomUserImpl.class,
					primaryKey);

			if (customUser == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCustomUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(customUser);
		}
		catch (NoSuchCustomUserException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CustomUser removeImpl(CustomUser customUser) {
		customUser = toUnwrappedModel(customUser);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(customUser)) {
				customUser = (CustomUser)session.get(CustomUserImpl.class,
						customUser.getPrimaryKeyObj());
			}

			if (customUser != null) {
				session.delete(customUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (customUser != null) {
			clearCache(customUser);
		}

		return customUser;
	}

	@Override
	public CustomUser updateImpl(CustomUser customUser) {
		customUser = toUnwrappedModel(customUser);

		boolean isNew = customUser.isNew();

		CustomUserModelImpl customUserModelImpl = (CustomUserModelImpl)customUser;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (customUser.getCreateDate() == null)) {
			if (serviceContext == null) {
				customUser.setCreateDate(now);
			}
			else {
				customUser.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!customUserModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				customUser.setModifiedDate(now);
			}
			else {
				customUser.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (customUser.isNew()) {
				session.save(customUser);

				customUser.setNew(false);
			}
			else {
				customUser = (CustomUser)session.merge(customUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!CustomUserModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(CustomUserModelImpl.ENTITY_CACHE_ENABLED,
			CustomUserImpl.class, customUser.getPrimaryKey(), customUser, false);

		clearUniqueFindersCache(customUserModelImpl, false);
		cacheUniqueFindersCache(customUserModelImpl);

		customUser.resetOriginalValues();

		return customUser;
	}

	protected CustomUser toUnwrappedModel(CustomUser customUser) {
		if (customUser instanceof CustomUserImpl) {
			return customUser;
		}

		CustomUserImpl customUserImpl = new CustomUserImpl();

		customUserImpl.setNew(customUser.isNew());
		customUserImpl.setPrimaryKey(customUser.getPrimaryKey());

		customUserImpl.setCustomUserId(customUser.getCustomUserId());
		customUserImpl.setUserId(customUser.getUserId());
		customUserImpl.setPartyId(customUser.getPartyId());
		customUserImpl.setPartyUserId(customUser.getPartyUserId());
		customUserImpl.setMobileNo(customUser.getMobileNo());
		customUserImpl.setMobCountryCode(customUser.getMobCountryCode());
		customUserImpl.setDeviceInfo(customUser.getDeviceInfo());
		customUserImpl.setDocumentVerified(customUser.isDocumentVerified());
		customUserImpl.setAccountCompleted(customUser.isAccountCompleted());
		customUserImpl.setDocumentRemindLater(customUser.isDocumentRemindLater());
		customUserImpl.setAccountRemindLater(customUser.isAccountRemindLater());
		customUserImpl.setProfileComplete(customUser.isProfileComplete());
		customUserImpl.setSocialLogin(customUser.isSocialLogin());
		customUserImpl.setRestPass(customUser.getRestPass());
		customUserImpl.setCreateDate(customUser.getCreateDate());
		customUserImpl.setCreatedBy(customUser.getCreatedBy());
		customUserImpl.setModifiedDate(customUser.getModifiedDate());
		customUserImpl.setModifiedBy(customUser.getModifiedBy());

		return customUserImpl;
	}

	/**
	 * Returns the custom user with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the custom user
	 * @return the custom user
	 * @throws NoSuchCustomUserException if a custom user with the primary key could not be found
	 */
	@Override
	public CustomUser findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCustomUserException {
		CustomUser customUser = fetchByPrimaryKey(primaryKey);

		if (customUser == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCustomUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return customUser;
	}

	/**
	 * Returns the custom user with the primary key or throws a {@link NoSuchCustomUserException} if it could not be found.
	 *
	 * @param customUserId the primary key of the custom user
	 * @return the custom user
	 * @throws NoSuchCustomUserException if a custom user with the primary key could not be found
	 */
	@Override
	public CustomUser findByPrimaryKey(long customUserId)
		throws NoSuchCustomUserException {
		return findByPrimaryKey((Serializable)customUserId);
	}

	/**
	 * Returns the custom user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the custom user
	 * @return the custom user, or <code>null</code> if a custom user with the primary key could not be found
	 */
	@Override
	public CustomUser fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CustomUserModelImpl.ENTITY_CACHE_ENABLED,
				CustomUserImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CustomUser customUser = (CustomUser)serializable;

		if (customUser == null) {
			Session session = null;

			try {
				session = openSession();

				customUser = (CustomUser)session.get(CustomUserImpl.class,
						primaryKey);

				if (customUser != null) {
					cacheResult(customUser);
				}
				else {
					entityCache.putResult(CustomUserModelImpl.ENTITY_CACHE_ENABLED,
						CustomUserImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CustomUserModelImpl.ENTITY_CACHE_ENABLED,
					CustomUserImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return customUser;
	}

	/**
	 * Returns the custom user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param customUserId the primary key of the custom user
	 * @return the custom user, or <code>null</code> if a custom user with the primary key could not be found
	 */
	@Override
	public CustomUser fetchByPrimaryKey(long customUserId) {
		return fetchByPrimaryKey((Serializable)customUserId);
	}

	@Override
	public Map<Serializable, CustomUser> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CustomUser> map = new HashMap<Serializable, CustomUser>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CustomUser customUser = fetchByPrimaryKey(primaryKey);

			if (customUser != null) {
				map.put(primaryKey, customUser);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CustomUserModelImpl.ENTITY_CACHE_ENABLED,
					CustomUserImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CustomUser)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CUSTOMUSER_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (CustomUser customUser : (List<CustomUser>)q.list()) {
				map.put(customUser.getPrimaryKeyObj(), customUser);

				cacheResult(customUser);

				uncachedPrimaryKeys.remove(customUser.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CustomUserModelImpl.ENTITY_CACHE_ENABLED,
					CustomUserImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the custom users.
	 *
	 * @return the custom users
	 */
	@Override
	public List<CustomUser> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<CustomUser> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<CustomUser> findAll(int start, int end,
		OrderByComparator<CustomUser> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<CustomUser> findAll(int start, int end,
		OrderByComparator<CustomUser> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<CustomUser> list = null;

		if (retrieveFromCache) {
			list = (List<CustomUser>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CUSTOMUSER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CUSTOMUSER;

				if (pagination) {
					sql = sql.concat(CustomUserModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CustomUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CustomUser>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the custom users from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CustomUser customUser : findAll()) {
			remove(customUser);
		}
	}

	/**
	 * Returns the number of custom users.
	 *
	 * @return the number of custom users
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CUSTOMUSER);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CustomUserModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the custom user persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CustomUserImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CUSTOMUSER = "SELECT customUser FROM CustomUser customUser";
	private static final String _SQL_SELECT_CUSTOMUSER_WHERE_PKS_IN = "SELECT customUser FROM CustomUser customUser WHERE customUserId IN (";
	private static final String _SQL_SELECT_CUSTOMUSER_WHERE = "SELECT customUser FROM CustomUser customUser WHERE ";
	private static final String _SQL_COUNT_CUSTOMUSER = "SELECT COUNT(customUser) FROM CustomUser customUser";
	private static final String _SQL_COUNT_CUSTOMUSER_WHERE = "SELECT COUNT(customUser) FROM CustomUser customUser WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "customUser.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CustomUser exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No CustomUser exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(CustomUserPersistenceImpl.class);
}