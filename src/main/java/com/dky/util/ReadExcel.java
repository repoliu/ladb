package com.dky.util;

import com.dky.entity.influ.ForHourWeather;
import com.dky.entity.influ.ForWeather;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

public class ReadExcel {
    //总行数
    private int totalRows;
    private int totalRowsResult;
    private int successRows;
    private int errorRows;
    private String fileType;
    private String areaName;
    private String suffix;

    public String getFileType() {
        return fileType;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getSuffix() {
        return suffix;
    }

    //错误信息接收器
    private StringBuilder errmsg = new StringBuilder();
    private DataFormatter dataFormatter = new DataFormatter();
    //构造方法
    public ReadExcel() {
        totalRows = 0;
    }

    public String getErrmsg() {
        return errmsg.toString();
    }

    public int getTotalRowsResult() {
        return totalRowsResult;
    }

    public int getSuccessRows() {
        return successRows;
    }

    public int getErrorRows() {
        return errorRows;
    }

    public Map<String, List<Object>> getExcelInfo(MultipartFile file) throws IOException {
        Map<String, List<Object>> result = new HashMap<>();
        String fileName = file.getOriginalFilename();//获取文件名
        boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
        suffix = ".xls";
        if (isExcel2007(fileName)) {
            isExcel2003 = false;
            suffix = ".xlsx";
        }
        List<Object> userList = createExcel(file.getInputStream(), isExcel2003);
        if (userList == null || userList.size() == 0){
            return null;
        }
        result.put(fileType, userList);
        return result;
    }

    private List<Object> createExcel(InputStream is, boolean isExcel2003) throws IOException {

        Workbook wb;
        if (isExcel2003) {// 当excel是2003时,创建excel2003
            wb = new HSSFWorkbook(is);
        } else {// 当excel是2007时,创建excel2007
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        Row row0 = sheet.getRow(0);
        Cell cell0 = row0.getCell(0);
        String text = dataFormatter.formatCellValue(cell0);
        fileType = text;
        switch (text) {
            case "HisHourWeather":
                return createHourWeather(sheet);
            case "ForHourWeather":
                return createHourWeather(sheet);
            case "ForWeather":
                return createWeather(sheet);
            case "HisWeather":
                return createWeather(sheet);
            default:
                return null;
        }
    }

    private List<Object> createWeather(Sheet sheet) {
        List<Object> objList = new ArrayList<>();
        // 循环Excel行数
        for (int r = 2; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null || (row.getCell(0) == null && row.getCell(1) == null && row.getCell(2) == null)) {
                continue;
            }
            ForWeather forWeather = new ForWeather();
            long id;
            try {
                id = Long.parseLong(dataFormatter.formatCellValue(row.getCell(0)));
                forWeather.setId(id);
            } catch (NumberFormatException e) {
                id = System.currentTimeMillis() + (long) (Math.random() * 100000000);
                forWeather.setId(id);
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现异常：").append("id格式错误或为空！系统自动赋值为【").append(id).append("】！如需要，请及时修改；<br>");
            }
            if (dataFormatter.formatCellValue(row.getCell(1)).equals("") || row.getCell(1) == null) {
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现错误：").append("地区为空！数据未插入！").append("如需要，请及时修改；<br>");
                continue;
            } else {
                forWeather.setAreaname(dataFormatter.formatCellValue(row.getCell(1)));
            }
            String a = dataFormatter.formatCellValue(row.getCell(2));
            if (a.equals("") || row.getCell(2) == null) {
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现错误：").append("时间为空！数据未插入！").append("如需要，请及时修改；<br>");
                continue;
            } else {
                forWeather.setMdate(a);
            }
            if (dataFormatter.formatCellValue(row.getCell(3)).equals("") || row.getCell(3) == null) {
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现异常：").append("天气类型为空！").append("如需要，请及时修改；<br>");
            } else {
                forWeather.setWeatherType(dataFormatter.formatCellValue(row.getCell(3)));
            }
            try {
                forWeather.setMaxTemperature(new BigDecimal(dataFormatter.formatCellValue(row.getCell(4))));
            } catch (NumberFormatException e) {
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现异常：").append("最高温度为空！").append("如需要，请及时修改；<br>");
            }
            try {
                forWeather.setMinTemperature(new BigDecimal(dataFormatter.formatCellValue(row.getCell(5))));
            } catch (NumberFormatException e)  {
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现异常：").append("最低温度为空！").append("如需要，请及时修改；<br>");
            }
            if (dataFormatter.formatCellValue(row.getCell(6)).equals("") || row.getCell(6) == null) {
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现异常：").append("降雨量为空！").append("如需要，请及时修改；<br>");
            } else {
                forWeather.setRainfall(dataFormatter.formatCellValue(row.getCell(6)));
            }
            try {
                forWeather.setHumidity(new BigDecimal(dataFormatter.formatCellValue(row.getCell(7))));
            } catch (NumberFormatException e){
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现异常：").append("湿度为空！").append("如需要，请及时修改；<br>");
            }
            try {
                forWeather.setWindpower(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(8))));
            } catch (NumberFormatException e){
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现异常：").append("风速为空！").append("如需要，请及时修改；<br>");
            }
            if (dataFormatter.formatCellValue(row.getCell(9)).equals("") || row.getCell(9) == null) {
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现异常：").append("风向为空！").append("如需要，请及时修改；<br>");
            } else {
                forWeather.setWindway(dataFormatter.formatCellValue(row.getCell(9)));
            }
            try {
                forWeather.setPressure(new BigDecimal(dataFormatter.formatCellValue(row.getCell(10))));
            } catch (NumberFormatException e ){
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现异常：").append("压力为空！").append("如需要，请及时修改；<br>");
            }
            if (dataFormatter.formatCellValue(row.getCell(11)).equals("") || row.getCell(11) == null) {
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现异常：").append("舒适度为空！").append("如需要，请及时修改；<br>");
            } else {
                forWeather.setCosiness(dataFormatter.formatCellValue(row.getCell(11)));
            }
            forWeather.setVerTime(new Date());
            areaName = forWeather.getAreaname();
            // 添加到list
            objList.add(forWeather);
        }
        totalRowsResult = totalRows-2;
        successRows = objList.size();
        errorRows = totalRowsResult - successRows;
        return objList;
    }

    private List<Object> createHourWeather(Sheet sheet) {
        List<Object> objList = new ArrayList<>();
        // 循环Excel行数
        for (int r = 2; r < totalRows; r++) {
            long id;
            Row row = sheet.getRow(r);
            if (row == null || (row.getCell(0) == null && row.getCell(1) == null && row.getCell(2) == null && row.getCell(3) == null)) {
                continue;
            }
            ForHourWeather forHourWeather = new ForHourWeather();
            try {
                id = Long.parseLong(dataFormatter.formatCellValue(row.getCell(0)));
                forHourWeather.setId(id);
            } catch (NumberFormatException e) {
                id = System.currentTimeMillis() + (long) (Math.random() * 100000000);
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现异常：").append("id格式错误或为空！系统自动赋值为【").append(id).append("】！如需要，请及时修改；<br>");
                forHourWeather.setId(id);
            }
            if (dataFormatter.formatCellValue(row.getCell(1)).equals("") || row.getCell(1) == null) {
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现错误：").append("地区为空！数据未插入！").append("如需要，请及时修改；<br>");
                continue;
            } else {
                forHourWeather.setAreaname(dataFormatter.formatCellValue(row.getCell(1)));
            }
            String a = dataFormatter.formatCellValue(row.getCell(2));
            if (a.equals("") || row.getCell(2) == null) {
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现错误：").append("时间为空！数据未插入！").append("如需要，请及时修改；<br>");
                continue;
            } else {
                forHourWeather.setMdate(a);
            }
            if (dataFormatter.formatCellValue(row.getCell(3)).equals("") || row.getCell(3) == null) {
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现错误：").append("天气类型为空！数据未插入！").append("如需要，请及时修改；<br>");
                continue;
            } else if (!dataFormatter.formatCellValue(row.getCell(3)).equals("temperature") && !dataFormatter.formatCellValue(row.getCell(3)).equals("rainfall")) {
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现错误：").append("天气类型错误！数据未插入！").append("如需要，请及时修改；<br>");
                continue;
            } else {
                forHourWeather.setWeatherType(dataFormatter.formatCellValue(row.getCell(3)));
            }
            try {
                forHourWeather.setF00(new BigDecimal(dataFormatter.formatCellValue(row.getCell(4))));
                forHourWeather.setF01(new BigDecimal(dataFormatter.formatCellValue(row.getCell(5))));
                forHourWeather.setF02(new BigDecimal(dataFormatter.formatCellValue(row.getCell(6))));
                forHourWeather.setF03(new BigDecimal(dataFormatter.formatCellValue(row.getCell(7))));
                forHourWeather.setF04(new BigDecimal(dataFormatter.formatCellValue(row.getCell(8))));
                forHourWeather.setF05(new BigDecimal(dataFormatter.formatCellValue(row.getCell(9))));
                forHourWeather.setF06(new BigDecimal(dataFormatter.formatCellValue(row.getCell(10))));
                forHourWeather.setF07(new BigDecimal(dataFormatter.formatCellValue(row.getCell(11))));
                forHourWeather.setF08(new BigDecimal(dataFormatter.formatCellValue(row.getCell(12))));
                forHourWeather.setF09(new BigDecimal(dataFormatter.formatCellValue(row.getCell(13))));
                forHourWeather.setF10(new BigDecimal(dataFormatter.formatCellValue(row.getCell(14))));
                forHourWeather.setF11(new BigDecimal(dataFormatter.formatCellValue(row.getCell(15))));
                forHourWeather.setF12(new BigDecimal(dataFormatter.formatCellValue(row.getCell(16))));
                forHourWeather.setF13(new BigDecimal(dataFormatter.formatCellValue(row.getCell(17))));
                forHourWeather.setF14(new BigDecimal(dataFormatter.formatCellValue(row.getCell(18))));
                forHourWeather.setF15(new BigDecimal(dataFormatter.formatCellValue(row.getCell(19))));
                forHourWeather.setF16(new BigDecimal(dataFormatter.formatCellValue(row.getCell(20))));
                forHourWeather.setF17(new BigDecimal(dataFormatter.formatCellValue(row.getCell(21))));
                forHourWeather.setF18(new BigDecimal(dataFormatter.formatCellValue(row.getCell(22))));
                forHourWeather.setF19(new BigDecimal(dataFormatter.formatCellValue(row.getCell(23))));
                forHourWeather.setF20(new BigDecimal(dataFormatter.formatCellValue(row.getCell(24))));
                forHourWeather.setF21(new BigDecimal(dataFormatter.formatCellValue(row.getCell(25))));
                forHourWeather.setF22(new BigDecimal(dataFormatter.formatCellValue(row.getCell(26))));
                forHourWeather.setF23(new BigDecimal(dataFormatter.formatCellValue(row.getCell(27))));
                forHourWeather.setF24(new BigDecimal(dataFormatter.formatCellValue(row.getCell(28))));
            } catch (NumberFormatException e) {
                errmsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("在第").append(r + 1).append("行出现错误：").append("数值错误或格式错误！数据未插入！").append("如需要，请及时修改；<br>");
                continue;
            }
            forHourWeather.setVerTime(new Date());
            areaName = forHourWeather.getAreaname();
            // 添加到list
            objList.add(forHourWeather);
        }
        totalRowsResult = totalRows-2;
        successRows = objList.size();
        errorRows = totalRowsResult - successRows;
        return objList;
    }

    private boolean isExcel2007(String fileName) {
        return fileName.matches("^.+\\.(?i)(xlsx)$");
    }
}
