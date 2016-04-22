package com.ed.v1.net.api;

/**
 * Created by Haijun.Wang on 15/9/13.
 */
public class BpInputResuqest extends APISimpleRequest{

    public static final String API = "/measurements/bps/handInputBps";

    public static final String PATIENT_ID = "patientId";
    public static final String EMPLOYEE_ID = "employeeId";
    public static final String STORE_ID = "branchStoreId";
    public static final String TIME = "acquisitionTime";
    public static final String SYSTOLIC = "systolic";
    public static final String DIASTOLOC = "diastolic";
    public static final String HEART_RATE = "heartRate";

    public BpInputResuqest(String patientId, String employeeId, String branchStoreId, String acquisitionTime
            , String systolic, String diastolic, String heartRate) {
        super(API);
        this.addParameter(PATIENT_ID, patientId);
        this.addParameter(EMPLOYEE_ID, employeeId);
        this.addParameter(STORE_ID, branchStoreId);
        this.addParameter(TIME, acquisitionTime);
        this.addParameter(SYSTOLIC, systolic);
        this.addParameter(DIASTOLOC, diastolic);
        this.addParameter(HEART_RATE, heartRate);
    }

}
