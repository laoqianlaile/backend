package com.elex.oa.dao.business;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.business.Clue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IClueDao extends BaseDao<Clue> {
    List<Clue> selectByCondition(Clue clue);
}
