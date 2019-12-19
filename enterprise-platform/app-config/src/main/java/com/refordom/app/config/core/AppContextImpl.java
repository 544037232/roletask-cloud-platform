/*
 * Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.refordom.app.config.core;

import com.refordom.app.config.AppDetails;
import com.refordom.app.model.AppModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * Base implementation of {@link SecurityContext}.
 * <p>
 * Used by default by {@link SecurityContextHolder} strategies.
 *
 * @author Ben Alex
 */
public class AppContextImpl implements AppContext {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    // ~ Instance fields
    // ================================================================================================

    private AppModel appModel;

    private AppDetails appDetails;

    // ~ Methods
    // ========================================================================================================

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AppContextImpl) {
            AppContextImpl test = (AppContextImpl) obj;

            if ((this.getAppModel() == null) && (test.getAppModel() == null)
                    && (this.getAppDetails() == null) && (test.getAppDetails() == null)) {
                return true;
            }

			return (this.getAppModel() != null) && (test.getAppModel() != null)
					&& this.getAppModel().equals(test.getAppModel())
					&& (this.getAppDetails() != null) && (test.getAppDetails() != null)
					&& this.getAppDetails().equals(test.getAppDetails());
        }

        return false;
    }

	@Override
	public int hashCode() {
		return Objects.hash(appModel, appDetails);
	}

	@Override
	public String toString() {
		return "AppContextImpl{" +
				"appModel=" + appModel +
				", appDetails=" + appDetails +
				'}';
	}

	@Override
    public AppModel getAppModel() {
        return appModel;
    }

    @Override
    public void setAppModel(AppModel appModel) {
        this.appModel = appModel;
    }

    @Override
    public AppDetails getAppDetails() {
        return appDetails;
    }

    @Override
    public void setAppDetails(AppDetails appDetails) {
        this.appDetails = appDetails;
    }
}
