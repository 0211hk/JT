/**
 * Copyright 2010-2011 the HK Software and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
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
