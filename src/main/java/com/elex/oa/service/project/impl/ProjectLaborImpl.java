package com.elex.oa.service.project.impl;

import com.alibaba.fastjson.JSONArray;
import com.elex.oa.dao.project.ProjectLaborDao;
import com.elex.oa.entity.project.ProjectLabor;
import com.elex.oa.service.project.ProjectLaborService;
import com.elex.oa.util.hr_util.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectLaborImpl implements ProjectLaborService {

    @Resource
    ProjectLaborDao projectLaborDao;

    public static List<String> findDates(String stime, String etime) throws ParseException {
        List<String> allDate = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dBegin = sdf.parse(stime);
        Date dEnd = sdf.parse(etime);
        allDate.add(sdf.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            allDate.add(sdf.format(calBegin.getTime()));
        }
        return allDate;
    }

    @Override
    public List queryLaborHourInfo(HttpServletRequest request,String employeeNumber) throws ParseException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<String> dateList = findDates(startDate,endDate);
        String projectCode = request.getParameter("projectCode");
        JSONArray projectArray =JSONArray.parseArray(projectCode);
        List<ProjectLabor> projectLaborList;
        List projectlist = new ArrayList();
        for (int j = 0;j < projectArray.size();j++) {
            projectLaborList = projectLaborDao.queryLaborHourInfo(employeeNumber,projectArray.get(j).toString(),startDate,endDate);
            if (startDate != null && endDate != null && employeeNumber != null && projectCode != null && projectLaborList.size() > 0) {
                Map<String,Object> map = new HashMap<>();
                map.put("projectCode",projectLaborList.get(j).getProjectCode());
                map.put("projectName",projectLaborList.get(j).getProjectName());
                List laborHour = new ArrayList();
                List list = new ArrayList();
                for (int i = 0;i < projectLaborList.size();i++) {
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(projectLaborList.get(i).getFillingDate().getTime()));
                    list.add(date);
                }
                for (int k = 0;k < dateList.size();k++) {
                    if (!list.contains(dateList.get(k))) {
                        laborHour.add("0");
                    }else{
                        laborHour.add(projectLaborDao.queryDateLabor(employeeNumber,projectLaborList.get(j).getProjectCode(),list.get(list.indexOf(dateList.get(k))).toString()));
                    }
                }
                map.put("laborHour",laborHour);
                projectlist.add(map);
            }
        }
        return projectlist;
    }

    @Override
    public String updateLaborHourInfo(HttpServletRequest request,String employeeNumber) {
        String projectList = request.getParameter("projectList");
        JSONArray projectArray =JSONArray.parseArray(projectList);
        String result = "success";
        ProjectLabor projectLabor = new ProjectLabor();
        for (int i = 0;i < projectArray.size();i++) {
            projectLabor.setEmployeeNumber( employeeNumber );
            projectLabor.setEmployeeName( projectArray.getJSONObject(i).getString("employeeName") );
            projectLabor.setProjectCode( projectArray.getJSONObject(i).getString("projectCode") );
            projectLabor.setProjectName( projectArray.getJSONObject(i).getString("projectName") );
            TimeUtil time = new TimeUtil();
            List dateList = Arrays.asList(projectArray.getJSONObject(i).getString("fillingDate").replaceAll("\"","").replaceAll("\\[","").replaceAll("\\]","").split(","));
            List hourList = Arrays.asList(projectArray.getJSONObject(i).getString("laborHour").replaceAll("\"","").replaceAll("\\[","").replaceAll("\\]","").split(","));
            for (int j = 0;j < dateList.size();j++) {
                projectLabor.setFillingDate( time.strToDate(dateList.get(j).toString(),"yyyy-MM-dd") );
                projectLabor.setLaborHour( BigDecimal.valueOf(Double.parseDouble(hourList.get(j).toString())) );
                projectLabor.setId(employeeNumber + projectLabor.getProjectCode() + dateList.get(j).toString());
                try {
                    projectLaborDao.updateLaborHourInfo(projectLabor);
                } catch (Exception e){
                    result = "fail";
                }
            }
        }
        return result;
    }
}
