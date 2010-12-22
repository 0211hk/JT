/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hk.jt.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.json.JSONArray;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author hk
 */
public class TwitterClientTest {

    private final Properties properties = new Properties();
    private final String _CONSUMER_KEY;
    private final String _CONSUMER_SERCRET;
    private final String _USER_ID;
    private final String _PASSWORD;
    private TwitterClient twitterClient = null;

    public TwitterClientTest() throws IOException{
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(
                "jt.properties");
        properties.load(inputStream);
        _CONSUMER_KEY = properties.getProperty("CONSUMER_KEY");
        _CONSUMER_SERCRET = properties.getProperty("CONSUMER_KEY_SERCRET");
        _USER_ID = properties.getProperty("TWITTER_ID");
        _PASSWORD = properties.getProperty("TWITTER_PASSWORD");
    }

    @Test
    public void testAccessToken() throws Exception {
        twitterClient = TwitterClient.getInstance(_CONSUMER_KEY, _CONSUMER_SERCRET, _USER_ID, _PASSWORD);
        twitterClient.getAccessToken();
        assertNotNull(twitterClient.getConfig().getAccessToken());
        assertNotNull(twitterClient.getConfig().getAccessTokenSercret());
    }

    @Test(dependsOnMethods={"testAccessToken"})
    public void testHomeTimeLine()throws Exception{
        JSONArray jsonArray = twitterClient.getHomeTimeline();
        assertEquals(20, jsonArray.length());
    }

    @Test(dependsOnMethods={"testAccessToken"})
    public void testHomeTimeLineAsync(){
        twitterClient.getHomeTimelineAsync(new TwitterCallbackIf<JSONArray>() {
            @Override
            public void twitterCallback(JSONArray returnMap) {
               assertEquals(20, returnMap.length());
            }
        });
    }
}
