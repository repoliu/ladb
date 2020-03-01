package com.dky.mapper;

import com.dky.entity.LoadMonthtypeload96;
import com.dky.entity.LoadMonthtypeload96Key;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LoadMonthtypeload96Mapper {
    int insert(LoadMonthtypeload96 record);
    LoadMonthtypeload96 selectByPrimaryKey(LoadMonthtypeload96Key key);
    List<LoadMonthtypeload96> selectQuery96(LoadMonthtypeload96Key key);
}