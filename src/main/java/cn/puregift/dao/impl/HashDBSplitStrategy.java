package cn.puregift.dao.impl;

import cn.puregift.dao.DBSplitStrategy;
import cn.puregift.util.ApiUtil;

/**
 * 基于哈希的DB分库分表策略.
 *
 * range: id % (dbCount * tableCountPerDB)
 * dbName: dbPrefix + (range % dbCount + 1)
 * tableName: tablePrefix + (range / dbCount +1)
 *
 * Created by elvin on 14-9-27.
 */
public class HashDBSplitStrategy implements DBSplitStrategy {
    @Override
    public String getDBName(long id) {
        if (dbCount == 1)
            return dbPrefix;

        long hash = ApiUtil.getHash4Split(id, dbCount * tableCountPerDB);
        return dbPrefix + String.valueOf(hash % dbCount + 1);
    }

    @Override
    public String getTableName(long id) {
        if (tableCountPerDB == 1)
            return tablePrefix;

        long hash = ApiUtil.getHash4Split(id, dbCount * tableCountPerDB);

        return tablePrefix + String.valueOf(hash / dbCount + 1);
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
