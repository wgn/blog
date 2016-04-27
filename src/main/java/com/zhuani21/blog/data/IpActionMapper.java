package com.zhuani21.blog.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

public class IpActionMapper {
	private static Logger logger = Logger.getLogger(IpActionMapper.class); 
	private static Map<String,Long> timeMap  = new ConcurrentHashMap<String,Long>(); 
	private static Map<String,Integer> countMap = new ConcurrentHashMap<String,Integer>(); 
	private static Integer U_COUNT_MAX = 15;
	private static Integer COUNT_MAX = 300;

	public static final boolean uaccess(String ipu){
		long nowTime = System.currentTimeMillis();
		Long lastTime = timeMap.get(ipu);
		if(null==lastTime){
			timeMap.put(ipu, nowTime);
			countMap.put(ipu, 1);
			//logger.info("passu," + ipu + ",init access count=1");
			return true;
		}else{
			if((nowTime-lastTime)<10000){
				int count = countMap.get(ipu);
				if(count<U_COUNT_MAX){
					count++;
					countMap.put(ipu, count);
					//logger.info("passu," + ipu + ",add access count=" + count);
					return true;
				}else{
					logger.info("no passu," + ipu + ",add access count=" + count);
					return false;
				}
			}else{
				timeMap.put(ipu, nowTime);
				countMap.put(ipu, 1);
				//logger.info("passu," + ipu + ",reset access count=1");
				return true;
			}
		}
	}

	public static boolean access(String ip) {
		long nowTime = System.currentTimeMillis();
		Long lastTime = timeMap.get(ip);
		if(null==lastTime){
			timeMap.put(ip, nowTime);
			countMap.put(ip, 1);
			//logger.info("pass," + ip + ",init access count=1");
			return true;
		}else{
			if((nowTime-lastTime)<10000){
				int count = countMap.get(ip);
				if(count<COUNT_MAX){
					count++;
					countMap.put(ip, count);
					//logger.info("pass," + ip + ",add access count=" + count);
					return true;
				}else{
					logger.info("no pass," + ip + ",add access count=" + count);
					return false;
				}
			}else{
				//logger.info("pass," + ip + ",reset access count=1");
				timeMap.put(ip, nowTime);
				countMap.put(ip, 1);
				return true;
			}
		}
	}
}
