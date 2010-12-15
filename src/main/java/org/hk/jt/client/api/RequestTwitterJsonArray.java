package org.hk.jt.client.api;

import org.hk.jt.client.Config;

import org.json.JSONArray;
import org.json.JSONException;

public class RequestTwitterJsonArray extends AbstractRequest<JSONArray>{

	public RequestTwitterJsonArray(final Config config,final PostParameterIf postParameterIf){
		super(config,postParameterIf);
	}
	
	@Override
	public JSONArray getResponse(String response)
			throws JSONException {
		System.out.println(super.getUrl());
		System.out.println(response);
		return new JSONArray(response);
	}
}
