package com.ed.v1.net;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public interface JSONDeserializable extends Serializable {

    void deserialize(JSONObject o) throws JSONException;

}
