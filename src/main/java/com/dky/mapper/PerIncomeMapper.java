package com.dky.mapper;

import com.dky.entity.PerIncome;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PerIncomeMapper {
    int insert(PerIncome perIncome);
    List<PerIncome> selectQuery(PerIncome perIncome);


}
