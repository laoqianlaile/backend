package com.elex.oa.dao.objectives;

import com.elex.oa.entity.objectives.Goal2;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GrossDao {
    //根据年度查询销售毛利信息
    Goal2 queryData(Goal2 goal2);
    //删除销售毛利信息
    void deleteData(Goal2 goal2);
    //保存销售毛利信息
    void saveData(Goal2 goal2);
}
