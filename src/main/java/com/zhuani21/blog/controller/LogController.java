package com.zhuani21.blog.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.bean.LogFileVO;
import com.zhuani21.blog.util.ConfigReader;

@Controller
@RequestMapping("/log")
public class LogController {
	@RequestMapping("/index")
	public ModelAndView index() throws Exception {
		//log4j.appender.FILE.File=
		
		ModelAndView modelAndView = new ModelAndView();
		List<LogFileVO> tomcatLogFileList = null;
		List<LogFileVO> customLogFileList = null;
		String logFile = ConfigReader.classpathPropertyLoad("log4j.properties", "log4j.appender.FILE.File");
		if(StringUtils.isNotBlank(logFile)){
			int e = logFile.lastIndexOf("/");
			String blogPath = logFile.substring(0, e);
			e = blogPath.lastIndexOf("/");
			String tomcatLogPath = blogPath.substring(0, e);
			tomcatLogFileList = getLogFileList(tomcatLogPath);
			customLogFileList = getLogFileList(blogPath);
		}
		
		
		modelAndView.addObject("tomcatLogFileList", tomcatLogFileList);
		modelAndView.addObject("customLogFileList", customLogFileList);
		modelAndView.setViewName("blogDebug");
		return modelAndView;
	}
	private List<LogFileVO> getLogFileList(String path) {
		List<LogFileVO> logFileList = null;
		String logFilePath = path;
		
		File logFileDir = new File(logFilePath);
		if(logFileDir.exists() && logFileDir.isDirectory()){
			File[] logFiles = logFileDir.listFiles();
			if(null!=logFiles && logFiles.length>0){
				logFileList = new ArrayList<LogFileVO>();
				for(File f: logFiles){
					if(f.isFile()){
						LogFileVO logFile = new LogFileVO();
						logFile.setFilename(f.getName());
						logFile.setSize(f.length());
						logFileList.add(logFile);
					}
				}
			}
		}
		return logFileList;
	}
	
	public static void main(String[] args){
		
	}
}
