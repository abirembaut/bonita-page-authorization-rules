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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bonitasoft.engine.persistence.OrderByType;
import org.bonitasoft.engine.profile.ProfileService;
import org.bonitasoft.engine.profile.model.SProfile;
import org.bonitasoft.engine.session.SessionService;
import org.bonitasoft.engine.sessionaccessor.SessionAccessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HasProfileRuleTest {
    
    @Mock
    ProfileService profileService;

    @Mock
    SessionService sessionService;

    @Mock
    SessionAccessor sessionAccessor;
    
    HasProfileRule isProfileMemberRule;
    
    @Before
    public void init() throws Exception {
        isProfileMemberRule = spy(new HasProfileRule(profileService, sessionService, sessionAccessor));
        doReturn("My profile").when(isProfileMemberRule).getProfileName();
        doReturn(2L).when(isProfileMemberRule).getLoggedUserId();
    }
    
    private void initProfilesList(String... profilesNames) throws Exception {
        List<SProfile> userProfiles = new ArrayList<>();
        for (String profilesName : profilesNames) {
            SProfile myProfile = mock(SProfile.class);
            doReturn(profilesName).when(myProfile).getName();
            userProfiles.add(myProfile);
        }
        doReturn(userProfiles).when(profileService).searchProfilesOfUser(2L, 0, Integer.MAX_VALUE, "name", OrderByType.ASC);
    }

    @Test
    public void should_allow_user_with_right_profile() throws Exception {
        initProfilesList("My profile", "My other Profile");
        
        assertTrue(isProfileMemberRule.isAllowed("anyKey", new HashMap<String, Serializable>()));
    }

    @Test
    public void should_not_allow_user_with_wrong_profile() throws Exception {
        initProfilesList("My new profile", "My other Profile");
        
        assertFalse(isProfileMemberRule.isAllowed("anyKey", new HashMap<String, Serializable>()));
    }
    
    @Test
    public void should_not_allow_user_with_no_profiles() throws Exception {
        initProfilesList();
        
        assertFalse(isProfileMemberRule.isAllowed("anyKey", new HashMap<String, Serializable>()));
    }

}
