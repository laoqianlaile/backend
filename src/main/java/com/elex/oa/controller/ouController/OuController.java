package com.elex.oa.controller.ouController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.ou.OuDep;
import com.elex.oa.entity.ou.OuPost;
import com.elex.oa.entity.ou.OuPostConditionInfo;
import com.elex.oa.service.ouService.IOuDepService;
import com.elex.oa.service.ouService.IOuPostService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\22 0022 14:54
 * @Version 1.0
 **/
@RequestMapping("/ou")
@CrossOrigin
@Controller
public class OuController {
    @Autowired
    private IOuDepService iOuDepService;
    @Autowired
    private IOuPostService iOuPostService;


    @RequestMapping("/dep/createDep")
    @ResponseBody
    public Object createDep(
            OuDep ouDep
    ){
        return iOuDepService.addOuDep(ouDep);
    }

    @RequestMapping("/post/createPost")
    @ResponseBody
    public Object createPost(
            OuPost ouPost
    ){
        return iOuPostService.addOuPost(ouPost);
    }

    @RequestMapping("/post/getPageOuPostList")
    @ResponseBody
    public PageInfo<OuPost> getPageOuPostList(
            @RequestParam("pageNum")Integer pageNum,
            @RequestParam("pageSize")Integer pageSize,
            OuPostConditionInfo ouPostConditionInfo
    ){
        return iOuPostService.getPageOuPostList(pageNum,pageSize,ouPostConditionInfo);
    }

    @RequestMapping("/post/getParamsOfOuPost")
    @ResponseBody
    public Object getParamsOfOuPost(){
        Map<String,Object> respMap = iOuPostService.getParamsOfOuPost();
        return respMap;
    }

    @RequestMapping("/post/validateByPostcode")
    @ResponseBody
    public Object validateByPostcode(
            String postcode
    ){
        return iOuPostService.validateByPostcode(postcode);
    }

    @RequestMapping("/post/getRecommendedOuPostcode")
    @ResponseBody
    public Object getRecommendedOuPostcode(){
        return iOuPostService.getRecommendedOuPostcode();
    }
}