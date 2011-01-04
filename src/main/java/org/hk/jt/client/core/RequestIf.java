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
package org.hk.jt.client.core;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.hk.jt.client.Config;

import org.apache.http.NameValuePair;
import org.hk.jt.client.HttpMethod;
import org.json.JSONException;

public interface RequestIf<T> {

	T getResponse(final String response) throws JSONException;

	Map<String, String> getPostParameters() throws UnsupportedEncodingException;

	List<NameValuePair> getFiles();

	String getUrl();

	SortedMap<String, String> getAuthParameter();
	
	Config getConfig();
	
	HttpMethod getMethod();
}
