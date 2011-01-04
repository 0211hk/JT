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
package org.hk.jt.client.util;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import org.hk.jt.client.Config;

public final class TwitterClientUtil {

    private TwitterClientUtil() {
    }

    public static SortedMap<String, String> getXAuthParameter(final Config config) {
        SortedMap<String, String> map = new TreeMap<String, String>();
        map.put("oauth_consumer_key", config.getConsumerKey());
        map.put("oauth_nonce", UUID.randomUUID().toString());
        map.put("oauth_signature_method", config.getSignatureMethod());
        map.put("oauth_timestamp", Long.toString(System.currentTimeMillis() / 1000));
        map.put("oauth_version", config.getOauthVersion());
        map.put("oauth_token", config.getAccessToken());
        return map;
    }

    public static SortedMap<String, String> getXAuthAccessParameter(final Config config) {
        SortedMap<String, String> map = new TreeMap<String, String>();
        map.put("oauth_consumer_key", config.getConsumerKey());
        map.put("oauth_nonce", UUID.randomUUID().toString());
        map.put("oauth_signature_method", config.getSignatureMethod());
        map.put("oauth_timestamp", Long.toString(System.currentTimeMillis() / 1000));
        map.put("oauth_version", config.getOauthVersion());
        return map;
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
