package com.elex.oa.entity.hr_entity;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ShiYun;
 * @Description:处理Excel的类
 * @Date:Created in  16:40 2018\5\7 0007
 * @Modify By:
 */
public class ReadPostlogExcel {
    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ReadPostlogExcel(){}
    //获取总行数
    public int getTotalRows()  { return totalRows;}
    //获取总列数
    public int getTotalCells() {  return totalCells;}
    //获取错误信息
    public String getErrorInfo() { return errorMsg; }

    /**
     * 读EXCEL文件，获取信息集合
     * @return
     */
    public List<PostLog> getExcelInfo(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();//获取文件名
        List<PostLog> postLogs = new ArrayList<PostLog>();
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            postLogs = createExcel(mFile.getInputStream(), isExcel2003);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return postLogs;
    }

    /**
     * 根据excel里面的内容读取客户信息
     * @param is 输入流
     * @param isExcel2003 excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public List<PostLog> createExcel(InputStream is, boolean isExcel2003) {
        List<PostLog> postLogs = new ArrayList<PostLog>();
        try{
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            postLogs = readExcelValue(wb);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return postLogs;
    }

    /**
     * 读取Excel里面客户的信息
     * @param wb
     * @return
     */
    private List<PostLog> readExcelValue(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        System.out.println(123);
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<PostLog> postLogs = new ArrayList<PostLog>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                break;
            }
            PostLog postLog = new PostLog();
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
                        postLog.setChangeinformation(str);
                    }else if ("岗位".equals(columnname.trim())) {
                        String str = String.valueOf(cell.getStringCellValue());
                        postLog.setPostname(str);
                    }else if ("变更前内容".equals(columnname.trim())) {
                        String str = String.valueOf(cell.getStringCellValue());
                        postLog.setBeforeinformation(str);
                    }else if ("变更后内容".equals(columnname.trim())) {
                        String str = String.valueOf(cell.getStringCellValue());
                        postLog.setAfterinformation(str);
                    }else if ("变更原因".equals(columnname.trim())) {
                        String str = String.valueOf(cell.getStringCellValue());
                        postLog.setChangereason(str);
                    }else if ("变更日期".equals(columnname.trim())) {
                        if(HSSFDateUtil.isCellDateFormatted(cell)){
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                            String str = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                            postLog.setChangedate(str);
                        }
                        /*if(HSSFCell.CELL_TYPE_STRING==cell.getCellType()){
                            deptLog.setChangedate(cell.getStringCellValue());
                        }*/
                        /*if (HSSFCell.CELL_TYPE_NUMERIC==cell.getCellType()) {
                            if(HSSFDateUtil.isCellDateFormatted(cell)){
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                                String str = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                                deptLog.setChangedate(str);
                            }
                            deptLog.setChangedate(new DecimalFormat("#").format(cell.getNumericCellValue()));
                        }
                        if(HSSFCell.CELL_TYPE_STRING==cell.getCellType()){
                            deptLog.setChangedate(cell.getStringCellValue());
                        }*/
                    }else if ("办理人".equals(columnname.trim())) {
                        String str = String.valueOf(cell.getStringCellValue());
                        postLog.setTransactortruename(str);
                    }
                }
            }
            // 添加到list
            /*if (deptLog.getChangedate()!=null && !"1899/12/31".equals(deptLog.getChangedate())) {
                deptLogs.add(deptLog);
            }*/
            postLogs.add(postLog);
        }
        return postLogs;
    }

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}