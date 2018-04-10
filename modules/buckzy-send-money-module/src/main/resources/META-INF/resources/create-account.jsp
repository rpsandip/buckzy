<%@ include file="/init.jsp" %>

<portlet:actionURL var="createAccountURL" name="/create-account"/>
<portlet:resourceURL id="/getbranch_detail" var="getBranchDetailURL"></portlet:resourceURL>
<portlet:resourceURL id="/getstate_detail" var="getStateDetailURL"></portlet:resourceURL>
<portlet:resourceURL id="/getcity_detail" var="getCityDetailURL"></portlet:resourceURL>
<portlet:resourceURL id="/getbank_detail" var="getBankDetailURL"></portlet:resourceURL>


<liferay-ui:error key="account-created-success" message="account-created-success"/>

<div class="row padding-0 margin-0">
<div class="col-sm-6 margin-bottom-20" style="color: #000000; font-weight: bold;  font-size: 18px; margin-bottom: 10px;">Receiver Personal Information</div>

<aui:form name="newAccountFm" action="${createAccountURL}" cssClass="form-horizontal form-label-left">
<div class="" style="color:#9B9A9B; font-size: 10px;">    
<div class="col-sm-6 margin-bottom-20">
	<aui:select name="country" label="Country" style="height: 37px; width: 100%;">
		<c:forEach items="${countryJsonList }" var="country">
              	 	<aui:option value="${country.get('cntrycd') },${country.get('intldialprfx') } , ${country.get('cntrydesc') }">${country.get('cntrydesc') }</aui:option>
        </c:forEach>
	</aui:select>
</div>

<div class="col-sm-6 ">
		<aui:input name="firstName" label="First Name" placeholder="Receiver's First Name" style="font-size: 9px; color:#9B9A9B; ">
			     <aui:validator name="required" />
			     <aui:validator name="maxLength">50</aui:validator>
	    </aui:input>
</div>
<div class="col-sm-6 ">
		 <aui:input name="lastName" label="Last Name" placeholder="Receiver's last name" style="font-size: 9px; color:#9B9A9B; ">
			     <aui:validator name="required" />
			     <aui:validator name="maxLength">50</aui:validator>
		</aui:input>
</div>
<div class="col-sm-12 ">
		<aui:input name="email" label="Email address" placeholder="Receiver's Email Address" style="font-size: 9px; color:#9B9A9B; ">
			     <aui:validator name="required" />
			     <aui:validator name="email" />
			     <aui:validator name="maxLength">100</aui:validator>
	    </aui:input>
</div>
<div class="col-sm-12 ">
	<aui:input name="address" label="Address" placeholder="Street address" style="font-size: 9px; color:#9B9A9B; ">
		      <aui:validator name="required" />
		     <aui:validator name="maxLength">100</aui:validator>
	</aui:input>
</div>
	
<div class="col-sm-6 ">
	<aui:input name="city" label="City" placeholder="city" style="font-size: 9px; color:#9B9A9B; ">
		      <aui:validator name="required" />
		     <aui:validator name="maxLength">50</aui:validator>
	</aui:input>
</div>

<div class="col-sm-6 ">
	<aui:input name="state" label="State" placeholder="state" style="font-size: 9px; color:#9B9A9B; ">
		     <aui:validator name="maxLength">50</aui:validator>
	</aui:input>
</div>

<div class="col-sm-6 margin-top-15">
	<aui:input name="pincode" label="PIN Code" placeholder="PIN Code" style="font-size: 9px; color:#9B9A9B; ">
	      <aui:validator name="required" />
	     <aui:validator name="maxLength">6</aui:validator>
	</aui:input>
</div>

<div class="col-sm-3 form-group margin-top-15">
	<aui:input name="mobileCountryCode" label="Country Code" placeholder="Country Code" style="font-size: 9px; color:#9B9A9B;">
   		<aui:validator name="required" />
    </aui:input>
</div>

<div class="col-sm-6 margin-top-15">
   	<aui:input name="phoneNumber" label="Phone Number" placeholder="Phone number" style="font-size: 9px; color:#9B9A9B; ">
	     <aui:validator name="maxLength">10</aui:validator>
	     <aui:validator name="required" />
	</aui:input>
</div>

</div>

<div class="col-sm-12 " style="color: #000000; font-weight: bold; margin-bottom: 7px; margin-top: 30px; font-size: 18px; margin-bottom: 10px;">
	Receiver Bank Account Information
</div>

<div class="col-sm-12 " style="font-size:10px; ">
	Money sent to your receiver&#39;s bank account must be in local currency. Also, you can&#39;t send money to a business or charity account.
</div>
<br/><br/>
<div class="" style="color:#9B9A9B; font-size: 10px;">
	<div class="col-sm-12">
			 <div class="col-sm-12">
                <div class="bank_detail">  
	                 <div class="col-sm-6">
	                 	<aui:input name="accountNumber" label="Account Number" placeholder="Account Number"  cssClass="form-control col-md-7 col-xs-6" style="font-size: 9px; color:#9B9A9B; ">
				     		<aui:validator name="required" />
				   		</aui:input>
	                 </div> 
	                 <div class="col-sm-6">
	                 	<aui:input name="accountNumber2" label="Re-enter Account Number" placeholder="Re-enter Account Number"  cssClass="form-control col-md-7 col-xs-6" style="font-size: 9px; color:#9B9A9B; ">
				     		<aui:validator name="equalTo">'#<portlet:namespace />accountNumber'</aui:validator>
				     	
				   		</aui:input>
	                 </div>
	                 
	                 <div class="col-sm-12">
					 	 <div class="col-sm-3">
						 	 <aui:input id="known_branch"  type="radio" name="searchBranchType" value="known_branch" label=""/>
			                    <label for="<portlet:namespace/>known_branch"><span id="unique_branch_code_label">Routing No.</span></label>
		                 </div>
		                 <div class="col-sm-9">
						 	 <aui:input id="search"  type="radio" name="searchBranchType" value="search" label=""/>
			                    <label for="<portlet:namespace/>search" >Search</label>
		                 </div>
                 	</div>
                 	<div class="col-sm-12">
                 		<div class="col-sm-3" id="known_branch_detail">
						 	 <aui:input type="text" name="unique_branch_code" label="Branch Code" ></aui:input>
		                </div>
		                <div class="col-sm-9" id="search_branch_detail">
		                	<div class="col-sm-4">
			                	<!-- <aui:select name="countryState" label="State" style="font-size: 9px; color:#9B9A9B; ">
			                	</aui:select> -->
		                	</div>
		                	<div class="col-sm-4">
		                		<aui:input name="countryCity" label="City" style="font-size: 9px; color:#9B9A9B; "/>
		                	</div>
		                	<div class="col-sm-4">
			                	<aui:select name="bankName" label="Bank Name" style="font-size: 9px; color:#9B9A9B; ">
			                	</aui:select>
			                </div>
	               			<div class="col-sm-4">
	                			<aui:select name="branchName" label="Branch" style="font-size: 9px; color:#9B9A9B; ">
			                	</aui:select>
	    		            </div>
		                </div>
	                </div> 
                 </div>
			 </div>
		</div>	
		
		<div class="col-sm-12 " style="color: #000000; font-weight: bold; margin-top:30px;  font-size: 18px; margin-bottom: 10px;">Purpose of Transfer</div>
		
		<div class="col-sm-12  padding-0" style="color:#9B9A9B; font-size: 10px;">
			<div class="col-sm-12">
				 <aui:input type="text" name="purTrans" label="" ></aui:input>
			</div>
		</div>
		
		<div class="col-sm-12" style="padding-top: 15px;">
			<div class="new-transfer createAccountBtn" style="max-width: 700px;">Complete Registration</div>
		</div>
		</aui:form>
		</div>


<aui:script>
var userModuleNameSpace =  '<portlet:namespace/>';
AUI().use('aui-base','aui-form-validator', 'aui-io-request' ,'node-event-simulate','autocomplete-list',
		'autocomplete-filters', 'autocomplete-highlighters', 'aui-autocomplete', function(A) {
	
	var createAccountBtn= A.one(".createAccountBtn");
	createAccountBtn.on('click', function(e) {
		var formValidator = Liferay.Form.get('<portlet:namespace />newAccountFm').formValidator;
		formValidator.validate();
		if(!formValidator.hasErrors()){
			if(A.one("#<portlet:namespace />search")._node.checked){
				searchBranchCodeValidator.validate();
				if(!searchBranchCodeValidator.hasErrors()){
					document.<portlet:namespace />newAccountFm.submit();		
				}
			}else{
				knownBranhcValidator.validate();
				if(!knownBranhcValidator.hasErrors()){
					document.<portlet:namespace />newAccountFm.submit();		
				}
			}
		}
	});
	
	var bankSelect = A.one("#<portlet:namespace />bankName");
	var countrySelect = A.one("#<portlet:namespace />country");
	var citySelect = A.one("#<portlet:namespace />countryCity");
	var searchBranchTypeBtns = A.all("input[name=<portlet:namespace />searchBranchType]");
	var knownBranchBtn = A.one("#<portlet:namespace />known_branch");
	var searchBtn = A.one("#<portlet:namespace />search");
	A.one("#<portlet:namespace />known_branch")._node.checked=true;
	countrySelect.simulate('change');
	
	A.one("#search_branch_detail").hide();
	
	countrySelect.on('change', function(e) {
		
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
		}
		$("#<portlet:namespace/>mobileCountryCode").val("+"+countryInitialPrefix);
	});
	
	
	var cityData;
	var cityAutoComplete = new A.AutoCompleteList({
		allowBrowserAutocomplete: 'true',
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
		
		var  getBankDetailURL = '${getBankDetailURL}';
		A.io.request(getBankDetailURL.toString(),{
			dataType: 'json',
			method: 'GET',
			data :{
				'<portlet:namespace/>cityCode' : cityId,
				'<portlet:namespace/>countryCode' : countryCode
			},
			on: {
			success: function() {
				A.one('#<portlet:namespace/>bankName').all('option').remove();
				A.one('#<portlet:namespace/>branchName').all('option').remove();

				var bankList=this.get('responseData');
				A.one('#<portlet:namespace/>bankName').append("<option  value='' >Select Bank</option>");
				for(var i in bankList){
				    A.one('#<portlet:namespace/>bankName').append('<option value="'+ bankList[i].bankId +','+ bankList[i].swiftBic +'">'+ bankList[i].bankNm +'</option>');
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
				for(var i in branchList){
				    A.one('#<portlet:namespace/>branchName').append('<option value="'+ branchList[i].branchId +','+ branchList[i].branchRtngnr +'">'+ branchList[i].branchDes +'</option>');
				}
			}
		  }
		
		});
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
	
	var searchBranchCodeValidator = new A.FormValidator({
		boundingBox: document.<portlet:namespace/>newAccountFm,
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
		boundingBox: document.<portlet:namespace/>newAccountFm,
		rules: {
			<portlet:namespace/>unique_branch_code: {
				required: true
			}
		}
	});
	
});
</aui:script>


<script>
jQuery.noConflict();
(function($) {
    $(function() {
    	
    	$('#<portlet:namespace />phoneNumber, #<portlet:namespace/>accountNumber,  #<portlet:namespace/>accountNumber2').bind('keyup paste', function(){
    		this.value = this.value.replace(/[^0-9]/g, '');
        });
    	
     });
})(jQuery);
</script> 