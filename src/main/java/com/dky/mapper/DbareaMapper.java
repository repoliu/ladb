package com.dky.mapper;

import com.dky.entity.Dbarea;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DbareaMapper {
    List<Dbarea> findAllArea();

    List<Dbarea> findAreaByArea(Integer area);

    String findAreaname(Integer area);
}