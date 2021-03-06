package com.elex.oa.controller.objectives;

import com.elex.oa.entity.objectives.Goal2;
import com.elex.oa.service.objectives.GrossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
@RequestMapping("/gross")
public class GrossController {
    @Autowired
    private GrossService grossService;

    //根据年度查询销售毛利信息
    @RequestMapping("/query_data")
    @ResponseBody
    public Goal2 queryData(Goal2 goal2) {
        return grossService.queryData(goal2);
    }

    //保存销售毛利信息
    @RequestMapping("/save_data")
    @ResponseBody
    public String saveData(Goal2 goal2) {
        return grossService.saveData(goal2);
    }
}
