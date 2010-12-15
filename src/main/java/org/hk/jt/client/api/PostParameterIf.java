package org.hk.jt.client.api;

import java.util.Map;

import org.hk.jt.client.core.RequestIf.Method;

public interface PostParameterIf {

	void setPostParameter(Map<String,String> defaultParameter);
	Method getMethod();
	String getUrl();
}
