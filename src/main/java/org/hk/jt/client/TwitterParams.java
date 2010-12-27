/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hk.jt.client;

/**
 *
 * @author hk
 */
public enum TwitterParams {

    SINCE_ID("since_id"), MAX_ID("max_id"), COUNT("count"), SCREEN_NAM(
    "screen_name"), INCLUDE_RTS("include_rts"), USER_A("user_a"), USER_B(
    "user_b"), FOLLOW("follow"), USER("user"), TEXT("text"), STATUS("status");
    private String params;

    private TwitterParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return this.params;
    }
}
