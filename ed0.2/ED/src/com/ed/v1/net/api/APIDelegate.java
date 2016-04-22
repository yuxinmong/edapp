package com.ed.v1.net.api;

import com.ed.v1.net.JSONDeserializable;

public interface APIDelegate<T extends JSONDeserializable> {

    public void onResponse(APIResponse<T> response);

}
