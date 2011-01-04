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

/**
 * enumeration TwitterURL<br />
 * recommend static import.
 * <pre>
 * import static org.hk.jt.client.TwitterUrls.*;
 * JSONObject j = twitterClient
 *                      .from(<b>STATUSES_UPDATE</b>)
 *                      .method(POST)
 *                      .param(TEXT,"test tweet")
 *                      .getJsonObject();
 * </pre>
 * @author hk
 */
public enum TwitterUrls {

    /**
     * Get HomeTimeline
     * @see http://dev.twitter.com/doc/get/statuses/home_timeline
     */
    HOME_TIMELINE("https://api.twitter.com/1/statuses/home_timeline.json"),
    /**
     * Get Mentions
     * @see http://dev.twitter.com/doc/get/statuses/mentions
     */
    MENTIONS("https://api.twitter.com/1/statuses/mentions.json"),
    /**
     * Get DirectMessage
     * @see http://dev.twitter.com/doc/get/direct_messages
     */
    DIRECT_MESSAGE("https://api.twitter.com/1/direct_messages.json"),
    /**
     * Get SentDirectMessage
     * @see http://dev.twitter.com/doc/get/direct_messages/sent
     */
    DIRECT_MESSAGE_SENT("https://api.twitter.com/1/direct_messages/sent.json"),
    /**
     * Get User
     * @see http://dev.twitter.com/doc/get/users/show
     */
    USERS_SHOW("https://api.twitter.com/1/users/show.json"),
    /**
     * Get UserTimeline
     * @see http://dev.twitter.com/doc/get/statuses/user_timeline
     */
    USER_TIME_LINE("http://api.twitter.com/1/statuses/user_timeline.json"),
    /**
     * Get VerifyCredintial
     * @see http://dev.twitter.com/doc/get/account/verify_credentials
     */
    VERIFY_CREDENTIALS("https://api.twitter.com/1/account/verify_credentials.json"),
    /**
     * Post UpdateStatus
     * @see http://dev.twitter.com/doc/post/statuses/update
     */
    STATUSES_UPDATE("https://api.twitter.com/1/statuses/update.json"),
    /**
     * Post StatusDestroy
     * @see http://dev.twitter.com/doc/post/statuses/destroy/:id
     */
    STATUSES_DESTROY("https://api.twitter.com/1/statuses/destroy/%s.json"),
    /**
     * Post DirectMessage
     * @see http://dev.twitter.com/doc/post/direct_messages/new
     */
    DIRECT_MESSAGES_NEW("https://api.twitter.com/1/direct_messages/new.json"),
    /**
     * Post DestroyMessage
     * @see http://dev.twitter.com/doc/post/direct_messages/destroy/:id
     */
    DELETE_MESSAGE_DESTROY("https://api.twitter.com/1/direct_messages/destroy/%s.json"),
    /**
     * Get ExistFriendship
     * @see http://dev.twitter.com/doc/get/friendships/exists
     */
    FRIENDSSHIPS_EXISTS("https://api.twitter.com/1/friendships/exists.json"),
    /**
     * Post CreateFriendship
     * @see http://dev.twitter.com/doc/post/friendships/create
     */
    FRIENDSSHIPS_CREATE("http://api.twitter.com/1/friendships/create.json");
    private final String url;

    private TwitterUrls(final String url) {
        this.url = url;
    }

    /**
     * GetUrl
     * @param args format parameter
     * @return url
     */
    public String getUrl(String... args) {
        return String.format(this.url, (Object[]) args);
    }

    /**
     * GetUrl
     * @return url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * GetUrl
     * @return url
     */
    @Override
    public String toString() {
        return this.url;
    }
}
