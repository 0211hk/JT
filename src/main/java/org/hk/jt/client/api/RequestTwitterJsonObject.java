package org.hk.jt.client.api;

import org.hk.jt.client.Config;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestTwitterJsonObject extends AbstractRequest<JSONObject> {

	public RequestTwitterJsonObject(final Config config,final PostParameterIf postParameterIf){
		super(config,postParameterIf);
	}

	@Override
	public JSONObject getResponse(String response) throws JSONException {
		System.out.println(super.getUrl());
		System.out.println(response);
		return new JSONObject(response);
	}
}
