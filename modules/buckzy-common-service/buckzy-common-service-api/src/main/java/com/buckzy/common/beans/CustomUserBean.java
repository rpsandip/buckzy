package com.buckzy.common.beans;

import com.buckzy.common.service.model.CustomUser;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

public class CustomUserBean {
	
	Log _log = LogFactoryUtil.getLog(CustomUserBean.class.getName());
	
	private long customUserId;
	private long userId;
	private String mobileCountryCode;
	private String mobNo;
	private long partyId;
	private long partyUserId;
	private boolean accountCompleted;
	private boolean isProfileComplete;
	private boolean documentVerified;
	private boolean documentRemindLater;
	private boolean accountRemindLater;
	
	public CustomUserBean(CustomUser customUser){
		if(Validator.isNotNull(customUser)){
			this.customUserId = customUser.getCustomUserId();
			this.userId = customUser.getUserId();
			this.mobileCountryCode  = customUser.getMobCountryCode();
			this.mobNo = customUser.getMobileNo();
			this.partyId = customUser.getPartyId();
			this.partyUserId = customUser.getPartyUserId();
			this.isProfileComplete = customUser.isProfileComplete();
			this.accountCompleted = customUser.getAccountCompleted();
			this.documentVerified  = customUser.getDocumentVerified();
			this.documentRemindLater = customUser.getDocumentRemindLater();
			this.accountRemindLater = customUser.getAccountRemindLater();
		}
	}

	public long getCustomUserId() {
		return customUserId;
	}

	public void setCustomUserId(long customUserId) {
		this.customUserId = customUserId;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getPartyId() {
		return partyId;
	}

	public void setPartyId(long partyId) {
		this.partyId = partyId;
	}


	public String getMobileCountryCode() {
		return mobileCountryCode;
	}

	public void setMobileCountryCode(String mobileCountryCode) {
		this.mobileCountryCode = mobileCountryCode;
	}

	public boolean isProfileComplete() {
		return isProfileComplete;
	}

	public void setProfileComplete(boolean isProfileComplete) {
		this.isProfileComplete = isProfileComplete;
	}

	public long getPartyUserId() {
		return partyUserId;
	}

	public void setPartyUserId(long partyUserId) {
		this.partyUserId = partyUserId;
	}

	public boolean isDocumentVerified() {
		return documentVerified;
	}

	public void setDocumentVerified(boolean documentVerified) {
		this.documentVerified = documentVerified;
	}

	public boolean isAccountCompleted() {
		return accountCompleted;
	}

	public void setAccountCompleted(boolean accountCompleted) {
		this.accountCompleted = accountCompleted;
	}

	public boolean isDocumentRemindLater() {
		return documentRemindLater;
	}

	public void setDocumentRemindLater(boolean documentRemindLater) {
		this.documentRemindLater = documentRemindLater;
	}

	public boolean isAccountRemindLater() {
		return accountRemindLater;
	}

	public void setAccountRemindLater(boolean accountRemindLater) {
		this.accountRemindLater = accountRemindLater;
	}
	
	
	
}
