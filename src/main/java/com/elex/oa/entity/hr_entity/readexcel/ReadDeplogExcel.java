package com.elex.oa.entity.hr_entity.readexcel;

import com.elex.oa.entity.hr_entity.department.DeptLog;
import com.elex.oa.util.hr_util.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @Author:ShiYun;
 * @Description:处理Excel的类
 * @Date:Created in  16:40 2018\5\7 0007
 * @Modify By:
 */
public class ReadDeplogExcel extends ReadBaseExcel<DeptLog>{
    @Override
    public Object getObject(Sheet sheet, Row row) {
        DeptLog deptLog = new DeptLog();
        // 循环Excel的列
        for (int c = 0; c < this.totalCells; c++) {
            Cell cell = row.getCell(c);
            Cell cell2 = sheet.getRow(0).getCell(c);
            String columnname;
            if (cell2!=null) {
                columnname = String.valueOf(cell2.getStringCellValue());
            } else {
                continue;
            }
            if (null != cell) {
                if ("变更项目".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    deptLog.setChangeinformation(str);
                }else if ("部门名称".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    deptLog.setDeptname(str);
                }else if ("变更前内容".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    deptLog.setBeforeinformation(str);
                }else if ("变更后内容".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    deptLog.setAfterinformation(str);
                }else if ("变更原因".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    deptLog.setChangereason(str);
                }else if ("变更日期".equals(columnname.trim())) {
                    String str = ExcelUtil.getStringByDateCell(cell);
                    deptLog.setChangedate(str);
                }else if ("办理人".equals(columnname.trim())) {
                    String str = String.valueOf(cell.getStringCellValue());
                    deptLog.setTransactortruename(str);
                }
            }
        }
        return deptLog;
    }
}
