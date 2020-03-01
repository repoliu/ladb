package com.dky.mapper;

import com.dky.entity.PerYdl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerYdlMapper {

        int insert(PerYdl perYdl);

        List<PerYdl> selectQuery(PerYdl perYdl);



}
