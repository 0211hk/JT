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

/**
 * enumeration Twitter Parameter Key<br />
 * recommend static import.
 * <pre>
 * import static org.hk.jt.client.HttpMethod.*;
 * JSONObject j = twitterClient
 *                      .from(STATUSES_UPDATE)
 *                      .method(POST)
 *                      .param(<b>TEXT</b>,"test tweet")
 *                      .getJsonObject();
 * </pre>
 * @author hk
 */
public enum TwitterParams {

    /**
     * since_id
     */
    SINCE_ID("since_id"),
    /**
     * max_id
     */
    MAX_ID("max_id"),
    /**
     * count
     */
    COUNT("count"),
    /**
     * screen name
     */
    SCREEN_NAME("screen_name"), 
    /**
     * include rts
     */
    INCLUDE_RTS("include_rts"),
    /**
     * user a
     */
    USER_A("user_a"),
    /**
     * user b
     */
    USER_B("user_b"),
    /**
     * follow
     */
    FOLLOW("follow"),
    /**
     * user
     */
    USER("user"),
    /**
     * text
     */
    TEXT("text"),
    /**
     * status
     */
    STATUS("status");
    private String params;

    private TwitterParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return this.params;
    }
}
