package com.elex.oa.service.ouService.Impl;

import com.elex.oa.common.hr.Commons;
import com.elex.oa.dao.ou.OuDepDao;
import com.elex.oa.dao.ou.OuPostDao;
import com.elex.oa.entity.ou.OuPost;
import com.elex.oa.entity.ou.OuPostConditionInfo;
import com.elex.oa.service.ouService.IOuPostService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\22 0022 15:17
 * @Version 1.0
 **/
@Service
public class OuPostServiceImpl implements IOuPostService {
    @Resource
    private OuPostDao ouPostDao;
    @Resource
    private HrUtils hrUtils;

    @Override
    public Object addOuPost(OuPost ouPost) {
        //先判断岗位编号
        if(StringUtils.isBlank(ouPost.getPostcode()))return RespUtil.successResp("500","岗位编号不能为空",null);
        List<OuPost> ouPostListTemp = ouPostDao.select(new OuPost(ouPost.getPostcode()));
        if(ouPostListTemp.size()>0)return RespUtil.successResp("500","岗位编号已存在",null);
        if(StringUtils.isBlank(ouPost.getPostname()))return RespUtil.successResp("500","岗位名称不能为空",null);
        if(null==ouPost.getPostlevelid())return RespUtil.successResp("500","岗级不能为空",null);
        ouPost.setId("post_"+System.currentTimeMillis());
        ouPost.setState(Commons.POST_ON);
        try {
            ouPostDao.insertSelective(ouPost);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("500","岗位添加失败",null);
        }
        return RespUtil.successResp("200","岗位添加成功",null);
    }

    @Override
    public PageInfo<OuPost> getPageOuPostList(Integer pageNum, Integer pageSize, OuPostConditionInfo ouPostConditionInfo) {
        PageHelper.startPage(pageNum,pageSize);
        List<OuPost> ouPostList = ouPostDao.selectPageOuPostList(ouPostConditionInfo);
        return new PageInfo<>(ouPostList);
    }

    @Override
    public Map<String,Object> getParamsOfOuPost() {
        List<Map<String, Object>> postList = ouPostDao.selectParamsOfOuPost();
        List<String> postcodeList = new ArrayList<>();
        List<String> postnameList = new ArrayList<>();
        List<String> postlevelList = new ArrayList<>();
        List<String> stateList = new ArrayList<>();
        Map<String,Object> respMap = new HashMap<>();
        for (Map<String,Object> post:postList
             ) {
            postcodeList.add((String) post.get("postcode"));
            postnameList.add((String) post.get("postname"));
            postlevelList.add((String) post.get("postlevel"));
            stateList.add((String) post.get("state"));
        }
        postcodeList = hrUtils.removeDeplication(postcodeList);
        postnameList = hrUtils.removeDeplication(postnameList);
        postlevelList = hrUtils.removeDeplication(postlevelList);
        stateList = hrUtils.removeDeplication(stateList);
        respMap.put("postcodeList",postcodeList);
        respMap.put("postnameList",postnameList);
        respMap.put("postlevelList",postlevelList);
        respMap.put("stateList",stateList);
        return respMap;
    }

    @Override
    public Object validateByPostcode(String postcode) {
        List<OuPost> ouPostList = ouPostDao.select(new OuPost(postcode));
        if(null==ouPostList || ouPostList.size()==0)return RespUtil.successResp("500","根据岗位编号查询岗位失败！",null);
        return RespUtil.successResp("200","根据岗位编号查询岗位成功！",getDetailOuPostByCursoryOuPost(ouPostList.get(0)));
    }

    @Override
    public Object getRecommendedOuPostcode() {
        int i =1;
        String postcode = "";
        while (true){
            if(i<10)postcode="00000"+i;
            if(i>9 && i<100)postcode="0000"+i;
            if(i>99 && i<1000)postcode="000"+i;
            if(i>999 && i<10000)postcode= "00"+i;
            if(i>9999 && i<100000)postcode= "0"+i;
            if(i>99999)postcode= ""+i;
            List<OuPost> ouPostList = null;
            try {
                ouPostList = ouPostDao.select(new OuPost(postcode));
            } catch (Exception e) {
                e.printStackTrace();
                return RespUtil.successResp("500","查询失败",e.getStackTrace());
            }
            if(null==ouPostList || ouPostList.size()==0)return RespUtil.successResp("200","查询成功！",postcode);
            i++;
        }
    }


    //根据摘要信息获得详细信息
    private OuPost getDetailOuPostByCursoryOuPost(OuPost ouPost){
        if(null==ouPost)return null;
        ouPost.setPostlevel(hrUtils.getDatavalueByHrsetid(ouPost.getPostlevelid()));
        return ouPost;
    }

}