package com.buckzy.common.beans;

import com.buckzy.common.service.exception.NoSuchCustomUserException;
import com.buckzy.common.service.model.CustomUser;
import com.buckzy.common.service.service.CustomUserLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

public class UserBean {
	
	Log _log = LogFactoryUtil.getLog(UserBean.class.getName());
	
	private long userId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String emailAddress;
	private CustomUserBean customUserBean;
	private PartyBean partyBean;
	private User user;
	
	public UserBean(User user){
		if(Validator.isNotNull(user)){
			this.userId = user.getUserId();
			this.firstName = user.getFirstName();
			this.lastName = user.getLastName();
			this.middleName = user.getMiddleName();
			this.emailAddress = user.getEmailAddress();
			this.user = user;
			try {
				CustomUser customUser = CustomUserLocalServiceUtil.getCustomUserByLRUserId(this.userId);
				CustomUserBean customUserBean = new CustomUserBean(customUser);
				this.customUserBean = customUserBean;
			} catch (NoSuchCustomUserException e) {
				_log.error(e.getMessage());
			}
		}
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public CustomUserBean getCustomUserBean() {
		return customUserBean;
	}
	public void setCustomUserBean(CustomUserBean customUserBean) {
		this.customUserBean = customUserBean;
	}

	public PartyBean getPartyBean() {
		return partyBean;
	}

	public void setPartyBean(PartyBean partyBean) {
		this.partyBean = partyBean;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
	
	
}
