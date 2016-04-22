package com.ed.v1.net.api;

import com.android.volley.Request;
import com.ed.v1.net.bean.BSHistoryContent;

/**
 * Created by Haijun.Wang on 15/9/8.
 */
public class BSHistoryRequest extends APICollectionRequest<BSHistoryContent> {

    public static final String API = "/measurements/bss/patientBss";

    public static final String PATIENT_ID = "patientId";
    public static final String DATE = "syncDate";

    public BSHistoryRequest(String patientId) {
        super(API, BSHistoryContent.class);
        this.addParameter(PATIENT_ID, patientId);
    }

    @Override
    public int getMethod() {
        return Request.Method.GET;
    }
}
