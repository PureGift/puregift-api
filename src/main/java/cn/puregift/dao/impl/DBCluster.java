package cn.puregift.dao.impl;

import cn.puregift.dao.DBSplitStrategy;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * 分布式DB集群类.
 * Created by elvin on 14-9-30.
 */
public class DBCluster {
    /**
     * 获取数据所在的DB.
     * @param id
     * @return
     */
    public String getDBName(long id) {
        return dbSplitStrategy.getDBName(id);
    }

    /**
     * 获取数据所在的表.
     * @param id
     * @return
     */
    public String getTableName(long id){
        return dbSplitStrategy.getTableName(id);
    }

    /**
     * 获取操作数据所用的jdbcTemplate
     * @param id
     * @return
     */
    public JdbcTemplate getJdbcTemplate(long id) throws IllegalArgumentException{
        String dbName = getDBName(id);
        JdbcTemplate jt = jts.get(dbName);

        if (jt != null) {
            return jt;
        }else {
            throw new IllegalArgumentException(new StringBuilder(128).append("DBCluster no the database, id=").append(id).append(", dbName=").append(dbName).toString());
        }
    }

    public JdbcTemplate getIdxJdbcTemplate(String dbName) throws IllegalArgumentException{
        JdbcTemplate jt = jts.get(dbName);

        if (jt != null) {
            return jt;
        }else{
            throw new IllegalArgumentException(new StringBuilder(64).append("DBCluster no the database, dbName=").append(dbName).toString());
        }
    }

    public void setJts(Map<String, JdbcTemplate> jts){
        this.jts = jts;
    }

    public void setDbSplitStrategy(DBSplitStrategy dbSplitStrategy){
        this.dbSplitStrategy = dbSplitStrategy;
    }

    protected Map<String, JdbcTemplate> jts;
    protected DBSplitStrategy dbSplitStrategy;
}
