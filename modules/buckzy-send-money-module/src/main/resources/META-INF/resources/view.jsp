<%@ include file="/init.jsp"%>

<portlet:renderURL var="createAccountURL">
	<portlet:param name="mvcRenderCommandName" value="/create_account" />
</portlet:renderURL>

<portlet:actionURL var="sendPaymentURL" name="/send_payment"/>

<portlet:resourceURL id="/getExchange_Rate" var="getExchangeRateURL"></portlet:resourceURL>

<liferay-ui:error key="sender-account-not-exist" message="sender-account-not-exist"/>
<liferay-ui:error key="exchange-rate-not-available" message="exchange-rate-not-available"/>
<liferay-ui:error key="payment-error" message="payment-error"/>
<liferay-ui:success key="payment-succcess"message="payment-succcess" />
<liferay-ui:success key="account-created-success"message="account-created-success" />

<aui:form name="paymentFm" action="${sendPaymentURL}" cssClass="form-horizontal form-label-left" enctype="multipart/form-data">
<div class="row padding-0 margin-0">
	<div class="col-sm-7 padding-0" style="padding: 10px;">
		<div class="col-sm-12 title padding-0 margin-0">Send Money</div>

		<div class="col-xs-12 border-1 padding-bottom-20">
			<div class="col-sm-12 margin-bottom-10 margin-top-10">How would
				you like to pay for your transfer?</div>

			<div class="col-sm-4">
				<div class="outer">
					<div class="inner text-center"
						style="background-image: url('/o/buckzy-theme/images/test-icon1.png'); background-position: center;">&nbsp;</div>
				</div>
			</div>

			<div class="col-sm-4">
				<div class="outer">
					<div class="inner text-center">
						<img src="/o/buckzy-theme/images/test-icon2.png" />
					</div>
				</div>
			</div>

			<div class="col-sm-4">
				<div class="outer">
					<div class="inner text-center">
						<img src="/o/buckzy-theme/images/test-icon3.png"
							style="margin-top: 3px;" />
					</div>
				</div>
			</div>

			<div class="col-sm-12 margin-top-20 margin-bottom-10">
				<hr class="hr-grey " />
			</div>

			<div class="col-sm-12 margin-top-20">Send money to</div>

			<div class="col-sm-8">
				<aui:select name="receiver" label=""
					style="height: 37px; width: 100%;">
					<c:forEach items="${receiverList }" var="receiver">
						<aui:option value="${receiver.get('prtyid')}, ${receiver.get('basecurrcd')}" 
						selected="${receiver.get('prtyid') eq paymentBean.rcvrid ? true : false }"
						>${receiver.get('frstnm') } ${receiver.get('lastnm') }</aui:option>
					</c:forEach>
				</aui:select>
			</div>

			<div class="col-sm-4 create-new-account">
				<a href="${createAccountURL }">Create a new account</a>
			</div>

			<div class="col-xs-5 margin-top-30" style="padding-right: 0px;">
				<span>Sending Currency</span>
				<div class="col-sm-12  padding-0 margin-0 padding-5">
					<aui:select name="fromCur" label="">
						<c:forEach items="${currenyJSONList }" var="currency">
							<c:if test="${currency.get('currcd') eq loginUserCurrencyCd}">
								<aui:option value="${currency.get('currcd') }">${currency.get('currnm') }</aui:option>
							</c:if>
						</c:forEach>
					</aui:select>
				</div>
			</div>

			<div class="col-xs-2 margin-top-50">&nbsp;</div>

			<div class="col-xs-5 margin-top-30" style="padding-left: 0px;">
				<span>Receiving Currency</span>
				<div class="col-sm-12  padding-0 margin-0 padding-5">
					<aui:select name="toCur" label="">
						<aui:option value="">Select Receiver Currency</aui:option>
						<c:forEach items="${currenyJSONList }" var="currency">
							<aui:option value="${currency.get('currcd') }"
							selected="${currency.get('currcd') eq paymentBean.rcvrcurrcd ? true : false }"
							>${currency.get('currnm') }</aui:option>
						</c:forEach>
					</aui:select>
				</div>
			</div>

			<div class="col-xs-12">
				<div id="exchangeDetail"></div>
			</div>

			<div class="col-xs-12">
				<aui:input name="amount" label="amount" value="${paymentBean.sndrinstramt }">
					<aui:validator name="required" />
					<aui:validator name="number" />
				</aui:input>
			</div>
			
			<div class="col-xs-12">
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

			<div class="col-sm-12 margin-top-40 margin-bottom-10">
				<hr class="hr-grey " />
			</div>

			<div class="col-sm-12 margin-bottom-10 margin-top-10">How would
				your receiver want the money?</div>

			<div class="col-sm-4">
				<div class="outer">
					<div class="inner text-center">
						<img src="/o/buckzy-theme/images/test-icon2.png" />
					</div>
				</div>
			</div>

			<!-- <div class="col-sm-4">
				<div class="outer">
					<div class="inner text-center">
						<img src="/o/buckzy-theme/images/test-icon4.png"
							style="margin-top: 3px;" />
					</div>
				</div>
			</div> -->

			<div class="col-sm-4">
				<div class="outer">
					<div class="inner text-center">
						<img src="/o/buckzy-theme/images/test-icon3.png"
							style="margin-top: 3px;" />
					</div>
				</div>
			</div>
		</div>

		<div class="col-xs-12">
			<div class="col-sm-8 submit-payment" style="margin-top: 10px;">
				<div class="new-transfer">Submit</div>
			</div>

			<div class="col-sm-4" style="margin-top: 10px;">
				<div class="new-transfer" style="display: block;">Cancel</div>
			</div>
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
</aui:form>

<aui:script>

AUI().use('aui-base','aui-form-validator', 'aui-io-request','node-event-simulate', function(A) {
	
	var submitPaymentBtn = A.one('.submit-payment');
	submitPaymentBtn.on('click', function(e) {
		var formValidator = Liferay.Form.get('<portlet:namespace />paymentFm').formValidator;
		formValidator.validate();
		receiverValidator.validate();
		if(!formValidator.hasErrors() && !receiverValidator.hasErrors()){
			document.<portlet:namespace />paymentFm.submit();
		}
	});
	
	var fromCurSelect = A.one("#<portlet:namespace />fromCur");
	var toCurSelect = A.one("#<portlet:namespace />toCur");
	var amountSelect = A.one("#<portlet:namespace />amount");
	var receiverSelect = A.one("#<portlet:namespace />receiver");
	var currenctOptions = A.one("#<portlet:namespace />toCur").all('option')._nodes;
	
	receiverSelect.on('change', function(e) {
		var receiverDetail = this.get('value');
		var receiverCur='';
		if(receiverDetail.indexOf(",")>0){
			receiverCur = receiverDetail.split(",")[1];
		}
		A.one('#<portlet:namespace/>toCur').all('option').remove();
		for(var i=0;i<currenctOptions.length;i++){
			if(currenctOptions[i].value.trim()==receiverCur.trim()){
				console.log("got receiverCur ->" + receiverCur);
				A.one('#<portlet:namespace/>toCur').append(currenctOptions[i]);
				//A.one('#<portlet:namespace/>toCur').append('<option selected value=""> Select Receiver Currency</option>');
				getExchangeRate(fromCurSelect.val(),toCurSelect.val());
			}
		}
		fromCurSelect.simulate('change');
	});
	
	fromCurSelect.on('change', function(e) {
		A.one("#exchangeDetail").text('');
		A.one("#exchange_total_detail").text('');
		getExchangeRate(fromCurSelect.val(),toCurSelect.val());
	});
	
	toCurSelect.on('change', function(e) {
		A.one("#exchangeDetail").text('');
		A.one("#exchange_total_detail").text('');
		getExchangeRate(fromCurSelect.val(),toCurSelect.val());
	});
	
	amountSelect.on('change', function(e) {
		
		getTranferedAmount();
		
	});
	
	function getTranferedAmount(amount,exchangeRate, fromCurCode, toCurCode){
		
		var amount = amountSelect.val();
		amount = parseFloat(amount);
		
		var exchangeRate = A.one("#<portlet:namespace/>exchangeRate").val();
		
		console.log("exchangeRate->"  + exchangeRate);
		
		if(amount!="" && typeof amount != 'undefined' && !isNaN(amount) && exchangeRate != 0 && typeof exchangeRate != 'undefined' && !isNaN(exchangeRate)){
			
			var transferedAmount = amount * exchangeRate;
			
			console.log("transferedAmount->" + transferedAmount);
	
			A.one("#transferFromAmt").text(amount  + " " + fromCurSelect.val());
			A.one("#transferFromFee").text( "0.00"+ " " + fromCurSelect.val());
			A.one("#transferFromTotal").text(amount + " " + fromCurSelect.val());
			A.one("#transferToTotal").text(transferedAmount + " " + toCurSelect.val());
		}else{
			A.one("#transferFromAmt").text(" ");
			A.one("#transferFromFee").text(" ");
			A.one("#transferFromTotal").text(" ");
			A.one("#transferToTotal").text(" ");
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
		getExchangeRate(fromCurSelect.val(),toCurSelect.val());
		 A.one("#<portlet:namespace />amount").setAttribute("readonly", true);
		 A.one("#<portlet:namespace />purTrans").val(purposeOfTras);
	}
	
	fromCurSelect.simulate('change');
	receiverSelect.simulate('change');
			
});
</aui:script>