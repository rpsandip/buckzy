<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<style>
   		.portlet-title-default, .portlet-title-text {
			display: none;
		}

		
		 .form-control{
		    border-radius: unset !important;
		}
		
		.reg-form .form-group{
			padding: 0;
			margin: 0;
		 }
		 
		 .pad-0{
		 	padding: 0
		 }
		 
		 .pad-side-8{
		 	padding-left: 8px !important;
		 }
		 
		 .form-group{
		 	margin: 0 0 10px 0 !important
		 }
		 
		 .icon-input .input-group{
		 	width: 100%;
    		padding-right: 10px;
   			 margin-top: 10px;
    	}
    	
    	.icon-input .form-group{
    		display: inline-block;
    		float: left;
    		width: 85%;
   		    line-height: 20px;
    	}
    	
    	.icon-input .input-group-addon{
		    position: relative;
		    display: inline-block;
		    line-height: 20px;
		    float: left;
		    width: 15%;
    	}
    	
    	.icon-input .form-control{
    		border-radius: 0 5px 5px 0 !important;
    	}
    	
    	.help-block{
    		margin-bottom: 0px !important;
    	}
	
		.alert-success, .alert-danger{
    		width: 81% !important;
		    margin-left: 83px !important;
    	}
</style>