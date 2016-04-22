package com.ed.v1.common;

/**
 * Created by Haijun.Wang on 15/6/24.
 */
public class Constants {

	public static final class CareLinker {
		public static final String DEVELOP_SITE_URL = "http://api.carelinker.com/carelinker/api";

		public static final String PRODUCT_SITE_URL = "https://platform.carelinker.com/carelinker/api";

		public static final String APP_TYPE = "enterprise";
		public static final String VERSION = "131";

		public static final String SHARE_NAME = "pharmacy_v2";
		public static final String BIG_V_LIST = "bigVList";
		public static final String DRUG_LIB_VERSION = "drug_lib_version";

		public static final String URL_CHECK_CODE = "/system/captchas";
		public static final String URL_LOGIN = "/users/loginInfo";
		public static final String URL_REGISTER = "/users/user";
		public static final String URL_CID = "/users/userInfo/cid";
		public static final String URL_GET_VOIP = "/users/userInfo/ronglianAccount";
		public static final String URL_GET_FRIENDS = "/friends/patients/tags";
		public static final String URL_GET_MESSAGELIST = "/messages/libs/group";
		public static final String URL_GET_ALLFRIENDS = "/friends/patients";
		public static final String URL_GET_FRIENDS_BY_SEARCH = "/friends/patients/search";
		public static final String URL_GET_TOKEN = "/images/qiniu/upToken";
		public static final String URL_MARK = "/employees/auditInfo";
		public static final String URL_FORGET_PASSWORD = "/users/userInfo/password";
		public static final String URL_PATIENT_LOGIN = "/users/userInfo/patientInfo";
		public static final String URL_BIND_WX = "/wechat/loginQrTicket";
		public static final String URL_DEVICE_LIST = "/devices/gprs/patientDevices/binding";
		public static final String URL_PROFILE_HEALTHHISTORY = "/profiles/healthHistorys";
		public static final String URL_HEALTH_CUSTOM = "/profiles/healthCustom";
		public static final String URL_PATIENTS_IMAGES = "/images/patients/images";
		public static final String URL_DEL_PATIENTS_IMAGES = "/images/patients/images/image";

		/* userinfo */
		public static final String AGESEX = "/profiles/healthProfiles/baseInfos";
		public static final String PLATFORM = "android";
		public static final String UpdateAGESEX = "/profiles/healthProfiles";
		public static final String POINTS = "/points/employees";
		public static final String TOTAL = "/points/employees/total";
		public static final String POINS_SETTLEMENT = "/points/settlement";
		public static final String POINS_INCOMES = "/points/employees/settlement";
		public static final String METHODS = "/points/settlement/methods";
		public static final String SAVE_METHODS = "/points/settlement/methods/method";
		public static final String DELETE_METHODS = "/points/settlement/methods/method";
		public static final String QING_NIU = "http://carelinkermsd.qiniudn.com/";
		public static final String BIG_V = "/friends/bigV";
		/**
		 * 获取患者全部健康档案
		 */
		public static final String URL_HEALTH_PROFILE = "/profiles/healthProfiles";
		// 用药列表
		public static final String URL_TAKE_DRUG = "/drugs/patientDrugs";
		/**
		 * 药品库
		 */
		public static final String URL_DRUG_LIST = "/drugs/libs/drugs";
		public static final String URL_ONE_DRUG = "/drugs/libs/drug";
		/**
		 * 患教文章
		 */
		public static final String URL_ARTICLES_TITLE = "/articles/patientEdu/firstClass";
		public static final String URL_ARTICLES_LIST = "/articles/patientEdu";

		// 首页通知列表
		public static final String URL_GET_NOTICE_LIST = "/articles/system";
		/**
		 * 知识
		 */
		public static final String URL_GET_FIRSTCLASS = "/articles/knowledge/firstClass";
		public static final String URL_GET_COURSELIST = "/articles/knowledge/lessons";
		public static final String URL_GET_SECTIONS = "/articles/knowledge/sections";

		/**
		 * 第三方登录
		 */
		public static String WX_APPID = "wx6cfe640aebc9c49b";
		public static String WX_APPSECRET = "38fd82f0b17a34476ad7de230c6f826f";
		public static String WX_GETTOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
		public static String WX_GETUSERINFO = "https://api.weixin.qq.com/sns/userinfo";
		public static String URL_LOGIN_BY_TP = "/users/loginInfo/tp";
	}
}
