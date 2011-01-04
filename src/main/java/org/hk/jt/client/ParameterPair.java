/**
 * Copyright 2010-2011 the HK Software and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.hk.jt.client;

import org.apache.http.NameValuePair;

/**
 * Simple Name And Value pair Object.
 * <pre>
 * JSONArray j = twitterClient
 *                      .from(HOME_TIMELINE)
 *                      .param(
 *                          <b>new ParameterPair(SINCE_ID, "100"),
 *                          new ParameterPair("max_id", "100")</b>
 *                       )
 *                       .getJsonArray();
 * </pre>
 * @see NameValuePair
 * @author hk
 */
public class ParameterPair  implements NameValuePair{

    /**
     * name
     */
    private final String name;
    /**
     * value
     */
    private final String value;

    /**
     * Create Object With String
     * @param name TwitterParameterName
     * @param value TwitterParameterValue
     */
    public ParameterPair(final String name, final String value) {
        super();
        if (name == null) {
            throw new IllegalArgumentException("Name may not be null");
        }
        this.name = name;
        this.value = value;
    }

    /**
     * Create Object With TwitterParams
     * @see TwitterParams
     * @param name TwitterParameterName
     * @param value TwitterParameterValue
     */
    public ParameterPair(final TwitterParams name , final String value){
        super();
        if(name == null){
            throw new IllegalArgumentException("Name may not be null");
        }
        this.name = name.toString();
        this.value = value;
    }

    /**
     * get TwitterParameterName
     * @return name TwitterParameterName
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * get TwitterParameterValue
     * @return name TwitterParameterValue
     */
    @Override
    public String getValue() {
        return this.value;
    }
}
