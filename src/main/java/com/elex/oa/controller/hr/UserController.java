package com.elex.oa.controller.hr;

import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformation;
import com.elex.oa.entity.hr_entity.personalinformation.User;
import com.elex.oa.service.hr_service.IDeptService;
import com.elex.oa.service.hr_service.IPersonalInformationService;
import com.elex.oa.service.hr_service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:用户
 * @Date:Created in  11:57 2018\4\12 0012
 * @Modify By:
 */
@Controller
@CrossOrigin
public class UserController {
    @Autowired
    IUserService iUserService;
    @Autowired
    IPersonalInformationService iPersonalInformationService;
    @Autowired
    IDeptService iDeptService;

    @RequestMapping("/updateEmployeenumber")
    @ResponseBody
    public String updateEmployeenumber(){
        Boolean aBoolean = true;
        List<PersonalInformation> personalInformations = iPersonalInformationService.queryAllByNull();
        for (PersonalInformation per:personalInformations){
            aBoolean = iUserService.modifyUser(new User(per.getUserid(), per.getEmployeenumber()));
        }
        return aBoolean?"Success":"Error";
    }

    @RequestMapping("/queryUserByUserId")
    @ResponseBody
    public User queryUserByUserId(
            @RequestParam("userid") Integer userid
    ){
        return iUserService.getById(userid);
    }

    @RequestMapping("/queryAllUsers")
    @ResponseBody
    public List<User> queryAllUsers(){
        return iUserService.selectAllOrderByDictionary();
    }

    @RequestMapping("/queryAllServings")
    @ResponseBody
    public List<Map> queryAllServings(){
        return iUserService.queryAllServings();
    }

    @RequestMapping("/user/queryEmployeenumberByUsername_ON")
    @ResponseBody
    public Object queryEmployeenumberByUsernameOn(
            @RequestParam("username")String username
    ){
        return iUserService.queryEmployeenumberByUsernameON(username);
    }

    @RequestMapping("/queryUserListOfBusiness")
    @ResponseBody
    public Object queryUserListOfBusiness(
            @RequestParam("range")String range,
            @RequestParam(name = "username",required = false)String username
    ){
       return iUserService.queryUserListOfBusiness(range,username);
    }

}
