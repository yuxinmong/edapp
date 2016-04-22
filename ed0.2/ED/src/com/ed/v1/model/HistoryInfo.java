package com.ed.v1.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "t_hostory")
public class HistoryInfo {

	@DatabaseField(generatedId = true)
	private int id;
	
	@DatabaseField(canBeNull=false)
    public String userName;

    @DatabaseField(defaultValue = "false")
    private boolean booldsugar;

    @DatabaseField(defaultValue = "")
    private int systolic;

    @DatabaseField(defaultValue = "")
    private int diastolic;

    @DatabaseField(defaultValue = "")
    private int status;
    
    @DatabaseField(defaultValue = "")
    private double riskScore;
    
    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getRiskScore() {
		return riskScore;
	}

	public void setRiskScore(double riskScore) {
		this.riskScore = riskScore;
	}

	public double getLevel() {
        return sugerLevel;
    }

    public void setLevel(double d) {
        this.sugerLevel = d;
    }

    @DatabaseField(defaultValue = "")
    private int pulse;

    @DatabaseField(defaultValue = "")
    private double sugerLevel;
    //高血压中风风险指数
    @DatabaseField(defaultValue = "")
    private int hsRiskscore;
    //正常人患糖尿病风险指数
    @DatabaseField(defaultValue = "")
    private int ndRiskscore;
    //糖尿病前期风险指数
    @DatabaseField(defaultValue = "")
    private int pdRiskscore;
    //糖尿病心血管疾病风险指数
    @DatabaseField(defaultValue = "")
    private int daRiskscore;
    
    public double getSugerLevel() {
		return sugerLevel;
	}

	public void setSugerLevel(double sugerLevel) {
		this.sugerLevel = sugerLevel;
	}

	public int getHsRiskscore() {
		return hsRiskscore;
	}

	public void setHsRiskscore(int hsRiskscore) {
		this.hsRiskscore = hsRiskscore;
	}

	public int getNdRiskscore() {
		return ndRiskscore;
	}

	public void setNdRiskscore(int ndRiskscore) {
		this.ndRiskscore = ndRiskscore;
	}

	public int getPdRiskscore() {
		return pdRiskscore;
	}

	public void setPdRiskscore(int pdRiskscore) {
		this.pdRiskscore = pdRiskscore;
	}

	public int getDaRiskscore() {
		return daRiskscore;
	}

	public void setDaRiskscore(int daRiskscore) {
		this.daRiskscore = daRiskscore;
	}

	public int getSugerTime() {
        return sugerTime;
    }

    public void setSugerTime(int sugerTime) {
        this.sugerTime = sugerTime;
    }

    @DatabaseField(defaultValue = "")
    private int sugerTime;

    @DatabaseField(canBeNull = false)
    private long measurementTime;

    public HistoryInfo(String userName, int systolic, int diastolic, int pulse, long date) {
    	this.userName = userName;
        this.booldsugar = false;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.pulse = pulse;
        this.measurementTime = date;
    }

    public HistoryInfo(String userName, int sugerTime, double sugerLevel, long date) {
        this.userName = userName;
        this.sugerTime = sugerTime;
        this.sugerLevel = sugerLevel;
        this.measurementTime = date;
        this.booldsugar = true;
    }

    public HistoryInfo() {

    }

    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(long measurementTime) {
        this.measurementTime = measurementTime;
    }

    public boolean getBooldsugar() {
        return booldsugar;
    }

    public void setBooldsugar(boolean booldsugar) {
        this.booldsugar = booldsugar;
    }

    public int getSystolic() {
        return systolic;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    public int getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }
}
