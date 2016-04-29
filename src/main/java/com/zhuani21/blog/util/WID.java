package com.zhuani21.blog.util;

public class WID {
	private static int i = 0;
	public static void main(String[] args) {
		for(int a=0;a<115;a++){
			get();
		}
	}
	//return -时间戳毫秒（13位）+流水号（2位）+ 机器编号(1位)
	public synchronized static String get(String mno){
		mno = ((null == mno) ? "0" : mno);
		i = i % 100;
		String index = (i < 10) ? ("0" + i) : "" + i;
		long l = System.currentTimeMillis();
		String id =  l + index + mno;
		i++;
		System.out.println(mno + ":" + l + ":" + index + ":" + id);
		return id;
	}
	public synchronized static String get(){
		return get(null);
	}
	public synchronized static Long getLong(){
		return Long.parseLong(get());
	}
}
