package com.codegenerator.core.component;

//可能破坏随时间递增的性质
public class CachedSeedGetter {
    private static long cache=0;
    private static long val=0;

    public synchronized static Long cachedGet(String key,int cacheLength){
        if(cache==0){
            Long readVal = SeedContext.getter.getAndIncrease(key, cacheLength+1);
            cache=cacheLength;
            val=readVal;
            return readVal;
        }else{
            cache--;
            return ++val;
        }
    }
}
