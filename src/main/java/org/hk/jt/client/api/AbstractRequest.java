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
package org.hk.jt.client.api;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.apache.http.NameValuePair;
import org.json.JSONException;

import org.hk.jt.client.Config;
import org.hk.jt.client.HttpMethod;
import org.hk.jt.client.core.RequestIf;
import org.hk.jt.client.util.TwitterClientUtil;

public abstract class AbstractRequest<T> implements RequestIf<T> {

	private final Config config;
	private final PostParameterIf postParameterIf;

	AbstractRequest(final Config config,
			final PostParameterIf postParameterIf) {
		this.config = config;
		this.postParameterIf = postParameterIf;
	}

	@Override
	public Map<String, String> getPostParameters()
			throws UnsupportedEncodingException {
		final Map<String, String> map = new HashMap<String, String>();
		postParameterIf.setPostParameter(map);
		return map;
	}

	@Override
	public List<NameValuePair> getFiles() {
		return null;
	}

	@Override
	public SortedMap<String, String> getAuthParameter() {
		return TwitterClientUtil.getXAuthParameter(config);
	}

	@Override
	public Config getConfig() {
		return config;
	}

	@Override
	abstract public T getResponse(String response) throws JSONException;

	@Override
	public HttpMethod getMethod() {
		return postParameterIf.getMethod();
	}

	@Override
	public String getUrl() {
		return postParameterIf.getUrl();
	}
}
