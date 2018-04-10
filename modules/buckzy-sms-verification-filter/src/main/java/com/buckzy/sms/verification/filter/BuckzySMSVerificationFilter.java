package com.buckzy.sms.verification.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

@Component(
		  immediate = true,
		  property = {
		      "servlet-context-name=",
		      "servlet-filter-name=SMS Verification Filter",
		      "dispatcher=FORWARD",
		      "dispatcher=REQUEST",
		      "url-pattern=/group/guest/*",
		      "url-regex-ignore-pattern=^/html/.+\\.(css|gif|html|ico|jpg|js|png)(\\?.*)?$"
		  },
		  service = Filter.class
		)
public class BuckzySMSVerificationFilter extends BaseFilter{

	@Override
	 protected void processFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	 throws Exception {

	 User user = (User) request.getAttribute(WebKeys.USER);
	 
	 String requestURI =  request.getRequestURI();
	 
	 if(requestURI.indexOf("group")>=0){
		 // Check user is locked or not
		 if(user.isLockout()){
			 response.sendRedirect("/c/portal/logout");
		 }
		 
		 
		 // Check for Email address verification
		 if(!user.getEmailAddressVerified()){
			 response.sendRedirect("/web/guest/email-verification");
		 }
		 
		 // Check SMS is verified or not
		 Object smsVerified = request.getSession().getAttribute("smsVerified");
		 
		 if(Validator.isNotNull(smsVerified) && (Boolean)smsVerified){
			 super.processFilter(request, response, filterChain);
		 }else{
			 response.sendRedirect("/web/guest/sms-verification");
		 }
	 }else{
		 super.processFilter(request, response, filterChain);
	 }
	 
	 }

	 @Override
	 protected Log getLog() {
	 return _log;
	 }

	 private static final Log _log = LogFactoryUtil.getLog(BuckzySMSVerificationFilter.class);

}
