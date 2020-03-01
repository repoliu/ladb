package com.dky.controller.generatrixAnalysis;

import com.dky.entity.Dbarea;
import com.dky.service.generatrixAnalysis.DbAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by sks on 2017/3/28.
 */
@Controller
@RequestMapping("/DbAreaData")
public class DbAreaController {

    @Autowired
    DbAreaService dbAreaService;
    @RequestMapping("/show")
    ModelAndView getDbArea() {
        List<Dbarea> dbArealist = dbAreaService.selectDbArea();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("areas", dbArealist);
        //modelAndView.setViewName("generatrixAnalysis/pages/index");
        modelAndView.setViewName("generatrixAnalysis/generatrixDataQuery");
        return modelAndView;
    }

}
