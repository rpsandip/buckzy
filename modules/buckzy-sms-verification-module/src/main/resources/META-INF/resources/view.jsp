<%@ include file="/init.jsp" %>
<%@page import="com.liferay.portal.kernel.util.PropsUtil" %>

<portlet:actionURL var="goHomePageURL" name="/go_home_page">
</portlet:actionURL>

<div style="max-width: 540px; margin: 0 auto; margin-top: 45px;">
	<div class="pin-detail" style="border: 1px solid #DB2222; outline: #E8E8E8 solid 3px; padding: 75px 35px 55px 35px;">
			<div style="color:red;" class="err-msg1"></div>
			<div id="newMobRegistration">
				<div class="col-sm-12 padding-0 margin-0 margin-top-10">
	                     You have not registered your mobile number, Please enter number with country code We will send you verification code and register your number.
	            </div>
	            <div class="col-sm-12 margin-top-10" style="background-color: #FFFFFF; padding:45px 20px 20px 20px; ">
	                 <div class="col-xs-3">
	                         <input type="text" name="newMobContryCode"  id ="newMobContryCode"  style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 100px;" placeholder="Country Code" maxlength="3"/>
	                 </div>
	                 <div class="col-xs-6">
	                         <input type="text" name="newMobNo"  id ="newMobNo"  style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 200px;" placeholder="Mobile No" maxlength="10"/>
	                 </div>
	                  <div class="col-sm-3  registerNewMobileBtn" style="font-size: 10px; width: 100px;">
	              		 <div class="new-transfer">Send</div>
	           		  </div>
	             </div>
             </div>
			<div class="row padding-0 margin-0 padding-10">
                 <div class="col-sm-12 padding-0 margin-0" style="margin-bottom: 7px; font-size: 18px;">Enter your PIN</div>
                 <div class="col-sm-12 padding-0 margin-0 margin-top-10" style="font-size: 10px;">
                     A pin number has been sent to you on your mobile number <span id="mobile-num"></span>. Once you have verified, your digital profile will be activated.
                 </div>
                 <div class="col-sm-12 padding-0 margin-0 margin-top-10" style="font-size: 10px;">
                     Enter the 4-digit code from the Mobile:
                 </div>
                                 
                 
                 <div class="col-sm-12 margin-top-10" style="background-color: #FFFFFF; padding:45px 20px 20px 20px; ">
                     <div class="col-xs-2">
                         <input type="text" name="verificationCode1"  id ="verificationCode1"  style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 30px;" maxlength="1"/>
                     </div>
                     <div class="col-xs-2">
                         <input type="text" name="verificationCode2" id ="verificationCode2"  style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 30px;" maxlength="1"/>
                     </div>
                     <div class="col-xs-2">
                         <input type="text" name="verificationCode3" id ="verificationCode3"  style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 30px;" maxlength="1"/>
                     </div>
                     <div class="col-xs-2">
                         <input type="text" name="verificationCode4" id ="verificationCode4"  style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 30px;" maxlength="1"/>
                     </div>
                 </div>
                 <!-- <div class="col-sm-12 margin-top-15" style="font-size: 12px;">
                     <aui:button type="button" value="Submit Registration" style="width: 700px;" cssClass="submitRegistrationBtn new-transfer btn btn-red"/>
                 </div> -->
                 <div class="col-sm-12 margin-top-15 submitLogInBtn" style="font-size: 12px; width: 700px;">
              		 <div class="new-transfer" style="margin-left:25px !important;">Submit</div>
           		</div>
                 <div class="col-sm-12 padding-0 margin-0 margin-top-10" style="font-size: 10px;">
                     If you haven't received the message within a few minutes we can <a id="resend-pin">Resend the PIN</a> or please check your spam folder.
                 </div>
             </div>
		</div>
		
		<div class="new-device-detail" style="border: 1px solid #DB2222; outline: #E8E8E8 solid 3px; padding: 75px 35px 55px 35px; display: none;">
			 <div style="color:red;" class="err-msg2"></div>
			<div class="row padding-0 margin-0 padding-10">
				  <h3>New Device PIN Verification</h3>
                 <div class="col-sm-12 padding-0 margin-0" style="margin-bottom: 7px; font-size: 18px;">Enter your PIN</div>
                 <div class="col-sm-12 padding-0 margin-0 margin-top-10" style="font-size: 10px;">
                     A pin number has been sent to you on your mobile number <span id="mobile-num"></span>. Once you have verified, your digital profile will be activated.
                 </div>
                 <div class="col-sm-12 padding-0 margin-0 margin-top-10" style="font-size: 10px;">
                     Enter the 4-digit code from the Mobile:
                 </div>
                 <div class="col-sm-12 margin-top-10" style="background-color: #FFFFFF; padding:45px 20px 20px 20px; ">
                     <div class="col-xs-2">
                         <input type="text" name="newDeviceVerificationCode1"  id ="newDeviceVerificationCode1"  style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 30px;" maxlength="1"/>
                     </div>
                     <div class="col-xs-2">
                         <input type="text" name="newDeviceVerificationCode2" id ="newDeviceVerificationCode2"  style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 30px;" maxlength="1"/>
                     </div>
                     <div class="col-xs-2">
                         <input type="text" name="newDeviceVerificationCode3" id ="newDeviceVerificationCode3"  style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 30px;" maxlength="1"/>
                     </div>
                     <div class="col-xs-2">
                         <input type="text" name="newDeviceVerificationCode4" id ="newDeviceVerificationCode4"  style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 30px;" maxlength="1"/>
                     </div>
                 </div>
                 <!-- <div class="col-sm-12 margin-top-15" style="font-size: 12px;">
                     <aui:button type="button" value="Submit Registration" style="width: 700px;" cssClass="submitRegistrationBtn new-transfer btn btn-red"/>
                 </div> -->
                 <div class="col-sm-12 margin-top-15 newDeviceSubmitLogInBtn" style="font-size: 12px; width: 700px;">
              		 <div class="new-transfer" style="margin-left:25px !important;">Submit</div>
           		</div>
                 <div class="col-sm-12 padding-0 margin-0 margin-top-10" style="font-size: 10px;">
                     If you haven't received the message within a few minutes we can <a id="new-device-resend-pin">Resend the PIN</a> or please check your spam folder.
                 </div>
             </div>
		</div>
</div>


<aui:script>

AUI().use('aui-io-request','liferay-portlet-url' ,'aui-base','aui-form-validator','node-event-simulate', function(A) {
	
	A.one(".new-device-detail").hide();
	A.one(".err-msg1").hide();
	A.one(".err-msg2").hide();
	A.one("#newMobRegistration").hide();
	
	 var smsAPIKey = '${smsAPIkey}';
	 var resendPinBtn = A.one('#resend-pin');
	 var newDeviceResentPinBtn= A.one('#new-device-resend-pin');
	 var submitLogInBtn = A.one(".submitLogInBtn");
	 var newDeviceSubmitBtn= A.one(".newDeviceSubmitLogInBtn");
	 var registerNewMobileBtn = A.one(".registerNewMobileBtn");
	 var firstVerificationCode;
	 var secondVerificationCode;
	 var newDeviceVerificationCode;
	 var secondNewDeviceVerificationCode;
	 var failedVerificationCount=0;
	 var mobileNo = '${mobileNo}';
	 var deviceInfo= '${deviceInfo}';
	 var validDevice=true;
	 var userId='${userId}';
	 var currentDiviceInfo;
	 var hasValidMobileNo = true;
	 var emailAddress ='${emailAddress}';
	 
	 
	 sendInitialSMSVerificationCode();
	 
	 submitLogInBtn.on('click', function(e) {
		    
		    if(failedVerificationCount<3){
		    	
			// verify verification code
			var digit1 = A.one("#verificationCode1").val();
			var digit2 = A.one("#verificationCode2").val();
			var digit3 = A.one("#verificationCode3").val();
			var digit4 = A.one("#verificationCode4").val();
			
			var userVerificationCode = digit1+''+digit2+''+digit3+''+digit4;
		
			
			userVerificationCode = parseInt(userVerificationCode);
			
			if((userVerificationCode==firstVerificationCode) || (userVerificationCode==secondVerificationCode)){

				var isValidDeviceLogin = checkForValidDeviceLogin();
					
				
				  // TODO : Need to make it in Login action command after login
					if(!isValidDeviceLogin){
						failedVerificationCount=0;
						A.one(".err-msg1").hide();
						addDeviceInfo();
						if(!hasValidMobileNo){
							mobileNo = A.one("#newMobContryCode").val() +A.one("#newMobNo").val();
							addMobileInfo();
						}
						sendNewDeviceVerificationCode();
					}else{
						if(!hasValidMobileNo){
							mobileNo = A.one("#newMobContryCode").val() +A.one("#newMobNo").val();
							addMobileInfo();
						}
						window.location.href= '${goHomePageURL}';
					}
				
			}else{
				A.one(".err-msg1").text('Please enter valid verification code');
		    	A.one(".err-msg1").show();
				failedVerificationCount++;
				if(failedVerificationCount==3){
					A.one("#verificationCode1").setAttribute('disabled');
					A.one("#verificationCode2").setAttribute('disabled');
					A.one("#verificationCode3").setAttribute('disabled');
					A.one("#verificationCode4").setAttribute('disabled');
					A.one("#newMobContryCode").setAttribute('disabled');
					A.one("#newMobNo").setAttribute('disabled');
					A.one(".new-transfer").remove();
					lockUser();
				}
				return;
			}
		 }else{
			 lockUser();
		 }
	 });
	 
	 newDeviceSubmitBtn.on('click', function(e) {
		 
		    if(failedVerificationCount<3){
			
			// verify verification code
			var digit1 = A.one("#newDeviceVerificationCode1").val();
			var digit2 = A.one("#newDeviceVerificationCode2").val();
			var digit3 = A.one("#newDeviceVerificationCode3").val();
			var digit4 = A.one("#newDeviceVerificationCode4").val();
			var userVerificationCode = digit1+''+digit2+''+digit3+''+digit4;
			userVerificationCode = parseInt(userVerificationCode);
			
			if((userVerificationCode==newDeviceVerificationCode) || (userVerificationCode==secondNewDeviceVerificationCode)){
				window.location.href= '${goHomePageURL}';
			}else{
				
				A.one(".err-msg2").text('Please enter valid verification code');
		    	A.one(".err-msg2").show();
				failedVerificationCount++;
				if(failedVerificationCount==3){
					A.one("#newDeviceVerificationCode1").setAttribute('disabled');
					A.one("#newDeviceVerificationCode2").setAttribute('disabled');
					A.one("#newDeviceVerificationCode3").setAttribute('disabled');
					A.one("#newDeviceVerificationCode4").setAttribute('disabled');
					lockUser();
				}
				return;
			}
		 }else{
			 lockUser();
		 }
	 });
	 
	 resendPinBtn.on('click', function(e) {
			
		 	if(mobileNo!=0){
			    secondVerificationCode = Math.floor(1000 + Math.random() * 9000);
				resendPinBtn.remove();
				
				var xhr = new XMLHttpRequest(),
			    body = JSON.stringify({"content": "Buckzy Portal Login Verification Code: "+secondVerificationCode, "to": [mobileNo]});
				xhr.open("POST",'https://platform.clickatell.com/messages',true);
				xhr.setRequestHeader("Content-Type", "application/json");
				xhr.setRequestHeader("Authorization", smsAPIKey);
				xhr.onreadystatechange = function(){
				    if (xhr.status == 202) {
				    	
				    }else if(xhr.readyState == 4  && xhr.status != 202){
				    	alert("Error: Failed to send mobile verificatin code, Please contact Adminstrator");
				    }
			    };
				xhr.send(body);
		 	}
		});
	 
	 newDeviceResentPinBtn.on('click', function(e) {
		    
		 	if(mobileNo!=''){
			 	secondNewDeviceVerificationCode = Math.floor(1000 + Math.random() * 9000);
			    newDeviceResentPinBtn.remove();
				
				var xhr = new XMLHttpRequest(),
			    body = JSON.stringify({"content": "Buckzy Portal Device Verification Code: "+secondNewDeviceVerificationCode, "to": [mobileNo]});
				xhr.open("POST",'https://platform.clickatell.com/messages',true);
				xhr.setRequestHeader("Content-Type", "application/json");
				xhr.setRequestHeader("Authorization", smsAPIKey);
				xhr.onreadystatechange = function(){
				    if (xhr.status == 202) {
				    	
				    }else if(xhr.readyState == 4  && xhr.status != 202){
				    	alert("Error: Failed to send new device mobile verificatin code, Please contact Adminstrator");
				    }
			    };
				xhr.send(body);
		 	}
		});
	
	 
	 registerNewMobileBtn.on('click', function(e) {
		 
		 firstVerificationCode =  Math.floor(1000 + Math.random() * 9000);
		 var newMobCountryCode = A.one("#newMobContryCode").val();
		 var newMobNo = A.one("#newMobNo").val();
		 var xhr = new XMLHttpRequest(),
		    body = JSON.stringify({"content": "Buckzy Portal Device Verification Code: "+firstVerificationCode, "to": [newMobCountryCode+newMobNo]});
			xhr.open("POST",'https://platform.clickatell.com/messages',true);
			xhr.setRequestHeader("Content-Type", "application/json");
			xhr.setRequestHeader("Authorization", smsAPIKey);
			xhr.onreadystatechange = function(){
			    if (xhr.status == 202) {
			    	
			    }else if(xhr.readyState == 4  && xhr.status != 202){
			    	alert("Error: Failed to send new device mobile verificatin code, Please contact Adminstrator");
			    }
		    };
			xhr.send(body);
	 });
	 
	 $('#verificationCode1,#verificationCode2,#verificationCode3,#verificationCode4,#newDeviceVerificationCode1,#newDeviceVerificationCode,#newDeviceVerificationCode3,#newDeviceVerificationCode4').bind('keyup paste', function(){
 		this.value = this.value.replace(/[^0-9]/g, '');
 		if(parseInt(this.value)>10){
 			this.value = '';
 		}
     });
	 
	 $('#newMobContryCode,#newMobNo').bind('keyup paste', function(){
	 		this.value = this.value.replace(/[^0-9]/g, '');	 		
	     });
	 
	 
	 function sendInitialSMSVerificationCode(){
	    	
		 if(mobileNo!=""){
			 hasValidMobileNo = true;
		     // Send Initial SMS Verification Code
			firstVerificationCode =  Math.floor(1000 + Math.random() * 9000);
			var xhr = new XMLHttpRequest(),
		    body = JSON.stringify({"content": "Buckzy Portal Login Verification Code: "+firstVerificationCode, "to": [mobileNo]});
			xhr.open("POST",'https://platform.clickatell.com/messages',true);
			xhr.setRequestHeader("Content-Type", "application/json");
			xhr.setRequestHeader("Authorization", smsAPIKey);
			xhr.onreadystatechange = function(){
			    if (xhr.status == 202) {
			    	//A.one(".pin-detail").show();
					//A.one("#"+logInFm).hide();
					//A.one(".err-msg").hide();
					A.one("#mobile-num").text(mobileNo);
			    }else if(xhr.readyState == 4  && xhr.status != 202){
			    	alert("Error: Failed to send mobile verificatin code. Please contact Administrator");
			    }
		    };
			xhr.send(body);
		 }else{
			   A.one("#newMobRegistration").show();
			   hasValidMobileNo=false;
		 }
		
	 }
	 
	 function detectDeviceInfo(){
	    	 
	    	 console.log(navigator.userAgent);
	    	 var browserName = getBrowserDetail();
	    	 var isMobile = isMobileBrowser();
	    	 var osName = getOSDetail();
	    	 
	    	 var deviceInfo={
	    		"isMobile":isMobile,
	    		"browser" : browserName,
	    		"OS": osName
	    	 };
	    	
	    	 console.log("deviceInfo->"+ deviceInfo.isMobile + " " + deviceInfo.browser + " " + deviceInfo.OS);
	    	 return deviceInfo;
	    	
	     }
	     
	     function isMobileBrowser(){
	    	 var isMobile=false;
	    	 var mobile = ['iphone','ipad','android','blackberry','nokia','opera mini','windows mobile','windows phone','iemobile']; 
	    	 for (var i in mobile){ 
	    		 if (navigator.userAgent.toLowerCase().indexOf(mobile[i].toLowerCase()) > 0){
	    			 isMobile =  true;
	    		 }
	    	 }
			 return isMobile;
	     }
	     
	     function getOSDetail(){
	    	 var OSName="";
	    	 if (navigator.appVersion.indexOf("Win")!=-1) OSName="Windows";
	    	 if (navigator.appVersion.indexOf("Mac")!=-1) OSName="MacOS";
	    	 if (navigator.appVersion.indexOf("X11")!=-1) OSName="UNIX";
	    	 if (navigator.appVersion.indexOf("Linux")!=-1) OSName="Linux";
			 return OSName;
	     }
	     
	     function getBrowserDetail(){
	    	 
	    	 var nVer = navigator.appVersion;
	    	 var nAgt = navigator.userAgent;
	    	 var browserName  = navigator.appName;
	    	 var nameOffset,verOffset,ix;

	    	 // In Opera, the true version is after "Opera" or after "Version"
	    	 if ((verOffset=nAgt.indexOf("Opera"))!=-1) {
	    	  browserName = "Opera";
	    	 }
	    	 // In MSIE, the true version is after "MSIE" in userAgent
	    	 else if ((verOffset=nAgt.indexOf("MSIE"))!=-1) {
	    	  browserName = "Microsoft Internet Explorer";
	    	 }
	    	 // In Chrome, the true version is after "Chrome" 
	    	 else if ((verOffset=nAgt.indexOf("Chrome"))!=-1) {
	    	  browserName = "Chrome";
	    	 }
	    	 // In Safari, the true version is after "Safari" or after "Version" 
	    	 else if ((verOffset=nAgt.indexOf("Safari"))!=-1) {
	    	  browserName = "Safari";
	    	 }
	    	 // In Firefox, the true version is after "Firefox" 
	    	 else if ((verOffset=nAgt.indexOf("Firefox"))!=-1) {
	    	  browserName = "Firefox";
	    	 }
	    	 // In most other browsers, "name/version" is at the end of userAgent 
	    	 else if ( (nameOffset=nAgt.lastIndexOf(' ')+1) < 
	    	           (verOffset=nAgt.lastIndexOf('/')) ) 
	    	 {
	    	  browserName = nAgt.substring(nameOffset,verOffset);
	    	 }
	    	 
	    	 return browserName;
	     }
	     
	     function checkForValidDeviceLogin() {
	    	 currentDiviceInfo = detectDeviceInfo();
	    	 console.log("currentDiviceInfo->"+ currentDiviceInfo.isMobile + " " + currentDiviceInfo.browser + " " + currentDiviceInfo.OS);
	    	 var isValidDevice = false;
		    	 var deviceJsonArray = JSON.parse(deviceInfo);
		    	 console.log("deviceJsonArray->"+ deviceJsonArray);
		    	 console.log("deviceJsonArray.length->" + deviceJsonArray.length);
		    	 for(var i=0;i<deviceJsonArray.length;i++){
		    		 var deviceObj = deviceJsonArray[i];
		    		 console.log(deviceObj.isMobile + " " + currentDiviceInfo.isMobile);
		    		 if((deviceObj.isMobile==currentDiviceInfo.isMobile) && (deviceObj.browser==currentDiviceInfo.browser) && (deviceObj.OS==currentDiviceInfo.OS)){
		    			 isValidDevice = true;
		    		 }
		    	 }
	    	 console.log('isValidDevice->' + isValidDevice);
	    	 return isValidDevice;
	     }
	     
	     function addDeviceInfo(){
	    	 
	    	 var resourceURL = Liferay.PortletURL.createResourceURL();
		     resourceURL.setResourceId('/add-device-info');
		     resourceURL.setParameter('emailAddress',emailAddress);
		     resourceURL.setParameter('deviceInfo', JSON.stringify(currentDiviceInfo));
		     resourceURL.setPortletId("<%=themeDisplay.getPortletDisplay().getId() %>");
		     
		     
		     A.io.request(resourceURL.toString(),{
					dataType: 'json',
					method: 'GET',
					on: {
					success: function() {
						var response=this.get('responseData');
						console.log("New Device add response ->" + response.status);
					  }
					}
				}); 
	    	 
	     }
	     
		function addMobileInfo(){
			
	    	 var resourceURL = Liferay.PortletURL.createResourceURL();
		     //resourceURL.setPortletId('com_buckzy_registration_module_portlet_portlet_BuckzyRegistrationModulePortlet');
		     resourceURL.setResourceId('/add-mobile-info');
		     resourceURL.setParameter('emailAddress', emailAddress);
		     resourceURL.setParameter('countryCode', A.one("#newMobContryCode").val());
		     resourceURL.setParameter('mobileNo', A.one("#newMobNo").val());
		     resourceURL.setPortletId("<%=themeDisplay.getPortletDisplay().getId() %>");
		     
		     
		     A.io.request(resourceURL.toString(),{
					dataType: 'json',
					method: 'GET',
					on: {
					success: function() {
						var response=this.get('responseData');
						console.log("New Device add response ->" + response.status);
						hasValidMobileNo = true;
					  }
					}
				}); 
	    	 
	     }
	     
	     function sendNewDeviceVerificationCode(){
	    	 
	    	 	if(mobileNo!=''){
		    	    // Send SMS Verification Code
					newDeviceVerificationCode =  Math.floor(1000 + Math.random() * 9000);
					var xhr = new XMLHttpRequest(),
				    body = JSON.stringify({"content": "Buckzy Portal Device Verification Code: "+newDeviceVerificationCode, "to": [mobileNo]});
					xhr.open("POST",'https://platform.clickatell.com/messages',true);
					xhr.setRequestHeader("Content-Type", "application/json");
					xhr.setRequestHeader("Authorization", smsAPIKey);
					xhr.onreadystatechange = function(){
					    if (xhr.status == 202) {
					    	A.one(".pin-detail").hide();
							A.one(".new-device-detail").show();
					    }else if(xhr.readyState == 4  && xhr.status != 202){
					    	alert("Error: Failed to send new device mobile verificatin code, Please contact Adminstrator");
					    }
				    };
					xhr.send(body);
	    	 	}
	     }
	     
	     
	     function lockUser(screen){
	    	 
	    	 var resourceURL = Liferay.PortletURL.createResourceURL();
		     //resourceURL.setPortletId('com_buckzy_registration_module_portlet_portlet_BuckzyRegistrationModulePortlet');
		     resourceURL.setResourceId('/lock-user');
		     resourceURL.setPortletId("<%=themeDisplay.getPortletDisplay().getId() %>");
		     resourceURL.setParameter('userId',userId);
		     
		     
	    	 A.io.request(resourceURL.toString(),{
					dataType: 'json',
					method: 'GET',
					on: {
					success: function() {
						var response=this.get('responseData');
						var isLocked   = response.lock;
						if(isLocked){
							if(screen!='new-device'){
								A.one(".err-msg1").show();
								A.one(".err-msg1").text('Opss!! You reached maximum limit of security code. Your account has ben locked, Please contact Administrator');
							}else{
								A.one(".err-msg2").show();
								A.one(".err-msg2").text('Opss!! You reached maximum limit of security code. Your account has ben locked, Please contact Administrator');
							}
						}
					  }
					}
				});
	     }
	// Socail Button Changes :: END
});

</aui:script>