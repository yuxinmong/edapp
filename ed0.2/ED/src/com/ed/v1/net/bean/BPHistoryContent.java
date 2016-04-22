package com.ed.v1.net.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Haijun.Wang on 15/8/29.
 */
public class BPHistoryContent extends Content{

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private List<HistoryItem> patientBpList;

    public List<HistoryItem> getPatientBpList() {
        return patientBpList;
    }

    public static class HistoryItem extends Content {
        @JsonProperty
        private String id;
        @JsonProperty
        private String patientId;
        @JsonProperty
        private String acquisitionTime;
        @JsonProperty
        private int type;
        @JsonProperty
        private int systolic;
        @JsonProperty
        private int diastolic;
        @JsonProperty
        private int heartRate;
        @JsonProperty
        private double riskScore;
        @JsonProperty
        private int status;
        @JsonProperty
        private int level;

        public String getId() {
            return id;
        }

        public String getPatientId() {
            return patientId;
        }

        public String getAcquisitionTime() {
            return acquisitionTime;
        }

        public int getType() {
            return type;
        }

        public int getSystolic() {
            return systolic;
        }

        public int getDiastolic() {
            return diastolic;
        }

        public int getHeartRate() {
            return heartRate;
        }

        public double getRiskScore() {
            return riskScore;
        }

        public int getStatus() {
            return status;
        }

        public int getLevel() {
            return level;
        }
    }
}
