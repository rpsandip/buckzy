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
		
		.input-group .form-control{
		    border-radius: unset;
		}
		
		.input-group .form-control{
		    border-radius: unset;
		}
		
		.reg-form .form-group{
			padding: 0;
			margin: 0;
		 }
		 
		 .pad-0{
		 	padding: 0
		 }
		 
		  .pad-side-10{
		 	padding: 0 10px;
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
    	
    	.card-fname-label .control-label{
    		padding: 0px 15px !important;
    	}
    	
    	.card-fname-label .required{
    		padding: 0px 15px !important;
    	}
    	
    	.card-lname-label .control-label{
    		padding: 0px 20px !important;
    	}
    	
    	.card-lname-label .required{
    		padding: 0px 15px !important;
    	}
    	
    	.re-accnt .control-label{
    		padding-left: 7px;
    	}
    	
    	.re-accnt .required{
    		padding-left: 7px;
    	}
    	
    	.alert-success, .alert-danger{
    		width: 81% !important;
		    margin-left: 83px !important;
    	}
</style>