package com.dky.mapper;

import com.dky.entity.PerGdp;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PerGdpMapper {

    int insert(PerGdp perGdp);


    List<PerGdp> selectQuery(PerGdp perGdp);
















}
