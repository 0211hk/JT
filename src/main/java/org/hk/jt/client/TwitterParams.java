package org.hk.jt.client;

/**
 * enumeration Twitter Parameter Key<br />
 * recommend static import.
 * <pre>
 * import static org.hk.jt.client.HttpMethod.*;
 * JSONObject j = twitterClient
 *                      .from(STATUSES_UPDATE)
 *                      .method(POST)
 *                      .param(<b>TEXT</b>,"test tweet")
 *                      .getJsonObject();
 * </pre>
 * @author hk
 */
public enum TwitterParams {

    /**
     * since_id
     */
    SINCE_ID("since_id"),
    /**
     * max_id
     */
    MAX_ID("max_id"),
    /**
     * count
     */
    COUNT("count"),
    /**
     * screen name
     */
    SCREEN_NAME("screen_name"), 
    /**
     * include rts
     */
    INCLUDE_RTS("include_rts"),
    /**
     * user a
     */
    USER_A("user_a"),
    /**
     * user b
     */
    USER_B("user_b"),
    /**
     * follow
     */
    FOLLOW("follow"),
    /**
     * user
     */
    USER("user"),
    /**
     * text
     */
    TEXT("text"),
    /**
     * status
     */
    STATUS("status");
    private String params;

    private TwitterParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return this.params;
    }
}
