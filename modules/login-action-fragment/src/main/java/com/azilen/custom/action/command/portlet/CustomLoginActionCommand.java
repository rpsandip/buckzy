/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.azilen.custom.action.command.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPoolUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
@Component(
        immediate = true,
        property = {
            "javax.portlet.name=com_liferay_login_web_portlet_FastLoginPortlet",
            "javax.portlet.name=com_liferay_login_web_portlet_LoginPortlet",
            "mvc.command.name=/login/login",
            "service.ranking:Integer=1850"},
        service = MVCActionCommand.class
        )
public class CustomLoginActionCommand extends BaseMVCActionCommand {
    
    private static final Log _log = LogFactoryUtil.getLog(CustomLoginActionCommand.class);
    
    @Reference(target = "(&(mvc.command.name=/login/login)(javax.portlet.name=com_liferay_login_web_portlet_FastLoginPortlet)(javax.portlet.name=com_liferay_login_web_portlet_LoginPortlet)(component.name=com.liferay.login.web.internal.portlet.action.LoginMVCActionCommand))")
    protected MVCActionCommand mvcActionCommand;    
    
    public CustomLoginActionCommand() {
        super();
    }
    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        
    	 String emailAdddress = ParamUtil.getString(actionRequest, "login");
    	 String password  = ParamUtil.getString(actionRequest, "password");
    	 
    	 // We need to store user password in session because REST_API login will require string password
    	 // to connect it.
    	 //PortalUtil.getHttpServletRequest(actionRequest).getSession().setAttribute("user_token", password);
    	 PortalCache portalCache =   MultiVMPoolUtil.getCache(User.class.getName());
    	 portalCache.put("user_token", password);
    	 
    	 
         mvcActionCommand.processAction(actionRequest, actionResponse);
    }
}
