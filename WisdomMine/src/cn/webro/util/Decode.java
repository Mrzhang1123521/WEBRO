package cn.webro.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Decode {

	public String getDecode(String opin){
		String result ="";
		if(opin!=null&&!"".equals(opin)){
			try {
				result= URLDecoder.decode(opin, "utf-8");
			} catch (UnsupportedEncodingException e) {
				result="";
			}
		}
		return result;
	}
	public String getzhongwen(String opin){
		String result ="";
		if(opin!=null&&!"".equals(opin)){
			try {
				result= URLDecoder.decode(opin, "utf-8");
			} catch (UnsupportedEncodingException e) {
				result="";
			}
		}
		return result;
	}
}
