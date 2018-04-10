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

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CustomUser in entity cache.
 *
 * @author Sandip.Patel
 * @see CustomUser
 * @generated
 */
@ProviderType
public class CustomUserCacheModel implements CacheModel<CustomUser>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CustomUserCacheModel)) {
			return false;
		}

		CustomUserCacheModel customUserCacheModel = (CustomUserCacheModel)obj;

		if (customUserId == customUserCacheModel.customUserId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, customUserId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{customUserId=");
		sb.append(customUserId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", partyId=");
		sb.append(partyId);
		sb.append(", partyUserId=");
		sb.append(partyUserId);
		sb.append(", mobileNo=");
		sb.append(mobileNo);
		sb.append(", mobCountryCode=");
		sb.append(mobCountryCode);
		sb.append(", deviceInfo=");
		sb.append(deviceInfo);
		sb.append(", documentVerified=");
		sb.append(documentVerified);
		sb.append(", accountCompleted=");
		sb.append(accountCompleted);
		sb.append(", profileComplete=");
		sb.append(profileComplete);
		sb.append(", socialLogin=");
		sb.append(socialLogin);
		sb.append(", restPass=");
		sb.append(restPass);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", modifiedBy=");
		sb.append(modifiedBy);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CustomUser toEntityModel() {
		CustomUserImpl customUserImpl = new CustomUserImpl();

		customUserImpl.setCustomUserId(customUserId);
		customUserImpl.setUserId(userId);
		customUserImpl.setPartyId(partyId);
		customUserImpl.setPartyUserId(partyUserId);

		if (mobileNo == null) {
			customUserImpl.setMobileNo(StringPool.BLANK);
		}
		else {
			customUserImpl.setMobileNo(mobileNo);
		}

		if (mobCountryCode == null) {
			customUserImpl.setMobCountryCode(StringPool.BLANK);
		}
		else {
			customUserImpl.setMobCountryCode(mobCountryCode);
		}

		if (deviceInfo == null) {
			customUserImpl.setDeviceInfo(StringPool.BLANK);
		}
		else {
			customUserImpl.setDeviceInfo(deviceInfo);
		}

		customUserImpl.setDocumentVerified(documentVerified);
		customUserImpl.setAccountCompleted(accountCompleted);
		customUserImpl.setProfileComplete(profileComplete);
		customUserImpl.setSocialLogin(socialLogin);

		if (restPass == null) {
			customUserImpl.setRestPass(StringPool.BLANK);
		}
		else {
			customUserImpl.setRestPass(restPass);
		}

		if (createDate == Long.MIN_VALUE) {
			customUserImpl.setCreateDate(null);
		}
		else {
			customUserImpl.setCreateDate(new Date(createDate));
		}

		customUserImpl.setCreatedBy(createdBy);

		if (modifiedDate == Long.MIN_VALUE) {
			customUserImpl.setModifiedDate(null);
		}
		else {
			customUserImpl.setModifiedDate(new Date(modifiedDate));
		}

		customUserImpl.setModifiedBy(modifiedBy);

		customUserImpl.resetOriginalValues();

		return customUserImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		customUserId = objectInput.readLong();

		userId = objectInput.readLong();

		partyId = objectInput.readLong();

		partyUserId = objectInput.readLong();
		mobileNo = objectInput.readUTF();
		mobCountryCode = objectInput.readUTF();
		deviceInfo = objectInput.readUTF();

		documentVerified = objectInput.readBoolean();

		accountCompleted = objectInput.readBoolean();

		profileComplete = objectInput.readBoolean();

		socialLogin = objectInput.readBoolean();
		restPass = objectInput.readUTF();
		createDate = objectInput.readLong();

		createdBy = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		modifiedBy = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(customUserId);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(partyId);

		objectOutput.writeLong(partyUserId);

		if (mobileNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(mobileNo);
		}

		if (mobCountryCode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(mobCountryCode);
		}

		if (deviceInfo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(deviceInfo);
		}

		objectOutput.writeBoolean(documentVerified);

		objectOutput.writeBoolean(accountCompleted);

		objectOutput.writeBoolean(profileComplete);

		objectOutput.writeBoolean(socialLogin);

		if (restPass == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(restPass);
		}

		objectOutput.writeLong(createDate);

		objectOutput.writeLong(createdBy);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(modifiedBy);
	}

	public long customUserId;
	public long userId;
	public long partyId;
	public long partyUserId;
	public String mobileNo;
	public String mobCountryCode;
	public String deviceInfo;
	public boolean documentVerified;
	public boolean accountCompleted;
	public boolean profileComplete;
	public boolean socialLogin;
	public String restPass;
	public long createDate;
	public long createdBy;
	public long modifiedDate;
	public long modifiedBy;
}