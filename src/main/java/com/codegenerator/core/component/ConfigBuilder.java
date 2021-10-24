package com.codegenerator.core.component;

import java.util.function.Supplier;

public class ConfigBuilder {

    public ConfigBuilder setConfigReader(ConfigReader reader){
        SeedContext.configReader=reader;
        return this;
    }

    public ConfigBuilder addRefresher(String key, Supplier<Refresher> refresher){
        SeedContext.refresherMap.put(key,refresher);
        return this;
    }

    public ConfigBuilder setSeedGetter(SeedGetter seedGetter){
        SeedContext.getter=seedGetter;
        return this;
    }
}
