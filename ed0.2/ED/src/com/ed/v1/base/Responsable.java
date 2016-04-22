package com.ed.v1.base;

import com.ed.v1.net.JSONDeserializable;
import com.ed.v1.net.api.APIResponse;

/**
 * Created by Haijun.Wang on 15/6/24.
 */
public interface Responsable {
    /**
     * 网络请求出错时被调用，需要特殊处理时可覆盖此方法
     *
     * @param response
     * @return 返回true表示
     */
    public boolean requestFailed(APIResponse<? extends JSONDeserializable> response);
}
