package com.elex.oa.service.restructure_hrservice.impl;

import com.elex.oa.dao.hr.*;
import com.elex.oa.dao.restructure_hr.IPersonalinfoDao;
import com.elex.oa.entity.hr_entity.*;
import com.elex.oa.entity.hr_entity.costinformation.CostInformation;
import com.elex.oa.entity.hr_entity.manageinformation.ManageInformation;
import com.elex.oa.entity.hr_entity.personalinformation.BaseInformation;
import com.elex.oa.entity.hr_entity.personalinformation.OtherInformation;
import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformation;
import com.elex.oa.entity.hr_entity.personalinformation.User;
import com.elex.oa.entity.restructure_hrentity.Personalinfo;
import com.elex.oa.service.restructure_hrservice.IPersonalinfoService;
import com.elex.oa.util.hr_util.HrUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\29 0029 17:24
 * @Version 1.0
 **/
@Service
public class PersonalinfoServiceImpl implements IPersonalinfoService {
    @Resource
    IUserDao iUserDao;
    @Resource
    IPersonalInformationDao iPersonalInformationDao;
    @Resource
    IBaseInformationDao iBaseInformationDao;
    @Resource
    IManageInformationDao iManageInformationDao;
    @Resource
    ICostInformationDao iCostInformationDao;
    @Resource
    IOtherInformationDao iOtherInformationDao;
    @Resource
    IDimissionInformationDao iDimissionInformationDao;
    @Resource
    IPersonalinfoDao iPersonalinfoDao;
    @Resource
    HrUtils hrUtils;


    @Override
    public Boolean changeTable() {
        Boolean valBoolean = true;
        List<Personalinfo> personalinfoList = new ArrayList<>();
        List<User> userList = iUserDao.selectAll();
        for (User u:userList
             ) {
            Personalinfo personalinfo = new Personalinfo();
            //tb_id_user表数据的转移
            personalinfo = getPersonalinfoByUser(u, personalinfo);
            if(null==personalinfo){
                valBoolean = false;
                continue;
            }
            Personalinfo personalinfoTemp = iPersonalinfoDao.selectPersonalinfoByEmployeenumber(personalinfo.getEmployeenumber());
            //存在则更新数据，不存在则添加数据
            if(null==personalinfoTemp)iPersonalinfoDao.insert(personalinfo);
            if(null!=personalinfoTemp)iPersonalinfoDao.updateByPersonalinfo(personalinfo);
        }
        return valBoolean;
    }

    @Override
    public List<Map<String,String>> queryAllUsersByEmployeeON() {
        return iPersonalinfoDao.selectUsersByEmployeeON();
    }

    private Personalinfo getPersonalinfoByUser(User u, Personalinfo personalinfo) {
        if(null==u.getEmployeenumber())return null;
        personalinfo.setEmployeenumber(u.getEmployeenumber());
        personalinfo.setIsactive(String.valueOf(u.getIsactive()));
        personalinfo.setUsername(u.getUsername());
        personalinfo.setTruename(u.getTruename());
        personalinfo.setPassword(u.getPassword());
        personalinfo.setState(String.valueOf(u.getState()));
        //tb_id_personalinformation表数据的转移
        PersonalInformation personalInformation = iPersonalInformationDao.selectByEmployeenumber(u.getEmployeenumber());
        personalinfo = getPersonalinfoByPersonalinformation(personalinfo, personalInformation);
        //tb_hr_dimission表数据的转移
        personalinfo = getPersonalinfoByDimissioninformation(u, personalinfo);
        return personalinfo;
    }

    private Personalinfo getPersonalinfoByDimissioninformation(User u, Personalinfo personalinfo) {
        DimissionInformation dimissionInformation = iDimissionInformationDao.selectByUserid(u.getId());
        if(null==dimissionInformation)return personalinfo;
        personalinfo.setLastworkingdate(dimissionInformation.getLastworkingdate());
        personalinfo.setDimissiontypeid(hrUtils.getDatacodeByHrsetid(dimissionInformation.getDimissiontypeid()));
        personalinfo.setDimissiondirectionid(hrUtils.getDatacodeByHrsetid(dimissionInformation.getDimissiondirectionid()));
        personalinfo.setDimissionreasonid(hrUtils.getDatacodeByHrsetid(dimissionInformation.getDimissionreasonid()));
        personalinfo.setDimission_transaction_id(hrUtils.getEmployeenumberByUserid(u.getId()));
        personalinfo.setDimission_transaction_date(dimissionInformation.getTransactiondate());
        return personalinfo;
    }

    private Personalinfo getPersonalinfoByPersonalinformation(Personalinfo personalinfo, PersonalInformation personalInformation) {
        if(null==personalInformation)return personalinfo;
        personalinfo.setSex(personalInformation.getSex());
        //部门编号需转换
        personalinfo.setDepcode(hrUtils.getDepcodeByDepid(personalInformation.getDepid()));
        //办公电话需转换
        personalinfo.setTelphoneid(hrUtils.getDatacodeByHrsetid(personalInformation.getTelphoneid()));
        personalinfo.setMobilephone(personalInformation.getMobilephone());
        //tb_id_baseinformation表数据的转移
        BaseInformation baseInformation = iBaseInformationDao.selectById(personalInformation.getBaseinformationid());
        personalinfo = getPersonalinfoByBaseinformation(personalinfo, baseInformation);
        //tb_id_manageinformation表数据的转移
        ManageInformation manageInformation = iManageInformationDao.selectById(personalInformation.getManageinformationid());
        personalinfo = getPersonalinfoByManageinformation(personalinfo, manageInformation);
        //tb_id_costinformation表数据的转移
        CostInformation costInformation = iCostInformationDao.selectById(personalInformation.getCostinformationid());
        personalinfo = getPersonalinfoByCostinformation(personalinfo, costInformation);
        //tb_id_otherinformation表数据的转移
        OtherInformation otherInformation = iOtherInformationDao.selectById(personalInformation.getOtherinformationid());
        personalinfo = getPersonalinfoByOtherinformation(personalinfo, otherInformation);
        return personalinfo;
    }

    private Personalinfo getPersonalinfoByOtherinformation(Personalinfo personalinfo, OtherInformation otherInformation) {
        if(null==otherInformation)return null;
        personalinfo.setPrivateemail(otherInformation.getPrivateemail());
        personalinfo.setCompanyemail(otherInformation.getCompanyemail());
        personalinfo.setEmergencycontact(otherInformation.getEmergencycontract());
        personalinfo.setEmergencyrpid(hrUtils.getDatacodeByHrsetid(otherInformation.getEmergencyrpid()));
        personalinfo.setEmergencyphone(otherInformation.getEmergencyphone());
        personalinfo.setAddress(otherInformation.getAddress());
        personalinfo.setRemark(otherInformation.getRemark());
        return personalinfo;
    }

    private Personalinfo getPersonalinfoByCostinformation(Personalinfo personalinfo, CostInformation costInformation) {
        if(null==costInformation)return personalinfo;
        personalinfo.setSalarystandardid(hrUtils.getDatacodeByHrsetid(costInformation.getSalarystandardid()));
        personalinfo.setSsbid(hrUtils.getDatacodeByHrsetid(costInformation.getSsbid()));
        personalinfo.setSsbgscdid(hrUtils.getDatacodeByHrsetid(costInformation.getSsbgscdid()));
        personalinfo.setSsbgrcdid(hrUtils.getDatacodeByHrsetid(costInformation.getSsbgrcdid()));
        personalinfo.setGjjid(hrUtils.getDatacodeByHrsetid(costInformation.getGjjid()));
        personalinfo.setGjjgscdid(hrUtils.getDatacodeByHrsetid(costInformation.getGjjgscdid()));
        personalinfo.setGjjgrcdid(hrUtils.getDatacodeByHrsetid(costInformation.getGjjgrcdid()));
        personalinfo.setKhhid(hrUtils.getDatacodeByHrsetid(costInformation.getKhhid()));
        personalinfo.setSalaryaccount(hrUtils.getDatacodeByHrsetid(costInformation.getKhhid()));
        personalinfo.setSalaryaccount(costInformation.getSalaryaccount());
        personalinfo.setSbjndid(hrUtils.getDatacodeByHrsetid(costInformation.getSbjndid()));
        personalinfo.setSbcode(costInformation.getSbcode());
        personalinfo.setGjjcode(costInformation.getGjjcode());
        return personalinfo;
    }

    private Personalinfo getPersonalinfoByManageinformation(Personalinfo personalinfo, ManageInformation manageInformation) {
        if(null==manageInformation)return personalinfo;
        personalinfo.setEmployeetypeid(hrUtils.getDatacodeByHrsetid(manageInformation.getEmployeetypeid()));
        personalinfo.setEntrydate(manageInformation.getEntrydate());
        personalinfo.setZhuanzhengdate(manageInformation.getZhuanzhengdate());
        return personalinfo;
    }

    private Personalinfo getPersonalinfoByBaseinformation(Personalinfo personalinfo, BaseInformation baseInformation) {
        if(null==baseInformation)return personalinfo;
        personalinfo.setUserphoto(baseInformation.getUserphoto());
        personalinfo.setIdphoto1(baseInformation.getIdphoto1());
        personalinfo.setIdphoto2(baseInformation.getIdphoto2());
        personalinfo.setEnglishname(baseInformation.getEnglishname());
        personalinfo.setIdcode(baseInformation.getIdcode());
        personalinfo.setBirthday(baseInformation.getBirthday());
        personalinfo.setConstellation(baseInformation.getConstellation());
        personalinfo.setChinesecs(baseInformation.getChinesecs());
        personalinfo.setRaceid(hrUtils.getDatacodeByHrsetid(baseInformation.getRaceid()));
        personalinfo.setMarriage(baseInformation.getMarriage());
        personalinfo.setChildrenid(hrUtils.getDatacodeByHrsetid(baseInformation.getChildrenid()));
        personalinfo.setZzmmid(hrUtils.getDatacodeByHrsetid(baseInformation.getZzmmid()));
        personalinfo.setZgxlid(hrUtils.getDatacodeByHrsetid(baseInformation.getByyxid()));
        personalinfo.setSxzyid(hrUtils.getDatacodeByHrsetid(baseInformation.getSxzyid()));
        personalinfo.setPyfsid(hrUtils.getDatacodeByHrsetid(baseInformation.getPyfsid()));
        personalinfo.setFirstlaid(hrUtils.getDatacodeByHrsetid(baseInformation.getFirstlaid()));
        personalinfo.setElselaid(hrUtils.getDatacodeByHrsetid(baseInformation.getElselaid()));
        personalinfo.setPosttitleid(hrUtils.getDatacodeByHrsetid(baseInformation.getPosttitleid()));
        personalinfo.setZyzstypeid(hrUtils.getDatacodeByHrsetid(baseInformation.getZyzstypeid()));
        personalinfo.setZyzsnameid(hrUtils.getDatacodeByHrsetid(baseInformation.getZyzsnameid()));
        personalinfo.setParentcompanyid(hrUtils.getDatacodeByHrsetid(baseInformation.getParentcompanyid()));
        personalinfo.setFirstworkingtime(baseInformation.getFirstworkingtime());
        personalinfo.setHousehold_register(baseInformation.getHj());
        return personalinfo;
    }
}
