package com.dky.service.influencingFactorAnalysis.impl;

import com.dky.entity.Dbarea;
import com.dky.entity.influ.ForHourWeather;
import com.dky.entity.influ.ForWeather;
import com.dky.entity.influ.WeatherInfo;
import com.dky.mapper.DbareaMapper;
import com.dky.mapper.influ.*;
import com.dky.service.influencingFactorAnalysis.FileService;
import com.dky.util.FileUtils;
import com.dky.util.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {
    private final DbareaMapper dbareaMapper;
    private final ForHourWeatherMapper forHourWeatherMapper;
    private final HisHourWeatherMapper hisHourWeatherMapper;
    private final ForWeatherMapper forWeatherMapper;
    private final HisWeatherMapper hisWeatherMapper;
    private final WeatherInfoMapper weatherInfoMapper;

    /**
     * 使用构造方法注入service，可以明确成员变量的加载顺序；
     * 加上final只会在程序启动的时候初始化一次，并且在程序运行的时候不会再改变；
     */
    @Autowired
    public FileServiceImpl(DbareaMapper dbareaMapper, ForHourWeatherMapper forHourWeatherMapper,
                           HisHourWeatherMapper hisHourWeatherMapper, ForWeatherMapper forWeatherMapper,
                           HisWeatherMapper hisWeatherMapper, WeatherInfoMapper weatherInfoMapper) {
        this.forHourWeatherMapper = forHourWeatherMapper;
        this.dbareaMapper = dbareaMapper;
        this.hisHourWeatherMapper = hisHourWeatherMapper;
        this.forWeatherMapper = forWeatherMapper;
        this.hisWeatherMapper = hisWeatherMapper;
        this.weatherInfoMapper = weatherInfoMapper;
    }

    /**
     * 调用工具类对Excel文件进行解析
     *
     * @param file 传回的文件
     * @return 上传信息得回调，包括错误信息
     * @throws IOException 文件流错误
     */
    @Override
    @SuppressWarnings("all")
    public Map<String, Object> readExcel(MultipartFile file) throws IOException {
        Map<String, Object> msg = new HashMap<>();

        //这个字段用来定位上传信息弹出位置
        int position = 0;

        //读取Excel中的信息
        ReadExcel readExcel = new ReadExcel();
        Map<String, List<Object>> objMap;
        objMap = readExcel.getExcelInfo(file);
        if (objMap == null) {
            return null;
        }
        String type = null;

        //遍历map，把数据插入到相应的数据表中
        Iterator<Map.Entry<String, List<Object>>> it = objMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, List<Object>> entry = it.next();
            List<Object> objList = entry.getValue();
            String key = entry.getKey();
            switch (key) {
                case "HisHourWeather":
                    type = "实际24小时气象信息";
                    msg = insertIntoHourWeather(objList, key, type, readExcel.getErrmsg());
                    break;
                case "ForHourWeather":
                    type = "预测24小时气象信息";
                    msg = insertIntoHourWeather(objList, key, type, readExcel.getErrmsg());
                    position = 50;
                    break;
                case "ForWeather":
                    type = "预测气象状况信息";
                    msg = insertIntoWeather(objList, key, type, readExcel.getErrmsg());
                    position = 100;
                    break;
                case "HisWeather":
                    type = "实际气象状况信息";
                    msg = insertIntoWeather(objList, key, type, readExcel.getErrmsg());
                    position = 150;
                    break;
                default:
                    break;
            }
        }
        String areaName = readExcel.getAreaName();
        String suffix = readExcel.getSuffix();
        String path = FileUtils.creatPath("气象印象因素",areaName,type,suffix);
        FileUtils.saveFileByStream(file.getInputStream(), path);
        msg.put("type", type);
        msg.put("readMsg", readExcel.getErrmsg());
        msg.put("readTotalRows", readExcel.getTotalRowsResult());
        msg.put("readSuccessRows", readExcel.getSuccessRows());
        msg.put("readErrorRows", readExcel.getErrorRows());
        msg.put("position", position);
        return msg;
    }

    //插入到实际/预测气象信息表中
    private Map<String, Object> insertIntoWeather(List<Object> objList, String key, String type, String errorExcel) {
        List<Dbarea> dbareaList = dbareaMapper.findAllArea();
        StringBuilder sb = new StringBuilder();
        int updateRows = 0;
        Map<String, Integer> map = new HashMap<>();
        for (Dbarea d : dbareaList) {
            map.put(d.getDname(), d.getArea());
        }
        int a;
        int i;
        int success = 0;
        int error = 0;
        String aName = null;
        StringBuilder errorMsg = new StringBuilder();
        if (key.equals("ForWeather")) {
            for (Object obj : objList) {
                ForWeather forWeather = (ForWeather) obj;
                Integer area = map.get(forWeather.getAreaname());
                if (area == null) {
                    error++;
                    errorMsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("写入/更新记录失败！地区不存在！时间：").append(forWeather.getMdate()).append("；地区：").append(forWeather.getAreaname()).append("；<br>");
                    continue;
                }
                aName = forWeather.getAreaname();
                forWeather.setArea(area);
                Map<String, Object> param = new HashMap<>();
                param.put("areaname", forWeather.getAreaname());
                param.put("mdate", forWeather.getMdate());
                a = forWeatherMapper.isExist(param);
                if (a > 0) {
                    i = forWeatherMapper.updateByKey(forWeather);
                    updateRows++;
                    sb.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("更新了一条记录，时间：").append(forWeather.getMdate()).append("；地点：").append(forWeather.getAreaname()).append("；");
                    sb.append("<br>");
                } else {
                    i = forWeatherMapper.insert(forWeather);
                }
                if (i > 0) {
                    success++;
                } else {
                    error++;
                    errorMsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("写入/更新记录失败，时间：").append(forWeather.getMdate()).append("；地区：").append(forWeather.getAreaname()).append("；<br>");
                }
            }
        } else if (key.equals("HisWeather")) {
            for (Object obj : objList) {
                ForWeather forWeather = (ForWeather) obj;
                Integer area = map.get(forWeather.getAreaname());
                if (area == null) {
                    error++;
                    errorMsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("写入/更新记录失败！地区不存在！时间：").append(forWeather.getMdate()).append("；地区：").append(forWeather.getAreaname()).append("；<br>");
                    continue;
                }
                aName = forWeather.getAreaname();
                forWeather.setArea(area);
                Map<String, Object> param = new HashMap<>();
                param.put("mdate", forWeather.getMdate());
                param.put("areaname", forWeather.getAreaname());
                a = hisWeatherMapper.isExist(param);
                if (a > 0) {
                    updateRows++;
                    i = hisWeatherMapper.updateByKey(forWeather);
                    sb.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("更新了一条记录，时间：").append(forWeather.getMdate()).append("；地点：").append(forWeather.getAreaname()).append("；");
                    sb.append("<br>");
                } else {
                    i = hisWeatherMapper.insert(forWeather);
                }
                if (i > 0) {
                    success++;
                } else {
                    error++;
                    errorMsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("写入/更新记录失败，时间：").append(forWeather.getMdate()).append("；地区：").append(forWeather.getAreaname()).append("；<br>");
                }
            }
        }
        if ((updateRows + success) > 0) {
            insertWeatherInfo(sb.toString() + errorExcel + errorMsg.toString(), type + "错误信息", aName);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("updateRows", updateRows);
        result.put("updateMsg", sb.toString());
        result.put("successRows", success);
        result.put("errorRows", error);
        result.put("errorMsg", errorMsg.toString());
        return result;
    }

    //插入到实际/预测24小时气象信息表中
    private Map<String, Object> insertIntoHourWeather(List<Object> objList, String key, String type, String errorExcel) {
        List<Dbarea> dbareaList = dbareaMapper.findAllArea();
        Map<String, Integer> map = new HashMap<>();
        for (Dbarea d : dbareaList) {
            map.put(d.getDname(), d.getArea());
        }
        StringBuilder sb = new StringBuilder();
        int updateRows = 0;
        int a;
        int i;
        int success = 0;
        int error = 0;
        String aName = null;
        StringBuilder errorMsg = new StringBuilder();
        if (key.equals("ForHourWeather")) {
            for (Object obj : objList) {
                ForHourWeather forHourWeather = (ForHourWeather) obj;
                Integer area = map.get(forHourWeather.getAreaname());
                if (area == null) {
                    error++;
                    errorMsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("写入/更新记录失败！地区不存在！时间：").append(forHourWeather.getMdate()).append("；地区：").append(forHourWeather.getAreaname())
                            .append("；天气类型：").append(forHourWeather.getWeatherType()).append("。<br>");
                    continue;
                }
                aName = forHourWeather.getAreaname();
                forHourWeather.setArea(area);
                Map<String, Object> param = new HashMap<>();
                param.put("mdate", forHourWeather.getMdate());
                param.put("areaname", forHourWeather.getAreaname());
                param.put("weatherType", forHourWeather.getWeatherType());
                a = forHourWeatherMapper.isExist(param);
                if (a > 0) {
                    i = forHourWeatherMapper.updateByKey(forHourWeather);
                    updateRows++;
                    sb.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("更新了一条记录，时间：").append(forHourWeather.getMdate()).append("；地点：").append(forHourWeather.getAreaname()).append("；天气类型：")
                            .append(forHourWeather.getWeatherType()).append("；<br>");
                } else {
                    i = forHourWeatherMapper.insert(forHourWeather);
                }
                if (i > 0) {
                    success++;
                } else {
                    error++;
                    errorMsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("写入/更新记录失败，时间：").append(forHourWeather.getMdate()).append("；地区：").append(forHourWeather.getAreaname())
                            .append("；天气类型：").append(forHourWeather.getWeatherType()).append("。<br>");
                }
            }
        } else if (key.equals("HisHourWeather")) {
            for (Object obj : objList) {
                ForHourWeather forHourWeather = (ForHourWeather) obj;
                Integer area = map.get(forHourWeather.getAreaname());
                if (area == null) {
                    error++;
                    errorMsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("写入/更新记录失败！地区不存在！时间：").append(forHourWeather.getMdate()).append("；地区：").append(forHourWeather.getAreaname())
                            .append("；天气类型：").append(forHourWeather.getWeatherType()).append("。<br>");
                    continue;
                }
                aName = forHourWeather.getAreaname();
                forHourWeather.setArea(area);
                Map<String, Object> param = new HashMap<>();
                param.put("areaname", forHourWeather.getAreaname());
                param.put("mdate", forHourWeather.getMdate());
                param.put("weatherType", forHourWeather.getWeatherType());
                a = hisHourWeatherMapper.isExist(param);
                if (a > 0) {
                    updateRows++;
                    i = hisHourWeatherMapper.updateByKey(forHourWeather);
                    sb.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("更新了一条记录，时间：").append(forHourWeather.getMdate()).append("；地点：").append(forHourWeather.getAreaname()).append("；天气类型：")
                            .append(forHourWeather.getWeatherType()).append("；<br>");
                } else {
                    i = hisHourWeatherMapper.insert(forHourWeather);
                }
                if (i > 0) {
                    success++;
                } else {
                    error++;
                    errorMsg.append("&nbsp;&nbsp;&nbsp;&nbsp;").append("写入记录失败，时间：").append(forHourWeather.getMdate()).append("；地区：").append(forHourWeather.getAreaname())
                            .append("；天气类型：").append(forHourWeather.getWeatherType()).append("。<br>");
                }
            }
        }
        if ((updateRows + success) > 0) {
            insertWeatherInfo(sb.toString() + errorExcel + errorMsg.toString(), type + "错误信息", aName);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("updateRows", updateRows);
        result.put("updateMsg", sb.toString());
        result.put("successRows", success);
        result.put("errorRows", error);
        result.put("errorMsg", errorMsg.toString());
        return result;
    }

    //插入导入记录到表中
    private void insertWeatherInfo(String errorMsg, String type, String aName) {
        WeatherInfo info = new WeatherInfo();
        InputStream in = null;
        try {
            in = new ByteArrayInputStream(errorMsg.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String notes = null;
        try {
            notes = FileUtils.creatPath("气象印象因素",aName,type,".html");
            FileUtils.saveFileByStream(in, notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        info.setAreaName(aName);
        info.setWeatherResult("成功");
        info.setTime(new Date());
        info.setNotes(notes);
        weatherInfoMapper.insert(info);
    }
}
