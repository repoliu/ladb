package com.dky.service.define.impl;

import com.dky.entity.define.Template;
import com.dky.mapper.TemplateMapper;
import com.dky.service.define.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TemplateServiceImpl implements TemplateService{
    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public void insert(List<Template> list) {
        templateMapper.insert(list);
    }

    @Override
    public int numberAdd() {
        if (templateMapper.numberAdd()==null){
            return 0;
        }
        else {
            return templateMapper.numberAdd()+1;
        }

    }

    @Override
    public boolean judgeNameSame(String name) {
        boolean b=false; //返回true为存在相同值，返回false则不存在相同值
        if(templateMapper.judgeNameSame(name)>0 ){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public List<Map<String,Object>> getNameAndNumber() {
        return templateMapper.getNameAndNumber();
    }
}
