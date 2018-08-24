package com.elex.oa.service.service_shiyun;

import com.elex.oa.entity.entity_shiyun.PersonalInformation;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:人事信息
 * @Date:Created in  9:58 2018\4\8 0008
 * @Modify By:
 */
public interface IPersonalInformationService{
    /**
     *@Author:ShiYun;
     *@Description:根据条件查询人事信息
     *@Date: 10:03 2018\4\8 0008
     */
    PageInfo<PersonalInformation> queryPIs(Map<String,Object> paramMap) throws ParseException;

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询人事信息
     *@Date: 18:06 2018\4\8 0008
     */
    PersonalInformation queryOneById(Integer id);
    PersonalInformation queryOneById2(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据员工ID查询人事信息
     *@Date: 10:59 2018\4\9 0009
     */
    PersonalInformation queryOneByUserid(Integer userid);

    /**
     *@Author:ShiYun;
     *@Description:添加人事信息的主要信息并返回主键
     *@Date: 18:47 2018\4\10 0010
     */
    Integer saveOne(PersonalInformation personalInformation);

    /**
     *@Author:ShiYun;
     *@Description:修改人事信息
     *@Date: 16:30 2018\4\11 0011
     */
    void modifyOne(PersonalInformation personalInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询人事信息（不分页）
     *@Date: 16:28 2018\4\13 0013
     */
    List<PersonalInformation> queryPIs(PersonalInformation personalInformation);

    /**
     *@Author:ShiYun;
     *@Description:查询所有人事信息（不分页）
     *@Date: 17:27 2018\4\18 0018
     */
    List<PersonalInformation> queryAllByNull();

    /**
     *@Author:ShiYun;
     *@Description:根据部门ID查询人事信息
     *@Date: 16:23 2018\5\28 0028
     */
    List<PersonalInformation> queryByDepid(Integer depid);

    /**
     *@Author:ShiYun;
     *@Description:根据员工号查询员工
     *@Date: 10:01 2018\8\9 0009
     */
    List<PersonalInformation> queryByEmployeenumber(String employeenumber);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除人事主体信息
     *@Date: 16:10 2018\8\20 0020
     */
    void removeOne(Integer perid);
}
