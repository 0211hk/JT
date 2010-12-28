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

	public AbstractRequest(final Config config,
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
