package com.zhuani21.blog.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuani21.blog.auto.bean.Code;
import com.zhuani21.blog.auto.bean.CodeExample;
import com.zhuani21.blog.auto.mapper.CodeMapper;
import com.zhuani21.blog.bean.CodeCustom;
import com.zhuani21.blog.exception.BlogBaseException;
import com.zhuani21.blog.service.CodeService;
import com.zhuani21.blog.util.BeanCopyUtils;
import com.zhuani21.blog.util.CollectionCheckUtils;
@Service("codeService")
public class CodeServiceImpl implements CodeService {
	@Autowired
	private CodeMapper codeMapper;
	@Override
	public List<CodeCustom> queryCodeList(String codeType) throws Exception {
		CodeExample codeExample = null;
		if (StringUtils.isNotBlank(codeType)) {
			codeExample = new CodeExample();
			codeExample.createCriteria().andTypeEqualTo(codeType);
		}
		List<Code> codeList = codeMapper.selectByExample(codeExample);
		List<CodeCustom> codeCustomList = BeanCopyUtils.getCustomBeanList(codeList, CodeCustom.class);
		return codeCustomList;
	}
	@Override
	public CodeCustom queryCodeById(Integer codeId)  {
		CodeCustom codeCustom = new CodeCustom();
		Code code = codeMapper.selectByPrimaryKey(codeId);
		BeanUtils.copyProperties(code, codeCustom);
		return codeCustom;
	}
	@Override
	public List<CodeCustom> queryBaseCodeTypeList() throws Exception {
		CodeExample codeExample = new CodeExample();
		codeExample.createCriteria().andTypeEqualTo(CodeCustom.BASE_CODE_TYPE);
		List<Code> codeList = codeMapper.selectByExample(codeExample);
		List<CodeCustom> baseCodeCustomList = BeanCopyUtils.getCustomBeanList(codeList, CodeCustom.class);
		return baseCodeCustomList;
	}
	@Override
	public void insertCode(Code code) {
		codeMapper.insert(code);
	}
	@Override
	public Code queryCodeByCodeType(String type) throws BlogBaseException {
		if(StringUtils.isBlank(type)){
			throw new BlogBaseException("类型不能为空");
		}
		CodeExample codeExample = new CodeExample();
		codeExample.createCriteria().andTypeEqualTo(CodeCustom.BASE_CODE_TYPE).andCodeEqualTo(type);
		List<Code> originCodeList = codeMapper.selectByExample(codeExample);
		if(CollectionCheckUtils.isNotBlankList(originCodeList)){
			return originCodeList.get(0);
		}
		return null;
	}
	@Override
	public void updateCode(Code code) {
		codeMapper.updateByPrimaryKey(code);
	}
	@Override
	public void deleteCodeById(Integer id) {
		codeMapper.deleteByPrimaryKey(id);
	}
}
