<%@ include file="/init.jsp" %>
<liferay-ui:success key="registration-success" message="registration-success"/>

<portlet:actionURL var="otpVerificationURL" name="/otp_verification">
</portlet:actionURL>

<liferay-ui:error key="not-verfied" message="not-verfied"/>
<liferay-ui:success key="verified" message="verified"/>


<h3>Registration - Mobile Verification</h3>
<aui:form name="otpVerificationFm" action="${otpVerificationURL}" cssClass="form-horizontal form-label-left">
   
<c:if test="${success }">
	<h4>Registration done successfully and you have verified your registered mobile number.
	Please check your inbox to get email verification link <a href="/web/guest"><b>Click here</b></a> to Login.</h4> 
</c:if>   
   
<div class="pin-detail" style="border: 1px solid #DB2222; outline: #E8E8E8 solid 3px; padding: 75px 35px 55px 35px;">
	<div class="row padding-0 margin-0 padding-10">
               <div class="col-sm-12 padding-0 margin-0" style="margin-bottom: 7px; font-size: 18px;">OTP - Enter your PIN</div>
               <div class="col-sm-12 padding-0 margin-0 margin-top-10" style="font-size: 12px;">
                   A pin number has been sent to you on your mobile number <span id="mobile-num"></span>. Once you have verified, your digital profile will be activated.
               </div>
               <div class="col-sm-12 padding-0 margin-0 margin-top-10" style="font-size: 12px;">
                   Enter the <span style="color: #a94442; font-size: 14;">6-digit</span> code from the Mobile:
               </div>
               <div class="col-sm-12 margin-top-10" style="background-color: #FFFFFF; padding:45px 20px 20px 20px; ">
                   <div class="col-xs-2">
                   	   <aui:input type="text" name="verificationCode1"  label="" style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 35px;">
			     			<aui:validator name="maxLength">1</aui:validator>
                   	   </aui:input>
                   </div>
                   <div class="col-xs-2">
                        <aui:input type="text" name="verificationCode2"  label="" style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 35px;">
			     			<aui:validator name="maxLength">1</aui:validator>
                   	   </aui:input>
                   </div>
                   <div class="col-xs-2">
                        <aui:input type="text" name="verificationCode3"  label="" style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 35px;">
			     			<aui:validator name="maxLength">1</aui:validator>
                   	   </aui:input>
                   </div>
                   <div class="col-xs-2">
                        <aui:input type="text" name="verificationCode4"  label="" style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 35px;">
			     			<aui:validator name="maxLength">1</aui:validator>
                   	   </aui:input>
                   </div>
                   <div class="col-xs-2">
                        <aui:input type="text" name="verificationCode5"  label="" style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 35px;">
			     			<aui:validator name="maxLength">1</aui:validator>
                   	   </aui:input>
                   </div>
                   <div class="col-xs-2">
                        <aui:input type="text" name="verificationCode6"  label="" style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 35px;">
			     			<aui:validator name="maxLength">1</aui:validator>
                   	   </aui:input>
                   </div>
                   <div style="color: #961622; font-size: 14px; font-weight: bold; padding-top: 10px;" id="otpErrMsg">
                   		
                   </div>
                   
               </div>
               <!-- <div class="col-sm-12 margin-top-15" style="font-size: 12px;">
                   <aui:button type="button" value="Submit Registration" style="width: 700px;" cssClass="submitRegistrationBtn new-transfer btn btn-red"/>
               </div> -->
               <div class="col-sm-12 margin-top-15 submitRegistrationBtn" style="font-size: 12px; width: 700px;">
            		 <div class="new-transfer">Submit</div>
         		</div>
               <div class="col-sm-12 padding-0 margin-0 margin-top-10" style="font-size: 12px;">
                   If you haven't received the message within a few minutes we can <a id="resend-pin"> Resend the PIN</a> or please check your spam folder.
               </div>
           </div>
</div>
<aui:input type="hidden" name="email" value="${email }"/>
<aui:input type="hidden" name="password" value="${partyPass }"/>

</aui:form>

<script>
jQuery.noConflict();
(function($) {
    $(function() {
    	
    	var pns = "<portlet:namespace/>";
		
    	AUI().use('aui-base','aui-form-validator',  'aui-io-request', function(A) {
    		
    		var submitRegistrationBtn = A.one('.submitRegistrationBtn');
    		var resendPinBtn = A.one('#resend-pin');
    		var otp = '${otp}';
    		var smsAPIKey = '${smsAPIkey}';
    		var mobileNo= '${mobileNo}';
    		
    		submitRegistrationBtn.on('click', function(e) {
    		
				A.one("#otpErrMsg").text('');
    			var formValidator = Liferay.Form.get('<portlet:namespace />otpVerificationFm').formValidator;
    			formValidator.validate();
    			var validOTP=true;
    			if(isNaN(parseInt(A.one('#<portlet:namespace/>verificationCode1').val())) || (parseInt(A.one('#<portlet:namespace/>verificationCode1').val())>9 && parseInt(A.one('#<portlet:namespace/>verificationCode1').val()) <0 ) ){
    				validOTP=false;
    			}if(isNaN(parseInt(A.one('#<portlet:namespace/>verificationCode2').val())) || (parseInt(A.one('#<portlet:namespace/>verificationCode2').val())>9 && parseInt(A.one('#<portlet:namespace/>verificationCode2').val()) <0 ) ){
    				validOTP=false;
    			}if(isNaN(parseInt(A.one('#<portlet:namespace/>verificationCode3').val())) || (parseInt(A.one('#<portlet:namespace/>verificationCode3').val())>9 && parseInt(A.one('#<portlet:namespace/>verificationCode3').val()) <0 ) ){
    				validOTP=false;
    			}if(isNaN(parseInt(A.one('#<portlet:namespace/>verificationCode4').val())) || (parseInt(A.one('#<portlet:namespace/>verificationCode4').val())>9 && parseInt(A.one('#<portlet:namespace/>verificationCode4').val()) <0 ) ){
    				validOTP=false;
    			}if(isNaN(parseInt(A.one('#<portlet:namespace/>verificationCode5').val())) || (parseInt(A.one('#<portlet:namespace/>verificationCode5').val())>9 && parseInt(A.one('#<portlet:namespace/>verificationCode5').val()) <0 ) ){
    				validOTP=false;
    			}if(isNaN(parseInt(A.one('#<portlet:namespace/>verificationCode6').val())) || (parseInt(A.one('#<portlet:namespace/>verificationCode6').val())>9 && parseInt(A.one('#<portlet:namespace/>verificationCode6').val()) <0 ) ){
    				validOTP=false;
    			}
    			
    			console.log("validOTP ->" + validOTP);
    			if(!validOTP){
    				A.one("#otpErrMsg").text('Invalid OTP');
    				return;
    			}
    			
   				if(!formValidator.hasErrors()){
   					document.<portlet:namespace />otpVerificationFm.submit();
   				}
    		});
    		
    		resendPinBtn.on('click', function(e) {
    			resendPinBtn.remove();
    			
    			A.io.request(getStateDetailURL.toString(),{
    				dataType: 'json',
    				method: 'GET',
    				data :{
    					'<portlet:namespace/>email' : countryDetail[0],
    					'<portlet:namespace/>password' : countryDetail[0]
    				},
    				on: {
    				success: function() {
    					var otpSent=this.get('responseData');
    					if(otpSent){
    						alert("Opt Sent successfully");
    					}else{
    						alert("Error while sening OTP, Please try again");
    					}
    				}
    			  }
    			});
    		});
    		
	    	 $('#<portlet:namespace/>verificationCode1,#<portlet:namespace/>verificationCode2,#<portlet:namespace/>verificationCode3,#<portlet:namespace/>verificationCode4,#<portlet:namespace/>verificationCode5,#<portlet:namespace/>verificationCode6').bind('keyup paste', function(){
	    		this.value = this.value.replace(/[^0-9]/g, '');
	    		if(parseInt(this.value)>10){
	    			this.value = '';
	    		}
	        }); 
	    	
	    	$('#<portlet:namespace />mobile').bind('keyup paste', function(){
	    		this.value = this.value.replace(/[^0-9]/g, '');
	        });
	    	
	    	$("#<portlet:namespace/>verificationCode1").on("keyup", function(e){
	    		$("#<portlet:namespace/>verificationCode2").focus();
	    	});
	    	
	    	$("#<portlet:namespace/>verificationCode2").on("keyup", function(e){
	    		$("#<portlet:namespace/>verificationCode3").focus();
	    	});
	    	
	    	$("#<portlet:namespace/>verificationCode3").on("keyup", function(e){
	    		$("#<portlet:namespace/>verificationCode4").focus();
	    	});
	    	
	    	$("#<portlet:namespace/>verificationCode4").on("keyup", function(e){
	    		$("#<portlet:namespace/>verificationCode5").focus();
	    	});
	    	
	    	$("#<portlet:namespace/>verificationCode5").on("keyup", function(e){
	    		$("#<portlet:namespace/>verificationCode6").focus();
	    	});
	    
	    	
	    	if(otp != ''){
	    		console.log("Sending Mobile Verification Code :" + otp + " To Mobile ->" + mobileNo);
	    		var xhr = new XMLHttpRequest();
			    body = JSON.stringify({"content": "Buckzy Portal Registration Verification Code: "+otp, "to": [mobileNo]});
				xhr.open("POST",'https://platform.clickatell.com/messages',true);
				xhr.setRequestHeader("Content-Type", "application/json");
				xhr.setRequestHeader("Authorization", smsAPIKey);
				xhr.onreadystatechange = function(){
				    if (xhr.status == 202) {
						A.one("#mobile-num").text(mobileNo);
				    }else if(xhr.readyState == 4  && xhr.status != 202){
				    	//alert("Error: Failed to send mobile verificatin code. Please contact Administrator");
				    }
			    };
				xhr.send(body);
	    	}
    	 
    	});
    	
     });
})(jQuery);
</script> 

