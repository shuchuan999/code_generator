package com.codegenerator.core.component;

import com.codegenerator.core.dao.CoreDao;

import java.util.List;

public class JDBCConfigReader implements ConfigReader {

    @Override
    public List<SeedConfig> read() {
        CoreDao coreDao = SpringUtil.getBean(CoreDao.class);
        return coreDao.read();
    }

    @Override
    public SeedConfig readOneAndLock(String key) {
        CoreDao coreDao = SpringUtil.getBean(CoreDao.class);
        return coreDao.readOneAndLock(key);
    }
}
