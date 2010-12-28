package org.hk.jt.client;

import org.apache.http.NameValuePair;

/**
 * Simple Name And Value pair Object.
 * <pre>
 * JSONArray j = twitterClient
 *                      .from(HOME_TIMELINE)
 *                      .param(
 *                          <b>new ParameterPair(SINCE_ID, "100"),
 *                          new ParameterPair("max_id", "100")</b>
 *                       )
 *                       .getJsonArray();
 * </pre>
 * @see NameValuePair
 * @author hk
 */
public class ParameterPair  implements NameValuePair{

    /**
     * name
     */
    private final String name;
    /**
     * value
     */
    private final String value;

    /**
     * Create Object With String
     * @param name TwitterParameterName
     * @param value TwitterParameterValue
     */
    public ParameterPair(final String name, final String value) {
        super();
        if (name == null) {
            throw new IllegalArgumentException("Name may not be null");
        }
        this.name = name;
        this.value = value;
    }

    /**
     * Create Object With TwitterParams
     * @see TwitterParams
     * @param name TwitterParameterName
     * @param value TwitterParameterValue
     */
    public ParameterPair(final TwitterParams name , final String value){
        super();
        if(name == null){
            throw new IllegalArgumentException("Name may not be null");
        }
        this.name = name.toString();
        this.value = value;
    }

    /**
     * get TwitterParameterName
     * @return name TwitterParameterName
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * get TwitterParameterValue
     * @return name TwitterParameterValue
     */
    @Override
    public String getValue() {
        return this.value;
    }
}
