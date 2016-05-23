package com.zhuani21.blog.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.data.CookieMapper;
import com.zhuani21.blog.data.SysProperties;
import com.zhuani21.blog.sqlite.service.CookieService;


public class WebRequestUtils {
	
	private static int PROXY_UA=0, PROXY_ORI_UA_HEADER=1, PROXY_ORI_UA_PARAM=3; 
	private static String[][] PROXY = {{"opera mini","x-operamini-phone-ua","x-forwarded-for", "(operamini)"},
		{"google wireless transcoder","x-device-user-agent","x-forwarded-for", "(gwt)"},
		{"novarra-vision","x-device-user-agent","x-forwarded-for", "(novarra)"},
		{" bolt","x-bolt-phone-ua","x-forwarded-for", "(bolt)"},
		{" skyfire","x-skyfire-phone","x-forwarded-for", "(skyfire)"},
		{"s40ovibrowser","user-agent","x-forwarded-for", "(ovibrowser)"}, // Ovi browser has to be last
		{"ucbrowser","user-agent","x-forwarded-for", "(ucwebbrowser)"},
		{"blackberry","user-agent","x-forwarded-for,http_client_ip,http_x_forwarded_for,http_x_forwarded,http_forwarded_for,http_forwarded", "(blackberry)"}
	};
	
	public static final String getIp(HttpServletRequest request){
		if (request == null){
			return null;
		}
		// try to get ip from header first
		// this is to easy our qa and internal testing
		String ip = request.getHeader("ip");
		if (ip != null) {
			if (!checkIpText(ip)){
				ip = null;
			}
		}

		// not available in any internal testing form
		// get the real IP
		if (ip == null) {
			ip = getIPFromHeader(request);
			if (null == ip) {
				ip = request.getRemoteAddr();
			}
		}
		return ip;
	}
	
	public static final String getUa(HttpServletRequest request) {
		if (request == null)
			return null;

		String ua = request.getHeader("user-agent");
		if (ua != null) {
			String ua2 = ua.toLowerCase();
			if (ua != null && ua.length() > 0) {
				for (int i = 0; i < PROXY.length; i++) {
					if (ua2.indexOf(PROXY[i][PROXY_UA]) != -1) {
						// Get the useragent from the appropriate header
						String device_ua = request.getHeader(PROXY[i][PROXY_ORI_UA_HEADER]);
						// Use default value for user-agent from the header
						// if not available from the custom header
						if (!StringUtils.isNotBlank(device_ua))
							device_ua = request.getHeader("user-agent");
						// If UA still not available, set it to empty
						if (!StringUtils.isNotBlank(device_ua))
							device_ua = "";
						// Attach browser / gateway identifier
						String param = PROXY[i][PROXY_ORI_UA_PARAM];

						ua = MessageFormat.format("{0}{1}", param, device_ua);

						//vulogger.info(PROXY[i][PROXY_UA] + " detected: ip=" + ip + " ua=" + ua);
					}
				}
			}
		} else {
			ua = null;
		}
		return ua;
	}
	/**
	 * 校验IP格式
	 * @param ip
	 * @return
	 */
	public static final boolean checkIpText(String ip) {
		boolean ok = true;
		int index = 0;
		int ndot = 0;
		for (int i = 0; i < ip.length(); i++) {
			char c = ip.charAt(i);
			if (c == '.') {
				try {
					int j = Integer.parseInt(ip.substring(index, i));
					if (ndot == 0 && (j < 1 || j > 255)) {
						ok = false;
						break;
					} else if (j < 0 || j > 255) {
						ok = false;
						break;
					}
					ndot++;
					index = i + 1;
				} catch (NumberFormatException e) {
					ok = false;
					break;
				}
			} else if (c <= 0 && c >= 9) {
				ok = false;
				break;
			}
		}
		if (ndot == 3) {
			try {
				int j = Integer.parseInt(ip.substring(index, ip.length()));
				if (j < 0 || j > 255) {
					ok = false;
				}
			} catch (NumberFormatException e) {
				ok = false;
			}
		} else {
			ok = false;
		}
		return ok;
	}
	
	static String[] ipheaders = {"x-forwarded-for","http_client_ip","http_x_forwarded_for","http_x_forwarded","http_forwarded_for","http_forwarded"};
	/**
	 * 使用更极端的方式获取IP
	 * @param request
	 * @return
	 */
	private static String getIPFromHeader(HttpServletRequest request){
		String ip = null;
		for(String header:ipheaders){
			ip = request.getHeader(header);
			if(StringUtils.trimToNull(ip)!=null){
				// Get the ip from the appropriate header
				if (ip.indexOf(',')>0){
					String[] ips = ip.split(",");
					ip=null;
					for(String ip1:ips){
						ip = ip1.trim();
						try {
							InetAddress ipaddresss = Inet4Address.getByName(ip);
							if (!ip.contains("unknown") && !ipaddresss.isSiteLocalAddress() && !ipaddresss.isLoopbackAddress() /*&& !ip.startsWith("192") && !ip.startsWith("172") && !ip.startsWith("10.")*/)
								break;
							else
								ip = null;
						} catch (UnknownHostException e) {
							//vulogger.error("1error ip:"+ip);
							//                            e.printStackTrace();
						}
					}
				}else{
					try {
						if(ip.contains("unknown") || Inet4Address.getByName(ip).isSiteLocalAddress() || Inet4Address.getByName(ip).isLoopbackAddress()) ip = null;
					} catch (UnknownHostException e) {
						//vulogger.error("2error ip:"+ip);
						//                            e.printStackTrace();
					}
				}
				if(ip!=null)
					break;
			}
		}
		return StringUtils.trimToNull(ip);
	}
	
	public static User getUserFromCookie(HttpServletRequest req, CookieService cookieService) {
		Cookie cookie = getCookieByName(req,WConstant.COOKIE_LOGIN_KEY);
		if(null!=cookie){
			String value = cookie.getValue();
			String ip = WebRequestUtils.getIp(req);
			if(StringUtils.isNoneBlank(value) && StringUtils.isNoneBlank(ip)){
				User user = null ;
				
				if("1".equals(SysProperties.get("CookieMapperModel"))){
					user = CookieMapper.get(ip + value);
				}else if("2".equals(SysProperties.get("CookieMapperModel"))){
					user = cookieService.get(ip + value);
				}
				
				return user;
			}
		}
		return null;
	}
	private static  Cookie getCookieByName(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;
    }else{
         return null;
    }   
}
private static  Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
    Cookie[] cookies = request.getCookies();
    if(null!=cookies){
        for(Cookie cookie : cookies){
            cookieMap.put(cookie.getName(), cookie);
        }
    }
    return cookieMap;
}
}
