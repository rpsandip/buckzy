<%@ include file="/init.jsp" %>
<%@page import="com.liferay.portal.kernel.json.JSONObject" %>

<liferay-ui:error key="registration-error" message="registration-error"/>
<liferay-ui:error key="registration-custom-err" message="${customErr }"/>
<liferay-ui:error key="user-exist" message="user-exist"/>
<portlet:resourceURL id="/getStateByCountryCode" var="getStateDetailURL"></portlet:resourceURL>
<portlet:actionURL var="registrationURL" name="/register_user"/>
<portlet:resourceURL id="/getcity_detail" var="getCityDetailURL"></portlet:resourceURL>
<portlet:resourceURL id="/check_user_exist" var="checkUserExistURL"></portlet:resourceURL>

   <aui:form name="registrationFm" action="${registrationURL}" cssClass="form-horizontal form-label-left">
       <div style="display: table; width: 100%;">
       		<div style="display: table-row;">
            	<div style="display: table-cell; padding-bottom: 50px; ">
                    <div class="main-container" style="max-width: 700px;">
                        <div class="row padding-0 margin-0">
                        	<div class="col-sm-12" style="color: #000000; font-weight: bold; margin-bottom: 7px; font-size: 27px;">Register</div>
           	   					<div class="registration1 col-xs-12 padding-0 border-1" style="padding: 20px;  font-size: 14px; background-color: #FFFFFF;">
           	  						<div class="col-sm-12" style="margin-bottom: 15px; display: block; font-size:18px; padding-left: 0px;">
                                    	Please enter information as it appears on your government-issued ID
                                	</div>
                                	<div class="col-sm-12 pad-0">
						           	    <div class="col-sm-6 reg-form has-feedback" style="padding: 0 10px 0 0;">
							           	   <aui:input name="firstName" label="First Name" placeholder="First Name" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px; width: 100%; border:1px solid #D3D3D3; border-radius: 1px;">
										     <aui:validator name="maxLength">70</aui:validator>
										   </aui:input>
							           </div>
	           						   <div class="col-sm-6 reg-form has-feedback" style="padding: 0 0 0 10px;">
							               <aui:input name="middleName" label="Middle Name" placeholder="Middle Name(Optional)" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px;  width: 100%; border:1px solid #D3D3D3; border-radius: 1px;">
										     <aui:validator name="maxLength">70</aui:validator>
										   </aui:input>
	           							</div>
           							</div>
           							<div class="col-sm-12 has-feedback pad-0">
						              <aui:input name="lastName"  label="Last Name" placeholder="Last Name" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px;  width: 100%; border:1px solid #D3D3D3; border-radius: 1px;">
									     <aui:validator name="maxLength">70</aui:validator>
									   </aui:input>
						           </div>
						           <div class="col-sm-12 padding-0 icon-input">
                                        <div class="col-sm-6 pad-0">
                                            <div class="input-group">
                                                <span class="input-group-addon" ><i class="glyphicon glyphicon-envelope"></i></span>
                                                 <aui:input name="emailAdddress" label=""  placeholder="Email Address" cssClass="form-control col-md-7 col-xs-6">
												     <aui:validator name="email" />
												     <aui:validator name="maxLength">70</aui:validator>
												   </aui:input>
                                            </div>
                                        </div>
                                        <div class="col-sm-6 pad-0">
                                            <div class="input-group">
                                            	<span id="userExistErrMsg" style="color: #a94442;"></span>
                                            </div>
                                        </div>    
                                    </div>
                                    <div class="col-sm-12 padding-0 icon-input">
                                        <div class="col-sm-6 pad-0">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                                <aui:input type="password" name="password"  label=""  placeholder="Create your password" cssClass="form-control col-md-7 col-xs-6">
															<aui:validator name="minLength" errorMessage="err-valid-pw-low-range">8</aui:validator>
															<aui:validator name="custom" errorMessage="err-valid-pw-policy">
																	function(val, fieldNode, ruleValue) {
												             			var passwordPattern = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
												            			return passwordPattern.test(val);
																	}
												         	</aui:validator>
											   </aui:input>
                                            </div>
                                        </div>
                                    </div>
                                     <div class="col-sm-12 padding-0 icon-input">
                                        <div class="col-sm-6 pad-0">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                                <aui:input type="password" name="password2" label=""  placeholder="Create your password" cssClass="form-control col-md-7 col-xs-6">
																<aui:validator name="minLength" errorMessage="err-valid-pw-low-range">8</aui:validator>
																<aui:validator name="equalTo">'#<portlet:namespace />password'</aui:validator>
																<aui:validator name="custom" errorMessage="err-valid-pw-policy">
																		function(val, fieldNode, ruleValue) {
													             			var passwordPattern = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
													            			return passwordPattern.test(val);
																		}
													         	</aui:validator>
												   </aui:input>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12" style="margin-bottom: 15px; display: block; font-size:18px; padding-left: 0px;">
                                    	We'll ask you this question if you ever need to reset your password
                                	</div>
                                    <div class="col-sm-12 pad-0">
                                        <aui:select name="reminderQuestion1" label="Security Question 1"  cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px; ">
										    <aui:option value="">Select Security Question</aui:option>
										    <% Set<String> questions = (Set<String>)renderRequest.getAttribute("remiderQuestions");
										    for (String question : questions) {
										    %>
										    <aui:option label="<%= question %>" />
										    <%} %>
										 </aui:select>
                                    </div>
                                    <div class="col-sm-12 pad-0">
                                    	<aui:input name="reminderAns1" placeholder="Answer" label="Answer" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px; color:#000000;" >
									     <aui:validator name="maxLength">35</aui:validator>
									   </aui:input>
                                    </div>
						            <div class="col-sm-12 pad-0">
						              <aui:select name="reminderQuestion2" label="Security Question 2"  cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px; ">
									    <aui:option value="">Select Security Question</aui:option>
									    <% Set<String> questions = (Set<String>)renderRequest.getAttribute("remiderQuestions");
									    for (String question : questions) {
									    %>
									    <aui:option label="<%= question %>" />
									    <%} %>
									   </aui:select>
						           </div>
						            <div class="col-sm-12 pad-0">
						              <aui:input name="reminderAns2"  placeholder="Answer" label="Answer" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px; color:#000000;">
									     <aui:validator name="maxLength">35</aui:validator>
									   </aui:input>
									</div>
									<div class="col-sm-12 margin-top-30 next-page">
                                        <div class="new-transfer">Proceed to next page</div>
                                    </div>
                                    </div>
                                    <div class="registration2" style="display:none;">
                                    <div class=" col-xs-12 padding-0 border-1" style=" font-size: 12px;">
                                    		<div class="col-sm-12" style="margin-top: 10px;"><b style="font-size: 16px;      padding-left: 0px;">Enter your address</b></div>
                                    		<div class="col-sm-12">
                                        		<aui:input name="address" placeholder="Street address" label="Street Address" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px; ">
											      <aui:validator name="maxLength">200</aui:validator>
											    </aui:input>
                                    		</div>
                                    		<div class="col-sm-12" style="margin: 0; padding: 0;">
	                                    		<div class="col-sm-4">
			                                        <aui:select name="country" label="Country" cssClass="form-control col-md-7 col-xs-6"  style="font-size: 12px; ">
									              	 <c:forEach items="${countryJsonList }" var="country">
									              	 	<%
									              	 		JSONObject countryDetail = (JSONObject)pageContext.getAttribute("country");
									              	 		String countryCode = (String)countryDetail.get("cntrycd");
									              	 	%>
									              	 	<aui:option value="${country.get('cntrycd') },${country.get('intldialprfx') } , ${country.get('cntrydesc')}, ${country.get('threedigitcd') }">${country.get('cntrydesc') } &nbsp; 
									              	 	   <img src='<%= themeDisplay.getPathThemeImages() + "/flag/" + countryCode + ".png"%>' />
									              	 	</aui:option>
									              	 </c:forEach>
									              </aui:select>
			                                    </div>
			                                    <div class="col-sm-4 form-group" style=" margin-left: 6px;">
			                                        <aui:select name="state" label="State" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px; "></aui:select>
	                                    		</div>
	                                    		<div class="col-sm-4 city-auto form-group" style="margin-left: 20px">
	                                    			<aui:input name="city" label="City" placeholder="City" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px; ">
												     	<aui:validator name="maxLength">50</aui:validator>
												   </aui:input>
	                                    		</div>
                                    		</div>
                                    		<div class="col-sm-12" style="margin: 0; padding: 0;">
                                    		<div class="col-sm-4 form-group" style="margin-left: 0px;">
                                    			<aui:input name="zipcode" label="Zipcode" placeholder="Zipcode" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px; ">
                                    				<aui:validator name="maxLength">20</aui:validator>
											   	</aui:input>
                                    		</div>
                                    		<div class="col-sm-4 form-group" style="margin-left: 22px;">
                                    			 <aui:input name="countryCode" label="Country Code" placeholder="Country Code" cssClass="form-control col-md-7 col-xs-6" value="+1" style="font-size: 12px; ">
			   									</aui:input>
                                    		</div>
                                    		<div class="col-sm-4 form-group" style=" margin-left: 20px; ">
                                    			 <aui:input name="mobile" label="Mobile" placeholder="Mobile" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px; ">
			     									<aui:validator name="maxLength">16</aui:validator>
			   									</aui:input>
                                    		</div>
                                    		</div>
			   						</div>
			   						<div class="col-xs-12 padding-0 border-1" style="padding: 20px;  font-size: 12px; margin-top: 10px;">
                                		<div class="col-sm-12" style="margin-bottom: 10px; font-size:18px; padding-left: 0px;">Additional information</div>
			                                <div class="col-sm-12 form-group has-feedback" style="padding: 0;">
			                                    <!-- <aui:input name="dob" label="Date Of Birth" placeholder="Date Of Birth MM/DD/YYYY" cssClass="form-control col-md-7 col-xs-6 myDatepicker" style="font-size: 12px;  width: 100%; border:1px solid #D3D3D3; border-radius: 1px;">
												     <aui:validator name="maxLength">15</aui:validator>
												</aui:input> -->
												<div class="col-sm-2 form-group has-feedback" style="padding: 0;">
													<aui:select name="dobMonth" label="Month" cssClass="form-control col-md-7 col-xs-6"  style="font-size: 12px; ">
														<aui:option value="00">January</aui:option>
														<aui:option value="01">February</aui:option>
														<aui:option value="02">March</aui:option>
														<aui:option value="03">April</aui:option>
														<aui:option value="04">May</aui:option>
														<aui:option value="05">June</aui:option>
														<aui:option value="06">July</aui:option>
														<aui:option value="07">August</aui:option>
														<aui:option value="08">September</aui:option>
														<aui:option value="09">October</aui:option>
														<aui:option value="10">Novemnber</aui:option>
														<aui:option value="11">December</aui:option>
													</aui:select>
												</div>
												<div class="col-sm-2 form-group has-feedback" style="padding: 0; padding-left: 10px;">
													<aui:input name="dobDay" label="Day" placeholder="Day" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px; ">
			     										<aui:validator name="maxLength">2</aui:validator>
			   										</aui:input>
												</div>
												<div class="col-sm-2 form-group has-feedback" style="padding: 0; padding-left: 10px;">
													<aui:input name="dobYear" label="Year"  placeholder="Year" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px; ">
			     										<aui:validator name="maxLength">4</aui:validator>
			   										</aui:input>
												</div>
			                                </div>
			                                 <div class="col-sm-12 form-group has-feedback">
			                                 	<div id="dobErrMsg" style="color: #961622; font-size: 14px; font-weight: bold;"></div>
			                                 </div>
			                                 
			                        </div>
			                        <div class="col-sm-12">
                                		<div class="checkbox">
                                			<aui:input type="checkbox" name="cond1" value="" label="By registering I have read and agree to the <a><b>Terms and Conditions</b></a> of Buckzy payments Inc. including online <a><b>Privecy Statement</b></a>.</a>">
                                			</aui:input>
                                		</div>
                                		<div class="checkbox">
                                    		<aui:input type="checkbox" name="cond2" value="" label="I understand and consent to receiving electronic information and communications, including email, phone and SMS about Buckzy Products and Services. I understand agree to the <a><b>Terms and Conditions</b></a>, Online <a><b>Privacy Statement</b></a>">
                                			</aui:input>
                                		</div>
                                		
                            		</div>
                             		<div class="col-sm-12" style="    margin-top: 20px;">
                                		<div class="prev-page new-transfer col-sm-2">Prev</div>
                                		<div class="new-transfer registerBtn">Complete Registration</div>
                            		</div>
                            		</div>
			  				 </div>
			   			</div>
           			</div>
           		</div>
           </div>  
        <aui:input type="hidden" name="deviceInfo" />
		
		<div class="pin-detail" style="border: 1px solid #DB2222; outline: #E8E8E8 solid 3px; padding: 75px 35px 55px 35px;">
			<div class="row padding-0 margin-0 padding-10">
                 <div class="col-sm-12 padding-0 margin-0" style="margin-bottom: 7px; font-size: 18px;">Enter your PIN</div>
                 <div class="col-sm-12 padding-0 margin-0 margin-top-10" style="font-size: 12px;">
                     A pin number has been sent to you on your mobile number <span id="mobile-num"></span>. Once you have verified, your digital profile will be activated.
                 </div>
                 <div class="col-sm-12 padding-0 margin-0 margin-top-10" style="font-size: 12px;">
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
                 <div class="col-sm-12 padding-0 margin-0 margin-top-10" style="font-size: 12px;">
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
    		var nextPageBtn = A.one(".next-page");
    		var prevPageBtn = A.one(".prev-page");
    		var dobDaySelect = A.one("#<portlet:namespace/>dobDay");
    		var dobYearSelect = A.one("#<portlet:namespace/>dobYear");
    		var dobMonthSelect = A.one("#<portlet:namespace/>dobMonth");
    		var emailSelect = A.one("#<portlet:namespace/>emailAdddress");
    		var validDob = false;
    		A.one(".pin-detail").hide();
    		
    		registerBtn.on('click', function(e) {
    			var formValidator = Liferay.Form.get('<portlet:namespace />registrationFm').formValidator;
    			formValidator.validate();
    			registration1.validate();
    			registration2.validate();
    			if(!validDob){
    				A.one("#dobErrMsg").text("Please enter valid date");
    				return;
    			}
   				if(!registration2.hasErrors() && !registration1.hasErrors()){
   					document.<portlet:namespace />registrationFm.submit();
   				}
    		});
    		
    		submitRegistrationBtn.on('click', function(e) {
    			// Check OTP thorugh REST API.
    		});
    		
    		nextPageBtn.on('click', function(e) {
    			A.one("#userExistErrMsg").text('');
    			registration1.validate();
    			
    			var  checkUserExistURL = '${checkUserExistURL}';
    			A.io.request(checkUserExistURL.toString(),{
    				dataType: 'json',
    				method: 'GET',
    				data :{
    					'<portlet:namespace/>emailAddress' : A.one("#<portlet:namespace/>emailAdddress").val()
    				},
    				on: {
    				success: function() {
    					var resp=this.get('responseData');
    					
    					console.log('isUserExist->' + resp.isUserExist);
    					
    					if(resp.isUserExist){
    	    				A.one("#userExistErrMsg").text('Email Address already exist.');
    	    				return;
    	    			}
    	    			
    					if(!registration1.hasErrors()){
    						$(".registration1").hide();
    	       				$(".registration2").show();		
    					}
    					}
    			 	 }
    			});
    		});
    		
    		prevPageBtn.on('click', function(e) {
    				$(".registration1").show();
       				$(".registration2").hide();	
			});
    		
    		
    		emailSelect.on('change', function(e) {
    			A.one("#userExistErrMsg").text('');
    			if(this.get('value') && this.get('value').indexOf(".")>0){
	    			var  checkUserExistURL = '${checkUserExistURL}';
	    			A.io.request(checkUserExistURL.toString(),{
	    				dataType: 'json',
	    				method: 'GET',
	    				data :{
	    					'<portlet:namespace/>emailAddress' : A.one("#<portlet:namespace/>emailAdddress").val()
	    				},
	    				on: {
	    				success: function() {
	    					var resp=this.get('responseData');
		    					if(resp.isUserExist){
		    	    				A.one("#userExistErrMsg").text('Email Address already exist.');
		    	    				return;
		    	    			}
	    					}
	    			 	 }
	    			});
    			}
    		});
    		
    		country.on('change', function(e) {
    			console.log(this.get('value'));
    			var countryDetail = this.get('value').split(",");
    			$("#<portlet:namespace/>countryCode").val("+"+countryDetail[1]);
    			console.log("simulating country");
    			var  getStateDetailURL = '${getStateDetailURL}';
    			A.io.request(getStateDetailURL.toString(),{
    				dataType: 'json',
    				method: 'GET',
    				data :{
    					'<portlet:namespace/>cntryCode' : countryDetail[0]
    				},
    				on: {
    				success: function() {
    					A.one('#<portlet:namespace/>state').all('option').remove();
    					A.one('#<portlet:namespace/>city').text('');

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
    			allowBrowserAutocomplete: false,
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
    					var country = A.one("#<portlet:namespace/>country").get('value');
    					var countryCode ='';
    					if(country && country.length>0){
    						countryCode = country.split(",")[0];
    					}
    					var cityAutoCompelteRequest=A.io.request(getCityDetailURL.toString(),{
    												dataType: 'json',
    												method:'POST',
    												data:{
    													<portlet:namespace />keyword:inputValue,
    													<portlet:namespace />stateCode: A.one("#<portlet:namespace/>state").val(),
    													<portlet:namespace />countryCode: countryCode
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
    		
    		dobDaySelect.on('change', function(e) {
    			A.one("#dobErrMsg").text("");
    			if(dobYearSelect.val() && !isValidDOB()){
    				A.one("#dobErrMsg").text("Please enter valid date");
    			}
    		});
    		
    		dobYearSelect.on('change', function(e) {
    			A.one("#dobErrMsg").text("");
    			if(dobDaySelect.val() && !isValidDOB()){
    				validDob = false;
    				A.one("#dobErrMsg").text("Please enter valid date");
    			}
    		});
    		
    		dobMonthSelect.on('change', function(e) {
    			A.one("#dobErrMsg").text("");
    			if(dobDaySelect.val() &&  dobYearSelect.val() && !isValidDOB()){
    				validDob = false;
    				A.one("#dobErrMsg").text("Please enter valid date");
    			}
    		});
    		
    		$('#<portlet:namespace/>dobDay').bind('keyup paste', function(){
    			this.value = this.value.replace(/[^0-9]/g, '');
    			if(this.value && this.value.length>2){
    				this.value = this.value.substring(0,2);
    			}
    			if(this.value && parseInt(this.value)>31){
    				this.value = '';
    			}
    	    });
    		
    		$('#<portlet:namespace/>dobYear').bind('keyup paste', function(){
    			this.value = this.value.replace(/[^0-9]/g, '');
    			if(this.value && this.value.length>4){
    				this.value = this.value.substring(0,4);
    			}
    	    });
    		
    		function isValidDOB(){
    			var month = A.one("#<portlet:namespace />dobMonth").val();
    			var year = A.one("#<portlet:namespace />dobYear").val();
    			var day = A.one("#<portlet:namespace />dobDay").val();
    		    console.log("dob ->" +year + " " + month + " " + day);
    			var isValidDob =   new Date(parseInt(year), parseInt(month), parseInt(day)) < new Date();
    			console.log("isValidDob ->" + isValidDob);
    			if(isValidDob){
    				validDob = true;
    			}else{
    				validDob = false;
    			}
    			return isValidDob;
    		}
    		
    		
    		new Formatter(document.getElementById('<portlet:namespace/>'+'mobile'), {
    			'pattern': '({{999}})-{{999}}-{{9999}}',
    			'persistent': false
    		});
    		
    		
    		var registration1 = new A.FormValidator({
    			boundingBox: document.<portlet:namespace/>registrationFm,
    			rules: {
    				<portlet:namespace/>firstName: {
    					required: true
    				},
    				<portlet:namespace/>lastName: {
    					required: true
    				},
    				<portlet:namespace/>emailAdddress: {
    					required: true
    				},<portlet:namespace/>password: {
    					required: true
    				},<portlet:namespace/>password2: {
    					required: true
    				},<portlet:namespace/>reminderQuestion1: {
    					required: true
    				},<portlet:namespace/>reminderAns1: {
    					required: true
    				},<portlet:namespace/>reminderQuestion2: {
    					required: true
    				},<portlet:namespace/>reminderAns2: {
    					required: true
    				}
    				
    			}
    		});
    		
    		var registration2 = new A.FormValidator({
    			boundingBox: document.<portlet:namespace/>registrationFm,
    			rules: {
    				<portlet:namespace/>address: {
    					required: true
    				},
    				<portlet:namespace/>state: {
    					required: true
    				},
    				<portlet:namespace/>country: {
    					required: true
    				},
    				<portlet:namespace/>city: {
    					required: true
    				},<portlet:namespace/>zipcode: {
    					required: true
    				},<portlet:namespace/>countryCode: {
    					required: true
    				},<portlet:namespace/>mobile: {
    					required: true
    				},<portlet:namespace/>dob: {
    					required: true
    				},<portlet:namespace/>cond1: {
    					required: true
    				},<portlet:namespace/>cond2: {
    					required: true
    				}
    				
    			},
    			fieldStrings: {
    				<portlet:namespace/>cond1: {
    					required: 'Please check this box if you want to proceed'
    					},
    					<portlet:namespace/>cond2: {
    					required: 'Please check this box if you want to proceed'
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
    	
    	var dtPkDate = new Date();
    	dtPkDate.setDate(dtPkDate.getDate() - 1);
    	jQuery('.myDatepicker').datetimepicker({
	        format: 'MM/DD/YYYY',
	        maxDate: dtPkDate
	    });
    	
    	$('#verificationCode1,#verificationCode2,#verificationCode3,#verificationCode4').bind('keyup paste', function(){
    		this.value = this.value.replace(/[^0-9]/g, '');
    		if(parseInt(this.value)>10){
    			this.value = '';
    		}
        });
    	
    	
/*     	$('#<portlet:namespace />mobile').bind('keyup paste', function(){
    		this.value = this.value.replace(/[^0-9]/g, '');
        }); */
    	
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
    			        	A.one('#<portlet:namespace/>state').val(geoStateCode);
    			        }
    			    });
    			    
    			  }
    			});
    		 
         });
    	 
    	});
    	
     });
})(jQuery);
</script> 