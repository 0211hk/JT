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
import org.hk.jt.client.api.RequestTwitterJsonArray;
import static org.hk.jt.client.api.TwitterUrls.*;
import org.hk.jt.client.api.RequestTwitterJsonObject;
import org.hk.jt.client.api.RequestTwitterString;
import org.hk.jt.client.api.TwitterUrls;
import org.hk.jt.client.core.Request;
import org.hk.jt.client.core.RequestIf.Method;
import static org.hk.jt.client.core.RequestIf.Method.*;
import org.hk.jt.client.core.RequestIf.Params;
import org.hk.jt.client.util.TwitterClientUtil;

/**
 * Access to Twitter
 * 
 * <pre>
 * // create TwitterClient instance
 * TwitterClient twitterClient = TwitterClient.getInstance(_CONSUMER_KEY,
 * 		_CONSUMER_SERCRET, _USER_ID, _PASSWORD);
 * // first you get AccessToken
 * twitterClient.getAccessToken();
 * // now you can get ScreenName and TwitterUserId
 * String screenName = twitterClient.getConfig().getScreenName();
 * long twitterUserId = twitterClient.getConfig().getTwitterUserId();
 * // get home timeline
 * JSONArray jsonArray = twitterClient.from(HOME_TIMELINE.toString())
 * 		.getJsonArray();
 * // expect 20
 * System.out.println(jsonArray.length());
 * 
 * jsonArray = twitterClient.from(HOME_TIMELINE.toString()).param(&quot;page&quot;, &quot;100&quot;)
 * 		.getJsonArray();
 * // expect 100
 * System.out.println(jsonArray.length());
 * </pre>
 * 
 * @author hk
 */
public final class TwitterClient {

    private static TwitterClient instance = new TwitterClient();
    private Config config;
    private final ExecutorService es = Executors.newCachedThreadPool();
    private String url = HOME_TIMELINE.toString();
    private final Map<String, String> paramMap = new HashMap<String, String>();
    private Method method = GET;

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
                instance.config.setTwitterUserId(Long.parseLong(map.get("user_id")));
            }
        }
    }

    /**
     * set Twitter API URL
     * 
     * @see TwitterUrls
     * @param url
     * @return
     */
    public TwitterClient from(final String url) {
        instance.url = url;
        return instance;
    }

    public TwitterClient from(final TwitterUrls url) {
        instance.url = url.toString();
        return instance;
    }

    /**
     * set extra parameter
     *
     * @param key
     * @param value
     * @return
     */
    public TwitterClient param(final String key, final String value) {
        if (value != null && !value.equals("-1") && !value.equals("")) {
            instance.paramMap.put(key, value);
        }
        return instance;
    }

    public TwitterClient param(final Params param,final String value){
        if (value != null && !value.equals("-1") && !value.equals("")) {
            instance.paramMap.put(param.toString(), value);
        }
        return instance;
    }

    /**
     * set http method GET or POST or PUT or DELETE
     *
     * @param method
     * @return
     */
    public TwitterClient method(final Method method) {
        instance.method = method;
        return instance;
    }

    public TwitterClient params(final Map<String, String> paramMap) {
        instance.paramMap.putAll(paramMap);
        return instance;
    }

    public TwitterClient paramsSet(final Map<Params,String> paramMap){
        for (Params p : paramMap.keySet()){
            instance.paramMap.put(p.toString(), paramMap.get(p));
        }
        return instance;
    }

    public TwitterClient clearParam() {
        instance.paramMap.clear();
        return instance;
    }

    /**
     * get Twitter API return value as JsonArray
     *
     * @return
     * @throws Exception
     */
    public JSONArray getJsonArray() throws Exception {
        JSONArray jsonArray = execRequest(new Request<JSONArray>(
                new RequestTwitterJsonArray(instance.config, new PostParameter(
                this.method, this.url, this.paramMap))));
        this.url = HOME_TIMELINE.toString();
        this.method = GET;
        this.paramMap.clear();
        return jsonArray;
    }

    /**
     * get Twitter API return value as JsonObject
     *
     * @return
     * @throws Exception
     */
    public JSONObject getJsonObject() throws Exception {
        JSONObject jsonObject = execRequest(new Request<JSONObject>(
                new RequestTwitterJsonObject(instance.config,
                new PostParameter(this.method, this.url, this.paramMap))));
        this.url = HOME_TIMELINE.toString();
        this.method = GET;
        this.paramMap.clear();
        return jsonObject;
    }

    /**
     * get Twitter API return value as String
     *
     * @return
     * @throws Exception
     */
    public String get() throws Exception {
        String response = execRequest(new Request<String>(
                new RequestTwitterString(instance.config, new PostParameter(
                this.method, this.url, this.paramMap))));
        this.url = HOME_TIMELINE.toString();
        this.method = GET;
        this.paramMap.clear();
        return response;
    }

    /**
     * get async Twitter API return value as JsonArray
     *
     * @param twitterCallbackIf
     * @throws Exception
     */
    public void getAsyncJsonArray(final TwitterCallbackIf<JSONArray> twitterCallbackIf)
            throws Exception {
        es.submit(new ExecRequestCallable<JSONArray>(new Request<JSONArray>(
                new RequestTwitterJsonArray(instance.config, new PostParameter(
                this.method, this.url, paramMap))), twitterCallbackIf));
        this.url = HOME_TIMELINE.toString();
        this.method = GET;
        this.paramMap.clear();
        es.shutdown();
    }

    /**
     * get async Twitter API return value as JsonObject
     *
     * @param twitterCallbackIf
     * @throws Exception
     */
    public void getAsyncJsonObject(final TwitterCallbackIf<JSONObject> twitterCallbackIf)
            throws Exception {
        es.submit(new ExecRequestCallable<JSONObject>(new Request<JSONObject>(
                new RequestTwitterJsonObject(instance.config,
                new PostParameter(this.method, this.url, paramMap))),
                twitterCallbackIf));
        this.url = HOME_TIMELINE.toString();
        this.method = GET;
        this.paramMap.clear();
        es.shutdown();
    }

    /**
     * get Async Twitter API return value as String
     *
     * @param twitterCallbackIf
     * @throws Exception
     */
    public void getAsync(final TwitterCallbackIf<String> twitterCallbackIf)
            throws Exception {
        es.submit(new ExecRequestCallable<String>(new Request<String>(
                new RequestTwitterString(instance.config, new PostParameter(
                this.method, this.url, paramMap))), twitterCallbackIf));
        this.url = HOME_TIMELINE.toString();
        this.method = GET;
        this.paramMap.clear();
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
            if (paramMap != null && !paramMap.isEmpty()) {
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
