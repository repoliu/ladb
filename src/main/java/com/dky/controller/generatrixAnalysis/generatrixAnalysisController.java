package com.dky.controller.generatrixAnalysis;

import com.dky.util.ReadProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author liuhaijian
 * @date Create in 2017/11/6
 */
@Controller
@RequestMapping("/generatrix")
public class generatrixAnalysisController {
    @RequestMapping(value="/generatrixIndex" ,method = RequestMethod.GET)
    public String periodIndex(Model model){
        model.addAttribute("areaname",ReadProperties.readProperties("generatrixArea"));
        return "generatrixAnalysis/generatrixIndex";
    }
    @RequestMapping(value="/clusterIndex" ,method = RequestMethod.GET)
    public String clusterIndex(){
        return "generatrixAnalysis/clusterAnalysis";
    }

}
