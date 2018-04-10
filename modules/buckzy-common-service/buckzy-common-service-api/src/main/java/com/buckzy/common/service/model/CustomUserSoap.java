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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Sandip.Patel
 * @generated
 */
@ProviderType
public class CustomUserSoap implements Serializable {
	public static CustomUserSoap toSoapModel(CustomUser model) {
		CustomUserSoap soapModel = new CustomUserSoap();

		soapModel.setCustomUserId(model.getCustomUserId());
		soapModel.setUserId(model.getUserId());
		soapModel.setPartyId(model.getPartyId());
		soapModel.setPartyUserId(model.getPartyUserId());
		soapModel.setMobileNo(model.getMobileNo());
		soapModel.setMobCountryCode(model.getMobCountryCode());
		soapModel.setDeviceInfo(model.getDeviceInfo());
		soapModel.setDocumentVerified(model.getDocumentVerified());
		soapModel.setAccountCompleted(model.getAccountCompleted());
		soapModel.setProfileComplete(model.getProfileComplete());
		soapModel.setSocialLogin(model.getSocialLogin());
		soapModel.setRestPass(model.getRestPass());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setModifiedBy(model.getModifiedBy());

		return soapModel;
	}

	public static CustomUserSoap[] toSoapModels(CustomUser[] models) {
		CustomUserSoap[] soapModels = new CustomUserSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CustomUserSoap[][] toSoapModels(CustomUser[][] models) {
		CustomUserSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CustomUserSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CustomUserSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CustomUserSoap[] toSoapModels(List<CustomUser> models) {
		List<CustomUserSoap> soapModels = new ArrayList<CustomUserSoap>(models.size());

		for (CustomUser model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CustomUserSoap[soapModels.size()]);
	}

	public CustomUserSoap() {
	}

	public long getPrimaryKey() {
		return _customUserId;
	}

	public void setPrimaryKey(long pk) {
		setCustomUserId(pk);
	}

	public long getCustomUserId() {
		return _customUserId;
	}

	public void setCustomUserId(long customUserId) {
		_customUserId = customUserId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getPartyId() {
		return _partyId;
	}

	public void setPartyId(long partyId) {
		_partyId = partyId;
	}

	public long getPartyUserId() {
		return _partyUserId;
	}

	public void setPartyUserId(long partyUserId) {
		_partyUserId = partyUserId;
	}

	public String getMobileNo() {
		return _mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		_mobileNo = mobileNo;
	}

	public String getMobCountryCode() {
		return _mobCountryCode;
	}

	public void setMobCountryCode(String mobCountryCode) {
		_mobCountryCode = mobCountryCode;
	}

	public String getDeviceInfo() {
		return _deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		_deviceInfo = deviceInfo;
	}

	public boolean getDocumentVerified() {
		return _documentVerified;
	}

	public boolean isDocumentVerified() {
		return _documentVerified;
	}

	public void setDocumentVerified(boolean documentVerified) {
		_documentVerified = documentVerified;
	}

	public boolean getAccountCompleted() {
		return _accountCompleted;
	}

	public boolean isAccountCompleted() {
		return _accountCompleted;
	}

	public void setAccountCompleted(boolean accountCompleted) {
		_accountCompleted = accountCompleted;
	}

	public boolean getProfileComplete() {
		return _profileComplete;
	}

	public boolean isProfileComplete() {
		return _profileComplete;
	}

	public void setProfileComplete(boolean profileComplete) {
		_profileComplete = profileComplete;
	}

	public boolean getSocialLogin() {
		return _socialLogin;
	}

	public boolean isSocialLogin() {
		return _socialLogin;
	}

	public void setSocialLogin(boolean socialLogin) {
		_socialLogin = socialLogin;
	}

	public String getRestPass() {
		return _restPass;
	}

	public void setRestPass(String restPass) {
		_restPass = restPass;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public long getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(long createdBy) {
		_createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(long modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	private long _customUserId;
	private long _userId;
	private long _partyId;
	private long _partyUserId;
	private String _mobileNo;
	private String _mobCountryCode;
	private String _deviceInfo;
	private boolean _documentVerified;
	private boolean _accountCompleted;
	private boolean _profileComplete;
	private boolean _socialLogin;
	private String _restPass;
	private Date _createDate;
	private long _createdBy;
	private Date _modifiedDate;
	private long _modifiedBy;
}