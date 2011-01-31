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

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import static org.hk.jt.client.TwitterUrls.*;
import static org.hk.jt.client.HttpMethod.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * 
 * @author hk
 */
public class TwitterClientTest {

	private final Properties properties = new Properties();
	private final String _CONSUMER_KEY;
	private final String _CONSUMER_SERCRET;
	private final String _USER_ID;
	private final String _PASSWORD;
	private final String _LIST_USER;
	private final String _LIST_ID;
	private final String _FAVORITE_ID;
	private TwitterClient twitterClient = null;

	public TwitterClientTest() throws IOException {
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("jt.properties");
		properties.load(inputStream);
		_CONSUMER_KEY = properties.getProperty("CONSUMER_KEY");
		_CONSUMER_SERCRET = properties.getProperty("CONSUMER_KEY_SERCRET");
		_USER_ID = properties.getProperty("TWITTER_ID");
		_PASSWORD = properties.getProperty("TWITTER_PASSWORD");
		_LIST_USER = properties.getProperty("LIST_USER");
		_LIST_ID = properties.getProperty("LIST_ID");
		_FAVORITE_ID = properties.getProperty("FAVORITE_ID");
	}

	@Test
	public void testAccessToken() throws Exception {
		twitterClient = TwitterClient.getInstance(_CONSUMER_KEY,
				_CONSUMER_SERCRET, _USER_ID, _PASSWORD);
		Map<String, String> map = twitterClient.getAccessToken();
		assertNotNull(map.get("oauth_token"));
		assertNotNull(map.get("oauth_token_secret"));
	}

	@Test(dependsOnMethods = { "testAccessToken" })
	public void testHomeTimeLine() throws Exception {
		JSONArray jsonArray = twitterClient.from(HOME_TIMELINE).method(GET)
				.getJsonArray();
		assertEquals(20, jsonArray.length());
	}

	@Test(dependsOnMethods = { "testAccessToken" })
	public void testMention() throws Exception {
		JSONArray jsonArray = twitterClient.from(MENTIONS).method(GET)
				.getJsonArray();
		assertEquals(6, jsonArray.length());
	}

	@Test(dependsOnMethods = { "testAccessToken" })
	public void testList() throws Exception {
		JSONObject jsonObject = twitterClient.from(USER_LIST, _LIST_USER)
				.method(GET).getJsonObject();
		JSONArray array = jsonObject.getJSONArray("lists");
		assertEquals(array.length(), 20);
	}

	@Test(dependsOnMethods = { "testAccessToken" })
	public void testSubscriptionList() throws Exception {
		JSONObject jsonObject = twitterClient
				.from(SUBSCRIPTION_LIST, _LIST_USER).method(GET)
				.getJsonObject();
		JSONArray array = jsonObject.getJSONArray("lists");
		assertEquals(array.length(), 0);
	}

	@Test(dependsOnMethods = { "testAccessToken" })
	public void testCreateFavorite() throws Exception {
		JSONObject jsonObject = twitterClient.from(FAVORITE_CREATE, _FAVORITE_ID)
				.method(POST).getJsonObject();
		System.out.println(jsonObject);
	}

	@Test(dependsOnMethods = { "testAccessToken", "testCreateFavorite" })
	public void testFavorites() throws Exception {
		JSONArray jsonArray = twitterClient.from(FAVORITES).method(GET)
				.getJsonArray();
		assertEquals(jsonArray.length(),2);
	}

	@Test(dependsOnMethods = { "testAccessToken", "testCreateFavorite",
			"testFavorites" })
	public void testDestroyFavorite() throws Exception {
		JSONObject jsonObject = twitterClient.from(FAVORITE_DESTROY, _FAVORITE_ID)
				.method(POST).getJsonObject();
		System.out.println(jsonObject);
	}

	@Test(dependsOnMethods = { "testAccessToken" })
	public void testStatusesList() throws Exception {
		JSONArray jsonArray = twitterClient
				.from(STATUSES_LIST, _LIST_USER, _LIST_ID).method(GET)
				.getJsonArray();
		assertEquals(jsonArray.length(), 20);
	}

	// @Test(dependsOnMethods = { "testAccessToken" })
	// public void testDirectMessage() throws Exception {
	//
	// }
}
