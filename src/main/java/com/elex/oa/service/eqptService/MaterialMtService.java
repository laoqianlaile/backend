package com.elex.oa.service.eqptService;

import com.elex.oa.entity.Page;
import com.elex.oa.entity.eqpt.Material;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;


public interface MaterialMtService {
    PageInfo<Material> showDetail(Page page);
    // 查找物料
    PageInfo<Material> searchDetail(Page page, HttpServletRequest request);

    /*Material changeMaterialMt(HttpServletRequest request);

    void saveMaterialMt(HttpServletRequest request);

    void deleteMaterialMt (HttpServletRequest request);

    void insertMaterialMt(Material material, HttpServletRequest request);

    String state(HttpServletRequest request);

    String record(HttpServletRequest request);*/

}
