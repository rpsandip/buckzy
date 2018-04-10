<%@ include file="/init.jsp" %>
<liferay-ui:success key="payment-succcess"message="payment-succcess" />

<div style="max-width: 540px; margin: 0 auto; margin-top: 45px;">
	<div class="pin-detail" style="border: 1px solid #DB2222; outline: #E8E8E8 solid 3px; padding: 75px 35px 55px 35px;">
			<div style="color:red;" class="err-msg1"></div>
			<div class="row padding-0 margin-0 padding-10">
                 <div class="col-sm-12 padding-0 margin-0" style="margin-bottom: 7px; font-size: 18px;">Payment Detail :</div>
                 
                 <div class="col-sm-12 " style="background-color: #FFFFFF; padding:10px 20px 20px 20px; ">
                     <b>Payment Id : ${paymentId }</b>
                 </div>
                 <div class="col-sm-12 " style="background-color: #FFFFFF; padding:10px 20px 20px 20px; ">
                     <b>Sender Name : ${senderName }</b>
                 </div>
                 <div class="col-sm-12 " style="background-color: #FFFFFF; padding:10px 20px 20px 20px; ">
                     <b>Sender Account# : ${senderAcnt }</b>
                 </div>
                 <div class="col-sm-12 " style="background-color: #FFFFFF; padding:10px 20px 20px 20px; ">
                     <b>Amount Sent : ${senderAmount }</b>
                 </div>
                 <div class="col-sm-12 " style="background-color: #FFFFFF; padding:10px 20px 20px 20px; ">
                     <b>Receiver Name : ${recevierName }</b>
                 </div>
                 <div class="col-sm-12 " style="background-color: #FFFFFF; padding:10px 20px 20px 20px; ">
                     <b>Receiver Account# : ${recevierAcnt }</b>
                 </div>
                 <div class="col-sm-12 " style="background-color: #FFFFFF; padding:10px 20px 20px 20px; ">
                     <b>Amount To be receive  : ${ receiverAmount }</b>
                 </div>
		</div>
</div>
