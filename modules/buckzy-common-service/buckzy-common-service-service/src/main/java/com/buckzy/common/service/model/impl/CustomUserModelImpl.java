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

package com.buckzy.common.service.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.buckzy.common.service.model.CustomUser;
import com.buckzy.common.service.model.CustomUserModel;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the CustomUser service. Represents a row in the &quot;BUCKZY_CustomUser&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link CustomUserModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CustomUserImpl}.
 * </p>
 *
 * @author Sandip.Patel
 * @see CustomUserImpl
 * @see CustomUser
 * @see CustomUserModel
 * @generated
 */
@ProviderType
public class CustomUserModelImpl extends BaseModelImpl<CustomUser>
	implements CustomUserModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a custom user model instance should use the {@link CustomUser} interface instead.
	 */
	public static final String TABLE_NAME = "BUCKZY_CustomUser";
	public static final Object[][] TABLE_COLUMNS = {
			{ "customUserId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "partyId", Types.BIGINT },
			{ "partyUserId", Types.BIGINT },
			{ "mobileNo", Types.VARCHAR },
			{ "mobCountryCode", Types.VARCHAR },
			{ "deviceInfo", Types.VARCHAR },
			{ "documentVerified", Types.BOOLEAN },
			{ "accountCompleted", Types.BOOLEAN },
			{ "documentRemindLater", Types.BOOLEAN },
			{ "accountRemindLater", Types.BOOLEAN },
			{ "profileComplete", Types.BOOLEAN },
			{ "socialLogin", Types.BOOLEAN },
			{ "restPass", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "createdBy", Types.BIGINT },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "modifiedBy", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("customUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("partyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("partyUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("mobileNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("mobCountryCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("deviceInfo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("documentVerified", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("accountCompleted", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("documentRemindLater", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("accountRemindLater", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("profileComplete", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("socialLogin", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("restPass", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("createdBy", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedBy", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table BUCKZY_CustomUser (customUserId LONG not null primary key,userId LONG,partyId LONG,partyUserId LONG,mobileNo VARCHAR(75) null,mobCountryCode VARCHAR(75) null,deviceInfo VARCHAR(500) null,documentVerified BOOLEAN,accountCompleted BOOLEAN,documentRemindLater BOOLEAN,accountRemindLater BOOLEAN,profileComplete BOOLEAN,socialLogin BOOLEAN,restPass VARCHAR(100) null,createDate DATE null,createdBy LONG,modifiedDate DATE null,modifiedBy LONG)";
	public static final String TABLE_SQL_DROP = "drop table BUCKZY_CustomUser";
	public static final String ORDER_BY_JPQL = " ORDER BY customUser.customUserId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY BUCKZY_CustomUser.customUserId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.buckzy.common.service.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.buckzy.common.service.model.CustomUser"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.buckzy.common.service.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.buckzy.common.service.model.CustomUser"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.buckzy.common.service.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.buckzy.common.service.model.CustomUser"),
			true);
	public static final long MOBCOUNTRYCODE_COLUMN_BITMASK = 1L;
	public static final long MOBILENO_COLUMN_BITMASK = 2L;
	public static final long USERID_COLUMN_BITMASK = 4L;
	public static final long CUSTOMUSERID_COLUMN_BITMASK = 8L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.buckzy.common.service.service.util.ServiceProps.get(
				"lock.expiration.time.com.buckzy.common.service.model.CustomUser"));

	public CustomUserModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _customUserId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCustomUserId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _customUserId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CustomUser.class;
	}

	@Override
	public String getModelClassName() {
		return CustomUser.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("customUserId", getCustomUserId());
		attributes.put("userId", getUserId());
		attributes.put("partyId", getPartyId());
		attributes.put("partyUserId", getPartyUserId());
		attributes.put("mobileNo", getMobileNo());
		attributes.put("mobCountryCode", getMobCountryCode());
		attributes.put("deviceInfo", getDeviceInfo());
		attributes.put("documentVerified", getDocumentVerified());
		attributes.put("accountCompleted", getAccountCompleted());
		attributes.put("documentRemindLater", getDocumentRemindLater());
		attributes.put("accountRemindLater", getAccountRemindLater());
		attributes.put("profileComplete", getProfileComplete());
		attributes.put("socialLogin", getSocialLogin());
		attributes.put("restPass", getRestPass());
		attributes.put("createDate", getCreateDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("modifiedBy", getModifiedBy());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long customUserId = (Long)attributes.get("customUserId");

		if (customUserId != null) {
			setCustomUserId(customUserId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long partyId = (Long)attributes.get("partyId");

		if (partyId != null) {
			setPartyId(partyId);
		}

		Long partyUserId = (Long)attributes.get("partyUserId");

		if (partyUserId != null) {
			setPartyUserId(partyUserId);
		}

		String mobileNo = (String)attributes.get("mobileNo");

		if (mobileNo != null) {
			setMobileNo(mobileNo);
		}

		String mobCountryCode = (String)attributes.get("mobCountryCode");

		if (mobCountryCode != null) {
			setMobCountryCode(mobCountryCode);
		}

		String deviceInfo = (String)attributes.get("deviceInfo");

		if (deviceInfo != null) {
			setDeviceInfo(deviceInfo);
		}

		Boolean documentVerified = (Boolean)attributes.get("documentVerified");

		if (documentVerified != null) {
			setDocumentVerified(documentVerified);
		}

		Boolean accountCompleted = (Boolean)attributes.get("accountCompleted");

		if (accountCompleted != null) {
			setAccountCompleted(accountCompleted);
		}

		Boolean documentRemindLater = (Boolean)attributes.get(
				"documentRemindLater");

		if (documentRemindLater != null) {
			setDocumentRemindLater(documentRemindLater);
		}

		Boolean accountRemindLater = (Boolean)attributes.get(
				"accountRemindLater");

		if (accountRemindLater != null) {
			setAccountRemindLater(accountRemindLater);
		}

		Boolean profileComplete = (Boolean)attributes.get("profileComplete");

		if (profileComplete != null) {
			setProfileComplete(profileComplete);
		}

		Boolean socialLogin = (Boolean)attributes.get("socialLogin");

		if (socialLogin != null) {
			setSocialLogin(socialLogin);
		}

		String restPass = (String)attributes.get("restPass");

		if (restPass != null) {
			setRestPass(restPass);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long createdBy = (Long)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long modifiedBy = (Long)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}
	}

	@Override
	public long getCustomUserId() {
		return _customUserId;
	}

	@Override
	public void setCustomUserId(long customUserId) {
		_customUserId = customUserId;
	}

	@Override
	public String getCustomUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getCustomUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setCustomUserUuid(String customUserUuid) {
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@Override
	public long getPartyId() {
		return _partyId;
	}

	@Override
	public void setPartyId(long partyId) {
		_partyId = partyId;
	}

	@Override
	public long getPartyUserId() {
		return _partyUserId;
	}

	@Override
	public void setPartyUserId(long partyUserId) {
		_partyUserId = partyUserId;
	}

	@Override
	public String getPartyUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getPartyUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setPartyUserUuid(String partyUserUuid) {
	}

	@Override
	public String getMobileNo() {
		if (_mobileNo == null) {
			return StringPool.BLANK;
		}
		else {
			return _mobileNo;
		}
	}

	@Override
	public void setMobileNo(String mobileNo) {
		_columnBitmask |= MOBILENO_COLUMN_BITMASK;

		if (_originalMobileNo == null) {
			_originalMobileNo = _mobileNo;
		}

		_mobileNo = mobileNo;
	}

	public String getOriginalMobileNo() {
		return GetterUtil.getString(_originalMobileNo);
	}

	@Override
	public String getMobCountryCode() {
		if (_mobCountryCode == null) {
			return StringPool.BLANK;
		}
		else {
			return _mobCountryCode;
		}
	}

	@Override
	public void setMobCountryCode(String mobCountryCode) {
		_columnBitmask |= MOBCOUNTRYCODE_COLUMN_BITMASK;

		if (_originalMobCountryCode == null) {
			_originalMobCountryCode = _mobCountryCode;
		}

		_mobCountryCode = mobCountryCode;
	}

	public String getOriginalMobCountryCode() {
		return GetterUtil.getString(_originalMobCountryCode);
	}

	@Override
	public String getDeviceInfo() {
		if (_deviceInfo == null) {
			return StringPool.BLANK;
		}
		else {
			return _deviceInfo;
		}
	}

	@Override
	public void setDeviceInfo(String deviceInfo) {
		_deviceInfo = deviceInfo;
	}

	@Override
	public boolean getDocumentVerified() {
		return _documentVerified;
	}

	@Override
	public boolean isDocumentVerified() {
		return _documentVerified;
	}

	@Override
	public void setDocumentVerified(boolean documentVerified) {
		_documentVerified = documentVerified;
	}

	@Override
	public boolean getAccountCompleted() {
		return _accountCompleted;
	}

	@Override
	public boolean isAccountCompleted() {
		return _accountCompleted;
	}

	@Override
	public void setAccountCompleted(boolean accountCompleted) {
		_accountCompleted = accountCompleted;
	}

	@Override
	public boolean getDocumentRemindLater() {
		return _documentRemindLater;
	}

	@Override
	public boolean isDocumentRemindLater() {
		return _documentRemindLater;
	}

	@Override
	public void setDocumentRemindLater(boolean documentRemindLater) {
		_documentRemindLater = documentRemindLater;
	}

	@Override
	public boolean getAccountRemindLater() {
		return _accountRemindLater;
	}

	@Override
	public boolean isAccountRemindLater() {
		return _accountRemindLater;
	}

	@Override
	public void setAccountRemindLater(boolean accountRemindLater) {
		_accountRemindLater = accountRemindLater;
	}

	@Override
	public boolean getProfileComplete() {
		return _profileComplete;
	}

	@Override
	public boolean isProfileComplete() {
		return _profileComplete;
	}

	@Override
	public void setProfileComplete(boolean profileComplete) {
		_profileComplete = profileComplete;
	}

	@Override
	public boolean getSocialLogin() {
		return _socialLogin;
	}

	@Override
	public boolean isSocialLogin() {
		return _socialLogin;
	}

	@Override
	public void setSocialLogin(boolean socialLogin) {
		_socialLogin = socialLogin;
	}

	@Override
	public String getRestPass() {
		if (_restPass == null) {
			return StringPool.BLANK;
		}
		else {
			return _restPass;
		}
	}

	@Override
	public void setRestPass(String restPass) {
		_restPass = restPass;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public long getCreatedBy() {
		return _createdBy;
	}

	@Override
	public void setCreatedBy(long createdBy) {
		_createdBy = createdBy;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getModifiedBy() {
		return _modifiedBy;
	}

	@Override
	public void setModifiedBy(long modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			CustomUser.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CustomUser toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (CustomUser)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CustomUserImpl customUserImpl = new CustomUserImpl();

		customUserImpl.setCustomUserId(getCustomUserId());
		customUserImpl.setUserId(getUserId());
		customUserImpl.setPartyId(getPartyId());
		customUserImpl.setPartyUserId(getPartyUserId());
		customUserImpl.setMobileNo(getMobileNo());
		customUserImpl.setMobCountryCode(getMobCountryCode());
		customUserImpl.setDeviceInfo(getDeviceInfo());
		customUserImpl.setDocumentVerified(getDocumentVerified());
		customUserImpl.setAccountCompleted(getAccountCompleted());
		customUserImpl.setDocumentRemindLater(getDocumentRemindLater());
		customUserImpl.setAccountRemindLater(getAccountRemindLater());
		customUserImpl.setProfileComplete(getProfileComplete());
		customUserImpl.setSocialLogin(getSocialLogin());
		customUserImpl.setRestPass(getRestPass());
		customUserImpl.setCreateDate(getCreateDate());
		customUserImpl.setCreatedBy(getCreatedBy());
		customUserImpl.setModifiedDate(getModifiedDate());
		customUserImpl.setModifiedBy(getModifiedBy());

		customUserImpl.resetOriginalValues();

		return customUserImpl;
	}

	@Override
	public int compareTo(CustomUser customUser) {
		long primaryKey = customUser.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CustomUser)) {
			return false;
		}

		CustomUser customUser = (CustomUser)obj;

		long primaryKey = customUser.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		CustomUserModelImpl customUserModelImpl = this;

		customUserModelImpl._originalUserId = customUserModelImpl._userId;

		customUserModelImpl._setOriginalUserId = false;

		customUserModelImpl._originalMobileNo = customUserModelImpl._mobileNo;

		customUserModelImpl._originalMobCountryCode = customUserModelImpl._mobCountryCode;

		customUserModelImpl._setModifiedDate = false;

		customUserModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CustomUser> toCacheModel() {
		CustomUserCacheModel customUserCacheModel = new CustomUserCacheModel();

		customUserCacheModel.customUserId = getCustomUserId();

		customUserCacheModel.userId = getUserId();

		customUserCacheModel.partyId = getPartyId();

		customUserCacheModel.partyUserId = getPartyUserId();

		customUserCacheModel.mobileNo = getMobileNo();

		String mobileNo = customUserCacheModel.mobileNo;

		if ((mobileNo != null) && (mobileNo.length() == 0)) {
			customUserCacheModel.mobileNo = null;
		}

		customUserCacheModel.mobCountryCode = getMobCountryCode();

		String mobCountryCode = customUserCacheModel.mobCountryCode;

		if ((mobCountryCode != null) && (mobCountryCode.length() == 0)) {
			customUserCacheModel.mobCountryCode = null;
		}

		customUserCacheModel.deviceInfo = getDeviceInfo();

		String deviceInfo = customUserCacheModel.deviceInfo;

		if ((deviceInfo != null) && (deviceInfo.length() == 0)) {
			customUserCacheModel.deviceInfo = null;
		}

		customUserCacheModel.documentVerified = getDocumentVerified();

		customUserCacheModel.accountCompleted = getAccountCompleted();

		customUserCacheModel.documentRemindLater = getDocumentRemindLater();

		customUserCacheModel.accountRemindLater = getAccountRemindLater();

		customUserCacheModel.profileComplete = getProfileComplete();

		customUserCacheModel.socialLogin = getSocialLogin();

		customUserCacheModel.restPass = getRestPass();

		String restPass = customUserCacheModel.restPass;

		if ((restPass != null) && (restPass.length() == 0)) {
			customUserCacheModel.restPass = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			customUserCacheModel.createDate = createDate.getTime();
		}
		else {
			customUserCacheModel.createDate = Long.MIN_VALUE;
		}

		customUserCacheModel.createdBy = getCreatedBy();

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			customUserCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			customUserCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		customUserCacheModel.modifiedBy = getModifiedBy();

		return customUserCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{customUserId=");
		sb.append(getCustomUserId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", partyId=");
		sb.append(getPartyId());
		sb.append(", partyUserId=");
		sb.append(getPartyUserId());
		sb.append(", mobileNo=");
		sb.append(getMobileNo());
		sb.append(", mobCountryCode=");
		sb.append(getMobCountryCode());
		sb.append(", deviceInfo=");
		sb.append(getDeviceInfo());
		sb.append(", documentVerified=");
		sb.append(getDocumentVerified());
		sb.append(", accountCompleted=");
		sb.append(getAccountCompleted());
		sb.append(", documentRemindLater=");
		sb.append(getDocumentRemindLater());
		sb.append(", accountRemindLater=");
		sb.append(getAccountRemindLater());
		sb.append(", profileComplete=");
		sb.append(getProfileComplete());
		sb.append(", socialLogin=");
		sb.append(getSocialLogin());
		sb.append(", restPass=");
		sb.append(getRestPass());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", createdBy=");
		sb.append(getCreatedBy());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", modifiedBy=");
		sb.append(getModifiedBy());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("com.buckzy.common.service.model.CustomUser");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>customUserId</column-name><column-value><![CDATA[");
		sb.append(getCustomUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>partyId</column-name><column-value><![CDATA[");
		sb.append(getPartyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>partyUserId</column-name><column-value><![CDATA[");
		sb.append(getPartyUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>mobileNo</column-name><column-value><![CDATA[");
		sb.append(getMobileNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>mobCountryCode</column-name><column-value><![CDATA[");
		sb.append(getMobCountryCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>deviceInfo</column-name><column-value><![CDATA[");
		sb.append(getDeviceInfo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>documentVerified</column-name><column-value><![CDATA[");
		sb.append(getDocumentVerified());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountCompleted</column-name><column-value><![CDATA[");
		sb.append(getAccountCompleted());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>documentRemindLater</column-name><column-value><![CDATA[");
		sb.append(getDocumentRemindLater());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accountRemindLater</column-name><column-value><![CDATA[");
		sb.append(getAccountRemindLater());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>profileComplete</column-name><column-value><![CDATA[");
		sb.append(getProfileComplete());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>socialLogin</column-name><column-value><![CDATA[");
		sb.append(getSocialLogin());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>restPass</column-name><column-value><![CDATA[");
		sb.append(getRestPass());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createdBy</column-name><column-value><![CDATA[");
		sb.append(getCreatedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
		sb.append(getModifiedBy());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = CustomUser.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			CustomUser.class
		};
	private long _customUserId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private long _partyId;
	private long _partyUserId;
	private String _mobileNo;
	private String _originalMobileNo;
	private String _mobCountryCode;
	private String _originalMobCountryCode;
	private String _deviceInfo;
	private boolean _documentVerified;
	private boolean _accountCompleted;
	private boolean _documentRemindLater;
	private boolean _accountRemindLater;
	private boolean _profileComplete;
	private boolean _socialLogin;
	private String _restPass;
	private Date _createDate;
	private long _createdBy;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _modifiedBy;
	private long _columnBitmask;
	private CustomUser _escapedModel;
}