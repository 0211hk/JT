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
	private static final String ACCESS_TOKEN_URL = "https://api.twitter.com/oauth/access_token";
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
		return ACCESS_TOKEN_URL;
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
		return HttpMethod.POST;
	}
}
