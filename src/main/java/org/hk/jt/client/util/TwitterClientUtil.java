package org.hk.jt.client.util;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import org.hk.jt.client.Config;

public final class TwitterClientUtil {

	private TwitterClientUtil(){}
	
	public static SortedMap<String, String> getXAuthParameter(Config config){
		SortedMap<String, String> map = new TreeMap<String, String>();
		map.put("oauth_consumer_key", config.getConsumerKey());
		map.put("oauth_nonce", UUID.randomUUID().toString());
		map.put("oauth_signature_method", config.getSignatureMethod());
		map.put("oauth_timestamp", Long.toString(System.currentTimeMillis() / 1000));
		map.put("oauth_version", config.getOauthVersion());
		map.put("oauth_token", config.getAccessToken());
		return map;
	}
	
	public static SortedMap<String, String> getXAuthAccessParameter(Config config){
		SortedMap<String, String> map = new TreeMap<String, String>();
		map.put("oauth_consumer_key", config.getConsumerKey());
		map.put("oauth_nonce", UUID.randomUUID().toString());
		map.put("oauth_signature_method", config.getSignatureMethod());
		map.put("oauth_timestamp", Long.toString(System.currentTimeMillis() / 1000));
		map.put("oauth_version", config.getOauthVersion());
		return map;
	}
	
	public static boolean isEmpty(String str){
		if(str == null || str.equals("")){
			return true;
		}
		return false;
	}
	
	public static boolean isInteger(String str){
		try{
			Integer.parseInt(str);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	public static boolean isLong(String str){
		try{
			Long.parseLong(str);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
