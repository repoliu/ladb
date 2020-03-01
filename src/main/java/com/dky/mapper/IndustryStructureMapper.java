package com.dky.mapper;

import com.dky.entity.IndustryStructure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndustryStructureMapper {
    List<IndustryStructure> selectQuery(IndustryStructure industryStructure);
    List<IndustryStructure> select();


    int insert(IndustryStructure industryStructure);




}
