package com.zhuani21.blog.service;

import java.util.List;

import com.zhuani21.blog.auto.bean.Code;
import com.zhuani21.blog.bean.CodeCustom;

public interface CodeService {
	public List<CodeCustom> queryCodeList(String codeType) throws Exception ;

	public CodeCustom queryCodeById(Integer integer) ;

	public List<CodeCustom> queryBaseCodeTypeList() throws Exception;

	public void insertCode(Code code) ;

	public Code queryCodeByCodeType(String type) throws Exception;

	public void updateCode(Code code);

	public void deleteCodeById(Integer id);
}
