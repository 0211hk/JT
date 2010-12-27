package org.hk.jt.client.api;

import org.hk.jt.client.Config;


public class RequestTwitterString extends AbstractRequest<String>{

	public RequestTwitterString(final Config config,final PostParameterIf postParameterIf){
		super(config,postParameterIf);
	}
	
	@Override
	public String getResponse(String response){
		System.out.println(super.getUrl());
		System.out.println(response);
		return response;
	}
}
