/*
 * Copyright (c) 2010-2023 Evolveum
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.identity.connid.conector.integrity.utils;

import org.identityconnectors.common.security.GuardedString;

/**
 * Created by Viliam Repan (lazyman).
 */

/**
 * Convenience class overriding the standard Guarded string Accessor interface which is used to access
 * the clear string value of a Guarded string. The benefit of this class is a null check additional to the
 * return of the string of the password char sequence. The class is typically used as a parameter of the access method
 * implemented in the standard GuardedString clas.
 */
public class StringAccessor implements GuardedString.Accessor {

    private String value;


    @Override
    public void access(char[] chars) {
        value = chars == null ? null : String.valueOf(chars);
    }

    /**
     * Getter method for the value generated by the access method.
     */
    public String getValue() {
        return value;
    }
}
