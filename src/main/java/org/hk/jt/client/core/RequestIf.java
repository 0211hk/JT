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
