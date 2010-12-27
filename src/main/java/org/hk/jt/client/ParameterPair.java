/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.hk.jt.client;

import org.apache.http.NameValuePair;

/**
 *
 * @author hk
 */
public class ParameterPair  implements NameValuePair{

    private final String name;
    private final String value;

    /**.
     *
     * @param name The name.
     * @param value The value.
     */
    public ParameterPair(final String name, final String value) {
        super();
        if (name == null) {
            throw new IllegalArgumentException("Name may not be null");
        }
        this.name = name;
        this.value = value;
    }

    public ParameterPair(final TwitterParams name , final String value){
        super();
        if(name == null){
            throw new IllegalArgumentException("Name may not be null");
        }
        this.name = name.toString();
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }
}
