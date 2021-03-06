package com.elex.oa.entity.hr_entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:离职信息
 * @Date:Created in  15:46 2018\4\13 0013
 * @Modify By:
 */
@Table(name = "tb_hr_dimission")
public class DimissionInformation  implements Serializable {

    private static final long serialVersionUID = -7386971547883517342L;

    @Id
    private Integer id;// 主键
    private Integer dimissionuserid;// 离职人员的ID
    @Transient
    private List<Integer> userids;//判断条件
    private String lastworkingdate;// 最后工作日
    @Transient
    private String lastworkingdatevalue1;//判断条件
    @Transient
    private String lastworkingdatevalue2;//判断条件
    @Transient
    private String dimissiontype;// 离职类型
    @Transient
    private List<String> dimissiontypes;
    private Integer dimissiontypeid;//离职类型ID
    @Transient
    private String dimissiontypevalue;//判断条件
    @Transient
    private String dimissionreason;// 离职原因
    @Transient
    private List<String> dimissionreasons;
    private Integer dimissionreasonid;//离职原因ID
    @Transient
    private String dimissionreasonvalue;//判断条件
    @Transient
    private String transactorusername;//办理人的登录ID
    private Integer transactoruserid;// 办理人的ID
    private String transactiondate;// 办理日期
    @Transient
    private String transactiondatevalue1;//判断条件
    @Transient
    private String transactiondatevalue2;//判断条件
    @Transient
    private String dimissiondirection;// 离职去向
    @Transient
    private List<String> dimissiondirections;
    private Integer dimissiondirectionid;//离职去向ID
    @Transient
    private String dimissiondirectionvalue;//判断条件
    @Transient
    private String dimissiontruename;// 离职人员的姓名
    @Transient
    private List<String> dimissiontruenames;
    @Transient
    private String dimissiontruenamevalue;//判断条件
    @Transient
    private String employeenumber;// 离职人员的工号
    @Transient
    private List<String> employeenumbers;
    @Transient
    private String employeenumbervalue;//判断条件
    @Transient
    private String depname;// 离职人员的部门名称
    @Transient
    private List<String> depnames;
    @Transient
    private String depnamevalue;//判断条件
    @Transient
    private String postnames;// 离职人员的岗位名称
    @Transient
    private String postname;//判断条件
    @Transient
    private List<String> postnameList;
    @Transient
    private String postnamevalue;//判断条件
    @Transient
    private List<Integer> perids;//判断条件
    @Transient
    private String transactortruename;// 办理人的姓名
    @Transient
    private List<String> transactortruenames;
    @Transient
    private String transactortruenamevalue;//判断条件

    private Integer approvalnumbers;// 待审批单的数量
    private Integer approvalstatue;// 待审批单是否确认处理
    private Integer workingnumbers;// 代办任务的数量
    private Integer workingstatue;// 代办任务是否确认处理
    private Integer filenumbers;// 文件占用数量
    private Integer filestatue;// 文件占用是否确认处理
    private Integer officesupplynumbers;// 办公用品领用数量
    private Integer officesupplystatue;// 办公用品领用是否确认处理

    public DimissionInformation() {
    }

    public DimissionInformation(Integer dimissionuserid) {
        this.dimissionuserid = dimissionuserid;
    }

    public String getTransactorusername() {
        return transactorusername;
    }

    public void setTransactorusername(String transactorusername) {
        this.transactorusername = transactorusername;
    }

    public List<String> getDimissiontypes() {
        return dimissiontypes;
    }

    public void setDimissiontypes(List<String> dimissiontypes) {
        this.dimissiontypes = dimissiontypes;
    }

    public List<String> getDimissionreasons() {
        return dimissionreasons;
    }

    public void setDimissionreasons(List<String> dimissionreasons) {
        this.dimissionreasons = dimissionreasons;
    }

    public List<String> getDimissiondirections() {
        return dimissiondirections;
    }

    public void setDimissiondirections(List<String> dimissiondirections) {
        this.dimissiondirections = dimissiondirections;
    }

    public List<String> getDimissiontruenames() {
        return dimissiontruenames;
    }

    public void setDimissiontruenames(List<String> dimissiontruenames) {
        this.dimissiontruenames = dimissiontruenames;
    }

    public List<String> getEmployeenumbers() {
        return employeenumbers;
    }

    public void setEmployeenumbers(List<String> employeenumbers) {
        this.employeenumbers = employeenumbers;
    }

    public List<String> getDepnames() {
        return depnames;
    }

    public void setDepnames(List<String> depnames) {
        this.depnames = depnames;
    }

    public List<String> getPostnameList() {
        return postnameList;
    }

    public void setPostnameList(List<String> postnameList) {
        this.postnameList = postnameList;
    }

    public List<String> getTransactortruenames() {
        return transactortruenames;
    }

    public void setTransactortruenames(List<String> transactortruenames) {
        this.transactortruenames = transactortruenames;
    }

    public Integer getDimissiontypeid() {
        return dimissiontypeid;
    }

    public void setDimissiontypeid(Integer dimissiontypeid) {
        this.dimissiontypeid = dimissiontypeid;
    }

    public Integer getDimissionreasonid() {
        return dimissionreasonid;
    }

    public void setDimissionreasonid(Integer dimissionreasonid) {
        this.dimissionreasonid = dimissionreasonid;
    }

    public Integer getDimissiondirectionid() {
        return dimissiondirectionid;
    }

    public void setDimissiondirectionid(Integer dimissiondirectionid) {
        this.dimissiondirectionid = dimissiondirectionid;
    }

    public List<Integer> getUserids() {
        return userids;
    }

    public void setUserids(List<Integer> userids) {
        this.userids = userids;
    }

    public List<Integer> getPerids() {
        return perids;
    }

    public void setPerids(List<Integer> perids) {
        this.perids = perids;
    }

    public String getLastworkingdatevalue1() {
        return lastworkingdatevalue1;
    }

    public void setLastworkingdatevalue1(String lastworkingdatevalue1) {
        this.lastworkingdatevalue1 = lastworkingdatevalue1;
    }

    public String getLastworkingdatevalue2() {
        return lastworkingdatevalue2;
    }

    public void setLastworkingdatevalue2(String lastworkingdatevalue2) {
        this.lastworkingdatevalue2 = lastworkingdatevalue2;
    }

    public String getDimissiontypevalue() {
        return dimissiontypevalue;
    }

    public void setDimissiontypevalue(String dimissiontypevalue) {
        this.dimissiontypevalue = dimissiontypevalue;
    }

    public String getDimissionreasonvalue() {
        return dimissionreasonvalue;
    }

    public void setDimissionreasonvalue(String dimissionreasonvalue) {
        this.dimissionreasonvalue = dimissionreasonvalue;
    }

    public String getTransactiondatevalue1() {
        return transactiondatevalue1;
    }

    public void setTransactiondatevalue1(String transactiondatevalue1) {
        this.transactiondatevalue1 = transactiondatevalue1;
    }

    public String getTransactiondatevalue2() {
        return transactiondatevalue2;
    }

    public void setTransactiondatevalue2(String transactiondatevalue2) {
        this.transactiondatevalue2 = transactiondatevalue2;
    }

    public String getDimissiondirectionvalue() {
        return dimissiondirectionvalue;
    }

    public void setDimissiondirectionvalue(String dimissiondirectionvalue) {
        this.dimissiondirectionvalue = dimissiondirectionvalue;
    }

    public String getDimissiontruenamevalue() {
        return dimissiontruenamevalue;
    }

    public void setDimissiontruenamevalue(String dimissiontruenamevalue) {
        this.dimissiontruenamevalue = dimissiontruenamevalue;
    }

    public String getEmployeenumbervalue() {
        return employeenumbervalue;
    }

    public void setEmployeenumbervalue(String employeenumbervalue) {
        this.employeenumbervalue = employeenumbervalue;
    }

    public String getDepnamevalue() {
        return depnamevalue;
    }

    public void setDepnamevalue(String depnamevalue) {
        this.depnamevalue = depnamevalue;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getPostnamevalue() {
        return postnamevalue;
    }

    public void setPostnamevalue(String postnamevalue) {
        this.postnamevalue = postnamevalue;
    }

    public String getTransactortruenamevalue() {
        return transactortruenamevalue;
    }

    public void setTransactortruenamevalue(String transactortruenamevalue) {
        this.transactortruenamevalue = transactortruenamevalue;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDimissionuserid() {
        return dimissionuserid;
    }

    public void setDimissionuserid(Integer dimissionuserid) {
        this.dimissionuserid = dimissionuserid;
    }

    public String getLastworkingdate() {
        return lastworkingdate;
    }

    public void setLastworkingdate(String lastworkingdate) {
        this.lastworkingdate = lastworkingdate;
    }

    public String getDimissiontype() {
        return dimissiontype;
    }

    public void setDimissiontype(String dimissiontype) {
        this.dimissiontype = dimissiontype;
    }

    public String getDimissionreason() {
        return dimissionreason;
    }

    public void setDimissionreason(String dimissionreason) {
        this.dimissionreason = dimissionreason;
    }

    public Integer getTransactoruserid() {
        return transactoruserid;
    }

    public void setTransactoruserid(Integer transactoruserid) {
        this.transactoruserid = transactoruserid;
    }

    public String getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(String transactiondate) {
        this.transactiondate = transactiondate;
    }

    public String getDimissiondirection() {
        return dimissiondirection;
    }

    public void setDimissiondirection(String dimissiondirection) {
        this.dimissiondirection = dimissiondirection;
    }

    public String getDimissiontruename() {
        return dimissiontruename;
    }

    public void setDimissiontruename(String dimissiontruename) {
        this.dimissiontruename = dimissiontruename;
    }

    public String getEmployeenumber() {
        return employeenumber;
    }

    public void setEmployeenumber(String employeenumber) {
        this.employeenumber = employeenumber;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getPostnames() {
        return postnames;
    }

    public void setPostnames(String postnames) {
        this.postnames = postnames;
    }

    public String getTransactortruename() {
        return transactortruename;
    }

    public void setTransactortruename(String transactortruename) {
        this.transactortruename = transactortruename;
    }

    public Integer getApprovalnumbers() {
        return approvalnumbers;
    }

    public void setApprovalnumbers(Integer approvalnumbers) {
        this.approvalnumbers = approvalnumbers;
    }

    public Integer getApprovalstatue() {
        return approvalstatue;
    }

    public void setApprovalstatue(Integer approvalstatue) {
        this.approvalstatue = approvalstatue;
    }

    public Integer getWorkingnumbers() {
        return workingnumbers;
    }

    public void setWorkingnumbers(Integer workingnumbers) {
        this.workingnumbers = workingnumbers;
    }

    public Integer getWorkingstatue() {
        return workingstatue;
    }

    public void setWorkingstatue(Integer workingstatue) {
        this.workingstatue = workingstatue;
    }

    public Integer getFilenumbers() {
        return filenumbers;
    }

    public void setFilenumbers(Integer filenumbers) {
        this.filenumbers = filenumbers;
    }

    public Integer getFilestatue() {
        return filestatue;
    }

    public void setFilestatue(Integer filestatue) {
        this.filestatue = filestatue;
    }

    public Integer getOfficesupplynumbers() {
        return officesupplynumbers;
    }

    public void setOfficesupplynumbers(Integer officesupplynumbers) {
        this.officesupplynumbers = officesupplynumbers;
    }

    public Integer getOfficesupplystatue() {
        return officesupplystatue;
    }

    public void setOfficesupplystatue(Integer officesupplystatue) {
        this.officesupplystatue = officesupplystatue;
    }
}
