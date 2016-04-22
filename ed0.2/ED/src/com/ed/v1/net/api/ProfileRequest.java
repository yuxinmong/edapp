package com.ed.v1.net.api;

import com.android.volley.Request;
import com.ed.v1.model.CyberKangPersonalInfo;

/**
 * Created by Haijun.Wang on 15/9/12.
 */
public class ProfileRequest extends APICollectionRequest<CyberKangPersonalInfo> {

    public static final String API = "/profiles/healthProfiles";

    public static final String PATIENT_ID = "patientId";

    public ProfileRequest(String patientId) {
        super(API, CyberKangPersonalInfo.class);
        this.addParameter(PATIENT_ID, patientId);
    }

    @Override
    public int getMethod() {
        return Request.Method.GET;
    }
}

