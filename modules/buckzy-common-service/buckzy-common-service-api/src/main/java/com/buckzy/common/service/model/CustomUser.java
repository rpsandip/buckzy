/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.buckzy.common.service.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CustomUser service. Represents a row in the &quot;BUCKZY_CustomUser&quot; database table, with each column mapped to a property of this class.
 *
 * @author Sandip.Patel
 * @see CustomUserModel
 * @see com.buckzy.common.service.model.impl.CustomUserImpl
 * @see com.buckzy.common.service.model.impl.CustomUserModelImpl
 * @generated
 */
@ImplementationClassName("com.buckzy.common.service.model.impl.CustomUserImpl")
@ProviderType
public interface CustomUser extends CustomUserModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.buckzy.common.service.model.impl.CustomUserImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CustomUser, Long> CUSTOM_USER_ID_ACCESSOR = new Accessor<CustomUser, Long>() {
			@Override
			public Long get(CustomUser customUser) {
				return customUser.getCustomUserId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CustomUser> getTypeClass() {
				return CustomUser.class;
			}
		};
}