<%@ include file="/init.jsp" %>

<liferay-ui:success key="verified" message="verified"/>
<liferay-ui:error key="not-verified" message="not-verified"/>

<h3>Email Verification Code</h3>
<portlet:actionURL var="verificationCodeSubmitURL" name="/submit_verification_code"/>

<aui:form name="registrationFm" action="${verificationCodeSubmitURL}" cssClass="form-horizontal form-label-left">
   <div class="col-sm-12 padding-0 profile-detail" style=" color:#9B9A9B;">
   		<div class="col-sm-12">
          <aui:input name="verificationCode" label="Verification Code" placeholder="Verification Code" cssClass="form-control col-md-7 col-xs-6">
		     <aui:validator name="required" />
		     <aui:validator name="maxLength">70</aui:validator>
		   </aui:input>
        </div>
        <aui:input type="hidden" name="verificationCode" value="${verificationCode }"/>
        
         <div class="col-sm-12 margin-top-15 emailVerificationBtn" style="font-size: 12px;">
               <div class="new-transfer">Submit</div>
          </div>
   </div>
</aui:form>

<aui:script>

AUI().use('aui-base','aui-form-validator', function(A) {
	var emailVerificationBtn= A.one(".emailVerificationBtn");
	
	emailVerificationBtn.on('click', function(e) {
		var formValidator = Liferay.Form.get('<portlet:namespace />registrationFm').formValidator;
		formValidator.validate();
		if(!formValidator.hasErrors()){
			document.<portlet:namespace />registrationFm.submit();
		}
	});	
});
</aui:script>