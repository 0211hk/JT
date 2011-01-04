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

import org.hk.jt.client.Config;
import org.hk.jt.client.core.RequestIf;
import org.hk.jt.client.util.TwitterClientUtil;

import org.apache.http.NameValuePair;
import org.hk.jt.client.HttpMethod;

public class AccessToken implements RequestIf<Map<String,String>>{

	private final Config config;
	private final PostParameterIf postParameterIf;

	public AccessToken(final Config config){
		this(config,null);
	}
	
	public AccessToken(final Config config,final PostParameterIf postParameterIf){
		this.config = config;
		this.postParameterIf = postParameterIf;
	}

	@Override
	public	Map<String,String> getResponse(String response) {
		System.out.println(getUrl());
		System.out.println(response);
		String[] resp = response.split("&");
		Map<String,String> map = new HashMap<String,String>();
		for(String r : resp){
			String[] param = r.split("=");
			map.put(param[0], param[1]);
		}
		return map;
	}

	@Override
	public Map<String,String> getPostParameters() throws UnsupportedEncodingException {
		final Map<String,String> map = new HashMap<String,String>();
		map.put("x_auth_mode", "client_auth");
		map.put("x_auth_password", config.getPassword());
		map.put("x_auth_username", config.getUserId());
		if(postParameterIf != null){
			postParameterIf.setPostParameter(map);
		}
		return map;
	}

	@Override
	public List<NameValuePair> getFiles() {
		return null;
	}

	@Override
	public String getUrl(){
		return postParameterIf.getUrl();
	}

	@Override
	public SortedMap<String, String> getAuthParameter() {
		return TwitterClientUtil.getXAuthAccessParameter(config);
	}

	@Override
	public Config getConfig() {
		return config;
	}

	@Override
	public HttpMethod getMethod() {
		return postParameterIf.getMethod();
	}
}
