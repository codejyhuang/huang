package com.hrym.wechat.controller;

import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.wechat.entity.AppRecordCommon;
import com.hrym.wechat.entity.TaskCommon;
import com.hrym.wechat.service.IFlockCountReportService;
import com.hrym.wechat.service.IFlockService;
import com.hrym.wechat.service.IItemCustomService;
import com.hrym.wechat.service.IItemLessonService;
import com.hrym.wechat.smallProgram.FlockCountReportParam;
import com.hrym.wechat.smallProgram.ItemParam;
import com.hrym.wechat.smallProgram.QueryObjectParam;
import com.hrym.wechat.smallProgram.WechatFlockParam;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 共修群功课
 */
@RestController
@RequestMapping("hrym/wechat/assignment")
public class FlockAssignmentController extends BaseController {

    @Autowired
    private IItemLessonService iItemLessonService;

    @Autowired
    private IItemCustomService iItemCustomService;
    
    @Autowired
    private IFlockCountReportService flockCountReportService;
    @Autowired
    private IFlockService flockService;


    /**
     *
     * 新建群  列表群功课
     * @param qo
     * @return
     */
    @RequestMapping("listAssignment")
    @Allowed
    public BaseResult listLesson(@RequestBody QueryObjectParam qo) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message,iItemLessonService.listByQo(qo));
    }

    /**
     *
     * 群功课列表  管理群<群详情里>
     * @param
     * @return
     */
    @RequestMapping("listFlockAssignment")
    @Allowed
    public BaseResult listFlockLesson(@RequestBody QueryObjectParam qo) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message,iItemLessonService.queryByQo(qo));
    }


    /**
     * 自建功课
     * @param
     * @return
     */
    @RequestMapping("buildCustom")
    @Allowed
    public BaseResult buildCustom(@RequestBody ItemParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message,iItemCustomService.buildCustom(param));
    }
    
    /**
     * 报数
     * @param
     * @return
     */
    @RequestMapping("/flockCountReport")
    @Allowed
    public BaseResult flockCountReport(@RequestBody FlockCountReportParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message,flockCountReportService.flockCountReport(param));
    }

    /**
     * 群功课详细信息
     * @param
     * @return
     */
    @RequestMapping("/flockAssignmentMessage")
    @Allowed
    public BaseResult flockAssignmentMessage(@RequestBody ItemParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message,iItemLessonService.flockAssignmentMessage(param));
    }




    //                                       ----------------   1.1   新增接口  我的功课   ------------------

    /**
     * 我的功课
     * @param
     * @return
     */
    @RequestMapping("/selfItem")
    @Allowed
    public BaseResult selfItem(@RequestBody ItemParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message,iItemLessonService.selfItem(param));
    }


    /**
     * 我的功课  功课列表接口
     * @param
     * @return
     */
    @RequestMapping("/selfItemList")
    @Allowed
    public BaseResult selfItemList(@RequestBody QueryObjectParam qo) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message,iItemLessonService.selfItemList(qo));
    }


    /**
     * 添加功课  add task
     * @param
     * @return
     */
    @RequestMapping("/addTask")
    @Allowed
    public BaseResult addTask(@RequestBody ItemParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        iItemLessonService.addTask(param);
        return new BaseResult(code, message,null);
    }


    /**
     * 删除功课  remove task
     * @param
     * @return
     */
    @RequestMapping("/removeTask")
    @Allowed
    public BaseResult removeTask(@RequestBody ItemParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        iItemLessonService.removeTask(param);
        return new BaseResult(code, message,null);
    }

    /**
     * 自修详情卡片
     * @param
     * @return
     */
    @RequestMapping("/selfRepairCard")
    @Allowed
    public BaseResult selfRepairCard(@RequestBody ItemParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message,iItemLessonService.selfRepairCard(param));
    }


    /**
     * 自修详情 修行历史记录
     * @param
     * @return
     */
    @RequestMapping("/selfRepairHistory")
    @Allowed
    public BaseResult selfRepairHistory(@RequestBody ItemParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message,iItemLessonService.selfRepairHistory(param));
    }

    /**
     * 自修详情 修行历史记录
     * @param
     * @return
     */
    @RequestMapping("/removeSelfRepairHistory")
    @Allowed
    public BaseResult removeSelfRepairHistory(@RequestBody ItemParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        iItemLessonService.removeSelfRepairHistory(param);
        return new BaseResult(code, message,null);
    }


    /**
     * 共修详情 共修群列表
     * @param
     * @return
     */
    @RequestMapping("/itemFlockList")
    @Allowed
    public BaseResult itemFlockList(@RequestBody ItemParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message,iItemLessonService.itemFlockList(param));
    }

    /**
     * 共修详情 共修群功课详情卡片
     * @param
     * @return
     */
    @RequestMapping("/flockItemMessageCard")
    @Allowed
    public BaseResult flockItemMessageCard(@RequestBody ItemParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message,iItemLessonService.flockItemMessageCard(param));
    }


    /**
     * 共修详情 共修群功课  可点赞动态
     * @param
     * @return
     */
    @RequestMapping("/flockItemMessageRecord")
    @Allowed
    public BaseResult flockItemMessageRecord(@RequestBody ItemParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message,iItemLessonService.flockItemMessageRecord(param));
    }


    /**
     * 共修详情 点赞用户列表
     * @param
     * @return
     */
    @RequestMapping("/queryLikeMember")
    @Allowed
    public BaseResult queryLikeMember(@RequestBody ItemParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        return new BaseResult(code, message,iItemLessonService.queryLikeMember(param));
    }

    /**
     * 动态 点赞
     * @param
     * @return
     */
    @RequestMapping("/parseRecord")
    @Allowed
    public BaseResult parseRecord(@RequestBody ItemParam param) {
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        iItemLessonService.parseRecord(param);
        return new BaseResult(code, message,null);
    }



    /**
     * 导入历史记录
     * @param param
     * @return
     */
    @RequestMapping("/importHistory")
    @Allowed
    public BaseResult importingHistoryRecord(@RequestBody FlockCountReportParam param) {

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        flockCountReportService.importHistory(param);
        return new BaseResult(code, message,null);
    }






    @RequestMapping("/updateitemListOrder")
    @Allowed
    public BaseResult updateitemListOrder(@RequestBody WechatFlockParam param){
        
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        
        iItemLessonService.updateitemListOrder(param);
        return new BaseResult(code, message,null);
        
    }
    
    
    //文件下载：导出excel表
    @RequestMapping(value = "/exportExcel")
    @Allowed
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,Integer userId) throws UnsupportedEncodingException {
        //一、从后台拿数据
        if (null == request || null == response)
        {
            return;
        }
        
       Map<String,Object>  map= flockService.backItemDatas(userId);
        System.out.println(map);
       
//            List<VipConsumes> list = null;
//        String startTime = request.getParameter("startTime");
//        String endTime = request.getParameter("endTime");
//            int consumesType = Integer.parseInt(request.getParameter("consumesType"));
//            int conPaymentStatus =Integer.parseInt(request.getParameter("conPaymentStatus"));
//
//            VipConsumesExample example = new VipConsumesExample();
//            if(consumesType!=0 && conPaymentStatus!=0){
//                example.createCriteria().andTimeBetween(startTime, endTime).andConsumeTypeEqualTo(consumesType).andStatusEqualTo(conPaymentStatus);
//            }else if(consumesType ==0 && conPaymentStatus!=0) {
//                example.createCriteria().andTimeBetween(startTime, endTime).andStatusEqualTo(conPaymentStatus);
//            }else if(consumesType!=0 && conPaymentStatus==0){
//                example.createCriteria().andTimeBetween(startTime, endTime).andConsumeTypeEqualTo(consumesType);
//            }else {
//                example.createCriteria().andTimeBetween(startTime, endTime);
//            }
//            list = this.vipConsumesDao.selectByExample(example);
        //二、 数据转成excel
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/x-download");
        
        String fileName = "用户数据备份.xlsx";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        // 第一步：定义一个新的工作簿
        XSSFWorkbook wb = new XSSFWorkbook();
        // 第二步：创建第一个Sheet页
        XSSFSheet sheet = wb.createSheet("用户总计");
        sheet.setDefaultRowHeight((short) (2 * 256));//设置行高
        sheet.setColumnWidth(0, 4000);//设置列宽
        sheet.setColumnWidth(1,5500);
        sheet.setColumnWidth(2,5500);
        sheet.setColumnWidth(3,5500);
        
        XSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 16);
        
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("用户昵称 ");
        cell = row.createCell(1);
        cell.setCellValue("修行时间 ");
        cell = row.createCell(2);
        cell.setCellValue("功课门数");
        cell = row.createCell(3);
        cell.setCellValue("累计报数");
        cell = row.createCell(4);
        
        XSSFRow rows;
        XSSFCell cells;
            // 第三步：在这个sheet页里创建一行
            rows = sheet.createRow(1);
            // 第四步：在该行创建一个单元格
            cells = rows.createCell(0);
            // 第五步：在该单元格里设置值
            cells.setCellValue((String) map.get("nickName"));
            
            cells = rows.createCell(1);
            cells.setCellValue((Integer) map.get("timeStr"));
            cells = rows.createCell(2);
            cells.setCellValue((Integer) map.get("count"));
            cells = rows.createCell(3);
            cells.setCellValue((Integer) map.get("doneNum"));
            cells = rows.createCell(4);
        
        // 第二步：创建第二个Sheet页
        XSSFSheet sheet1 = wb.createSheet("用户功课详细");
        sheet1.setDefaultRowHeight((short) (2 * 256));//设置行高
        sheet1.setColumnWidth(0, 4000);//设置列宽
        sheet1.setColumnWidth(1,5500);
        sheet1.setColumnWidth(2,5500);
        sheet1.setColumnWidth(3,5500);
        sheet1.setColumnWidth(11,3000);
        sheet1.setColumnWidth(12,3000);
        sheet1.setColumnWidth(13,3000);
        XSSFFont font1 = wb.createFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short) 16);
        
        XSSFRow row1 = sheet1.createRow(0);
        XSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("功课名称 ");
        cell1 = row1.createCell(1);
        cell1.setCellValue("累计报数 ");
        cell1 = row1.createCell(2);
        cell1.setCellValue("功课状态");
        
        XSSFRow rows1;
        XSSFCell cells1;
        List<TaskCommon> commonList = (List<TaskCommon>) map.get("taskCommonList");
        for (TaskCommon taskCommon : commonList) {
            // 第三步：在这个sheet页里创建一行
           
            rows1 = sheet1.createRow(commonList.indexOf(taskCommon)+1);
            // 第四步：在该行创建一个单元格
            cells1 = rows1.createCell(0);
            // 第五步：在该单元格里设置值
            cells1.setCellValue(taskCommon.getLessonName());
            
            cells1 = rows1.createCell(1);
            cells1.setCellValue(taskCommon.getDoneNum());
            cells1 = rows1.createCell(2);
            if (taskCommon.getIsExit() !=null &&taskCommon.getIsExit()== 1) {
                cells1.setCellValue("未删除");
                
            } else {
                cells1.setCellValue("删除");
            }
        }
        // 第二步：创建第三个Sheet页
        XSSFSheet sheet2 = wb.createSheet("报数详细");
        sheet2.setDefaultRowHeight((short) (2 * 256));//设置行高
        sheet2.setColumnWidth(0, 4000);//设置列宽
        sheet2.setColumnWidth(1,5500);
        sheet2.setColumnWidth(2,5500);
        sheet2.setColumnWidth(3,5500);
        XSSFFont font2 = wb.createFont();
        font2.setFontName("宋体");
        font2.setFontHeightInPoints((short) 16);
        
        XSSFRow row2 = sheet2.createRow(0);
        XSSFCell cell2 = row2.createCell(0);
        cell2.setCellValue("功课名称 ");
        cell2 = row2.createCell(1);
        cell2.setCellValue("报数 ");
        cell2 = row2.createCell(2);
        cell2.setCellValue("报数时间");
        
        XSSFRow rows2;
        XSSFCell cells2;
        List<AppRecordCommon> recordCommonList = (List<AppRecordCommon>) map.get("recordCommonList");
        for (AppRecordCommon record :recordCommonList) {
            // 第三步：在这个sheet页里创建一行
            rows2 = sheet2.createRow(recordCommonList.indexOf(record)+1);
            // 第四步：在该行创建一个单元格
            cells2 = rows2.createCell(0);
            // 第五步：在该单元格里设置值
            cells2.setCellValue(record.getLessonName());
            
            cells2 = rows2.createCell(1);
            cells2.setCellValue(record.getReportNum());
            cells2 = rows2.createCell(2);
            cells2.setCellValue(record.getTimeStr());
        }
        
        
        try {
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.close();
            wb.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }


//    @RequestMapping("/")

}
