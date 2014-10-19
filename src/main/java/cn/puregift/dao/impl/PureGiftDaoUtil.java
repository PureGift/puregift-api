package cn.puregift.dao.impl;

/**
 * Created by elvin on 14-10-3.
 */
public class PureGiftDaoUtil {
    public static String generateSql(String sql, String dbName, String tableName){
        return sql.replace("$db$", dbName).replace("$table$", tableName);
    }
}
