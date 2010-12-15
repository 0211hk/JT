package org.hk.jt.client;

public final class Config {

	private static Config instance = new Config();

	private Config() {
	}

	private final String signatureMethod = "HMAC-SHA1";
	private final String oauthVersion = "1.0";
	private final String algolithm = "HmacSHA1";
	private String consumerKey = "";
	private String consumerSercret = "";
	private String userName = "";
	private String password = "";
	private String accessToken = "";
	private String accessTokenSercret = "";
	private String screenName = "";
	private String twitterUserName = "";
	private long userId = -1;

	public static Config getInstance(final String consumerKey,
			final String consumerSercret) {
		return getInstance(consumerKey, consumerSercret, null, null);
	}

	public static Config getInstance(final String consumerKey,
			final String consumerSercret, final String userName,
			final String password) {
		instance.consumerKey = consumerKey;
		instance.consumerSercret = consumerSercret;
		instance.userName = userName;
		instance.password = password;
		return instance;
	}
	
	public static Config getInstanceWithAccessToken(final String consumerKey,
			final String consumerSercret, final String accessToken,
			final String accessTokenSercret){
		instance.consumerKey = consumerKey;
		instance.consumerSercret = consumerSercret;
		instance.accessToken = accessToken;
		instance.accessTokenSercret = accessTokenSercret;
		return instance;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getScreenName() {
		return screenName;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.setLength(0);
		str.append(String.format("signatureMethod = %s \n", getSignatureMethod()));
		str.append(String.format("oauthVersionc = %s \n", getOauthVersion()));
		str.append(String.format("algolithm = %s \n", getAlgolithm()));
		str.append(String.format("consumerKey = %s \n", getConsumerKey()));
		str.append(String.format("consumerSercret = %s \n", getConsumerSercret()));
		str.append(String.format("userName = %s \n", getUserName()));
		str.append(String.format("password = %s \n", getPassword()));
		str.append(String.format("accessToken = %s \n", getAccessToken()));
		str.append(String.format("accessTokenSercret = %s ", getAccessTokenSercret()));
		str.append(String.format("screenName = %s ", getScreenName()));
		return str.toString();
	}

	public void setTwitterUserName(String twitterUserName) {
		this.twitterUserName = twitterUserName;
	}

	public String getTwitterUserName() {
		return twitterUserName;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}
}