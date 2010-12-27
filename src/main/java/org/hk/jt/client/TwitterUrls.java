package org.hk.jt.client;
/**
 *
 * @author hk
 */
public enum TwitterUrls {
	HOME_TIMELINE("https://api.twitter.com/1/statuses/home_timeline.json"),
	MENTIONS("https://api.twitter.com/1/statuses/mentions.json"),
	DIRECT_MESSAGE("https://api.twitter.com/1/direct_messages.json"),
	DIRECT_MESSAGE_SENT("https://api.twitter.com/1/direct_messages/sent.json"),
	USERS_SHOW("https://api.twitter.com/1/users/show.json"),
	USER_TIME_LINE("http://api.twitter.com/1/statuses/user_timeline.json"),
	VERIFY_CREDENTIALS("https://api.twitter.com/1/account/verify_credentials.json"),
	STATUSES_UPDATE("https://api.twitter.com/1/statuses/update.json"),
	STATUSES_DESTROY("https://api.twitter.com/1/statuses/destroy/%s.json"),
	DIRECT_MESSAGES_NEW("https://api.twitter.com/1/direct_messages/new.json"),
	DELETE_MESSAGE_DESTROY("https://api.twitter.com/1/direct_messages/destroy/%s.json"),
	FRIENDSSHIPS_EXISTS("https://api.twitter.com/1/friendships/exists.json"),
	FRIENDSSHIPS_CREATE("http://api.twitter.com/1/friendships/create.json");
	
	private final String url;
	
	private TwitterUrls(final String url){
		this.url = url;
	}

        public String getUrl(String...args){
            return String.format(this.url,(Object[]) args);
        }
	
	public String getUrl(){
		return this.url;
	}

        @Override
	public String toString(){
		return this.url;
	}
}
