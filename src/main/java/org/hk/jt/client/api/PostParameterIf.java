package org.hk.jt.client.api;

import java.util.Map;

import org.hk.jt.client.HttpMethod;

public interface PostParameterIf {

	void setPostParameter(Map<String,String> defaultParameter);
	HttpMethod getMethod();
	String getUrl();
}
