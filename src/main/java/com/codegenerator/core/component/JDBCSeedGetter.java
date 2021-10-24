package com.codegenerator.core.component;

import com.codegenerator.core.dao.CoreDao;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class JDBCSeedGetter implements SeedGetter {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Long getAndIncrease(String key,int step) {
        CoreDao coreDao = SpringUtil.getBean(CoreDao.class);
        long val = coreDao.getValAndLock(key);
        coreDao.setSeedVal(key,val+step);
        return val;
    }
}
