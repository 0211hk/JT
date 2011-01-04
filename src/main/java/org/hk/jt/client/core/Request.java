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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.hk.jt.client.Config;
import org.hk.jt.client.HttpMethod;
import org.hk.jt.client.util.Base64;
import org.hk.jt.client.util.SignatureEncode;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;

public class Request<T> {

	private static final String CONTENTTYPE_BINARY = "application/octet-stream";
	private final Config config;
	final String SIGN_FORMAT = "%s&%s";
	private final RequestIf<T> requestIf;
	private Map<String, String> postParameter = new HashMap<String, String>();
	private SortedMap<String, String> authParameter = new TreeMap<String, String>();

	public Request(final RequestIf<T> requestIf) {
		this.requestIf = requestIf;
		this.config = this.requestIf.getConfig();
	}

	public T request() throws UnsupportedEncodingException, JSONException, IOException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException  {
		postParameter = requestIf.getPostParameters();
		authParameter = requestIf.getAuthParameter();
		HttpResponse response = null;
		if (requestIf.getMethod() == HttpMethod.POST) {
			response = execPost();
		} else if (requestIf.getMethod() == HttpMethod.GET) {
			response = execGet();
		}

		if (response == null) {
			throw new NullPointerException("response is Null!!!");
		}

		BufferedReader in = null;
		StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		try {
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()), 8);
			String str;
			while ((str = in.readLine()) != null) {
				sb.append(str);
			}
			in.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (in != null) {
				in.close();
			}
		}
		if (response.getStatusLine().getStatusCode() == 401) {
			throw new IllegalStateException("Status Code => 401 \n" + "URL => "
					+ requestIf.getUrl().toString() + "\n" + "ResponseBody => "
					+ sb.toString());
		}
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Status Code not 200 StatusCode => "
					+ response.getStatusLine().getStatusCode() + "\n URL => "
					+ requestIf.getUrl().toString() + "\n Body => "
					+ sb.toString());
		}
		return requestIf.getResponse(sb.toString());
	}

	private HttpResponse execPost() throws URISyntaxException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, IOException  {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost();
		httpPost.setURI(new URI(requestIf.getUrl()));
		httpPost.setHeader("Authorization", createAuthorizationValue());

		if (postParameter != null && !postParameter.isEmpty()) {
			final List<NameValuePair> params = new ArrayList<NameValuePair>();
			for (String key : postParameter.keySet()) {
				params.add(new BasicNameValuePair(key, postParameter.get(key)));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		}

		if (requestIf.getFiles() != null) {
			final MultipartEntity entity = new MultipartEntity(
					HttpMultipartMode.BROWSER_COMPATIBLE);
			List<NameValuePair> fileList = requestIf.getFiles();
			if (fileList != null && !fileList.isEmpty()) {
				for (NameValuePair val : fileList) {
					File file = new File(val.getValue());
					entity.addPart(val.getName(), new FileBody(file,
							CONTENTTYPE_BINARY));
				}
			}
			httpPost.setEntity(entity);
		}
		httpPost.getParams().setBooleanParameter(
				CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
		return client.execute(httpPost);
	}

	private HttpResponse execGet() throws URISyntaxException, UnsupportedEncodingException, InvalidKeyException, IOException, NoSuchAlgorithmException  {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet();
		if (postParameter != null && !postParameter.isEmpty()) {
			httpGet.setURI(new URI(requestIf.getUrl() + "?" + getRequestParam()));
		} else {
			httpGet.setURI(new URI(requestIf.getUrl()));
		}
		httpGet.setHeader("Authorization", createAuthorizationValue());
		return client.execute(httpGet);
	}

	private String createAuthorizationValue() throws InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException {
		StringBuilder builder = new StringBuilder();
		builder.append("OAuth ");
		for (Map.Entry<String, String> param : authParameter.entrySet()) {
			builder.append(param.getKey()).append("=");
			builder.append("\"").append(param.getValue()).append("\",");
		}
		builder.append("oauth_signature=");
		String sig = getSignature();
		builder.append("\"").append(sig).append("\"");
		return builder.toString();
	}

	private String getSignature() throws NoSuchAlgorithmException,
			InvalidKeyException, UnsupportedEncodingException {
		String keyString = String.format(SIGN_FORMAT,
				config.getConsumerSercret(), config.getAccessTokenSercret());
		String signatureBaseString = getSignatureBaseString();
		Mac mac = Mac.getInstance(this.config.getAlgolithm());
		Key key = new SecretKeySpec(keyString.getBytes(),
				this.config.getAlgolithm());
		mac.init(key);
		byte[] digest = mac.doFinal(signatureBaseString.getBytes());
		return encodeURL(Base64.encodeBytes(digest));
	}

	private String encodeURL(String value) {
		String encoded = null;
		try {
			encoded = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException ignore) {
		}
		StringBuilder buf = new StringBuilder(encoded.length());
		char focus;
		for (int i = 0; i < encoded.length(); i++) {
			focus = encoded.charAt(i);
			if (focus == '*') {
				buf.append("%2A");
			} else if (focus == '+') {
				buf.append("%20");
			} else if (focus == '%' && (i + 1) < encoded.length()
					&& encoded.charAt(i + 1) == '7'
					&& encoded.charAt(i + 2) == 'E') {
				buf.append('~');
				i += 2;
			} else {
				buf.append(focus);
			}
		}
		return buf.toString();
	}

	private String getSignatureBaseString() throws UnsupportedEncodingException {
		return requestIf.getMethod().toString() + "&"
				+ encodeURL(requestIf.getUrl()) + "&"
				+ SignatureEncode.encode(getRequestParameters());
	}

	private String getRequestParameters() throws UnsupportedEncodingException {
		SortedMap<String, String> map = authParameter;
		if (postParameter != null && postParameter.size() > 0) {
			for (Map.Entry<String, String> param : postParameter.entrySet()) {
				map.put(param.getKey(), encodeURL(param.getValue()));
			}
		}
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> param : map.entrySet()) {
			builder.append(param.getKey());
			builder.append("=");
			builder.append(param.getValue());
			builder.append("&");
		}
		String parameters = builder.toString();
		if (parameters != null && parameters.length() != 0) {
			parameters = builder.toString().substring(0, builder.length() - 1);
		}
		return parameters;
	}

	private String getRequestParam() throws UnsupportedEncodingException {
		StringBuilder builder = new StringBuilder();
		if (postParameter != null && postParameter.size() > 0) {
			for (Map.Entry<String, String> param : postParameter.entrySet()) {
				if (param.getValue().length() != 0) {
					builder.append(param.getKey());
					builder.append("=");
					builder.append(param.getValue());
					builder.append("&");
				}
			}
		}
		String parameters = builder.toString();
		if (parameters != null && parameters.length() != 0) {
			parameters = builder.toString().substring(0, builder.length() - 1);
		}
		return parameters;
	}
}
