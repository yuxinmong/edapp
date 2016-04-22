package com.ed.v1.net.api;

/**
 * Created by Haijun.Wang on 15/9/4.
 */
public class BSDataUploadRequest extends APISimpleRequest{

    public static final String API = "/measurements/bss/bluetoothBss";

    public static final String PATIENT_ID = "patientId";
    public static final String EMPLOYEE_ID = "employeeId";
    public static final String STORE_ID = "branchStoreId";
    public static final String TIME = "acquisitionTime";
    public static final String SUGER = "bloodsugar";
    public static final String STATE = "stage";
    public static final String IMEI = "imei";

    public BSDataUploadRequest(String patientId, String employeeId, String branchStoreId, String acquisitionTime
            , String bloodsugar, String stage, String imei) {
        super(API);
        this.addParameter(PATIENT_ID, patientId);
        this.addParameter(EMPLOYEE_ID, employeeId);
        this.addParameter(STORE_ID, branchStoreId);
        this.addParameter(TIME, acquisitionTime);
        this.addParameter(SUGER, bloodsugar);
        this.addParameter(STATE, stage);
        this.addParameter(IMEI, imei);
    }
}
