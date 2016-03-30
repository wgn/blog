package com.zhuani21.blog.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class BeanCopyUtils {

	public static final <T, TT> List<T> getCustomBeanList(List<TT> source, Class<T> clazz) throws InstantiationException, IllegalAccessException {
		List<T> resList = new ArrayList<T>();

		if (null != source && source.size() > 0) {
			for (TT tt : source) {
				T t = clazz.newInstance();
				BeanUtils.copyProperties(tt, t);
				resList.add(t);
			}
		}

		return resList;
	}

}
