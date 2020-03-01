package com.dky.service.periodicityAnalysis.impl;

import com.dky.entity.LoadMonthtypeload288;
import com.dky.entity.LoadMonthtypeload288Key;
import com.dky.mapper.LoadMonthtypeload288Mapper;
import com.dky.service.periodicityAnalysis.LoadMonthtypeload288Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("loadMonthtypeload288Service")
public class LoadMonthtypeload288ServiceImpl implements LoadMonthtypeload288Service {
    @Autowired
    private LoadMonthtypeload288Mapper loadMonthtypeload288Mapper;
    @Override
    public List<LoadMonthtypeload288> selectQuery288(LoadMonthtypeload288Key key) {
        return loadMonthtypeload288Mapper.selectQuery288(key);
    }

    @Override
    public int insert(LoadMonthtypeload288 record) {
        return loadMonthtypeload288Mapper.insert(record);
    }
}
