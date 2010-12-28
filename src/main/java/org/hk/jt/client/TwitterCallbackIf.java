package org.hk.jt.client;

/**
 * Twitter Callback interface.
 * <pre>
 * twitterClient.from(DIRECT_MESSAGE).getAsyncJsonArray(new TwitterCallbackIf&lt;JSONArray&gt;() {
 *          &#064;Override
 *          public void twitterCallback(JSONArray returnMap) {
 *              //TODO:
 *          }
 *      });
 * </pre>
 * @author administrator
 * @param <T>
 */
public interface TwitterCallbackIf<T> {

    /**
     * Async Callback
     * return value support JSONArray or JSONObject or String
     * @param returnMap JSONArray or JSONObject or String
     */
    public void twitterCallback(T returnMap);
}
