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

import java.io.UnsupportedEncodingException;

public final class SignatureEncode {

    private SignatureEncode() {
    }
    private static final String UNRESERVEDCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.~";

    public static String encode(String s) throws UnsupportedEncodingException {
        byte[] bytes = s.getBytes("utf-8");
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            char c = (char) b;
            if (UNRESERVEDCHARS.indexOf(String.valueOf(c)) >= 0) {
                builder.append(String.valueOf(c));
            } else {
                builder.append("%").append(String.valueOf(Integer.toHexString(b > 0 ? b : b + 256)).toUpperCase());
            }
        }
        return builder.toString();
    }
}
