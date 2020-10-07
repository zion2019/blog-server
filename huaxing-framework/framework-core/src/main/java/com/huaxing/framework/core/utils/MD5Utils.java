package com.huaxing.framework.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.Base64Utils;

public class MD5Utils {
	
	/**利用MD5进行加密
     * @param str  待加密的字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException  
     */
    public static String md5(String str){
    	String newstr = "";
    	try{
	        //确定计算方法
	        MessageDigest md5=MessageDigest.getInstance("MD5");
	        //加密后的字符串
         	newstr=Base64Utils.encodeToString(md5.digest(str.getBytes("utf-8")));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return newstr;
    }
    
    public static void main(String[] args) {
		System.out.println(MD5Utils.md5("123456"));
	}

}
