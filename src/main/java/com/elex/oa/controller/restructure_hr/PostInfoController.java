package com.elex.oa.controller.restructure_hr;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.entity.restructure_hrentity.Postinfo;
import com.elex.oa.entity.restructure_hrentity.Postloginfo;
import com.elex.oa.service.restructure_hrService.IPostinfoService;
import com.elex.oa.service.restructure_hrService.IPostloginfoService;
import com.elex.oa.util.hr_util.HrUtilsTemp;
import com.elex.oa.util.resp.RespUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\20 0020 16:37
 * @Version 1.0
 **/
@Controller
@CrossOrigin
@RequestMapping("/postinfo")
public class PostInfoController {
    @Autowired
    IPostinfoService iPostinfoService;
    @Autowired
    IPostloginfoService iPostloginfoService;
    @Autowired
    HrUtilsTemp hrUtilsTemp;

    @RequestMapping("/listPosts")
    @ResponseBody
    public Map<String,Object> listPosts(){
        return iPostinfoService.getPostTree();
    }

    @RequestMapping("/queryOnePostByPostcode")
    @ResponseBody
    public Postinfo queryOnePostByPostcode(
            @RequestParam("postcode")String postcode
    ){
        return iPostinfoService.queryOnePostByPostcode(postcode);
    }

    @RequestMapping("/queryPosts")
    @ResponseBody
    public List<Postinfo> queryPosts(){
        return iPostinfoService.queryPostinfoList();
    }

    @RequestMapping("/validateByPostcode")
    @ResponseBody
    public Object validateByPostcode(
            @RequestParam("postcode")String postcode
    ){
        Boolean aBoolean = iPostinfoService.validateByPostcode(postcode);
        return aBoolean? RespUtil.successResp("200","岗位编号已存在！",null):RespUtil.successResp("500","岗位编号不存在",null);
    }

    @RequestMapping("/addOnePost")
    @ResponseBody
    public Object addOnePost(
            HttpServletRequest request,
            Postinfo postinfo
    ){
        //获取岗位说明书的地址
        String dutyfile = hrUtilsTemp.getSignalFileAddress(request, "df", "/org/file/");
        postinfo.setDutyfile(dutyfile);
        //调用服务层
        Boolean aBoolean = iPostinfoService.addOnePost(postinfo);
        return aBoolean?RespUtil.successResp("200","添加成功！",null):RespUtil.successResp("500","添加失败！",null);
    }

    @RequestMapping("/queryPostsRemoveChilren")
    @ResponseBody
    public List<Map<String, String>> queryPostsRemoveChilren(
            @RequestParam("postcode")String postcode
    ){
        return iPostinfoService.queryPostsRemoveChilren(postcode);
    }

    @RequestMapping("/updateOnePost")
    @ResponseBody
    public Object updateOnePost(
            Postinfo postinfo,
            HttpServletRequest request,
            @RequestParam("transactorusername") String transactorusername
    ){
        String dutyfile = hrUtilsTemp.getSignalFileAddress(request, "df", "/org/file/");
        postinfo.setDutyfile(dutyfile);
        Boolean aBoolean = iPostinfoService.updateOnePost(postinfo,transactorusername);
        return aBoolean?RespUtil.successResp("200","修改成功！",null):RespUtil.successResp("500","修改失败！",null);
    }

    @RequestMapping("/deletePostsByPostcode")
    @ResponseBody
    public Object deletePostsByPostcode(
            @RequestParam("postcode")String postcode
    ){
        Boolean aBoolean = iPostinfoService.deletePostsByPostcode(postcode);
        return aBoolean?RespUtil.successResp("200","删除成功！",null):RespUtil.successResp("500","删除失败！",null);
    }

    @RequestMapping("/sortPostinformation")
    @ResponseBody
    public List<Map<String,Object>> sortPostinformation(
            @RequestParam("postcode")String postcode
    ){
        List<Map<String, Object>> respMap = iPostinfoService.getSortPostinformation(postcode);
        return respMap;
    }

    @RequestMapping("/submitSortdata")
    @ResponseBody
    public Map<String,Object> submitSortdata(
            @RequestParam("sortdata") String sortdata
    ){
        //将JSON字符串转换成List集合
        List<Map> respMap = JSONObject.parseArray(sortdata, Map.class);
        return iPostinfoService.submitSortdata(respMap);
    }

    @RequestMapping("/queryPostLogInformations")
    @ResponseBody
    public PageInfo<Postloginfo> queryPostLogInformations(
            @RequestParam("page")Integer page,
            @RequestParam("rows")Integer rows,
            Postloginfo postloginfo
    ){
        return iPostloginfoService.queryDeptLogInformations(page,rows,postloginfo);
    }
}