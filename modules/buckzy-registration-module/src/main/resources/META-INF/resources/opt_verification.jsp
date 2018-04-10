<%@ include file="/init.jsp" %>
<liferay-ui:success key="registration-success" message="registration-success"/>

<portlet:actionURL var="otpVerificationURL" name="/otp_verification">
</portlet:actionURL>

<liferay-ui:error key="not-verfied" message="not-verfied"/>
<liferay-ui:success key="verified" message="verified"/>


<h3>Registration</h3>
<aui:form name="otpVerificationFm" action="${otpVerificationURL}" cssClass="form-horizontal form-label-left">
   
<c:if test="${success }">
	<h4>Registration done successfully and you have verified your registered mobile number.
	Please check your inbox to get email verification link</h4> 
</c:if>   
   
<div class="pin-detail" style="border: 1px solid #DB2222; outline: #E8E8E8 solid 3px; padding: 75px 35px 55px 35px;">
	<div class="row padding-0 margin-0 padding-10">
               <div class="col-sm-12 padding-0 margin-0" style="margin-bottom: 7px; font-size: 18px;">Enter your PIN</div>
               <div class="col-sm-12 padding-0 margin-0 margin-top-10" style="font-size: 10px;">
                   A pin number has been sent to you on your mobile number <span id="mobile-num"></span>. Once you have verified, your digital profile will be activated.
               </div>
               <div class="col-sm-12 padding-0 margin-0 margin-top-10" style="font-size: 10px;">
                   Enter the 6-digit code from the Mobile:
               </div>
               <div class="col-sm-12 margin-top-10" style="background-color: #FFFFFF; padding:45px 20px 20px 20px; ">
                   <div class="col-xs-2">
                   	   <aui:input type="text" name="verificationCode1"  label="" style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 30px;">
                   	   		<aui:validator name="required" />
			     			<aui:validator name="maxLength">1</aui:validator>
                   	   </aui:input>
                   </div>
                   <div class="col-xs-2">
                        <aui:input type="text" name="verificationCode2"  label="" style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 30px;">
                   	   		<aui:validator name="required" />
			     			<aui:validator name="maxLength">1</aui:validator>
                   	   </aui:input>
                   </div>
                   <div class="col-xs-2">
                        <aui:input type="text" name="verificationCode3"  label="" style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 30px;">
                   	   		<aui:validator name="required" />
			     			<aui:validator name="maxLength">1</aui:validator>
                   	   </aui:input>
                   </div>
                   <div class="col-xs-2">
                        <aui:input type="text" name="verificationCode4"  label="" style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 30px;">
                   	   		<aui:validator name="required" />
			     			<aui:validator name="maxLength">1</aui:validator>
                   	   </aui:input>
                   </div>
                   <div class="col-xs-2">
                        <aui:input type="text" name="verificationCode5"  label="" style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 30px;">
                   	   		<aui:validator name="required" />
			     			<aui:validator name="maxLength">1</aui:validator>
                   	   </aui:input>
                   </div>
                   <div class="col-xs-2">
                        <aui:input type="text" name="verificationCode6"  label="" style="border: 0px; border-bottom: 2px solid #C7C7CC; width: 30px;">
                   	   		<aui:validator name="required" />
			     			<aui:validator name="maxLength">1</aui:validator>
                   	   </aui:input>
                   </div>
               </div>
               <!-- <div class="col-sm-12 margin-top-15" style="font-size: 12px;">
                   <aui:button type="button" value="Submit Registration" style="width: 700px;" cssClass="submitRegistrationBtn new-transfer btn btn-red"/>
               </div> -->
               <div class="col-sm-12 margin-top-15 submitRegistrationBtn" style="font-size: 12px; width: 700px;">
            		 <div class="new-transfer" style="margin-left:25px !important;">Submit</div>
         		</div>
               <div class="col-sm-12 padding-0 margin-0 margin-top-10" style="font-size: 10px;">
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
    		
    		
    		submitRegistrationBtn.on('click', function(e) {
    			var formValidator = Liferay.Form.get('<portlet:namespace />otpVerificationFm').formValidator;
    			formValidator.validate();
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
    	 
    	});
    	
     });
})(jQuery);
</script> 

