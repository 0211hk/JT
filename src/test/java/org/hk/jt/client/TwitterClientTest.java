/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hk.jt.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author hiromari
 */
public class TwitterClientTest {

    private Properties properties = new Properties();

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(
                "jt.properties");
        properties.load(inputStream);
    }

    @Test
    public void testAccessToken() {
        System.out.println(properties.getProperty("CONSUMER_KEY"));
    }
}
