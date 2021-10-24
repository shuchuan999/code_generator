package com.codegenerator.core.component;

import com.codegenerator.core.dao.CoreDao;

public class SetToOneRefresher extends Refresher {

    public static String type="set_to_one";

    @Override
    public void refresh(String key) {
        CoreDao coreDao = SpringUtil.getBean(CoreDao.class);
        coreDao.refreshVal(key,1L);
    }

}
