package org.hk.jt.client;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONObject;

import org.hk.jt.client.api.AccessToken;
import org.hk.jt.client.api.PostParameterIf;
import org.hk.jt.client.api.RequestTwitterBoolean;
import org.hk.jt.client.api.RequestTwitterJsonArray;
import org.hk.jt.client.api.TwitterUrls;
import org.hk.jt.client.api.RequestTwitterJsonObject;
import org.hk.jt.client.core.Request;
import org.hk.jt.client.core.RequestIf.Method;
import org.hk.jt.client.util.TwitterClientUtil;

public final class TwitterClient {

	private static TwitterClient instance = new TwitterClient();
	private Config config;
	private final ExecutorService es = Executors.newCachedThreadPool();

	private TwitterClient() {
	}

	/***
	 * create instance with twitter user name & password
	 * 
	 * @param consumerKey
	 *            - twitter xauth consumer key
	 * @param consumerSercret
	 *            - twitter xauth consumer sercret
	 * @param userName
	 *            - twitter username
	 * @param password
	 *            - twitter password
	 * @return this
	 */
	public static TwitterClient getInstance(final String consumerKey,
			final String consumerSercret, final String userName,
			final String password) {
		instance.config = Config.getInstance(consumerKey, consumerSercret,
				userName, password);
		return instance;
	}

	/**
	 * create instance with twitter accesstoken & accesstoken sercret
	 * 
	 * @param consumerKey
	 *            - twitter xauth consumer key
	 * @param consumerSercret
	 *            - twitter xauth consumer sercret
	 * @param accessToken
	 *            - accesstoken
	 * @param accessTokenSercret
	 *            - accesstoken sercret
	 * @return
	 */
	public static TwitterClient getInstanceWithAccessToken(
			final String consumerKey, final String consumerSercret,
			final String accessToken, final String accessTokenSercret) {
		instance.config = Config.getInstanceWithAccessToken(consumerKey,
				consumerSercret, accessToken, accessTokenSercret);
		return instance;
	}

	/**
	 * get accesstoken
	 * 
	 * @throws Exception
	 */
	public void getAccessToken() throws Exception {
		Map<String, String> map = execRequest(new Request<Map<String, String>>(
				new AccessToken(instance.config)));
		instance.config.setAccessToken(map.get("oauth_token"));
		instance.config.setAccessTokenSercret(map.get("oauth_token_secret"));
		instance.config.setScreenName(map.get("screen_name"));
		if (map.containsKey("user_id")) {
			String userId = map.get("user_id");
			if (TwitterClientUtil.isLong(userId)) {
				instance.config.setUserId(Long.parseLong(map.get("user_id")));
			}
		}
	}

	/**
	 * get home timeline default 20 count
	 * 
	 * @return JSONArray
	 * @throws Exception
	 */
	public JSONArray getHomeTimeline() throws Exception {
		return this.getHomeTimeline(null);
	}

	public JSONArray getHomeTimeline(Map<String, String> paramMap)
			throws Exception {
		return execRequest(new Request<JSONArray>(new RequestTwitterJsonArray(
				instance.config, new PostParameter(Method.GET,
						TwitterUrls.GET_TIME_LINE.getUrl(), paramMap))));
	}

	/**
	 * get home timeline async default 20 count
	 * 
	 * @param twitterCallbackIf
	 *            - async callback
	 */
	public void getHomeTimelineAsync(
			final TwitterCallbackIf<JSONArray> twitterCallbackIf) {
		this.getHomeTimelineAsync(twitterCallbackIf, null);
	}

	/**
	 * getHomeTimeline async default 20 count with parameter
	 * 
	 * @param twitterCallbackIf
	 * @param paramMap
	 */
	public void getHomeTimelineAsync(
			final TwitterCallbackIf<JSONArray> twitterCallbackIf,
			Map<String, String> paramMap) {
		es.submit(new ExecRequestCallable<JSONArray>(new Request<JSONArray>(
				new RequestTwitterJsonArray(instance.config, new PostParameter(
						Method.GET, TwitterUrls.GET_TIME_LINE.getUrl(),
						paramMap))), twitterCallbackIf));
		es.shutdown();
	}

	/**
	 * get mention default 20 count
	 * 
	 * @return JSONArray
	 * @throws Exception
	 */
	public JSONArray getMention() throws Exception {
		return getMention(null);
	}

	/**
	 * get mention default 20 count async
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public JSONArray getMention(Map<String, String> paramMap) throws Exception {
		return execRequest(new Request<JSONArray>(new RequestTwitterJsonArray(
				instance.config, new PostParameter(Method.GET,
						TwitterUrls.GET_MENTION.getUrl(), paramMap))));
	}

	/**
	 * get mention async default 20 count
	 * 
	 * @param twitterCallbackIf
	 *            - async callback
	 */
	public void getMentionAsync(
			final TwitterCallbackIf<JSONArray> twitterCallbackIf) {
		getMentionAsync(twitterCallbackIf, null);
	}

	/**
	 * get mention async default 20 count with parameter
	 * 
	 * @param twitterCallbackIf
	 *            - async callback
	 */
	public void getMentionAsync(
			final TwitterCallbackIf<JSONArray> twitterCallbackIf,
			Map<String, String> paramMap) {
		es.submit(new ExecRequestCallable<JSONArray>(
				new Request<JSONArray>(new RequestTwitterJsonArray(
						instance.config, new PostParameter(Method.GET,
								TwitterUrls.GET_MENTION.getUrl(), paramMap))),
				twitterCallbackIf));
		es.shutdown();
	}

	/**
	 * get home timeline default 20 count
	 * 
	 * @return JSONArray
	 * @throws Exception
	 */
	public JSONArray getUserTimeline() throws Exception {
		return this.getUserTimeline(null);
	}

	public JSONArray getUserTimeline(Map<String, String> paramMap)
			throws Exception {
		return execRequest(new Request<JSONArray>(new RequestTwitterJsonArray(
				instance.config, new PostParameter(Method.GET,
						TwitterUrls.GET_USER_TIME_LINE.getUrl(), paramMap))));
	}

	/**
	 * get home timeline async default 20 count
	 * 
	 * @param twitterCallbackIf
	 *            - async callback
	 */
	public void getUserTimelineAsync(
			final TwitterCallbackIf<JSONArray> twitterCallbackIf) {
		this.getHomeTimelineAsync(twitterCallbackIf, null);
	}

	/**
	 * getHomeTimeline async default 20 count with parameter
	 * 
	 * @param twitterCallbackIf
	 * @param paramMap
	 */
	public void getUserTimelineAsync(
			final TwitterCallbackIf<JSONArray> twitterCallbackIf,
			Map<String, String> paramMap) {
		es.submit(new ExecRequestCallable<JSONArray>(new Request<JSONArray>(
				new RequestTwitterJsonArray(instance.config, new PostParameter(
						Method.GET, TwitterUrls.GET_USER_TIME_LINE.getUrl(),
						paramMap))), twitterCallbackIf));
		es.shutdown();
	}

	/**
	 * get user data
	 * 
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getUserShow() throws Exception {
		return this.getUserShow(null);
	}

	/**
	 * get user data with parameter
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public JSONObject getUserShow(Map<String, String> paramMap)
			throws Exception {
		if (paramMap == null) {
			paramMap = new HashMap<String, String>();
		}
		paramMap.put("screen_name", instance.config.getScreenName());
		return execRequest(new Request<JSONObject>(
				new RequestTwitterJsonObject(instance.config,
						new PostParameter(Method.GET,
								TwitterUrls.GET_SHOW_USER.getUrl(), paramMap))));
	}

	/**
	 * get user data async
	 * 
	 * @param twitterCallbackIf
	 *            - asyc callback
	 */
	public void getUserShowAsync(
			final TwitterCallbackIf<JSONObject> twitterCallbackIf) {
		getUserShowAsync(twitterCallbackIf, null);
	}

	/**
	 * get user data async with parameter
	 * 
	 * @param twitterCallbackIf
	 * @param paramMap
	 */
	public void getUserShowAsync(
			final TwitterCallbackIf<JSONObject> twitterCallbackIf,
			Map<String, String> paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap<String, String>();
		}
		paramMap.put("screen_name", instance.config.getScreenName());
		es.submit(new ExecRequestCallable<JSONObject>(new Request<JSONObject>(
				new RequestTwitterJsonObject(instance.config,
						new PostParameter(Method.GET, TwitterUrls.GET_SHOW_USER
								.getUrl(), paramMap))), twitterCallbackIf));
		es.shutdown();
	}

	/**
	 * Recertification
	 * 
	 * @return JSONObject
	 * @throws Exception
	 */
	public JSONObject getVerifyCredential() throws Exception {
		return getVerifyCredential(null);
	}

	/**
	 * Recertification with parameter
	 * 
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public JSONObject getVerifyCredential(Map<String, String> paramMap)
			throws Exception {
		JSONObject object = execRequest(new Request<JSONObject>(
				new RequestTwitterJsonObject(instance.config,
						new PostParameter(Method.GET,
								TwitterUrls.GET_VERIFY_CREDENTIALS.getUrl(),
								paramMap))));
		instance.config.setScreenName(object.getString("screen_name"));
		instance.config.setTwitterUserName(object.getString("name"));
		instance.config.setUserId(object.getLong("id"));
		return object;
	}

	/**
	 * get direct message default 20 count
	 * 
	 * @return JSONArray
	 * @throws Exception
	 */
	public JSONArray getDirectMessage() throws Exception {
		return getDirectMessage(null);
	}

	public JSONArray getDirectMessage(Map<String, String> paramMap)
			throws Exception {
		return execRequest(new Request<JSONArray>(new RequestTwitterJsonArray(
				instance.config, new PostParameter(Method.GET,
						TwitterUrls.GET_DIRECT_MESSAGE.getUrl(), paramMap))));
	}

	/**
	 * get direct message async default 20 count
	 * 
	 * @param twitterCallbackIf
	 *            - async callback
	 */
	public void getDirectMessageAsync(
			final TwitterCallbackIf<JSONArray> twitterCallbackIf) {
		getDirectMessageAsync(twitterCallbackIf, null);
	}

	public void getDirectMessageAsync(
			final TwitterCallbackIf<JSONArray> twitterCallbackIf,
			Map<String, String> paramMap) {
		es.submit(new ExecRequestCallable<JSONArray>(new Request<JSONArray>(
				new RequestTwitterJsonArray(instance.config, new PostParameter(
						Method.GET, TwitterUrls.GET_DIRECT_MESSAGE.getUrl(),
						paramMap))), twitterCallbackIf));
		es.shutdown();
	}

	/**
	 * get sent message default 20 count
	 * 
	 * @return JSONArray
	 * @throws Exception
	 */
	public JSONArray getSentMessage() throws Exception {
		return getSentMessage(null);
	}

	public JSONArray getSentMessage(Map<String, String> paramMap)
			throws Exception {
		return execRequest(new Request<JSONArray>(new RequestTwitterJsonArray(
				instance.config, new PostParameter(Method.GET,
						TwitterUrls.GET_SENT_MESSAGE.getUrl(), paramMap))));
	}

	/**
	 * get sent message async default 20 count
	 * 
	 * @param twitterCallbackIf
	 *            - async callback
	 */
	public void getSentMessageAsync(
			final TwitterCallbackIf<JSONArray> twitterCallbackIf) {
		getSentMessageAsync(twitterCallbackIf, null);
	}

	public void getSentMessageAsync(
			final TwitterCallbackIf<JSONArray> twitterCallbackIf,
			Map<String, String> paramMap) {
		es.submit(new ExecRequestCallable<JSONArray>(new Request<JSONArray>(
				new RequestTwitterJsonArray(instance.config, new PostParameter(
						Method.GET, TwitterUrls.GET_SENT_MESSAGE.getUrl(),
						paramMap))), twitterCallbackIf));
		es.shutdown();
	}

	public JSONObject postTweet(final Map<String, String> paramMap)
			throws Exception {
		return execRequest(new Request<JSONObject>(
				new RequestTwitterJsonObject(instance.config,
						new PostParameter(Method.POST,
								TwitterUrls.POST_TWEET.getUrl(), paramMap))));
	}

	public void postTweetAsync(
			final TwitterCallbackIf<JSONObject> twitterCallbackIf,
			final Map<String, String> paramMap) {
		es.submit(new ExecRequestCallable<JSONObject>(new Request<JSONObject>(
				new RequestTwitterJsonObject(instance.config,
						new PostParameter(Method.POST, TwitterUrls.POST_MESSAGE
								.getUrl(), paramMap))), twitterCallbackIf));
		es.shutdown();
	}

	public JSONObject destroyTweet(final long tweetId) throws Exception {
		return destroyTweet(tweetId, null);
	}

	public JSONObject destroyTweet(final long tweetId,
			final Map<String, String> paramMap) throws Exception {
		String url = String.format(TwitterUrls.DELETE_TWEET.getUrl(),
				String.valueOf(tweetId));
		return execRequest(new Request<JSONObject>(
				new RequestTwitterJsonObject(instance.config,
						new PostParameter(Method.POST, url, paramMap))));
	}

	public void destroyTweetAsync(final long tweetId,
			final TwitterCallbackIf<JSONObject> twitterCallbackIf) {
		this.destroyTweetAsync(tweetId, twitterCallbackIf, null);
	}

	public void destroyTweetAsync(final long tweetId,
			final TwitterCallbackIf<JSONObject> twitterCallbackIf,
			final Map<String, String> paramMap) {
		es.submit(new ExecRequestCallable<JSONObject>(new Request<JSONObject>(
				new RequestTwitterJsonObject(instance.config,
						new PostParameter(Method.POST, TwitterUrls.DELETE_TWEET
								.getUrl(), paramMap))), twitterCallbackIf));
		es.shutdown();
	}

	public JSONObject postMessage(final Map<String, String> paramMap)
			throws Exception {
		return execRequest(new Request<JSONObject>(
				new RequestTwitterJsonObject(instance.config,
						new PostParameter(Method.POST,
								TwitterUrls.POST_MESSAGE.getUrl(), paramMap))));
	}

	public void postMessageAsync(
			final TwitterCallbackIf<JSONObject> twitterCallbackIf,
			final Map<String, String> paramMap) {
		es.submit(new ExecRequestCallable<JSONObject>(new Request<JSONObject>(
				new RequestTwitterJsonObject(instance.config,
						new PostParameter(Method.POST, TwitterUrls.POST_MESSAGE
								.getUrl(), paramMap))), twitterCallbackIf));
		es.shutdown();
	}

	public JSONObject destroyMessage(final long messageId) throws Exception {
		return this.destroyMessage(messageId, null);
	}

	public JSONObject destroyMessage(final long messageId,
			Map<String, String> paramMap) throws Exception {
		String url = String.format(TwitterUrls.DELETE_MESSAGE.getUrl(),
				String.valueOf(messageId));
		return execRequest(new Request<JSONObject>(
				new RequestTwitterJsonObject(instance.config,
						new PostParameter(Method.POST, url, paramMap))));
	}

	public void destroyMessageAsync(final long messageId,
			final TwitterCallbackIf<JSONObject> twitterCallbackIf) {
		destroyMessageAsync(messageId, twitterCallbackIf, null);
	}

	public void destroyMessageAsync(final long messageId,
			final TwitterCallbackIf<JSONObject> twitterCallbackIf,
			Map<String, String> paramMap) {
		es.submit(new ExecRequestCallable<JSONObject>(
				new Request<JSONObject>(new RequestTwitterJsonObject(
						instance.config, new PostParameter(Method.POST,
								TwitterUrls.DELETE_MESSAGE.getUrl(), paramMap))),
				twitterCallbackIf));
		es.shutdown();
	}

	public Boolean isFollowing() throws Exception {
		return this.isFollowing(null);
	}

	public Boolean isFollowing(final Map<String, String> paramMap)
			throws Exception {
		return execRequest(new Request<Boolean>(new RequestTwitterBoolean(
				instance.config, new PostParameter(Method.GET,
						TwitterUrls.IS_FOLLOW.getUrl(), paramMap))));
	}

	public void isFollowingAsync(
			final TwitterCallbackIf<Boolean> twitterCallbackIf) {
		isFollowingAsync(twitterCallbackIf, null);
	}

	public void isFollowingAsync(
			final TwitterCallbackIf<Boolean> twitterCallbackIf,
			final Map<String, String> paramMap) {
		es.submit(new ExecRequestCallable<Boolean>(
				new Request<Boolean>(new RequestTwitterBoolean(instance.config,
						new PostParameter(Method.GET, TwitterUrls.IS_FOLLOW
								.getUrl(), paramMap))), twitterCallbackIf));
		es.shutdown();
	}

	public JSONObject follow() throws Exception {
		return follow(null);
	}

	public JSONObject follow(final Map<String, String> paramMap)
			throws Exception {
		return execRequest(new Request<JSONObject>(
				new RequestTwitterJsonObject(instance.config,
						new PostParameter(Method.POST,
								TwitterUrls.REQUEST_FOLLOW.getUrl(), paramMap))));
	}

	public void followAsync(
			final TwitterCallbackIf<JSONObject> twitterCallbackIf) {
		followAsync(twitterCallbackIf, null);
	}

	public void followAsync(
			final TwitterCallbackIf<JSONObject> twitterCallbackIf,
			final Map<String, String> paramMap) {
		es.submit(new ExecRequestCallable<JSONObject>(
				new Request<JSONObject>(new RequestTwitterJsonObject(
						instance.config, new PostParameter(Method.POST,
								TwitterUrls.REQUEST_FOLLOW.getUrl(), paramMap))),
				twitterCallbackIf));
		es.shutdown();
	}

	public Config getConfig() {
		return instance.config;
	}

	private <E> E execRequest(Request<E> req) throws Exception {
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
		private final Method method;
		private final String urls;
		private final Map<String, String> paramMap;

		public PostParameter(final Method method, final String urls,
				final Map<String, String> paramMap) {
			this.method = method;
			this.urls = urls;
			this.paramMap = paramMap;
		}

		@Override
		public void setPostParameter(Map<String, String> defaultParameter) {
			if (paramMap != null && paramMap.size() != 0) {
				for (String key : paramMap.keySet()) {
					defaultParameter.put(key, paramMap.get(key));
				}
				paramMap.clear();
			}
		}

		@Override
		public Method getMethod() {
			return method;
		}

		@Override
		public String getUrl() {
			return urls;
		}
	}
}