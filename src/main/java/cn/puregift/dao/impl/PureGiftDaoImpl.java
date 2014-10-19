package cn.puregift.dao.impl;

import cn.puregift.Model.OuterAccount;
import cn.puregift.Model.OuterAccountType;
import cn.puregift.Model.Sex;
import cn.puregift.Model.UserInfo;
import cn.puregift.dao.PureGiftDao;
import cn.puregift.util.ApiConstant;
import cn.puregift.util.ApiLogger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by elvin on 14-7-5.
 */
public class PureGiftDaoImpl implements PureGiftDao{
    /**
     * 保存用户信息.
     *
     * @param newUser
     * @return
     */
    @Override
    public boolean addUserInfo(UserInfo newUser) throws IllegalArgumentException{
        long start = System.currentTimeMillis();

        DBCluster dbCluster = dbClusters.get(ApiConstant.DB_USER_INFO);
        String dbName = dbCluster.getDBName(newUser.getUid());
        String tableName = dbCluster.getTableName(newUser.getUid());
        String sql = PureGiftDaoUtil.generateSql(ADD_USER_INFO, dbName, tableName);

        boolean addRet = false;
        try {
            JdbcTemplate jt = dbCluster.getIdxJdbcTemplate(dbName);
            addRet = jt.update(sql, new Object[]{newUser.getUid(), newUser.getName(), newUser.getHeadImgUrl(), newUser.getSex().get(),
                    newUser.getBirthday() == null ?  null : new java.sql.Date(newUser.getBirthday().getTime())}) > 0;
        }catch(IllegalArgumentException illegalArgumentException){
            ApiLogger.error("PureGiftDaoImpl addUserInfo error when getIdxJdbcTemplate, dbName=" + dbName, illegalArgumentException);
            throw illegalArgumentException;
        }catch(DataAccessException dataAccessException){
            ApiLogger.error("PureGiftDaoImpl addUserInfo error when update db, sql=" + sql, dataAccessException);
            throw dataAccessException;
        }

        long end = System.currentTimeMillis();
        if(end - start > ApiConstant.OP_DB_TIMEOUT){
            ApiLogger.warn("PureGiftDaoImpl addUserInfo too slow, useTime=" + (end - start));
        }

        return addRet;
    }

    /**
     * 获取某个用户的信息.
     *
     * @param userId
     * @return
     */
    @Override
    public UserInfo getUserInfo(long userId) {
        long start = System.currentTimeMillis();

        DBCluster dbCluster = dbClusters.get(ApiConstant.DB_USER_INFO);
        String dbName = dbCluster.getDBName(userId);
        String tableName = dbCluster.getTableName(userId);
        String sql = PureGiftDaoUtil.generateSql(GET_USER_INFO, dbName, tableName);

        UserInfo userInfo = null;
        try {
            JdbcTemplate jt = dbCluster.getIdxJdbcTemplate(dbName);
            userInfo = jt.query(sql, new Object[]{userId}, new ResultSetExtractor<UserInfo>() {
                @Override
                public UserInfo extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    if(resultSet.next()){
                        UserInfo tmpUserInfo = new UserInfo();
                        tmpUserInfo.setUid(resultSet.getLong("userId"));
                        tmpUserInfo.setName(resultSet.getString("name"));
                        tmpUserInfo.setHeadImgUrl(resultSet.getString("headImgUrl"));
                        tmpUserInfo.setSex(Sex.valueOf(resultSet.getByte("sex")));
                        java.sql.Date birthday = resultSet.getDate("birthday");
                        if(null != birthday)
                            tmpUserInfo.setBirthday(new java.util.Date(birthday.getTime()));

                        return tmpUserInfo;
                    }else
                        return null;
                }
            });
        }catch(IllegalArgumentException illegalArgumentException){
            ApiLogger.error("PureGiftDaoImpl getUserInfo error when getIdxJdbcTemplate, dbName=" + dbName, illegalArgumentException);
            throw illegalArgumentException;
        }catch(DataAccessException dataAccessException){
            ApiLogger.error("PureGiftDaoImpl getUserInfo error when query db, sql=" + sql, dataAccessException);
            throw dataAccessException;
        }

        long end = System.currentTimeMillis();
        if(end - start > ApiConstant.OP_DB_TIMEOUT){
            ApiLogger.warn("PureGiftDaoImpl getUserInfo too slow, useTime=" + (end - start));
        }

        return userInfo;
    }

    /**
     * 更新用户信息.
     *
     * @param updateUser
     * @return
     */
    @Override
    public boolean updateUserInfo(UserInfo updateUser) {
        long start = System.currentTimeMillis();

        DBCluster dbCluster = dbClusters.get(ApiConstant.DB_USER_INFO);
        String dbName = dbCluster.getDBName(updateUser.getUid());
        String tableName = dbCluster.getTableName(updateUser.getUid());
        String sql = PureGiftDaoUtil.generateSql(UPDATE_USER_INFO, dbName, tableName);

        boolean updateRet = false;
        try {
            JdbcTemplate jt = dbCluster.getIdxJdbcTemplate(dbName);
            updateRet = jt.update(sql, new Object[]{updateUser.getName(), updateUser.getHeadImgUrl(), updateUser.getSex().get(),
                    updateUser.getBirthday() == null ?  null : new java.sql.Date(updateUser.getBirthday().getTime()), updateUser.getUid()}) > 0;
        }catch(IllegalArgumentException illegalArgumentException){
            ApiLogger.error("PureGiftDaoImpl updateUserInfo error when getIdxJdbcTemplate, dbName=" + dbName, illegalArgumentException);
            throw illegalArgumentException;
        }catch(DataAccessException dataAccessException){
            ApiLogger.error("PureGiftDaoImpl updateUserInfo error when update db, sql=" + sql, dataAccessException);
            throw dataAccessException;
        }

        long end = System.currentTimeMillis();
        if(end - start > ApiConstant.OP_DB_TIMEOUT){
            ApiLogger.warn("PureGiftDaoImpl updateUserInfo too slow, useTime=" + (end - start));
        }

        return updateRet;
    }

    /**
     * 获取第三方账号对应的站内uid.
     *
     * @param outerUid
     * @param type
     * @return
     */
    @Override
    public long getInnerUidByOuterUid(long outerUid, OuterAccountType type) {
        long start = System.currentTimeMillis();

        DBCluster dbCluster = dbClusters.get(ApiConstant.DB_OUTER_ACCOUNT);
        String dbName = dbCluster.getDBName(outerUid);
        String tableName = dbCluster.getTableName(outerUid);
        String sql = PureGiftDaoUtil.generateSql(GET_INNERUID_BY_OUTERUID, dbName, tableName);

        long innerUid = 0;
        try {
            JdbcTemplate jt = dbCluster.getIdxJdbcTemplate(dbName);
            innerUid = jt.query(sql, new Object[]{outerUid, type.get()}, new ResultSetExtractor<Long>() {
                @Override
                public Long extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    if(resultSet.next()){
                        long uid = resultSet.getLong("innerUid");
                        return uid;
                    }else
                        return 0l;
                }
            });
        }catch(IllegalArgumentException illegalArgumentException){
            ApiLogger.error("PureGiftDaoImpl getInnerUidByOuterUid error when getIdxJdbcTemplate, dbName=" + dbName, illegalArgumentException);
            throw illegalArgumentException;
        }catch(DataAccessException dataAccessException){
            ApiLogger.error("PureGiftDaoImpl getInnerUidByOuterUid error when query db, sql=" + sql, dataAccessException);
            throw dataAccessException;
        }

        long end = System.currentTimeMillis();
        if(end - start > ApiConstant.OP_DB_TIMEOUT){
            ApiLogger.warn("PureGiftDaoImpl getInnerUidByOuterUid too slow, useTime=" + (end - start));
        }

        return innerUid;
    }

    /**
     * 新增第三方账号
     *
     * @param outerUid
     * @param type
     * @param innerUid
     * @return
     */
    @Override
    public boolean addOuterAccount(long outerUid, OuterAccountType type, long innerUid) {
        long start = System.currentTimeMillis();

        DBCluster dbCluster = dbClusters.get(ApiConstant.DB_OUTER_ACCOUNT);
        String dbName = dbCluster.getDBName(outerUid);
        String tableName = dbCluster.getTableName(outerUid);
        String sql = PureGiftDaoUtil.generateSql(ADD_INNERUID_BY_OUTERUID, dbName, tableName);

        boolean addRet = false;
        try {
            JdbcTemplate jt = dbCluster.getIdxJdbcTemplate(dbName);
            addRet = jt.update(sql, new Object[]{outerUid, type.get(), innerUid}) > 0;
        }catch(IllegalArgumentException illegalArgumentException){
            ApiLogger.error("PureGiftDaoImpl addOuterAccount error when getIdxJdbcTemplate, dbName=" + dbName, illegalArgumentException);
            throw illegalArgumentException;
        }catch(DataAccessException dataAccessException){
            ApiLogger.error("PureGiftDaoImpl addOuterAccount error when update db, sql=" + sql, dataAccessException);
            throw dataAccessException;
        }

        long end = System.currentTimeMillis();
        if(end - start > ApiConstant.OP_DB_TIMEOUT){
            ApiLogger.warn("PureGiftDaoImpl addOuterAccount too slow, useTime=" + (end - start));
        }

        return addRet;
    }

    /**
     * 更新第三方账号.
     *
     * @param outerUid
     * @param type
     * @param innerUid
     * @return
     */
    @Override
    public boolean updateOuterAccount(long outerUid, OuterAccountType type, long innerUid) {
        long start = System.currentTimeMillis();

        DBCluster dbCluster = dbClusters.get(ApiConstant.DB_OUTER_ACCOUNT);
        String dbName = dbCluster.getDBName(outerUid);
        String tableName = dbCluster.getTableName(outerUid);
        String sql = PureGiftDaoUtil.generateSql(UPDATE_INNERUID_BY_OUTERUID, dbName, tableName);

        boolean updateRet = false;
        try {
            JdbcTemplate jt = dbCluster.getIdxJdbcTemplate(dbName);
            updateRet = jt.update(sql, new Object[]{innerUid, outerUid, type.get()}) > 0;
        }catch(IllegalArgumentException illegalArgumentException){
            ApiLogger.error("PureGiftDaoImpl updateOuterAccount error when getIdxJdbcTemplate, dbName=" + dbName, illegalArgumentException);
            throw illegalArgumentException;
        }catch(DataAccessException dataAccessException){
            ApiLogger.error("PureGiftDaoImpl updateOuterAccount error when update db, sql=" + sql, dataAccessException);
            throw dataAccessException;
        }

        long end = System.currentTimeMillis();
        if(end - start > ApiConstant.OP_DB_TIMEOUT){
            ApiLogger.warn("PureGiftDaoImpl updateOuterAccount too slow, useTime=" + (end - start));
        }

        return updateRet;
    }


    public void setDbClusters(Map<String, DBCluster> dbClusters){
        this.dbClusters = dbClusters;
    }

    protected Map<String, DBCluster> dbClusters;

    private final String ADD_USER_INFO = "insert into $db$.$table$ (userId,name,headImgUrl,sex,birthday) values(?,?,?,?,?)";
    private final String GET_USER_INFO = "select userId,name,headImgUrl,sex,birthday from $db$.$table$ where userId = ?";
    private final String UPDATE_USER_INFO = "update $db$.$table$ set name=?,headImgUrl=?,sex=?,birthday=? where userId=?";
    private final String GET_INNERUID_BY_OUTERUID = "select innerUid from $db$.$table$ where outerUid=? and type=?";
    private final String ADD_INNERUID_BY_OUTERUID = "insert into $db$.$table$ (outerUid,type,innerUid) values(?,?,?)";
    private final String UPDATE_INNERUID_BY_OUTERUID = "update $db$.$table$ set innerUid=? where outerUid=? and type=?";
}
