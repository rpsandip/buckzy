<%@ include file="/init.jsp"%>

<liferay-ui:error key="document-upload-err" message="document-upload-err"/>
<liferay-ui:success key="document-upload-success" message="document-upload-success"/>
<liferay-ui:error key="acccount-err" message="acccount-err"/>
<liferay-ui:success key="account-success" message="account-success"/>

<c:choose>
	<c:when test="${!isProfileCompleted}">
		<%@ include file="/profile-incomplete.jsp"%>
	</c:when>
	<c:otherwise>
		<%@ include file="/profile-complete.jsp"%>
	</c:otherwise>
</c:choose>
<div>

</div>