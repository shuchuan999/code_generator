package com.codegenerator.core.dao;

import com.codegenerator.core.component.SeedConfig;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CoreDao {


    @Select({
            "select * from seed_config "
    })
    @Results(id = "seedConfigMap",
            value = {
                    @Result(property = "key",column = "key"),
                    @Result(property = "isAutoRefresh",column = "is_auto_refresh"),
                    @Result(property = "cron",column = "cron"),
                    @Result(property = "lastUpdateTime",column = "last_update_time"),
                    @Result(property = "refreshType",column = "refresh_type")
            }
    )
    public List<SeedConfig> read();

    @Select({
            "select * from seed_config where key=#{key} for update"
    })
    @ResultMap(value = "seedConfigMap")
    public SeedConfig readOneAndLock(@Param("key") String key);

    @Update({
            "update seed_config set seed_val=#{val} where seed_key=#{key} "
    })
    public int refreshVal(@Param("key") String key,@Param("val") Long val);

    @Select({
            "select seed_val from seed_config where key=#{key} for update"
    })
    public long getValAndLock(@Param("key") String key);

    @Update({
            "update seed_val set seed_val=#{newVal} where key=#{key} "
    })
    public int setSeedVal(@Param("key") String key,@Param("newVal") long newVal);
}
