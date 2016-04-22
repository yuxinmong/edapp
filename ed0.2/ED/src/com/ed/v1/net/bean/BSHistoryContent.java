package com.ed.v1.net.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Haijun.Wang on 15/9/8.
 */
public class BSHistoryContent extends Content{

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private List<HistoryItem> patientBsList;

    public List<HistoryItem> getPatientBsList() {
        return patientBsList;
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
        private double bloodsugar;
        @JsonProperty
        private int stage;
        @JsonProperty
        private double hsRiskscore;
        @JsonProperty
        private double ndRiskscore;
        @JsonProperty
        private double pdRiskscore;
        @JsonProperty
        private double daRiskscore;
        @JsonProperty
        private int status;

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

        public double getBloodsugar() {
            return bloodsugar;
        }

        public int getStage() {
            return stage;
        }

        public double getHsRiskscore() {
            return hsRiskscore;
        }

        public double getNdRiskscore() {
            return ndRiskscore;
        }

        public double getPdRiskscore() {
            return pdRiskscore;
        }

        public double getDaRiskscore() {
            return daRiskscore;
        }

        public int getStatus() {
            return status;
        }
    }
}
