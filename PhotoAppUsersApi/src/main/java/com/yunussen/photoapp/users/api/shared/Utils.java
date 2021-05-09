package com.yunussen.photoapp.users.api.shared;

import java.security.SecureRandom;

public class Utils {

	private static final String CHARACTER="0123456789qwertyuopasdfghjklizxcvbnmQWERTYUOPASDFGHJKLZXCVBNM";
	public static String getUserId(int length) {
		return getRandomId(length);
	}
	
	public static String getRandomId(int length) {
		
		StringBuilder sb=new StringBuilder(length);
		int number=new SecureRandom().nextInt(62);
		for(int i=0;i<=length;i++) {
			sb.append(CHARACTER.charAt(number));
		}
		 return sb.toString();
	}
	
	
}
