<%@ include file="/init.jsp" %>
<%@page import="com.liferay.portal.kernel.json.JSONObject" %>

<liferay-ui:error key="registration-error" message="registration-error"/>
<liferay-ui:error key="user-exist" message="user-exist"/>
<portlet:resourceURL id="/getStateByCountryCode" var="getStateDetailURL"></portlet:resourceURL>
<portlet:actionURL var="registrationURL" name="/register_user"/>
<portlet:resourceURL id="/getcity_detail" var="getCityDetailURL"></portlet:resourceURL>


<h3>Registration</h3>
 <div class="row padding-0 margin-0">
   
   <aui:form name="registrationFm" action="${registrationURL}" cssClass="form-horizontal form-label-left">
       <div class="col-sm-12 padding-0 profile-detail" style=" color:#9B9A9B;">
           <div class="col-sm-6">
           	   <aui:input name="firstName" label="First Name" placeholder="First Name" cssClass="form-control col-md-7 col-xs-6">
			     <aui:validator name="required" />
			     <aui:validator name="maxLength">70</aui:validator>
			   </aui:input>
           </div>
           <div class="col-sm-6">
               <aui:input name="middleName" label="Middle Name" placeholder="Middle Name(Optional)" cssClass="form-control col-md-7 col-xs-6">
			     <aui:validator name="maxLength">70</aui:validator>
			   </aui:input>
           </div>
           <div class="col-sm-6">
              <aui:input name="lastName" label="Last Name" placeholder="Last Name" cssClass="form-control col-md-7 col-xs-6">
			     <aui:validator name="required" />
			     <aui:validator name="maxLength">70</aui:validator>
			   </aui:input>
           </div>
           <div class="col-sm-6">
              <aui:input name="dob" label="Date of Birth" placeholder="Date Of Birth MM/DD/YYYY" cssClass="form-control col-md-7 col-xs-6 myDatepicker">
			     <aui:validator name="required" />
			     <aui:validator name="maxLength">15</aui:validator>
			   </aui:input>
           </div>
           
           <hr/>
           
           <div class="col-sm-4">
           	   <aui:input name="emailAdddress" label="Email Address" placeholder="Email Address" cssClass="form-control col-md-7 col-xs-6">
			     <aui:validator name="required" />
			     <aui:validator name="email" />
			     <aui:validator name="maxLength">70</aui:validator>
			   </aui:input>
           </div>
           <div class="col-sm-4">
               <aui:input type="password" name="password" label="Password" placeholder="Create your password" cssClass="form-control col-md-7 col-xs-6">
			     <aui:validator name="required"></aui:validator>
							<aui:validator name="minLength" errorMessage="err-valid-pw-low-range">12</aui:validator>
							<aui:validator name="custom" errorMessage="err-valid-pw-policy">
									function(val, fieldNode, ruleValue) {
				             			var passwordPattern = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{12,})");
				            			return passwordPattern.test(val);
									}
				         	</aui:validator>
			   </aui:input>
           </div>
           <div class="col-sm-4">
               <aui:input type="password" name="password2" label="Confirm Password" placeholder="Create your password" cssClass="form-control col-md-7 col-xs-6">
			     <aui:validator name="required"></aui:validator>
							<aui:validator name="minLength" errorMessage="err-valid-pw-low-range">12</aui:validator>
							<aui:validator name="equalTo">'#<portlet:namespace />password'</aui:validator>
							<aui:validator name="custom" errorMessage="err-valid-pw-policy">
									function(val, fieldNode, ruleValue) {
				             			var passwordPattern = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{12,})");
				            			return passwordPattern.test(val);
									}
				         	</aui:validator>
			   </aui:input>
           </div>
           
            <hr/>
            
           <div class="col-sm-12">
              <aui:input name="address" label="Street Address" placeholder="Enter your address" cssClass="form-control col-md-7 col-xs-6">
			     <aui:validator name="required" />
			     <aui:validator name="maxLength">200</aui:validator>
			   </aui:input>
           </div>
           
           <div class="col-sm-3">
              <aui:select name="country" label="Country" cssClass="form-control col-md-7 col-xs-6">
              	 <c:forEach items="${countryJsonList }" var="country">
              	 	<%
              	 		JSONObject countryDetail = (JSONObject)pageContext.getAttribute("country");
              	 		String countryCode = (String)countryDetail.get("cntrycd");
              	 	%>
              	 	<aui:option value="${country.get('cntrycd') },${country.get('intldialprfx') } , ${country.get('cntrydesc') }">${country.get('cntrydesc') } &nbsp; 
              	 	   <img src='<%= themeDisplay.getPathThemeImages() + "/flag/" + countryCode + ".png"%>' />
              	 	</aui:option>
              	 </c:forEach>
              </aui:select>
           </div>
           
            <div class="col-sm-3">
			   <aui:select name="state" label="State" cssClass="form-control col-md-7 col-xs-6">
              	
              </aui:select>
           </div>
           
            
           <div class="col-sm-3">
              <aui:input name="city" label="City" placeholder="City" cssClass="form-control col-md-7 col-xs-6">
			     <aui:validator name="required" />
			     <aui:validator name="maxLength">50</aui:validator>
			   </aui:input>
           </div>
           
           
           <div class="col-sm-3">
              <aui:input name="zipcode" label="Zipcode" placeholder="Zipcode" cssClass="form-control col-md-7 col-xs-6">
			     <aui:validator name="required" />
			     <aui:validator name="maxLength">6</aui:validator>
			   </aui:input>
           </div>
          
           <div class="col-sm-3">
              <aui:input name="countryCode" label="Country Code" placeholder="Country Code" cssClass="form-control col-md-7 col-xs-6" value="+1">
			   </aui:input>
           </div>
           
            <div class="col-sm-9">
              <aui:input name="mobile" label="Mobile" placeholder="Mobile" cssClass="form-control col-md-7 col-xs-6">
			     <aui:validator name="required" />
			     <aui:validator name="maxLength">10</aui:validator>
			   </aui:input>
           </div>
           
           <hr/>
           
           <div class="col-sm-12">
              <aui:select name="reminderQuestion1" label="Security Question1"  cssClass="form-control col-md-7 col-xs-6">
			    <aui:option value="">Select Security Question</aui:option>
			    <% Set<String> questions = (Set<String>)renderRequest.getAttribute("remiderQuestions");
			    for (String question : questions) {
			    %>
			    <aui:option label="<%= question %>" />
			    <%} %>
			   </aui:select>
           </div>
           
            <div class="col-sm-12">
              <aui:input name="reminderAns1" label="Answer" placeholder="Answer" cssClass="form-control col-md-7 col-xs-6">
			     <aui:validator name="required" />
			     <aui:validator name="maxLength">14</aui:validator>
			   </aui:input>
           </div>
           
            <div class="col-sm-12">
              <aui:select name="reminderQuestion2" label="Security Question2"  cssClass="form-control col-md-7 col-xs-6">
			    <aui:option value="">Select Security Question</aui:option>
			    <% Set<String> questions = (Set<String>)renderRequest.getAttribute("remiderQuestions");
			    for (String question : questions) {
			    %>
			    <aui:option label="<%= question %>" />
			    <%} %>
			   </aui:select>
           </div>
           
            <div class="col-sm-12">
              <aui:input name="reminderAns2" label="Answer" placeholder="Answer" cssClass="form-control col-md-7 col-xs-6">
			     <aui:validator name="required" />
			     <aui:validator name="maxLength">14</aui:validator>
			   </aui:input>
           </div>
           
           <aui:input type="hidden" name="deviceInfo" />
           
          <!--  <div class="col-sm-12" style="padding-top: 15px;">
              <aui:button type="button" value="Submit" style="width: 700px;" cssClass=""/>
           </div> -->
           
           <div class="col-sm-12 margin-top-15 registerBtn" style="font-size: 12px;">
               <div class="new-transfer">Submit</div>
           </div>
		</div>
		
		<div class="pin-detail" style="border: 1px solid #DB2222; outline: #E8E8E8 solid 3px; padding: 75px 35px 55px 35px;">
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
                 <div class="col-sm-12 margin-top-15 submitRegistrationBtn" style="font-size: 12px; width: 700px;">
              		 <div class="new-transfer" style="margin-left:25px !important;">Submit Registration</div>
           		</div>
                 <div class="col-sm-12 padding-0 margin-0 margin-top-10" style="font-size: 10px;">
                     If you haven't received the message within a few minutes we can <a id="resend-pin"> Resend the PIN</a> or please check your spam folder.
                 </div>
             </div>
		</div>
		
    </aui:form>

<script>
var geoCountryCode;
var geoState;
var geoCity;
</script>
<script>
jQuery.noConflict();
(function($) {
    $(function() {
    	
    	var pns = "<portlet:namespace/>";
    	
    	
    	AUI().use('aui-base','aui-form-validator',  'aui-io-request', 'autocomplete-list',
    			'autocomplete-filters', 'autocomplete-highlighters', 'aui-autocomplete', 'node-event-simulate', function(A) {
    		var registerBtn= A.one(".registerBtn");
    		var firstVerificationCode;
    		var secondVerificationCode;
    		var submitRegistrationBtn = A.one('.submitRegistrationBtn');
    		var smsAPIKey='${smsAPIKey}';
    		var country = A.one("#<portlet:namespace />country");
    		var reminderQuestion1 = A.one("#<portlet:namespace />reminderQuestion1");
    		
    		A.one(".pin-detail").hide();
    		registerBtn.on('click', function(e) {
    			var formValidator = Liferay.Form.get('<portlet:namespace />registrationFm').formValidator;
    			formValidator.validate();
   				if(!formValidator.hasErrors()){
   					document.<portlet:namespace />registrationFm.submit();
   				}
    		});
    		
    		submitRegistrationBtn.on('click', function(e) {
    			// Check OTP thorugh REST API.
    		});
    		
    		country.on('change', function(e) {
    			console.log(this.get('value'));
    			var countryDetail = this.get('value').split(",");
    			$("#<portlet:namespace/>countryCode").val("+"+countryDetail[1]);
    			
    			var  getStateDetailURL = '${getStateDetailURL}';
    			A.io.request(getStateDetailURL.toString(),{
    				dataType: 'json',
    				method: 'GET',
    				data :{
    					'<portlet:namespace/>countryCode' : countryDetail[0]
    				},
    				on: {
    				success: function() {
    					A.one('#<portlet:namespace/>state').all('option').remove();
    					var stateList=this.get('responseData');
    					A.one('#<portlet:namespace/>state').append("<option  value='' >Select State</option>");
    					var selectedState='';
    					for(var i in stateList){
    						A.one('#<portlet:namespace/>state').append('<option value="'+ stateList[i].statecd  +'">'+ stateList[i].statedesc +'</option>');
    						if(stateList[i].statecd==geoStateCode){
    							selectedState = stateList[i];
    						}
    					}
    					
    					if(selectedState){
    						A.one('#<portlet:namespace/>state').val(selectedState.statecd);
    					}
    				}
    			  }
    			});
    		});
    		
    		var cityData;
    		
    		var cityAutoComplete = new A.AutoCompleteList({
    			allowBrowserAutocomplete: 'true',
    			activateFirstItem: 'true',
    			inputNode: '#<portlet:namespace />city',
    			resultTextLocator:'cityDesc',
    			render: 'true',
    			resultHighlighter: 'phraseMatch',
    			resultFilters:['phraseMatch'],
    			source:function(){
    				
    				var inputValue=A.one("#<portlet:namespace />city").get('value');
    				console.log('inputValue->' + inputValue);
    				
    				if(inputValue.length>=3){
    					var getCityDetailURL = '${getCityDetailURL}';
    					var finalData;
    					var cityAutoCompelteRequest=A.io.request(getCityDetailURL.toString(),{
    												dataType: 'json',
    												method:'POST',
    												data:{
    													<portlet:namespace />keyword:inputValue,
    												},
    												autoLoad:false,
    												sync:false,
    												on: {
    													success:function(){
    														var data=this.get('responseData');
    														console.log(data.length);
    														cityData = data;
    													}
    												}
    										});
    					cityAutoCompelteRequest.start();
    					return cityData;
    			   }
    			}
    		});
    		
    		cityAutoComplete.on('select', function(e) {
    			console.log("11 ->" + e.result.raw.cityId);
    			//simulateBanksOnCityChange(e.result.raw.cityId,e.result.raw.cntryCd);
    		});
    		
    		cityAutoComplete.render();
    		
    		
    		reminderQuestion1.on('change', function(e) {
    			var q1 = this.get('value');
    			var q2array = A.one("#<portlet:namespace />reminderQuestion2").all('option')._nodes;
    			for(var i=0;i<q2array.length;i++){
    				if(q2array[i].value==q1){
    					q2array[i].remove();
    				}
    			}

    		});
    		 
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
    	   	 
    	   	 console.log("deviceInfo->"+ deviceInfo);
    	   	 A.one("#<portlet:namespace/>deviceInfo").val(JSON.stringify(deviceInfo));
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
    	    
    	    detectDeviceInfo();
    	
    	
    	jQuery('.myDatepicker').datetimepicker({
	        format: 'MM/DD/YYYY',
	        maxDate: new Date()
	    });
    	
    	$('#verificationCode1,#verificationCode2,#verificationCode3,#verificationCode4').bind('keyup paste', function(){
    		this.value = this.value.replace(/[^0-9]/g, '');
    		if(parseInt(this.value)>10){
    			this.value = '';
    		}
        });
    	
    	$('#<portlet:namespace />mobile').bind('keyup paste', function(){
    		this.value = this.value.replace(/[^0-9]/g, '');
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
    			    
    			    $("#"+pns+"city").val(geoCity);
    			    $("#"+pns+"country option").each(function(i){
    			        if($(this).val().indexOf(geoCountryCode)>=0){
    			        	$(this).prop('selected', true);
    			        	country.simulate('change');
    			        }
    			    });
    			    
    			  }
    			});
    		 
         });
    	 
    	});
    	
     });
})(jQuery);
</script> 