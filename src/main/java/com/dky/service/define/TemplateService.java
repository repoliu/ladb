package com.dky.service.define;

import com.dky.entity.define.Template;

import java.util.List;
import java.util.Map;

public interface TemplateService {
    void insert(List<Template> list);

    int numberAdd();

    boolean judgeNameSame(String name);

    List<Map<String,Object>> getNameAndNumber();
}
