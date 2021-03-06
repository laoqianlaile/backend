package com.elex.oa.dao.ou;

import com.elex.oa.dao.BaseDao;
import com.elex.oa.entity.ou.OuPost;
import com.elex.oa.entity.ou.OuPostConditionInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OuPostDao extends BaseDao<OuPost> {
    List<OuPost> selectPageOuPostList(OuPostConditionInfo ouPostConditionInfo);

    List<Map<String,Object>> selectParamsOfOuPost();
}
