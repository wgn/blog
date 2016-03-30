<<<<<<< HEAD
package com.zhuani21.blog.util;

import java.util.List;

public class CollectionCheckUtils {
	public static final <T> boolean isBlankList(List<T> list) {
		if(null!=list && list.size()>0){
			return false;
		}
		return true;
	}
	
	public static final <T> boolean isNotBlankList(List<T> list) {
		if(isBlankList(list)){
			return false;
		}
		return true;
	}
}
=======
package com.zhuani21.blog.util;

import java.util.List;

public class CollectionCheckUtils {
	public static final <T> boolean isBlankList(List<T> list) {
		if(null!=list && list.size()>0){
			return false;
		}
		return true;
	}
	
	public static final <T> boolean isNotBlankList(List<T> list) {
		if(isBlankList(list)){
			return false;
		}
		return true;
	}
}
>>>>>>> f3885674040c9c9bbe2419a1017c37fc9cbbee33
