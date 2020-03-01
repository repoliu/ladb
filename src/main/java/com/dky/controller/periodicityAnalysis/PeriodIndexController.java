package com.dky.controller.periodicityAnalysis;

import com.dky.util.ReadProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author YangSL
 * @PackageName: com.dky.controller.periodictyAnalysis
 * @Description: 跳入周期性分析的首页
 * @date Create in 2017/10/18 9:41
 */
@Controller
@RequestMapping("/periodicity")
public class PeriodIndexController {
    /**
     * 通过拦截器，跳转到影响因素分析首页
     * @return 首页所在地址的字符串形式
     */
    @RequestMapping(value="/perIndex" ,method = RequestMethod.GET)
    public String periodIndex(Model model){
        model.addAttribute("area", ReadProperties.readProperties("periodicityArea"));
        return "periodicityAnalysis/periodIndex";
    }

}
