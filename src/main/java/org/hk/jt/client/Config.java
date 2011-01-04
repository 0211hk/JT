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

public final class Config {

	private Config(){}
	
	private final String signatureMethod = "HMAC-SHA1";
	private final String oauthVersion = "1.0";
	private final String algolithm = "HmacSHA1";
	private String consumerKey = "";
	private String consumerSercret = "";
	private String userId = "";
	private String password = "";
	private String accessToken = "";
	private String accessTokenSercret = "";

	static Config getInstance(final String consumerKey,
			final String consumerSercret,final String userId,final String password) {
		Config c = new Config();
		c.consumerKey = consumerKey;
		c.consumerSercret = consumerSercret;
		c.userId = userId;
		c.password = password;
		return c;
	}

	static Config getInstanceWithAccessToken(final String consumerKey,
			final String consumerSercret, final String accessToken,
			final String accessTokenSercret) {
		Config c = new Config();
		c.consumerKey = consumerKey;
		c.consumerSercret = consumerSercret;
		c.accessToken = accessToken;
		c.accessTokenSercret = accessTokenSercret;
		return c;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSercret() {
		return consumerSercret;
	}

	public void setConsumerSercret(String consumerSercret) {
		this.consumerSercret = consumerSercret;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSignatureMethod() {
		return signatureMethod;
	}

	public String getOauthVersion() {
		return oauthVersion;
	}

	public String getAlgolithm() {
		return algolithm;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessTokenSercret(String accessTokenSercret) {
		this.accessTokenSercret = accessTokenSercret;
	}

	public String getAccessTokenSercret() {
		return accessTokenSercret;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.setLength(0);
		str.append(String.format("signatureMethod = %s \n",
				getSignatureMethod()));
		str.append(String.format("oauthVersionc = %s \n", getOauthVersion()));
		str.append(String.format("algolithm = %s \n", getAlgolithm()));
		str.append(String.format("consumerKey = %s \n", getConsumerKey()));
		str.append(String.format("consumerSercret = %s \n",
				getConsumerSercret()));
		str.append(String.format("userName = %s \n", getUserId()));
		str.append(String.format("password = %s \n", getPassword()));
		str.append(String.format("accessToken = %s \n", getAccessToken()));
		str.append(String.format("accessTokenSercret = %s ",
				getAccessTokenSercret()));
		return str.toString();
	}
}