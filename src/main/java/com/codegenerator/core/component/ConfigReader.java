package com.codegenerator.core.component;

import java.util.List;

public interface ConfigReader {

    List<SeedConfig> read();

    SeedConfig readOneAndLock(String key);
}
