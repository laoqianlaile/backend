package com.elex.oa.entity.ou;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\22 0022 14:58
 * @Version 1.0
 **/
@Table(name = "tb_ou_dep")
public class OuDep {
    @Id
    private String id;//主键

    private String name;//部门名称
    private String code;//部门编号
    @Column(name = "functionalTypeid")
    private Integer functionalTypeid;//职能类型ID
    @Column(name = "parentDepcode")
    private String parentDepcode;//上级部门编号
    @Column(name = "depTypeid")
    private Integer depTypeid;//部门类型ID
    private String level;//部门层级
    @Column(name = "`order`")//order是特殊字符（排序），不能直接用
    private String order;//排序编号
    @Column(name = "principaluserEmployeenumber")
    private String principaluserEmployeenumber;//部门正职工号
    @Column(name = "deputyuserEmployeenumber")
    private String deputyuserEmployeenumber;//部门副职工号
    private String subdepartments;//子部门[子部门编号1;子部门编号2]
    private String posts;//岗位[岗位编号1:工号1,工号2;岗位编号2:工号3,工号4]
    @Column(name = "managerEmployeenumber")
    private String managerEmployeenumber;//分管领导工号
    private String companyname;//公司名称
    private String state;//状态

    public OuDep() {
    }

    public OuDep(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getFunctionalTypeid() {
        return functionalTypeid;
    }

    public void setFunctionalTypeid(Integer functionalTypeid) {
        this.functionalTypeid = functionalTypeid;
    }

    public String getParentDepcode() {
        return parentDepcode;
    }

    public void setParentDepcode(String parentDepcode) {
        this.parentDepcode = parentDepcode;
    }

    public Integer getDepTypeid() {
        return depTypeid;
    }

    public void setDepTypeid(Integer depTypeid) {
        this.depTypeid = depTypeid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPrincipaluserEmployeenumber() {
        return principaluserEmployeenumber;
    }

    public void setPrincipaluserEmployeenumber(String principaluserEmployeenumber) {
        this.principaluserEmployeenumber = principaluserEmployeenumber;
    }

    public String getDeputyuserEmployeenumber() {
        return deputyuserEmployeenumber;
    }

    public void setDeputyuserEmployeenumber(String deputyuserEmployeenumber) {
        this.deputyuserEmployeenumber = deputyuserEmployeenumber;
    }

    public String getSubdepartments() {
        return subdepartments;
    }

    public void setSubdepartments(String subdepartments) {
        this.subdepartments = subdepartments;
    }

    public String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

    public String getManagerEmployeenumber() {
        return managerEmployeenumber;
    }

    public void setManagerEmployeenumber(String managerEmployeenumber) {
        this.managerEmployeenumber = managerEmployeenumber;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "OuDep{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", functionalTypeid=" + functionalTypeid +
                ", parentDepcode='" + parentDepcode + '\'' +
                ", depTypeid=" + depTypeid +
                ", level='" + level + '\'' +
                ", order='" + order + '\'' +
                ", principaluserEmployeenumber='" + principaluserEmployeenumber + '\'' +
                ", deputyuserEmployeenumber='" + deputyuserEmployeenumber + '\'' +
                ", subdepartments='" + subdepartments + '\'' +
                ", posts='" + posts + '\'' +
                ", managerEmployeenumber='" + managerEmployeenumber + '\'' +
                ", companyname='" + companyname + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}