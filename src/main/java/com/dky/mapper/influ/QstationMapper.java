package com.dky.mapper.influ;

import com.dky.entity.influ.Qstation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QstationMapper {
    String findStcdByAreaname(String stnm);

    void insert(Qstation qstation);
}