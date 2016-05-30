package com.zhuani21.blog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class T {

	/**
	 * 订单号（生成规则：取系统配置文件的instance号2位+12位currentTimeMillis/10(10毫秒)+2位流水号）
	 * 
	 * @return
	 */
	private static int i = 0;
	private static String instanceId = "";

	public static String getInstanceId() {
		return instanceId;
	}

	public static void setInstanceId(String instanceId) {
		if (instanceId == null || instanceId.length() != 2) {
			throw new IllegalArgumentException("Set instanceId error: the length should be 2 '" + (instanceId == null ? "" : instanceId) + "' is not correct.");
		}
		T.instanceId = instanceId;
	}

	/**
	 * @DESCRIPION :每10毫秒可生成100个序列号；优于每毫秒10个序列号
	 * @Create on: 2013-4-16 下午5:27:05
	 * @Author : "Jack"
	 * @return : String
	 */
	public synchronized static String genOrdId16() {
		i = i % 100;
		String index = (i < 10) ? ("0" + i) : "" + i;
		String orderNum = instanceId + System.currentTimeMillis() / 10 + index;
		i++;
		return orderNum;
	}

	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = sdf.parse("2016-05-26 10:18:18");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, 14);
		Date nd = c.getTime();
		System.out.println(sdf.format(nd));
		System.out.println(c.get(Calendar.DATE));
		System.out.println(c.get(Calendar.DAY_OF_MONTH));
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		System.out.println(c.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		System.out.println(c.get(Calendar.DAY_OF_YEAR));
	}

}
