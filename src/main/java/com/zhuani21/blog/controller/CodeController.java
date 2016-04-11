package com.zhuani21.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhuani21.blog.auto.bean.Code;
import com.zhuani21.blog.bean.CodeCustom;
import com.zhuani21.blog.service.CodeService;

@Controller
@RequestMapping("/code")
public class CodeController {
	
	private static Logger logger = Logger.getLogger(CodeController.class); 
	
	@Autowired
	CodeService codeService;

	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		String codeType = req.getParameter("codeType");
		ModelAndView modelAndView = new ModelAndView();
		List<CodeCustom> baseCodeTypeList = codeService.queryBaseCodeTypeList();
		List<CodeCustom> codeList = codeService.queryCodeList(codeType);
		modelAndView.addObject("codeList", codeList);
		modelAndView.addObject("selectedCodeType", codeType);
		modelAndView.addObject("codeTypeList", baseCodeTypeList);
		modelAndView.setViewName("codeList");
		return modelAndView;
	}
	@RequestMapping(value={"/add"},method={RequestMethod.GET})
	public ModelAndView addView(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		return addAndEditView(req,resp,"add");
	}
	@RequestMapping(value={"/add"},method={RequestMethod.POST})
	public ModelAndView save(@Valid Code code,BindingResult bindingResult) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		if(bindingResult.hasErrors()){
			modelAndView.addObject("validationErrors", bindingResult.getAllErrors());
			modelAndView.addObject("opType", "add");
			modelAndView.addObject("code", code);
			modelAndView.setViewName("codeUpdate");
			return modelAndView;
		}
		if(!CodeCustom.BASE_CODE_TYPE.equals(code.getType())){
			Code originCode = codeService.queryCodeByCodeType(code.getType());
			if(originCode==null){
				modelAndView.addObject("baseCodeTypeError", "请先添加基本类型配置，基本类型为base，编码为要添加新类型的type，名称为新类型名");
				modelAndView.addObject("opType", "add");
				modelAndView.addObject("code", code);
				modelAndView.setViewName("codeUpdate");
				return modelAndView;
			}
		}
		code.setId(null);
		codeService.insertCode(code);
		modelAndView.setViewName("redirect:/code/list");
		return modelAndView;
	}
	@RequestMapping(value={"/edit"},method={RequestMethod.GET})
	public ModelAndView editView(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		return addAndEditView(req,resp,"edit");
	}
	@RequestMapping(value={"/edit/{id}"},method={RequestMethod.GET})
	public ModelAndView editRestView(@PathVariable("id") Integer id) throws Exception {
		return addAndEditRestView(id,"edit");
	}
	
	@RequestMapping(value={"/delete"})
	public ModelAndView deleteCode(Integer id) throws Exception {
		ModelAndView modelAndView= new ModelAndView();
		if(null!=id && id>0){
			codeService.deleteCodeById(id);
			modelAndView.setViewName("redirect:/code/list");
			return modelAndView;
		}
		return null;
	}
	
	@RequestMapping(value={"/edit"},method={RequestMethod.POST})
	public ModelAndView edit(@Validated CodeCustom code,BindingResult bindingResult) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		CodeCustom dbCode = null;
		if(code!=null && code.getId()!=null && code.getId()!=0){
			dbCode = codeService.queryCodeById(code.getId());
			if(dbCode!=null){
				logger.info("new:" + code + ";old:" + dbCode);
				codeService.updateCode(code);
			}
		}
		modelAndView.setViewName("redirect:/code/list");
		return modelAndView;
	}
	
	private ModelAndView addAndEditView(HttpServletRequest req,HttpServletResponse resp,String opType) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("codeUpdate");
		modelAndView.addObject("opType",opType);
		String codeId = req.getParameter("id");
		if(StringUtils.isBlank(codeId)){
			return modelAndView;
		}
		Integer codeIntId = Integer.parseInt(codeId);
		CodeCustom code = codeService.queryCodeById(codeIntId);
		modelAndView.addObject("code", code);
		return modelAndView;
	}
	private ModelAndView addAndEditRestView(Integer id,String opType) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("codeUpdate");
		modelAndView.addObject("opType",opType);
		if(null==id || id==0){
			return modelAndView;
		}
		Integer codeIntId = id;
		CodeCustom code = codeService.queryCodeById(codeIntId);
		modelAndView.addObject("code", code);
		return modelAndView;
	}
}
