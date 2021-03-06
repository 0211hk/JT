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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.NameValuePair;

import org.json.JSONArray;
import org.json.JSONObject;

import org.hk.jt.client.api.AccessToken;
import org.hk.jt.client.api.PostParameterIf;
import org.hk.jt.client.api.RequestTwitterJsonArray;
import org.hk.jt.client.api.RequestTwitterJsonObject;
import org.hk.jt.client.api.RequestTwitterString;
import org.hk.jt.client.core.Request;
import static org.hk.jt.client.HttpMethod.*;
import static org.hk.jt.client.TwitterUrls.*;

/**
 * Access to Twitter.
 * 
 * <pre>
 * import org.hk.jt.client.TwitterClient;
 * //static import
 * import static org.hk.jt.client.TwitterUrls.*;
 * 
 * // create TwitterClient this
 * TwitterClient twitterClient = TwitterClient.getthis(_CONSUMER_KEY,
 * 		_CONSUMER_SERCRET, _USER_ID, _PASSWORD);
 * // first you get AccessToken
 * twitterClient.getAccessToken();
 * // get home timeline
 * JSONArray jsonArray = twitterClient.from(HOME_TIMELINE)
 * 		.getJsonArray();
 * // expect 20
 * System.out.println(jsonArray.length());
 * 
 * jsonArray = twitterClient.from(HOME_TIMELINE).param(&quot;page&quot;, &quot;100&quot;)
 * 		.getJsonArray();
 * // expect 100
 * System.out.println(jsonArray.length());
 * </pre>
 * 
 * @author hk
 */
public final class TwitterClient {

	private final Config config;
	private final ExecutorService es = Executors.newCachedThreadPool();
	private final Map<String, String> paramMap = new HashMap<String, String>();
	private String url = null;
	private HttpMethod method = null;

	private TwitterClient(final String consumerKey,
			final String consumerSercret, final String userId,
			final String password, final boolean isAccessToken) {
		if (isAccessToken) {
			config = Config.getInstanceWithAccessToken(consumerKey,
					consumerSercret, userId, password);
		} else {
			config = Config.getInstance(consumerKey, consumerSercret, userId,
					password);
		}
	}

	/***
	 * create instance with twitter user name & password
	 * 
	 * @param consumerKey
	 *            twitter xauth consumer key
	 * @param consumerSercret
	 *            twitter xauth consumer sercret
	 * @param userName
	 *            twitter username
	 * @param password
	 *            twitter password
	 * @return this
	 */
	public static TwitterClient getInstance(final String consumerKey,
			final String consumerSercret, final String userName,
			final String password) {
		return new TwitterClient(consumerKey, consumerSercret, userName,
				password, false);
	}

	/**
	 * create instance with twitter accesstoken & accesstoken sercret
	 * 
	 * @param consumerKey
	 *            twitter xauth consumer key
	 * @param consumerSercret
	 *            twitter xauth consumer sercret
	 * @param accessToken
	 *            accesstoken
	 * @param accessTokenSercret
	 *            accesstoken sercret
	 * @return this
	 */
	public static TwitterClient getInstanceWithAccessToken(
			final String consumerKey, final String consumerSercret,
			final String accessToken, final String accessTokenSercret) {
		return new TwitterClient(consumerKey, consumerSercret, accessToken,
				accessTokenSercret, true);
	}

	/**
	 * set Twitter API URL
	 * 
	 * @see TwitterUrls
	 * @param url
	 *            Twitter API URL
	 * @return this
	 */
	public TwitterClient from(final String url) {
		this.url = url;
		return this;
	}

	/**
	 * Set Twitter API URL With format String
	 * 
	 * @param <T>
	 * @param url
	 *            Twitter API URL (A format string)
	 * @param args
	 *            Arguments referenced by the format specifiers in the format
	 *            string.
	 * @return this
	 */
	public <T> TwitterClient from(final String url, final T... args) {
		this.url = String.format(url, args);
		return this;
	}

	/**
	 * set Twitter API URL
	 * 
	 * @see TwitterUrls
	 * @param url
	 *            Twitter API URL
	 * @return this
	 */
	public TwitterClient from(final TwitterUrls url) {
		this.url = url.toString();
		return this;
	}

	/**
	 * Set Twitter API URL With format String
	 * 
	 * @param url
	 *            Twitter API URL(A format string)
	 * @param args
	 *            Arguments referenced by the format specifiers in the format
	 *            string.
	 * @return
	 */
	public TwitterClient from(final TwitterUrls url, final String... args) {
		this.url = String.format(url.toString(), (Object[]) args);
		return this;
	}

	/**
	 * set extra parameter
	 * 
	 * @param key
	 *            parameter key
	 * @param value
	 *            parameter value
	 * @return this
	 */
	public TwitterClient param(final String key, final String value) {
		if (value != null && !value.equals("-1") && !value.equals("")) {
			this.paramMap.put(key, value);
		}
		return this;
	}

	/**
	 * set extra parameter
	 * 
	 * @param key
	 *            parameter key
	 * @param value
	 *            parameter value
	 * @return this
	 */
	public TwitterClient param(final TwitterParams param, final String value) {
		if (value != null && !value.equals("-1") && !value.equals("")) {
			this.paramMap.put(param.toString(), value);
		}
		return this;
	}

	/**
	 * set extra parameter
	 * 
	 * @param nameValuePair
	 *            Parameter Key & Parameter value
	 * @return this
	 */
	public TwitterClient param(final NameValuePair... nameValuePair) {
		for (NameValuePair pair : nameValuePair) {
			if (pair.getValue() != null && !pair.getValue().equals("-1")
					&& !pair.getValue().equals("")) {
				this.paramMap.put(pair.getName(), pair.getValue());
			}
		}
		return this;
	}

	public TwitterClient param(final Map<String, String> params) {
		for (String key : params.keySet()) {
			if (params.get(key) != null && !params.get(key).equals("-1")
					&& !params.get(key).equals("")) {
				this.paramMap.put(key, params.get(key));
			}
		}
		return this;
	}

	public TwitterClient param(final List<NameValuePair> nameValuePairList) {
		for (NameValuePair pair : nameValuePairList) {
			if (pair.getValue() != null && !pair.getValue().equals("-1")
					&& !pair.getValue().equals("")) {
				this.paramMap.put(pair.getName(), pair.getValue());
			}
		}
		return this;
	}

	/**
	 * set http method GET or POST or PUT or DELETE
	 * 
	 * @param method
	 * @return this
	 */
	public TwitterClient method(final HttpMethod method) {
		this.method = method;
		return this;
	}

	/**
	 * clear parameter
	 * 
	 * @return
	 */
	public TwitterClient clearParam() {
		this.paramMap.clear();
		return this;
	}

	/**
	 * get Twitter return value as JsonArray
	 * 
	 * @return
	 * @throws Exception
	 */
	public JSONArray getJsonArray() throws Exception {
		JSONArray jsonArray = execRequest(new Request<JSONArray>(
				new RequestTwitterJsonArray(this.config, new PostParameter(
						this.method, this.url, this.paramMap))));
		return jsonArray;
	}

	/**
	 * get accesstoken
	 * 
	 * @return map
	 * @throws Exception
	 */
	public Map<String, String> getAccessToken() throws Exception {
		Map<String, String> map = execRequest(new Request<Map<String, String>>(
				new AccessToken(config, new PostParameter(POST,
						ACCESS_TOKEN.toString(), this.paramMap))));
		config.setAccessToken(map.get("oauth_token"));
		config.setAccessTokenSercret(map.get("oauth_token_secret"));
		return map;
	}

	/**
	 * get Twitter return value as JsonObject
	 * 
	 * @return jsonObject
	 * @throws Exception
	 */
	public JSONObject getJsonObject() throws Exception {
		JSONObject jsonObject = execRequest(new Request<JSONObject>(
				new RequestTwitterJsonObject(this.config, new PostParameter(
						this.method, this.url, this.paramMap))));
		return jsonObject;
	}

	/**
	 * get Twitter return value as String
	 * 
	 * @return String
	 * @throws Exception
	 */
	public synchronized String get() throws Exception {
		String response = execRequest(new Request<String>(
				new RequestTwitterString(this.config, new PostParameter(
						this.method, this.url, this.paramMap))));
		return response;
	}

	/**
	 * get async Twitter return value as JsonArray
	 * 
	 * @param twitterCallbackIf
	 * @throws Exception
	 */
	public void getAsyncJsonArray(
			final TwitterCallbackIf<JSONArray> twitterCallbackIf)
			throws Exception {
		es.submit(new ExecRequestCallable<JSONArray>(new Request<JSONArray>(
				new RequestTwitterJsonArray(this.config, new PostParameter(
						this.method, this.url, paramMap))), twitterCallbackIf));
		es.shutdown();
	}

	/**
	 * get async Twitter return value as JsonObject
	 * 
	 * @param twitterCallbackIf
	 *            call back Object must implements TwitterCallbackIf
	 * @throws Exception
	 */
	public void getAsyncJsonObject(
			final TwitterCallbackIf<JSONObject> twitterCallbackIf)
			throws Exception {
		es.submit(new ExecRequestCallable<JSONObject>(new Request<JSONObject>(
				new RequestTwitterJsonObject(this.config, new PostParameter(
						this.method, this.url, paramMap))), twitterCallbackIf));
		es.shutdown();
	}

	/**
	 * get Async Twitter return value as String
	 * 
	 * @param twitterCallbackIf
	 *            call back Object must implements TwitterCallbackIf
	 * @throws Exception
	 */
	public void getAsync(final TwitterCallbackIf<String> twitterCallbackIf)
			throws Exception {
		es.submit(new ExecRequestCallable<String>(new Request<String>(
				new RequestTwitterString(this.config, new PostParameter(
						this.method, this.url, paramMap))), twitterCallbackIf));
		es.shutdown();
	}

	private <E> E execRequest(final Request<E> req) throws Exception {
		return req.request();
	}

	class ExecRequestCallable<T> implements Callable<Void> {

		private final Request<T> request;
		private final TwitterCallbackIf<T> twitterCallbackIf;

		ExecRequestCallable(final Request<T> request,
				final TwitterCallbackIf<T> twitterCallbackIf) {
			this.request = request;
			this.twitterCallbackIf = twitterCallbackIf;
		}

		@Override
		public Void call() throws Exception {
			twitterCallbackIf.twitterCallback(request.request());
			return null;
		}
	}

	class PostParameter implements PostParameterIf {

		private final HttpMethod method;
		private final String urls;
		private final Map<String, String> paramMap;

		public PostParameter(final HttpMethod method, final String urls,
				final Map<String, String> paramMap) {
			if (method == null) {
				throw new NullPointerException(
						"HttpMethod is null.Must be set HttpMethod.");
			}
			if (urls == null || urls.equals("")) {
				throw new NullPointerException(
						"TwitterUrl is null.Must be set Url.");
			}

			this.method = method;
			this.urls = urls;
			this.paramMap = paramMap;
		}

		@Override
		public void setPostParameter(Map<String, String> defaultParameter) {
			if (paramMap != null && !paramMap.isEmpty()) {
				for (String key : paramMap.keySet()) {
					defaultParameter.put(key, paramMap.get(key));
				}
				paramMap.clear();
			}
		}

		@Override
		public HttpMethod getMethod() {
			return method;
		}

		@Override
		public String getUrl() {
			return urls;
		}
	}
}
