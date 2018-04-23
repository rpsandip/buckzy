<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp"%>
<%@page import="com.liferay.portal.kernel.util.PropsUtil"%>
<%@page import="com.liferay.portal.kernel.model.Layout"%>
<%@page
	import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.exception.PortalException"%>
<%@ page import="com.liferay.portal.kernel.servlet.SessionMessages"%>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil"%>

<style>
.lexicon-icon, .lexicon-icon-asterisk{
	display: none;
}
.portlet-content{
	background: none !important;
}

.input-group .form-control{
	    border-bottom-right-radius: unset;
    border-top-right-radius: unset;
}
</style>

<%
	String fmName = "";
	SessionMessages.add(renderRequest,
			PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);

	HttpServletRequest httpRequestCustom = PortalUtil
			.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));

	String restAPIErr = httpRequestCustom.getParameter("login");

	if (Validator.isNotNull(restAPIErr) && restAPIErr.equals("invalid")) {
		SessionErrors.add(renderRequest, "rest-api-failed");
	}

	long registrationPagePlid = 0;
	try {
		Layout registrationPageLauout = LayoutLocalServiceUtil
				.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), false, "/registration");
		registrationPagePlid = registrationPageLauout.getPlid();
	} catch (PortalException e) {

		e.printStackTrace();
	}
%>
<c:choose>
	<c:when test="<%=themeDisplay.isSignedIn()%>">

		<%
			String signedInAs = HtmlUtil.escape(user.getFullName());

					if (themeDisplay.isShowMyAccountIcon() && (themeDisplay.getURLMyAccount() != null)) {
						String myAccountURL = String.valueOf(themeDisplay.getURLMyAccount());

						signedInAs = "<a class=\"signed-in\" href=\"" + HtmlUtil.escape(myAccountURL) + "\">"
								+ signedInAs + "</a>";
					}

					// Logout the user if he comes to Sing In page with login.
					PortalUtil.getHttpServletResponse(renderResponse).sendRedirect("/c/portal/logout");
		%>

		<liferay-ui:message arguments="<%=signedInAs%>"
			key="you-are-signed-in-as-x" translateArguments="<%=false%>" />
	</c:when>
	<c:otherwise>

		<%
			String formName = "loginForm";

					if (windowState.equals(LiferayWindowState.EXCLUSIVE)) {
						formName += "Modal";
					}

					fmName = formName;
					String redirect = ParamUtil.getString(request, "redirect");

					String login = LoginUtil.getLogin(request, "login", company);
					String password = StringPool.BLANK;
					boolean rememberMe = ParamUtil.getBoolean(request, "rememberMe");

					if (Validator.isNull(authType)) {
						authType = company.getAuthType();
					}
		%>

		<portlet:actionURL name="/login/login"
			secure="<%=PropsValues.COMPANY_SECURITY_AUTH_REQUIRES_HTTPS || request.isSecure()%>"
			var="loginURL">
			<portlet:param name="mvcRenderCommandName" value="/login/login" />
		</portlet:actionURL>
		<div style="max-width: 900px; margin: 0 auto; margin-top: 45px;">

			<aui:form action="<%=loginURL%>" cssClass="sign-in-form"
				method="post" name="<%=formName%>">
				<aui:input name="saveLastPath" type="hidden" value="<%=false%>" />
				<aui:input name="redirect" type="hidden" value="<%=redirect%>" />
				<aui:input name="doActionAfterLogin" type="hidden"
					value="<%=portletName.equals(PortletKeys.FAST_LOGIN) ? true : false%>" />
				<aui:input type="hidden" name="deviceInfo" />
				<div class="inline-alert-container lfr-alert-container"></div>



				<c:choose>
					<c:when
						test='<%=SessionMessages.contains(request, "passwordSent")%>'>

						<div class="alert alert-success">
							<liferay-ui:message
								key="your-password-was-sent-to-the-provided-email-address" />
						</div>
					</c:when>
					<c:when
						test='<%=SessionMessages.contains(request, "userAdded")%>'>

						<%
							String userEmailAddress = (String) SessionMessages.get(request, "userAdded");
												String userPassword = (String) SessionMessages.get(request, "userAddedPassword");
						%>

						<div class="alert alert-success">
							<c:choose>
								<c:when
									test="<%=company.isStrangersVerify() || Validator.isNull(userPassword)%>">
									<liferay-ui:message key="thank-you-for-creating-an-account" />

									<c:if test="<%=company.isStrangersVerify()%>">
										<liferay-ui:message arguments="<%=userEmailAddress%>"
											key="your-email-verification-code-was-sent-to-x"
											translateArguments="<%=false%>" />
									</c:if>
								</c:when>
								<c:otherwise>
									<liferay-ui:message arguments="<%=userPassword%>"
										key="thank-you-for-creating-an-account.-your-password-is-x"
										translateArguments="<%=false%>" />
								</c:otherwise>
							</c:choose>

							<c:if
								test="<%=PrefsPropsUtil.getBoolean(company.getCompanyId(),
											PropsKeys.ADMIN_EMAIL_USER_ADDED_ENABLED)%>">
								<liferay-ui:message arguments="<%=userEmailAddress%>"
									key="your-password-was-sent-to-x"
									translateArguments="<%=false%>" />
							</c:if>
						</div>
					</c:when>
					<c:when
						test='<%=SessionMessages.contains(request, "userPending")%>'>

						<%
							String userEmailAddress = (String) SessionMessages.get(request, "userPending");
						%>

						<div class="alert alert-success">
							<liferay-ui:message arguments="<%=userEmailAddress%>"
								key="thank-you-for-creating-an-account.-you-will-be-notified-via-email-at-x-when-your-account-has-been-approved"
								translateArguments="<%=false%>" />
						</div>
					</c:when>
				</c:choose>

				<liferay-ui:error key="rest-api-failed"
					message="authentication-failed" />
				<liferay-ui:error exception="<%=AuthException.class%>"
					message="Authentication Failed. Either Email or Password is incorrect" />
				<liferay-ui:error exception="<%=CompanyMaxUsersException.class%>"
					message="unable-to-log-in-because-the-maximum-number-of-users-has-been-reached" />
				<liferay-ui:error
					exception="<%=CookieNotSupportedException.class%>"
					message="authentication-failed-please-enable-browser-cookies" />
				<liferay-ui:error exception="<%=NoSuchUserException.class%>"
					message="authentication-failed" />
				<liferay-ui:error exception="<%=PasswordExpiredException.class%>"
					message="your-password-has-expired" />
				<liferay-ui:error
					exception="<%=UserEmailAddressException.MustNotBeNull.class%>"
					message="please-enter-an-email-address" />
				<liferay-ui:error
					exception="<%=UserLockoutException.LDAPLockout.class%>"
					message="this-account-is-locked" />

				<liferay-ui:error
					exception="<%=UserLockoutException.PasswordPolicyLockout.class%>">

					<%
						UserLockoutException.PasswordPolicyLockout ule = (UserLockoutException.PasswordPolicyLockout) errorException;
					%>

					<liferay-ui:message arguments="<%=ule.user.getUnlockDate()%>"
						key="this-account-is-locked-until-x"
						translateArguments="<%=false%>" />
				</liferay-ui:error>

				<liferay-ui:error exception="<%=UserPasswordException.class%>"
					message="authentication-failed" />
				<liferay-ui:error
					exception="<%=UserScreenNameException.MustNotBeNull.class%>"
					message="the-screen-name-cannot-be-blank" />


				<div class="col-xs-12 border-1 country-idetification-img"
					style="background-size: cover; padding-left: 0px; padding-right: 0px; ">

					<div style="color: red;" class="err-msg"></div>
					<div class="col-sm-5"
						style="background-color: #FFFFFF; padding: 45px 35px 45px 35px; margin-left: 0px; margin-right: 0px;">
						<div class="col-sm-12 border-1"
							style="background-color: #FBFBFB; padding: 15px;">
							<div style="margin-bottom: 20px;">
								<img
									src='<%=themeDisplay.getPathThemeImages() + "/logo.png"%>' />
							</div>

							<%
								String loginLabel = null;

											if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
												loginLabel = "email-address";
											} else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
												loginLabel = "screen-name";
											} else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
												loginLabel = "id";
											}
							%>

							<div class="input-group">
								<aui:input cssClass="clearable form-control"
									placeholder="Email Address" style="font-size: 13px;"
									name="login" type="text" label="Email Address">
									<aui:validator name="required" />
									<aui:validator name="email" />
								</aui:input>
							</div>
							<div class="input-group" style="margin-top: 20px;">
								<aui:input name="password" placeholder="password" label="Password"
									cssClass="form-control" type="password"
									style="font-size: 13px; width: 260px;" value="<%=password%>">
									<aui:validator name="required" />
								</aui:input>
							</div>

							<aui:button-row>
								<aui:button cssClass="btn-red" id="loginBtn"
									style="margin:0 auto; max-width: 150px; margin-top: 20px;    width: 140px; margin-left: 60px; padding: 5px; text-align: center;" type="
									submit" value="sign-in" />
							</aui:button-row>

							<c:if
								test="<%=company.isAutoLogin() && !PropsValues.SESSION_DISABLED%>">
								<div class="col-xs-12 padding-0 margin-top-10"
									style="font-size: 12px; color: #171D93;">
									<div class="checkbox">
										<aui:input checked="<%=rememberMe%>"
											style="margin-top: 0px;" name="rememberMe" type="checkbox" />
									</div>
								</div>
							</c:if>
							<div class="col-xs-12 padding-0 margin-top-10"
								style="font-size: 12px; color: #171D93;">
								<a href="#" id="forgot-pass" style="color: #171D93;"> Forgot Your Password? </a>
							</div>
						</div>
						
						<div class="col-sm-12 padding-0 padding-top-20" style="font-size: 12px; font-weight: bold; color: #171D93; text-align: center;">
                        	<div class="col-sm-6" style="padding-top: 7px;">
                            	Not a customer?
                       		</div> 
                       		 <div class="col-sm-6">
                            	<div class="btn-red" style="margin:0 auto; max-width: 150px;  padding: 5px; text-align: center;"> 
									<a style="color: #ffffff;" href="/web/guest/registration">Click to join</a>
								</div>
                        	</div>
                   		</div>
					</div>
					<div class="col-sm-7"

						style="padding-left: 30px; font-weight: bold; color: #FFFFFF; text-align: center; padding-top: 30px; padding-bottom: 30px;">
						Experience the Immediate Cross Border <br /> Money Transfer with
						Buckzy<br />
						<br /> Receive money within Minutes<br /> Available 24x7, 365 days

					</div>
				</div>

			</aui:form>


			<div class="forgot-password"
				style="border: 1px solid #DB2222; outline: #E8E8E8 solid 3px; padding: 0px 35px 55px 35px; display: none;">
				<div style="color: red;" class="err-msg2"></div>
				<div class="row padding-0 margin-0 padding-10">
					<h3>Forgot Password</h3>
					<div class="success-forgot-msg" style="color: green;"></div>
					<div class="error-forgot-msg" style="color: red;"></div>
					<div class="col-sm-12 padding-0 profile-detail"
						style="color: #9B9A9B;">
						<div class="col-sm-6">
							<aui:input name="forgot-email" label="Email Address"
								placeholder="Email Address"
								cssClass="form-control col-md-7 col-xs-6">
								<aui:validator name="required" />
								<aui:validator name="maxLength">70</aui:validator>
							</aui:input>
						</div>
					</div>
					<div class="col-sm-12 margin-top-15 forgotPasswordBtn"
						style="font-size: 12px;">
						<div class="new-transfer">Submit</div>
					</div>
				</div>
			</div>

		</div>

		<aui:script sandbox="<%=true%>">
			
		
		  var form = AUI.$(document.<portlet:namespace /><%= formName %>);

			form.on(
				'submit',
				function(event) {
					<c:if test="<%= Validator.isNotNull(redirect) %>">
						var redirect = form.fm('redirect');

						if (redirect) {
							var redirectVal = redirect.val();

							redirect.val(redirectVal + window.location.hash);
						}
					</c:if>

					submitForm(form);
				}
			);

			form.fm('password').on(
				'keypress',
				function(event) {
					Liferay.Util.showCapsLock(event, '<portlet:namespace />passwordCapsLockSpan');
				}
			);
		
		</aui:script>
			
	
		</c:otherwise>
</c:choose>

<script>
jQuery.noConflict();
(function($) {
    $(function() {
    	
AUI().use('aui-io-request','liferay-portlet-url' ,'aui-base','aui-form-validator','node-event-simulate', function(A) {
	
	<c:if test="<%= !themeDisplay.isSignedIn() %>">
	
	// Social Button Changes :: START
	/* 
	var googleBtnCount=0;
	 var facebookBtnCount=0;
	 
	A.all('.navigation ul li a').each(function (node) {
	      var socialText = node.text().trim();
	     
	      if(socialText=="Facebook"){
	    	  if(facebookBtnCount==0){ 
	    	  	node.setAttribute('class', 'btn btn-block btn-social btn-facebook');
	    	    node.html('<span class="fa fa-facebook"></span> Sign in with Facebook');
	    	  }else{
	    		  node.remove();
	    	  }
	    	   facebookBtnCount++;
	      }else if(socialText=="Google"){
	    	  if(googleBtnCount===0){
	    	  	 node.setAttribute('class', 'btn btn-block btn-social btn-google');
	    	 	 node.html('<span class="fa fa-google-plus"></span> Sign in with Google+' + '&nbsp;');
	    	  }else{
	    		  node.remove();
	    	  }
	    	  googleBtnCount++;
	      }else if(socialText=="Forgot Password"){
	    	  node.text('');
	    	  //A.one("#forgot-pass").setAttribute("href", node.getAttribute('href'));
	      }else{
	    	  node.text('');
	      }
	  }); */
	
	 A.one("#<portlet:namespace />loginBtn").setAttribute('class', 'btn btn-red');
	 
	 </c:if>
	 
	 
	 A.one(".forgot-password").hide();
	 A.one(".success-forgot-msg").hide();
	 A.one(".error-forgot-msg").hide();
	 
	 var forgotPass = A.one("#forgot-pass");
	 var forgotPassBtn = A.one(".forgotPasswordBtn"); 
	 var logInFm ='<portlet:namespace />' + '<%=fmName%>';
	 
	 forgotPass.on('click', function(e) {
		 A.one("#"+logInFm).hide();
		 A.one(".forgot-password").show();
	 });
	 
	 forgotPassBtn.on('click', function(e) {
		 
		 A.one(".success-forgot-msg").hide();
		 A.one(".error-forgot-msg").hide();
		 
		 var resourceURL = Liferay.PortletURL.createResourceURL();
	     resourceURL.setPortletId('com_buckzy_registration_module_portlet_portlet_BuckzyRegistrationModulePortlet');
	     resourceURL.setResourceId('/forgot-password');
	     resourceURL.setPlid('<%=registrationPagePlid%>');
	     resourceURL.setParameter('emailAddress',A.one("#<portlet:namespace/>forgot-email").val());
	     
	     A.io.request(resourceURL.toString(),{
				dataType: 'json',
				method: 'GET',
				on: {
				success: function() {
					var response=this.get('responseData');
					
					console.log("response.sentEmail->" +response.sentEmail);
					
					if(response.sentEmail){
						A.one(".success-forgot-msg").text("Change password link has been sent to registered email address.");
						A.one(".success-forgot-msg").show();
					}else{
						A.one(".error-forgot-msg").text(response.message);
						A.one(".error-forgot-msg").show();
					}
				}
				}
			});
	 });
	 
	// Socail Button Changes :: END
   });
   
//TODO: Need valid buckzy api key from ipstack
$.getJSON("https://api.ipify.org?format=json", function (data) {
	 var ipAddress = data.ip;
	 console.log("ipAddress->"+ ipAddress);
	 $.ajax({
		  url: "http://api.ipstack.com/"+ipAddress+"?access_key=b882e16f7138b81448cbbf2a7d2d020d",
		  cache: false,
		  success: function(result){
		    console.log(result);
		    geoCountryCode = result.country_code;
		    geoStateCode = result.region_code;
		    geoCity = result.city;

		    console.log("geoCountry_Code->" + geoCountryCode + 'geoStateCode->' + geoStateCode + " geoCity ->" + geoCity);
		    
		    if(geoCountryCode=="IN"){
		    	$('.country-idetification-img').css('background-image', "url('<%=themeDisplay.getPathThemeImages() + "/in.jpg"%>')");
		    }else if(geoCountryCode=="US"){
		    	$('.country-idetification-img').css('background-image', "url('<%=themeDisplay.getPathThemeImages() + "/us.jpg"%>')");
		    }else if(geoCountryCode=="UK"){
			    $('.country-idetification-img').css('background-image', "url('<%=themeDisplay.getPathThemeImages() + "/uk.jpg"%>')");
			}else{
				$('.country-idetification-img').css('background-image', "url('<%=themeDisplay.getPathThemeImages() + "/ca.jpg"%>')");
			}
		    
		  }
		});
	 
});

     });
})(jQuery);
</script> 