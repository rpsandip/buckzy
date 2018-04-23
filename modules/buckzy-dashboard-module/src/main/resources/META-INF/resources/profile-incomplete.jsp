<%@ include file="/init.jsp" %>

<portlet:actionURL var="uploadDocumentURL" name="/upload_Document"/>
<portlet:actionURL var="updateAccountURL" name="/update_Account"/>
<liferay-ui:success key="document-upload-success" message="document-upload-success"/>
<liferay-ui:success key="account-success" message="account-success"/>
<portlet:resourceURL id="/getcard_detail" var="getCardDetailURL"></portlet:resourceURL>
<portlet:resourceURL id="/getcity_detail" var="getCityDetailURL"></portlet:resourceURL>
<portlet:resourceURL id="/getbank_detail" var="getBankDetailURL"></portlet:resourceURL>
<portlet:resourceURL id="/getbranch_detail" var="getBranchDetailURL"></portlet:resourceURL>


<div style="display: table-cell; padding-bottom: 50px; ">
                    <div class="main-container">
                        <div class="col-sm-12" style="color: #961622; font-size: 14px; font-weight: bold;">
                            You are almost there! We require additional information to complete your account setup
                        </div>
                        <div class="col-sm-4" style="margin-top: 20px; font-weight: bold;">
                            Profile ID: ${userBean.partyBean.prtyid }
                        </div>
                        <div class="col-sm-4" style="color: #961622; font-size: 14px; margin-top: 20px; font-weight: bold;">
                            ${userBean.firstName } ${userBean.lastName }
                        </div>
                        <div class="col-sm-4" style="margin-top: 20px;">
                        	<%
                        		String ucntry = (String)renderRequest.getAttribute("userCountry");
                        	%>
                            <img src="<%= themeDisplay.getPathThemeImages() + "/flag/"+ ucntry + ".png"%>"  height="50" width="50"/>
                        </div>
                       
                        <div class="col-sm-12" style="border: 3px solid #F2F2F2; border-left: 3px solid #11AB3E; border-radius: 5px; padding: 20px 10px; margin-top: 20px;">
                            <table style="width: 100%;" >
                                <tr>
                                    <td style="width: 25px;">
                                        <div style="width:20px; height: 20px; border-radius: 50%; background-color: #394353; color: #FFFFFF; padding-left: 6px; ">1</div>
                                    </td>
                                    <td style="padding-left: 10px; font-weight: bold;">
                                        Contact information
                                    </td>
                                    <td style="color: #308151; text-align: center; font-size: 12px; ">
                                        <i class="fa fa-mobile" aria-hidden="true" style="font-size: 28px; color: #000000;"></i><br/>
                                        Verified
                                    </td>
                                    <td style="color: #308151; text-align: center; font-size: 12px; ">
                                        <i class="fa fa-envelope" aria-hidden="true" style="font-size: 24px; color: #000000;"></i><br/>
                                        Verified
                                    </td>
                                    <td style="width: 50px; color: #308151; text-align: center; ">
                                        <i class="fa fa-check" aria-hidden="true" style="font-size: 20px; "></i>
                                    </td>
                                </tr>
                            </table>
                           
                        </div>
                        
                        <div class="col-sm-12" style="border: 3px solid #F2F2F2; border-left: 3px solid #EE0D16; border-radius: 5px; padding: 20px 10px; margin-top: 20px;">
                            <aui:form name="uploadDocFm" action="${uploadDocumentURL}" cssClass="form-horizontal form-label-left" enctype="multipart/form-data">
                            <table style="width: 100%; cursor: pointer;" id="upload1">
                                <tr>
                                    <td style="width: 25px;">
                                        <div style="width:20px; height: 20px; border-radius: 50%; background-color: #394353; color: #FFFFFF; padding-left: 6px; ">2</div>
                                    </td>
                                    <td style="padding-left: 10px;">
                                        <span style="font-size: 14px; font-weight: bold;" >Upload a government issued photo ID (Drivers licence or passport prefered)</span><br/>
                                        <aui:input id="drive_license"  type="radio" name="documentVerificationType" value="drive_license" label=""/>
						                    <label for="<portlet:namespace/>drive_license" style="    color: black;">Drivers License</label>
						                     		
						                <aui:input id="password"  type="radio" name="documentVerificationType" value="passport" label="" />
						                   <label for="<portlet:namespace/>no" style="    color: black;">Passport</label>
                                    </td>
                                    <td style="width: 50px; color: #308151; text-align: center; ">
                                        <c:choose>
                                        	<c:when test="${userBean.customUserBean.documentVerified }">
                                        		<i class="fa fa-check" aria-hidden="true" style="font-size: 20px; "></i>
                                        	</c:when>
                                        	<c:otherwise>
                                        		<i class="fa fa-exclamation-triangle" aria-hidden="true" style="font-size: 20px; color: #FFCC00; "></i>
                                        	</c:otherwise>
                                        </c:choose>
                                        
                                    </td>
                                </tr>
                            </table>
                            <div id="upload1-expand2" style="margin-left: 30px; margin-top: 30px;">
                                 
                                 <table>
                                     <tr>
                                         <td>
                                             Upload
                                         </td>
                                         <td class="padding-left-10">
							                  <input type="file" id="verificationDoc" name="verificationDoc" required="required" class="border-1 padding-5">
                                         </td>
                                         <td class="padding-left-10">
                                             <div style="width: 30px; height: 30px; background-color: #ED1C24; border-radius: 50%; padding-left: 9px"; padding-top: 5px;">
                                                 <i class="fa fa-upload" aria-hidden="true" style="font-size: 14px; color:  #FFFFFF;"></i>
                                             </div>
                                         </td>
                                     </tr>
                                     <tr>
                                         <td style="text-align: left; padding-top: 20px; padding-left: 36px;"  colspan="3">
                                             <button class="submit uploadDoc">Submit</button>
                                         </td>
                                     </tr>
                                </table>
                            </div>
                            </aui:form>
                        </div>
                        
                        <div class="col-sm-12" style="border: 3px solid #F2F2F2; border-left: 3px solid #EE0D16; border-radius: 5px; padding: 20px 10px; margin-top: 20px;">
                            <aui:form name="accountFm" action="${updateAccountURL}" cssClass="form-horizontal form-label-left" >
                            <table style="width: 100%;">
                                <tr>
                                    <td style="width: 25px;">
                                        <div style="width:20px; height: 20px; border-radius: 50%; background-color: #394353; color: #FFFFFF; padding-left: 6px; ">3</div>
                                    </td>
                                    <td style="padding-left: 10px;">
                                        <span style="font-size: 14px; font-weight: bold;">Set up a funding account to send money (Debit card or bank account accepted)</span><br/>
                                        <div style="margin-top: 15px;">
                                        <aui:input id="debit_card"  type="radio" name="accountType" value="debit_card" label=""/>
						                    <label for="<portlet:namespace/>debit_card" checked="true" style="    color: black;">Debit Card</label>
					                	<aui:input id="bank_account"  type="radio" name="accountType" value="bank" label=""/>
						                    <label for="<portlet:namespace/>bank_account">Bank Account</label>
						                </div>
                                    </td>
                                    <td style="width: 50px; color: #308151; text-align: center; ">
                                        <c:choose>
                                        	<c:when test="${userBean.customUserBean.accountCompleted }">
                                        		<i class="fa fa-check" aria-hidden="true" style="font-size: 20px; "></i>
                                        	</c:when>
                                        	<c:otherwise>
                                        		<i class="fa fa-exclamation-triangle" aria-hidden="true" style="font-size: 20px; color: #FFCC00; "></i>
                                        	</c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </table>
                            <div id="debit_card_detail" style="margin-left: 30px; margin-top: 30px;">
                                 <div id="carderrMsg" style="color:red;"></div>
                                <table>
                                    <tr>
                                        <td>
                                            Card number
                                        </td>
                                        <td class="padding-left-10">
                                            
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <aui:input name="cardNumber" label="" placeholder="Card Number"  cssClass="form-control col-md-7 col-xs-6" value="${ userBean.partyBean.accountBean.acctnr}">
									     		<aui:validator name="number" />
									    		 <aui:validator name="maxLength">20</aui:validator>
									   		</aui:input>
                                        </td>
                                        <td class="padding-left-20">
                                            <span id="visa" style="display:none;"><img src="<%= themeDisplay.getPathThemeImages() + "/visa.jpg"%>" width="40" height="25"  style="margin-bottom: 14px;"/></span>
                                            <span id="mastercard" style="display: none;"><img src="<%= themeDisplay.getPathThemeImages() + "/mastercard.jpg"%>" width="40" height="25"  style="margin-bottom: 14px;"/></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="">
                                            First name on account
                                        </td>
                                        <td style="padding-left: 26px;">
                                            Last name on Account
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                           <aui:input name="cardFirstName" label="" placeholder="First Name"  cssClass="form-control col-md-7 col-xs-6" value="${ userBean.partyBean.accountBean.fName}" >
									    		 <aui:validator name="maxLength">50</aui:validator>
									   		</aui:input>
                                        </td>
                                        <td style="padding-left: 40px;">
                                            <aui:input name="cardLastNumber" label="" placeholder="Last Name"  cssClass="form-control col-md-7 col-xs-6" value="${ userBean.partyBean.accountBean.lName}" style="color:#9B9A9B; width: 100%; border:1px solid #D3D3D3; border-radius: 1px;">
									    		 <aui:validator name="maxLength">50</aui:validator>
									   		</aui:input>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="padding-top-10">
                                            Expires on
                                        </td>
                                        <td class="padding-left-20 padding-top-10">
                                            
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <div class="col-sm-12">
                                            	<div class="col-sm-6">
                                            		<aui:select name="expireOnMonth" label="" style="font-size: 12px; color:#9B9A9B; margin-left: -25px;">
								                 		<aui:option value="">MM</aui:option>
								                 		<aui:option value="00" selected="${userBean.partyBean.accountBean.exprymnth eq '00' ? true : false }">00</aui:option>
								                 		<aui:option value="01" selected="${userBean.partyBean.accountBean.exprymnth eq '01' ? true : false }">01</aui:option>
								                 		<aui:option value="02" selected="${userBean.partyBean.accountBean.exprymnth eq '02' ? true : false }">02</aui:option>
								                 		<aui:option value="03" selected="${userBean.partyBean.accountBean.exprymnth eq '03' ? true : false }">03</aui:option>
								                 		<aui:option value="04" selected="${userBean.partyBean.accountBean.exprymnth eq '04' ? true : false }">04</aui:option>
								                 		<aui:option value="05" selected="${userBean.partyBean.accountBean.exprymnth eq '05' ? true : false }" >05</aui:option>
								                 		<aui:option value="06" selected="${userBean.partyBean.accountBean.exprymnth eq '06' ? true : false }">06</aui:option>
								                 		<aui:option value="07" selected="${userBean.partyBean.accountBean.exprymnth eq '07' ? true : false }">07</aui:option>
								                 		<aui:option value="08" selected="${userBean.partyBean.accountBean.exprymnth eq '08' ? true : false }">08</aui:option>
								                 		<aui:option value="09" selected="${userBean.partyBean.accountBean.exprymnth eq '09' ? true : false }">09</aui:option>
								                 		<aui:option value="10" selected="${userBean.partyBean.accountBean.exprymnth eq '10' ? true : false }">10</aui:option>
								                 		<aui:option value="11" selected="${userBean.partyBean.accountBean.exprymnth eq '11' ? true : false }">11</aui:option>
						                 			</aui:select>
                                            	</div>
                                            	<div class="col-sm-6">
                                            		<aui:select name="expireOnYear" label="" style="font-size: 12px; color:#9B9A9B;">
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
                                        </td>
                                        <td class="padding-left-20">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="text-align: right;" class="padding-top-10">
                                            
                                        </td>
                                    </tr>
                                    <tr>
                                       <td style="text-align: left; padding-top: 20px;" colspan="3">
                                             <button class="submit updateAccountCardBtn" style="margin-left: 0px;">Submit</button>
                                         </td>
                                    </tr>
                                </table>
                            </div>
                            <div id="bank_detail" style="display:none; margin-left: 30px; margin-top: 30px;">
                                <table>
                                    <tr>
                                        <td>
                                            <aui:input name="accountNumber" label="Account Number" placeholder="Account Number"  cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px; color:#9B9A9B; width: 100%; border:1px solid #D3D3D3; border-radius: 1px;     margin-left: 15px;" value="${ userBean.partyBean.accountBean.acctnr}">
									     		<aui:validator name="required" />
									   		</aui:input>
                                        </td>
                                        <td style="padding-left: 52px;">
                                           <aui:input name="accountNumber2" label="Re Enter Account Number" placeholder="Re-enter Account Number"  cssClass="form-control col-md-7 col-xs-6" style="font-size: 12px; color:#9B9A9B; width: 100%; border:1px solid #D3D3D3; border-radius: 1px;     margin-left: 7px;" value="${ userBean.partyBean.accountBean.acctnr}">
									     		<aui:validator name="equalTo">'#<portlet:namespace />accountNumber'</aui:validator>
									   		</aui:input>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="padding-top-10">
                                            <aui:input id="known_branch"  type="radio" name="searchBranchType" value="known_branch" label=""/>
								                    <label for="<portlet:namespace/>known_branch"><span id="unique_branch_code_label">Routing No.</span></label>
                                        </td>
                                        <td class="padding-left-20 padding-top-10">
                                            <aui:input id="search"  type="radio" name="searchBranchType" value="search" label=""/>
								                    <label for="<portlet:namespace/>search" >Search</label>
                                        </td>
                                    </tr>
                                   <tr>
                                        <td colspan="2">
                                        	<div class="col-sm-3" id="known_branch_detail">
											 	 <aui:input type="text" name="unique_branch_code" label="Branch Code" ></aui:input>
							                </div>
                                        	<div class="col-sm-9" id="search_branch_detail">
							                	<div class="col-sm-4 city-auto">
							                		<aui:input name="countryCity" label="City" style="font-size: 12px; color:#9B9A9B;" value="${userBean.partyBean.accountBean.branchBean.cityName}"/>
							                		
							                	</div>
							                	<div class="col-sm-4">
								                	<aui:select name="bankName" label="Bank Name" style="font-size: 12px; color:#9B9A9B; margin-left:10px; ">
								                	</aui:select>
								                </div>
						               			<div class="col-sm-4">
						                			<aui:select name="branchName" label="Branch" style="font-size: 12px; color:#9B9A9B; margin-left:20px;">
								                	</aui:select>
						    		            </div>
							                </div>
                                        </td>
                                    </tr>
                                     <tr>
                                         <td style="text-align: left; padding-top: 20px;" colspan="3">
                                             <button class="submit updateAccountBankBtn" style="margin-left: 0px;">Submit</button>
                                         </td>
                                     </tr>
                                </table>
                            </div>
                            </aui:form>
                        </div>
                    </div>
                </div>
                <div style="display: table-cell;"></div>
                
                
<aui:script>

AUI().use('aui-base','aui-form-validator', 'aui-io-request','node-event-simulate', 'autocomplete-list',
		'autocomplete-filters', 'autocomplete-highlighters', 'aui-autocomplete', function(A) {

	var submitDocButton = A.one(".uploadDoc");
	var accountTypeBtns = A.all("input[name=<portlet:namespace />accountType]");
	var searchBranchTypeBtns = A.all("input[name=<portlet:namespace />searchBranchType]");
	var cardNumber = A.one("#<portlet:namespace />cardNumber");
	var bankSelect = A.one("#<portlet:namespace />bankName");
	var stateSelect = A.one("#<portlet:namespace />countryState");
	var citySelect = A.one("#<portlet:namespace />countryCity");
	var citySelected = '${userBean.partyBean.accountBean.branchBean.cityId}';
	var selectedBranchId = '${userBean.partyBean.accountBean.branchBean.branchId}';
	var selectedBankId = '${userBean.partyBean.accountBean.branchBean.bankId}';
	var accountType='${ userBean.partyBean.accountBean.accountCategory}';
	
	A.one("#<portlet:namespace />search")._node.checked=true;
	A.one("#known_branch_detail").hide();
	A.one("#search_branch_detail").show();
	
	console.log("accountType->" + accountType);
	
	submitDocButton.on('click', function(e) {
		var formValidator = Liferay.Form.get('<portlet:namespace />uploadDocFm').formValidator;
		formValidator.validate();
		if(!formValidator.hasErrors()){
			document.<portlet:namespace />uploadDocFm.submit();	
		}
	});
	
	var updateAccountCardBtn = A.one('.updateAccountCardBtn');
	var updateAccountBankBtn = A.one('.updateAccountBankBtn');
	
	updateAccountCardBtn.on('click', function(e) {
			console.log("updateAccountCardBtn -> click");
		if(A.one("#<portlet:namespace />debit_card")._node.checked){
				console.log("debitCard validator");
				debitCardValidator.validate();
				console.log("debitCardValidator.hasErrors()->" + debitCardValidator.hasErrors());
				if(!debitCardValidator.hasErrors()){
					document.<portlet:namespace />accountFm.submit();		
				}
		 }
	});
	
	updateAccountBankBtn.on('click', function(e) {
	
	console.log("updateAccountBankBtn -> click");
	if(A.one("#<portlet:namespace />bank_account")._node.checked){
			
		if(A.one("#<portlet:namespace />search")._node.checked){
			bankValidator.validate();
			console.log("bankValidator" + bankValidator.hasErrors());
			if(!bankValidator.hasErrors()){
				document.<portlet:namespace />accountFm.submit();		
			}	
		}else{
			
			knownBranhcValidator.validate();
			console.log("knownBranhcValidator" + knownBranhcValidator.hasErrors());
			if(!knownBranhcValidator.hasErrors()){
				document.<portlet:namespace />accountFm.submit();
			}
		}
	}
});
	
	if(accountType==1 || accountType=="1"){
		A.one("#<portlet:namespace />bank_account")._node.checked=true;
		A.one("#bank_detail").show();
		A.one("#debit_card_detail").hide();
		console.log("in accountype 1");
	}else{
		A.one("#<portlet:namespace />debit_card")._node.checked=true;
		A.one("#bank_detail").hide();
		A.one("#debit_card_detail").show();
	}
	
	
	for(var i = 0; i < accountTypeBtns._nodes.length; i++) {
		accountTypeBtns._nodes[i].onclick = function() {
			if(this.getAttribute('id').indexOf('bank_account')>=0){
				A.one("#bank_detail").show();
				A.one("#debit_card_detail").hide();
			}else{
				A.one("#bank_detail").hide();
				A.one("#debit_card_detail").show();
			}
    	};
	}
	
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
					console.log("cardDetail->" + cardDetail);
					if(cardDetail.type && cardDetail.type!="" && cardDetail.type!="DEBIT"){
						$("#carderrMsg").text("Only debit cards are accepted");
						$("#mastercard").hide();
						$("#visa").hide();
					}
					if(cardDetail && cardDetail.scheme=="VISA"){
						$("#mastercard").hide();
						$("#visa").show();
					}else if(cardDetail && cardDetail.scheme=="MASTERCARD"){
						$("#mastercard").show();
						$("#visa").hide();
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
	
	
	var knownBranhcValidator = new A.FormValidator({
		boundingBox: document.<portlet:namespace/>accountFm,
		rules: {
			
		}
	});
	
	var bankValidator = new A.FormValidator({
		boundingBox: document.<portlet:namespace/>accountFm,
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
	
	
	var debitCardValidator = new A.FormValidator({
		boundingBox: document.<portlet:namespace/>accountFm,
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
	
	if(selectedBranchId!=""){
		if(parseInt(citySelected)>0){
			var countryCode = '${userBean.partyBean.accountBean.branchBean.cntryCd}';			
			simulateBanksOnCityChange(citySelected,countryCode);
		}
	}
	
});
</aui:script>