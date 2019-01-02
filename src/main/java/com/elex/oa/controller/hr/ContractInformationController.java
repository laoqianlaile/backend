package com.elex.oa.controller.hr;

import com.alibaba.fastjson.JSON;
import com.elex.oa.common.hr.Commons;
import com.elex.oa.entity.hr_entity.*;
import com.elex.oa.service.hr_service.*;
import com.elex.oa.util.hr_util.IDcodeUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:ShiYun;
 * @Description:合同信息
 * @Date:Created in  16:07 2018\4\9 0009
 * @Modify By:
 */
@Controller
@CrossOrigin
public class ContractInformationController {

    @Autowired
    IContractInformationService iContractInformationService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IPersonalInformationService iPersonalInformationService;
    @Autowired
    IRenewContractRecordService iRenewContractRecordService;
    @Autowired
    IHRsetService ihRsetService;

    /**
     * @Author:ShiYun;
     * @Description:合同信息的查询
     * @Date: 16:08 2018\4\9 0009
     */
    @RequestMapping("/queryContractInformations")
    @ResponseBody
    public PageInfo<ContractInformation> queryContractInformations(
            @RequestParam("page") int page,
            @RequestParam("rows") int rows,
            ContractInformation contractInformation
    ) throws ParseException {
        HashMap<String, Object> paraMap = new HashMap<>();
        paraMap.put("pageNum", page);
        paraMap.put("pageSize", rows);
        paraMap.put("entity", contractInformation);

        PageInfo<ContractInformation> contractInformationPageInfo = iContractInformationService.queryByConditions(paraMap);
        List<ContractInformation> list = contractInformationPageInfo.getList();
        if (list.size() == 0) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            // 获得工号
            Integer userid = list.get(i).getUserid();
            PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(userid);
            list.get(i).setEmployeenumber(personalInformation.getEmployeenumber());
            // 获得姓名
            User user = iUserService.getById(userid);
            list.get(i).setTruename(user.getTruename());
            //获得合同类型
            HRset hRsetContracttype = ihRsetService.queryById(list.get(i).getContracttypeid());
            if (hRsetContracttype != null) {
                list.get(i).setContracttype(hRsetContracttype.getDatavalue());
            }
            // 获得合同年限
            /*list.get(i).setContractage(IDcodeUtil.getContractage(list.get(i).getStartdate(),list.get(i).getEnddate()));*/
        }
        contractInformationPageInfo.setList(list);

        return contractInformationPageInfo;
    }

    /**
     * @Author:ShiYun;
     * @Description:根据userid查询合同信息
     * @Date: 16:05 2018\5\30 0030
     */
    @RequestMapping("/queryContractsByUserid")
    @ResponseBody
    public List<ContractInformation> queryContractsByUserid(
            @RequestParam("personalInformationId") Integer personalInformationId
    ) {
        List<ContractInformation> contractInformationList = null;
        if (iPersonalInformationService.queryOneById(personalInformationId) != null) {
            contractInformationList = iContractInformationService.queryByUserid(iPersonalInformationService.queryOneById(personalInformationId).getUserid());
        } else {
            return null;
        }
        return contractInformationList;
    }

    /**
     * @Author:ShiYun;
     * @Description:查询合同续签记录
     * @Date: 13:29 2018\4\10 0010
     */
    @RequestMapping("/queryOneContractInformation")
    @ResponseBody
    public List<RenewContractRecord> queryOneContractInformation(@RequestParam("contractId") int contractId) {
        List<RenewContractRecord> renewContractRecords = iRenewContractRecordService.queryRenewContractRecordsByContractId(contractId);
        for (int i = 0; i < renewContractRecords.size(); i++) {
            User user = iUserService.getById(renewContractRecords.get(i).getZhbgruserid());
            if (user != null) {
                renewContractRecords.get(i).setZhbgrtruename(user.getTruename());
            }
        }
        return renewContractRecords;
    }

    /**
     * @Author:ShiYun;
     * @Description:根据ID查询合同信息
     * @Date: 17:21 2018\4\12 0012
     */
    @RequestMapping("/queryContractsById")
    @ResponseBody
    public ContractInformation queryContractsById(
            @RequestParam("contractid") Integer contractid
    ) {
        ContractInformation contractInformation = iContractInformationService.queryById(contractid);
        return contractInformation;
    }

    /**
     * @Author:ShiYun;
     * @Description:添加合同信息
     * @Date: 14:16 2018\4\20 0020
     */
    @RequestMapping("/addContractInformation")
    @ResponseBody
    public String addContractInformation(
            ContractInformation contractInformation,
            @RequestParam("transactorusername") String transactorusername,
            HttpServletRequest request
    ) throws IOException, ParseException {
        try {
            //获得办理人ID
            User user = new User();
            user.setUsername(transactorusername);
            contractInformation.setTransactoruserid(iUserService.selectByCondition(user).get(0).getId());
            //获得附件
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> files = multipartHttpServletRequest.getFiles("attfile");
            if (files.size() != 0) {
                String realPath = Commons.realpath + "/hr/file";
                Long l = Calendar.getInstance().getTimeInMillis();
                File file = new File(realPath + "/" + l);
                file.mkdirs();
                String attachment = "/hr/file/" + l + "/" + files.get(0).getOriginalFilename();
                files.get(0).transferTo(new File(realPath + "/" + l, files.get(0).getOriginalFilename()));
                contractInformation.setAttachment(attachment);
            }
            //获得办理日期
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String transdate = simpleDateFormat.format(new Date());
            contractInformation.setTransdate(transdate);

            //将合同添加到数据库中
            Integer contractInformaionId = iContractInformationService.addOne(contractInformation);
        } catch (IOException e) {
            e.printStackTrace();
            return "添加失败！";
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "添加失败！";
        } catch (ParseException e) {
            e.printStackTrace();
            return "添加失败！";
        }
        return "添加成功！";
    }

    /**
     * @Author:ShiYun;
     * @Description:修改合同信息
     * @Date: 9:59 2018\5\29 0029
     */
    @RequestMapping("/updateContractInformation")
    @ResponseBody
    public String updateContractInformation(
            ContractInformation contractInformation,
            @RequestParam("transactorusername") String transactorusername,
            HttpServletRequest request
    ) throws IOException, ParseException {
        Boolean b = false;
        ContractInformation c2 = iContractInformationService.queryById(contractInformation.getId());
        //获得办理人ID
        User user = new User();
        user.setUsername(transactorusername);
        if (iUserService.selectByCondition(user).get(0) != null && iUserService.selectByCondition(user).get(0).getId() != c2.getTransactoruserid()) {
            contractInformation.setTransactoruserid(iUserService.selectByCondition(user).get(0).getId());
            b = true;
        }
        //获得附件
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartHttpServletRequest.getFiles("attfile");
        if (files.size() != 0) {
            String realPath = Commons.realpath + "/hr/file";
            Long l = Calendar.getInstance().getTimeInMillis();
            File file = new File(realPath + "/" + l);
            file.mkdirs();
            String attachment = "/hr/file/" + l + "/" + files.get(0).getOriginalFilename();
            files.get(0).transferTo(new File(realPath + "/" + l, files.get(0).getOriginalFilename()));
            contractInformation.setAttachment(attachment);
            b = true;
        }
        if (!c2.getContractcode().equals(contractInformation.getContractcode()) ||
                !c2.getStartdate().equals(contractInformation.getStartdate()) ||
                !c2.getEnddate().equals(contractInformation.getEnddate()) ||
                c2.getContracttypeid() != contractInformation.getContracttypeid() ||
                !c2.getRemark().equals(contractInformation.getRemark())) {
            b = true;
        }
        //获得办理日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String transdate = simpleDateFormat.format(new Date());
        if (b) {
            contractInformation.setTransdate(transdate);
        }

        //将合同提交到数据库中
        if (b) {
            iContractInformationService.modifyOne(contractInformation);
        }
        if (b) {
            return "提交成功！";
        } else {
            return "请修改数据后再提交！";
        }
    }

    /**
     * @Author:ShiYun;
     * @Description:查询所有合同信息（不分页）
     * @Date: 16:01 2018\5\25 0025
     */
    @RequestMapping("/queryAllContractInformations")
    @ResponseBody
    public List<ContractInformation> queryAllContractInformations() throws ParseException {
        ContractInformation contractInformation = new ContractInformation();
        List<ContractInformation> contractInformationList = iContractInformationService.queryAll(contractInformation);
        for (ContractInformation contractInformation1 : contractInformationList) {
            if (iUserService.getById(contractInformation1.getUserid()) != null) {
                contractInformation1.setTruename(iUserService.getById(contractInformation1.getUserid()).getTruename());
            }
            if (iPersonalInformationService.queryOneByUserid(contractInformation1.getUserid()) != null) {
                contractInformation1.setEmployeenumber(iPersonalInformationService.queryOneByUserid(contractInformation1.getUserid()).getEmployeenumber());
            }
            HRset hRsetContracttype = ihRsetService.queryById(contractInformation.getContracttypeid());
            if (hRsetContracttype != null) {
                contractInformation1.setContracttype(hRsetContracttype.getDatavalue());
            }
            try {
                contractInformation1.setContractage(IDcodeUtil.getContractage(contractInformation1.getStartdate(), contractInformation1.getEnddate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (iUserService.getById(contractInformation1.getTransactoruserid()) != null) {
                contractInformation1.setTransactortruename(iUserService.getById(contractInformation1.getTransactoruserid()).getTruename());
            }
        }
        return contractInformationList;
    }

    /**
     * @Author:ShiYun;
     * @Description:根据ID删除合同信息
     * @Date: 10:40 2018\5\29 0029
     */
    @RequestMapping("/deleteContractsByIds")
    @ResponseBody
    public String deleteContractsByIds(
            @RequestParam("contractids") List<Integer> contractids
    ) {
        for (int i = 0; i < contractids.size(); i++) {
            iContractInformationService.removeOne(contractids.get(i));
        }
        return "删除成功！";
    }

    /**
     * @Author:ShiYun;
     * @Description:导入合同信息
     * @Date: 11:04 2018\5\29 0029
     */
    @RequestMapping("/importContractinformations")
    @ResponseBody
    public String importContractinformations(
            @RequestParam("file") MultipartFile multipartFile
    ) {
        Map<String, String> responseMap = new HashMap<>();
        try {
            ReadContractExcel readContractExcel = new ReadContractExcel();
            List<ContractInformation> excelInfo = readContractExcel.getExcelInfo(multipartFile);
            for (ContractInformation contractInformation : excelInfo) {
                //合同编号为必填项
                if (StringUtils.isEmpty(contractInformation.getContractcode())) continue;
                //姓名为必填项
                if (StringUtils.isEmpty(contractInformation.getTruename())) {
                    responseMap.put("合同编号[" + contractInformation.getContractcode() + "]", "姓名为空");
                    continue;
                }
                //姓名为必填项
                if (null != iUserService.queryByTruename(contractInformation.getTruename())) {
                    contractInformation.setUserid(iUserService.queryByUsername(contractInformation.getTruename()).getId());
                } else {
                    responseMap.put("合同编号[" + contractInformation.getContractcode() + "]，", "所对应的姓名不存在:亲，请先在人力资源-->人事档案-->人事信息中导入人事信息！");
                    continue;
                }
                //根据合同编号查询合同，有则更新，没有则添加
                ContractInformation contractcodeInstance = iContractInformationService.queryByContractcode(contractInformation.getContractcode());
                if (null==contractcodeInstance) {
                    addContractcode(contractInformation);
                } else {
                    contractInformation.setId(contractcodeInstance.getId());
                    updateContractcode(contractInformation);
                }
            }
        } catch (Exception e) {
            return "导入失败！";
        }
        return responseMap.size()==0?"导入成功！":JSON.toJSONString(responseMap);
    }

    private void updateContractcode(ContractInformation contractInformation) throws ParseException {
        List<HRset> hRsetContracttypeList = ihRsetService.queryByConditions(new HRset(Commons.HRSET_CONTRACTTYPE, contractInformation.getContracttype()));
        Integer contracttypeid = null;
        if (null != hRsetContracttypeList && hRsetContracttypeList.size() == 1) {
            contracttypeid = hRsetContracttypeList.get(0).getId();
        }else if(null==hRsetContracttypeList || hRsetContracttypeList.size()==0){
            contracttypeid = ihRsetService.addOne(new HRset(Commons.HRSET_CONTRACTTYPE, contractInformation.getContracttype()));
        }
        contractInformation.setContracttypeid(contracttypeid);
        //办理人不存在则添加默认值（管理员-->实际情况不能这么搞）
        if (StringUtils.isEmpty(contractInformation.getTransactortruename()))
            contractInformation.setTransactortruename("admin");
        User transactor = iUserService.queryByUsername(contractInformation.getTransactortruename());
        if (transactor != null) {
            contractInformation.setTransactoruserid(transactor.getId());
        }
        //办理日期不存在则添加默认值（入职时间-->实际情况不能这么搞）
        if (StringUtils.isEmpty(contractInformation.getTransdate()))
            contractInformation.setTransdate(contractInformation.getStartdate());
        //合同结束时间不存在则添加默认值（开始时间-->实际情况不能这么搞）
        iContractInformationService.modifyOne(contractInformation);
    }

    private void addContractcode(ContractInformation contractInformation) throws ParseException {
        List<HRset> hRsetContracttypeList = ihRsetService.queryByConditions(new HRset(Commons.HRSET_CONTRACTTYPE, contractInformation.getContracttype()));
        Integer contracttypeid = null;
        if (null != hRsetContracttypeList && hRsetContracttypeList.size() == 1) {
            contracttypeid = hRsetContracttypeList.get(0).getId();
        }else if(null==hRsetContracttypeList || hRsetContracttypeList.size()==0){
            contracttypeid = ihRsetService.addOne(new HRset(Commons.HRSET_CONTRACTTYPE, contractInformation.getContracttype()));
        }
        contractInformation.setContracttypeid(contracttypeid);
        //办理人不存在则添加默认值（管理员-->实际情况不能这么搞）
        if (StringUtils.isEmpty(contractInformation.getTransactortruename()))
            contractInformation.setTransactortruename("admin");
        User transactor = iUserService.queryByUsername(contractInformation.getTransactortruename());
        if (transactor != null) {
            contractInformation.setTransactoruserid(transactor.getId());
        }
        //办理日期不存在则添加默认值（入职时间-->实际情况不能这么搞）
        if (StringUtils.isEmpty(contractInformation.getTransdate()))
            contractInformation.setTransdate(contractInformation.getStartdate());
        //合同结束时间不存在则添加默认值（开始时间-->实际情况不能这么搞）
        iContractInformationService.addOne(contractInformation);
    }
}
