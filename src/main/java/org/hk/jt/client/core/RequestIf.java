package org.hk.jt.client.core;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.hk.jt.client.Config;

import org.apache.http.NameValuePair;
import org.json.JSONException;

public interface RequestIf<T> {

	T getResponse(final String response) throws JSONException;

	Map<String, String> getPostParameters() throws UnsupportedEncodingException;

	List<NameValuePair> getFiles();

	String getUrl();

	SortedMap<String, String> getAuthParameter();
	
	Config getConfig();
	
	Method getMethod();
	
	public enum Method{
		GET("GET"),POST("POST"),DELETE("DELETE"),PUT("PUT");
		private String method;
		private Method(String method){
			this.method = method;
		}
		public String stringValue(){
			return this.method;
		}
	}
}
