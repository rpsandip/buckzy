<%@ page import="com.buckzy.common.beans.PaymentBean" %>
<div class="row padding-0 margin-0">
	<div class="col-xs-12 padding-0">
		<table class="table table-top margin-0">
			<thead>
				<tr>
					<th>Total Transactions</th>
					<th>Total Amount</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${totalTransactions }</td>
					<td>
					 <fmt:formatNumber type = "number"  maxFractionDigits = "3" value = "${totalAmountTransferred}" /> ${sndrcurrcd }</td>
				</tr>
			</tbody>
		</table>
	</div>

<!--
	<div class="col-xs-6 padding-0">
		  
		<table class="table table-top margin-0">
			<thead>
				<tr>
					<th>Total Transactions</th>
					<th>Total Amount</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>$250.00</td>
				</tr>
			</tbody>
		</table>
	</div>  -->
</div>

<hr style="background-color: #F2F2F2; margin-bottom: 40px;" />
<div>
	<div class="new-transfer" ><a href="/group/guest/send-money" style="    color: white;">Click here for new transfer</a></div>
</div>

<hr style="background-color: #F2F2F2; margin-top: 40px; margin-bottom: 7px;" />

<c:choose>
	<c:when test="${ totalTransactions gt 0 }">
		<c:forEach items="${paymentBeanList }" var="paymentBean">
				<div class="row padding-0 margin-0">
					<div class="col-xs-8 border-right-2">
						<table class="table table-main">
							<tbody>
								<tr>
									<%
									PaymentBean paymentBean = (PaymentBean)pageContext.getAttribute("paymentBean");
									%>
									<td rowspan="2"><img class="img-responsive"
										src='<%= themeDisplay.getPathThemeImages() + "/flag/" + paymentBean.getRcvrcntrycd() + ".png" %>'  height="50" width="50"/></td>
										
									<th><fmt:formatDate pattern = "MMM-dd-YYYY" value = "${paymentBean.createdDate}" /></th>
									<th>Tracking #</th>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>${paymentBean.rcvrnm }</td>
									<td>${paymentBean.lineitemid }</td>
									<td><fmt:formatNumber type = "number"  maxFractionDigits = "3" value = "${paymentBean.sndrinstramt }" /> <span class="currency">${sndrcurrcd }</span></td>
								</tr>
							</tbody>
						</table>
					</div>
				
					<div class="col-xs-4">
						<table class="table-icons">
							<tbody>
								<tr>
									<td><img src="/o/buckzy-theme/images/resend_icon.png" /></td>
									<td><img src="/o/buckzy-theme/images/details-icons.png" /></td>
									<td><img src="/o/buckzy-theme/images/complete-icon.png" /></td>
								</tr>
								<tr>
									<td
										style="color: #7092BE; font-size: 12px; font-weight: bold; font-family: Verdana;"><a href='<%="/group/guest/send-money?paymentId=" + paymentBean.getLineitemid() %>'>Resend</a></td>
									<td
										style="color: #B60E16; font-size: 12px; font-weight: bold; font-family: Verdana;"><a href='<%="/group/guest/send-money?paymentId=" + paymentBean.getLineitemid()+"&viewdetail=true" %>'>Details</a></td>
									<td
										style="color: #0E471F; font-size: 12px; font-weight: bold; font-family: Verdana;">Complete</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				
				<hr style="background-color: #F2F2F2; margin-top: 7px; margin-bottom: 7px;" />
		</c:forEach>
	</c:when>
	<c:otherwise>
	    <div style="color: #7092BE; font-size: 14px; font-weight: bold; text-align: center;">
	    	No Transaction Records found
	    </div>
	</c:otherwise>
</c:choose>