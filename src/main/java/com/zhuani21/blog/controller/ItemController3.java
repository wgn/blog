package com.zhuani21.blog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.bean.Items;

@Controller
public class ItemController3 {

	@RequestMapping("/aaa")
	public ModelAndView handleRequest() throws ServletException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		List<Items> itemsList = new ArrayList<Items>();
		Items items_1 = new Items();
		items_1.setName("联想笔记本");
		items_1.setPrice(6000f);
		items_1.setDetail("ThinkPad T430 联想笔记本电脑！");

		Items items_2 = new Items();
		items_2.setName("苹果手机");
		items_2.setPrice(5000f);
		items_2.setDetail("iphone6苹果手机！");

		itemsList.add(items_1);
		itemsList.add(items_2);
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("item/itemList");
		return modelAndView;
	}

}
