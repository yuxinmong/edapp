package com.ed.v1.net.api;

import com.ed.v1.datacenter.DataCenter;
import com.ed.v1.util.DateUtil;

/**
 * Created by Haijun.Wang on 15/9/12.
 */
public class ProfileUploadRequest extends APISimpleRequest {

    public static final String API = "/profiles/healthProfiles";

    public static final String PATIENT_ID = "patientId";
    public static final String EMPLOYEE_ID = "employeeUserId";
    public static final String STORE_ID = "branchStoreId";
    public static final String TIME = "updateTime";
    public static final String WEIGHT = "weight";



    public ProfileUploadRequest() {
        super(API);
        this.addParameter(PATIENT_ID, DataCenter.getInstance()
                .getPatientInfo().getPatientId());
        this.addParameter(EMPLOYEE_ID, DataCenter.getInstance()
                .getUserInfo().getUserId());
        this.addParameter(STORE_ID, DataCenter.getInstance()
                .getUserInfo().getBranchStoreId());
        this.addParameter(TIME, DateUtil.getUploadTimeByNow());
    }

    public void addWeight(String weight){
        this.addParameter(WEIGHT, weight);
    }
}
