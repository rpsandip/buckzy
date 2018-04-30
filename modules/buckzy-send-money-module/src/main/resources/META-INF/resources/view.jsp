<%@page import="javax.portlet.RenderRequest"%>
<%@ include file="/init.jsp"%>

<portlet:renderURL var="createAccountURL">
	<portlet:param name="mvcRenderCommandName" value="/create_account" />
</portlet:renderURL>

<portlet:actionURL var="sendPaymentURL" name="/send_payment"/>

<portlet:resourceURL id="/getExchange_Rate" var="getExchangeRateURL"></portlet:resourceURL>
<portlet:resourceURL id="/getReceiver_Account_Detail" var="getReceiverAccountURL"></portlet:resourceURL>

<liferay-ui:error key="sender-account-not-exist" message="sender-account-not-exist"/>
<liferay-ui:error key="exchange-rate-not-available" message="exchange-rate-not-available"/>
<liferay-ui:error key="payment-error" message="payment-error"/>
<liferay-ui:error key="payment-custom-error" message="${customErr }"/>
<liferay-ui:success key="payment-succcess"message="payment-succcess" />
<liferay-ui:success key="account-created-success"message="account-created-success" />
<liferay-ui:error key="account-created-error" message="account-created-error"/>
<liferay-ui:error key="account-create-custom-err" message="${customErr }"/>
<% String loginUserCountryCode = (String)renderRequest.getAttribute("loginUserCountryCode"); %>

<aui:form name="paymentFm" action="${sendPaymentURL}" cssClass="form-horizontal form-label-left" enctype="multipart/form-data">
<div class="row padding-0 margin-0">
	<div class="col-sm-7 padding-0" style="padding: 10px;">
		<div class="col-sm-12 title padding-0 margin-0">Send Money</div>

		<div class="col-xs-12 border-1 padding-bottom-20">
			<div class="col-md-1" style="    padding: 10px 0 0 0;"><span class="num">1</span></div>
			<div class="col-md-11 pad-0" style="vertical-align: middle;">
				<div class="col-sm-12 margin-top-10" style="    padding-top: 10px; margin-bottom: 30px;"><b>How would
					you like to pay for your transfer?</b></div>
				
				<div class="col-sm-12 pad-0" style="padding-left: 15px;">
					<div class="col-sm-7 pad-0">
						<c:choose>
							<c:when test="${userBean.partyBean.accountBean.accountCategory==1 }">
								Bank Account : Last 4 - ${userBean.partyBean.accountBean.acctnr4dgt }
							</c:when>
							<c:otherwise>
								 Debit Card : Last 4 - ${userBean.partyBean.accountBean.acctnr4dgt }
							</c:otherwise>
						</c:choose>
					</div>
					<div class="col-sm-5 pad-0">
						<a href="" style="font-size: 12px;">Add / Modify Payment Type</a>
					</div>
				</div>
			</div>
			<div class="col-sm-12 margin-top-20 margin-bottom-10">
				<hr class="hr-grey " />
			</div>

			<div class="col-md-1" style="    padding: 18px 0 0 0;"><span class="num">2</span></div>
			<div class="col-md-11 pad-0" style="vertical-align: middle;">
				<div class="col-sm-12 margin-top-20" style="margin-top: 0;"><b>Send money to</b></div>
				<div class="col-sm-8">
					<aui:select name="receiver" label=""
						style="height: 37px; width: 100%;">
						<c:forEach items="${receiverList }" var="receiver">
							<aui:option value="${receiver.get('prtyid')}, ${receiver.get('basecurrcd')}, ${receiver.get('countryCode')}" 
							selected="${receiver.get('prtyid') eq paymentBean.rcvrid ? true : false }"
							>${receiver.get('frstnm') } ${receiver.get('lastnm') } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ${receiver.get('recAccNr4Digit') }</aui:option>
						</c:forEach>
					</aui:select>
				</div>
	
				<div class="col-sm-4 create-new-account" style="padding: 0;line-height: 35px;">
					<a href="${createAccountURL }">Create a new account</a>
				</div>
				
				<div class="col-xs-12 margin-top-30" style="padding-right: 0px;">
					<div id="recBankAddress1" style="    font-size: 12px; margin-bottom: 25px;"></div>
					<div id="recBankAddress2"></div>
				</div>
	
				<div class="col-xs-8">
					<%-- <aui:input name="purTrans" label="Purpose Of Transfer" value="${paymentBean.purpofpymt }">
						<aui:validator name="required" />
						 <aui:validator name="maxLength">45</aui:validator>
					</aui:input> --%>
					<aui:select name="purTrans" label="Purpose Of Transfer">
						<aui:option value="Family Support">Family Support</aui:option>
						<aui:option value="Bill pay">Bill pay</aui:option>
						<aui:option value="Vendor payment">Vendor payment</aui:option>
					</aui:select>
				</div>
			
			</div>
			
			<div class="col-sm-12 margin-top-20 margin-bottom-10">
				<hr class="hr-grey " />
			</div>
			
			<div class="col-md-1" style="    padding: 0px 0 0 0;"><span class="num">3</span></div>
			<div class="col-md-11 pad-0" style="vertical-align: middle;">
				<div class="col-sm-12 margin-bottom-10 margin-top-10"><b>Enter the amount to send or receive.</b></div>
	
				<div class="col-md-8 pad-0">
					<div class="col-xs-12 pad-0">
						<span style="
							    position: absolute;
							    top: 34px;
							    left: 5px;
							"><img class="img-responsive" src='<%= themeDisplay.getPathThemeImages() + "/flag/" + loginUserCountryCode + ".png" %>' height="30" width="30" style="
							    border-radius: 50%;
							    float: left;
							    margin-right: 5px;
							">${loginUserCurrencyCd}</span>
						<aui:input name="amount" label="Sending Amount" value="${paymentBean.sndrinstramt }" style="text-align: right;">
							<aui:validator name="required" />
							<aui:validator name="number" />
						</aui:input>
					</div>
				
					<div class="col-xs-12 pad-0" style="    padding: 20px 0 0 0;">
						<span style="
							    position: absolute;
							    top: 55px;
							    left: 5px;
							"><img class="img-responsive rec-img" src="" height="30" width="30" style="
							    border-radius: 50%;
							    float: left;
							    margin-right: 5px;
							"><spanv id="recCurCode"></span></span>
						<aui:input name="receivingAmount" label="Receiving Amount" value="${paymentBean.sndrinstramt }" style="text-align: right;">
							<aui:validator name="required" />
							<aui:validator name="number" />
						</aui:input>
					</div>
				
				</div>
				<div class="col-md-1" style="    padding: 22px 0 0 0;">
					<img src="<%=themeDisplay.getPathThemeImages() + "/exchange-cur.jpg"%>" style="height: 140px;"/>
				</div>
				<div class="col-md-3" style="    padding: 16% 0 0 0;">
						<label>Exchange Rate</label>
						<div id="exchangeDetail" style="font-size: 12px;"></div>
				</div>
			</div>
		</div>

		<div class="col-xs-12">
				<c:choose>
					<c:when test="${isAccountVerfied }">
						<div class="col-sm-12 submit-payment" style="margin-top: 10px;">
							<div class="new-transfer">Submit</div>
						</div>	
					</c:when>
					<c:otherwise>
						<div class="col-sm-12" style="color: #961622; font-size: 14px; font-weight: bold;">
							You have not added your bank account. Please update your profile <a href="/group/guest/profile">here</a>
						</div>
					</c:otherwise>
				</c:choose>
		</div>
	</div>

	<div class="col-sm-5 padding-0" style="padding: 10px;">
		<div class="col-xs-12 title padding-0 margin-0">Summary</div>

		<div class="col-xs-12 border-1 padding-10">
			<table style="width: 100%">
				<tbody>
					<tr>
						<td>Transfer amount :</td>
						<td id="transferFromAmt"></td>
					</tr>
					<tr>
						<td>Transfer fee :</td>
						<td id="transferFromFee"></td>
					</tr>
					<tr>
						<td>Transfer total :</td>
						<td id="transferFromTotal"></td>
					</tr>
				</tbody>
			</table>

			<div
				class="col-sm-12 border-bottom-1 margin-top-20 padding-0 margin-0 padding-bottom-5">Total
				to receiver</div>

			<div class="col-sm-6 padding-0 margin-0 margin-top-5" id="transferToTotal"></div>

			<div class="col-sm-6 padding-0 margin-0 margin-top-5" id="exchange_total_detail"></div>

			<div class="col-sm-12 delivery-time padding-0 margin-0">Delivery
				time: Immediate (3 to 5 minutes)</div>
		</div>

		<div class="col-sm-12 border-1 customer-service margin-top-20">
			<div class="col-sm-12 text-center margin-bottom-10">
				<img src="/o/buckzy-theme/images//chat-icon.png" />
			</div>

			<div class="col-sm-12 text-center padding-0">Click to chat with
				a customer support representative</div>

			<div class="col-sm-12 text-center padding-0">or call us at +1
				855 382 4738 or +1 201 483 2883</div>
		</div>
	</div>
</div>
<aui:input type="hidden" name="exchangeRate"/>
<aui:input type="hidden" name="fromCur" value="${loginUserCurrencyCd }"/>
<aui:input type="hidden" name="toCur"/>
</aui:form>

<aui:script>

AUI().use('aui-base','aui-form-validator', 'aui-io-request','node-event-simulate', function(A) {
	
	var submitPaymentBtn = A.one('.submit-payment');
	if(submitPaymentBtn){
		submitPaymentBtn.on('click', function(e) {
			var formValidator = Liferay.Form.get('<portlet:namespace />paymentFm').formValidator;
			formValidator.validate();
			receiverValidator.validate();
			if(!formValidator.hasErrors() && !receiverValidator.hasErrors()){
				document.<portlet:namespace />paymentFm.submit();
			}
		});
	}
	
	var amountSelect = A.one("#<portlet:namespace />amount");
	var receivingAmountSelect = A.one("#<portlet:namespace />receivingAmount");
	var receiverSelect = A.one("#<portlet:namespace />receiver");
	var senderCurCode = '${loginUserCurrencyCd}';
	var receiverCurCode;
	
	receiverSelect.on('change', function(e) {

		amountSelect.val('');
		receivingAmountSelect.val('');
		var receiverDetail = this.get('value');
		var receiverCur='';
		if(receiverDetail.indexOf(",")>0){
			receiverCurCode = receiverDetail.split(",")[1];
		}
		var cntryCd = receiverDetail.split(",")[2];
		A.one(".rec-img").setAttribute("src","/o/buckzy-theme/images/flag/"+cntryCd.trim().toLowerCase()+".png");
		A.one("#recCurCode").text(receiverCurCode);
		A.one("#<portlet:namespace/>toCur").val(receiverCurCode);
		A.one("#recBankAddress1").text('');
		A.one("#recBankAddress2").text('');

		getExchangeRate(senderCurCode,receiverCurCode);
		
		// Get Bank address Detail
		var getReceiverAccountURL = '${getReceiverAccountURL}';
		A.io.request(getReceiverAccountURL.toString(),{
			dataType: 'json',
			method: 'GET',
			data :{
				'<portlet:namespace/>receiverPartyId' : receiverDetail.split(",")[0]
			},
			on: {
			success: function() {
				var receiverBankDetail=this.get('responseData');
				A.one("#recBankAddress1").text(receiverBankDetail.accountBranchDetail.branchAddrline);
			}
		  }
		});
		
	});
	
	/*
	fromCurSelect.on('change', function(e) {
		A.one("#exchangeDetail").text('');
		A.one("#exchange_total_detail").text('');
		getExchangeRate(fromCurSelect.val(),toCurSelect.val());
	});*/
	
	/*
	toCurSelect.on('change', function(e) {
		A.one("#exchangeDetail").text('');
		A.one("#exchange_total_detail").text('');
		getExchangeRate(fromCurSelect.val(),toCurSelect.val());
	});*/
	
	amountSelect.on('change', function(e) {
		getTranferedAmount(true);
	});
	
	receivingAmountSelect.on('change', function(e) {
		getTranferedAmount(false);
	});
	
	function getTranferedAmount(fromSender){

		if(fromSender){
			var amount = amountSelect.val();
			amount = parseFloat(amount);
			var exchangeRate = A.one("#<portlet:namespace/>exchangeRate").val();
			console.log("exchangeRate->"  + exchangeRate);
			if(amount!="" && typeof amount != 'undefined' && !isNaN(amount) && exchangeRate != 0 && typeof exchangeRate != 'undefined' && !isNaN(exchangeRate)){
				var transferedAmount = amount/exchangeRate;
				console.log("transferedAmount->" + transferedAmount);
				A.one("#transferFromAmt").text(amount  + " " + senderCurCode);
				A.one("#transferFromFee").text( "0.00"+ " " + senderCurCode);
				A.one("#transferFromTotal").text(amount + " " + senderCurCode);
				A.one("#transferToTotal").text(transferedAmount + " " + receiverCurCode);
				receivingAmountSelect.val(transferedAmount);
			}else{
				A.one("#transferFromAmt").text("");
				A.one("#transferFromFee").text("");
				A.one("#transferFromTotal").text("");
				A.one("#transferToTotal").text("");
				receivingAmountSelect.val("");
			}
		}else{
			var amount = receivingAmountSelect.val();
			amount = parseFloat(amount);
			var exchangeRate = A.one("#<portlet:namespace/>exchangeRate").val();
			console.log("exchangeRate->"  + exchangeRate);
			if(amount!="" && typeof amount != 'undefined' && !isNaN(amount) && exchangeRate != 0 && typeof exchangeRate != 'undefined' && !isNaN(exchangeRate)){
				var transferedAmount = amount * exchangeRate;
				console.log("transferedAmount->" + transferedAmount);
				A.one("#transferFromAmt").text(amount  + " " + senderCurCode);
				A.one("#transferFromFee").text( "0.00"+ " " + senderCurCode);
				A.one("#transferFromTotal").text(amount + " " + senderCurCode);
				A.one("#transferToTotal").text(transferedAmount + " " + receiverCurCode);
				amountSelect.val(transferedAmount);
			}else{
				A.one("#transferFromAmt").text("");
				A.one("#transferFromFee").text("");
				A.one("#transferFromTotal").text("");
				A.one("#transferToTotal").text("");
				amountSelect.val("");
			}
		}
		
	}
	
	function getExchangeRate(fromCurCode, toCurCode){
		
		var  getExchangeRateURL = '${getExchangeRateURL}';
		A.io.request(getExchangeRateURL.toString(),{
			dataType: 'json',
			method: 'GET',
			data :{
				'<portlet:namespace/>fromCurCode' : fromCurCode,
				'<portlet:namespace/>toCurCode' : toCurCode
			},
			on: {
			success: function() {
				var exchangeDetail=this.get('responseData');
			    var exchangeRate = exchangeDetail.convertionRate;
			    if(exchangeRate){
			    	A.one("#exchangeDetail").text("1 " + fromCurCode + " = " + exchangeRate + " " + toCurCode);
			    	A.one("#exchange_total_detail").text("1 " + fromCurCode + " = " + exchangeRate + " " + toCurCode);
			    	A.one("#<portlet:namespace/>exchangeRate").val(exchangeRate);
			    }else{
			    	A.one("#exchangeDetail").text("No Exchange Rate available");
			    	A.one("#exchange_total_detail").text("No Exchange Rate available");
			    	A.one("#transferFromAmt").text("");
					A.one("#transferFromFee").text("");
					A.one("#transferFromTotal").text("");
					A.one("#transferToTotal").text("");
					A.one("#<portlet:namespace/>exchangeRate").val(0);
			    }
			    getTranferedAmount();
			}
		  }
		});
		
	}
	
	var receiverValidator = new A.FormValidator({
		boundingBox: document.<portlet:namespace/>paymentFm,
		rules: {
			<portlet:namespace/>receiver: {
				required: true
			}
		}
	});
	
	var paymentId = '${paymentBean.lineitemid}';
	var purposeOfTras = '${paymentBean.purpofpymt}';
	if(parseInt(paymentId)>0){
		getExchangeRate(senderCurCode,receiverCurCode);
		 A.one("#<portlet:namespace />amount").setAttribute("readonly", true);
		 A.one("#<portlet:namespace />receivingAmount").setAttribute("readonly", true);
		 A.one("#<portlet:namespace />purTrans").val(purposeOfTras);
	}
	
	//fromCurSelect.simulate('change');
	receiverSelect.simulate('change');
			
});
</aui:script>