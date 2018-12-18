package com.hrym.common.util;

import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;

/**
 * Created by hrym13 on 2018/12/6.
 */
public class ExcelUtil {
    public static  String genarateExcel() {
        
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("企业信息管理");
        
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        // 生成表头
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("企业名称");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("状态");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("法人代表");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("创建时间");

//        List<Info> list = super.copyList(infoDao.selectAll(),Info.class);
        for (int i = 0; i < 10; i++) {
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue("哈哈");
            if ("1".equals(1)) {
                row.createCell(1).setCellValue("正常");
            }else {
                row.createCell(1).setCellValue("不启用");
            }
            
            row.createCell(2).setCellValue("是是是");
            row.createCell(3).setCellValue("111"+i);
            
        }
        String filePath = "/Users/hrym13/IdeaProjects/hrym/home.xls";
        try {
            FileOutputStream fout = new FileOutputStream("home.xls");
            workbook.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String urlPath = filePath;
        
        return urlPath;
    }
    
    
    
    public static void main(String[] args) {
    
        genarateExcel();
    }
}
