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

package com.buckzy.common.service.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.buckzy.common.beans.PaymentBean;
import com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil;
import com.buckzy.common.service.service.CustomUserLocalServiceUtil;
import com.buckzy.common.service.service.base.BuckzyCommonLocalServiceBaseImpl;
import com.buckzy.common.util.BuckzyConstants;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.TicketConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.RandomUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TicketLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.buckzy.common.beans.PaymentBean;
/**
 * The implementation of the buckzy common local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.buckzy.common.service.service.BuckzyCommonLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Sandip.Patel
 * @see BuckzyCommonLocalServiceBaseImpl
 * @see com.buckzy.common.service.service.BuckzyCommonLocalServiceUtil
 */
public class BuckzyCommonLocalServiceImpl
	extends BuckzyCommonLocalServiceBaseImpl {
	
	Log _log = LogFactoryUtil.getLog(BuckzyCommonLocalServiceImpl.class.getName());
	
	/**
	 * Register User
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param emailAddress
	 * @param password1
	 * @param address
	 * @param city
	 * @param zipcode
	 * @param state
	 * @param dob
	 * @param mobileNum
	 * @return
	 * @throws PortalException 
	 */
	public JSONObject registerUser(String token,String firstName, String middleName, String lastName, String emailAddress, String password1,
			String address, String city, String zipcode, String state, String countryCode, String currencyCode,String dob, String mobileNum, String mobileCountryCode,
			String reminderQuestion, String reminderAnswer, String reminderQuestion2, String reminderAnswer2, String deviceInfo, boolean isSocialLogin ,long creatorUserId, long groupId, ServiceContext serviceContext) throws PortalException{
		
		User user = null;
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		// Validate User Input
		
		String userValidationErrMsg = validateUser(firstName, lastName, emailAddress, password1, 
				address, city, zipcode, state, dob, mobileNum);
		
		if(userValidationErrMsg.length()==0){
			
			serviceContext.setUuid(UUID.randomUUID().toString());
			serviceContext.setCreateDate(new Date());
			serviceContext.setModifiedDate(new Date());
			
			String[] birthDay = validateBirthDay(dob, responseObj);
			int month=1;
			int day=1;
			int year=1970;
			
			if(birthDay.length>0){
				month = Integer.parseInt(birthDay[0]);
				day = Integer.parseInt(birthDay[1]);
				year =Integer.parseInt(birthDay[2]);
				//month--;
			}
			
			long defaultSiteId = getDefaultSiteGroupId();
			if(defaultSiteId>0){
				long[] groupIds = {defaultSiteId};
				
				// Create User 
				user = UserLocalServiceUtil.addUser(creatorUserId, PortalUtil.getDefaultCompanyId(), false, password1, password1,
						 true, null, emailAddress, 0l, StringPool.BLANK,PortalUtil.getSiteDefaultLocale(groupId),
						 firstName, middleName, lastName, 1l, 1l, true, month, 
						  day,year,StringPool.BLANK, groupIds, null, null, null,true, serviceContext);
				
				
				if(Validator.isNotNull(user)){
	
					user.setReminderQueryQuestion(reminderQuestion.concat(StringPool.COMMA).concat(reminderQuestion2));
					user.setReminderQueryAnswer(reminderAnswer.concat(StringPool.COMMA).concat(reminderAnswer2));
					user.setEmailAddressVerified(false);
					
					UserLocalServiceUtil.updateUser(user);
					
					// Add custom user detail
					responseObj =  CustomUserLocalServiceUtil.addBuckzyCustomUser(token, user, emailAddress,password1,address, zipcode, city, state, countryCode, currencyCode, mobileNum, mobileCountryCode, deviceInfo, isSocialLogin,creatorUserId);
				
					responseObj.put("user", user);
					
					try {
						createEmailVerificationTicketAndSendMail(user, serviceContext);
					} catch (AddressException e) {
						_log.error(e);
					} catch (IOException e) {
						_log.error(e);
					}
				
				}
			}
			
		}else{
			responseObj.put("userErrMsg", userValidationErrMsg);
		}
		
		return responseObj;
		
	}
	
	private void createEmailVerificationTicketAndSendMail(User user, ServiceContext serviceContext) throws IOException, AddressException{
		
		Ticket ticket = TicketLocalServiceUtil.addDistinctTicket(
				user.getCompanyId(), User.class.getName(), user.getUserId(),
				TicketConstants.TYPE_EMAIL_ADDRESS, user.getEmailAddress(), null,
				serviceContext);
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, Integer.parseInt(PropsUtil.get("email.address.verification.token.expire.days"))); // Adding 1 days
		ticket.setExpirationDate(c.getTime());
		TicketLocalServiceUtil.updateTicket(ticket);
		
		String emailVerificationLink = serviceContext.getPortalURL() + "/web/guest/email-verification?ticketKey=" +  ticket.getKey();
		
		// Send Mail Code
		
		InputStream is = null;
	    UnsyncBufferedReader unsyncBufferedReader = null;
	    ClassLoader classloader = getClass().getClassLoader();
		is = classloader.getResourceAsStream("email_verification.tmpl");
		StringBundler sb = new StringBundler();
		unsyncBufferedReader = new UnsyncBufferedReader(new InputStreamReader(is));
		String line = null;
		while ((line = unsyncBufferedReader.readLine()) != null) {
			 sb.append(line);
			 sb.append(CharPool.NEW_LINE);
		}
		unsyncBufferedReader.close();
		is.close();
		
		
		String body = sb.toString().trim();
		String[] variables = new String[] { "[$USER_NAME]","[$PORTAL_URL]","[$EMAIL_VERIFICATION_URL]", "[$VERIFICATION_KEY]"};
		String[] values = new String[] { user.getFirstName()+StringPool.SPACE+user.getLastName(),
				serviceContext.getPortalURL(),
				emailVerificationLink, ticket.getKey()};
		
		body = StringUtil.replace(body, variables, values);
		
		
		String subject = serviceContext.getPortalURL() + " Email Address Verification";
		String fromMail = BuckzyConstants.FROM_EMAIL;
		InternetAddress from = new InternetAddress(fromMail);
		InternetAddress[] to =new InternetAddress[1];
		to[0] = new InternetAddress(user.getEmailAddress());
		
		MailMessage mailMessage = new MailMessage(from, subject, body, true);
		
		mailMessage.setTo(to);
		MailServiceUtil.sendEmail(mailMessage);
		
		_log.info("Email Address verification mail send to ->" + user.getEmailAddress());
	}
	
	private String validateUser(String firstName, String lastName, String emailAddress, String password1,
			String address, String city, String zipcode, String state, String dob, String mobileNumber) {
		String errMsg=StringPool.BLANK;
		
		if(Validator.isNull(firstName) || firstName.length()>70){
			errMsg = "First Name can not be blank or more than 70 Chars";
		}else if(Validator.isNull(lastName) || lastName.length()>70){
			errMsg = "Last Name can not be blank or more than 70 Chars";
		}else if(Validator.isNull(emailAddress) || emailAddress.length()>70 ||  !Validator.isEmailAddress(emailAddress)){
			errMsg = "Email Address can not be blank or more than 70 Chars";
		}else if(Validator.isNull(password1) || password1.length()<12){
			errMsg = "Password must be at least 12 Chars";
		}else if(Validator.isNull(address) || address.length()>200){
			errMsg = "Address can not blank or more than 200 Chars";
		}else if(Validator.isNull(city) || city.length()>50){
			errMsg = "City can not be blank or more than 50 Chars";
		}else if(Validator.isNull(state) || state.length()>50){
			errMsg = "State can not be blank or more than 50 Chars";
		}else if(Validator.isNull(zipcode) || zipcode.length()>20){
			errMsg = "Zipcode can not be blank or more than 20 Chars";
		}else if(Validator.isNull(dob)){
			errMsg = "Date of Birth can not be blank";
		}else if(Validator.isNull(mobileNumber) || mobileNumber.length()>16){
			errMsg = "Invalid mobile number";
		}
		
		return errMsg;
	}
	
	private String[] validateBirthDay(String birthDay,JSONObject responseObj){
		SimpleDateFormat df = new SimpleDateFormat(BuckzyConstants.DATE_FORMAT);
		try {
			df.parse(birthDay);
			return birthDay.split(StringPool.FORWARD_SLASH);
		} catch (ParseException e) {
			responseObj.put("userErrMsg", "Invalid Date Of Birth;");
			_log.debug("Not valid Birthday in Registration ::" + birthDay);
		}
		return new String[]{};
	}
	
	
	public JSONObject  getAPIResponse(String URL, String type, String params,String token){
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try{
		
	    if(Validator.isNotNull(type) && type.equals(BuckzyConstants.HTTP_GET)){
	    	
	    	HttpGet getRequest = new HttpGet(BuckzyConstants.BASE_URL+URL);
	    	getRequest.setHeader("Content-type", "application/json");
	    	if(Validator.isNotNull(token)){
	    		getRequest.setHeader("Authorization", token);
	    	}
	        
	        HttpResponse response = httpClient.execute(getRequest);
	       
	        HttpEntity httpEntity = response.getEntity();
		    String apiOutput = EntityUtils.toString(httpEntity);
		    
		    int status = response.getStatusLine().getStatusCode();
		        
		    httpClient.getConnectionManager().shutdown();
		    
		    JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		    responseObj.put("status", status);
		    responseObj.put("data", apiOutput);
		    
		        
		    return responseObj;
	        
	        
	    }else if (Validator.isNotNull(type) && type.equals(BuckzyConstants.HTTP_POST)){
	    	
	    	HttpPost postRequest = new HttpPost(BuckzyConstants.BASE_URL+URL);
	    	postRequest.setHeader("Content-type", "application/json");
	    	postRequest.setHeader("Authorization", token);
	    	StringEntity stringEntity =new StringEntity(params);
			postRequest.setEntity(stringEntity);
	        
			HttpResponse response = httpClient.execute(postRequest);
			
	        HttpEntity httpEntity = response.getEntity();
		    String apiOutput = EntityUtils.toString(httpEntity);
		    
		    int status = response.getStatusLine().getStatusCode();
		    JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		    responseObj.put("status", status);
		    responseObj.put("data", apiOutput);
		    
		    httpClient.getConnectionManager().shutdown();
		    return responseObj;
		    
	    }else if (Validator.isNotNull(type) && type.equals(BuckzyConstants.HTTP_PUT)){
	    	HttpPut  putRequest = new HttpPut(BuckzyConstants.BASE_URL+URL);
	    	putRequest.setHeader("Content-type", "application/json");
	    	putRequest.setHeader("Authorization", token);
	    	StringEntity stringEntity =new StringEntity(params);
	    	putRequest.setEntity(stringEntity);
	        
			HttpResponse response = httpClient.execute(putRequest);
			
	        HttpEntity httpEntity = response.getEntity();
		    String apiOutput = EntityUtils.toString(httpEntity);
		    
		    int status = response.getStatusLine().getStatusCode();
		    JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		    responseObj.put("status", status);
		    responseObj.put("data", apiOutput);
		        
		    httpClient.getConnectionManager().shutdown();
		    return responseObj;
	    }
	    
         
		}catch(Exception e){
			httpClient.getConnectionManager().shutdown();
		}
		finally {
			httpClient.getConnectionManager().shutdown();
		}
		
		return JSONFactoryUtil.createJSONObject();
	}
	
	
	public JSONObject getMultiPartResponse(String URL, long partyId ,String token, File file, String docTypeCode){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		 JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		try{
			HttpPost postRequest = new HttpPost(BuckzyConstants.BASE_URL+URL);
			postRequest.setHeader("Authorization", token);
			
			MultipartEntity entity = new MultipartEntity();

			JSONObject requestJson = JSONFactoryUtil.createJSONObject();
			requestJson.put("docId", "");
			requestJson.put("prtyId", partyId);
			requestJson.put("pymtItemId", "");
			requestJson.put("docTypeCd", docTypeCode);
			requestJson.put("docNr", "");
			requestJson.put("relateDate", "");
			requestJson.put("docFile", "file");
			requestJson.put("fileName", "");
			
			entity.addPart("request", new StringBody(requestJson.toJSONString()));
			entity.addPart("file", new FileBody(file));
			
			postRequest.setEntity(entity);
			
			HttpResponse response = httpClient.execute(postRequest);
			
	        HttpEntity httpEntity = response.getEntity();
		    String apiOutput = EntityUtils.toString(httpEntity);
		        
		    int status = response.getStatusLine().getStatusCode();
		   
		    responseObj.put("status", status);
		    responseObj.put("data", apiOutput);
		        
		    httpClient.getConnectionManager().shutdown();
		    return responseObj;
				
			
		}catch(Exception e){
			httpClient.getConnectionManager().shutdown();
		}
		finally {
			httpClient.getConnectionManager().shutdown();
		}
		
		return responseObj;
	} 
	
	
	public String getToken(String email, String password){
		
		String token = StringPool.BLANK;
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try{
			HttpPost postRequest = new HttpPost(BuckzyConstants.BASE_URL+BuckzyConstants.LOGIN_POST_URL);
	    	postRequest.setHeader("Content-type", "application/json");
	    	
	    	JSONObject paramsJsonObj = JSONFactoryUtil.createJSONObject();
			paramsJsonObj.put("email", email);
			paramsJsonObj.put("password", password);
			
			String params = paramsJsonObj.toJSONString();
			
	    	StringEntity stringEntity =new StringEntity(params);
			postRequest.setEntity(stringEntity);
	        
			HttpResponse response = httpClient.execute(postRequest);
			
			int statusCode = response.getStatusLine().getStatusCode();
	        if (statusCode == 200){	

	        	Header[] headerArray = response.getAllHeaders();
			    for(Header header : headerArray){
			    	if(header.getName().equals("Authorization")){
			    		token = header.getValue();
			    	}
			    }
			       
		        httpClient.getConnectionManager().shutdown();
	        }else{
	        	throw new RuntimeException("Failed with HTTP error code : " + statusCode);
	        }
		}catch(Exception e){
			httpClient.getConnectionManager().shutdown();
		}
		finally {
			httpClient.getConnectionManager().shutdown();
		}
		
		return token;
	}
	
	public JSONArray getCountryList(String token){
		
		JSONArray countryArray = JSONFactoryUtil.createJSONArray();
		
		JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(BuckzyConstants.GET_COUNTRY_LIST_URL, BuckzyConstants.HTTP_GET, StringPool.BLANK, token);
		
		try {
			if(response.getInt("status")==200){
				countryArray = JSONFactoryUtil.createJSONArray(response.getString("data"));
			}
		} catch (JSONException e) {
			_log.error(e.getMessage());
		}
		
		return countryArray;
	}
	
    public JSONArray getBankList(String token, String cityId){
		
		JSONArray bankArray = JSONFactoryUtil.createJSONArray();
		
		JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(BuckzyConstants.GET_BANK__LIST_URL+cityId, BuckzyConstants.HTTP_GET, StringPool.BLANK, token);
		try {
			if(response.getInt("status")==200){
				bankArray = JSONFactoryUtil.createJSONArray(response.getString("data"));
			}
		} catch (JSONException e) {
			_log.error(e.getMessage());
		}
		
		return bankArray;
	}
    
    public JSONArray getStateList(String token, String countryCode){
		
		JSONArray stateArray = JSONFactoryUtil.createJSONArray();
		String url = BuckzyConstants.GET_STATE_LIST_URL.replace("countryCode", countryCode);
		JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(url, BuckzyConstants.HTTP_GET, StringPool.BLANK, token);
		try {
			if(response.getInt("status")==200){
				stateArray = JSONFactoryUtil.createJSONArray(response.getString("data"));
			}
		} catch (JSONException e) {
			_log.error(e.getMessage());
		}
		
		return stateArray;
	}
    
public JSONArray getCityList(String token, String keyWord, String stateCode, String countryCode){
		
		JSONArray cityArray = JSONFactoryUtil.createJSONArray();
		String url = BuckzyConstants.GET_CITY_LIST_URL.replace("cityName", keyWord);
		if(Validator.isNotNull(keyWord) && keyWord.length()>0){
			url += "cityNm="+keyWord;
		}
		if(Validator.isNotNull(stateCode)){
			url += "&stateCd="+stateCode;
		}
		if(Validator.isNotNull(countryCode)){
			url +="&cntryCd=" + countryCode;
		}
		
		JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(url, BuckzyConstants.HTTP_GET, StringPool.BLANK, token);
		try {
			if(response.getInt("status")==200){
				cityArray = JSONFactoryUtil.createJSONArray(response.getString("data"));
			}
		} catch (JSONException e) {
			_log.error(e.getMessage());
		}
		
		return cityArray;
	}
    
    public JSONArray getBranches(String token, String  bankId, String cityId){
		
		JSONArray branchArray = JSONFactoryUtil.createJSONArray();
		String url = BuckzyConstants.GET_BRANCH__LIST_URL.replace("bankId", bankId);
		url = url + cityId;
		JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(url, BuckzyConstants.HTTP_GET, StringPool.BLANK, token);
		try {
			if(response.getInt("status")==200){
				branchArray = JSONFactoryUtil.createJSONArray(response.getString("data"));
			}
		} catch (JSONException e) {
			_log.error(e.getMessage());
		}
		
		return branchArray;
	}
    
    public JSONArray getCurrencyList(String token){
    	JSONArray currencyArray = JSONFactoryUtil.createJSONArray();
		JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(BuckzyConstants.GET_CURRENCY_LIST_URL, BuckzyConstants.HTTP_GET, StringPool.BLANK, token);
		try {
			if(response.getInt("status")==200){
				currencyArray = JSONFactoryUtil.createJSONArray(response.getString("data"));
			}
		} catch (JSONException e) {
			_log.error(e.getMessage());
		}
		
		return currencyArray;
    }
    
    public JSONArray getReceiverList(String token, long partyId){
    	JSONArray receiverArray = JSONFactoryUtil.createJSONArray();
    	String url = BuckzyConstants.GET_RECEIVER_LIST_URL.replace("partyId", String.valueOf(partyId));
    	JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(url, BuckzyConstants.HTTP_GET, StringPool.BLANK, token);
		try {
			if(response.getInt("status")==200){
				receiverArray = JSONFactoryUtil.createJSONArray(response.getString("data"));
			}
		} catch (JSONException e) {
			_log.error(e.getMessage());
		}
    	
    	return receiverArray;
    }
    
    public List<PaymentBean> getPaymentTransactionList(String token, int pageNo, int pageSize){
    	JSONArray paymentTransactionArray = JSONFactoryUtil.createJSONArray();
    	String url = BuckzyConstants.GET_ALL_PAYMENTS.replace("pageNo", String.valueOf(pageNo));
    	url = url.replace("pageSize", String.valueOf(pageSize));
    	JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(url, BuckzyConstants.HTTP_GET, StringPool.BLANK, token);
    	List<PaymentBean> paymentBeanList = new ArrayList<PaymentBean>();
    	try {
			if(response.getInt("status")==200){
				JSONObject responseObj = JSONFactoryUtil.createJSONObject(response.getString("data"));
				paymentTransactionArray = JSONFactoryUtil.createJSONArray(responseObj.getString("content"));
				paymentBeanList = getPaymentBeanList(paymentTransactionArray);
			}
		} catch (JSONException e) {
			_log.error(e.getMessage());
		}
    	
    	return paymentBeanList;
    }
    
    private List<PaymentBean> getPaymentBeanList(JSONArray paymentJsonArray){
    	List<PaymentBean> paymentBeanList = new ArrayList<PaymentBean>();
    	if(Validator.isNotNull(paymentJsonArray)){
    		for(int i=0;i<paymentJsonArray.length();i++){
    			JSONObject paymentObj = paymentJsonArray.getJSONObject(i);
    			paymentBeanList.add(converPaymentObjectToBean(paymentObj));
    		}
    	}
    	return paymentBeanList;
    }
    
    public JSONObject getPaymentDetail(String token, long paymentId){
    	JSONObject responseObj = JSONFactoryUtil.createJSONObject();
    	JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(BuckzyConstants.GET_PAYMENT_DETAIL_URL+paymentId, BuckzyConstants.HTTP_GET, StringPool.BLANK, token);
		try {
			if(response.getInt("status")==200){
				responseObj = JSONFactoryUtil.createJSONObject(response.getString("data"));
			}
		} catch (JSONException e) {
			_log.error(e.getMessage());
		}
    	return responseObj;
    }
    
    public PaymentBean converPaymentObjectToBean(JSONObject paymentObj){
    	
    	PaymentBean paymentBean = new PaymentBean();
    	
    	String receiverCountryCode = paymentObj.getString("rcvrcntrycd");
		if(Validator.isNotNull(receiverCountryCode)){
			receiverCountryCode = receiverCountryCode.toLowerCase();
		}
    	
		paymentBean.setLineitemid(paymentObj.getLong("lineitemid"));
		paymentBean.setPartyId(paymentObj.getLong("partyId"));
		paymentBean.setPymtmthdcd(paymentObj.getString("pymtmthdcd"));
		paymentBean.setRqstexecdt(paymentObj.getString("rqstexecdt"));
		paymentBean.setSndracctid(paymentObj.getLong("sndracctid"));
		paymentBean.setSndrrefid(paymentObj.getString("sndrrefid"));
		paymentBean.setSndrcntrycd(paymentObj.getString("sndrcntrycd"));
		paymentBean.setSndracctnr(paymentObj.getString("sndracctnr"));
		paymentBean.setSndrdbtrnm(paymentObj.getString("sndrdbtrnm"));
		paymentBean.setSndrbankid(paymentObj.getInt("sndrbankid"));
		paymentBean.setSndrcurrcd(paymentObj.getString("sndrcurrcd"));
		paymentBean.setSndrbankbic(paymentObj.getString("sndrbankbic"));
		paymentBean.setSndrinstramt(paymentObj.getDouble("sndrinstramt"));
		paymentBean.setAmtfxrate(paymentObj.getDouble("amtfxrate"));
		paymentBean.setRcvrcurrcd(paymentObj.getString("rcvrcurrcd"));
		paymentBean.setRcvramt(paymentObj.getDouble("rcvramt"));
		paymentBean.setRcvrbankid(paymentObj.getInt("rcvrbankid"));
		paymentBean.setRcvrbankbic(paymentObj.getString("rcvrbankbic"));
		paymentBean.setRcvrid(paymentObj.getLong("rcvrid"));
		paymentBean.setRcvremail(paymentObj.getString("rcvremail"));
		paymentBean.setRcvrmobilenr(paymentObj.getString("rcvrmobilenr"));
		paymentBean.setRcvrnm(paymentObj.getString("rcvrnm"));
		paymentBean.setRcvracctnr(paymentObj.getString("rcvracctnr"));
		paymentBean.setRcvrcntrycd(receiverCountryCode);    			
		paymentBean.setRcvracctid(paymentObj.getLong("rcvracctid"));
		paymentBean.setPurpofpymt(paymentObj.getString("purpofpymt"));
		paymentBean.setPurpcd(paymentObj.getString("purpcd"));
		paymentBean.setRcvrbankbranchid(paymentObj.getInt("rcvrbankbranchid"));
		paymentBean.setCreatedDate(new Date(paymentObj.getLong("createdAt")));
		return paymentBean;
    }
    
    
    public JSONObject getExchangeRate(String token, String fromCurCode, String toCurCode){
    	JSONObject responseObj = JSONFactoryUtil.createJSONObject();
    	String url = BuckzyConstants.GET_EXCHANGE_URL.replace("FromCurCd", fromCurCode);
    	url = url.replace("ToCurCd", toCurCode);
    	JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(url, BuckzyConstants.HTTP_GET, StringPool.BLANK, token);
		try {
			if(response.getInt("status")==200){
				responseObj = JSONFactoryUtil.createJSONObject(response.getString("data"));
			}
		} catch (JSONException e) {
			_log.error(e.getMessage());
		}
		/*
		if(fromCurCode.equals("CAD") && toCurCode.equals("SGD")){
			JSONObject dummJson = JSONFactoryUtil.createJSONObject();
			dummJson.put("toCurrencyCode", "SGD");
			dummJson.put("convertionRate", 1);
			dummJson.put("fromCurrencyCode", "CAD");
			return dummJson;
		}*/
    	
    	return responseObj;
    }
    
    public JSONObject getBankDetail(String token, int bankId){
    	JSONObject responseObj = JSONFactoryUtil.createJSONObject();
    	String url = BuckzyConstants.GET_BANK_DETAIL + bankId;
    	JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(url, BuckzyConstants.HTTP_GET, StringPool.BLANK, token);
		try {
			if(response.getInt("status")==200){
				responseObj = JSONFactoryUtil.createJSONObject(response.getString("data"));
			}
		} catch (JSONException e) {
			_log.error(e.getMessage());
		}
    	
    	return responseObj;
    }
    
    public JSONObject getPartyByEmail(String token, String emailAddres){
    	JSONObject responseObj = JSONFactoryUtil.createJSONObject();
    	String url = BuckzyConstants.GET_PARTY_BY_EMAIL + emailAddres;
    	JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(url, BuckzyConstants.HTTP_GET, StringPool.BLANK, token);
		try {
			if(response.getInt("status")==200){
				responseObj = JSONFactoryUtil.createJSONObject(response.getString("data"));
			}
		} catch (JSONException e) {
			_log.error(e.getMessage());
		}
    	
    	return responseObj;
    }
    
    public JSONObject makePayment(String token, long senderPartyId, long senderAcntId, long receiverPartyId,
    		long receiverAcntId, String sendingCurCode, String receivingCurCode, float exchangeRate, double amount,
    		String purposeOfTransfer, String purposeCode, String instructionText) throws PortalException{
    	
    	JSONObject responseObj = JSONFactoryUtil.createJSONObject();
    	
    	JSONObject paramsJsonObj = JSONFactoryUtil.createJSONObject();
    	paramsJsonObj.put("sndrPartyId", senderPartyId);
    	paramsJsonObj.put("sndrAccntId", senderAcntId);
    	paramsJsonObj.put("rcvrPartyId", receiverPartyId);
    	paramsJsonObj.put("rcvrAccntId", receiverAcntId);
    	paramsJsonObj.put("sendingCurrCd", sendingCurCode);
    	paramsJsonObj.put("recevingCurrCd", receivingCurCode);
    	paramsJsonObj.put("exchangeRate", String.valueOf(exchangeRate));
    	paramsJsonObj.put("sendingAmount", amount);
    	paramsJsonObj.put("purposeOfTransfer", purposeOfTransfer);
    	paramsJsonObj.put("purposeCode", purposeCode);
    	paramsJsonObj.put("instructionText", instructionText);
    	String params = paramsJsonObj.toJSONString();
    	
    	JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(BuckzyConstants.MAKE_PAYMENT, BuckzyConstants.HTTP_POST, params, token);
    	JSONObject dataObj = JSONFactoryUtil.createJSONObject(response.getString("data"));
    	int status = response.getInt("status");
    	
	    if(status==200){
	    	dataObj.put("responseStatus", "success");
	    	responseObj =  dataObj;
	    }else{
	    	String errMsg = BuckzyCommonLocalServiceUtil.extractErrMsgFromJson(dataObj.getJSONObject("errors"));
			PortalException pe = new PortalException(errMsg);
			throw pe;
	    }
    	return responseObj;
    }
    
    public JSONObject getCreditCardDetail(String token, String cardNumber){
    	
    	JSONObject creditCardJsonObject = JSONFactoryUtil.createJSONObject();
		
		JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(BuckzyConstants.GET_CARD_DETAIL+cardNumber, BuckzyConstants.HTTP_GET, StringPool.BLANK, token);
		
		try {
			if(response.getInt("status")==200){
				creditCardJsonObject = JSONFactoryUtil.createJSONObject(response.getString("data"));
			}
		} catch (JSONException e) {
			_log.error(e.getMessage());
		}
		
		return creditCardJsonObject;
    }
    
    public boolean verifyOTP(String email, String password, String verificationCode){
    	
    	String token = StringPool.BLANK;
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try{
			HttpPost postRequest = new HttpPost(BuckzyConstants.BASE_URL+BuckzyConstants.LOGIN_POST_URL);
	    	postRequest.setHeader("Content-type", "application/json");
	    	
	    	JSONObject paramsJsonObj = JSONFactoryUtil.createJSONObject();
			paramsJsonObj.put("email", email);
			paramsJsonObj.put("password", password);
			paramsJsonObj.put("otp", verificationCode);
			
			String params = paramsJsonObj.toJSONString();
			
	    	StringEntity stringEntity =new StringEntity(params);
			postRequest.setEntity(stringEntity);
	        
			HttpResponse response = httpClient.execute(postRequest);
				
			int statusCode = response.getStatusLine().getStatusCode();
	        if (statusCode == 200){	
	        	return true;
	        }else{
	        	return false;
	        }
		}catch(Exception e){
			httpClient.getConnectionManager().shutdown();
		}
		finally {
			httpClient.getConnectionManager().shutdown();
		}
		
		return false;
    }
	
    public boolean resendOTP(String email, String password){
    	
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try{
			HttpPost postRequest = new HttpPost(BuckzyConstants.BASE_URL+BuckzyConstants.POST_RESEND_OTP);
	    	postRequest.setHeader("Content-type", "application/json");
	    	
	    	JSONObject paramsJsonObj = JSONFactoryUtil.createJSONObject();
			paramsJsonObj.put("email", email);
			paramsJsonObj.put("password", password);
			
			String params = paramsJsonObj.toJSONString();
			
	    	StringEntity stringEntity =new StringEntity(params);
			postRequest.setEntity(stringEntity);
	        
			HttpResponse response = httpClient.execute(postRequest);
				
			int statusCode = response.getStatusLine().getStatusCode();
			
	        if (statusCode == 204){	
	        	return true;
	        }else{
	        	return false;
	        }
		}catch(Exception e){
			httpClient.getConnectionManager().shutdown();
		}
		finally {
			httpClient.getConnectionManager().shutdown();
		}
		
		return false;
		
    }
	
    
    public String getRegistrationOTP(long userId){
    	
		String url = BuckzyConstants.GET_OTP_DETAIL.replace("userId", String.valueOf(userId));
		JSONObject response = BuckzyCommonLocalServiceUtil.getAPIResponse(url, BuckzyConstants.HTTP_GET, StringPool.BLANK, "");
		String otp = StringPool.BLANK;
			if(response.getInt("status")==200){
				otp = response.getString("data");
			}
		
		return otp;
    }
    
    
    public void sendOTPMail(String emailAddress, String userName, String otp, String mobileNumber){
		
		String subject = "Buckzy Portal Email Verification OTP";
		String fromMail = "no-reply@buckzy.com";
		
		try {
			
			InternetAddress from = new InternetAddress(fromMail);
			InternetAddress[] to =new InternetAddress[1];
			to[0] = new InternetAddress(emailAddress);
			
			String body = "Hi " + userName + ", <br/><br/>";
			body+= " You have just registered in Buckzy Portal. We need verified your mobile number so we have sent you OTP verification code to your registered mobile : <b>" + mobileNumber + "</b> </br><br/>";
			body+="  In case if you will not get otp you can use this otp for mobile verification : <b>" + otp + "</b> </br><br/>";
			body+=" Thank you, <br/>";
			body+=" Team Buckzy";
			
			MailMessage mailMessage = new MailMessage(from, subject, body, true);
			
			mailMessage.setTo(to);
			MailServiceUtil.sendEmail(mailMessage);
		} catch (AddressException e) {
			_log.error(e);
		}
		
    	
    }
    
    
	public long getDefaultSiteGroupId(){
		long guestGroupId = 0l;
		final long companyId = PortalUtil.getDefaultCompanyId();
		try {
			guestGroupId = GroupLocalServiceUtil.getGroup(companyId, BuckzyConstants.BUCKZY_DEFAULT_SITE).getGroupId();
		} catch (PortalException e) {
			_log.error(e);
		}
		return guestGroupId;
	}
	
	public String encryptPassword(String password) {
		
		String encryptedPass =StringPool.BLANK;
		
		try{
		final Cipher encryptCipher = Cipher.getInstance("AES");	        				
		encryptCipher.init(Cipher.ENCRYPT_MODE, generateMySQLAESKey("test", "UTF-8"));
		encryptedPass = new String(Hex.encodeHex(encryptCipher.doFinal("Sandip@123".getBytes("UTF-8"))));
		}catch(InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e){
			_log.error(e);
		}
		return encryptedPass;
	}
	
	public String dedcryptPassword(String encryptedPass){
		String pass = StringPool.BLANK;
		try{
			final Cipher decryptCipher = Cipher.getInstance("AES");	        				
			decryptCipher.init(Cipher.DECRYPT_MODE, generateMySQLAESKey("test", "UTF-8"));
			pass  = new String(decryptCipher.doFinal(Hex.decodeHex(encryptedPass.toCharArray())));
		}catch(InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException |  BadPaddingException | DecoderException e){
			
		}
		return pass;
	}
	
	public String extractErrMsgFromJson(JSONObject erroObj){
		String userErrMsg = StringPool.BLANK;
		if(Validator.isNotNull(erroObj)){
			Iterator<String> keys = erroObj.keys();	
			while(keys.hasNext()) {
				String key = (String)keys.next();
				JSONArray msgArray = erroObj.getJSONArray(key);
				if(Validator.isNotNull(msgArray) && msgArray.length()>0){
					userErrMsg = (String)msgArray.getJSONObject(0).get("message");
					break;
				}
			}
		}
		return userErrMsg;
	}
	
	private static SecretKeySpec generateMySQLAESKey(final String key, final String encoding) {
		try {
			final byte[] finalKey = new byte[16];
			int i = 0;
			for(byte b : key.getBytes(encoding))
				finalKey[i++%16] ^= b;			
			return new SecretKeySpec(finalKey, "AES");
		} catch(UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
}