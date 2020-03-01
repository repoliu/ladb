package com.dky.service.periodicityAnalysis.impl;

import com.dky.entity.LoadMonthtypeload96;
import com.dky.entity.LoadMonthtypeload96Key;
import com.dky.mapper.LoadMonthtypeload96Mapper;
import com.dky.service.periodicityAnalysis.LoadMonthtypeload96Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("loadMonthtypeload96Service")
public class LoadMonthtypeload96ServiceImpl implements LoadMonthtypeload96Service {
    @Autowired
    private LoadMonthtypeload96Mapper loadMonthtypeload96Mapper;
    @Override
    public List<LoadMonthtypeload96> selectQuery96(LoadMonthtypeload96Key key) {
        return loadMonthtypeload96Mapper.selectQuery96(key);
    }

    @Override
    public int insert(LoadMonthtypeload96 key) {
        return loadMonthtypeload96Mapper.insert(key);
    }
}
