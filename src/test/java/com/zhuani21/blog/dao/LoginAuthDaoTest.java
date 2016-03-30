package com.zhuani21.blog.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhuani21.blog.auto.bean.User;
import com.zhuani21.blog.bean.LoginAuthCustom;
import com.zhuani21.blog.dao.LoginAuthDao;
import com.zhuani21.blog.vo.LoginAuthCustomVo;

public class LoginAuthDaoTest {
	LoginAuthDao loginAuthDao = null;
	@Before
	public void setUpBeforeClass() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		loginAuthDao = (LoginAuthDao) ac.getBean("loginAuthDao");;
	}

	@Test
	public void test() throws Exception {
		LoginAuthCustomVo loginAuthCustomVo = new LoginAuthCustomVo();
		LoginAuthCustom loginAuthCustom = new LoginAuthCustom();
		loginAuthCustom.setUsername("zhuani21");
		loginAuthCustom.setPassword("1234");
		loginAuthCustomVo.setLoginAuthCustom(loginAuthCustom);
		User u = loginAuthDao.findUserByLoginAuth(loginAuthCustomVo);
		System.out.println(u.getNickname() + u.getEmail() + u.getMobile());
	}

}
