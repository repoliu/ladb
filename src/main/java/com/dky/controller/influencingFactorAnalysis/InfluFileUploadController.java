package com.dky.controller.influencingFactorAnalysis;

import com.dky.entity.influ.WeatherInfo;
import com.dky.service.influencingFactorAnalysis.FileService;
import com.dky.service.influencingFactorAnalysis.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 影响因素上传文件
 * 1、处理上传文件得请求
 * 2、保存上传内容
 *
 * @author YangSL
 */
@Controller
@RequestMapping("/influence")
public class InfluFileUploadController {
    private final FileService fileService;
    private final WeatherService weatherService;
    @Autowired
    public InfluFileUploadController(FileService fileService,WeatherService weatherService) {
        this.fileService = fileService;
        this.weatherService = weatherService;
    }

    /**
     * 注：由于表结构的变更，此功能所上传的表已弃用，所以此功能可能会弃用或者改变；
     *
     * 接收上传请求，将上传的Excel文件处理后存储到数据库和服务器上；
     *
     * @param file 接收到的上传文件，文件格式为Excel2003（*.xls）和Excel2007（*.xlsx）；
     * @return 上传信息得回调，包括错误信息
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> upload(@RequestParam(value = "file") MultipartFile file){
        Map<String,Object> result = null;
        try {
            result = fileService.readExcel(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result == null){
            return null;
        }
        List<WeatherInfo> iList = null;
        try {
            iList = weatherService.findImportRecord();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        result.put("iList", iList);
        return result;
    }
}
