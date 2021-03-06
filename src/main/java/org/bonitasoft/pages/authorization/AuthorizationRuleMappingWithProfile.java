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

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.core.form.impl.AuthorizationRuleMappingImpl;

/**
 * @author Anthony Birembaut
 *
 */
public class AuthorizationRuleMappingWithProfile extends AuthorizationRuleMappingImpl {

    public List<String> getProcessOverviewRuleKeys() {
        List<String> processOverviewRuleKeys = new ArrayList<String>(super.getProcessOverviewRuleKeys());
        processOverviewRuleKeys.add(HasProfileRule.KEY);
        return processOverviewRuleKeys;
    }

    public List<String> getTaskRuleKeys() {
        List<String> taskRuleKeys = new ArrayList<String>(super.getTaskRuleKeys());
        taskRuleKeys.add(HasProfileRule.KEY);
        return taskRuleKeys;
    }

}
