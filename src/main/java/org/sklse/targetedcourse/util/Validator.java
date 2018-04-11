package org.sklse.targetedcourse.util;

import javax.servlet.ServletException;
import java.util.regex.Pattern;

/**
 * 校验器：利用正则表达式校验邮箱、手机号等
 * 
 * @author liujiduo
 * 
 */
public class Validator {
	/**
	 * 正则表达式：验证用户名
	 */
	public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

	/**
	 * 正则表达式：验证密码
	 */
	public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

	/**
	 * 正则表达式：验证手机号
	 */
	public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

	/**
	 * 正则表达式：验证邮箱
	 */
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 * 正则表达式：验证汉字
	 */
	public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

	/**
	 * 正则表达式：验证身份证
	 */
	public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

	/**
	 * 正则表达式：验证URL
	 */
	public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

	/**
	 * 正则表达式：验证IP地址
	 */
	public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

	/**
	 * 正则表达式：验证身份证号
	 */
	public static final String REGEX_IDENTIFICATION = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
	public static final String REGEX_IDENTIFICATION18 = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$\n)";
	/**
	 * 校验用户名
	 * 
	 * @param username
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isUsername(String username) {
		return Pattern.matches(REGEX_USERNAME, username);
	}

	/**
	 * 校验密码
	 * 
	 * @param password
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isPassword(String password) {
		return Pattern.matches(REGEX_PASSWORD, password);
	}

	/**
	 * 校验手机号
	 * 
	 * @param mobile
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isMobile(String mobile) {
		return Pattern.matches(REGEX_MOBILE, mobile);
	}

	/**
	 * 校验邮箱
	 * 
	 * @param email
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isEmail(String email) {
		return Pattern.matches(REGEX_EMAIL, email);
	}

	/**
	 * 校验汉字
	 * 
	 * @param chinese
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isChinese(String chinese) {
		return Pattern.matches(REGEX_CHINESE, chinese);
	}

	/**
	 * 校验身份证
	 * 
	 * @param idCard
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isIDCard(String idCard) {
		return Pattern.matches(REGEX_ID_CARD, idCard);
	}

	/**
	 * 校验URL
	 * 
	 * @param url
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isUrl(String url) {
		return Pattern.matches(REGEX_URL, url);
	}

	/**
	 * 校验IP地址
	 * 
	 * @param ipAddr
	 * @return
	 */
	public static boolean isIPAddr(String ipAddr) {
		return Pattern.matches(REGEX_IP_ADDR, ipAddr);
	}

	/**
	 * 校验身份证号
	 *
	 * @param idetification
	 * @return
	 */
	public static boolean isIdentification(String idetification){
		return Pattern.matches(REGEX_IDENTIFICATION, idetification);
	}

	/**
	 * 校验手机号和密码
	 * @param phoneNumber
	 * @param password
	 * @return 手机号密码匹配信息
	 * @throws ServletException
	 */
	public static String isPhoneNumberAndPassword(String phoneNumber,String password) throws ServletException {
		if (phoneNumber == null || password == null) {
			return ("Account and password can not be empty.");
		}
		if (!Validator.isMobile(phoneNumber)) {
			return  ("PhoneNumber not valid.");
		}

		if (!Validator.isPassword(password)) {
			return ("Password not valid. It consists of letters and numbers only and its length should range from 6 to 16 ");
		}
			return "true";


	}

	/**
	 * 校验身份证号和密码
	 * @param identification
	 * @param password
	 * @return 验证信息
	 */
	public static String isIdentificationAndPassword(String identification, String password){
		if(identification == null || password == null){
			return ("Account and password can not be empty");
		}
		if(!Validator.isIdentification(identification)){
			return ("Identification is not valid");
		}
		if(!Validator.isPassword(password)){
			return ("Password not valid. It consists of letters and numbers only and its length should range from 6 to 16");
		}
		return "true";
	}
	public static void main(String[] args) {
		String username = "123456";
		System.out.println(Validator.isPassword(username));
		System.out.println(Validator.isChinese(username));
		String iden = "110107199702022922";
		System.out.println(Validator.isIdentification(iden));
	}
}
