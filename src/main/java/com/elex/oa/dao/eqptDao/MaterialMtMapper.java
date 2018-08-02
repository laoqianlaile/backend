package com.elex.oa.dao.eqptDao;

import com.elex.oa.entity.eqpt.Material;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaterialMtMapper {
    List<Material> detailList();

    List<Material> searchDetail(Material material);

    /*Material MaterialMtId(Material material);

    void saveMaterialMt(Material material);

    void deleteMaterialMt(Material material);

    void newMaterialMt(Material material);*/

    String manageBS(Material material);

    String needCheck(Material material);

    String recordin(Material material);
    String recordout(Material material);
    String recordshift(Material material);
}
