package cn.puregift.dao.impl;

import cn.puregift.dao.DBSplitStrategy;
import cn.puregift.util.ApiLogger;
import cn.puregift.util.ApiUtil;
import cn.puregift.util.UuidFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基于时间的DB分库分表策略，每个月新增1张表.
 *
 * 分库：按id进行哈希.
 * 分表：从id中提取出年月(yyMM).
 *
 * Created by elvin on 14-10-1.
 */
public class DateDBSplitStrategy implements DBSplitStrategy {
    @Override
    public String getDBName(long id) {
        if (dbCount == 1)
            return dbPrefix;

        return dbPrefix + (ApiUtil.getHash4Split(id, dbCount) + 1);
    }

    @Override
    public String getTableName(long id) {
        if(tableCountPerDB == 1)
            return tablePrefix;

        long time = UuidFactory.getTimeFromUuid(id) * 1000;
        Date date = new Date(time);
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyMM");
            return tablePrefix + format.format(date);
        } catch(Exception e) {
            ApiLogger.error("DateDBSplitStrategy format data error, time=" + time, e);
            return tablePrefix;
        }
    }

    public void setDbPrefix(String dbPrefix){
        this.dbPrefix = dbPrefix;
    }

    public void setTablePrefix(String tablePrefix){
        this.tablePrefix = tablePrefix;
    }

    public void setDbCount(int dbCount){
        this.dbCount = dbCount;
    }

    public void setTableCountPerDB(int tableCountPerDB){
        this.tableCountPerDB = tableCountPerDB;
    }

    protected String dbPrefix;
    protected String tablePrefix;
    protected int dbCount;
    protected int tableCountPerDB;
}
