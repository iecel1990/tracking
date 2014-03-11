package com.yesmynet.tracking.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class TrackingTools {
	public static String getCookie(HttpServletRequest request,String cookieName) {
		if(StringUtils.isBlank(cookieName)){
			return "";
		}
		try {
			Cookie[] cookies = request.getCookies();
			if(cookies != null && cookies.length > 0){
				for (Cookie cookie : cookies) {
					if(cookie.getName().equals(cookieName)) {
						return URLDecoder.decode(cookie.getValue(),"utf-8");
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public static String getIp(HttpServletRequest request)
    {
        String ip = request.getHeader("X-Forwarded-For");
        // System.out.println("X-Forwarded-For"+ip);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
		String[] ips = ip.trim().split(",");
		String firstIP = ip;

		if (ips.length > 2) {
			firstIP = ips[1];
		}else{
			firstIP = ips[0];
		}
		return firstIP;
    }
}
