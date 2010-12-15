package org.hk.jt.client.api;

public enum TwitterUrls {
	GET_TIME_LINE("https://api.twitter.com/1/statuses/home_timeline.json"),
	GET_MENTION("https://api.twitter.com/1/statuses/mentions.json"),
	GET_DIRECT_MESSAGE("https://api.twitter.com/1/direct_messages.json"),
	GET_SENT_MESSAGE("https://api.twitter.com/1/direct_messages/sent.json"),
	GET_SHOW_USER("https://api.twitter.com/1/users/show.json"),
	GET_USER_TIME_LINE("http://api.twitter.com/1/statuses/user_timeline.json"),
	GET_VERIFY_CREDENTIALS("https://api.twitter.com/1/account/verify_credentials.json"),
	POST_TWEET("https://api.twitter.com/1/statuses/update.json"),
	DELETE_TWEET("https://api.twitter.com/1/statuses/destroy/%s.json"),
	POST_MESSAGE("https://api.twitter.com/1/direct_messages/new.json"),
	DELETE_MESSAGE("https://api.twitter.com/1/direct_messages/destroy/%s.json"),
	IS_FOLLOW("https://api.twitter.com/1/friendships/exists.json"),
	REQUEST_FOLLOW("http://api.twitter.com/1/friendships/create.json");
	
	private final String url;
	
	private TwitterUrls(final String url){
		this.url = url;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public String toString(){
		return this.url;
	}
}
