/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hk.jt.client;

/**
 *
 * @author hk
 */
public enum HttpMethod {

    GET("GET"), POST("POST"), DELETE("DELETE"), PUT("PUT");
    private String method;

    private HttpMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return this.method;
    }
}
