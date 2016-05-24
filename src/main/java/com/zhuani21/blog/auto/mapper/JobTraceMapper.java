package com.zhuani21.blog.auto.mapper;

import com.zhuani21.blog.auto.bean.JobTrace;
import com.zhuani21.blog.auto.bean.JobTraceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobTraceMapper {
    int countByExample(JobTraceExample example);

    int deleteByExample(JobTraceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JobTrace record);

    int insertSelective(JobTrace record);

    List<JobTrace> selectByExample(JobTraceExample example);

    JobTrace selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JobTrace record, @Param("example") JobTraceExample example);

    int updateByExample(@Param("record") JobTrace record, @Param("example") JobTraceExample example);

    int updateByPrimaryKeySelective(JobTrace record);

    int updateByPrimaryKey(JobTrace record);
}