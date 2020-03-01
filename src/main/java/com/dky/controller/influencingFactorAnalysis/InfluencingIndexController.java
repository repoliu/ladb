package com.dky.controller.influencingFactorAnalysis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 影响因素首页Controller类
 * 1、跳转到影响因素分析首页；
 *
 * @author YangSL
 */
@Controller
@RequestMapping("/influence")
public class InfluencingIndexController {
    /**
     * 通过拦截器，跳转到影响因素分析首页
     *
     * @return 首页所在地址的字符串形式
     */
    @RequestMapping(value="/influIndex" ,method = RequestMethod.GET)
    public String periodIndex(){
        return "influencingFactorAnalysis/influIndex";
    }
}
