package com.dky.controller.generatrixAnalysis;

import com.dky.controller.generatrixAnalysis.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于跳转
 * Created by henry on 2017/3/17.
 */
@Controller
public class RequestController extends BaseController {

    @RequestMapping("showPlan")
    public String RedirectShowPlan(){
        return "showPlan";
    }
}
