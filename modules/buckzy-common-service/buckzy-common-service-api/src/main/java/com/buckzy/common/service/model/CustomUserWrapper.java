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

package com.buckzy.common.service.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link CustomUser}.
 * </p>
 *
 * @author Sandip.Patel
 * @see CustomUser
 * @generated
 */
@ProviderType
public class CustomUserWrapper implements CustomUser, ModelWrapper<CustomUser> {
	public CustomUserWrapper(CustomUser customUser) {
		_customUser = customUser;
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
	public CustomUser toEscapedModel() {
		return new CustomUserWrapper(_customUser.toEscapedModel());
	}

	@Override
	public CustomUser toUnescapedModel() {
		return new CustomUserWrapper(_customUser.toUnescapedModel());
	}

	/**
	* Returns the account completed of this custom user.
	*
	* @return the account completed of this custom user
	*/
	@Override
	public boolean getAccountCompleted() {
		return _customUser.getAccountCompleted();
	}

	/**
	* Returns the account remind later of this custom user.
	*
	* @return the account remind later of this custom user
	*/
	@Override
	public boolean getAccountRemindLater() {
		return _customUser.getAccountRemindLater();
	}

	/**
	* Returns the document remind later of this custom user.
	*
	* @return the document remind later of this custom user
	*/
	@Override
	public boolean getDocumentRemindLater() {
		return _customUser.getDocumentRemindLater();
	}

	/**
	* Returns the document verified of this custom user.
	*
	* @return the document verified of this custom user
	*/
	@Override
	public boolean getDocumentVerified() {
		return _customUser.getDocumentVerified();
	}

	/**
	* Returns the profile complete of this custom user.
	*
	* @return the profile complete of this custom user
	*/
	@Override
	public boolean getProfileComplete() {
		return _customUser.getProfileComplete();
	}

	/**
	* Returns the social login of this custom user.
	*
	* @return the social login of this custom user
	*/
	@Override
	public boolean getSocialLogin() {
		return _customUser.getSocialLogin();
	}

	/**
	* Returns <code>true</code> if this custom user is account completed.
	*
	* @return <code>true</code> if this custom user is account completed; <code>false</code> otherwise
	*/
	@Override
	public boolean isAccountCompleted() {
		return _customUser.isAccountCompleted();
	}

	/**
	* Returns <code>true</code> if this custom user is account remind later.
	*
	* @return <code>true</code> if this custom user is account remind later; <code>false</code> otherwise
	*/
	@Override
	public boolean isAccountRemindLater() {
		return _customUser.isAccountRemindLater();
	}

	@Override
	public boolean isCachedModel() {
		return _customUser.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this custom user is document remind later.
	*
	* @return <code>true</code> if this custom user is document remind later; <code>false</code> otherwise
	*/
	@Override
	public boolean isDocumentRemindLater() {
		return _customUser.isDocumentRemindLater();
	}

	/**
	* Returns <code>true</code> if this custom user is document verified.
	*
	* @return <code>true</code> if this custom user is document verified; <code>false</code> otherwise
	*/
	@Override
	public boolean isDocumentVerified() {
		return _customUser.isDocumentVerified();
	}

	@Override
	public boolean isEscapedModel() {
		return _customUser.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _customUser.isNew();
	}

	/**
	* Returns <code>true</code> if this custom user is profile complete.
	*
	* @return <code>true</code> if this custom user is profile complete; <code>false</code> otherwise
	*/
	@Override
	public boolean isProfileComplete() {
		return _customUser.isProfileComplete();
	}

	/**
	* Returns <code>true</code> if this custom user is social login.
	*
	* @return <code>true</code> if this custom user is social login; <code>false</code> otherwise
	*/
	@Override
	public boolean isSocialLogin() {
		return _customUser.isSocialLogin();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _customUser.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CustomUser> toCacheModel() {
		return _customUser.toCacheModel();
	}

	@Override
	public int compareTo(CustomUser customUser) {
		return _customUser.compareTo(customUser);
	}

	@Override
	public int hashCode() {
		return _customUser.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _customUser.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new CustomUserWrapper((CustomUser)_customUser.clone());
	}

	/**
	* Returns the custom user uuid of this custom user.
	*
	* @return the custom user uuid of this custom user
	*/
	@Override
	public java.lang.String getCustomUserUuid() {
		return _customUser.getCustomUserUuid();
	}

	/**
	* Returns the device info of this custom user.
	*
	* @return the device info of this custom user
	*/
	@Override
	public java.lang.String getDeviceInfo() {
		return _customUser.getDeviceInfo();
	}

	/**
	* Returns the mob country code of this custom user.
	*
	* @return the mob country code of this custom user
	*/
	@Override
	public java.lang.String getMobCountryCode() {
		return _customUser.getMobCountryCode();
	}

	/**
	* Returns the mobile no of this custom user.
	*
	* @return the mobile no of this custom user
	*/
	@Override
	public java.lang.String getMobileNo() {
		return _customUser.getMobileNo();
	}

	/**
	* Returns the party user uuid of this custom user.
	*
	* @return the party user uuid of this custom user
	*/
	@Override
	public java.lang.String getPartyUserUuid() {
		return _customUser.getPartyUserUuid();
	}

	/**
	* Returns the rest pass of this custom user.
	*
	* @return the rest pass of this custom user
	*/
	@Override
	public java.lang.String getRestPass() {
		return _customUser.getRestPass();
	}

	/**
	* Returns the user uuid of this custom user.
	*
	* @return the user uuid of this custom user
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _customUser.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _customUser.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _customUser.toXmlString();
	}

	/**
	* Returns the create date of this custom user.
	*
	* @return the create date of this custom user
	*/
	@Override
	public Date getCreateDate() {
		return _customUser.getCreateDate();
	}

	/**
	* Returns the modified date of this custom user.
	*
	* @return the modified date of this custom user
	*/
	@Override
	public Date getModifiedDate() {
		return _customUser.getModifiedDate();
	}

	/**
	* Returns the created by of this custom user.
	*
	* @return the created by of this custom user
	*/
	@Override
	public long getCreatedBy() {
		return _customUser.getCreatedBy();
	}

	/**
	* Returns the custom user ID of this custom user.
	*
	* @return the custom user ID of this custom user
	*/
	@Override
	public long getCustomUserId() {
		return _customUser.getCustomUserId();
	}

	/**
	* Returns the modified by of this custom user.
	*
	* @return the modified by of this custom user
	*/
	@Override
	public long getModifiedBy() {
		return _customUser.getModifiedBy();
	}

	/**
	* Returns the party ID of this custom user.
	*
	* @return the party ID of this custom user
	*/
	@Override
	public long getPartyId() {
		return _customUser.getPartyId();
	}

	/**
	* Returns the party user ID of this custom user.
	*
	* @return the party user ID of this custom user
	*/
	@Override
	public long getPartyUserId() {
		return _customUser.getPartyUserId();
	}

	/**
	* Returns the primary key of this custom user.
	*
	* @return the primary key of this custom user
	*/
	@Override
	public long getPrimaryKey() {
		return _customUser.getPrimaryKey();
	}

	/**
	* Returns the user ID of this custom user.
	*
	* @return the user ID of this custom user
	*/
	@Override
	public long getUserId() {
		return _customUser.getUserId();
	}

	@Override
	public void persist() {
		_customUser.persist();
	}

	/**
	* Sets whether this custom user is account completed.
	*
	* @param accountCompleted the account completed of this custom user
	*/
	@Override
	public void setAccountCompleted(boolean accountCompleted) {
		_customUser.setAccountCompleted(accountCompleted);
	}

	/**
	* Sets whether this custom user is account remind later.
	*
	* @param accountRemindLater the account remind later of this custom user
	*/
	@Override
	public void setAccountRemindLater(boolean accountRemindLater) {
		_customUser.setAccountRemindLater(accountRemindLater);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_customUser.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this custom user.
	*
	* @param createDate the create date of this custom user
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_customUser.setCreateDate(createDate);
	}

	/**
	* Sets the created by of this custom user.
	*
	* @param createdBy the created by of this custom user
	*/
	@Override
	public void setCreatedBy(long createdBy) {
		_customUser.setCreatedBy(createdBy);
	}

	/**
	* Sets the custom user ID of this custom user.
	*
	* @param customUserId the custom user ID of this custom user
	*/
	@Override
	public void setCustomUserId(long customUserId) {
		_customUser.setCustomUserId(customUserId);
	}

	/**
	* Sets the custom user uuid of this custom user.
	*
	* @param customUserUuid the custom user uuid of this custom user
	*/
	@Override
	public void setCustomUserUuid(java.lang.String customUserUuid) {
		_customUser.setCustomUserUuid(customUserUuid);
	}

	/**
	* Sets the device info of this custom user.
	*
	* @param deviceInfo the device info of this custom user
	*/
	@Override
	public void setDeviceInfo(java.lang.String deviceInfo) {
		_customUser.setDeviceInfo(deviceInfo);
	}

	/**
	* Sets whether this custom user is document remind later.
	*
	* @param documentRemindLater the document remind later of this custom user
	*/
	@Override
	public void setDocumentRemindLater(boolean documentRemindLater) {
		_customUser.setDocumentRemindLater(documentRemindLater);
	}

	/**
	* Sets whether this custom user is document verified.
	*
	* @param documentVerified the document verified of this custom user
	*/
	@Override
	public void setDocumentVerified(boolean documentVerified) {
		_customUser.setDocumentVerified(documentVerified);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_customUser.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_customUser.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_customUser.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the mob country code of this custom user.
	*
	* @param mobCountryCode the mob country code of this custom user
	*/
	@Override
	public void setMobCountryCode(java.lang.String mobCountryCode) {
		_customUser.setMobCountryCode(mobCountryCode);
	}

	/**
	* Sets the mobile no of this custom user.
	*
	* @param mobileNo the mobile no of this custom user
	*/
	@Override
	public void setMobileNo(java.lang.String mobileNo) {
		_customUser.setMobileNo(mobileNo);
	}

	/**
	* Sets the modified by of this custom user.
	*
	* @param modifiedBy the modified by of this custom user
	*/
	@Override
	public void setModifiedBy(long modifiedBy) {
		_customUser.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this custom user.
	*
	* @param modifiedDate the modified date of this custom user
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_customUser.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_customUser.setNew(n);
	}

	/**
	* Sets the party ID of this custom user.
	*
	* @param partyId the party ID of this custom user
	*/
	@Override
	public void setPartyId(long partyId) {
		_customUser.setPartyId(partyId);
	}

	/**
	* Sets the party user ID of this custom user.
	*
	* @param partyUserId the party user ID of this custom user
	*/
	@Override
	public void setPartyUserId(long partyUserId) {
		_customUser.setPartyUserId(partyUserId);
	}

	/**
	* Sets the party user uuid of this custom user.
	*
	* @param partyUserUuid the party user uuid of this custom user
	*/
	@Override
	public void setPartyUserUuid(java.lang.String partyUserUuid) {
		_customUser.setPartyUserUuid(partyUserUuid);
	}

	/**
	* Sets the primary key of this custom user.
	*
	* @param primaryKey the primary key of this custom user
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_customUser.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_customUser.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this custom user is profile complete.
	*
	* @param profileComplete the profile complete of this custom user
	*/
	@Override
	public void setProfileComplete(boolean profileComplete) {
		_customUser.setProfileComplete(profileComplete);
	}

	/**
	* Sets the rest pass of this custom user.
	*
	* @param restPass the rest pass of this custom user
	*/
	@Override
	public void setRestPass(java.lang.String restPass) {
		_customUser.setRestPass(restPass);
	}

	/**
	* Sets whether this custom user is social login.
	*
	* @param socialLogin the social login of this custom user
	*/
	@Override
	public void setSocialLogin(boolean socialLogin) {
		_customUser.setSocialLogin(socialLogin);
	}

	/**
	* Sets the user ID of this custom user.
	*
	* @param userId the user ID of this custom user
	*/
	@Override
	public void setUserId(long userId) {
		_customUser.setUserId(userId);
	}

	/**
	* Sets the user uuid of this custom user.
	*
	* @param userUuid the user uuid of this custom user
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_customUser.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CustomUserWrapper)) {
			return false;
		}

		CustomUserWrapper customUserWrapper = (CustomUserWrapper)obj;

		if (Objects.equals(_customUser, customUserWrapper._customUser)) {
			return true;
		}

		return false;
	}

	@Override
	public CustomUser getWrappedModel() {
		return _customUser;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _customUser.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _customUser.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_customUser.resetOriginalValues();
	}

	private final CustomUser _customUser;
}