package com.codegenerator.core.component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SeedContext {

    public static ConfigReader configReader;

    public static Map<String, Supplier<Refresher>> refresherMap=new HashMap<>();

    public static SeedGetter getter;
}
