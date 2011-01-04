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
 * enumeration HttpMethod<br />
 * support GET, POST, DELETE, PUT<br />
 * recommend static import.
 * <pre>
 * import static org.hk.jt.client.HttpMethod.*;
 * JSONObject j = twitterClient
 *                      .from(STATUSES_UPDATE)
 *                      .method(<b>POST</b>)
 *                      .param(TEXT,"test tweet")
 *                      .getJsonObject();
 * </pre>
 * @author hk
 */
public enum HttpMethod {

    /**
     * Http Method GET
     */
    GET("GET"), 
    /**
     * Http Method POST
     */
    POST("POST"),
    /**
     * Http Method DELETE
     */
    DELETE("DELETE"),
    /**
     * Http Method PUT
     */
    PUT("PUT");
    private String method;

    private HttpMethod(String method) {
        this.method = method;
    }

    /**
     * return "GET" or "POST" or "DELETE" or "PUT"
     * @return http method
     */
    @Override
    public String toString() {
        return this.method;
    }
}
