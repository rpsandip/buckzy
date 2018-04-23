<%@ include file="/init.jsp" %>
<liferay-ui:success key="payment-succcess"message="payment-succcess" />

<div style="margin: 0 auto;">
	<div class="pin-detail" style="border: 1px solid #DB2222; outline: #E8E8E8 solid 3px; padding: 75px 35px 55px 35px;">
			
			<div class="col-sm-12 title padding-0 margin-0 text-center payment-transaction-details">Payment Transaction Details</div>
        <div class="col-xs-12 border-1 padding-10">
            <div class="col-sm-9 padding-10 padding-left-15 title-1">${txnDate }</div>
            <div class="col-sm-3 text-right padding-top-10" style="color: #579ED7; font-size: 11px;">
                <img src="<%= themeDisplay.getPathThemeImages() + "/print.png"%>" />PRINT
            </div>
            <div class="col-sm-6">
                <div class="title">Sending Party Details</div>
                <hr class="hr-black" />
                <table class="table-info">
                    <tr>
                        <td>
                        	<%
                        		String ucntry = (String)renderRequest.getAttribute("senderCountry");
                        		if(ucntry!=null){
                        			ucntry = ucntry.toLowerCase();
                        		}
                        	%>
                            <img src="<%= themeDisplay.getPathThemeImages() + "/flag/"+ ucntry + ".png"%>"  height="25" width="25"/>
                        </td>
                        <td>${paymentBean.sndrdbtrnm }</td>
                    </tr>
                    <tr>
                        <td>
                            <!-- <img src="images/visa-icon.png" /> -->
                        </td>
                        <td>Account # ending ${ paymentBean.sndracctnr}</td>
                    </tr>
                </table>
            </div>
            <div class="col-sm-6">
                <div class="title">Receiving Party Details</div>
                <hr class="hr-black" />
                <table class="table-info">
                    <tr>
                        <td>
                        	<%
                        		String reccntry = (String)renderRequest.getAttribute("receiverCountry");
                        		if(reccntry!=null){
                        			reccntry = ucntry.toLowerCase();
                        		}
                        	%>
                            <img src="<%= themeDisplay.getPathThemeImages() + "/flag/"+ reccntry + ".png"%>"  height="25" width="25"/>
                        </td>
                        <td>${paymentBean.rcvrnm } <span style="color: #3F6ADD; font-size: 12px; display: block;">verified member</span></td>
                    </tr>
                    <tr>
                        <td>
                            <img src="<%= themeDisplay.getPathThemeImages() + "/bank-icon.png"%>" />
                        </td>
                        <td>Account # ending ${paymentBean.rcvracctnr}</td>
                    </tr>
                    <!-- <tr>
                        <td>
                            <img src="images/bank-icon.png" />
                        </td>
                        <td>
                            <span style="display: block;">ICICI Bank</span>
                            <span style="display: block;">Kaloor Branch</span>
                            <span style="display: block;">Kochi, Kerala</span>
                            <span style="display: block;">India</span>
                        </td>
                    </tr> -->
                    <tr>
                        <td>
                            <img src="<%= themeDisplay.getPathThemeImages() + "/phone-icon.png"%>" />
                        </td>
                        <td>${paymentBean.rcvrmobilenr }</td>
                    </tr>
                    <tr>
                        <td>
                            <img src="<%= themeDisplay.getPathThemeImages() + "/email-icon.png"%>" />
                        </td>
                        <td>${paymentBean.rcvremail }</td>
                    </tr>
                </table>
            </div>
            <div class="col-sm-12">
                <hr class="hr-black" />
            </div>
            <div class="col-sm-12 margin-top-10 ">Purpose of transaction: ${ paymentBean.purpofpymt}</div>
            <div class="col-sm-12 margin-top-10">
                <hr class="hr-black" />
            </div>
            <div class="col-xs-12 margin-top-10">
                <table style="width: 100%">
                    <tr>
                        <td>Exchange Rate</td>
                        <td> Transfer amount</td>
                        <td>${paymentBean.sndrinstramt } ${paymentBean.sndrcurrcd }</td>
                    </tr>
                    <tr>
                        <td>1 ${paymentBean.sndrcurrcd } = ${paymentBean.amtfxrate} ${paymentBean.rcvrcurrcd }</td>
                        <td>Transfer fee</td>
                        <td>0.00 USD</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>Transfer total</td>
                        <td>${paymentBean.sndrinstramt } ${paymentBean.sndrcurrcd }</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>Total to receiver</td>
                        <td>${paymentBean.rcvramt } ${paymentBean.rcvrcurrcd }</td>
                    </tr>
                </table>
            </div>
                        
            <div class="col-sm-12 margin-top-10">
                <hr class="hr-black" />
            </div>
                        
            <div class="col-sm-12 margin-top-10">Status: Delivered to account on ${txnRecvDate} </div>
            <div class="col-sm-12">Time taken: 5 minutes</div>
        </div>`
			
		</div>
</div>
