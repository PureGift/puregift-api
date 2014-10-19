package cn.puregift.dao;

/**
 * DB分库分表策略.
 * Created by elvin on 14-9-27.
 */
public interface DBSplitStrategy {
    //根据ID获取数据所在库.
    public String getDBName(long id);

    //根据ID获取数据所在表.
    public String getTableName(long id);
}
