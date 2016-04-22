package com.ed.v1.net.api;

import com.android.volley.Request;
import com.ed.v1.net.bean.BPHistoryContent;

/**
 * Created by Haijun.Wang on 15/8/29.
 */
public class BPHistoryRequest extends APICollectionRequest<BPHistoryContent> {

    public static final String API = "/measurements/bps/patientBps";

    public static final String PATIENT_ID = "patientId";
    public static final String DATE = "syncDate";

    public BPHistoryRequest(String patientId) {
        super(API, BPHistoryContent.class);
        this.addParameter(PATIENT_ID, patientId);
    }

    @Override
    public int getMethod() {
        return Request.Method.GET;
    }

}