package org.hk.jt.client.api;

import org.hk.jt.client.Config;

import org.json.JSONException;

public class RequestTwitterBoolean extends AbstractRequest<Boolean> {

	public RequestTwitterBoolean(final Config config,final PostParameterIf postParameterIf){
		super(config,postParameterIf);
	}

	@Override
	public Boolean getResponse(String response) throws JSONException {
		System.out.println(super.getUrl());
		System.out.println(response);
		return Boolean.valueOf(response);
	}

}
