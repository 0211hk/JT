package org.hk.jt.client;

/**
 * enumeration HttpMethod<br />
 * support GET, POST, DELETE, PUT<br />
 * recommend static import.
 * <pre>
 * import static org.hk.jt.client.HttpMethod.*;
 * JSONObject j = twitterClient
 *                      .from(STATUSES_UPDATE)
 *                      .method(<b>POST</b>)
 *                      .param(TEXT,"test tweet")
 *                      .getJsonObject();
 * </pre>
 * @author hk
 */
public enum HttpMethod {

    /**
     * Http Method GET
     */
    GET("GET"), 
    /**
     * Http Method POST
     */
    POST("POST"),
    /**
     * Http Method DELETE
     */
    DELETE("DELETE"),
    /**
     * Http Method PUT
     */
    PUT("PUT");
    private String method;

    private HttpMethod(String method) {
        this.method = method;
    }

    /**
     * return "GET" or "POST" or "DELETE" or "PUT"
     * @return http method
     */
    @Override
    public String toString() {
        return this.method;
    }
}
