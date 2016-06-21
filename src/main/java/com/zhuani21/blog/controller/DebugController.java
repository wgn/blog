package com.zhuani21.blog.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.bean.DebugInfoVO;
import com.zhuani21.blog.bean.PropertyVO;
import com.zhuani21.blog.data.SysProperties;
import com.zhuani21.blog.util.PropertiesFileUtils;

@Controller
@RequestMapping("/debug")
public class DebugController {

	private static Logger logger = Logger.getLogger(DebugController.class); 
	
	@RequestMapping("/index")
	public ModelAndView info() throws IOException{
		ModelAndView modelAndView = new ModelAndView();
		
		DebugInfoVO debugInfoVO = systemPropertiesLoad();
		modelAndView.addObject("debugInfoVO", debugInfoVO);
		
		modelAndView.addObject("sysProperties", sysPrpoertiesSetting());
		
		
		modelAndView.setViewName("debugIndex");
		return modelAndView;
	}
	@RequestMapping("/update")
	public ModelAndView updateSysProperty(PropertyVO prop){
		ModelAndView modelAndView = new ModelAndView();
		
		String propValue = PropertiesFileUtils.getProperty("sys.properties", prop.getPropertyName());
		if(StringUtils.isNotBlank(propValue) // 系统已有该属性
				//&& StringUtils.isNotBlank(prop.getPropertyName())//新属性名不为空、因可获取该属性，固一定存在，没必要判断
				&& StringUtils.isNotBlank(prop.getPropertyValue())){//新属性值不为空
			PropertiesFileUtils.updateProperty("sys.properties", prop);
			logger.info("修改sys.properties文件的属性："+prop.getPropertyName()+"，原值：" + propValue + "，新值：" + prop.getPropertyValue());
			//重新读取sys.properties文件
			SysProperties.reloadSysProperties();
		}
		modelAndView.setViewName("redirect:/debug/index");
		return modelAndView;
	}
	
	/**
	 * 我想修改是有意义的，新增则基本没有，因所有的属性都在写程序的时候做了设置，后续的使用基本是修改，新增一段程序却没有增加属性的行为很奇怪。
	 * @param prop
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView addSysProperty(PropertyVO prop){
		ModelAndView modelAndView = new ModelAndView();
		if(null!=prop && StringUtils.isNotBlank(prop.getPropertyName())//新属性名不为空
				&& StringUtils.isNotBlank(prop.getPropertyValue())){//新属性值不为空
			//注释需要有#以及换行
			List<String> saveComments = transferComments(prop);
			prop.setComments(saveComments);
			PropertiesFileUtils.addProperty("sys.properties", prop);
			logger.info("修改sys.properties文件.新增属性:"+prop.getPropertyName()+"，值：" + prop.getPropertyValue());
			//重新读取sys.properties文件
			SysProperties.reloadSysProperties();
		}
		modelAndView.setViewName("redirect:/debug/index");
		return modelAndView;
	}
	private List<String> transferComments(PropertyVO prop) {
		List<String> saveComments = new ArrayList<String>();
		List<String> comments = prop.getComments();
		if(null!=comments && comments.size()>0){
			for(String comment:comments){
				String [] comms = comment.split(System.getProperty("line.separator"));
				if(null!=comms && comms.length>0){
					for(String s : comms){
						if(s!=null){
							if(s.startsWith("#")){
								saveComments.add(s);
							}else{
								saveComments.add("#" + s);
							}
						}
					}
				}
			}
		}
		if(saveComments.size()>0){
			return saveComments;
		}else{
			return null;
		}
	}

	private DebugInfoVO systemPropertiesLoad() {
		DebugInfoVO debugInfoVO = new DebugInfoVO();
		
		String osName = System.getProperty("os.name");
		debugInfoVO.getProps().put("os.name", osName);
		
		String lineSeparator = System.getProperty("line.separator");
		lineSeparator = lineSeparator.replaceAll ( "\n","\\\\n").replaceAll("\r", "\\\\r");
		debugInfoVO.getProps().put("line.separator", lineSeparator);
		return debugInfoVO;
	}

	private String sysPrpoertiesSetting() throws IOException {
		InputStream is = Resources.getResourceAsStream("sys.properties");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuffer sysProperties = new StringBuffer(System.getProperty("line.separator"));
		String line = br.readLine();
		while(null!=line){
			sysProperties.append(line).append(System.getProperty("line.separator"));
			line = null;
			line = br.readLine();
		}
		return sysProperties.toString();
	}

	
	
}
