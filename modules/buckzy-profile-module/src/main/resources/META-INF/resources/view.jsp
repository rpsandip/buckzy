<%@ include file="/init.jsp" %>

<portlet:actionURL var="profileUpdateURL" name="/profile_update"/>

<liferay-ui:success key="profile-update-success" message="profile-update-success"/>
<liferay-ui:error key="profile-update-error" message="profile-update-error"/>
<liferay-ui:error key="profile-custom-update-error" message="${customErr }"/>

<portlet:resourceURL id="/getbranch_detail" var="getBranchDetailURL"></portlet:resourceURL>
<portlet:resourceURL id="/getstate_detail" var="getStateDetailURL"></portlet:resourceURL>
<portlet:resourceURL id="/getcity_detail" var="getCityDetailURL"></portlet:resourceURL>
<portlet:resourceURL id="/getbank_detail" var="getBankDetailURL"></portlet:resourceURL>
<portlet:resourceURL id="/getcard_detail" var="getCardDetailURL"></portlet:resourceURL>
   
   <aui:form name="profileFm" action="${profileUpdateURL}" cssClass="form-horizontal form-label-left" enctype="multipart/form-data">
      <div style="display: table; width: 100%;">
       		<div style="display: table-row;">
            	<div style="display: table-cell; padding-bottom: 50px; ">
                    <div class="main-container" style="max-width: 700px;">
                        <div class="row padding-0 margin-0">
                        	<div class="col-sm-12" style="color: #000000; font-weight: bold; margin-bottom: 7px; font-size: 18px;">Profile</div>
      						<div class="col-xs-12 padding-0 border-1" style="padding: 20px;  font-size: 12px; background-color: #FFFFFF;">
      							<span style="font-size: 14px;     margin-left: 13px; font-weight: bold; color: black;">Personal Information</span><br/>
      							<div class="col-sm-12 pad-0">
	      							<div class="col-sm-6 form-group  has-feedback">
							         	   <aui:input name="firstName" label="First Name" value="${userBean.partyBean.frstnm }" placeholder="First Name" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px;  width: 100%; border:1px solid #D3D3D3; border-radius: 1px;">
				     							<aui:validator name="required" />
				     							<aui:validator name="maxLength">70</aui:validator>
				  						   </aui:input>
							         </div>
							         <div class="col-sm-6 form-group  has-feedback">
							              	<aui:input name="middleName" label="Middle Name" value="${userBean.partyBean.midlnm }" placeholder="Middle Name(Optional)" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px;  width: 100%; border:1px solid #D3D3D3; border-radius: 1px;">
				    							<aui:validator name="maxLength">70</aui:validator>
				   							</aui:input>
	           						 </div>
           						 </div>
           						 <div class="col-sm-12 pad-0">
	           						 <div class="col-sm-6 form-group  has-feedback">
										   <aui:input name="lastName" label="Last Name" value="${userBean.partyBean.lastnm }" placeholder="Last Name" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px;  width: 100%; border:1px solid #D3D3D3; border-radius: 1px;">
										     <aui:validator name="required" />
										     <aui:validator name="maxLength">70</aui:validator>
										   </aui:input>
							          </div>
							          <div class="col-sm-6 form-group  has-feedback">
							         	   <aui:input name="emailAdddress" label="Email Address"  value="${userBean.partyBean.email }" placeholder="Email Address" cssClass="form-control col-md-7 col-xs-6" readyonly="true" style="font-size: 12px;  width: 100%; border:1px solid #D3D3D3; border-radius: 1px;">
										     <aui:validator name="required" />
										     <aui:validator name="email" />
										     <aui:validator name="maxLength">70</aui:validator>
										   </aui:input>
							         </div>
						         </div>
						         <div class="col-sm-12 form-group  has-feedback" style="    padding: 0 15px;">
						         	<aui:input name="address" label="Address" value="${userBean.partyBean.partyAddressBean.postaddr }" placeholder="Enter your address" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px;  width: 100%; border:1px solid #D3D3D3; border-radius: 1px;">
								     	<aui:validator name="required" />
								    	 <aui:validator name="maxLength">200</aui:validator>
								   </aui:input>
						         </div>
						         <div class="col-sm-12 form-group  has-feedback" style="    padding: 0 15px;">
							         <div class="col-sm-4 form-group  has-feedback" style="    padding: 0 10px 0 0;">
							         	<aui:select name="country" label="Country" cssClass="form-control col-md-7 col-xs-6">
						              	 <c:forEach items="${countryJsonList }" var="country">
						              	 	<aui:option value="${country.get('cntrycd') },${country.get('intldialprfx') } , ${country.get('cntrydesc')},  ${country.get('threedigitcd') }" 
						              	 	selected="${country.get('cntrycd') eq userBean.partyBean.partyAddressBean.cntrycd ? true : false }">${country.get('cntrydesc') }</aui:option>
						              	 </c:forEach>
						              </aui:select>
							         </div>
							         <div class="col-sm-4 form-group  has-feedback pad-side-10">
							         	 <aui:select name="state" label="State" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px;"></aui:select>
							         </div>
							         <div class="col-sm-4 form-group  has-feedback pad-side-10">
							         	<aui:input name="city" label="City" value="${userBean.partyBean.partyAddressBean.townnm }" placeholder="City" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px;  width: 100%; border:1px solid #D3D3D3; border-radius: 1px;">
									     <aui:validator name="required" />
									     <aui:validator name="maxLength">50</aui:validator>
									   </aui:input>
							         </div>
						         </div>
						         <div class="col-sm-12 form-group  has-feedback">
							         <div class="col-sm-4 form-group  has-feedback" style="    padding: 0 10px 0 0;">
							         	 <aui:input name="zipcode" label="Zipcode" value="${userBean.partyBean.partyAddressBean.zipcd }" placeholder="Zipcode" cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px;  width: 100%; border:1px solid #D3D3D3; border-radius: 1px;">
										     <aui:validator name="required" />
										  </aui:input>
							         </div>
							         <div class="col-sm-4 form-group  has-feedback pad-side-10">
							         	<aui:input name="countryCode" label="Country Code" value="${userBean.partyBean.moblnrcntrycd }"   placeholder="Country Code" cssClass="form-control col-md-7 col-xs-6" readonly="true" style="font-size: 12px;  width: 100%; border:1px solid #D3D3D3; border-radius: 1px;">
									   	<aui:validator name="number" />
									   </aui:input>
							         </div>
							         <div class="col-sm-4 form-group  has-feedback pad-side-10">
							         	<aui:input name="mobile" label="Mobile" placeholder="Mobile" value="${userBean.partyBean.moblnr }"   cssClass="form-control col-md-7 col-xs-6" readonly="true" style="font-size: 12px;  width: 100%; border:1px solid #D3D3D3; border-radius: 1px;">
										     <aui:validator name="required" />
										     <aui:validator name="number" />
										     <aui:validator name="maxLength">10</aui:validator>
										 </aui:input>
							         </div>
						         </div>
      						</div>
      						<div class="col-xs-12 padding-0 border-1" style="padding: 20px;  font-size: 12px; background-color: #FFFFFF; margin-top: 20px;">
      							<div class="col-sm-12 padding-0 profile-detail" style="      margin-top: 15px;">
									       <c:choose>
									       	<c:when test="${userBean.partyBean.partyAddressBean.cntrycd eq 'CA'}">
									       		<span style="font-size: 14px; font-weight: bold;">Upload a government issued Bill (Utility Bill)</span><br/>
													<aui:input id="utility_bill" type="radio"
														name="documentVerificationType" value="utilityBill" label="" />
													<label for="<portlet:namespace/>utility_bill"
													style="color: black;     margin-top: 15px;">Utility Bill</label> 
									       	</c:when>
									       	<c:otherwise>
									       		<span style="font-size: 14px; font-weight: bold;     color: black;" >Upload a government issued photo ID (Drivers licence or passport prefered)</span><br/><br/>
							           	   		<aui:input id="drive_license"  type="radio" name="documentVerificationType" value="drive_license" label=""/>
						                   			 <label for="<portlet:namespace/>drive_license" style="    color: black;     margin-top: 15px;">Drivers License</label>
						                     		
						                   		<aui:input id="password"  type="radio" name="documentVerificationType" value="passport" label="" />
						                   		<label for="<portlet:namespace/>no" style="    color: black;     margin-top: 15px;">Passport</label>
									       	</c:otherwise>
									       </c:choose>
									       
						           		<div class="col-md-12 col-sm-6 col-xs-12">
							                  <input type="file" id="verificationDoc" name="verificationDoc" required="required" class="form-control col-md-7 col-xs-12 border-1 padding-5">
							            </div>
						           <hr/>
								</div>
      						</div>
      						<div class="col-xs-12 padding-0 border-1" style="padding: 20px;  font-size: 12px; background-color: #FFFFFF; margin-top: 20px;">
      							<div class="col-sm-12 padding-0 profile-detail" style=" ">
		
								 <span style="font-size: 14px; font-weight: bold; color: black;">Set up a funding account to send money (Debit card or bank account accepted)</span><br/>
								 <div id="carderrMsg" style="color:red; margin-left: 30px; marker-top: 10px;">
					                 </div>
								 <div class="col-sm-12 pad-0">
								 	 <div class="col-sm-6" style="    margin-top: 15px;">
									 	 <aui:input id="debit_card"  type="radio" name="accountType" value="debit_card" label=""/>
						                    <label for="<portlet:namespace/>debit_card" checked="true" style="    color: black;">Debit Card</label>
					                	<aui:input id="bank_account"  type="radio" name="accountType" value="bank" label="" style='margin-left: 20px;'/>
						                    <label for="<portlet:namespace/>bank_account"  style="    color: black;">Bank Account</label>
					                 </div>
					                 
					                 <div class="debit_card_detail">  
						                 <div class="col-sm-12 pad-0">	
						                 	 <div class="col-sm-9">
						                 	<aui:input name="cardNumber" label="Card Number" placeholder="Card Number"  cssClass="form-control col-md-7 col-xs-6"  style="font-size: 12px;  width: 100%; border:1px solid #D3D3D3; border-radius: 1px;     margin-left: 0px;">
									     		<aui:validator name="number" />
									    		 <aui:validator name="maxLength">20</aui:validator>
									   		</aui:input>
									   		</div>
									   		 <div class="col-sm-3 pad-0" style="    padding-top: 27px;">
										   		<div id="cardType">
										 	 		<span id="mastercard"><img src='<%= themeDisplay.getPathThemeImages() + "/mastercard_PNG6.png"%>' width="35px" height="20px;"></span>
										 	 		<span id="visa"><img src='<%= themeDisplay.getPathThemeImages() + "/visa-icon.png"%>' width="35px" height="20px;"></span>
										 	 	</div>
									 	 	</div>
						                 </div> 
						                 <div class="col-sm-6 card-fname-label pad-0">
						                 	<aui:input name="cardFirstName" label="First Name on Account" placeholder="First Name on Account"  cssClass="form-control col-md-7 col-xs-6" value="${ userBean.partyBean.accountBean.fName}" style="font-size: 12px;  width: 100%; border:1px solid #D3D3D3; border-radius: 1px;     margin-left: 15px;">
									    		 <aui:validator name="maxLength">50</aui:validator>
									   		</aui:input>
						                 </div> 
						                 <div class="col-sm-6 card-lname-label pad-0">
						                 	<aui:input name="cardLastNumber" label="Last Name on Account" placeholder="Last Name on Account"  cssClass="form-control col-md-7 col-xs-6" value="${ userBean.partyBean.accountBean.lName}" style="font-size: 12px;  width: 100%; border:1px solid #D3D3D3; border-radius: 1px;     margin-left: 20px;">
									    		 <aui:validator name="maxLength">50</aui:validator>
									   		</aui:input>
						                 </div> 
						                 <div class="col-sm-12 pad-0">
						                 <div class="col-sm-2">
						                 	<aui:select name="expireOnMonth" label="Expire On" >
						                 		<aui:option value="">MM</aui:option>
						                 		<aui:option value="00" selected="${userBean.partyBean.accountBean.exprymnth eq '00' ? true : false }">01</aui:option>
						                 		<aui:option value="01" selected="${userBean.partyBean.accountBean.exprymnth eq '01' ? true : false }">02</aui:option>
						                 		<aui:option value="02" selected="${userBean.partyBean.accountBean.exprymnth eq '02' ? true : false }">03</aui:option>
						                 		<aui:option value="03" selected="${userBean.partyBean.accountBean.exprymnth eq '03' ? true : false }">04</aui:option>
						                 		<aui:option value="04" selected="${userBean.partyBean.accountBean.exprymnth eq '04' ? true : false }">05</aui:option>
						                 		<aui:option value="05" selected="${userBean.partyBean.accountBean.exprymnth eq '05' ? true : false }">06</aui:option>
						                 		<aui:option value="06" selected="${userBean.partyBean.accountBean.exprymnth eq '06' ? true : false }">07</aui:option>
						                 		<aui:option value="07" selected="${userBean.partyBean.accountBean.exprymnth eq '07' ? true : false }">08</aui:option>
						                 		<aui:option value="08" selected="${userBean.partyBean.accountBean.exprymnth eq '08' ? true : false }">09</aui:option>
						                 		<aui:option value="09" selected="${userBean.partyBean.accountBean.exprymnth eq '09' ? true : false }">10</aui:option>
						                 		<aui:option value="10" selected="${userBean.partyBean.accountBean.exprymnth eq '10' ? true : false }">11</aui:option>
						                 		<aui:option value="11" selected="${userBean.partyBean.accountBean.exprymnth eq '11' ? true : false }">12</aui:option>
						                 	</aui:select>
						                 </div> 
						                 
						                 <div class="col-sm-2 pad-0">
						                 	<aui:select name="expireOnYear" label="" style="margin-left: 10px;     margin-top: 25px;">
						                 		<aui:option value="">YYYY</aui:option>
						                 		<aui:option value="18" selected="${userBean.partyBean.accountBean.expryyear eq '18' ? true : false }">2018</aui:option>
						                 		<aui:option value="19" selected="${userBean.partyBean.accountBean.expryyear eq '19' ? true : false }">2019</aui:option>
						                 		<aui:option value="20" selected="${userBean.partyBean.accountBean.expryyear eq '20' ? true : false }">2020</aui:option>
						                 		<aui:option value="21" selected="${userBean.partyBean.accountBean.expryyear eq '21' ? true : false }">2021</aui:option>
						                 		<aui:option value="22" selected="${userBean.partyBean.accountBean.expryyear eq '22' ? true : false }">2022</aui:option>
						                 		<aui:option value="23" selected="${userBean.partyBean.accountBean.expryyear eq '23' ? true : false }">2023</aui:option>
						                 		<aui:option value="24" selected="${userBean.partyBean.accountBean.expryyear eq '24' ? true : false }">2024</aui:option>
						                 		<aui:option value="25" selected="${userBean.partyBean.accountBean.expryyear eq '25' ? true : false }">2025</aui:option>
						                 		<aui:option value="26" selected="${userBean.partyBean.accountBean.expryyear eq '26' ? true : false }">2026</aui:option>
						                 		<aui:option value="27" selected="${userBean.partyBean.accountBean.expryyear eq '27' ? true : false }">2027</aui:option>
						                 		<aui:option value="28" selected="${userBean.partyBean.accountBean.expryyear eq '28' ? true : false }">2028</aui:option>
						                 	</aui:select>
						                 </div> 
						                 </div>
					                 </div>
								 </div>
								 <div class="col-sm-12">
					                 <div class="bank_detail">  
						                 <div class="col-sm-12 pad-0">
							                 <div class="col-sm-6 pad-0">
							                 	<aui:input name="accountNumber" label="Account Number" placeholder="Account Number"  cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px;  width: 100%; border:1px solid #D3D3D3; border-radius: 1px;     margin-left: 0px;" >
										   		</aui:input>
							                 </div> 
							                 <div class="col-sm-6 pad-0 re-accnt">
							                 	<aui:input name="accountNumber2" label="Re-enter Account Number" placeholder="Re-enter Account Number"  cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px;  width: 100%; border:1px solid #D3D3D3; border-radius: 1px;     margin-left: 7px;" >
										     		<aui:validator name="equalTo">'#<portlet:namespace />accountNumber'</aui:validator>
										   		</aui:input>
							                 </div>
						                 </div>
						                 <div class="col-sm-12 pad-0">
										 	 <div class="col-sm-3 pad-0">
											 	 <aui:input id="known_branch"  type="radio" name="searchBranchType" value="known_branch" label=""/>
								                    <label for="<portlet:namespace/>known_branch"><span id="unique_branch_code_label">Routing No.</span></label>
							                 </div>
							                 <div class="col-sm-9 pad-0">
											 	 <aui:input id="search"  type="radio" name="searchBranchType" value="search" label=""/>
								                    <label for="<portlet:namespace/>search" >Search</label>
							                 </div>
					                 	</div>
					                 	<div class="col-sm-12 pad-0">
					                 		<div class="col-sm-4 pad-0" id="known_branch_detail">
											 	 <aui:input type="text" name="unique_branch_code" label="Branch Code" ></aui:input>
							                </div>
							                <div class="col-sm-12 city-auto pad-0" id="search_branch_detail">
							                	<div class="col-sm-4 pad-0">
							                		<aui:input name="countryCity" label="City" style="font-size: 12px; " value="${userBean.partyBean.accountBean.branchBean.cityName}"/>
							                		
							                	</div>
							                	<div class="col-sm-4 pad-side-10">
								                	<aui:select name="bankName" label="Bank Name" style="font-size: 12px;">
								                	</aui:select>
								                </div>
						               			<div class="col-sm-4 pad-side-10" style="padding-left: 0px;">
						                			<aui:select name="branchName" label="Branch" style="font-size: 12px;">
								                	</aui:select>
						    		            </div>
							                </div>
						                </div> 
					                 </div>
					                 
					                 
								 </div>
								 <div class="col-sm-12 margin-top-15 profileBtn" style="font-size: 12px;">
					               <div class="new-transfer">Submit</div>
					           </div>
								</div>
      						</div>
      					</div>
      				</div>
      			</div>
      		</div>
      </div>				
		<aui:input type="hidden" name="userId" value="${customUserBean.userId}"/>
   </aui:form>
  
<script>
var cityData;
</script>
   
<aui:script>

AUI().use('aui-base','aui-form-validator', 'aui-io-request','node-event-simulate', 'autocomplete-list',
		'autocomplete-filters', 'autocomplete-highlighters', 'aui-autocomplete', function(A) {
	var profileBtn = A.one('.profileBtn');
	profileBtn.on('click', function(e) {
		var formValidator = Liferay.Form.get('<portlet:namespace />profileFm').formValidator;
		formValidator.validate();
		dropDownValidator.validate();
		if(!formValidator.hasErrors() && !dropDownValidator.hasErrors()){
			if(A.one("#<portlet:namespace />debit_card")._node.checked){
				debitCardValidator.validate();
				if(!isValidCard){
					$("#carderrMsg").text("Only debit cards are accepted");
					return;
				}
				if(!debitCardValidator.hasErrors()){
					document.<portlet:namespace />profileFm.submit();		
				}
			}else{
				if(A.one("#<portlet:namespace />search")._node.checked){
					bankValidator.validate();
					if(!bankValidator.hasErrors()){
						document.<portlet:namespace />profileFm.submit();		
					}	
				}else{
					document.<portlet:namespace />profileFm.submit();
				}
				
				
			}
		}
	});
	
	var accountTypeBtns = A.all("input[name=<portlet:namespace />accountType]");
	var bankReadioBtn = A.one("#<portlet:namespace />bank_account");
	var debitRadioBtn = A.one("#<portlet:namespace />debit_card");
	var bankSelect = A.one("#<portlet:namespace />bankName");
	var countrySelect = A.one("#<portlet:namespace />country");
	var stateSelect = A.one("#<portlet:namespace />state");
	var citySelect = A.one("#<portlet:namespace />countryCity");
	var cardNumber = A.one("#<portlet:namespace />cardNumber");
	var searchBranchTypeBtns = A.all("input[name=<portlet:namespace />searchBranchType]");
	var knownBranchBtn = A.one("#<portlet:namespace />known_branch");
	var searchBtn = A.one("#<portlet:namespace />search");
	var isValidCard = false;
	var accountNumber = '${ userBean.partyBean.accountBean.acctnr}';
	A.one("#<portlet:namespace />known_branch")._node.checked=true;
	A.one("#<portlet:namespace />search")._node.checked=true;
	A.one("#known_branch_detail").hide();
	A.one("#search_branch_detail").show();
	A.one(".debit_card_detail").show();
	A.one(".bank_detail").hide();
	$("#mastercard").hide();
	$("#visa").hide();
	
	var accountType='${ userBean.partyBean.accountBean.accountCategory}';
	A.one("#<portlet:namespace />debit_card")._node.checked=true;
	var citySelected = '${userBean.partyBean.accountBean.branchBean.cityId}';
	var selectedBranchId = '${userBean.partyBean.accountBean.branchBean.branchId}';
	var selectedBankId = '${userBean.partyBean.accountBean.branchBean.bankId}';
	var selectedStateCode = '${userBean.partyBean.partyAddressBean.state}';
	
	
	console.log("citySelected->" + citySelected + 
			" selectedBankId->" + selectedBankId  +" selectedBranchId->" + selectedBranchId);
	
	if(accountType==1 || accountType=="1"){
		A.one("#<portlet:namespace />bank_account")._node.checked=true;
		A.one(".bank_detail").show();
		A.one(".debit_card_detail").hide();
	}else{
		A.one("#<portlet:namespace />debit_card")._node.checked=true;
		A.one(".bank_detail").hide();
		A.one(".debit_card_detail").show();
	}
	
	for(var i = 0; i < accountTypeBtns._nodes.length; i++) {
		accountTypeBtns._nodes[i].onclick = function() {
			
			if(this.checked && this.getAttribute('id').indexOf('bank_account')>=0){
				A.one(".bank_detail").show();
				A.one(".debit_card_detail").hide();
			}else{
				A.one(".bank_detail").hide();
				A.one(".debit_card_detail").show();
			}
    	};
	}
	
	countrySelect.on('change', function(e) {
		console.log("going to simulate country");
		var country = this.get('value');
		var countryCode ='';
		var countryInitialPrefix='1';
		if(country && country.length>0){
			countryCode = country.split(",")[0];
			countryInitialPrefix = country.split(",")[1];
			if(countryCode!="IN"){
				A.one("#unique_branch_code_label").text("Routing No.");
			}else{
				A.one("#unique_branch_code_label").text("IFSC Code");
			}
			
			var  getStateDetailURL = '${getStateDetailURL}';
			A.io.request(getStateDetailURL.toString(),{
				dataType: 'json',
				method: 'GET',
				data :{
					'<portlet:namespace/>countryCode' : countryCode
				},
				on: {
				success: function() {
					A.one('#<portlet:namespace/>state').all('option').remove();
					A.one('#<portlet:namespace/>countryCity').text('');
					A.one('#<portlet:namespace/>bankName').all('option').remove();
					A.one('#<portlet:namespace/>branchName').all('option').remove();
					
					
					var stateList=this.get('responseData');
					
					A.one('#<portlet:namespace/>state').append("<option  value='' >Select State</option>");
					var selectedState='';
					for(var i in stateList){
						A.one('#<portlet:namespace/>state').append('<option value="'+ stateList[i].statecd  +'">'+ stateList[i].statedesc +'</option>');
						if(stateList[i].statecd==selectedStateCode){
							selectedState = stateList[i];
						}
					}
					
					if(selectedState){
						A.one('#<portlet:namespace/>state').val(selectedState.statecd);
					}
				}
			  }
			});
		}
		
		$("#<portlet:namespace/>mobileCountryCode").val("+"+countryInitialPrefix);

	});
	
	var cityData;
	var cityAutoComplete = new A.AutoCompleteList({
		allowBrowserAutocomplete: false,
		activateFirstItem: 'true',
		inputNode: '#<portlet:namespace />countryCity',
		resultTextLocator:'cityDesc',
		render: 'true',
		resultHighlighter: 'phraseMatch',
		resultFilters:['phraseMatch'],
		source:function(){
			
			var inputValue=A.one("#<portlet:namespace />countryCity").get('value');
				if(inputValue.length>3){
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
		simulateBanksOnCityChange(e.result.raw.cityId,e.result.raw.cntryCd);
	});
	
	cityAutoComplete.render();
	
	function simulateBanksOnCityChange(cityId, countryCode){
		
		console.log("simulateBanksOnCityChange->" + cityId + "," + countryCode);
		
		var  getBankDetailURL = '${getBankDetailURL}';
		A.io.request(getBankDetailURL.toString(),{
			dataType: 'json',
			method: 'GET',
			data :{
				'<portlet:namespace/>cityCode' : cityId,
				'<portlet:namespace/>cntryCode' : countryCode
			},
			on: {
			success: function() {
				A.one('#<portlet:namespace/>bankName').all('option').remove();
				A.one('#<portlet:namespace/>branchName').all('option').remove();

				var bankList=this.get('responseData');
				var selectedBank='';
				A.one('#<portlet:namespace/>bankName').append("<option  value='' >Select Bank</option>");
				for(var i=0;i<bankList.length;i++){
					if(selectedBankId != ''){
						console.log("selectedBankId->" + selectedBankId + " bankList[i].bankId->" + bankList[i].bankId + " ->" + bankList[i].bankNm);
						console.log("comparing bank ->"+ selectedBankId==(bankList[i].bankId));
				    	if(selectedBankId==(bankList[i].bankId)){
				    		selectedBank = bankList[i];
						}
				    }
					A.one('#<portlet:namespace/>bankName').append('<option value="'+ bankList[i].bankId +','+ bankList[i].bankNm +'">'+ bankList[i].bankNm +'</option>');
				}
				
				if(selectedBank!=""){
					A.one('#<portlet:namespace/>bankName').val(selectedBank.bankId+","+selectedBank.bankNm);
					console.log("simulating bank ->" + A.one('#<portlet:namespace/>bankName').val());
					bankSelect.simulate('change');
				}	
			}
		  }
		});
	}
	
	stateSelect.on('change', function(e) {
		A.one('#<portlet:namespace/>countryCity').text('');
		A.one('#<portlet:namespace/>bankName').all('option').remove();
		A.one('#<portlet:namespace/>branchName').all('option').remove();
	});
	
	bankSelect.on('change', function(e) {
		var  getBranchDetailURL = '${getBranchDetailURL}';
		A.io.request(getBranchDetailURL.toString(),{
			dataType: 'json',
			method: 'GET',
			data :{
				'<portlet:namespace/>bankCode' : this.get('value')
			},
			on: {
			success: function() {
				A.one('#<portlet:namespace/>branchName').all('option').remove();
				var branchList=this.get('responseData');
				A.one('#<portlet:namespace/>branchName').append("<option  value='' >Select Branch</option>");
				var selectedBranch='';
				for(var i in branchList){
					if(selectedBranchId != ''){
				    	if(selectedBranchId==branchList[i].branchId){
				    		selectedBranch = branchList[i];
						}
				    }
					A.one('#<portlet:namespace/>branchName').append('<option value="'+ branchList[i].branchId +','+ branchList[i].branchRtngnr +'">'+ branchList[i].branchDes +'</option>');
				}
				
				if(selectedBranch!=""){
					A.one('#<portlet:namespace/>branchName').val(selectedBranch.branchId+","+selectedBranch.branchRtngnr);
				}	
				
			}
		  }
		
		});
	});
	
	cardNumber.on('keyup', function(e) {
		console.log("cardnumber->" + this.get('value'));
		var cardNumber = this.get('value');
		$("#carderrMsg").text('');
		if(cardNumber && cardNumber.length>=4){
			var  getCardDetailURL = '${getCardDetailURL}';
			A.io.request(getCardDetailURL.toString(),{
				dataType: 'json',
				method: 'GET',
				data :{
					'<portlet:namespace/>cardNumber' : cardNumber
				},
				on: {
				success: function() {
					var cardDetail=this.get('responseData');
					console.log("cardDetail->" + cardDetail.type);
					if(cardDetail.type && cardDetail.type!="" && cardDetail.type!="DEBIT"){
						$("#carderrMsg").text("Only debit cards are accepted");
						$("#mastercard").hide();
						$("#visa").hide();
						isValidCard=false;
					}
					if(cardDetail && cardDetail.scheme=="VISA"){
						$("#mastercard").hide();
						$("#visa").show();
						isValidCard=true;
					}else if(cardDetail && cardDetail.scheme=="MASTERCARD"){
						$("#mastercard").show();
						$("#visa").hide();
						isValidCard=true;
					}
					
				}
			  }
			
			});
		}else{
			$("#carderrMsg").text('');
			$("#mastercard").hide();
			$("#visa").hide();
		}
	});
	
	for(var i = 0; i < searchBranchTypeBtns._nodes.length; i++) {
		searchBranchTypeBtns._nodes[i].onclick = function() {
			
			if(this.checked && this.getAttribute('id').indexOf('known_branch')>=0){
				A.one("#known_branch_detail").show();
				A.one("#search_branch_detail").hide();
			}else{
				A.one("#known_branch_detail").hide();
				A.one("#search_branch_detail").show();
			}
    	};
	}
	
	new Formatter(document.getElementById('<portlet:namespace/>'+'mobile'), {
		'pattern': '({{999}})-{{999}}-{{9999}}',
		'persistent': false
	});
	
	
	var searchBranchCodeValidator = new A.FormValidator({
		boundingBox: document.<portlet:namespace/>profileFm,
		rules: {
			<portlet:namespace/>countryState: {
				required: true
			},
			<portlet:namespace/>countryCity: {
				required: true
			},
			<portlet:namespace/>bankName: {
				required: true
			},
			<portlet:namespace/>branchName: {
				required: true
			}
		}
	});
	
	var knownBranhcValidator = new A.FormValidator({
		boundingBox: document.<portlet:namespace/>profileFm,
		rules: {
			<portlet:namespace/>unique_branch_code: {
				required: true
			}
		}
	});
	
	if(accountType==1){
		var country = countrySelect.val();
		if(parseInt(citySelected)>0){
			var countryCode = '${userBean.partyBean.accountBean.branchBean.cntryCd}';			
			simulateBanksOnCityChange(citySelected,countryCode);
		}
		A.one("#<portlet:namespace/>accountNumber").val(accountNumber);
		A.one("#<portlet:namespace/>accountNumber2").val(accountNumber);
	}

	if(accountType==2){
		A.one("#<portlet:namespace/>cardNumber").val(accountNumber);
		cardNumber.simulate('keyup');
	}
	
	countrySelect.simulate('change');
	
	
	var debitCardValidator = new A.FormValidator({
		boundingBox: document.<portlet:namespace/>profileFm,
		rules: {
			<portlet:namespace/>cardNumber: {
				required: true
			},
			<portlet:namespace/>cardFirstName: {
				required: true
			},
			<portlet:namespace/>cardLastNumber: {
				required: true
			},
			<portlet:namespace/>expireOnMonth: {
				required: true
			},
			<portlet:namespace/>expireOnYear: {
				required: true
			}
			
		}
	});
	
	var bankValidator = new A.FormValidator({
		boundingBox: document.<portlet:namespace/>profileFm,
		rules: {
			<portlet:namespace/>bankName: {
				required: true
			},
			<portlet:namespace/>branchName: {
				required: true
			},
			<portlet:namespace/>accountNumber: {
				required: true
			},
			<portlet:namespace/>accountNumber2: {
				required: true
			}
		}
	});
	
	var dropDownValidator = new A.FormValidator({
		boundingBox: document.<portlet:namespace/>profileFm,
		rules: {
			<portlet:namespace/>state: {
				required: true
			}
		}
	});
	
	$('#<portlet:namespace/>accountNumber,#<portlet:namespace/>accountNumbe2').bind('keyup paste', function(){
		this.value = this.value.replace(/[^0-9]/g, '');
    });
	
});
</aui:script>
	