package com.ed.v1.net.api;

/**
 * Created by Haijun.Wang on 15/8/29.
 */
public class BPDataUploadRequest extends APISimpleRequest {
    public static final String API = "/measurements/bps/bluetoothBps";

    public static final String PATIENT_ID = "patientId";
    public static final String EMPLOYEE_ID = "employeeId";
    public static final String STORE_ID = "branchStoreId";
    public static final String TIME = "acquisitionTime";
    public static final String SYSTOLIC = "systolic";
    public static final String DIASTOLOC = "diastolic";
    public static final String HEART_RATE = "heartRate";
    public static final String IMEI = "imei";

    public BPDataUploadRequest(String patientId, String employeeId, String branchStoreId, String acquisitionTime
            , String systolic, String diastolic, String heartRate, String imei) {
        super(API);
        this.addParameter(PATIENT_ID, patientId);
        this.addParameter(EMPLOYEE_ID, employeeId);
        this.addParameter(STORE_ID, branchStoreId);
        this.addParameter(TIME, acquisitionTime);
        this.addParameter(SYSTOLIC, systolic);
        this.addParameter(DIASTOLOC, diastolic);
        this.addParameter(HEART_RATE, heartRate);
        this.addParameter(IMEI, imei);
    }
}
