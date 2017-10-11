/**
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.pages.authorization;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.bonitasoft.engine.commons.exceptions.SExecutionException;
import org.bonitasoft.engine.page.AuthorizationRule;
import org.bonitasoft.engine.page.AuthorizationRuleWithParameters;
import org.bonitasoft.engine.persistence.OrderByType;
import org.bonitasoft.engine.persistence.SBonitaReadException;
import org.bonitasoft.engine.profile.ProfileService;
import org.bonitasoft.engine.profile.model.SProfile;
import org.bonitasoft.engine.session.SessionService;
import org.bonitasoft.engine.sessionaccessor.SessionAccessor;

/**
 * @author Anthony Birembaut
 *
 */
public class HasProfileRule extends AuthorizationRuleWithParameters implements AuthorizationRule {

    public static final String KEY = "HAS_PROFILE_RULE";

    ProfileService profileService;

    SessionService sessionService;

    SessionAccessor sessionAccessor;
    
    String profileName;

    public HasProfileRule(ProfileService profileService, SessionService sessionService, SessionAccessor sessionAccessor) {
        this.profileService = profileService;
        this.sessionService = sessionService;
        this.sessionAccessor = sessionAccessor;
    }
    
    public String getId() {
        return KEY;
    }

    public boolean isAllowed(String key, Map<String, Serializable> context) throws SExecutionException {
        long userId = getLoggedUserId();
        try {
            List<SProfile>  userProfiles = profileService.searchProfilesOfUser(userId, 0, Integer.MAX_VALUE, "name", OrderByType.ASC);
            for (SProfile userProfile : userProfiles) {
                if (getProfileName().equals(userProfile.getName())) {
                    return true;
                }
            }
        } catch (SBonitaReadException e) {
            e.printStackTrace();
        }
        return false;
    }

    public long getLoggedUserId() throws SExecutionException {
        return getLoggedUserId(sessionAccessor, sessionService);
    }

    public String getProfileName() {
        return profileName;
    }

    
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

}
