package com.elex.oa.controller.oucontroller;

import com.alibaba.fastjson.JSON;
import com.elex.oa.entity.ou.OuDep;
import com.elex.oa.entity.ou.OuPost;
import com.elex.oa.entity.ou.OuPostConditionInfo;
import com.elex.oa.service.ou_service.IOuDepService;
import com.elex.oa.service.ou_service.IOuPostService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/dep/queryAllDepByDep_ON")
    @ResponseBody
    public Object queryAllDepByDepON(){
        return iOuDepService.queryAllDepByDepON();
    }

    @RequestMapping("/dep/createDep")
    @ResponseBody
    public Object createDep(
            OuDep ouDep,
            @RequestParam("username")String username
    ){
        return iOuDepService.addOuDep(ouDep);
    }

    @RequestMapping("/dep/deleteDeptsByDepcode")
    @ResponseBody
    public Object deleteDeptsByDepcode(
            @RequestParam("depcode")String depcode
    ){
        return iOuDepService.removeDeptsByDepcode(depcode);
    }

    @RequestMapping("/dep/updateOuDep")
    @ResponseBody
    public Object updateOuDep(
            OuDep ouDep,
            @RequestParam("username")String username
    ){
        return iOuDepService.modifyOuDep(ouDep,username);
    }

    @RequestMapping("/dep/listDepts")
    @ResponseBody
    public Object listDepts(){
        return iOuDepService.listDepts();
    }

    @RequestMapping("/dep/queryOneDepByDepcode")
    @ResponseBody
    public Object queryOneDepByDepcode(
            @RequestParam("code") String code
    ){
        return iOuDepService.queryOneDepByDepcode(code);
    }

    @RequestMapping("/dep/queryAllDepinfoButSelf")
    @ResponseBody
    public Object queryAllDepinfoButSelf(
            @RequestParam("depcode")String depcode
    ){
        return iOuDepService.queryAllDepinfoButSelf(depcode);
    }

    @RequestMapping("/dep/querySortdataByParentDepcode")
    @ResponseBody
    public Object querySortdataByParentDepcode(
            @RequestParam("parentDepcode")String parentDepcode
    ){
        return iOuDepService.querySortdataByParentDepcode(parentDepcode);
    }

    @RequestMapping("/dep/submitSortdata")
    @ResponseBody
    public Object submitSortdata(
            @RequestParam("sortdata")String jsonStr
    ){
        List<Map> sortData = JSON.parseArray(jsonStr, Map.class);
        return iOuDepService.submitSortdata(sortData);
    }

    @RequestMapping("/post/createPost")
    @ResponseBody
    public Object createPost(
            OuPost ouPost
    ){
        return iOuPostService.addOuPost(ouPost);
    }

    @RequestMapping("/post/updatePost")
    @ResponseBody
    public Object updatePost(
            OuPost ouPost
    ){
        return iOuPostService.updatePost(ouPost);
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
        return iOuPostService.getParamsOfOuPost();
    }

    @RequestMapping("/post/validateByPostcode")
    @ResponseBody
    public Object validateByPostcode(
            String postcode
    ){
        return iOuPostService.validateByPostcode(postcode);
    }

    @RequestMapping("/post/validateByPostcodeButSelf")
    @ResponseBody
    public Object validateByPostcodeButSelf(
            String postcode,
            String postid
    ){
        return iOuPostService.validateByPostcodeButSelf(postcode,postid);
    }

    @RequestMapping("/post/getRecommendedOuPostcode")
    @ResponseBody
    public Object getRecommendedOuPostcode(){
        return iOuPostService.getRecommendedOuPostcode();
    }

    @RequestMapping("/post/queryAllPostcode_ON")
    @ResponseBody
    public Object queryAllPostcodeON(){
        return iOuPostService.queryAllPostcodeON();
    }

    @RequestMapping("/post/changeOuPostState")
    @ResponseBody
    public Object changeOuPostState(
            @RequestParam("flag")String flag,
            @RequestParam("postIdList")String postIdListOfString
    ){
        List<String> postIdList = JSON.parseArray(postIdListOfString, String.class);
        return iOuPostService.changeOuPostState(flag,postIdList);
    }
}
