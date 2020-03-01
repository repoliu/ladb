package com.dky.mapper;

import com.dky.entity.LoadMonthtypeload288;
import com.dky.entity.LoadMonthtypeload288Key;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LoadMonthtypeload288Mapper {
    int insert(LoadMonthtypeload288 record);
    LoadMonthtypeload288 selectByPrimaryKey(LoadMonthtypeload288Key key);
    List<LoadMonthtypeload288> selectQuery288(LoadMonthtypeload288Key key);
}