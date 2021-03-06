package com.elex.oa.dao.reportForm;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LeaveDao {
    // 查询请假表单信息
    List getLeaveForm(
            @Param("name") String name,
            @Param("startTime") String startTime,
            @Param("endTime") String endTime,
            @Param("projectId") String projectId,
            @Param("category") String category,
            @Param("department") String department
    );

}
