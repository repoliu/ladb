package com.dky.service.common;

import com.dky.entity.Dbarea;

import java.util.List;

public interface DbareaService {
    List<Dbarea> findAllArea();

    List<Dbarea> findAreaByArea(int area);

    String findAreanameByArea(int area);
}
