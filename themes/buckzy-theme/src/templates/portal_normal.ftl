<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />

	<@liferay_util["include"] page=top_head_include />
	
	<link rel="stylesheet" href="${css_folder}/bootstrap.min.css">
	<link rel="stylesheet" href="${css_folder}/bootstrap-theme.min.css">
	<link rel="stylesheet" href="${css_folder}/font-awesome.min.css">
	<link rel="stylesheet" href="${css_folder}/bootstrap-datetimepicker.css">
	<link rel="stylesheet" href="${css_folder}/bootstrap-social.css">
	<link rel="stylesheet" href="${css_folder}/styles.css">
	<link rel="stylesheet" href="${css_folder}/dd.css">
	<link rel="stylesheet" href="${css_folder}/toastem.css">
	<link rel="stylesheet" href="${css_folder}/custom_buckzy.css">
	
	
	<script>
    	define._amd = define.amd;
		define.amd = false;
	</script>
	
	<script src="${javascript_folder}/jquery.min.js"></script>
	<script src="${javascript_folder}/jquery-ui.js"></script>
	<script src="${javascript_folder}/jquery.dd.min.js"></script>
	<script src="${javascript_folder}/jquery.ddslick.min.js"></script>
	<script src="${javascript_folder}/moment.min.js"></script>
	<script src="${javascript_folder}/bootstrap-datetimepicker.min.js"></script>
	<script src="${javascript_folder}/jquery.formatter.js"></script>
	<script src="${javascript_folder}/toastem.js"></script>
	
	<script>
		define.amd = define._amd;
	</script>
	
</head>

<body style="background-color: #FBFBFB;">
    <!-- Toast message div -->
	<div id="toastem"></div>
	<div>
	<#if layout.isPublicLayout()>
		<@liferay_ui["quick-access"] contentId="#main-content" />
		<@liferay_util["include"] page=body_top_include />
		
		<@liferay_util["include"] page=body_bottom_include />
		<@liferay_util["include"] page=bottom_include />
		<div class="">
		
	        <div class="row header-container">
            	<div class="col-xs-5" style=" text-align: left; padding-left: 15px; padding-top: 7px;">
                	<a href="/c/portal/logout"><img src="${images_folder}/logo.png" /></a>
            	</div>
            </div>
            <div class="col-xs-7">
            </div> 	
			<div style="display: table; width: 100%;">
				<div style="display: table-row;">
				
				<div style="display: table-cell; padding-bottom: 50px; ">
                    <div class="main-container">
						<#if selectable>
							<@liferay_util["include"] page=content_include />
						<#else>
							${portletDisplay.recycle()}
				
							${portletDisplay.setTitle(the_title)}
				
							<@liferay_theme["wrap-portlet"] page="portlet.ftl">
								<@liferay_util["include"] page=content_include />
							</@>
						</#if>
					</div>
				</div>
				</div>
			</div>
		</div>
		
		<script>
    		define._amd = define.amd;
			define.amd = false;
		</script>
			<script src="${javascript_folder}/moment.min.js"></script>
			<script src="${javascript_folder}/bootstrap-datetimepicker.min.js"></script>
	
		<script>
			define.amd = define._amd;
		</script>
		
		
	 
 	<#else>
 		<@liferay_ui["quick-access"] contentId="#main-content" />
		<@liferay_util["include"] page=body_top_include />
		
		<#if is_omni_admin>
			<@liferay.control_menu />
		    <br/>
			<br/>
			<br/>
		</#if>
	
		<div class="">
			<div class="mobile-submenu">
	            <ul>
	                <li><img src="${images_folder}/icon1.png" />Send Money</li>
	                <li><img src="${images_folder}/icon2.png" />Request Money</li>
	                <li><img src="${images_folder}/icon3.png" />Support</li>
	                <li><img src="${images_folder}/icon4.png" />My Profile
	                    <ul>
	                        <li>Profile Settings</li>
	                        <li>My Accounts</li>
	                        <li>Wallet Settings</li>
	                        <li>Logout</li>
	                    </ul>
	                </li>
	            </ul>
	        </div>
	        
	        <div class="row header-container">
	            <div class="col-xs-5" style=" text-align: left; padding-left: 15px; padding-top: 7px;">
	                <a href="/group/guest"><img src="${images_folder}/logo.png" /></a>
	            </div>
	            <div class="col-xs-7" >
	                <div class="row padding-0 margin-0">
	                    <div class="fa fa-bars mobile-menu pointer" id="mobile-menu">
	                        
	                    </div>
	                    <div class="header-right-block">
	                        <div class="col-xs-3 padding-top-20">
	                            <a href="/group/guest/send-money"><span class="pointer"><img src="${images_folder}/icon1.png" />Send Money</span></a>
	                        </div>
	                        <div class="col-xs-3 padding-top-20">
	                            <a href="/group/guest/track-transfer"><span class="pointer"><img src="${images_folder}/icon2.png" />Request Money</span></a>
	                        </div>
	                        <div class="col-xs-3 padding-top-20">
	                            <div class="float-left pointer">
	                                <img src="${images_folder}/icon3.png" />
	                                Support
	                            </div>
	                            <!--<div class="support-message">1</div>  -->
	                        </div>
	                        <div class="col-xs-3 padding-top-20">
	                            <div id="profile" class="pointer" >
	                                <img src="${images_folder}/icon4.png" /><span class="myprofile pointer">My Profile</span>
	                                <ul class="submenu">
	                                    <li><a href="/group/guest/profile">Profile Settings</a></li>
	                                    <li>My Accounts</li>
	                                    <li>Wallet Settings</li>
	                                    <li><a href="/c/portal/logout">Logout</a></li>
	                                </ul>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	        <div class="left-menu-mobile">
	            <img src="${images_folder}/left-icon1.png" />
	            <img src="${images_folder}/left-icon2.png" />
	            <img src="${images_folder}/left-icon3.png" />
	            <img src="${images_folder}/left-icon4.png" />
	            <img src="${images_folder}/left-icon5.png" />
	            <img src="${images_folder}/left-icon6.png" />
	        </div>
			<div style="display: table; width: 100%;">
				<div style="display: table-row;">
				<div id="left-panel">
			        <div id="left-panel" >
	                    <img src="${images_folder}/left-icon1.png" />
	                    <img src="${images_folder}/left-icon2.png" />
	                    <img src="${images_folder}/left-icon3.png" />
	                    <img src="${images_folder}/left-icon4.png" />
	                    <img src="${images_folder}/left-icon5.png" />
	                    <img src="${images_folder}/left-icon6.png" />
	                    <div class="left-hover">
	                        <a href="/group/guest/home"><img src="${images_folder}/icon-8.png" /> Dashboard </a><br/>
	                        <img src="${images_folder}/icon-9.png" /> Payee Accounts <br/>
	                        <a href="/group/guest/send-money"><img src="${images_folder}/icon-10.png" /> New Transfer <br/></a>
	                        <a href="/group/guest/track-transfer"><img src="${images_folder}/icon-11.png" /> Request Money <br/></a>
	                        <img src="${images_folder}/icon-12.png" /> Download Mobile App <br/>
	                        <img src="${images_folder}/icon-13.png" /> Help & Support <br/>
	                    </div>
                	</div>		
				</div>
				<div style="display: table-cell; padding-bottom: 50px; ">
                    <div class="main-container">
						<#if selectable>
							<@liferay_util["include"] page=content_include />
						<#else>
							${portletDisplay.recycle()}
				
							${portletDisplay.setTitle(the_title)}
				
							<@liferay_theme["wrap-portlet"] page="portlet.ftl">
								<@liferay_util["include"] page=content_include />
							</@>
						</#if>
					</div>
				</div>
				</div>
			</div>
		</div>
		
		<!-- Footer : START -->
		<footer id="footer" role="contentinfo">
			<div class="footer-container">
		        <div class="footer-main">
		            <div class="col-sm-8 footer-padding-links">
		                <div class="col-sm-4">
		                    <a href="#">Home</a>
		                    <a href="#">About Us</a>
		                    <a href="#">Contact Us</a>
		                    <a href="#">Partnerships</a>
		                    <a href="#">Help</a>
		                </div>
		                <div class="col-sm-4">
		                    <a href="#">Terms and Conditions</a>
		                    <a href="#">Online Private Statement</a>
		                    <a href="#">Report Complaint</a>
		                    <a href="#">Blog</a>
		                </div>
		                <div class="col-sm-4">
		                    <a href="#">Developer API's</a>
		                    <a href="#">Small Business Solutions</a>
		                    <a href="#">Sitemap</a>
		                    <a href="#">Partner Portal</a>
		                </div>
		                <div class="col-sm-12 copyright">
		                    &copy; 2018 Buckzy Ltd. All Rights Reserved.
		                </div>
		            </div>
		            <div class="col-sm-4 footer-padding-icons">
		                <div class="col-sm-12 text-center">Follow Us</div>
		                <div class="col-sm-12 text-center">
		                    <div class="display-table margin-auto" >
		                        <div class="float-left margin-10">
		                            <a class="btn btn-block btn-social-icon btn-twitter">
		                                <span class="fa fa-twitter"></span>
		                            </a>
		                        </div>
		                        <div class="float-left margin-10">
		                            <a class="btn btn-block btn-social-icon btn-linkedin">
		                                <span class="fa fa-linkedin"></span>
		                            </a>
		                        </div>
		                        <div class="float-left margin-10">
		                            <a class="btn btn-block btn-social-icon btn-facebook">
		                                <span class="fa fa-facebook"></span>
		                            </a>
		                        </div>
		                    </div>
		                    <div class="col-sm-12 text-center">
		                        <img src="${images_folder}/google-play.png" class="google-play" />
		                    </div>
		                    <div class="col-sm-12 text-center">
		                        <img src="${images_folder}/apple-store.png" class="apple-store" />
		                    </div>
		                </div>
		            </div>
		            
		        </div>
		    </div>
		</footer>
		<!-- Footer : END -->
		
		
		<@liferay_util["include"] page=body_bottom_include />
		
		<@liferay_util["include"] page=bottom_include />
		
		<script>
    		define._amd = define.amd;
			define.amd = false;
		</script>
			<script src="${javascript_folder}/custom.js"></script>
		<script>
			define.amd = define._amd;
		</script>

	</#if>
	</div>
</body>
</html>