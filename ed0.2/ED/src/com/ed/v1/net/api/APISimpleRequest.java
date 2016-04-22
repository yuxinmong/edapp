package com.ed.v1.net.api;

import com.ed.v1.net.JSONDeserializable;

import org.json.JSONException;
import org.json.JSONObject;

public class APISimpleRequest extends APIRequest<JSONDeserializable> {

    public APISimpleRequest(String url) {
        super(url, JSONDeserializable.class);
    }

    @Override
    public APIResponse<JSONDeserializable> createResponse() {
        return new APIResponse<JSONDeserializable>(this) {
            @Override
            protected JSONDeserializable buildContent(JSONObject content) throws JSONException {
                return null;
            }
        };
    }
}
