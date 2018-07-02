package com.elex.oa.entity.project;

public class ProjectInfor {
    private int id;
    private String projectCode; //项目编号
    private String projectName; //项目名称
    private String inDepartment; //立项部门
    private String projectStatus; //项目状态
    private String proposer; //申请人
    private String writeDate; //立项日期
    private String department1;
    private String businessManager; //商务经理
    private String projectSource; //项目来源
    private String department2;
    private String projectManager; //项目经理
    private String projectType; //项目类型
    private String generalSituation;// 项目概况
    private String projectMembers; //项目成员
    private String relatedMembers; //相关人员
    private String startDate; //实施启动日期
    private String endDate; //预计结束日期
    private String partyName; //甲方名称
    private String partyAddress; //甲方地址
    private String partyPhone; //甲方电话
    private String partyFax; //甲方传真
    private String headName; //负责人姓名
    private String headPosition; //负责人职务
    private String headMobile; //负责人手机
    private String headEmail; //负责人邮件
    private String contactName; //联系人姓名
    private String contactPosition; //联系人职务
    private String contactMobile; //联系人手机
    private String contactEmail; //联系人邮件
    private String departmentManager; //部门经理

    public ProjectInfor() {
    }

    public ProjectInfor(int id, String projectCode, String projectName, String inDepartment, String projectStatus, String proposer, String writeDate, String department1, String businessManager, String projectSource, String department2, String projectManager, String projectType, String generalSituation, String projectMembers, String relatedMembers, String startDate, String endDate, String partyName, String partyAddress, String partyPhone, String partyFax, String headName, String headPosition, String headMobile, String headEmail, String contactName, String contactPosition, String contactMobile, String contactEmail, String departmentManager) {
        this.id = id;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.inDepartment = inDepartment;
        this.projectStatus = projectStatus;
        this.proposer = proposer;
        this.writeDate = writeDate;
        this.department1 = department1;
        this.businessManager = businessManager;
        this.projectSource = projectSource;
        this.department2 = department2;
        this.projectManager = projectManager;
        this.projectType = projectType;
        this.generalSituation = generalSituation;
        this.projectMembers = projectMembers;
        this.relatedMembers = relatedMembers;
        this.startDate = startDate;
        this.endDate = endDate;
        this.partyName = partyName;
        this.partyAddress = partyAddress;
        this.partyPhone = partyPhone;
        this.partyFax = partyFax;
        this.headName = headName;
        this.headPosition = headPosition;
        this.headMobile = headMobile;
        this.headEmail = headEmail;
        this.contactName = contactName;
        this.contactPosition = contactPosition;
        this.contactMobile = contactMobile;
        this.contactEmail = contactEmail;
        this.departmentManager = departmentManager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getInDepartment() {
        return inDepartment;
    }

    public void setInDepartment(String inDepartment) {
        this.inDepartment = inDepartment;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public String getDepartment1() {
        return department1;
    }

    public void setDepartment1(String department1) {
        this.department1 = department1;
    }

    public String getBusinessManager() {
        return businessManager;
    }

    public void setBusinessManager(String businessManager) {
        this.businessManager = businessManager;
    }

    public String getProjectSource() {
        return projectSource;
    }

    public void setProjectSource(String projectSource) {
        this.projectSource = projectSource;
    }

    public String getDepartment2() {
        return department2;
    }

    public void setDepartment2(String department2) {
        this.department2 = department2;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getGeneralSituation() {
        return generalSituation;
    }

    public void setGeneralSituation(String generalSituation) {
        this.generalSituation = generalSituation;
    }

    public String getProjectMembers() {
        return projectMembers;
    }

    public void setProjectMembers(String projectMembers) {
        this.projectMembers = projectMembers;
    }

    public String getRelatedMembers() {
        return relatedMembers;
    }

    public void setRelatedMembers(String relatedMembers) {
        this.relatedMembers = relatedMembers;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyAddress() {
        return partyAddress;
    }

    public void setPartyAddress(String partyAddress) {
        this.partyAddress = partyAddress;
    }

    public String getPartyPhone() {
        return partyPhone;
    }

    public void setPartyPhone(String partyPhone) {
        this.partyPhone = partyPhone;
    }

    public String getPartyFax() {
        return partyFax;
    }

    public void setPartyFax(String partyFax) {
        this.partyFax = partyFax;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getHeadPosition() {
        return headPosition;
    }

    public void setHeadPosition(String headPosition) {
        this.headPosition = headPosition;
    }

    public String getHeadMobile() {
        return headMobile;
    }

    public void setHeadMobile(String headMobile) {
        this.headMobile = headMobile;
    }

    public String getHeadEmail() {
        return headEmail;
    }

    public void setHeadEmail(String headEmail) {
        this.headEmail = headEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(String departmentManager) {
        this.departmentManager = departmentManager;
    }

    @Override
    public String toString() {
        return "ProjectInfor{" +
                "id=" + id +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", inDepartment='" + inDepartment + '\'' +
                ", projectStatus='" + projectStatus + '\'' +
                ", proposer='" + proposer + '\'' +
                ", writeDate='" + writeDate + '\'' +
                ", department1='" + department1 + '\'' +
                ", businessManager='" + businessManager + '\'' +
                ", projectSource='" + projectSource + '\'' +
                ", department2='" + department2 + '\'' +
                ", projectManager='" + projectManager + '\'' +
                ", projectType='" + projectType + '\'' +
                ", generalSituation='" + generalSituation + '\'' +
                ", projectMembers='" + projectMembers + '\'' +
                ", relatedMembers='" + relatedMembers + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", partyName='" + partyName + '\'' +
                ", partyAddress='" + partyAddress + '\'' +
                ", partyPhone='" + partyPhone + '\'' +
                ", partyFax='" + partyFax + '\'' +
                ", headName='" + headName + '\'' +
                ", headPosition='" + headPosition + '\'' +
                ", headMobile='" + headMobile + '\'' +
                ", headEmail='" + headEmail + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPosition='" + contactPosition + '\'' +
                ", contactMobile='" + contactMobile + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", departmentManager='" + departmentManager + '\'' +
                '}';
    }
}
