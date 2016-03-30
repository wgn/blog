package com.zhuani21.blog.auto.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.zhuani21.blog.auto.bean.LoginAuth;
import com.zhuani21.blog.auto.bean.LoginAuthExample;

public interface LoginAuthMapper {
    int countByExample(LoginAuthExample example);

    int deleteByExample(LoginAuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LoginAuth record);

    int insertSelective(LoginAuth record);

    List<LoginAuth> selectByExample(LoginAuthExample example);

    LoginAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LoginAuth record, @Param("example") LoginAuthExample example);

    int updateByExample(@Param("record") LoginAuth record, @Param("example") LoginAuthExample example);

    int updateByPrimaryKeySelective(LoginAuth record);

    int updateByPrimaryKey(LoginAuth record);
}