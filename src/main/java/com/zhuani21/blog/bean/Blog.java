package com.zhuani21.blog.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.zhuani21.blog.util.SerializeObject;

@SuppressWarnings("serial")
public class Blog implements Serializable {
	private List<String> contentList = new ArrayList<String>();

	public void add(String c) {
		if (StringUtils.isNotBlank(c)) {
			contentList.add(c);
			SerializeObject.serialize(this, "admin." + SerializeObject.BLOG_FILE_NAME);
		}
	}

	public List<String> getList() {
		if (contentList.size() == 0) {
			return contentList;
		} else {
			List<String> cList = new ArrayList<String>();
			int i = contentList.size() - 1;
			do {
				cList.add(contentList.get(i));
				i--;
<<<<<<< HEAD
			} while (i > 0);
=======
			} while (i > -1);
>>>>>>> f3885674040c9c9bbe2419a1017c37fc9cbbee33
			return cList;
		}
	}
}
