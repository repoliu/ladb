package com.dky.controller;

import com.dky.util.ReadProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YangSL
 * @PackageName: com.dky.controller
 * @Description: 跳转首页
 * @date Create in 2017/10/14 23:15
 */
@Controller("/")
public class IndexController {

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("area", ReadProperties.readProperties("periodicityArea"));
        return "periodicityAnalysis/periodIndex";
    }

    @RequestMapping("/header")
    public String header(){
        return "header";
    }

    @RequestMapping("/treeHeader")
    public String treeHeader(){
        return "treeHeader";
    }
}
