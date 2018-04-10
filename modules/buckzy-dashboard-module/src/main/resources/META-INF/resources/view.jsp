<%@ include file="/init.jsp"%>

<c:choose>
	<c:when test="${!isProfileCompleted}">
		<div class="row padding-0 margin-0">
			<div class="col-xs-12 border-right padding-0">
				<h3>Your profile is incomplete, Please update <a href="/group/guest/profile">here</a></h3>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="row padding-0 margin-0">
	<div class="col-xs-6 border-right padding-0">
		<table class="table table-top margin-0">
			<thead>
				<tr>
					<th>Total Transactions</th>
					<th>Total Amount</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>17</td>
					<td>$13,455.00</td>
				</tr>
			</tbody>
		</table>
	</div>

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
	</div>
</div>

<hr style="background-color: #F2F2F2; margin-bottom: 40px;" />
<div>
	<div class="new-transfer">Click here for new transfer</div>
</div>

<hr
	style="background-color: #F2F2F2; margin-top: 40px; margin-bottom: 7px;" />
<div class="row padding-0 margin-0">
	<div class="col-xs-7 border-right-2">
		<table class="table table-main">
			<tbody>
				<tr>
					<td rowspan="2"><img class="img-responsive"
						src="/o/buckzy-theme/images/test.png" /></td>
					<th>Nov-17-2017</th>
					<th>Tracking #</th>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>John Smith</td>
					<td>17-1121-28192</td>
					<td>1,350.00 <span class="currency">USD</span></td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="col-xs-5">
		<table class="table-icons">
			<tbody>
				<tr>
					<td><img src="/o/buckzy-theme/images/resend_icon.png" /></td>
					<td><img src="/o/buckzy-theme/images/details-icons.png" /></td>
					<td><img src="/o/buckzy-theme/images/complete-icon.png" /></td>
				</tr>
				<tr>
					<td
						style="color: #7092BE; font-size: 12px; font-weight: bold; font-family: Verdana;">Resend</td>
					<td
						style="color: #B60E16; font-size: 12px; font-weight: bold; font-family: Verdana;">Details</td>
					<td
						style="color: #0E471F; font-size: 12px; font-weight: bold; font-family: Verdana;">Complete</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<hr
	style="background-color: #F2F2F2; padding: 0px; margin-top: 7px; margin-bottom: 7px;" />
<div class="row padding-0 margin-0">
	<div class="col-xs-7 border-right-2">
		<table class="table table-main">
			<tbody>
				<tr>
					<td rowspan="2"><img class="img-responsive"
						src="/o/buckzy-theme/images/test.png" /></td>
					<th>Nov-17-2017</th>
					<th>Tracking #</th>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>John Smith</td>
					<td>17-1121-28192</td>
					<td>1,350.00 <span class="currency">USD</span></td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="col-xs-5">
		<table class="table-icons">
			<tbody>
				<tr>
					<td><img src="/o/buckzy-theme/images/resend_icon.png" /></td>
					<td><img src="/o/buckzy-theme/images/details-icons.png" /></td>
					<td><img src="/o/buckzy-theme/images/complete-icon.png" /></td>
				</tr>
				<tr>
					<td
						style="color: #7092BE; font-size: 12px; font-weight: bold; font-family: Verdana;">Resend</td>
					<td
						style="color: #B60E16; font-size: 12px; font-weight: bold; font-family: Verdana;">Details</td>
					<td
						style="color: #0E471F; font-size: 12px; font-weight: bold; font-family: Verdana;">Complete</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<hr
	style="background-color: #F2F2F2; margin-top: 7px; margin-bottom: 7px;" />
<div class="row padding-0 margin-0">
	<div class="col-xs-7 border-right-2">
		<table class="table table-main">
			<tbody>
				<tr>
					<td rowspan="2"><img class="img-responsive"
						src="/o/buckzy-theme/images/test.png" /></td>
					<th>Nov-17-2017</th>
					<th>Tracking #</th>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>John Smith</td>
					<td>17-1121-28192</td>
					<td>1,350.00 <span class="currency">USD</span></td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="col-xs-5">
		<table class="table-icons">
			<tbody>
				<tr>
					<td><img src="/o/buckzy-theme/images/resend_icon.png" /></td>
					<td><img src="/o/buckzy-theme/images/details-icons.png" /></td>
					<td><img src="/o/buckzy-theme/images/complete-icon.png" /></td>
				</tr>
				<tr>
					<td
						style="color: #7092BE; font-size: 12px; font-weight: bold; font-family: Verdana;">Resend</td>
					<td
						style="color: #B60E16; font-size: 12px; font-weight: bold; font-family: Verdana;">Details</td>
					<td
						style="color: #0E471F; font-size: 12px; font-weight: bold; font-family: Verdana;">Complete</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<hr style="background-color: #F2F2F2; margin-top: 7px; margin-bottom: 7px;" />
	</c:otherwise>
</c:choose>
<div>

</div>