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
 * Twitter Callback interface.
 * <pre>
 * twitterClient.from(DIRECT_MESSAGE).getAsyncJsonArray(new TwitterCallbackIf&lt;JSONArray&gt;() {
 *          &#064;Override
 *          public void twitterCallback(JSONArray returnMap) {
 *              //TODO:
 *          }
 *      });
 * </pre>
 * @author administrator
 * @param <T>
 */
public interface TwitterCallbackIf<T> {

    /**
     * Async Callback
     * return value support JSONArray or JSONObject or String
     * @param returnMap JSONArray or JSONObject or String
     */
    public void twitterCallback(T returnMap);
}
