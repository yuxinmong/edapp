package com.ed.v1.model;

import java.util.Date;

import com.ed.v1.net.bean.Content;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CyberKangPersonalInfo extends Content {

	public CyberKangPersonalInfo() {
	}

	private String userId;
	private String name;
	private String gender;

	@JsonProperty
	private Date birthday;
	@JsonProperty
	private String patientName;
	@JsonProperty
	private int sex;
	@JsonProperty
	private double height;
	@JsonProperty
	private double weight;
	@JsonProperty
	private double valueTc;// 胆固醇
	@JsonProperty
	private double valueHbalc;// 糖化血红蛋白
	@JsonProperty
	private double valueHdl;// 高密度脂蛋白，单位：mmol/L
	@JsonProperty
	private double valueTg;// 甘油三酯，单位：mmol/L
	@JsonProperty
	private double valueFbg;// 空腹血糖，单位：mmol/L

	// 病史
	@JsonProperty
	private int isSmoke;// 是否吸烟

	// 服药史
	@JsonProperty
	private int isTakingAhds; // 是否服用抗高血压药
	@JsonProperty
	private int isTakingApds;// 是否服用抗凝药
	@JsonProperty
	private int isTakingLlds;// 是否服用降低脂质药物
	@JsonProperty
	private int isTakingAads;// 是否服用抗心律失常药物
	@JsonProperty
	private int isTakingBabds;// 是否服用β肾上腺素受体阻断剂
	@JsonProperty
	private int isTakingAdds;// 是否服用抗糖尿病药
	@JsonProperty
	private int isTakingDds;// 是否服用利尿剂
	@JsonProperty
	private int isTakingNsaids;// 是否服用非甾体抗炎药
	@JsonProperty
	private int isTakingPids;// 是否服用正性肌力药物

	@JsonProperty
	private String msCardName;// 会员卡名
	@JsonProperty
	private String msCardId;// 会员卡号
	@JsonProperty
	private String relativePhone;// 亲属电话
	@JsonProperty
	private int isDrink;// 是否饮酒
	@JsonProperty
	private int isHavingAbl;// 是否高血脂
	@JsonProperty
	private int neverHaveAbl;// 无高血脂
	@JsonProperty
	private int isTakingDrug;// 是否服药 0-未填写 1-近期不服药 2-正在服药
	@JsonProperty
	private int neverHaveDiseases; // 无用药史 0-否 1-是
	@JsonProperty
	private int neverTakeDrugs; // 无疾病史 0-否 1-是
	@JsonProperty
	private int neverDrinkSmoke;// 无饮酒吸烟，0-否；1-是
	@JsonProperty
	private int labourLevel;// 劳动强度：1-卧床休息2-轻体力3-中体力4-重体力
	@JsonProperty
	private int isTakingAgds;// 是否服用抗心绞痛药
	@JsonProperty
	private int isTakingAtds;// 是否服用抗血栓药
	@JsonProperty
	private int neverHaveDm;// 没有糖尿病或糖尿病家族史
	@JsonProperty
	private int isHavingDmFhod;// 是否有糖尿病家族史
	@JsonProperty
	private int isHavingDm1;// 是否有1型糖尿病
	@JsonProperty
	private int isHavingDm2;// 是否有2型糖尿病
	@JsonProperty
	private int isHavingDmG;// 是否妊娠期糖尿病
	@JsonProperty
	private int isHavingDmS;// 是否特殊类型糖尿病
	@JsonProperty
	private int neverHaveGdc;// 没有糖尿病并发症
	@JsonProperty
	private int isHavingGdcDn;// 是否有糖尿病肾病
	@JsonProperty
	private int isHavingGdcDr;// 是否有糖尿病性视网膜病变/糖尿病性白内障
	@JsonProperty
	private int isHavingGdcDf;// 是否有糖尿病足
	@JsonProperty
	private int isHavingGdcDci;// 是否有糖尿病合并感染:呼吸道/泌尿道/皮肤/口腔
	@JsonProperty
	private int isHavingGdcPsd;// 糖尿病并发神经末梢感觉障碍
	@JsonProperty
	private int neverHaveHtn;// 没有高血压或高血压家族史
	@JsonProperty
	private int isHavingHtnFfoh;// 是否有高血压家族史
	@JsonProperty
	private int isHavingHtnP;// 是否有原发性高血压
	@JsonProperty
	private int isHavingHtnS;// 是否有继发性高血压
	@JsonProperty
	private int neverHaveHtnc;// 没有高血压并发症
	@JsonProperty
	private int isHavingHtncHe;// 是否有高血压脑病
	@JsonProperty
	private int isHavingHtncAaad;// 是否有高血压引起动脉硬化、主动脉夹层
	@JsonProperty
	private int isHavingHtncHrd;// 是否有高血压性肾损害
	@JsonProperty
	private int isHavingHtncRaafc;// 是否有高血压引起视网膜动脉硬化、眼底改变
	@JsonProperty
	private int neverHaveCd;// 没有心脑血管疾病
	@JsonProperty
	private int isHavingCdTia;// 是否有心绞痛/短暂性缺血性发作
	@JsonProperty
	private int isHavingCdHf;// 是否有心力衰竭
	@JsonProperty
	private int isHavingCdMi;// 是否有心肌梗塞
	@JsonProperty
	private int isHavingCdOhd;// 是否有其他心脏病
	@JsonProperty
	private int isHavingCdFhoa;// 是否有心血管疾病家族史
	@JsonProperty
	private int isHavingCdHs;// 是否有出血性脑卒中
	@JsonProperty
	private int isHavingCdCah;// 是否有缺血性脑卒中
	@JsonProperty
	private int isHavingCdFhos;// 是否有脑卒中家族史
	@JsonProperty
	private int neverHaveCtd;// 没有禁忌症
	@JsonProperty
	private int isHavingCtdAlf;// 是否肝功能异常
	@JsonProperty
	private int isHavingCtdRdf;// 是否肾功能异常
	@JsonProperty
	private int isHavingCtdCdf;// 是否心功能异常
	@JsonProperty
	private int isHavingCtdApf;// 是否肺功能异常
	@JsonProperty
	private int isHavingCtdE;// 是否水肿
	@JsonProperty
	private int isHavingCtdGr;// 是否胃肠道反应
	@JsonProperty
	private int neverHaveMah;// 没有用药过敏史
	@JsonProperty
	private int isHavingMahPcs;// 是否有青霉素类过敏史
	@JsonProperty
	private int isHavingMahCps;// 是否有头孢类过敏史
	@JsonProperty
	private int isHavingMahSfs;// 是否有磺胺类过敏史
	@JsonProperty
	private int isHavingMahAs;// 是否有阿司匹林过敏史
	@JsonProperty
	private int isHavingMahAls;// 是否有别嘌呤过敏史
	@JsonProperty
	private int neverHaveDevice;// 没有家用设备
	@JsonProperty
	private int isHavingBpDevice;// 是否有家用血压设备
	@JsonProperty
	private int isHavingBsDevice;// 是否有家用血糖设备

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public int getNeverHaveAbl() {
		return neverHaveAbl;
	}

	public void setNeverHaveAbl(int neverHaveAbl) {
		this.neverHaveAbl = neverHaveAbl;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getValueTc() {
		return valueTc;
	}

	public void setValueTc(double valueTc) {
		this.valueTc = valueTc;
	}

	public double getValueHbalc() {
		return valueHbalc;
	}

	public void setValueHbalc(double valueHbalc) {
		this.valueHbalc = valueHbalc;
	}

	public int getIsSmoke() {
		return isSmoke;
	}

	public void setIsSmoke(int isSmoke) {
		this.isSmoke = isSmoke;
	}

	public int getIsTakingAhds() {
		return isTakingAhds;
	}

	public void setIsTakingAhds(int isTakingAhds) {
		this.isTakingAhds = isTakingAhds;
	}

	public int getIsTakingApds() {
		return isTakingApds;
	}

	public void setIsTakingApds(int isTakingApds) {
		this.isTakingApds = isTakingApds;
	}

	public int getIsTakingAgds() {
		return isTakingAgds;
	}

	public void setIsTakingAgds(int isTakingAgds) {
		this.isTakingAgds = isTakingAgds;
	}

	public int getIsTakingLlds() {
		return isTakingLlds;
	}

	public void setIsTakingLlds(int isTakingLlds) {
		this.isTakingLlds = isTakingLlds;
	}

	public int getIsTakingAads() {
		return isTakingAads;
	}

	public void setIsTakingAads(int isTakingAads) {
		this.isTakingAads = isTakingAads;
	}

	public int getIsTakingBabds() {
		return isTakingBabds;
	}

	public void setIsTakingBabds(int isTakingBabds) {
		this.isTakingBabds = isTakingBabds;
	}

	public int getIsTakingAdds() {
		return isTakingAdds;
	}

	public void setIsTakingAdds(int isTakingAdds) {
		this.isTakingAdds = isTakingAdds;
	}

	public int getIsTakingDds() {
		return isTakingDds;
	}

	public void setIsTakingDds(int isTakingDds) {
		this.isTakingDds = isTakingDds;
	}

	public int getIsTakingNsaids() {
		return isTakingNsaids;
	}

	public void setIsTakingNsaids(int isTakingNsaids) {
		this.isTakingNsaids = isTakingNsaids;
	}

	public int getIsTakingPids() {
		return isTakingPids;
	}

	public void setIsTakingPids(int isTakingPids) {
		this.isTakingPids = isTakingPids;
	}

	public String getMsCardName() {
		return msCardName;
	}

	public void setMsCardName(String msCardName) {
		this.msCardName = msCardName;
	}

	public String getMsCardId() {
		return msCardId;
	}

	public void setMsCardId(String msCardId) {
		this.msCardId = msCardId;
	}

	public String getRelativePhone() {
		return relativePhone;
	}

	public void setRelativePhone(String relativePhone) {
		this.relativePhone = relativePhone;
	}

	public int getIsDrink() {
		return isDrink;
	}

	public void setIsDrink(int isDrink) {
		this.isDrink = isDrink;
	}

	public int getIsHavingAbl() {
		return isHavingAbl;
	}

	public void setIsHavingAbl(int isHavingAbl) {
		this.isHavingAbl = isHavingAbl;
	}

	public int getIsTakingDrug() {
		return isTakingDrug;
	}

	public void setIsTakingDrug(int isTakingDrug) {
		this.isTakingDrug = isTakingDrug;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getIsTakingAtds() {
		return isTakingAtds;
	}

	public void setIsTakingAtds(int isTakingAtds) {
		this.isTakingAtds = isTakingAtds;
	}

	public int getNeverHaveDm() {
		return neverHaveDm;
	}

	public void setNeverHaveDm(int neverHaveDm) {
		this.neverHaveDm = neverHaveDm;
	}

	public int getIsHavingDmFhod() {
		return isHavingDmFhod;
	}

	public void setIsHavingDmFhod(int isHavingDmFhod) {
		this.isHavingDmFhod = isHavingDmFhod;
	}

	public int getIsHavingDm1() {
		return isHavingDm1;
	}

	public void setIsHavingDm1(int isHavingDm1) {
		this.isHavingDm1 = isHavingDm1;
	}

	public int getIsHavingDm2() {
		return isHavingDm2;
	}

	public void setIsHavingDm2(int isHavingDm2) {
		this.isHavingDm2 = isHavingDm2;
	}

	public int getIsHavingDmG() {
		return isHavingDmG;
	}

	public void setIsHavingDmG(int isHavingDmG) {
		this.isHavingDmG = isHavingDmG;
	}

	public int getIsHavingDmS() {
		return isHavingDmS;
	}

	public void setIsHavingDmS(int isHavingDmS) {
		this.isHavingDmS = isHavingDmS;
	}

	public int getNeverHaveGdc() {
		return neverHaveGdc;
	}

	public void setNeverHaveGdc(int neverHaveGdc) {
		this.neverHaveGdc = neverHaveGdc;
	}

	public int getIsHavingGdcDn() {
		return isHavingGdcDn;
	}

	public void setIsHavingGdcDn(int isHavingGdcDn) {
		this.isHavingGdcDn = isHavingGdcDn;
	}

	public int getIsHavingGdcDr() {
		return isHavingGdcDr;
	}

	public void setIsHavingGdcDr(int isHavingGdcDr) {
		this.isHavingGdcDr = isHavingGdcDr;
	}

	public int getIsHavingGdcDf() {
		return isHavingGdcDf;
	}

	public void setIsHavingGdcDf(int isHavingGdcDf) {
		this.isHavingGdcDf = isHavingGdcDf;
	}

	public int getIsHavingGdcDci() {
		return isHavingGdcDci;
	}

	public void setIsHavingGdcDci(int isHavingGdcDci) {
		this.isHavingGdcDci = isHavingGdcDci;
	}

	public int getIsHavingGdcPsd() {
		return isHavingGdcPsd;
	}

	public void setIsHavingGdcPsd(int isHavingGdcPsd) {
		this.isHavingGdcPsd = isHavingGdcPsd;
	}

	public int getNeverHaveHtn() {
		return neverHaveHtn;
	}

	public void setNeverHaveHtn(int neverHaveHtn) {
		this.neverHaveHtn = neverHaveHtn;
	}

	public int getIsHavingHtnFfoh() {
		return isHavingHtnFfoh;
	}

	public void setIsHavingHtnFfoh(int isHavingHtnFfoh) {
		this.isHavingHtnFfoh = isHavingHtnFfoh;
	}

	public int getIsHavingHtnP() {
		return isHavingHtnP;
	}

	public void setIsHavingHtnP(int isHavingHtnP) {
		this.isHavingHtnP = isHavingHtnP;
	}

	public int getIsHavingHtnS() {
		return isHavingHtnS;
	}

	public void setIsHavingHtnS(int isHavingHtnS) {
		this.isHavingHtnS = isHavingHtnS;
	}

	public int getNeverHaveHtnc() {
		return neverHaveHtnc;
	}

	public void setNeverHaveHtnc(int neverHaveHtnc) {
		this.neverHaveHtnc = neverHaveHtnc;
	}

	public int getIsHavingHtncHe() {
		return isHavingHtncHe;
	}

	public void setIsHavingHtncHe(int isHavingHtncHe) {
		this.isHavingHtncHe = isHavingHtncHe;
	}

	public int getIsHavingHtncAaad() {
		return isHavingHtncAaad;
	}

	public void setIsHavingHtncAaad(int isHavingHtncAaad) {
		this.isHavingHtncAaad = isHavingHtncAaad;
	}

	public int getIsHavingHtncHrd() {
		return isHavingHtncHrd;
	}

	public void setIsHavingHtncHrd(int isHavingHtncHrd) {
		this.isHavingHtncHrd = isHavingHtncHrd;
	}

	public int getIsHavingHtncRaafc() {
		return isHavingHtncRaafc;
	}

	public void setIsHavingHtncRaafc(int isHavingHtncRaafc) {
		this.isHavingHtncRaafc = isHavingHtncRaafc;
	}

	public int getNeverHaveCd() {
		return neverHaveCd;
	}

	public void setNeverHaveCd(int neverHaveCd) {
		this.neverHaveCd = neverHaveCd;
	}

	public int getIsHavingCdTia() {
		return isHavingCdTia;
	}

	public void setIsHavingCdTia(int isHavingCdTia) {
		this.isHavingCdTia = isHavingCdTia;
	}

	public int getIsHavingCdHf() {
		return isHavingCdHf;
	}

	public void setIsHavingCdHf(int isHavingCdHf) {
		this.isHavingCdHf = isHavingCdHf;
	}

	public int getIsHavingCdMi() {
		return isHavingCdMi;
	}

	public void setIsHavingCdMi(int isHavingCdMi) {
		this.isHavingCdMi = isHavingCdMi;
	}

	public int getIsHavingCdOhd() {
		return isHavingCdOhd;
	}

	public void setIsHavingCdOhd(int isHavingCdOhd) {
		this.isHavingCdOhd = isHavingCdOhd;
	}

	public int getIsHavingCdFhoa() {
		return isHavingCdFhoa;
	}

	public void setIsHavingCdFhoa(int isHavingCdFhoa) {
		this.isHavingCdFhoa = isHavingCdFhoa;
	}

	public int getIsHavingCdHs() {
		return isHavingCdHs;
	}

	public void setIsHavingCdHs(int isHavingCdHs) {
		this.isHavingCdHs = isHavingCdHs;
	}

	public int getIsHavingCdCah() {
		return isHavingCdCah;
	}

	public void setIsHavingCdCah(int isHavingCdCah) {
		this.isHavingCdCah = isHavingCdCah;
	}

	public int getIsHavingCdFhos() {
		return isHavingCdFhos;
	}

	public void setIsHavingCdFhos(int isHavingCdFhos) {
		this.isHavingCdFhos = isHavingCdFhos;
	}

	public int getNeverHaveCtd() {
		return neverHaveCtd;
	}

	public void setNeverHaveCtd(int neverHaveCtd) {
		this.neverHaveCtd = neverHaveCtd;
	}

	public int getIsHavingCtdAlf() {
		return isHavingCtdAlf;
	}

	public void setIsHavingCtdAlf(int isHavingCtdAlf) {
		this.isHavingCtdAlf = isHavingCtdAlf;
	}

	public int getIsHavingCtdRdf() {
		return isHavingCtdRdf;
	}

	public void setIsHavingCtdRdf(int isHavingCtdRdf) {
		this.isHavingCtdRdf = isHavingCtdRdf;
	}

	public int getIsHavingCtdCdf() {
		return isHavingCtdCdf;
	}

	public void setIsHavingCtdCdf(int isHavingCtdCdf) {
		this.isHavingCtdCdf = isHavingCtdCdf;
	}

	public int getIsHavingCtdApf() {
		return isHavingCtdApf;
	}

	public void setIsHavingCtdApf(int isHavingCtdApf) {
		this.isHavingCtdApf = isHavingCtdApf;
	}

	public int getIsHavingCtdE() {
		return isHavingCtdE;
	}

	public void setIsHavingCtdE(int isHavingCtdE) {
		this.isHavingCtdE = isHavingCtdE;
	}

	public int getIsHavingCtdGr() {
		return isHavingCtdGr;
	}

	public void setIsHavingCtdGr(int isHavingCtdGr) {
		this.isHavingCtdGr = isHavingCtdGr;
	}

	public int getNeverHaveMah() {
		return neverHaveMah;
	}

	public void setNeverHaveMah(int neverHaveMah) {
		this.neverHaveMah = neverHaveMah;
	}

	public int getIsHavingMahPcs() {
		return isHavingMahPcs;
	}

	public void setIsHavingMahPcs(int isHavingMahPcs) {
		this.isHavingMahPcs = isHavingMahPcs;
	}

	public int getIsHavingMahCps() {
		return isHavingMahCps;
	}

	public void setIsHavingMahCps(int isHavingMahCps) {
		this.isHavingMahCps = isHavingMahCps;
	}

	public int getIsHavingMahSfs() {
		return isHavingMahSfs;
	}

	public void setIsHavingMahSfs(int isHavingMahSfs) {
		this.isHavingMahSfs = isHavingMahSfs;
	}

	public int getIsHavingMahAs() {
		return isHavingMahAs;
	}

	public void setIsHavingMahAs(int isHavingMahAs) {
		this.isHavingMahAs = isHavingMahAs;
	}

	public int getIsHavingMahAls() {
		return isHavingMahAls;
	}

	public void setIsHavingMahAls(int isHavingMahAls) {
		this.isHavingMahAls = isHavingMahAls;
	}

	public double getValueHdl() {
		return valueHdl;
	}

	public void setValueHdl(double valueHdl) {
		this.valueHdl = valueHdl;
	}

	public double getValueTg() {
		return valueTg;
	}

	public void setValueTg(double valueTg) {
		this.valueTg = valueTg;
	}

	public double getValueFbg() {
		return valueFbg;
	}

	public void setValueFbg(double valueFbg) {
		this.valueFbg = valueFbg;
	}

	public int getNeverHaveDiseases() {
		return neverHaveDiseases;
	}

	public void setNeverHaveDiseases(int neverHaveDiseases) {
		this.neverHaveDiseases = neverHaveDiseases;
	}

	public int getNeverTakeDrugs() {
		return neverTakeDrugs;
	}

	public void setNeverTakeDrugs(int neverTakeDrugs) {
		this.neverTakeDrugs = neverTakeDrugs;
	}

	public int getNeverDrinkSmoke() {
		return neverDrinkSmoke;
	}

	public void setNeverDrinkSmoke(int neverDrinkSmoke) {
		this.neverDrinkSmoke = neverDrinkSmoke;
	}

	public int getLabourLevel() {
		return labourLevel;
	}

	public void setLabourLevel(int labourLevel) {
		this.labourLevel = labourLevel;
	}

	public int getNeverHaveDevice() {
		return neverHaveDevice;
	}

	public void setNeverHaveDevice(int neverHaveDevice) {
		this.neverHaveDevice = neverHaveDevice;
	}

	public int getIsHavingBpDevice() {
		return isHavingBpDevice;
	}

	public void setIsHavingBpDevice(int isHavingBpDevice) {
		this.isHavingBpDevice = isHavingBpDevice;
	}

	public int getIsHavingBsDevice() {
		return isHavingBsDevice;
	}

	public void setIsHavingBsDevice(int isHavingBsDevice) {
		this.isHavingBsDevice = isHavingBsDevice;
	}

}
