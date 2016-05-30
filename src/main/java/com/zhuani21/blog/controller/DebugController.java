package com.zhuani21.blog.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.bean.DebugInfoVO;

@Controller
@RequestMapping("/debug")
public class DebugController {

	//private static Logger logger = Logger.getLogger(DebugController.class); 
	
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
	public ModelAndView gosd(){
		return null;
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
		while(StringUtils.isNoneBlank(line)){
			sysProperties.append(line).append(System.getProperty("line.separator"));
			line = null;
			line = br.readLine();
		}
		return sysProperties.toString();
	}

	
	
}
