<<<<<<< HEAD
package com.zhuani21.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WDate {
	private String toString ;
	
	public WDate(){}
	public WDate(String formate){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		toString = sdf.format(now);
	}
	
	@Override
	public String toString() {
		return toString;
	}
}
=======
package com.zhuani21.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WDate {
	private String toString ;
	
	public WDate(){}
	public WDate(String formate){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		toString = sdf.format(now);
	}
	
	@Override
	public String toString() {
		return toString;
	}
}
>>>>>>> f3885674040c9c9bbe2419a1017c37fc9cbbee33
