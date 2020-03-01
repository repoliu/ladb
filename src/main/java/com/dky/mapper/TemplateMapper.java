package com.dky.mapper;

import com.dky.entity.define.Template;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TemplateMapper {

    void insert(List<Template> list);

    Integer numberAdd();

    int judgeNameSame(String name);

    List<Map<String,Object>> getNameAndNumber();


}
